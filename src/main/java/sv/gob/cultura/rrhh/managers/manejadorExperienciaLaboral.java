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
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.context.RequestContext;
import sv.gob.cultura.rrhh.entities.Dependencias;
import sv.gob.cultura.rrhh.entities.DirNacional;
import sv.gob.cultura.rrhh.entities.Empleados;
import sv.gob.cultura.rrhh.entities.ExperienciaLaboral;
import sv.gob.cultura.rrhh.facades.DependenciasFacade;
import sv.gob.cultura.rrhh.facades.DirNacionalFacade;
import sv.gob.cultura.rrhh.facades.EmpleadosFacade;
import sv.gob.cultura.rrhh.facades.ExperienciaLaboralFacade;

/**
 *
 * @author Edwin
 */
@Named(value = "manejadorExperienciaLaboral")
@ViewScoped
public class manejadorExperienciaLaboral implements Serializable {

    public manejadorExperienciaLaboral() {
    }
//******************************************************************************
// ************** LLAMADA A LOS ENTERPRICE JAVA BEANS **************************
//******************************************************************************
    @EJB
    private ExperienciaLaboralFacade experienciaLaboralFacade;
    @EJB
    private EmpleadosFacade empleadosFacade;
    @EJB
    private DependenciasFacade dependenciasFacade;
    @EJB
    private DirNacionalFacade dirNacionalFacade;
//******************************************************************************
//*********************** OBJETOS DE LOS ENTIDADES *****************************
//******************************************************************************    
    ExperienciaLaboral experienciaLaboral = new ExperienciaLaboral();
//******************************************************************************
//****** VARIABLES QUE CONTRENDRAN ID´S O STRING DE FORMULARIOS ****************
//******************************************************************************
    private int direccionNacional;          // id direccion nacional para filtrar dependencias
    private int dependecia;                 // id dependencia para filtrar empleado
    private int empleadoSelecionado;        // id de empleado selecionado
    private String nombreEmp;               // nombre de empleado seleccionado
    private String NR;                      // NR de empleado para realizar busqueda
    private Date fechaDesde;
// *****************************************************************************
//********************** GET DE ENTERPRICE JAVA BEAN ***************************
//******************************************************************************

    public ExperienciaLaboralFacade getExperienciaLaboralFacade() {
        return experienciaLaboralFacade;
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

// *****************************************************************************
//******************* GET y SET DE OBJETOS DE ENTIDADES ************************
//******************************************************************************
    public ExperienciaLaboral getExperienciaLaboral() {
        return experienciaLaboral;
    }

    public void setExperienciaLaboral(ExperienciaLaboral experienciaLaboral) {
        this.experienciaLaboral = experienciaLaboral;
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

    public Date getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(Date fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

//******************************************************************************
// **************** LISTA DE ELEMENTOS EN TABLAS *******************************
//******************************************************************************
    public List<ExperienciaLaboral> experienciaLaboralPublico() {
        return getExperienciaLaboralFacade().experienciaLaboralSector("Público", this.getEmpleadoSelecionado());
    }

    public List<ExperienciaLaboral> experienciaLaboralPrivado() {
        return getExperienciaLaboralFacade().experienciaLaboralSector("Privado", this.getEmpleadoSelecionado());
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
//******************************************************************************
//*************************** FUNCIONES DE GUARDAR *****************************
//******************************************************************************

    public void guardarExpLaboralPublico() {
        try {
            experienciaLaboral.setIdEmpleado(new Empleados(this.getEmpleadoSelecionado()));
            experienciaLaboral.setSectorExpLab("Público");
            experienciaLaboral.setFechaDesdeExpLab(this.getFechaDesde());

            //Fehca de creacion y usuario=1
            experienciaLaboral.setFechaCreaExp(new Date());
            experienciaLaboral.setUserCreaExp(1);

            getExperienciaLaboralFacade().create(experienciaLaboral);
            experienciaLaboral = new ExperienciaLaboral();
            this.setFechaDesde(new Date());

            RequestContext.getCurrentInstance().execute("PF('publico').hide()");
            RequestContext.getCurrentInstance().update("tabla1");

            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Ingresado", "Registro Ingresado");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } catch (Exception e) {

        }
    }

    public void guardarExpLaboralPrivado() {
        try {
            experienciaLaboral.setIdEmpleado(new Empleados(this.getEmpleadoSelecionado()));
            experienciaLaboral.setSectorExpLab("Privado");
            experienciaLaboral.setFechaDesdeExpLab(this.getFechaDesde());

            //Fehca de creacion y usuario=1
            experienciaLaboral.setFechaCreaExp(new Date());
            experienciaLaboral.setUserCreaExp(1);

            getExperienciaLaboralFacade().create(experienciaLaboral);
            experienciaLaboral = new ExperienciaLaboral();
            this.setFechaDesde(new Date());

            RequestContext.getCurrentInstance().execute("PF('privado').hide()");
            RequestContext.getCurrentInstance().update("tabla2");

            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Ingresado", "Registro Ingresado");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } catch (Exception e) {

        }

    }

    public void editarExpLaboral() {
        try {
            //Fehca de creacion y usuario=1
            experienciaLaboral.setFechaModExp(new Date());
            experienciaLaboral.setUserModExp(1);
            experienciaLaboral.setFechaDesdeExpLab(this.getFechaDesde());

            getExperienciaLaboralFacade().edit(experienciaLaboral);
            experienciaLaboral = new ExperienciaLaboral();
            this.setFechaDesde(new Date());

            RequestContext.getCurrentInstance().execute("PF('privadoEdit').hide()");
            RequestContext.getCurrentInstance().execute("PF('publicoEdit').hide()");
            RequestContext.getCurrentInstance().update("tabla1");
            RequestContext.getCurrentInstance().update("tabla2");

            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Modificado", "Registro Modificado");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } catch (Exception e) {

        }

    }

    public void experienciaLaboralSeleccionada(ExperienciaLaboral experiencia) {
        experienciaLaboral = experiencia;
    }

    public String eliminar() {
        try {
            getExperienciaLaboralFacade().remove(experienciaLaboral);
            experienciaLaboral = new ExperienciaLaboral();
            this.setFechaDesde(new Date());
            RequestContext.getCurrentInstance().execute("PF('confirmation').hide()");
            RequestContext.getCurrentInstance().execute("PF('confirmation1').hide()");
            RequestContext.getCurrentInstance().update("tabla1");
            RequestContext.getCurrentInstance().update("tabla2");
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

    public void empleadoSelecionadoValidoP(ActionEvent event) {
        // verifica que se seleciono un empleado o que se busco un empleado
        if (this.getEmpleadoSelecionado() == 0) {
            experienciaLaboral = new ExperienciaLaboral();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debe Buscar un Empleado", "Debe Buscar un Empleado"));
        } else {
            RequestContext.getCurrentInstance().execute("PF('publico').show()");
        }
    }

    public void empleadoSelecionadoValidoPP(ActionEvent event) {
        // verifica que se seleciono un empleado o que se busco un empleado
        if (this.getEmpleadoSelecionado() == 0) {
            experienciaLaboral = new ExperienciaLaboral();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debe Buscar un Empleado", "Debe Buscar un Empleado"));
        } else {
            RequestContext.getCurrentInstance().execute("PF('privado').show()");
        }
    }

    public void tabla() {//Actualiza tabla y nombre de empleado
        RequestContext.getCurrentInstance().update("nombre1");
        RequestContext.getCurrentInstance().update("nombre2");
        RequestContext.getCurrentInstance().update("tabla1");
        RequestContext.getCurrentInstance().update("tabla2");
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

}
