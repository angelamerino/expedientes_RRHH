/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.cultura.rrhh.manejadores;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.application.ViewHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.context.RequestContext;
import sv.gob.cultura.rrhh.entidades.Dependencias;
import sv.gob.cultura.rrhh.entidades.DirNacional;
import sv.gob.cultura.rrhh.entidades.Empleados;
import sv.gob.cultura.rrhh.entidades.MovimientosEmp;
import sv.gob.cultura.rrhh.entidades.TipoMov;
import sv.gob.cultura.rrhh.facades.DependenciasFacade;
import sv.gob.cultura.rrhh.facades.DirNacionalFacade;
import sv.gob.cultura.rrhh.facades.EmpleadosFacade;
import sv.gob.cultura.rrhh.facades.MovimientosEmpFacade;
import sv.gob.cultura.rrhh.facades.TipoMovFacade;

/**
 *
 * @author Edwin
 */
@Named(value = "manejadorMovimientosEmp")
@ViewScoped
public class manejadorMovimientosEmp implements Serializable {

    private static final long serialVersionUID = 1L;
// ************** LLAMADA A LOS ENTERPRICE JAVA BEANS **************************
//******************************************************************************
    @EJB
    private MovimientosEmpFacade movimientosEmpFacade;
    @EJB
    private TipoMovFacade tipoMovFacade;
    @EJB
    private EmpleadosFacade empleadosFacade;
    @EJB
    private DependenciasFacade dependenciasFacade;
    @EJB
    private DirNacionalFacade dirNacionalFacade;
//*********************** OBJETOS DE LOS ENTIDADES *****************************
//******************************************************************************
    private Empleados empleado = new Empleados();
    private TipoMov tipoMov = new TipoMov();
    private MovimientosEmp movimientosEmp = new MovimientosEmp();
//****** VARIABLES QUE CONTRENDRAN ID´S O STRING DE FORMULARIOS ****************
//******************************************************************************
    private int tipoMovimiento;                         // id tipo de movimimiento de empleado
    private int empleadoSelecionado;                    // id de empleado selecinado
    private int dirNacinal;                             // id direccion nacional para filtar dependencias
    private int dependencia;                            // id dependdencias
    private int depActual;                              // id dependencia actual
    private Date fechaTralado;                          // fecha de traslado de empeado a nuevo cargo
    private Date fechaNoti;                             // fecha de notificacion de traslado
    private Date fechaInicio;                           // fecha de inicio en nuevo cargo temporalmente
    private Date fechaFinal;                            // fecha de finalicacion en nuevo cargo temporalmente        
    private String NR = "NR 0614-090179-134-0";         // NR empleado para busqueda
    private String nombreEmp;                           // nombre de empleado encontrado
    private String depenActual;                         // Nombre de la dependencia actual
    private String nuevoCargo;                          // nombre de nuevo cargo
    private String antiguoCargo;                        // nombre de antiguo cargo
//********************** GET DE ENTERPRICE JAVA BEAN ***************************
//******************************************************************************

    public MovimientosEmpFacade getMovimientosEmpFacade() {
        return movimientosEmpFacade;
    }

    public TipoMovFacade getTipoMovFacade() {
        return tipoMovFacade;
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
//******************************************************************************
    public Empleados getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleados empleado) {
        this.empleado = empleado;
    }

    public TipoMov getTipoMov() {
        return tipoMov;
    }

    public void setTipoMov(TipoMov tipoMov) {
        this.tipoMov = tipoMov;
    }

    public MovimientosEmp getMovimientosEmp() {
        return movimientosEmp;
    }

    public void setMovimientosEmp(MovimientosEmp movimientosEmp) {
        this.movimientosEmp = movimientosEmp;
    }

    public int getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(int tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public String getNR() {
        return NR;
    }

    public void setNR(String NR) {
        this.NR = NR;
    }

    public int getEmpleadoSelecionado() {
        return empleadoSelecionado;
    }

    public void setEmpleadoSelecionado(int empleadoSelecionado) {
        this.empleadoSelecionado = empleadoSelecionado;
    }

    public int getDirNacinal() {
        return dirNacinal;
    }

    public void setDirNacinal(int dirNacinal) {
        this.dirNacinal = dirNacinal;
    }

    public int getDependencia() {
        return dependencia;
    }

    public void setDependencia(int dependencia) {
        this.dependencia = dependencia;
    }

    public String getDepenActual() {
        return depenActual;
    }

    public void setDepenActual(String depenActual) {
        this.depenActual = depenActual;
    }

    public Date getFechaTralado() {
        return fechaTralado;
    }

    public void setFechaTralado(Date fechaTralado) {
        this.fechaTralado = fechaTralado;
    }

    public Date getFechaNoti() {
        return fechaNoti;
    }

    public void setFechaNoti(Date fechaNoti) {
        this.fechaNoti = fechaNoti;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public int getDepActual() {
        return depActual;
    }

    public void setDepActual(int depActual) {
        this.depActual = depActual;
    }

    public String getNuevoCargo() {
        return nuevoCargo;
    }

    public void setNuevoCargo(String nuevoCargo) {
        this.nuevoCargo = nuevoCargo;
    }

    public String getAntiguoCargo() {
        return antiguoCargo;
    }

    public void setAntiguoCargo(String antiguoCargo) {
        this.antiguoCargo = antiguoCargo;
    }

    public String getNombreEmp() {
        return nombreEmp;
    }

    public void setNombreEmp(String nombreEmp) {
        this.nombreEmp = nombreEmp;
    }

// **************** LISTA DE ELEMENTOS EN TABLAS *******************************
//******************************************************************************
    public List<TipoMov> todosTipoMov() {
        return getTipoMovFacade().findAll();
    }

    public List<DirNacional> todosDirNacional() {
        return getDirNacionalFacade().findAll();
    }

    public List<Dependencias> dependenciasFiltradas() {
        return getDependenciasFacade().buscarDependencias(this.getDirNacinal());
    }

    public List<MovimientosEmp> todosMoviemientosEmp() {
        return getMovimientosEmpFacade().findAll();
    }
//********************************* FUNCIONES **********************************
//******************************************************************************

    public void agregarinformacion() { 
        // Para continuar con el proceso de ingreso de inforacion 
        // verifica el NR digitado y el tipo de movimiento de empleado
        System.out.println("Buscamos empleado");
        System.out.println(this.getTipoMovimiento());
        System.out.println(this.getNR());

        Empleados emp = getEmpleadosFacade().buscarEmpNR(this.getNR());

        if (emp == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Empleado No encontrado"));
        } else {

            //Informacin Requrida de empleado
            this.setEmpleadoSelecionado(emp.getIdEmpleado());
            this.setDepenActual(emp.getIdDependenciaF().getNombreDependencia());
            this.setDepActual(emp.getIdDependenciaF().getIdDependencia());
            this.setAntiguoCargo(emp.getCargoFuncional());

            System.out.println(emp.getNombreEmpleado());

            switch (this.getTipoMovimiento()) { // Según el tipo de movimiento
                case 1:
                    RequestContext.getCurrentInstance().execute("PF('traslado').show()");
                    break;
                case 2:
                    RequestContext.getCurrentInstance().execute("PF('ascenso').show()");
                    break;
                case 3:
                    RequestContext.getCurrentInstance().execute("PF('trasladoTemp').show()");
                    break;
            }
        }
    }

    public String guardarMovTraslado() {
        //guarda movimiento por traslado
        Empleados emp = empleadosFacade.buscarEmpNR(this.getNR());
        // CARGO ACTUAL ***
        // NUEVO CARGO ***

        //Registro del Moviemiento de empleado
        this.movimientosEmp.setIdTipoMov(new TipoMov(this.getTipoMovimiento()));
        this.movimientosEmp.setIdEmpleado(new Empleados(this.getEmpleadoSelecionado()));
        this.movimientosEmp.setIdDependencia(new Dependencias(this.getDependencia())); //Nueva dependencia
        this.movimientosEmp.setDepIdDependencia(emp.getIdDependenciaF()); //Antigua dependencia
        this.movimientosEmp.setDependenciaActual(this.getDepenActual());
        this.movimientosEmp.setNuevoCargoMov(this.getNuevoCargo()); //nuevo cargo
        this.movimientosEmp.setCargoActualMov(emp.getCargoFuncional()); //cargo actual
        this.movimientosEmp.setFechaMov(this.getFechaTralado());
        this.movimientosEmp.setFechaNoti(this.getFechaNoti());
        this.movimientosEmp.setUserCreaMov(1);
        this.movimientosEmp.setFechaCreaMov(new Date());
        getMovimientosEmpFacade().create(movimientosEmp);

        //Se edita información  de empleado
        emp.setCargoFuncional(this.getNuevoCargo());
        emp.setUserModEmp(1);                                                       //USUARIO QUE MODIFICO
        emp.setFechaModEmp(new Date());
        getEmpleadosFacade().edit(emp);

        movimientosEmp = new MovimientosEmp();
        return "gestion_movimientos_emp";
    }

    public String guardarMovAsc() {
        //Guarda movimiento por Ascenso
        Empleados emp = empleadosFacade.buscarEmpNR(this.getNR());
        // CARGO ACTUAL ***
        // NUEVO CARGO ***

        //Registro del Moviemiento de empleado
        this.movimientosEmp.setIdTipoMov(new TipoMov(this.getTipoMovimiento()));
        this.movimientosEmp.setIdEmpleado(new Empleados(this.getEmpleadoSelecionado()));

        this.movimientosEmp.setIdDependencia(emp.getIdDependenciaF()); //nueva dependencia sigue siendo la misma
        this.movimientosEmp.setDepIdDependencia(emp.getIdDependenciaF()); //antigua dependecncia sigue siendo la misma

        this.movimientosEmp.setDependenciaActual(this.getDepenActual());
        this.movimientosEmp.setNuevoCargoMov(this.getNuevoCargo()); //nuevo cargo
        this.movimientosEmp.setCargoActualMov(emp.getCargoFuncional()); //cargo actual
        this.movimientosEmp.setFechaMov(this.getFechaTralado());
        this.movimientosEmp.setFechaNoti(this.getFechaNoti());
        this.movimientosEmp.setUserCreaMov(1);
        this.movimientosEmp.setFechaCreaMov(new Date());
        getMovimientosEmpFacade().create(movimientosEmp);

        //Se edita información  de empleado
        emp.setCargoFuncional(this.getNuevoCargo());
        emp.setUserModEmp(1);                                                       //USUARIO QUE MODIFICO
        emp.setFechaModEmp(new Date());
        getEmpleadosFacade().edit(emp);

        movimientosEmp = new MovimientosEmp();
        return "gestion_movimientos_emp";
    }
    
    public String guardarMovTemp() {
        //guarda movimiento tempralmente
        Empleados emp = empleadosFacade.buscarEmpNR(this.getNR());
        // CARGO ACTUAL ***
        // NUEVO CARGO ***

        //Registro del Moviemiento de empleado
        this.movimientosEmp.setIdTipoMov(new TipoMov(this.getTipoMovimiento()));
        this.movimientosEmp.setIdEmpleado(new Empleados(this.getEmpleadoSelecionado()));
        this.movimientosEmp.setIdDependencia(new Dependencias(this.getDependencia())); //Nueva dependencia
        this.movimientosEmp.setDepIdDependencia(emp.getIdDependenciaF()); //Antigua dependencia
        this.movimientosEmp.setDependenciaActual(this.getDepenActual());
        this.movimientosEmp.setNuevoCargoMov(this.getNuevoCargo()); //nuevo cargo
        this.movimientosEmp.setCargoActualMov(emp.getCargoFuncional()); //cargo actual
        this.movimientosEmp.setFechaMov(this.getFechaTralado());
        this.movimientosEmp.setFechaIniTemp(this.getFechaInicio());
        this.movimientosEmp.setFechaFinTemp(this.getFechaFinal());
        this.movimientosEmp.setUserCreaMov(1);
        this.movimientosEmp.setFechaCreaMov(new Date());
        getMovimientosEmpFacade().create(movimientosEmp);

        //Se edita información  de empleado
        emp.setCargoFuncional(this.getNuevoCargo());
        emp.setUserModEmp(1);                                                       //USUARIO QUE MODIFICO
        emp.setFechaModEmp(new Date());
        getEmpleadosFacade().edit(emp);

        movimientosEmp = new MovimientosEmp();
        return "gestion_movimientos_emp";
    }
    
    public String editarMovTraslado() { //MOVIEMNTO POR TRASLADO
        Empleados emp = empleadosFacade.buscarEmpNR(this.getNR());
        // CARGO ACTUAL ***
        // NUEVO CARGO ***

        //Registro del Moviemiento de empleado
        this.movimientosEmp.setIdDependencia(new Dependencias(this.getDependencia())); //Nueva dependencia
        this.movimientosEmp.setDepIdDependencia(emp.getIdDependenciaF()); //Antigua dependencia
        this.movimientosEmp.setDependenciaActual(this.getDepenActual());
        this.movimientosEmp.setNuevoCargoMov(this.getNuevoCargo()); //nuevo cargo
        this.movimientosEmp.setCargoActualMov(emp.getCargoFuncional()); //cargo actual
        this.movimientosEmp.setFechaMov(this.getFechaTralado());
        this.movimientosEmp.setFechaNoti(this.getFechaNoti());
        this.movimientosEmp.setUserModMov(1);
        this.movimientosEmp.setFechaModMov(new Date());
        getMovimientosEmpFacade().edit(movimientosEmp);

        //Se edita información  de empleado
        emp.setCargoFuncional(this.getNuevoCargo());
        emp.setUserModEmp(1);                                                       //USUARIO QUE MODIFICO
        emp.setFechaModEmp(new Date());
        getEmpleadosFacade().edit(emp);

        movimientosEmp = new MovimientosEmp();
        return "gestion_movimientos_emp";
    }

    public String editarMovAsc() { //MOVIMIENTO POR ASCENSO
        Empleados emp = empleadosFacade.buscarEmpNR(this.getNR());
        // CARGO ACTUAL ***
        // NUEVO CARGO ***

        //Registro del Moviemiento de empleado
        this.movimientosEmp.setIdDependencia(emp.getIdDependenciaF()); //nueva dependencia sigue siendo la misma
        this.movimientosEmp.setDepIdDependencia(emp.getIdDependenciaF()); //antigua dependecncia sigue siendo la misma

        this.movimientosEmp.setDependenciaActual(this.getDepenActual());
        this.movimientosEmp.setNuevoCargoMov(this.getNuevoCargo()); //nuevo cargo
        this.movimientosEmp.setCargoActualMov(emp.getCargoFuncional()); //cargo actual
        this.movimientosEmp.setFechaMov(this.getFechaTralado());
        this.movimientosEmp.setFechaNoti(this.getFechaNoti());
        this.movimientosEmp.setUserModMov(1);
        this.movimientosEmp.setFechaModMov(new Date());
        getMovimientosEmpFacade().edit(movimientosEmp);

        //Se edita información  de empleado
        emp.setCargoFuncional(this.getNuevoCargo());
        emp.setUserModEmp(1);                                                       //USUARIO QUE MODIFICO
        emp.setFechaModEmp(new Date());
        getEmpleadosFacade().edit(emp);

        movimientosEmp = new MovimientosEmp();
        return "gestion_movimientos_emp";
    }
    
    public String editarMovTemp() { //MOVIMIENTO TEMPORALMENTE
        Empleados emp = empleadosFacade.buscarEmpNR(this.getNR());
        // CARGO ACTUAL ***
        // NUEVO CARGO ***

        //Registro del Moviemiento de empleado
        this.movimientosEmp.setIdDependencia(new Dependencias(this.getDependencia())); //Nueva dependencia
        this.movimientosEmp.setDepIdDependencia(emp.getIdDependenciaF()); //Antigua dependencia
        this.movimientosEmp.setDependenciaActual(this.getDepenActual());
        this.movimientosEmp.setNuevoCargoMov(this.getNuevoCargo()); //nuevo cargo
        this.movimientosEmp.setCargoActualMov(emp.getCargoFuncional()); //cargo actual
        this.movimientosEmp.setFechaMov(this.getFechaTralado());
        this.movimientosEmp.setFechaIniTemp(this.getFechaInicio());
        this.movimientosEmp.setFechaFinTemp(this.getFechaFinal());
        this.movimientosEmp.setUserModMov(1);
        this.movimientosEmp.setFechaModMov(new Date());
        getMovimientosEmpFacade().edit(movimientosEmp);

        //Se edita información  de empleado
        emp.setCargoFuncional(this.getNuevoCargo());
        emp.setUserModEmp(1);                                                       //USUARIO QUE MODIFICO
        emp.setFechaModEmp(new Date());
        getEmpleadosFacade().edit(emp);

        movimientosEmp = new MovimientosEmp();
        return "gestion_movimientos_emp";
    }

    public String editarTipoMov(MovimientosEmp movEmp) {

        //obtine el tipo de movimiento 
        movimientosEmp = movEmp;
        Empleados emp = getEmpleadosFacade().find(movEmp.getIdEmpleado().getIdEmpleado());
        TipoMov m = movEmp.getIdTipoMov();
        this.setNR(movEmp.getIdEmpleado().getNrEmpleado());        
        this.setEmpleadoSelecionado(emp.getIdEmpleado());
        this.setDepenActual(emp.getIdDependenciaF().getNombreDependencia());
        this.setDepActual(emp.getIdDependenciaF().getIdDependencia());
        this.setAntiguoCargo(emp.getCargoFuncional());
        

        //Llenado de Campos para edición
        this.setNombreEmp(movEmp.getIdEmpleado().getNombreEmpleado());
        this.setDirNacinal(movEmp.getDepIdDependencia().getIdDirNac().getIdDirNac());
        this.setDependencia(movEmp.getIdDependencia().getIdDependencia());
        this.setNuevoCargo(movEmp.getNuevoCargoMov());
        this.setFechaTralado(movEmp.getFechaMov());
        this.setFechaNoti(movEmp.getFechaNoti());
        this.setFechaInicio(movEmp.getFechaIniTemp());
        this.setFechaFinal(movEmp.getFechaFinTemp());

        //Solo existen 3 tipos de movimeintos
        switch (m.getIdTipoMov()) {
            case 1: // TRASLADO
                RequestContext.getCurrentInstance().execute("PF('trasladoEditar').show()");
                break;
            case 2: // ASCENSO
                RequestContext.getCurrentInstance().execute("PF('ascensoEditar').show()");
                break;
            case 3: // TRASLADO TEMPORAL
                RequestContext.getCurrentInstance().execute("PF('temEditar').show()");
                break;

        }
        return "gestion_movimientos_emp";
    }
    
    public String eliminarMov(MovimientosEmp movEmp) {
        getMovimientosEmpFacade().remove(movEmp);
        return "gestion_movimientos_emp";
    }

    public String nuevoMovientoEmp() {
        return "reg_movimientos_emp";
    }

    public String cancelarModal() {
        refresh();
        return "reg_movimientos_emp";
    }

    public String cancelarEditar() {
        refresh();
        return "gestion_movimientos_emp";
    }

    public void refresh() { //elimina bean para nuevo ungreso o cancelación
        FacesContext context = FacesContext.getCurrentInstance();
        Application application = context.getApplication();
        ViewHandler viewHandler = application.getViewHandler();
        UIViewRoot viewRoot = viewHandler.createView(context, context.getViewRoot().getViewId());
        context.setViewRoot(viewRoot);
        context.renderResponse();
    }
    
    public manejadorMovimientosEmp() {
    }

}
