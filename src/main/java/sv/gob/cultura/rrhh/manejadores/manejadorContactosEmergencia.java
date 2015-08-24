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
import sv.gob.cultura.rrhh.entidades.ContactoEmergenciaEmp;
import sv.gob.cultura.rrhh.entidades.Dependencias;
import sv.gob.cultura.rrhh.entidades.DirNacional;
import sv.gob.cultura.rrhh.entidades.Empleados;
import sv.gob.cultura.rrhh.entidades.Parentesco;
import sv.gob.cultura.rrhh.facades.ContactoEmergenciaEmpFacade;
import sv.gob.cultura.rrhh.facades.DependenciasFacade;
import sv.gob.cultura.rrhh.facades.DirNacionalFacade;
import sv.gob.cultura.rrhh.facades.EmpleadosFacade;
import sv.gob.cultura.rrhh.facades.ParentescoFacade;

/**
 *
 * @author Edwin
 */
@Named(value = "manejadorContactosEmergencia")
@ViewScoped
public class manejadorContactosEmergencia implements Serializable {

//******************************************************************************
// ************** LLAMADA A LOS ENTERPRICE JAVA BEANS **************************
//******************************************************************************
    @EJB
    private ParentescoFacade parentescoFacade;
    @EJB
    private ContactoEmergenciaEmpFacade contactoEmergenciaEmpFacade;
    @EJB
    private EmpleadosFacade empleadosFacade;
    @EJB
    private DirNacionalFacade dirNacionalFacade;
    @EJB
    private DependenciasFacade dependenciasFacade;

    public manejadorContactosEmergencia() {
    }
//******************************************************************************
//*********************** OBJETOS DE LOS ENTIDADES *****************************
//******************************************************************************    
    ContactoEmergenciaEmp contactoEmergenciaEmp = new ContactoEmergenciaEmp();
    Empleados empleado = new Empleados();
    Parentesco parentesco = new Parentesco();

//******************************************************************************
//****** VARIABLES QUE CONTRENDRAN ID´S O STRING DE FORMULARIOS ****************
//******************************************************************************    
    private int direccionNacional;      // id de Direccion Nacional para filtrar dependencias
    private int dependecia;             // id de dependencias para filtrar empleados
    private int empleadoSelecionado;    // id de empleado selecinado
    private String nombreEmp;           // nombre de empleado selecinado
    private String NR;                  // NR de empleado para hacer la busqueda

// *****************************************************************************
//********************** GET DE ENTERPRICE JAVA BEAN ***************************
//******************************************************************************
    public ContactoEmergenciaEmpFacade getContactoEmergenciaEmpFacade() {
        return contactoEmergenciaEmpFacade;
    }

    public EmpleadosFacade getEmpleadosFacade() {
        return empleadosFacade;
    }

    public ContactoEmergenciaEmp getContactoEmergenciaEmp() {
        return contactoEmergenciaEmp;
    }

    public ParentescoFacade getParentescoFacade() {
        return parentescoFacade;
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
    public void setContactoEmergenciaEmp(ContactoEmergenciaEmp contactoEmergenciaEmp) {
        this.contactoEmergenciaEmp = contactoEmergenciaEmp;
    }

    public Empleados getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleados empleado) {
        this.empleado = empleado;
    }

    public Parentesco getParentesco() {
        return parentesco;
    }

    public void setParentesco(Parentesco parentesco) {
        this.parentesco = parentesco;
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
    public List<Parentesco> todosParentesco() {
        return getParentescoFacade().findAll();
    }

    public List<ContactoEmergenciaEmp> contactoEmergenciaEmpleado() {
        return getContactoEmergenciaEmpFacade().buscarContactos(this.getEmpleadoSelecionado());
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
    public void guardarContactosEmergencia() {
        //Setea Id empleado, fecha de creacion y el usuario que lo ingreso de momento idUsuer 1
        contactoEmergenciaEmp.setIdEmpleado(new Empleados(this.getEmpleadoSelecionado()));
        contactoEmergenciaEmp.setFechaCreaContac(new Date());
        contactoEmergenciaEmp.setUserCreaContac(1);     
        getContactoEmergenciaEmpFacade().create(contactoEmergenciaEmp);
        contactoEmergenciaEmp = new ContactoEmergenciaEmp();
    }
    
    public void editarEmergencia(){
        contactoEmergenciaEmp.setFechaModContac(new Date());
        contactoEmergenciaEmp.setUserModContac(1); 
        getContactoEmergenciaEmpFacade().edit(contactoEmergenciaEmp);
        contactoEmergenciaEmp = new ContactoEmergenciaEmp();
    }

    public String eliminar(ContactoEmergenciaEmp contacto) {
        getContactoEmergenciaEmpFacade().remove(contacto);
        return null;
    }

    public void buscarNR(ActionEvent event){
        Empleados emp = getEmpleadosFacade().buscarEmpNR(this.getNR());
        if (emp == null) {
            this.setNombreEmp("");
        } else {
            this.setEmpleadoSelecionado(emp.getIdEmpleado());
            this.setNombreEmp(emp.getNombreEmpleado());
        }
    }
    
    public void empleadoSelecionadoValido(ActionEvent event) {
        //verifica si se seleciono un empleado o si se busco un empleado
        if (this.getEmpleadoSelecionado() == 0) {
            contactoEmergenciaEmp = new ContactoEmergenciaEmp();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Debe Buscar un Empleado"));
        } else {
            RequestContext.getCurrentInstance().execute("PF('emergencia').show()");
        }
    }
}
