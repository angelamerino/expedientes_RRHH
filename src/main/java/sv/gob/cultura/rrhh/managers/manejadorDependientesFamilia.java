/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.cultura.rrhh.managers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
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
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.apache.log4j.PropertyConfigurator;
import org.primefaces.context.RequestContext;
import sv.gob.cultura.rrhh.entities.Dependencias;
import sv.gob.cultura.rrhh.entities.DirNacional;
import sv.gob.cultura.rrhh.entities.Empleados;
import sv.gob.cultura.rrhh.entities.FamiliaDependientesEmp;
import sv.gob.cultura.rrhh.entities.Parentesco;
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
        newFamilyMember = new FamiliaDependientesEmp();
    }

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

    private Empleados selectedEmp = new Empleados();
    private FamiliaDependientesEmp newFamilyMember = new FamiliaDependientesEmp(), selecteFamilyMember = new FamiliaDependientesEmp();
    private List<FamiliaDependientesEmp> familyMembers = new ArrayList<>();
    private int direccionNacional, dependecia, edadMin, edadMax, idParentesco;

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

    public FamiliaDependientesEmp getNewFamilyMember() {
        return newFamilyMember;
    }

    public void setNewFamilyMember(FamiliaDependientesEmp newFamilyMember) {
        this.newFamilyMember = newFamilyMember;
    }

    public FamiliaDependientesEmp getSelecteFamilyMember() {
        return selecteFamilyMember;
    }

    public void setSelecteFamilyMember(FamiliaDependientesEmp selecteFamilyMember) {
        this.selecteFamilyMember = selecteFamilyMember;
    }

    public int getDireccionNacional() {
        return direccionNacional;
    }

    public void setDireccionNacional(int direccionNacional) {
        this.direccionNacional = direccionNacional;
        this.setDependecia(0);
    }

    public int getDependecia() {
        return dependecia;
    }

    public void setDependecia(int dependecia) {
        this.dependecia = dependecia;
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

    public Empleados getSelectedEmp() {
        return selectedEmp;
    }

    public void setSelectedEmp(Empleados selectedEmp) {
        this.selectedEmp = selectedEmp;
    }

    public List<FamiliaDependientesEmp> getFamilyMembers() {
        return familyMembers;
    }

    public void setFamilyMembers(List<FamiliaDependientesEmp> familyMembers) {
        this.familyMembers = familyMembers;
    }

    public void populateFamily() {
        familyMembers = new ArrayList<>();
        familyMembers = getFamiliaDependientesEmpFacade().buscarDependientes(selectedEmp.getIdEmpleado());
    }

    public List<Parentesco> todosParentesco() {
        return getParentescoFacade().findAll();
    }

    public List<FamiliaDependientesEmp> allDependientes() {
        return getFamiliaDependientesEmpFacade().buscarDependientes(selectedEmp.getIdEmpleado());
    }

    public List<DirNacional> todosDirNacional() {
        return getDirNacionalFacade().findAll();
    }

    public List<Dependencias> dependenciasFiltradas() {
        empleadoFiltrado().clear();
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

    public void guardarFamiliaDependiente() {
        try {
            newFamilyMember.setIdEmpleado(selectedEmp);
            newFamilyMember.setFechaCreaFam(new Date());
            //Change when security is ready
            newFamilyMember.setUserCreaFam(1);
            getFamiliaDependientesEmpFacade().create(newFamilyMember);
            newFamilyMember = new FamiliaDependientesEmp();
            RequestContext.getCurrentInstance().execute("PF('familia').hide()");
            RequestContext.getCurrentInstance().update("tabla");
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Ingresado", "Registro Ingresado");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } catch (Exception e) {

        }
    }

    public void editarFamiliaDependiente() {
        try {
            //Fecha de Modificación y usuario Id =1
            newFamilyMember.setFechaModFam(new Date());
            //Change when security is ready
            newFamilyMember.setUserModFam(1);
            getFamiliaDependientesEmpFacade().edit(newFamilyMember);
            newFamilyMember = new FamiliaDependientesEmp();
            RequestContext.getCurrentInstance().execute("PF('familiaEdit').hide()");
            RequestContext.getCurrentInstance().update("tabla");
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Modificado", "Registro Modificado");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } catch (Exception e) {

        }
    }

    public String eliminar() {
        try {
            getFamiliaDependientesEmpFacade().remove(newFamilyMember);
            newFamilyMember = new FamiliaDependientesEmp();
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
        if (selectedEmp == null) {
            newFamilyMember = new FamiliaDependientesEmp();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debe Buscar un Empleado", "Debe Buscar un Empleado"));
        } else {
            RequestContext.getCurrentInstance().update("panelFamilia");
            RequestContext.getCurrentInstance().execute("PF('familia').show()");
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
