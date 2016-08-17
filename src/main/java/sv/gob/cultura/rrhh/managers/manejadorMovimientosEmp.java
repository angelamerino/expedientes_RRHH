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
    private MovimientosEmp newMovEmp = new MovimientosEmp(), selectedMovEmp = new MovimientosEmp();
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

    public MovimientosEmp getSelectedMovEmp() {
        return selectedMovEmp;
    }

    public void setSelectedMovEmp(MovimientosEmp selectedMovEmp) {
        this.selectedMovEmp = selectedMovEmp;
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
        return getDependenciasFacade().findByDirNac(direccionNacional);
    }

    public List<Empleados> fetchEmpleadosFiltrados() {
        return getEmpleadosFacade().findByDependencia(dependecia);
    }

    public List<MovimientosEmp> fetchAllMovs() {
        return getMovimientosEmpFacade().findByIdEmp(selectedEmp);
    }

    public void saveMovimiento() {
        try {
            newMovEmp.setIdEmpleado(selectedEmp);
            //Crea historico de cargo en objeto newMovEmp
            newMovEmp.setCargoActualMov(selectedEmp.getCargoFuncional());
            //Temporal using user 1
            newMovEmp.setUserCreaMov(1);
            newMovEmp.setFechaCreaMov(new Date());
            newMovEmp.setDependenciaActual(selectedEmp.getIdDependenciaN().getNombreDependencia());
            //Actualiza cargo funcional con el nuevo de objeto newMovEmp
            selectedEmp.setCargoNominal(newMovEmp.getNuevoCargoMov());
            selectedEmp.setIdDependenciaN(new Dependencias(dependecia));
            //Persist on database
            getMovimientosEmpFacade().create(newMovEmp);
            getEmpleadosFacade().edit(selectedEmp);
            newMovEmp = new MovimientosEmp();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Guardar movimiento", "Movimiento guardado correctamente"));
        } catch (Exception e) {
        }
    }

    public void editMovimiento() {
        Empleados emp = new Empleados();
        try {
            selectedMovEmp.setFechaModMov(new Date());
            selectedMovEmp.setUserModMov(1);
            if (direccionNacional != 0 && dependecia != 0) {
                emp = getEmpleadosFacade().find(selectedMovEmp.getIdEmpleado().getIdEmpleado());
                emp.setIdDependenciaN(new Dependencias(dependecia));
                getMovimientosEmpFacade().edit(selectedMovEmp);
                getEmpleadosFacade().edit(emp);
            } else {
                getMovimientosEmpFacade().edit(selectedMovEmp);
            }
            direccionNacional = dependecia = 0;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Editar movimiento", "Movimiento editado correctamente"));
        } catch (Exception e) {
        }
    }
    
    public void checkEmpSelection() {
        RequestContext context = RequestContext.getCurrentInstance();
        if (selectedEmp.getIdEmpleado() != null) {
            context.update("panel-new-mov");
            context.execute("PF('dlgNewMovEmp').show();");
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Selección de empleado", "Debe seleccionar un empleado de la lista"));
        }
    }
}
