package sv.gob.cultura.rrhh.manejadores;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
    private int anio;
    private Date anioEditar;
    private int idCapHorasDias;
    private int idCapAsig;
    private int direccionNacional;
    private int dependecia;
    private int empleadoSelecionado;
    private String nombreEmp;
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

// **************** LISTA DE ELEMENTOS EN TABLAS *******************************
//******************************************************************************
    public List<Anio> todosAnios() {
        return getAnioFacade().findAll();
    }

    public List<Capacitaciones> todasCapacitaciones() {
        return getCapacitacionesFacade().findAll();
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
        return getEmpleadosFacade().buscarEmp(this.getDependecia());
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
    
    public List<AsignarAsistenciaCap> todasAsignacionesCap(){
        return getAsignarAsistenciaCapFacade().findAll();
    }
//*************************** FUNCIONES DE GUARDAR *****************************
//******************************************************************************

    public String guardarCapacitacion() {
        capacitaciones.setFechaCreaCap(new Date());
        capacitaciones.setUserCreaCap(1);
        capacitaciones.setAnioCap(fechaAnio(this.getAnio()));
        getCapacitacionesFacade().create(capacitaciones);
        capacitaciones = new Capacitaciones();
        return "gestion_capacitaciones";
    }

    public String eliminarCapacitacion(Capacitaciones cap) {
        getCapacitacionesFacade().remove(cap);
        return "gestion_capacitaciones";
    }

    public String editarCapacitacion() {
        capacitaciones.setFechaModCap(new Date());
        capacitaciones.setUserModCap(1);
        capacitaciones.setAnioCap(fechaAnio(this.getAnio()));
        getCapacitacionesFacade().edit(capacitaciones);
        capacitaciones = new Capacitaciones();
        return "gestion_capacitaciones";
    }

    public String nuevaCapacitacion() {
        return "reg_capacitaciones";
    }

    public String cancelarCapacitacion() {
        return "gestion_capacitaciones";
    }

    public String guardarHorariosCap() {
        horariosCap.setIdCap(new Capacitaciones(this.getIdCapHorasDias()));
        getHorariosCapFacade().create(horariosCap);
        horariosCap = new HorariosCap();
        return "gestion_capacitaciones";
    }

    public String eliminarHorariosCap(HorariosCap horarios) {
        getHorariosCapFacade().remove(horarios);
        return "gestion_capacitaciones";
    }

    public String nuevaAsigCapacitacion() {
        return "reg_asignacion_capacitaciones";
    }

    public String cancelarAsignacion() {
        return "gestion_asignacion_capacitaciones";
    }

    public String buscarEmpleadosCapa() {
        System.out.println("Capacitacion seleccionada: " + this.getIdCapAsig());
        return null;
    }

    public void addEmpCapa() {
        asignarAsistenciaCap.setIdCap(new Capacitaciones(this.getIdCapAsig()));
        asignarAsistenciaCap.setIdEmpleado(new Empleados(this.getEmpleadoSelecionado()));
        //asignarAsistenciaCap.setCapAsignada("SI");
        //asignarAsistenciaCap.setCapAsistida("NO");
        asignarAsistenciaCap.setFechaCreaAsigAsis(new Date());
        asignarAsistenciaCap.setUserCreaAsigAsis(1);
        getAsignarAsistenciaCapFacade().create(asignarAsistenciaCap);
        asignarAsistenciaCap = new AsignarAsistenciaCap();
    }
    
    public String eliminarEmpleadoCapacitacion(Empleados emp){
        AsignarAsistenciaCap asistencia = getAsignarAsistenciaCapFacade().asignaidCapEmpleado(emp.getIdEmpleado());
        getAsignarAsistenciaCapFacade().remove(asistencia);        
        return null;
    }
    
    public String eliminarAsignacion(AsignarAsistenciaCap asigCap){
        getAsignarAsistenciaCapFacade().remove(asigCap);
        return "gestion_asignacion_capacitaciones";
    }

    public void cancelar() {
    }

    public void nuevoEmpCapa(ActionEvent event) {
        if (this.getIdCapAsig() == 0) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Seleccione una Capacitación"));
        } else {
            this.setDireccionNacional(0);
            this.setDependecia(0);
            this.setEmpleadoSelecionado(0);
            RequestContext.getCurrentInstance().execute("PF('nuevoEmpCapacitacion').show()");
        }
    }

    public manejadorCapacitaciones() {
    }

    public Date fechaAnio(int anio) {
        anio = anio - 1900;
        Date fecha = new Date(anio, 11, 31);
        return fecha;
    }

    public int obtenerAnio(Date date) {
        if (null == date) {
            return 0;
        } else {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
            return Integer.parseInt(dateFormat.format(date));
        }
    }
}
