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
import org.primefaces.context.RequestContext;
import sv.gob.cultura.rrhh.entities.Dependencias;
import sv.gob.cultura.rrhh.entities.DirNacional;
import sv.gob.cultura.rrhh.entities.Empleados;
import sv.gob.cultura.rrhh.entities.HistorialSalarial;
import sv.gob.cultura.rrhh.entities.TipoMejoraSalarial;
import sv.gob.cultura.rrhh.facades.DependenciasFacade;
import sv.gob.cultura.rrhh.facades.DirNacionalFacade;
import sv.gob.cultura.rrhh.facades.EmpleadosFacade;
import sv.gob.cultura.rrhh.facades.HistorialSalarialFacade;
import sv.gob.cultura.rrhh.facades.TipoMejoraSalarialFacade;

/**
 *
 * @author olvarey
 */
@Named(value = "manejadorAumentoMejoraSalarial")
@ViewScoped
public class manejadorAumentoMejoraSalarial implements Serializable {

    private static final long serialVersionUID = 1L;
    @EJB
    private HistorialSalarialFacade historialSalarialFacade;
    @EJB
    private TipoMejoraSalarialFacade tipoMejoraSalarialFacade;
    @EJB
    private EmpleadosFacade empleadosFacade;
    @EJB
    private DependenciasFacade dependenciasFacade;
    @EJB
    private DirNacionalFacade dirNacionalFacade;
    private HistorialSalarial newHistSal = new HistorialSalarial(), selectedHistSal = new HistorialSalarial();
    private Empleados selectedEmp = new Empleados();
    private int direccionNacional;
    private int dependecia;

    public HistorialSalarial getNewHistSal() {
        return newHistSal;
    }

    public void setNewHistSal(HistorialSalarial newHistSal) {
        this.newHistSal = newHistSal;
    }

    public HistorialSalarial getSelectedHistSal() {
        return selectedHistSal;
    }

    public void setSelectedHistSal(HistorialSalarial selectedHistSal) {
        this.selectedHistSal = selectedHistSal;
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

    public HistorialSalarialFacade getHistorialSalarialFacade() {
        return historialSalarialFacade;
    }

    public TipoMejoraSalarialFacade getTipoMejoraSalarialFacade() {
        return tipoMejoraSalarialFacade;
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

    public List<DirNacional> fetchDireccionesNacionales() {
        return getDirNacionalFacade().findAll();
    }

    public List<Dependencias> fetchDependenciasFiltradas() {
        return getDependenciasFacade().findByDirNac(direccionNacional);
    }

    public List<Empleados> fetchFilteredEmps() {
        return getEmpleadosFacade().findByDependencia(dependecia);
    }

    public List<TipoMejoraSalarial> fetchTiposMejora() {
        return getTipoMejoraSalarialFacade().findAll();
    }

    public List<HistorialSalarial> fetchHistorialesSalariales() {
        return getHistorialSalarialFacade().findAll();
    }

    public List<HistorialSalarial> fetchHistorialByEmp() {
        if (selectedEmp.getIdEmpleado() != null) {
            return getHistorialSalarialFacade().findByIdEmp(selectedEmp.getIdEmpleado());

        } else {
            return null;
        }
    }

    public void checkEmpSelection() {
        RequestContext context = RequestContext.getCurrentInstance();
        if (selectedEmp.getIdEmpleado() != null) {
            context.update("panel-new-historial");
            context.execute("PF('dlgNewHistSal').show();");
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Selección de empleado", "Debe seleccionar un empleado de la lista"));
        }
    }

    public void saveMejoraSalarial() {
        try {
            newHistSal.setFechaCreaHsal(new Date());
            newHistSal.setUserCreaHsal(1);
            newHistSal.setIdEmpleado(selectedEmp);
            getHistorialSalarialFacade().create(newHistSal);
            newHistSal = new HistorialSalarial();
        } catch (Exception e) {
        }
    }
}
