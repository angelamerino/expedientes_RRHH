/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.cultura.rrhh.managers;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.application.ViewHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import sv.gob.cultura.rrhh.entities.Dependencias;
import sv.gob.cultura.rrhh.entities.DirNacional;
import sv.gob.cultura.rrhh.entities.Empleados;
import sv.gob.cultura.rrhh.entities.GradoSancion;
import sv.gob.cultura.rrhh.entities.Sanciones;
import sv.gob.cultura.rrhh.entities.TipoSancion;
import sv.gob.cultura.rrhh.facades.DependenciasFacade;
import sv.gob.cultura.rrhh.facades.DirNacionalFacade;
import sv.gob.cultura.rrhh.facades.EmpleadosFacade;
import sv.gob.cultura.rrhh.facades.GradoSancionFacade;
import sv.gob.cultura.rrhh.facades.SancionesFacade;
import sv.gob.cultura.rrhh.facades.TipoSancionFacade;

/**
 *
 * @author Edwin
 */
@Named(value = "manejadorAmonestacionesSanciones")
@ViewScoped
public class manejadorAmonestacionesSanciones implements Serializable {
// ************** LLAMADA A LOS ENTERPRICE JAVA BEANS **************************
//******************************************************************************

    @EJB
    private SancionesFacade sancionesFacade;
    @EJB
    private GradoSancionFacade gradoSancionFacade;
    @EJB
    private TipoSancionFacade tipoSancionFacade;
    @EJB
    private EmpleadosFacade empleadosFacade;
    @EJB
    private DependenciasFacade dependenciasFacade;
    @EJB
    private DirNacionalFacade dirNacionalFacade;
//*********************** OBJETOS DE LOS ENTIDADES *****************************
//******************************************************************************
    Sanciones sanciones = new Sanciones();
//****** VARIABLES QUE CONTRENDRAN ID´S O STRING DE FORMULARIOS ****************
//******************************************************************************
    private int direccionNacional;                              // id direccion nacional para filtar dependencias
    private int dependecia;                                     // id de dependecias para filtrar empleados
    private int empleadoSelecionado;                            // id d empleado selecinado
    private int gradoSancion;                                   // id de grado de sancion para filtrar tipo de sancion
    private int tipoSancion;                                    // id de tipo de sancion
    private String nombreEmp;                                   // nombre de empleado selecinado
    private String NR;                                          // NR de empleado para realizar busquda
//********************** GET DE ENTERPRICE JAVA BEAN ***************************
//******************************************************************************

    public SancionesFacade getSancionesFacade() {
        return sancionesFacade;
    }

    public GradoSancionFacade getGradoSancionFacade() {
        return gradoSancionFacade;
    }

    public TipoSancionFacade getTipoSancionFacade() {
        return tipoSancionFacade;
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

    public Sanciones getSanciones() {
        return sanciones;
    }

    public void setSanciones(Sanciones sanciones) {
        this.sanciones = sanciones;
    }

    public int getDireccionNacional() {
        return direccionNacional;
    }

    public void setDireccionNacional(int direccionNacional) {
        this.direccionNacional = direccionNacional;
    }

    public int getDependecia() {
        return dependecia;
    }

    public void setDependecia(int dependecia) {
        this.dependecia = dependecia;
    }

    public int getEmpleadoSelecionado() {
        return empleadoSelecionado;
    }

    public void setEmpleadoSelecionado(int empleadoSelecionado) {
        Empleados emp = getEmpleadosFacade().find(empleadoSelecionado);
        this.setNombreEmp(emp.getNombreEmpleado());
        this.empleadoSelecionado = empleadoSelecionado;
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

    public int getGradoSancion() {
        return gradoSancion;
    }

    public void setGradoSancion(int gradoSancion) {
        this.gradoSancion = gradoSancion;
    }

    public int getTipoSancion() {
        return tipoSancion;
    }

    public void setTipoSancion(int tipoSancion) {
        this.tipoSancion = tipoSancion;
    }

//******************************* LISTADOS *************************************
//****************************************************************************** 
    public List<DirNacional> todosDirNacional() {
        return getDirNacionalFacade().findAll();
    }

    public List<Dependencias> dependenciasFiltradas() {
        return getDependenciasFacade().findByDirNac(this.getDireccionNacional());
    }

    public List<Empleados> empleadoFiltrado() {
        return getEmpleadosFacade().findByDependencia(this.getDependecia());
    }

    public List<TipoSancion> todosTipoSancionIdGradoSancion() {
        return getTipoSancionFacade().buscarTipoSancionByGradoSancion(this.getGradoSancion());
    }

    public List<GradoSancion> todosGradosSancion() {
        return getGradoSancionFacade().findAll();
    }

    public List<Sanciones> todasSanciones() {
        return getSancionesFacade().findAll();
    }
//******************************* FUNCIONES ************************************
//******************************************************************************

    public String guardarAmonestacionSancion() {
        try {
            if (this.getEmpleadoSelecionado() == 0) { //verifica si se seleccino un empleado
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Seleccione Empleado", "Seleccione Empleado"));
                return null;
            } else {
                sanciones.setIdEmpleado(new Empleados(this.getEmpleadoSelecionado()));
                sanciones.setIdTipoSancion(new TipoSancion(this.getTipoSancion()));
                sanciones.setUserCreaSancion(1);
                sanciones.setFechaCreaSancion(new Date());
                getSancionesFacade().create(sanciones);
                sanciones = new Sanciones();
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Ingresado", "Registro Ingresado");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return "gestion_amonestaciones_sanciones";
            }
        } catch (Exception e) {
            return "reg_amonestaciones_sanciones";
        }
    }

    public String editarAmonestacionSancion() {
        try {
            sanciones.setIdTipoSancion(new TipoSancion(this.getTipoSancion()));
            sanciones.setUserModSancion(1);
            sanciones.setFechaModSancion(new Date());
            getSancionesFacade().edit(sanciones);
            sanciones = new Sanciones();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Modificado", "Registro Modificado");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return "gestion_amonestaciones_sanciones";
        } catch (Exception e) {
            return "gestion_amonestaciones_sanciones";
        }

    }

    public String eliminarAmonestacionSancion() {
        try {
            getSancionesFacade().remove(sanciones);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Eliminado", "Registro Eliminado");
            FacesContext.getCurrentInstance().addMessage(null, message);
            sanciones = new Sanciones();
            return "gestion_amonestaciones_sanciones";
        } catch (Exception e) {
            System.out.println("HOLA MUNDO");
            return "gestion_amonestaciones_sanciones";
        }
    }

    public void AnomestacionSancionSeleccionada(Sanciones san) {
        sanciones = san;
    }

    public String cancelar() {
        return "gestion_amonestaciones_sanciones";
    }

    public String nuevaAmonestacionSancion() {
        return "reg_amonestaciones_sanciones";
    }

    public void buscarNR(ActionEvent event) {
        // busca empleado por nr
        Empleados emp = getEmpleadosFacade().buscarEmpNR(this.getNR());
        if (emp == null) {
            this.setNombreEmp("");
        } else {
            this.setEmpleadoSelecionado(emp.getIdEmpleado());
            this.setNombreEmp(emp.getNombreEmpleado());
        }
    }

    public void refresh() { //Elimina el bean dejando limpio para nueva registro o cancelacion
        FacesContext context = FacesContext.getCurrentInstance();
        Application application = context.getApplication();
        ViewHandler viewHandler = application.getViewHandler();
        UIViewRoot viewRoot = viewHandler.createView(context, context.getViewRoot().getViewId());
        context.setViewRoot(viewRoot);
        context.renderResponse();
    }

    public manejadorAmonestacionesSanciones() {
    }

}
