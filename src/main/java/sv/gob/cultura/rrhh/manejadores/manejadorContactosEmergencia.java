/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.cultura.rrhh.manejadores;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.context.RequestContext;
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
public class manejadorContactosEmergencia implements Serializable {

    @EJB
    private ParentescoFacade parentescoFacade;
    @EJB
    private ContactoEmergenciaEmpFacade contactoEmergenciaEmpFacade;
    @EJB
    private EmpleadosFacade empleadosFacade;
    private Empleados selectedEmp = new Empleados();
    private ContactoEmergenciaEmp newContact = new ContactoEmergenciaEmp();
    private List<ContactoEmergenciaEmp> contactsList = new ArrayList<>();

    public manejadorContactosEmergencia() {
    }

    public ContactoEmergenciaEmpFacade getContactoEmergenciaEmpFacade() {
        return contactoEmergenciaEmpFacade;
    }

    public EmpleadosFacade getEmpleadosFacade() {
        return empleadosFacade;
    }

    public ParentescoFacade getParentescoFacade() {
        return parentescoFacade;
    }

    public Empleados getSelectedEmp() {
        return selectedEmp;
    }

    public void setSelectedEmp(Empleados selectedEmp) {
        this.selectedEmp = selectedEmp;
    }

    public ContactoEmergenciaEmp getNewContact() {
        return newContact;
    }

    public void setNewContact(ContactoEmergenciaEmp newContact) {
        this.newContact = newContact;
    }

    public List<ContactoEmergenciaEmp> getContactsList() {
        return contactsList;
    }

    public void setContactsList(List<ContactoEmergenciaEmp> contactsList) {
        this.contactsList = contactsList;
    }

    public List<Parentesco> todosParentesco() {
        return getParentescoFacade().findAll();
    }

    public List<Empleados> employees() {
        return getEmpleadosFacade().findAll();
    }

    public List<Parentesco> getAllParentescos() {
        return getParentescoFacade().findAll();
    }

    public void handleClick() {
        contactsList = new ArrayList<>();
        contactsList = getContactoEmergenciaEmpFacade().buscarContactos(selectedEmp.getIdEmpleado());
    }

    public void guardarContactosEmergencia() {
        try {
            newContact.setIdEmpleado(selectedEmp);
            newContact.setFechaCreaContac(new Date());
            newContact.setUserCreaContac(1);
            getContactoEmergenciaEmpFacade().create(newContact);
            newContact = new ContactoEmergenciaEmp();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Ingresado", "Registro Ingresado");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } catch (Exception e) {

        }
    }

    public void editarEmergencia() {
        try {
            newContact.setFechaModContac(new Date());
            newContact.setUserModContac(1);
            getContactoEmergenciaEmpFacade().edit(newContact);
            newContact = new ContactoEmergenciaEmp();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Modificado", "Registro Modificado");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } catch (Exception e) {

        }
    }

    public String eliminar() {
        try {
            getContactoEmergenciaEmpFacade().remove(newContact);
            newContact = new ContactoEmergenciaEmp();
            RequestContext.getCurrentInstance().execute("PF('confirmation').hide()");
            RequestContext.getCurrentInstance().update("tabla");
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Eliminado", "Registro Eliminado");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return null;
        } catch (Exception e) {
            return null;
        }
    }
}
