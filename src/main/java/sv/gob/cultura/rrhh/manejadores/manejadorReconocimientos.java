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
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import sv.gob.cultura.rrhh.entidades.Dependencias;
import sv.gob.cultura.rrhh.entidades.DirNacional;
import sv.gob.cultura.rrhh.entidades.Empleados;
import sv.gob.cultura.rrhh.entidades.Reconocimientos;
import sv.gob.cultura.rrhh.facades.DependenciasFacade;
import sv.gob.cultura.rrhh.facades.DirNacionalFacade;
import sv.gob.cultura.rrhh.facades.EmpleadosFacade;
import sv.gob.cultura.rrhh.facades.ReconocimientosFacade;

/**
 *
 * @author Edwin
 */
@Named(value = "manejadorReconocimientos")
@ViewScoped
public class manejadorReconocimientos implements Serializable {
// ************** LLAMADA A LOS ENTERPRICE JAVA BEANS **************************
//******************************************************************************

    @EJB
    private ReconocimientosFacade reconocimientosFacade;
    @EJB
    private EmpleadosFacade empleadosFacade;
    @EJB
    private DependenciasFacade dependenciasFacade;
    @EJB
    private DirNacionalFacade dirNacionalFacade;
//*********************** OBJETOS DE LOS ENTIDADES *****************************
//******************************************************************************
    private Reconocimientos reconocimientos = new Reconocimientos();
//****** VARIABLES QUE CONTRENDRAN ID´S O STRING DE FORMULARIOS ****************
//******************************************************************************
    private int direccionNacional;                              // id direccion nacional para filtar dependencias
    private int dependecia;                                     // id de dependecias para filtrar empleados
    private int empleadoSelecionado;                            // id d eempleado selecinado
    private String nombreEmp;                                   // nombre de empleado selecinado
    private String NR;                                          // NR de empleado para realizar busquda
//********************** GET DE ENTERPRICE JAVA BEAN ***************************
//******************************************************************************

    public ReconocimientosFacade getReconocimientosFacade() {
        return reconocimientosFacade;
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

    public Reconocimientos getReconocimientos() {
        return reconocimientos;
    }

    public void setReconocimientos(Reconocimientos reconocimientos) {
        this.reconocimientos = reconocimientos;
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
//******************************* LISTADOS *************************************
//****************************************************************************** 

    public List<DirNacional> todosDirNacional() {
        return getDirNacionalFacade().findAll();
    }

    public List<Dependencias> dependenciasFiltradas() {
        return getDependenciasFacade().buscarDependencias(this.getDireccionNacional());
    }

    public List<Empleados> empleadoFiltrado() {
        return getEmpleadosFacade().buscarEmp(this.getDependecia());
    }

    public List<Reconocimientos> todosReconocimientosEmp() {
        return getReconocimientosFacade().findAll();
    }
//******************************* FUNCIONES ************************************
//******************************************************************************

    public String guardarReconocimiento() {
        try {
            if (this.getEmpleadoSelecionado() == 0) { //verifica si se seleccino un empleado
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Seleccione Empleado", "Seleccione Empleado"));
                return null;
            } else {
                reconocimientos.setIdEmpleado(new Empleados(this.getEmpleadoSelecionado()));
                reconocimientos.setUserCreaRec(1);
                reconocimientos.setFechaCreaRec(new Date());
                getReconocimientosFacade().create(reconocimientos);
                reconocimientos = new Reconocimientos();
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Ingresado", "Registro Ingresado");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return "gestion_reconocimientos";
            }
        } catch (Exception e) {
            return "reg_reconocimientos";
        }
    }

    public String editarReconocimiento() {
        try {
            reconocimientos.setUserModRec(1);
            reconocimientos.setFechaModRec(new Date());
            getReconocimientosFacade().edit(reconocimientos);
            reconocimientos = new Reconocimientos();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Modificado", "Registro Modificado");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return "gestion_reconocimientos";
        } catch (Exception e) {
            return "gestion_reconocimientos";
        }
    }

    public String nuevoReconocimiento() {
        return "reg_reconocimientos";
    }

    public String cancelar() {
        refresh();
        return "reg_reconocimientos";
    }

    public String cancelarEditar() {
        return "gestion_reconocimientos";
    }

    public String eliminarReconocimiento() {
        try {
            getReconocimientosFacade().remove(reconocimientos);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Eliminado", "Registro Eliminado");
            FacesContext.getCurrentInstance().addMessage(null, message);
            reconocimientos = new Reconocimientos();
            return "gestion_reconocimientos";
        } catch (Exception e) {
            return "gestion_reconocimientos";
        }
    }

    public void reconocimientoSeleccinado(Reconocimientos recono) {
        reconocimientos = recono;
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

    public manejadorReconocimientos() {
    }

}
