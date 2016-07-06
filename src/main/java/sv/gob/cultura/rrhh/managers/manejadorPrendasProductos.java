/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.cultura.rrhh.managers;

import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import sv.gob.cultura.rrhh.entities.Producto;
import sv.gob.cultura.rrhh.entities.ProductoPrestacion;
import sv.gob.cultura.rrhh.entities.Proveedor;
import sv.gob.cultura.rrhh.facades.ProductoFacade;
import sv.gob.cultura.rrhh.facades.ProductoPrestacionFacade;
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
    @EJB
    private ProductoPrestacionFacade productoPrestacionFacade;

//*********************** OBJETOS DE LOS ENTIDADES *****************************
//******************************************************************************    
    private Proveedor proveedor = new Proveedor();
    private Producto producto = new Producto();
//****** VARIABLES QUE CONTRENDRAN ID´S O STRING DE FORMULARIOS ****************
//******************************************************************************  
    private int proveedorProd;          // id de proveedor de productos
//********************** GET DE ENTERPRICE JAVA BEAN ***************************
//******************************************************************************

    public ProveedorFacade getProveedorFacade() {
        return proveedorFacade;
    }

    public ProductoFacade getProductoFacade() {
        return productoFacade;
    }

    public ProductoPrestacionFacade getProductoPrestacionFacade() {
        return productoPrestacionFacade;
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
        try {
            producto.setFechaCreaProd(new Date());
            producto.setUserCreaProd(1);
            getProductoFacade().create(producto);
            producto = new Producto();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Ingresado", "Registro Ingresado");
            FacesContext.getCurrentInstance().addMessage(null, message);
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().getFlash().setKeepMessages(true);
            flashScope().put("manejadorPrendasProductos", this);
            return "gestion_prendas_productos";
        } catch (Exception e) {
            return "gestion_prendas_productos";
        }
    }

    public String gestionProductos() {
        return "gestion_prendas_productos";
    }

    public String eliminarProducto() {
        try {

            List<ProductoPrestacion> prestaciones = getProductoPrestacionFacade().findAll();
            boolean validar = false;

            if (prestaciones == null) {
                getProductoFacade().remove(producto);
            } else {
                Iterator<ProductoPrestacion> iteradorPres = prestaciones.iterator();
                while (iteradorPres.hasNext()) {
                    ProductoPrestacion pres = iteradorPres.next();
                    Integer idprod = pres.getProducto().getIdProducto();

                    if (Objects.equals(producto.getIdProducto(), idprod)) {
                        validar = false;
                        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "NO puede elimar productos que ya han sido asigandos a una Prestación", "NO puede elimar productos que ya han sido asigandos a una Prestación");
                        FacesContext.getCurrentInstance().addMessage(null, message);
                        FacesContext context = FacesContext.getCurrentInstance();
                        context.getExternalContext().getFlash().setKeepMessages(true);
                        flashScope().put("manejadorPrendasProductos", this);
                        break;
                    } else {
                        validar = true;
                    }
                }

            }

            if (validar == true) {
                getProductoFacade().remove(producto);
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Eliminado", "Registro Eliminado");
                FacesContext.getCurrentInstance().addMessage(null, message);
                FacesContext context = FacesContext.getCurrentInstance();
                context.getExternalContext().getFlash().setKeepMessages(true);
                flashScope().put("manejadorPrendasProductos", this);
            }
            producto = new Producto();
            return "gestion_prendas_productos";

        } catch (Exception e) {
            return "gestion_prendas_productos";
        }
    }

    public void productoSeleccinado(Producto prod) {
        producto = prod;
    }

    public String cancelar() {
        this.setProveedorProd(0);
        producto = new Producto();
        return "gestion_prendas_productos";
    }

    public String nuevoProducto() {
        return "prendas_productos";
    }

    public String editarProducto() {
        try {
            producto.setFechaModProd(new Date());
            producto.setUserModProd(1);
            getProductoFacade().edit(producto);
            producto = new Producto();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Modificado", "Registro Modificado");
            FacesContext.getCurrentInstance().addMessage(null, message);
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().getFlash().setKeepMessages(true);
            flashScope().put("manejadorPrendasProductos", this);
            return "gestion_prendas_productos";
        } catch (Exception e) {
            return "gestion_prendas_productos";
        }
    }

    //Mostrar Mensajes en la siguiente peticion!
    public static Flash flashScope() {
        return (FacesContext.getCurrentInstance().getExternalContext().getFlash());
    }

}
