package sv.gob.cultura.rrhh.manejadores;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.apache.log4j.PropertyConfigurator;
import org.primefaces.context.RequestContext;
import sv.gob.cultura.rrhh.entidades.Anio;
import sv.gob.cultura.rrhh.entidades.AsignarAsistenciaCap;
import sv.gob.cultura.rrhh.entidades.Capacitaciones;
import sv.gob.cultura.rrhh.entidades.Dependencias;
import sv.gob.cultura.rrhh.entidades.DirNacional;
import sv.gob.cultura.rrhh.entidades.Empleados;
import sv.gob.cultura.rrhh.entidades.HorariosCap;
import sv.gob.cultura.rrhh.facades.AnioFacade;
import sv.gob.cultura.rrhh.facades.AsignarAsistenciaCapFacade;
import sv.gob.cultura.rrhh.facades.CapacitacionesFacade;
import sv.gob.cultura.rrhh.facades.DependenciasFacade;
import sv.gob.cultura.rrhh.facades.DirNacionalFacade;
import sv.gob.cultura.rrhh.facades.EmpleadosFacade;
import sv.gob.cultura.rrhh.facades.HorariosCapFacade;

/**
 *
 * @author Edwin
 */
@Named(value = "manejadorCapacitaciones")
@ViewScoped
public class manejadorCapacitaciones implements Serializable {

    private static final long serialVersionUID = 6529685098267757690L;
// ************** LLAMADA A LOS ENTERPRICE JAVA BEANS **************************
//******************************************************************************

    @EJB
    private CapacitacionesFacade capacitacionesFacade;
    @EJB
    private AsignarAsistenciaCapFacade asignarAsistenciaCapFacade;
    @EJB
    private EmpleadosFacade empleadosFacade;
    @EJB
    private AnioFacade anioFacade;
    @EJB
    private HorariosCapFacade horariosCapFacade;
    @EJB
    private DependenciasFacade dependenciasFacade;
    @EJB
    private DirNacionalFacade dirNacionalFacade;

//*********************** OBJETOS DE LOS ENTIDADES *****************************
//******************************************************************************    
    Capacitaciones capacitaciones = new Capacitaciones();
    AsignarAsistenciaCap asignarAsistenciaCap = new AsignarAsistenciaCap();
    Empleados empleado = new Empleados();
    HorariosCap horariosCap = new HorariosCap();
//****** VARIABLES QUE CONTRENDRAN ID´S O STRING DE FORMULARIOS ****************
//******************************************************************************
    private int anio;                           // id de año que se selecciono
    private int anioFiltro;                     // id de año que se selecciono para filtrar
    private int idCapHorasDias;                 // id de horas y dias de capacitación
    private int idCapAsig;                      // id de Asignacion de capacitaciones
    private int direccionNacional;              // id dirección nacional para filtrar dependencias
    private int dependecia;                     // id dependencias para filtrar empleado
    private int empleadoSelecionado;            // id de empleado selecionado o buscado en el formulario
    private String nombreEmp;                   // Nombre de empleado seleccinado o buscado
    private Date anioEditar;                    // Fecha del cual se necesita unicamente el año
    private Date horaInicio;
    private Date horaFinal;
    private int horas;
    private int minutos;
    private String NR;
    private int capacitacionAsisReporte;        // id de capacion para reporte asistencia a capacitaciones
//********************** GET DE ENTERPRICE JAVA BEAN ***************************
//******************************************************************************

    public CapacitacionesFacade getCapacitacionesFacade() {
        return capacitacionesFacade;
    }

    public AsignarAsistenciaCapFacade getAsignarAsistenciaCapFacade() {
        return asignarAsistenciaCapFacade;
    }

    public EmpleadosFacade getEmpleadosFacade() {
        return empleadosFacade;
    }

    public AnioFacade getAnioFacade() {
        return anioFacade;
    }

    public HorariosCapFacade getHorariosCapFacade() {
        return horariosCapFacade;
    }

    public DependenciasFacade getDependenciasFacade() {
        return dependenciasFacade;
    }

    public DirNacionalFacade getDirNacionalFacade() {
        return dirNacionalFacade;
    }

//******************* GET y SET DE OBJETOS DE ENTIDADES ************************
//******************************************************************************
    public Capacitaciones getCapacitaciones() {
        return capacitaciones;
    }

    public void setCapacitaciones(Capacitaciones capacitaciones) {
        this.capacitaciones = capacitaciones;
    }

    public AsignarAsistenciaCap getAsignarAsistenciaCap() {
        return asignarAsistenciaCap;
    }

    public void setAsignarAsistenciaCap(AsignarAsistenciaCap asignarAsistenciaCap) {
        this.asignarAsistenciaCap = asignarAsistenciaCap;
    }

    public Empleados getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleados empleado) {
        this.empleado = empleado;
    }

    public HorariosCap getHorariosCap() {
        return horariosCap;
    }

    public void setHorariosCap(HorariosCap horariosCap) {
        this.horariosCap = horariosCap;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public Date getAnioEditar() {
        return anioEditar;
    }

    public void setAnioEditar(Date anioEditar) {
        this.anioEditar = anioEditar;
        // Obtenemos unicamente el año de la fecha ingresada
        this.setAnio(obtenerAnio(anioEditar));
    }

    public int getIdCapHorasDias() {
        return idCapHorasDias;
    }

    public void setIdCapHorasDias(int idCapHorasDias) {
        this.idCapHorasDias = idCapHorasDias;
    }

    public int getIdCapAsig() {
        return idCapAsig;
    }

    public void setIdCapAsig(int idCapAsig) {
        this.idCapAsig = idCapAsig;
    }

    public int getDireccionNacional() {
        return direccionNacional;
    }

    public void setDireccionNacional(int direccionNacional) {
        //Al seleccinar una dirección nacional, la dependencia y el empleado
        //y nombre de empleado se hacen nulos o ceros
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
        //buscamos empleado y obtenemos su nombre si no se encuenta se setea nulo
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

    public int getAnioFiltro() {
        return anioFiltro;
    }

    public void setAnioFiltro(int anioFiltro) {
        this.anioFiltro = anioFiltro;
    }

    public Date getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Date horaInicio) {
        Calendar fecha = dateToCalendar(horaInicio);
        this.setHoras(fecha.get(Calendar.HOUR_OF_DAY));
        this.setMinutos(fecha.get(Calendar.MINUTE));
        this.horaInicio = horaInicio;
    }

    public Date getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(Date horaFinal) {
        this.horaFinal = horaFinal;
    }

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }

    public int getMinutos() {
        return minutos;
    }

    public void setMinutos(int minutos) {
        this.minutos = minutos;
    }

    public String getNR() {
        return NR;
    }

    public void setNR(String NR) {
        this.NR = NR;
    }

    public int getCapacitacionAsisReporte() {
        return capacitacionAsisReporte;
    }

    public void setCapacitacionAsisReporte(int capacitacionAsisReporte) {
        this.capacitacionAsisReporte = capacitacionAsisReporte;
    }

// **************** LISTA DE ELEMENTOS EN TABLAS *******************************
//******************************************************************************
    public List<Anio> todosAnios() {
        return getAnioFacade().findAll();
    }

    public List<Capacitaciones> todasCapacitaciones() {
        return getCapacitacionesFacade().findAll();
    }

    public List<Capacitaciones> todasCapacitacionesPorAnioGestion() {
        return getCapacitacionesFacade().buscarCapacitacionByAnio(fechaAnio(obtenerAnio(new Date()))); //Año en curso
    }

    public List<Capacitaciones> todasCapacitacionesPorAnio() {
        return getCapacitacionesFacade().buscarCapacitacionByAnio(fechaAnio(this.getAnioFiltro()));
    }

    public List<HorariosCap> todosHorariosCap() {
        return getHorariosCapFacade().buscarHorasDiasCap(this.getIdCapHorasDias());
    }

    public List<DirNacional> todosDirNacional() {
        return getDirNacionalFacade().findAll();
    }

    public List<Dependencias> dependenciasFiltradas() {
        return getDependenciasFacade().buscarDependencias(this.getDireccionNacional());
    }

    public List<Empleados> empleadoFiltrado() {
        List<Empleados> empCap = empleadoCapacitaciones(); //Empleados en capacitacion
        List<Empleados> empDep = getEmpleadosFacade().buscarEmp(this.getDependecia()); //todos los empleados de una dependecia

        //Iterator<Empleados> itearadorEmpCapacitaciones = empCap.iterator();
        Iterator<Empleados> itearadorEmpDependencias = empDep.iterator();

        while (itearadorEmpDependencias.hasNext()) {
            Empleados emp = itearadorEmpDependencias.next();
            boolean s = empCap.contains(emp);
            if (s == true) {
                itearadorEmpDependencias.remove();
            }
        }
        return empDep;
    }

    public List<Empleados> empleadoCapacitaciones() {
        //busca los empleados de una capacitacion dada!!!
        ArrayList<Empleados> emp = new ArrayList<Empleados>();
        List<AsignarAsistenciaCap> asigCapList = getAsignarAsistenciaCapFacade().todasAsignaidCap(getIdCapAsig());

        if (asigCapList != null) {

            Iterator<AsignarAsistenciaCap> itearadorAsig = asigCapList.iterator();

            while (itearadorAsig.hasNext()) {
                Empleados empleadoEnLista = itearadorAsig.next().getIdEmpleado();
                emp.add(empleadoEnLista);
            }
        }
        return emp;
    }

    public List<AsignarAsistenciaCap> todasAsignacionesCap() {
        return getAsignarAsistenciaCapFacade().findAll();
    }

    public List<AsignarAsistenciaCap> reporteCapacitacionesEmp() {
        return getAsignarAsistenciaCapFacade().repCapacitacionesEmp(this.getEmpleadoSelecionado());
    }

    public List<AsignarAsistenciaCap> reporteAsistenciaCapacitaciones() {
        return getAsignarAsistenciaCapFacade().todasAsignaidCap(this.getCapacitacionAsisReporte());
    }

    public List<AsignarAsistenciaCap> empleadoAsignacionesCap() {
        List<AsignarAsistenciaCap> asigCapList = getAsignarAsistenciaCapFacade().todasAsignaidCap(getIdCapAsig());
        ArrayList<AsignarAsistenciaCap> emp = new ArrayList<AsignarAsistenciaCap>();

        if (asigCapList != null) {
            Iterator<AsignarAsistenciaCap> itearadorAsig = asigCapList.iterator();
            while (itearadorAsig.hasNext()) {
                emp.add(itearadorAsig.next());
            }
        }
        return emp;
    }

//********************************* FUNCIONES **********************************
//******************************************************************************
    public String guardarCapacitacion() {
        try {
            capacitaciones.setFechaCreaCap(new Date());
            capacitaciones.setUserCreaCap(1);
            capacitaciones.setAnioCap(fechaAnio(this.getAnio()));
            getCapacitacionesFacade().create(capacitaciones);
            capacitaciones = new Capacitaciones();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Ingresado", "Registro Ingresado");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return "gestion_capacitaciones";
        } catch (Exception e) {
            return "capacitaciones";
        }

    }

    public void capacitacionSeleccionada(Capacitaciones cap) {
        capacitaciones = cap;
    }

    public String eliminarCapacitacion() {
        try {
            getCapacitacionesFacade().remove(capacitaciones);
            capacitaciones = new Capacitaciones();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Eliminado", "Registro Eliminado");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return "gestion_capacitaciones";
        } catch (Exception e) {
            return "gestion_capacitaciones";
        }
    }

    public String editarCapacitacion() {
        try {
            capacitaciones.setFechaModCap(new Date());
            capacitaciones.setUserModCap(1);
            capacitaciones.setAnioCap(fechaAnio(this.getAnio()));
            getCapacitacionesFacade().edit(capacitaciones);
            capacitaciones = new Capacitaciones();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Modificado", "Registro Modificado");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return "gestion_capacitaciones";
        } catch (Exception e) {
            return "gestion_capacitaciones";
        }
    }

    public String nuevaCapacitacion() {
        return "capacitaciones";
    }

    public String cancelarCapacitacion() {
        return "gestion_capacitaciones";
    }

    public void guardarHorariosCap() {
        try {
            horariosCap.setIdCap(new Capacitaciones(this.getIdCapHorasDias()));
            horariosCap.setHoraInicioCap(this.getHoraInicio());
            getHorariosCapFacade().create(horariosCap);
            horariosCap = new HorariosCap();
            this.setHoraInicio(new Date());
            RequestContext.getCurrentInstance().update("tabla1");
            RequestContext.getCurrentInstance().execute("PF('dias').hide()");
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Ingresado", "Registro Ingresado");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } catch (Exception e) {
        }
    }

    public void horariosCapaSelecionado(HorariosCap horarios) {
        horariosCap = horarios;
    }

    public String eliminarHorariosCap() {
        try {
            getHorariosCapFacade().remove(horariosCap);
            horariosCap = new HorariosCap();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Eliminado", "Registro Eliminado");
            FacesContext.getCurrentInstance().addMessage(null, message);
            RequestContext.getCurrentInstance().update("tabla1");
            RequestContext.getCurrentInstance().execute("PF('confirmation1').hide()");
            return "gestion_capacitaciones";
        } catch (Exception e) {
            return "gestion_capacitaciones";
        }
    }

    public String nuevaAsigCapacitacion() {
        return "reg_asignacion_capacitaciones";
    }

    public String cancelarAsignacion() {
        return "gestion_asignacion_capacitaciones";
    }

    public String buscarEmpleadosCapa() {
        return null;
    }

    public void actualizarTabla() {
        RequestContext.getCurrentInstance().update("tabla");
    }

    public void addEmpCapa() {
        try {
            if (this.getEmpleadoSelecionado() != 0) {
                asignarAsistenciaCap.setIdCap(new Capacitaciones(this.getIdCapAsig()));
                asignarAsistenciaCap.setIdEmpleado(new Empleados(this.getEmpleadoSelecionado()));
                asignarAsistenciaCap.setCapAsignada(true);
                asignarAsistenciaCap.setCapAsistida(false);
                asignarAsistenciaCap.setFechaCreaAsigAsis(new Date());
                asignarAsistenciaCap.setUserCreaAsigAsis(1);
                getAsignarAsistenciaCapFacade().create(asignarAsistenciaCap);
                asignarAsistenciaCap = new AsignarAsistenciaCap();
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Ingresado", "Registro Ingresado");
                FacesContext.getCurrentInstance().addMessage(null, message);
                RequestContext.getCurrentInstance().update("tabla");
                RequestContext.getCurrentInstance().execute("PF('nuevoEmpCapacitacion').hide()");
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Seleccione Empleado", "Seleccione Empleado"));
            }
        } catch (Exception e) {

        }

    }

    public void empleadoSeleccionado(Empleados emp) {
        empleado = emp;
    }

    public String cancelarEditar() {
        return null;
    }

    public String eliminarEmpleadoCapacitacion() {
        try {
            AsignarAsistenciaCap asistencia = getAsignarAsistenciaCapFacade().asignaidCapEmpleado(empleado.getIdEmpleado());
            getAsignarAsistenciaCapFacade().remove(asistencia);
            empleado = new Empleados();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Eliminado", "Registro Eliminado");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public String eliminarAsignacion(AsignarAsistenciaCap asigCap) {
        try {
            getAsignarAsistenciaCapFacade().remove(asigCap);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Eliminado", "Registro Eliminado");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return "gestion_asignacion_capacitaciones";
        } catch (Exception e) {
            return "gestion_asignacion_capacitaciones";
        }
    }

    public void cambioAistencia(AsignarAsistenciaCap asigCap) { //Se actualiza en la misma pagina!!!
        try {
            if (asigCap.getCapAsistida() == true) {
                asigCap.setCapAsistida(false);
                getAsignarAsistenciaCapFacade().edit(asigCap);
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, asigCap.getIdEmpleado().getNombreEmpleado() + "\nNO Asistió a Capacitación", "\nNO Asistió a Capacitación");
                FacesContext.getCurrentInstance().addMessage(null, message);
            } else {
                asigCap.setCapAsistida(true);
                getAsignarAsistenciaCapFacade().edit(asigCap);
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, asigCap.getIdEmpleado().getNombreEmpleado() + "\nAsistió a Capacitación", "\nAsistió a Capacitación");
                FacesContext.getCurrentInstance().addMessage(null, message);
            }

            //return "asistencia_capacitaciones";
        } catch (Exception e) {
            // return "asistencia_capacitaciones";
        }
    }

    public void cancelar() {
        this.setDireccionNacional(0);
        this.setDependecia(0);
        this.setEmpleadoSelecionado(0);
    }

    public void nuevoEmpCapa(ActionEvent event) {
        try {
            if (this.getIdCapAsig() == 0) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Seleccione una Capacitación", "Seleccione una Capacitación"));
            } else {
                this.setDireccionNacional(0);
                this.setDependecia(0);
                this.setEmpleadoSelecionado(0);
                RequestContext.getCurrentInstance().execute("PF('nuevoEmpCapacitacion').show()");
            }
        } catch (Exception e) {

        }
    }

    public manejadorCapacitaciones() {
    }

    public Date fechaAnio(int anio) { //Regresa una fecha 31 de diiembre de un año dado
        anio = anio - 1900;
        Date fecha = new Date(anio, 11, 31);
        return fecha;
    }

    public int obtenerAnio(Date date) { // Regresa un año de una fecha dada
        if (null == date) {
            return 0;
        } else {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
            return Integer.parseInt(dateFormat.format(date));
        }
    }

    public static Calendar dateToCalendar(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }

    //*******************************************************************************
    public void preProcessPDF(Object document) throws IOException, DocumentException {

        final Document pdf = (Document) document;
        pdf.setPageSize(PageSize.LETTER);
        pdf.setMargins(10, 10, 10, 10); //para margenes de la página

        // Fuente
        BaseFont bf_helv = BaseFont.createFont(BaseFont.HELVETICA, "Cp1252", false);

        // Encabezado
//        HeaderFooter header = new HeaderFooter(new Phrase("Encabezado", new Font(bf_helv)), false);
//        header.setBorder(Rectangle.NO_BORDER);
//        header.setAlignment(Element.ALIGN_CENTER);
//        pdf.setHeader(header);  
        // Pie de pagina
        HeaderFooter footer = new HeaderFooter(new Phrase("Página: ", new Font(bf_helv)), true);
        // footer.setBorder(Rectangle.);
        footer.setAlignment(Element.ALIGN_CENTER);
        pdf.setFooter(footer);

        // Se abre documento para agregar elementos
        pdf.open();

        //banner del documento
        Image imagen = getImage("resources/images/bannerTop1.png");
        imagen.scalePercent(70); //Escala
        imagen.setAlignment(Element.ALIGN_CENTER);
        pdf.add(imagen);

        //agregado por mi
//        pdf.add(new Paragraph("RRHH - Secretaría de Cultura de la Presidencia"));
//        DateFormat formateer = new SimpleDateFormat("dd/MM/yy '-' hh:mm:ss");
//        Date currentDate = new Date();
//        String date = formateer.format(currentDate);
//        pdf.add(new Paragraph("Fecha de Generación: " + date));
//        pdf.add(new Paragraph("\n"));
        Paragraph nombre_rep = new Paragraph("Reporte Capacitaciones por Empleado",
                FontFactory.getFont("garamond", //fuente
                        12, //tamaño
                        Font.BOLD, //estilo
                        //Color.DARK_GRAY //
                        //Color.getHSBColor(0.7f, 0.9f, 0f) //color de la letra Color.getHSBColor(4, 72, 139)-- HSB (Hue, Saturation, Brightness – Matiz, Saturación, Brillo)
                        Color.getHSBColor(0.6f, 0.9f, 0.7f)
                ));
        nombre_rep.setSpacingAfter(25);
        nombre_rep.setSpacingBefore(25);
        nombre_rep.setAlignment(Element.ALIGN_CENTER);
        pdf.add(nombre_rep);
    }

    private Image getImage(String imageName) throws IOException, BadElementException {
        final Image image = Image.getInstance(getAbsolutePath(imageName));
        image.scalePercent(100f);
        return image;
    }

    private String getAbsolutePath(String imageName) {
        final ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        final StringBuilder logo = new StringBuilder().append(servletContext.getRealPath(""));
        logo.append(File.separator).append(imageName);
        return logo.toString();
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

    /*
     ******************************** REPORTES **********************************
     ******************************
     */
    public void rep_cap_emp() throws JRException, IOException, SQLException {
        if (this.getEmpleadoSelecionado() != 0) {
            // Configuraciones en el log4j.properties para evitar un waring en netbeans (no necesario)
            String log4jConfPath = getAbsolutePath("reportes\\log4j.properties");
            PropertyConfigurator.configure(log4jConfPath);

            // Hacemos una conexion a la base de datos (No encontre otra forma de hacerlo)
            Connection baseDatos = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/bd_rrhh", "postgres", "root123");

            // Path reales de los archivos de jasper y jrxml
            String jasper = getAbsolutePath("reportes\\rep_capacitaciones_emp.jasper");
            String rutaJrxml = getAbsolutePath("reportes\\rep_capacitaciones_emp.jrxml");
            String path = getAbsolutePath("resources\\images\\");
            System.out.println(path);
            //Compila el jrxml para obtener jasper (No es necesario)
            JasperCompileManager.compileReport(rutaJrxml);

            //parametros que enviamos al report
            Map parametros = new HashMap();
            parametros.put("id_empleado", this.getEmpleadoSelecionado());
            parametros.put("path", path);

            // Cargamos Archivo jasper ya compilado
            File fichero = new File(jasper);
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(fichero);

            // creamos objeto de tipo JasperPrint para llenar el jasper con datos, se pasan parametros
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, baseDatos);

            //Para desgargar pdf
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            response.addHeader("Content-disposition", "attachment; filename=Reporte_Capacitaciones_Por_Empleado.pdf");
            try (ServletOutputStream stream = response.getOutputStream()) {
                JasperExportManager.exportReportToPdfStream(jasperPrint, stream);

                stream.flush();
            }
            FacesContext.getCurrentInstance().responseComplete();
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ingrese NR Empleado", "Ingrese NR Empleado");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void generarReporte() throws net.sf.jasperreports.engine.JRException, FileNotFoundException, IOException, SQLException {
        if (this.getCapacitacionAsisReporte() != 0) {
            // Hacemos una conexion a la base de datos (No encontre otra forma de hacerlo)
            Connection baseDatos = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/bd_rrhh", "postgres", "root123");

            // Configuraciones en el log4j.properties para evitar un waring en netbeans (no necesario)
            String log4jConfPath = getAbsolutePath("reportes\\log4j.properties");
            PropertyConfigurator.configure(log4jConfPath);

            // Parametros Iniciales
            InputStream inputStream = null;
            String rutaJrxml = getAbsolutePath("reportes\\rep_asitencia_capacitacion.jrxml");
            String path = getAbsolutePath("resources\\images\\");

            // Carga el archivo Jrxml
            inputStream = new FileInputStream(rutaJrxml);

            // Cargamos Parametros que se enviaran
            Map parametros = new HashMap();
            parametros.put("id_capacitacion", this.getCapacitacionAsisReporte());
            parametros.put("path", path);

            // Compila y llena el reporte con datos
            JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, baseDatos);

            // Exportamos el informe a HTML
//            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
//            response.setContentType("text/html");
//            JRExporter exporter = null;
//            exporter = new JRHtmlExporter();
//            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
//            exporter.setParameter(JRExporterParameter.OUTPUT_WRITER, response.getWriter());
//            exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI, FacesContext.getCurrentInstance() + "/image?image=");
//            exporter.exportReport();
            
            //Para desgargar pdf
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            response.addHeader("Content-disposition", "attachment; filename=Reporte_Asistencia_Capacitacion.pdf");
            try (ServletOutputStream stream = response.getOutputStream()) {
                JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
                stream.flush();
            }
            FacesContext.getCurrentInstance().responseComplete();
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Seleccione una Capacitación", "Seleccione una Capacitación");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

}
