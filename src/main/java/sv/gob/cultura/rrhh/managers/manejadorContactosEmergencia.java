/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.cultura.rrhh.managers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import sv.gob.cultura.rrhh.entities.ContactoEmergenciaEmp;
import sv.gob.cultura.rrhh.entities.Dependencias;
import sv.gob.cultura.rrhh.entities.DirNacional;
import sv.gob.cultura.rrhh.entities.Empleados;
import sv.gob.cultura.rrhh.entities.Parentesco;
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

    @EJB
    private DependenciasFacade dependenciasFacade;
    @EJB
    private DirNacionalFacade dirNacionalFacade;
    @EJB
    private ParentescoFacade parentescoFacade;
    @EJB
    private ContactoEmergenciaEmpFacade contactoEmergenciaEmpFacade;
    @EJB
    private EmpleadosFacade empleadosFacade;
    private Empleados selectedEmp = new Empleados();
    private ContactoEmergenciaEmp newContact = new ContactoEmergenciaEmp(), selectedContact = new ContactoEmergenciaEmp();
    private List<ContactoEmergenciaEmp> contactsList = new ArrayList<>();
    private int direccionNacional, dependecia;

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

    public ContactoEmergenciaEmp getSelectedContact() {
        return selectedContact;
    }

    public void setSelectedContact(ContactoEmergenciaEmp selectedContact) {
        this.selectedContact = selectedContact;
    }

    public List<ContactoEmergenciaEmp> getContactsList() {
        return contactsList;
    }

    public void setContactsList(List<ContactoEmergenciaEmp> contactsList) {
        this.contactsList = contactsList;
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

    public List<Parentesco> todosParentesco() {
        return getParentescoFacade().findAll();
    }

    public List<Parentesco> getAllParentescos() {
        return getParentescoFacade().findAll();
    }

    public List<DirNacional> allDirNacional() {
        return dirNacionalFacade.findAll();
    }

    public List<Dependencias> dependenciasFiltradas() {
        filteredEmployees().clear();
        return dependenciasFacade.buscarDependencias(direccionNacional);
    }

    public List<Empleados> filteredEmployees() {
        return getEmpleadosFacade().buscarEmp(dependecia);
    }

    public void populateContacts() {
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
            populateContacts();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Ingreso de información", "El registro ha sido creado de manera exitosa.");
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
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Modificación de información", "El registro ha sido editado de manera exitosa.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } catch (Exception e) {

        }
    }

    public String eliminar() {
        try {
            getContactoEmergenciaEmpFacade().remove(selectedContact);
            selectedContact = new ContactoEmergenciaEmp();
            populateContacts();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Borrado de información", "El registro ha sido eliminado de manera exitosa.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public void resetEmployees() {
        filteredEmployees().clear();
    }
}
