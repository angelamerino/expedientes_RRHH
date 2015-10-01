package sv.gob.cultura.rrhh.manejadores;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
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
}
