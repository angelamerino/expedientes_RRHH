/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.cultura.rrhh.manejadores;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.apache.log4j.PropertyConfigurator;
import org.primefaces.context.RequestContext;
import sv.gob.cultura.rrhh.entidades.Dependencias;
import sv.gob.cultura.rrhh.entidades.DirNacional;
import sv.gob.cultura.rrhh.entidades.Empleados;
import sv.gob.cultura.rrhh.entidades.FamiliaDependientesEmp;
import sv.gob.cultura.rrhh.entidades.Parentesco;
import sv.gob.cultura.rrhh.facades.DependenciasFacade;
import sv.gob.cultura.rrhh.facades.DirNacionalFacade;
import sv.gob.cultura.rrhh.facades.EmpleadosFacade;
import sv.gob.cultura.rrhh.facades.FamiliaDependientesEmpFacade;
import sv.gob.cultura.rrhh.facades.ParentescoFacade;

/**
 *
 * @author Edwin
 */
@Named(value = "manejadorDependientesFamilia")
@ViewScoped
public class manejadorDependientesFamilia implements Serializable {

    public manejadorDependientesFamilia() {
    }

// ************** LLAMADA A LOS ENTERPRICE JAVA BEANS **************************
    @EJB
    private FamiliaDependientesEmpFacade familiaDependientesEmpFacade;
    @EJB
    private ParentescoFacade parentescoFacade;
    @EJB
    private EmpleadosFacade empleadosFacade;
    @EJB
    private DependenciasFacade dependenciasFacade;
    @EJB
    private DirNacionalFacade dirNacionalFacade;
    @Resource(mappedName = "RRHH")
    DataSource datasource;

//*********************** OBJETOS DE LOS ENTIDADES ***************************** 
    FamiliaDependientesEmp familiaDependientesEmp = new FamiliaDependientesEmp();
    Parentesco parentesco = new Parentesco();
//****** VARIABLES QUE CONTRENDRAN ID´S O STRING DE FORMULARIOS ****************
    private Date fechaNacFamiliaDependiente = new Date();       // fecha naciemeinto familiares
    private int edadFamiliaDependiente;                         // edad familiares calculada a partir de fecha
    private int direccionNacional;                              // id direccion nacional para filtar dependencias
    private int dependecia;                                     // id de dependecias para filtrar empleados
    private int empleadoSelecionado;                            // id d eempleado selecinado
    private String nombreEmp;                                   // nombre de empleado selecinado
    private String NR;                                          // NR de empleado para realizar busquda
    private int edadMin;
    private int edadMax;
    private int idParentesco;

//********************** GET DE ENTERPRICE JAVA BEAN ***************************
    public FamiliaDependientesEmpFacade getFamiliaDependientesEmpFacade() {
        return familiaDependientesEmpFacade;
    }

    public ParentescoFacade getParentescoFacade() {
        return parentescoFacade;
    }

    public EmpleadosFacade getEmpleadosFacade() {
        return empleadosFacade;
    }

    public DependenciasFacade getDependenciasFacade() {
        return dependenciasFacade;
    }

    public DirNacionalFacade getDirNacionalFacade() {
        return dirNacionalFacade;
    }

//******************* GET y SET DE OBJETOS DE ENTIDADES ************************
    public FamiliaDependientesEmp getFamiliaDependientesEmp() {
        return familiaDependientesEmp;
    }

    public void setFamiliaDependientesEmp(FamiliaDependientesEmp familiaDependientesEmp) {
        this.familiaDependientesEmp = familiaDependientesEmp;
    }

    public Parentesco getParentesco() {
        return parentesco;
    }

    public void setParentesco(Parentesco parentesco) {
        this.parentesco = parentesco;
    }

    public Date getFechaNacFamiliaDependiente() {
        return fechaNacFamiliaDependiente;
    }

    public void setFechaNacFamiliaDependiente(Date fechaNacFamiliaDependiente) {
        this.fechaNacFamiliaDependiente = fechaNacFamiliaDependiente;
        this.setEdadFamiliaDependiente(edad(fechaNacFamiliaDependiente));
    }

    public int getEdadFamiliaDependiente() {
        return edadFamiliaDependiente;
    }

    public void setEdadFamiliaDependiente(int edadFamiliaDependiente) {
        this.edadFamiliaDependiente = edadFamiliaDependiente;
    }

    public int getDireccionNacional() {
        return direccionNacional;
    }

    public void setDireccionNacional(int direccionNacional) {
        this.direccionNacional = direccionNacional;
        this.setNombreEmp("");
        this.setDependecia(0);
        this.setEmpleadoSelecionado(0);
    }

    public int getDependecia() {
        return dependecia;
    }

    public void setDependecia(int dependecia) {
        this.dependecia = dependecia;
        this.setNombreEmp("");
        this.setEmpleadoSelecionado(0);
    }

    public int getEmpleadoSelecionado() {
        return empleadoSelecionado;
    }

    public void setEmpleadoSelecionado(int empleadoSelecionado) {
        this.empleadoSelecionado = empleadoSelecionado;
        Empleados emp = getEmpleadosFacade().find(getEmpleadoSelecionado());
        if (emp == null) {
            this.setNombreEmp("");
        } else {
            this.setNombreEmp(emp.getNombreEmpleado());
        }
    }

    public String getNombreEmp() {
        return nombreEmp;
    }

    public void setNombreEmp(String nombreEmp) {
        this.nombreEmp = nombreEmp;
    }

    public String getNR() {
        return NR;
    }

    public void setNR(String NR) {
        this.NR = NR;
    }

    public int getEdadMin() {
        return edadMin;
    }

    public void setEdadMin(int edadMin) {
        this.edadMin = edadMin;
    }

    public int getEdadMax() {
        return edadMax;
    }

    public void setEdadMax(int edadMax) {
        this.edadMax = edadMax;
    }

    public int getIdParentesco() {
        return idParentesco;
    }

    public void setIdParentesco(int idParentesco) {
        this.idParentesco = idParentesco;
    }

// **************** LISTA DE ELEMENTOS EN TABLAS *******************************  
    public List<Parentesco> todosParentesco() {
        return getParentescoFacade().findAll();
    }

    public List<FamiliaDependientesEmp> todosFamiliaDependientesEmp() {
        return getFamiliaDependientesEmpFacade().buscarDependientes(this.getEmpleadoSelecionado());
    }

    public List<DirNacional> todosDirNacional() {
        return getDirNacionalFacade().findAll();
    }

    public List<Dependencias> dependenciasFiltradas() {
        return getDependenciasFacade().buscarDependencias(this.getDireccionNacional());
    }

    public List<Empleados> empleadoFiltrado() {
        return getEmpleadosFacade().buscarEmp(this.getDependecia());
    }

    public List<FamiliaDependientesEmp> repHijosEmpleadosEad() {
        this.setEdadMin(0);
        this.setEdadMax(80);
        return getFamiliaDependientesEmpFacade().buscarHijosEmpEdad(2, this.getEdadMin(), this.getEdadMax());
    }

    public List<Empleados> repPadresMadresFamilia() {
        List<FamiliaDependientesEmp> a = getFamiliaDependientesEmpFacade().buscarPadresFamilia(2);
        List<Empleados> emp = new ArrayList<>();

        Iterator<FamiliaDependientesEmp> nombreIterator = a.iterator();
        while (nombreIterator.hasNext()) {
            FamiliaDependientesEmp elemento = nombreIterator.next();
            Empleados emple = elemento.getIdEmpleado();
            boolean existe = emp.contains(emple);
            if (existe == false) {
                emp.add(emple);
            }
        }
        return emp;
    }

//*************************** FUNCIONES DE GUARDAR *****************************
    public void guardarFamiliaDependiente() {
        try {
            familiaDependientesEmp.setIdEmpleado(new Empleados(this.getEmpleadoSelecionado()));
            familiaDependientesEmp.setEdadFamilia(getEdadFamiliaDependiente());
            familiaDependientesEmp.setFechaNacFamilia(getFechaNacFamiliaDependiente());

            //Fecha de Creación y usuario Id =1
            familiaDependientesEmp.setFechaCreaFam(new Date());
            familiaDependientesEmp.setUserCreaFam(1);

            getFamiliaDependientesEmpFacade().create(familiaDependientesEmp);
            familiaDependientesEmp = new FamiliaDependientesEmp();
            this.setEdadFamiliaDependiente(0);
            this.setFechaNacFamiliaDependiente(new Date());
            RequestContext.getCurrentInstance().execute("PF('familia').hide()");
            RequestContext.getCurrentInstance().update("tabla");
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Ingresado", "Registro Ingresado");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } catch (Exception e) {

        }
    }

    public void editarFamiliaDependiente() {
        try {
            familiaDependientesEmp.setEdadFamilia(getEdadFamiliaDependiente());
            familiaDependientesEmp.setFechaNacFamilia(getFechaNacFamiliaDependiente());

            //Fecha de Modificación y usuario Id =1
            familiaDependientesEmp.setFechaModFam(new Date());
            familiaDependientesEmp.setUserModFam(1);

            getFamiliaDependientesEmpFacade().edit(familiaDependientesEmp);
            familiaDependientesEmp = new FamiliaDependientesEmp();
            this.setEdadFamiliaDependiente(0);
            this.setFechaNacFamiliaDependiente(new Date());
            RequestContext.getCurrentInstance().execute("PF('familiaEdit').hide()");
            RequestContext.getCurrentInstance().update("tabla");
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Modificado", "Registro Modificado");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } catch (Exception e) {

        }
    }

    public void dependienteSeleccionado(FamiliaDependientesEmp familiar) {
        familiaDependientesEmp = familiar;
    }

    public String eliminar() {
        try {
            getFamiliaDependientesEmpFacade().remove(familiaDependientesEmp);
            familiaDependientesEmp = new FamiliaDependientesEmp();
            RequestContext.getCurrentInstance().execute("PF('confirmation').hide()");
            RequestContext.getCurrentInstance().update("tabla");
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Eliminado", "Registro Eliminado");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public String cancelar() {
        return null;
    }

    //Devuelve edad apartir de fecha de nacimeinto
    public int edad(Date fecha_nac) {

        Date fechaNac = fecha_nac;
        Date fechaActual = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String hoy = formato.format(fechaActual);
        String nacimiento = formato.format(fechaNac);
        //String fechaNac = formato.format(fecha_nac);
        String[] dat1 = nacimiento.split("/");
        String[] dat2 = hoy.split("/");
        int anios = Integer.parseInt(dat2[2]) - Integer.parseInt(dat1[2]);
        int mes = Integer.parseInt(dat2[1]) - Integer.parseInt(dat1[1]);
        if (mes < 0) {
            anios = anios - 1;
        } else if (mes == 0) {
            int dia = Integer.parseInt(dat2[0]) - Integer.parseInt(dat1[0]);
            if (dia > 0) {
                anios = anios - 1;
            }
        }
        if (anios == -1) {
            anios = 0;
        }
        return anios;
    }

    public void empleadoSelecionadoValido(ActionEvent event) {
        //verifica si se selecino un empleado o si se busco un empleado
        if (this.getEmpleadoSelecionado() == 0) {
            familiaDependientesEmp = new FamiliaDependientesEmp();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debe Buscar un Empleado", "Debe Buscar un Empleado"));
        } else {
            RequestContext.getCurrentInstance().update("panelFamilia");
            RequestContext.getCurrentInstance().execute("PF('familia').show()");
        }
    }

    public void reset() {
        familiaDependientesEmp = new FamiliaDependientesEmp();
        this.setEdadFamiliaDependiente(0);
        this.setFechaNacFamiliaDependiente(new Date());
    }

    public void tabla() {//Actualiza tabla y nombre de empleado
        RequestContext.getCurrentInstance().update("nombre");
        RequestContext.getCurrentInstance().update("tabla");
    }

    public void buscarNR(ActionEvent event) {
        // busca empleado por nr
        Empleados emp = getEmpleadosFacade().buscarEmpNR(this.getNR());
        if (emp == null) {
            this.setNombreEmp("");
            this.setEmpleadoSelecionado(0);
        } else {
            this.setEmpleadoSelecionado(emp.getIdEmpleado());
            this.setNombreEmp(emp.getNombreEmpleado());
        }
    }

    // Obtine el path absoluto no importa de donde sea ejecutada la aplicación
    private String getAbsolutePath(String imageName) {
        final ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        final StringBuilder logo = new StringBuilder().append(servletContext.getRealPath(""));
        logo.append(File.separator).append(imageName);
        return logo.toString();
    }

    /*
     ******************************** REPORTES **********************************
     ******************************
     */
    public void repEmpleadosPadresMadresFamilia(ActionEvent actionEvent) throws JRException, IOException, SQLException {
        // Configuraciones en el log4j.properties para evitar un waring en netbeans (no necesario)
        String log4jConfPath = getAbsolutePath("reportes\\log4j.properties");
        PropertyConfigurator.configure(log4jConfPath);

        // Parametros Iniciales
        InputStream inputStream = null;
        String rutaJrxml = getAbsolutePath("reportes\\rep_emp_padres_familia.jrxml");
        String path = getAbsolutePath("resources\\images\\");

        // Carga el archivo Jrxml
        inputStream = new FileInputStream(rutaJrxml);

        // Cargamos Parametros que se enviaran
        Map parametros = new HashMap();
        parametros.put("path", path);

        // Compila y llena el reporte con datos
        JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, datasource.getConnection());

        //Para desgargar pdf
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.setContentType("application/pdf");
        response.addHeader("Content-disposition", "attachment; filename=Reporte_Padres_Madres_Familia.pdf");
        try (ServletOutputStream stream = response.getOutputStream()) {
            JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
            stream.flush();
        }
        FacesContext.getCurrentInstance().responseComplete();

    }
    
    public void repHijosEmpleadosEdad(ActionEvent actionEvent) throws JRException, IOException, SQLException {
        // Configuraciones en el log4j.properties para evitar un waring en netbeans (no necesario)
        String log4jConfPath = getAbsolutePath("reportes\\log4j.properties");
        PropertyConfigurator.configure(log4jConfPath);

        // Parametros Iniciales
        InputStream inputStream = null;
        String rutaJrxml = getAbsolutePath("reportes\\rep_hijos_emp_edad.jrxml");
        String path = getAbsolutePath("resources\\images\\");

        // Carga el archivo Jrxml
        inputStream = new FileInputStream(rutaJrxml);

        // Cargamos Parametros que se enviaran
        Map parametros = new HashMap();
        parametros.put("edadMin", this.getEdadMin());
        parametros.put("edadMax", this.getEdadMax());
        parametros.put("path", path);

        // Compila y llena el reporte con datos
        JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, datasource.getConnection());

        //Para desgargar pdf
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.setContentType("application/pdf");
        response.addHeader("Content-disposition", "attachment; filename=Reporte_Hijos_Empeados_Edad.pdf");
        try (ServletOutputStream stream = response.getOutputStream()) {
            JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
            stream.flush();
        }
        FacesContext.getCurrentInstance().responseComplete();

    }
}
