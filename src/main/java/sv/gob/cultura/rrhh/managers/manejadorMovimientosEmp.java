/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.cultura.rrhh.managers;

import java.io.Serializable;
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
import sv.gob.cultura.rrhh.entities.MovimientosEmp;
import sv.gob.cultura.rrhh.entities.TipoMov;
import sv.gob.cultura.rrhh.facades.DependenciasFacade;
import sv.gob.cultura.rrhh.facades.DirNacionalFacade;
import sv.gob.cultura.rrhh.facades.EmpleadosFacade;
import sv.gob.cultura.rrhh.facades.MovimientosEmpFacade;
import sv.gob.cultura.rrhh.facades.TipoMovFacade;

/**
 *
 * @author Edwin
 */
@Named(value = "manejadorMovimientosEmp")
@ViewScoped
public class manejadorMovimientosEmp implements Serializable {

    private static final long serialVersionUID = 1L;
    @EJB
    private MovimientosEmpFacade movimientosEmpFacade;
    @EJB
    private TipoMovFacade tipoMovFacade;
    @EJB
    private EmpleadosFacade empleadosFacade;
    @EJB
    private DependenciasFacade dependenciasFacade;
    @EJB
    private DirNacionalFacade dirNacionalFacade;
    private Empleados selectedEmp = new Empleados();
    private MovimientosEmp newMovEmp = new MovimientosEmp();
    private int tipoMovimiento;
    private int direccionNacional;
    private int dependecia;

    public manejadorMovimientosEmp() {

    }

    public MovimientosEmpFacade getMovimientosEmpFacade() {
        return movimientosEmpFacade;
    }

    public TipoMovFacade getTipoMovFacade() {
        return tipoMovFacade;
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

    public Empleados getSelectedEmp() {
        return selectedEmp;
    }

    public void setSelectedEmp(Empleados selectedEmp) {
        this.selectedEmp = selectedEmp;
    }

    public MovimientosEmp getNewMovEmp() {
        return newMovEmp;
    }

    public void setNewMovEmp(MovimientosEmp newMovEmp) {
        this.newMovEmp = newMovEmp;
    }

    public int getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(int tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
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

    public List<TipoMov> fetchTiposMov() {
        return getTipoMovFacade().findAll();
    }

    public List<DirNacional> todosDirNacional() {
        return getDirNacionalFacade().findAll();
    }

    public List<Dependencias> dependenciasFiltradas() {
        return getDependenciasFacade().buscarDependencias(direccionNacional);
    }

    public List<Empleados> fetchEmpleadosFiltrados() {
        return getEmpleadosFacade().buscarEmp(dependecia);
    }

    public List<MovimientosEmp> fetchAllMovs() {
        return getMovimientosEmpFacade().findAll();
    }

    public void saveMovimiento() {
        try {
            if (selectedEmp.getIdEmpleado() == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Empleado no encontrado", "Empleado no encontrado"));
            } else {
            }
        } catch (Exception e) {
        }
    }

}
