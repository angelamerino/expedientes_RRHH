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
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.context.RequestContext;
import sv.gob.cultura.rrhh.entidades.Dependencias;
import sv.gob.cultura.rrhh.entidades.DirNacional;
import sv.gob.cultura.rrhh.entidades.Empleados;
import sv.gob.cultura.rrhh.entidades.ExperienciaLaboral;
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
public class manejadorExperienciaLaboral implements Serializable{

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
        experienciaLaboral.setIdEmpleado(new Empleados(this.getEmpleadoSelecionado()));
        experienciaLaboral.setSectorExpLab("Público");
        
        //Fehca de creacion y usuario=1
        experienciaLaboral.setFechaCreaExp(new Date());
        experienciaLaboral.setUserCreaExp(1);
        
        getExperienciaLaboralFacade().create(experienciaLaboral);
        experienciaLaboral = new ExperienciaLaboral();
    }

    public void guardarExpLaboralPrivado() {
        experienciaLaboral.setIdEmpleado(new Empleados(this.getEmpleadoSelecionado()));
        experienciaLaboral.setSectorExpLab("Privado");
        
        //Fehca de creacion y usuario=1
        experienciaLaboral.setFechaCreaExp(new Date());
        experienciaLaboral.setUserCreaExp(1);
        
        getExperienciaLaboralFacade().create(experienciaLaboral);
        experienciaLaboral = new ExperienciaLaboral();
    }
    public void editarExpLaboral(){
        //Fehca de creacion y usuario=1
        experienciaLaboral.setFechaModExp(new Date());
        experienciaLaboral.setUserModExp(1);
        
        getExperienciaLaboralFacade().edit(experienciaLaboral);
        experienciaLaboral = new ExperienciaLaboral();
    }
    public String eliminar(ExperienciaLaboral experiencia) {
        getExperienciaLaboralFacade().remove(experiencia);
        return null;
    }   
    
    public void empleadoSelecionadoValidoP(ActionEvent event) {
        // verifica que se seleciono un empleado o que se busco un empleado
        if (this.getEmpleadoSelecionado() == 0) {
            experienciaLaboral = new ExperienciaLaboral();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Debe Buscar un Empleado"));
        } else {
            RequestContext.getCurrentInstance().execute("PF('publico').show()");
        }
    }
    public void empleadoSelecionadoValidoPP(ActionEvent event) {
        // verifica que se seleciono un empleado o que se busco un empleado
        if (this.getEmpleadoSelecionado() == 0) {
            experienciaLaboral = new ExperienciaLaboral();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Debe Buscar un Empleado"));
        } else {
            RequestContext.getCurrentInstance().execute("PF('privado').show()");
        }
    }
    
    public void buscarNR(ActionEvent event){
        // busca empleado por nr
        Empleados emp = getEmpleadosFacade().buscarEmpNR(this.getNR());
        if (emp == null) {
            this.setNombreEmp("");
        } else {
            this.setEmpleadoSelecionado(emp.getIdEmpleado());
            this.setNombreEmp(emp.getNombreEmpleado());
        }
    }

}
