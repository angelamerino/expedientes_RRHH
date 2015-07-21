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
import sv.gob.cultura.rrhh.entidades.Anio;
import sv.gob.cultura.rrhh.entidades.Dependencias;
import sv.gob.cultura.rrhh.entidades.DirNacional;
import sv.gob.cultura.rrhh.entidades.Empleados;
import sv.gob.cultura.rrhh.entidades.Prestacion;
import sv.gob.cultura.rrhh.entidades.Producto;
import sv.gob.cultura.rrhh.entidades.TipoPrestacion;
import sv.gob.cultura.rrhh.facades.AnioFacade;
import sv.gob.cultura.rrhh.facades.DependenciasFacade;
import sv.gob.cultura.rrhh.facades.DirNacionalFacade;
import sv.gob.cultura.rrhh.facades.EmpleadosFacade;
import sv.gob.cultura.rrhh.facades.PrestacionFacade;
import sv.gob.cultura.rrhh.facades.ProductoFacade;
import sv.gob.cultura.rrhh.facades.TipoPrestacionFacade;

/**
 *
 * @author Edwin
 */
@Named(value = "manejadorPrestaciones")
@ViewScoped
public class manejadorPrestaciones implements Serializable {

// ************** LLAMADA A LOS ENTERPRICE JAVA BEANS **************************
//******************************************************************************
    @EJB
    private ProductoFacade productoFacade;
    @EJB
    private AnioFacade anioFacade;
    @EJB
    private TipoPrestacionFacade tipoPrestacionFacade;
    @EJB
    private PrestacionFacade prestacionFacade;
    @EJB
    private EmpleadosFacade empleadosFacade;
    @EJB
    private DirNacionalFacade dirNacionalFacade;
    @EJB
    private DependenciasFacade dependenciasFacade;
//*********************** OBJETOS DE LOS ENTIDADES *****************************
//******************************************************************************    
    private Producto producto = new Producto();
    private Anio anio = new Anio();
    private TipoPrestacion tipoPrestacion = new TipoPrestacion();
    private Prestacion prestacion = new Prestacion();
    Empleados empleado = new Empleados();
//****** VARIABLES QUE CONTRENDRAN ID´S O STRING DE FORMULARIOS ****************
//****************************************************************************** 
    private int producPrestacion;
    private int cantidad;
    private int idPrestacionSelecionada;
    private int direccionNacional;
    private int dependecia;
    private int empleadoSelecionado;
    private int idPrestacionAsirnar;
    private int idPresGestion;
    private String nombreEmp;
//********************** GET DE ENTERPRICE JAVA BEAN ***************************
//******************************************************************************

    public ProductoFacade getProductoFacade() {
        return productoFacade;
    }

    public AnioFacade getAnioFacade() {
        return anioFacade;
    }

    public TipoPrestacionFacade getTipoPrestacionFacade() {
        return tipoPrestacionFacade;
    }

    public PrestacionFacade getPrestacionFacade() {
        return prestacionFacade;
    }

    public EmpleadosFacade getEmpleadosFacade() {
        return empleadosFacade;
    }

    public DirNacionalFacade getDirNacionalFacade() {
        return dirNacionalFacade;
    }

    public DependenciasFacade getDependenciasFacade() {
        return dependenciasFacade;
    }

//******************* GET y SET DE OBJETOS DE ENTIDADES ************************
//******************************************************************************
    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Anio getAnio() {
        return anio;
    }

    public void setAnio(Anio anio) {
        this.anio = anio;
    }

    public TipoPrestacion getTipoPrestacion() {
        return tipoPrestacion;
    }

    public void setTipoPrestacion(TipoPrestacion tipoPrestacion) {
        this.tipoPrestacion = tipoPrestacion;
    }

    public Prestacion getPrestacion() {
        return prestacion;
    }

    public void setPrestacion(Prestacion prestacion) {
        this.prestacion = prestacion;
    }

    public int getProducPrestacion() {
        return producPrestacion;
    }

    public void setProducPrestacion(int producPrestacion) {
        this.producPrestacion = producPrestacion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getIdPrestacionSelecionada() {
        return idPrestacionSelecionada;
    }

    public void setIdPrestacionSelecionada(int idPrestacionSelecionada) {
        this.idPrestacionSelecionada = idPrestacionSelecionada;
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

    public Empleados getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleados empleado) {
        this.empleado = empleado;
    }

    public int getIdPrestacionAsirnar() {
        return idPrestacionAsirnar;
    }

    public void setIdPrestacionAsirnar(int idPrestacionAsirnar) {
        this.idPrestacionAsirnar = idPrestacionAsirnar;
    }

    public int getIdPresGestion() {
        return idPresGestion;
    }

    public void setIdPresGestion(int idPresGestion) {
        this.idPresGestion = idPresGestion;
    }

    
// **************** LISTA DE ELEMENTOS EN TABLAS *******************************
//******************************************************************************
    public List<TipoPrestacion> todosTipoPrestacion() {
        return getTipoPrestacionFacade().findAll();
    }

    public List<Anio> todosAnios() {
        return getAnioFacade().findAll();
    }

    public List<Producto> todosProductos() {
        return getProductoFacade().findAll();
    }

    public List<Prestacion> todasPrestaciones() {
        return getPrestacionFacade().findAll();
    }

    public List<Prestacion> todasPrestacionesEmpleado() {
        Empleados emp = getEmpleadosFacade().find(getEmpleadoSelecionado());

        if (emp == null) {
            return null;
        } else {
            return emp.getPrestacionList();
        }

    }

    public List<Producto> productosPrestacion() {
        Prestacion pres = getPrestacionFacade().find(this.getIdPrestacionSelecionada());
        if (pres == null) {
            return null;
        } else {
            return null;//pres.getProductoList();
        }
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

    public List<Empleados> empleadoPrestaciones() {
        //busca los empleados de una prestacion dada!!!
        Prestacion pres = getPrestacionFacade().find(this.getIdPresGestion());
        if (pres == null) {
            return null;
        } else {
            return pres.getEmpleadosList();
        }
    }
//*************************** FUNCIONES DE GUARDAR *****************************
//******************************************************************************    

    public manejadorPrestaciones() {
    }

    public String guardarPrestacion() {
        prestacion.setFechaCreaPrestacion(new Date());
        prestacion.setUserCreaPrestacion(1);
        prestacion.setCostoPrestacion(0.00);        
        getPrestacionFacade().create(prestacion);
        prestacion = new Prestacion();
        return "gestion_prestaciones";
    }

    //Edita unicamente el año
    public String editarPrestacion() {
        prestacion.setFechaModPrestacion(new Date());
        prestacion.setUserModPrestacion(1);
        getPrestacionFacade().edit(prestacion);
        prestacion = new Prestacion();
        return "gestion_prestaciones";
    }

    public String eliminarPrestacion(Prestacion pres) {
        //Elimina productos de la prestacion
        //Setear costo de la prestacion a cero
        //Eliminar prestacion

        getPrestacionFacade().remove(pres);
        return "gestion_prestaciones";
    }

    public String cancelarPrestacion() {
        return "gestion_prestaciones";
    }

    public void cancelar() {

    }

    public String nuevaPrestacion() {
        return "reg_prestaciones";
    }

    public String addProdPrestacion() {
//        Prestacion pres = getPrestacionFacade().find(this.getIdPrestacionSelecionada());
//        List<Producto> prodPres = pres.getProductoList();
//        prodPres.add(new Producto(this.getProducPrestacion()));
//        pres.setProductoList(prodPres);
//        //Actualizar el costo de la prestacion sumando el costo del producto eliminado
//        getPrestacionFacade().edit(pres);
        return "gestion_prestaciones";
    }

    public String eliminarProdPrestacion(Producto prod) {
//        Prestacion pres = getPrestacionFacade().find(this.getIdPrestacionSelecionada());
//        List<Producto> prodPres = pres.getProductoList();
//        prodPres.remove(prod);
//        pres.setProductoList(prodPres);
//        //Actualizar el costo de la prestacion restando el costo del producto eliminado
//        getPrestacionFacade().edit(pres);
        return "gestion_prestaciones";
    }
    
    public String buscarEmpleadosPrestacion(){
        System.out.println("Prestacion seleccionada: "+this.getIdPresGestion());
        return null;
    }

    public void addPresEmp() {
        Empleados emp = getEmpleadosFacade().find(getEmpleadoSelecionado());
        List<Prestacion> prestacionList = emp.getPrestacionList();
        prestacionList.add(new Prestacion(this.getIdPrestacionAsirnar()));
        emp.setPrestacionList(prestacionList);
        getEmpleadosFacade().edit(emp);
        this.setIdPrestacionAsirnar(0);
    }
    
    public void addEmpPres(){
        Empleados emp = getEmpleadosFacade().find(getEmpleadoSelecionado());
        System.out.println("Empleado: " + emp.getNombreEmpleado());
        
        List<Prestacion> prestacionList = emp.getPrestacionList();
        prestacionList.add(new Prestacion(this.getIdPresGestion()));
        emp.setPrestacionList(prestacionList);
        
        Prestacion pres = getPrestacionFacade().find(this.getIdPresGestion());
        List<Empleados> empleadosList = pres.getEmpleadosList();
        empleadosList.add(emp);
        
        getPrestacionFacade().edit(pres);        
        getEmpleadosFacade().edit(emp);
        
        
    }

    public void eliminarPresEmp(Prestacion pres) {
        Empleados emp = getEmpleadosFacade().find(getEmpleadoSelecionado());
        List<Prestacion> prestacionList = emp.getPrestacionList();
        prestacionList.remove(pres);
        emp.setPrestacionList(prestacionList);
        getEmpleadosFacade().edit(emp);
    }
    
    public String eliminarEmpleadoPrestacion(Empleados emp){
        List<Prestacion> prestacionList = emp.getPrestacionList();
        prestacionList.remove(new Prestacion(this.getIdPresGestion()));
        emp.setPrestacionList(prestacionList);
        
        Prestacion pres = getPrestacionFacade().find(this.getIdPresGestion());
        List<Empleados> empleadosList = pres.getEmpleadosList();
        empleadosList.remove(emp);
        
        getPrestacionFacade().edit(pres);
        getEmpleadosFacade().edit(emp);
        
        return null;
    }
    
    public void nuevoEmpPrestacion(ActionEvent event){
        if (this.getIdPresGestion()== 0) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Seleccione una Prestación"));
        } else {
            this.setDireccionNacional(0);
            this.setDependecia(0);
            this.setEmpleadoSelecionado(0);
            RequestContext.getCurrentInstance().execute("PF('nuevoEmp').show()");
        }
    }

    public void empleadoSelecionadoValido(ActionEvent event) {
        if (this.getEmpleadoSelecionado() == 0) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Seleccione un Empleado"));
        } else {
            RequestContext.getCurrentInstance().execute("PF('asigPrestaciones').show()");
        }
    }

}
