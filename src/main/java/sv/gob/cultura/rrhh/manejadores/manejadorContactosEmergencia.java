/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.cultura.rrhh.manejadores;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import sv.gob.cultura.rrhh.entidades.ContactoEmergenciaEmp;
import sv.gob.cultura.rrhh.entidades.Empleados;
import sv.gob.cultura.rrhh.entidades.Parentesco;
import sv.gob.cultura.rrhh.facades.ContactoEmergenciaEmpFacade;
import sv.gob.cultura.rrhh.facades.EmpleadosFacade;
import sv.gob.cultura.rrhh.facades.ParentescoFacade;

/**
 *
 * @author Edwin
 */
@Named(value = "manejadorContactosEmergencia")
@ViewScoped
public class manejadorContactosEmergencia implements Serializable{

    public manejadorContactosEmergencia() {
    }
    
//******************************************************************************
// ************** LLAMADA A LOS ENTERPRICE JAVA BEANS **************************
//******************************************************************************
    @EJB
    private ParentescoFacade parentescoFacade;
    @EJB
    private ContactoEmergenciaEmpFacade contactoEmergenciaEmpFacade;
    @EJB
    private EmpleadosFacade empleadosFacade;
    
//******************************************************************************
//*********************** OBJETOS DE LOS ENTIDADES *****************************
//******************************************************************************    
    ContactoEmergenciaEmp contactoEmergenciaEmp = new ContactoEmergenciaEmp();
    Empleados empleado = new Empleados();
    Parentesco parentesco = new Parentesco();
    
//******************************************************************************
//****** VARIABLES QUE CONTRENDRAN ID´S O STRING DE FORMULARIOS ****************
//******************************************************************************
    
    private int empleadoId = 3;  // id empleado Ahora prueba empleado id=3

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

    public int getEmpleadoId() {
        return empleadoId;
    }

    public void setEmpleadoId(int empleadoId) {
        this.empleadoId = empleadoId;
    }

    public Parentesco getParentesco() {
        return parentesco;
    }

    public void setParentesco(Parentesco parentesco) {
        this.parentesco = parentesco;
    }
//******************************************************************************
// **************** LISTA DE ELEMENTOS EN TABLAS *******************************
//******************************************************************************
    
    public List<Parentesco> todosParentesco() {
        return getParentescoFacade().findAll();
    }
    
    public List<ContactoEmergenciaEmp> todosContactoEmergenciaEmp() {
        return getContactoEmergenciaEmpFacade().findAll();
    }
    
//******************************************************************************
//*************************** FUNCIONES DE GUARDAR *****************************
//******************************************************************************
    
    public void guardarContactosEmergencia() {
        contactoEmergenciaEmp.setIdEmpleado(new Empleados(empleadoId));
        getContactoEmergenciaEmpFacade().create(contactoEmergenciaEmp);
        contactoEmergenciaEmp = new ContactoEmergenciaEmp();
    }
}
