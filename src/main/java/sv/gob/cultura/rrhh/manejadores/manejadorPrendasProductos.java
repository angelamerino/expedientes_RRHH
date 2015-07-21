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
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import sv.gob.cultura.rrhh.entidades.Producto;
import sv.gob.cultura.rrhh.entidades.Proveedor;
import sv.gob.cultura.rrhh.facades.ProductoFacade;
import sv.gob.cultura.rrhh.facades.ProveedorFacade;

/**
 *
 * @author Edwin
 */
//1. implements Serializable
//2. Insertar Facade con Call Bean
//3. Generar solo Get al Bean(2)
//4. Crear un objeto de la entidad
//5. Get y Set del objeto (all+insert)
@Named(value = "manejadorPrendasProductos")
@ViewScoped
public class manejadorPrendasProductos implements Serializable {

// ************** LLAMADA A LOS ENTERPRICE JAVA BEANS **************************
//******************************************************************************
    @EJB
    private ProveedorFacade proveedorFacade;
    @EJB
    private ProductoFacade productoFacade;

//*********************** OBJETOS DE LOS ENTIDADES *****************************
//******************************************************************************    
    private Proveedor proveedor = new Proveedor();
    private Producto producto = new Producto();
//****** VARIABLES QUE CONTRENDRAN ID´S O STRING DE FORMULARIOS ****************
//******************************************************************************  
    private int proveedorProd;
//********************** GET DE ENTERPRICE JAVA BEAN ***************************
//******************************************************************************

    public ProveedorFacade getProveedorFacade() {
        return proveedorFacade;
    }

    public ProductoFacade getProductoFacade() {
        return productoFacade;
    }

//******************* GET y SET DE OBJETOS DE ENTIDADES ************************
//******************************************************************************
    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getProveedorProd() {
        return proveedorProd;
    }

    public void setProveedorProd(int proveedorProd) {
        this.proveedorProd = proveedorProd;
    }

// **************** LISTA DE ELEMENTOS EN TABLAS *******************************
//******************************************************************************
    public List<Proveedor> todosProveedores() {
        return getProveedorFacade().findAll();
    }

    public List<Producto> todosProductos() {
        return getProductoFacade().findAll();
    }

//*************************** FUNCIONES DE GUARDAR *****************************
//******************************************************************************
    public manejadorPrendasProductos() {
    }

    public String guardarProducto() {
        producto.setFechaCreaProd(new Date());
        producto.setUserCreaProd(1);
        getProductoFacade().create(producto);
        producto = new Producto();
        return "gestion_prendas_productos";
    }

    public String eliminarProducto(Producto prod) {
        getProductoFacade().remove(prod);        
        return "gestion_prendas_productos";
    }

    public String cancelar() {
        this.setProveedorProd(0);
        producto = new Producto();
        return "gestion_prendas_productos";
    }
    
    public String nuevoProducto(){
        return "reg_prendas_productos";
    }
    
    public String editarProducto(){
        producto.setFechaModProd(new Date());
        producto.setUserModProd(1);
        getProductoFacade().edit(producto);
        producto = new Producto();
        return "gestion_prendas_productos";
    }

}
