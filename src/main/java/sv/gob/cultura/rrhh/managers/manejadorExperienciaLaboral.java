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
import javax.inject.Named;
import javax.faces.view.ViewScoped;
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

    @EJB
    private ExperienciaLaboralFacade experienciaLaboralFacade;
    @EJB
    private EmpleadosFacade empleadosFacade;
    @EJB
    private DependenciasFacade dependenciasFacade;
    @EJB
    private DirNacionalFacade dirNacionalFacade;
    private ExperienciaLaboral newExpLab = new ExperienciaLaboral(), selectedExpLab = new ExperienciaLaboral();
    private Empleados selectedEmp = new Empleados();
    private int direccionNacional;          // id direccion nacional para filtrar dependencias
    private int dependecia;                 // id dependencia para filtrar empleado
    private String nombreEmp;               // nombre de empleado seleccionado
    private String NR;                      // NR de empleado para realizar busqueda
    private Date fechaDesde;

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

    public ExperienciaLaboral getNewExpLab() {
        return newExpLab;
    }

    public void setNewExpLab(ExperienciaLaboral newExpLab) {
        this.newExpLab = newExpLab;
    }

    public ExperienciaLaboral getSelectedExpLab() {
        return selectedExpLab;
    }

    public void setSelectedExpLab(ExperienciaLaboral selectedExpLab) {
        this.selectedExpLab = selectedExpLab;
    }

    public Empleados getSelectedEmp() {
        return selectedEmp;
    }

    public void setSelectedEmp(Empleados selectedEmp) {
        this.selectedEmp = selectedEmp;
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

    public List<DirNacional> todosDirNacional() {
        return getDirNacionalFacade().findAll();
    }

    public List<Dependencias> dependenciasFiltradas() {
        return getDependenciasFacade().buscarDependencias(this.getDireccionNacional());
    }

    public List<Empleados> fetchEmpleadosFiltrado() {
        return getEmpleadosFacade().buscarEmp(this.getDependecia());
    }

    public List<ExperienciaLaboral> fetchExpLaboralesByEmp() {
        if (selectedEmp.getIdEmpleado() != null) {
            return getExperienciaLaboralFacade().findByEmpId(selectedEmp.getIdEmpleado());
        } else {
            return null;
        }
    }

    public void saveExpLaboral() {
        try {
            newExpLab.setIdEmpleado(selectedEmp);
            newExpLab.setFechaCreaExp(new Date());
            //Temporal assigment
            newExpLab.setUserCreaExp(1);
            getExperienciaLaboralFacade().create(newExpLab);
            newExpLab = new ExperienciaLaboral();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Ingresado", "Registro Ingresado");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } catch (Exception e) {

        }
    }

    public void editExpLaboral() {
        try {
            selectedExpLab.setFechaModExp(new Date());
            selectedExpLab.setUserModExp(1);
            getExperienciaLaboralFacade().edit(selectedExpLab);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Modificado", "Registro Modificado");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } catch (Exception e) {

        }

    }

    public void deleteExpLaboral() {
        try {
            getExperienciaLaboralFacade().remove(selectedExpLab);
            selectedExpLab = new ExperienciaLaboral();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Eliminado", "Registro Eliminado");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } catch (Exception e) {
        }
    }

}
