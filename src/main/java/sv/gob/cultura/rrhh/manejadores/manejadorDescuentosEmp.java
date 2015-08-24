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
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import sv.gob.cultura.rrhh.entidades.Dependencias;
import sv.gob.cultura.rrhh.entidades.DescuentosEmp;
import sv.gob.cultura.rrhh.entidades.DirNacional;
import sv.gob.cultura.rrhh.entidades.Empleados;
import sv.gob.cultura.rrhh.entidades.TipoDescuento;
import sv.gob.cultura.rrhh.facades.DependenciasFacade;
import sv.gob.cultura.rrhh.facades.DescuentosEmpFacade;
import sv.gob.cultura.rrhh.facades.DirNacionalFacade;
import sv.gob.cultura.rrhh.facades.EmpleadosFacade;
import sv.gob.cultura.rrhh.facades.TipoDescuentoFacade;

/**
 *
 * @author Edwin
 */
@Named(value = "manejadorDescuentosEmp")
@ViewScoped
public class manejadorDescuentosEmp implements Serializable {

    private static final long serialVersionUID = 1L;
// ************** LLAMADA A LOS ENTERPRICE JAVA BEANS **************************
//******************************************************************************
    @EJB
    private TipoDescuentoFacade tipoDescuentoFacade;
    @EJB
    private EmpleadosFacade empleadosFacade;
    @EJB
    private DescuentosEmpFacade descuentosEmpFacade;
    @EJB
    private DirNacionalFacade dirNacionalFacade;
    @EJB
    private DependenciasFacade dependenciasFacade;
//*********************** OBJETOS DE LOS ENTIDADES *****************************
//******************************************************************************
    DescuentosEmp descuentosEmp = new DescuentosEmp();
    Empleados empleados = new Empleados();
    TipoDescuento TipoDescuento = new TipoDescuento();
//****** VARIABLES QUE CONTRENDRAN ID´S O STRING DE FORMULARIOS ****************
//******************************************************************************
    private int direccionNacional;                  //id Direccion Nacional Para filtar dependencias
    private int dependecia;                         //id Dependencia para filtar empleado
    private int empleadoSelecionado = 0;            //id empleado inicialmente en cero = ninguno
//********************** GET DE ENTERPRICE JAVA BEAN ***************************
//******************************************************************************

    public TipoDescuentoFacade getTipoDescuentoFacade() {
        return tipoDescuentoFacade;
    }

    public EmpleadosFacade getEmpleadosFacade() {
        return empleadosFacade;
    }

    public DescuentosEmpFacade getDescuentosEmpFacade() {
        return descuentosEmpFacade;
    }

    public DirNacionalFacade getDirNacionalFacade() {
        return dirNacionalFacade;
    }

    public DependenciasFacade getDependenciasFacade() {
        return dependenciasFacade;
    }

//******************* GET y SET DE OBJETOS DE ENTIDADES ************************
//******************************************************************************
    public DescuentosEmp getDescuentosEmp() {
        return descuentosEmp;
    }

    public void setDescuentosEmp(DescuentosEmp descuentosEmp) {
        this.descuentosEmp = descuentosEmp;
    }

    public Empleados getEmpleados() {
        return empleados;
    }

    public void setEmpleados(Empleados empleados) {
        this.empleados = empleados;
    }

    public TipoDescuento getTipoDescuento() {
        return TipoDescuento;
    }

    public void setTipoDescuento(TipoDescuento TipoDescuento) {
        this.TipoDescuento = TipoDescuento;
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

    public int getEmpleadoSelecionado() {
        return empleadoSelecionado;
    }

    public void setEmpleadoSelecionado(int empleadoSelecionado) {
        this.empleadoSelecionado = empleadoSelecionado;
    }

// **************** LISTA DE ELEMENTOS EN TABLAS *******************************
//******************************************************************************
    public List<TipoDescuento> todosTIpoDescuentos() {
        return getTipoDescuentoFacade().findAll();
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

    public List<DescuentosEmp> todosDescuentos() {
        return getDescuentosEmpFacade().findAll();
    }
//******************************* FUNCIONES ************************************
//******************************************************************************

    public String guardarDescuento() {
        if (this.getEmpleadoSelecionado() == 0) { //Verifica que se ha seleccinado un empleado
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Seleccione un Empleado"));
            return null;
        } else {
            descuentosEmp.setIdEmpleado(new Empleados(this.getEmpleadoSelecionado()));
            descuentosEmp.setUserCreaDesc(1); // USUARIO CREA
            descuentosEmp.setFechaCreaDesc(new Date());
            getDescuentosEmpFacade().create(descuentosEmp);
            descuentosEmp = new DescuentosEmp();
            empleadoSelecionado = 0;
            return "gestion_descuentos_emp";
        }
    }

    public String editarDescuento() {
        descuentosEmp.setUserModDesc(1); // USUARIO MODIFICA
        descuentosEmp.setFechaModDesc(new Date());
        getDescuentosEmpFacade().edit(descuentosEmp);
        descuentosEmp = new DescuentosEmp();
        empleadoSelecionado = 0;
        return "gestion_descuentos_emp";
    }

    public String cancelar() {
        return "reg_descuentos_emp";
    }
   
    public String cancelarEditar() {
        return "gestion_descuentos_emp";
    }
    
    public String nuevoDescuento() {
        return "reg_descuentos_emp";
    }
    
    public String eliminarDescuento(DescuentosEmp DesEmp) {
        getDescuentosEmpFacade().remove(DesEmp);
        return "gestion_descuentos_emp";
    }

    public manejadorDescuentosEmp() {
    }

}
