/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.cultura.rrhh.manejadores;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.apache.log4j.PropertyConfigurator;
import org.primefaces.context.RequestContext;
import sv.gob.cultura.rrhh.entidades.Anio;
import sv.gob.cultura.rrhh.entidades.Dependencias;
import sv.gob.cultura.rrhh.entidades.DirNacional;
import sv.gob.cultura.rrhh.entidades.Empleados;
import sv.gob.cultura.rrhh.entidades.Prestacion;
import sv.gob.cultura.rrhh.entidades.Producto;
import sv.gob.cultura.rrhh.entidades.ProductoPrestacion;
import sv.gob.cultura.rrhh.entidades.ProductoPrestacionPK;
import sv.gob.cultura.rrhh.entidades.TipoPrestacion;
import sv.gob.cultura.rrhh.facades.AnioFacade;
import sv.gob.cultura.rrhh.facades.DependenciasFacade;
import sv.gob.cultura.rrhh.facades.DirNacionalFacade;
import sv.gob.cultura.rrhh.facades.EmpleadosFacade;
import sv.gob.cultura.rrhh.facades.PrestacionFacade;
import sv.gob.cultura.rrhh.facades.ProductoFacade;
import sv.gob.cultura.rrhh.facades.ProductoPrestacionFacade;
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
    @EJB
    private ProductoPrestacionFacade productoPrestacionFacade;

//*********************** OBJETOS DE LOS ENTIDADES *****************************
//******************************************************************************    
    private Producto producto = new Producto();
    private Anio anio = new Anio();
    private TipoPrestacion tipoPrestacion = new TipoPrestacion();
    private Prestacion prestacion = new Prestacion();
    private Empleados empleado = new Empleados();
    private ProductoPrestacionPK productoPrestacionPK = new ProductoPrestacionPK();
    private ProductoPrestacion productoPrestacion = new ProductoPrestacion();

//****** VARIABLES QUE CONTRENDRAN ID´S O STRING DE FORMULARIOS ****************
//****************************************************************************** 
    private int producPrestacion;               // id de productos
    private int cantidad;                       // cantidad de productos
    private int idPrestacionSelecionada;        // id de prestacin selecionada
    private int direccionNacional;              // id direccion nacional para filtar dependencias
    private int dependecia;                     // id dependecia para filtar empleado
    private int empleadoSelecionado;            // id empleado selecionado
    private int idPrestacionAsirnar;            // id prestacion asignar
    private int idPresGestion;                  // id prestacion para editar y adicionar productos
    private String nombreEmp;                   // nombre de empleado selecionado
    private String NR;                          // NR empleado para busqueda
    private double totalPrestacion;

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

    public ProductoPrestacionFacade getProductoPrestacionFacade() {
        return productoPrestacionFacade;
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

    public ProductoPrestacionPK getProductoPrestacionPK() {
        return productoPrestacionPK;
    }

    public void setProductoPrestacionPK(ProductoPrestacionPK productoPrestacionPK) {
        this.productoPrestacionPK = productoPrestacionPK;
    }

    public ProductoPrestacion getProductoPrestacion() {
        return productoPrestacion;
    }

    public void setProductoPrestacion(ProductoPrestacion productoPrestacion) {
        this.productoPrestacion = productoPrestacion;
    }

    public String getNR() {
        return NR;
    }

    public void setNR(String NR) {
        this.NR = NR;
    }

    public double getTotalPrestacion() {
        return totalPrestacion;
    }

    public void setTotalPrestacion(double totalPrestacion) {
        this.totalPrestacion = totalPrestacion;
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

    public List<Prestacion> todasPrestacionesPorAnio() {//AÑO EN CURSO
        List<Prestacion> pres = getPrestacionFacade().buscarPrestacionAnio(obtenerAnio(new Date()));
        return pres;
    }

    public List<Prestacion> todasPrestacionesEmpleado() {
        Empleados emp = getEmpleadosFacade().find(getEmpleadoSelecionado());

        if (emp == null) {
            return null;
        } else {
            return emp.getPrestacionList();
        }

    }

    public List<ProductoPrestacion> productosPrestacion() {
        return getProductoPrestacionFacade().buscarProdIdPrestacion(this.getIdPrestacionSelecionada());
    }

    public List<ProductoPrestacion> productosPrestacionReporte() {
        this.totalPrestacion = 0.0;
        List<ProductoPrestacion> lista = getProductoPrestacionFacade().buscarProdIdPrestacion(this.getIdPrestacionSelecionada());

        Iterator<ProductoPrestacion> listaIterador = lista.iterator();
        while (listaIterador.hasNext()) {
            ProductoPrestacion elemento = listaIterador.next();
            double tot = elemento.getProducto().getCostoUnit() * elemento.getCantidad();
            this.totalPrestacion = this.totalPrestacion + tot;
            
        }

        return lista;
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
    public String guardarPrestacion() {
        try {
            prestacion.setFechaCreaPrestacion(new Date());      //Fecha de creación
            prestacion.setUserCreaPrestacion(1);                //Usuario que crea Prestación
            prestacion.setCostoPrestacion(0.00);
            getPrestacionFacade().create(prestacion);
            prestacion = new Prestacion();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Ingresado", "Registro Ingresado");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return "gestion_prestaciones";
        } catch (Exception e) {
            return "prestaciones";
        }
    }

    //Edita unicamente el año
    public String editarPrestacion() {
        try {
            prestacion.setFechaModPrestacion(new Date());
            prestacion.setUserModPrestacion(1);
            getPrestacionFacade().edit(prestacion);
            prestacion = new Prestacion();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Modificado", "Registro Modificado");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return "gestion_prestaciones";
        } catch (Exception e) {
            return "gestion_prestaciones";
        }
    }

    public void prestacionSelecionada(Prestacion pres) {
        prestacion = pres;
    }

    public String eliminarPrestacion() {
        try {
            //Elimina productos de la prestacion para eliminar en cascada
            List<ProductoPrestacion> todosProducPres = productosPrestacion();
            //Elimina cada uno de los productos
            Iterator<ProductoPrestacion> nombreIterator = todosProducPres.iterator();
            while (nombreIterator.hasNext()) {
                ProductoPrestacion elemento = nombreIterator.next();
                getProductoPrestacionFacade().remove(elemento);
            }

            getPrestacionFacade().remove(prestacion);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Eliminado", "Registro Eliminado");
            FacesContext.getCurrentInstance().addMessage(null, message);

            prestacion = new Prestacion();

            return "gestion_prestaciones";
        } catch (Exception e) {
            return "gestion_prestaciones";
        }
    }

    public String cancelarPrestacion() {
        //Formulario para un nuevo ingreso
        this.setProducPrestacion(0);
        this.setCantidad(0);
        return "gestion_prestaciones";
    }

    public void cancelar() {

    }

    public String nuevaPrestacion() {
        return "prestaciones";
    }

    public void addProdPrestacion() {
        try {
            //Setear idprestacion e idproducto en ProductoPrestacionPK
            productoPrestacionPK.setIdPrestacion(this.getIdPrestacionSelecionada());
            productoPrestacionPK.setIdProducto(this.getProducPrestacion());

            boolean existe = false;
            List<ProductoPrestacion> todosProducPres = productosPrestacion();
            for (ProductoPrestacion iterador : todosProducPres) {
                if (iterador.getPrestacion().equals(new Prestacion(this.getIdPrestacionSelecionada()))) {
                    if (iterador.getProducto().equals(new Producto(this.getProducPrestacion()))) {
                        existe = true;
                        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Producto ya esta incluido en esta Prestació", "Producto ya esta incluido en esta Prestación");
                        FacesContext.getCurrentInstance().addMessage(null, message);
                    }
                }
            }

            if (existe == false) {
                //Setear productoPrestacionPk en productoPrestacion para guardar en tabla producto_prestacion en la base de datps
                productoPrestacion.setProductoPrestacionPK(productoPrestacionPK);
                productoPrestacion.setPrestacion(new Prestacion(this.getIdPrestacionSelecionada()));
                productoPrestacion.setProducto(new Producto(this.getProducPrestacion()));
                productoPrestacion.setCantidad(this.getCantidad());

                getProductoPrestacionFacade().create(productoPrestacion);

                //Edición de el costo de la prestación
                Prestacion pres = getPrestacionFacade().find(this.getIdPrestacionSelecionada());
                Producto prod = getProductoFacade().find(this.getProducPrestacion());

                //costo anterior y nuevo costo de prestación
                double costoPrestacion = pres.getCostoPrestacion();
                double costoUnit = prod.getCostoUnit();
                double costoPorUnidades = costoUnit * this.getCantidad();
                double costoTotalPrestacion = costoPrestacion + costoPorUnidades;

                //Edición de prestación con nuevo costo de prestación
                pres.setCostoPrestacion(costoTotalPrestacion);
                getPrestacionFacade().edit(pres);

                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Ingresado", "Registro Ingresado");
                FacesContext.getCurrentInstance().addMessage(null, message);
            }

            this.setProducPrestacion(0);
            this.setCantidad(0);

            RequestContext.getCurrentInstance().update("tablaProdPres");
            RequestContext.getCurrentInstance().execute("PF('addProductoPres').hide()");

            productoPrestacionPK = new ProductoPrestacionPK();
            productoPrestacion = new ProductoPrestacion();

        } catch (Exception e) {
        }
    }

    public void productoPrestacionSelecionada(ProductoPrestacion prodPres) {
        productoPrestacion = prodPres;
    }

    public void eliminarProdPrestacion() {
        try {
            //Obtiene prestacion y producto a eliminar
            Prestacion pres = getPrestacionFacade().find(productoPrestacion.getPrestacion().getIdPrestacion());
            Producto prod = getProductoFacade().find(productoPrestacion.getProducto().getIdProducto());

            //Valor anterior y nuevo costo de prestación
            double costoPrestacion = pres.getCostoPrestacion();
            double costoUnit = prod.getCostoUnit();
            double costoPorUnidades = costoUnit * productoPrestacion.getCantidad();
            double costoTotalPrestacion = costoPrestacion - costoPorUnidades;
            pres.setCostoPrestacion(costoTotalPrestacion);

            getPrestacionFacade().edit(pres);
            getProductoPrestacionFacade().remove(productoPrestacion);

            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Eliminado", "Registro Eliminado");
            FacesContext.getCurrentInstance().addMessage(null, message);

            productoPrestacion = new ProductoPrestacion();

            RequestContext.getCurrentInstance().update("tablaProdPres");
            RequestContext.getCurrentInstance().execute("PF('confirmationProd').hide()");

        } catch (Exception e) {
        }
    }

    public String buscarEmpleadosPrestacion() {
        return null;
    }

    public void addPresEmp() {
        try {
            //Obtener empleado para poder ingresar en la lista de prestaciones
            Empleados emp = getEmpleadosFacade().find(getEmpleadoSelecionado());

            boolean existe = false;
            List<Prestacion> prestacionList = emp.getPrestacionList();

            for (Prestacion iterador : prestacionList) {
                if (iterador.getIdPrestacion() == this.getIdPrestacionAsirnar()) {
                    existe = true;
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Prestación Ya fue asignada a este Empleado", "Prestación Ya fue asignada a este Empleado");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                }
            }

            if (existe == false) {
                prestacionList.add(new Prestacion(this.getIdPrestacionAsirnar()));
                emp.setPrestacionList(prestacionList);

                Prestacion pres = getPrestacionFacade().find(this.getIdPrestacionAsirnar());
                List<Empleados> empleadosList = pres.getEmpleadosList();
                empleadosList.add(emp);

                getPrestacionFacade().edit(pres);
                getEmpleadosFacade().edit(emp);
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Ingresado", "Registro Ingresado");
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
            this.setIdPrestacionAsirnar(0);
            RequestContext.getCurrentInstance().execute("PF('asigPrestaciones').hide()");
            tabla();
            // return null;
        } catch (Exception e) {
            this.setIdPrestacionAsirnar(0);
            //return null;
        }
    }

    public void addEmpPres() {
        try {
            //Obtiene empleado para sewr ingresado en la lista de prestaciones
            Empleados emp = getEmpleadosFacade().find(getEmpleadoSelecionado());

            boolean existe = false;
            List<Prestacion> prestacionList = emp.getPrestacionList();

            for (Prestacion iterador : prestacionList) {
                if (iterador.getIdPrestacion() == this.getIdPresGestion()) {
                    existe = true;
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Prestación ya fue asignada a este Empleado", "Prestación ya fue asignada a este Empleado");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                }
            }

            if (existe == false) {
                prestacionList.add(new Prestacion(this.getIdPresGestion()));
                emp.setPrestacionList(prestacionList);

                Prestacion pres = getPrestacionFacade().find(this.getIdPresGestion());
                List<Empleados> empleadosList = pres.getEmpleadosList();
                empleadosList.add(emp);

                getPrestacionFacade().edit(pres);
                getEmpleadosFacade().edit(emp);
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Ingresado", "Registro Ingresado");
                FacesContext.getCurrentInstance().addMessage(null, message);
                RequestContext.getCurrentInstance().execute("PF('nuevoEmp').hide()");
                RequestContext.getCurrentInstance().update("tabla");
                this.setEmpleadoSelecionado(0);
                this.setDireccionNacional(0);
                this.setDependecia(0);
            }
        } catch (Exception e) {

        }
    }

    public String eliminarPresEmp() {
        try {
            //Se elimina empleado de la lista Prestaciones List y se edita información de empleado
            Empleados emp = getEmpleadosFacade().find(getEmpleadoSelecionado());
            List<Prestacion> prestacionList = emp.getPrestacionList();
            prestacionList.remove(prestacion);
            emp.setPrestacionList(prestacionList);

            List<Empleados> empleadosList = prestacion.getEmpleadosList();
            empleadosList.remove(emp);

            getPrestacionFacade().edit(prestacion);
            getEmpleadosFacade().edit(emp);

            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Eliminado", "Registro Eliminado");
            FacesContext.getCurrentInstance().addMessage(null, message);
            prestacion = new Prestacion();
            return null;
        } catch (Exception e) {
            return null;
        }

    }

    public String cancelarEditar() {
        return null;
    }

    public void empleadoSeleccionado(Empleados emp) {
        empleado = emp;
    }

    public String eliminarEmpleadoPrestacion() {
        try {
            List<Prestacion> prestacionList = empleado.getPrestacionList();
            prestacionList.remove(new Prestacion(this.getIdPresGestion()));
            empleado.setPrestacionList(prestacionList);

            Prestacion pres = getPrestacionFacade().find(this.getIdPresGestion());
            List<Empleados> empleadosList = pres.getEmpleadosList();
            empleadosList.remove(empleado);

            getPrestacionFacade().edit(pres);
            getEmpleadosFacade().edit(empleado);

            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Eliminado", "Registro Eliminado");
            FacesContext.getCurrentInstance().addMessage(null, message);

            empleado = new Empleados();
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public void nuevoEmpPrestacion(ActionEvent event) {
        if (this.getIdPresGestion() == 0) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Seleccione una Prestación", "Seleccione una Prestación"));
        } else {
            this.setDireccionNacional(0);
            this.setDependecia(0);
            this.setEmpleadoSelecionado(0);
            RequestContext.getCurrentInstance().execute("PF('nuevoEmp').show()");
        }
    }

    public void empleadoSelecionadoValido(ActionEvent event) {
        if (this.getEmpleadoSelecionado() == 0) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debe buscar un Empleado", "Debe buscar un Empleado"));
        } else {
            RequestContext.getCurrentInstance().execute("PF('asigPrestaciones').show()");
        }
    }

    public void tabla() {
        RequestContext.getCurrentInstance().update("tabla");
    }

    public void buscarNR(ActionEvent event) {
        Empleados emp = getEmpleadosFacade().buscarEmpNR(this.getNR());
        if (emp == null) {
            this.setNombreEmp("");
        } else {
            this.setEmpleadoSelecionado(emp.getIdEmpleado());
            this.setNombreEmp(emp.getNombreEmpleado());
        }
    }

    public Date fechaAnio(int anio) { //Regresa una fecha 31 de diiembre de un año dado
        anio = anio - 1900;
        Date fecha = new Date(anio, 11, 31);
        return fecha;
    }

    public int obtenerAnio(Date date) { // Regresa un año de una fecha dada
        if (null == date) {
            return 0;
        } else {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
            return Integer.parseInt(dateFormat.format(date));
        }
    }

    public manejadorPrestaciones() {
    }

    // Mostrar Mensajes en la siguiente peticion!
    public static Flash flashScope() {
        return (FacesContext.getCurrentInstance().getExternalContext().getFlash());
    }

    // Obtine el path absoluto no importa de donde sea ejecutada la aplicación
    private String getAbsolutePath(String imageName) {
        final ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        final StringBuilder logo = new StringBuilder().append(servletContext.getRealPath(""));
        logo.append(File.separator).append(imageName);
        return logo.toString();
    }

    /*
     ******************************** REPORTES **********************************
     ******************************
     */
    public void generarReporte() throws net.sf.jasperreports.engine.JRException, FileNotFoundException, IOException, SQLException {
        if (this.getIdPresGestion() != 0) {
            // Hacemos una conexion a la base de datos (No encontre otra forma de hacerlo)
            Connection baseDatos = java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/bd_rrhh", "postgres", "root123");

            // Configuraciones en el log4j.properties para evitar un waring en netbeans (no necesario)
            String log4jConfPath = getAbsolutePath("reportes\\log4j.properties");
            PropertyConfigurator.configure(log4jConfPath);

            // Parametros Iniciales
            InputStream inputStream = null;
            String rutaJrxml = getAbsolutePath("reportes\\rep_emp_prestacion.jrxml");
            String path = getAbsolutePath("resources\\images\\");

            // Carga el archivo Jrxml
            inputStream = new FileInputStream(rutaJrxml);

            // Cargamos Parametros que se enviaran
            Map parametros = new HashMap();
            parametros.put("id_prestacion", this.getIdPresGestion());
            parametros.put("path", path);

            // Compila y llena el reporte con datos
            JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, baseDatos);

            //Para desgargar pdf
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            response.addHeader("Content-disposition", "attachment; filename=Reporte_Empeados_Prestacion.pdf");
            try (ServletOutputStream stream = response.getOutputStream()) {
                JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
                stream.flush();
            }
            FacesContext.getCurrentInstance().responseComplete();
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Seleccione una Prestación", "Seleccione una Prestación");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

}
