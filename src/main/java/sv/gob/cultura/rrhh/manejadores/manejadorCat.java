/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.cultura.rrhh.manejadores;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import sv.gob.cultura.rrhh.entidades.AdministradoraPensiones;
import sv.gob.cultura.rrhh.entidades.CaracteristicasIdioma;
import sv.gob.cultura.rrhh.entidades.EstadoCivil;
import sv.gob.cultura.rrhh.entidades.Estados;
import sv.gob.cultura.rrhh.entidades.GradoSancion;
import sv.gob.cultura.rrhh.entidades.Idiomas;
import sv.gob.cultura.rrhh.entidades.InstBancaria;
import sv.gob.cultura.rrhh.entidades.Nivel;
import sv.gob.cultura.rrhh.entidades.Paises;
import sv.gob.cultura.rrhh.entidades.Parentesco;
import sv.gob.cultura.rrhh.entidades.ProfOficios;
import sv.gob.cultura.rrhh.entidades.Proveedor;
import sv.gob.cultura.rrhh.entidades.TipoDescuento;
import sv.gob.cultura.rrhh.entidades.TipoMejoraSalarial;
import sv.gob.cultura.rrhh.entidades.TipoMov;
import sv.gob.cultura.rrhh.entidades.TipoPrestacion;
import sv.gob.cultura.rrhh.entidades.TipoSangre;
import sv.gob.cultura.rrhh.facades.AdministradoraPensionesFacade;
import sv.gob.cultura.rrhh.facades.CaracteristicasIdiomaFacade;
import sv.gob.cultura.rrhh.facades.EstadoCivilFacade;
import sv.gob.cultura.rrhh.facades.EstadosFacade;
import sv.gob.cultura.rrhh.facades.GradoSancionFacade;
import sv.gob.cultura.rrhh.facades.IdiomasFacade;
import sv.gob.cultura.rrhh.facades.InstBancariaFacade;
import sv.gob.cultura.rrhh.facades.NivelFacade;
import sv.gob.cultura.rrhh.facades.PaisesFacade;
import sv.gob.cultura.rrhh.facades.ParentescoFacade;
import sv.gob.cultura.rrhh.facades.ProfOficiosFacade;
import sv.gob.cultura.rrhh.facades.ProveedorFacade;
import sv.gob.cultura.rrhh.facades.TipoDescuentoFacade;
import sv.gob.cultura.rrhh.facades.TipoMejoraSalarialFacade;
import sv.gob.cultura.rrhh.facades.TipoMovFacade;
import sv.gob.cultura.rrhh.facades.TipoPrestacionFacade;
import sv.gob.cultura.rrhh.facades.TipoSangreFacade;
/*agregados por mi*/
//import javax.faces.context.FacesContext;
//import org.primefaces.event.SelectEvent;
//import javax.faces.application.FacesMessage;
//import org.primefaces.event.RowEditEvent;

/**
 *
 * @author Angela
 */
@Named(value = "manejadorCat")
@ViewScoped
public class manejadorCat implements Serializable {
    private static final long serialVersionUID = 1L;

    /* 1. INSERTO CODIGO PARA CADA FACADE CON "Call Enterprice Beans"  */
    @EJB
    private GradoSancionFacade gradoSancionFacade;
    @EJB
    private TipoSangreFacade tipoSangreFacade;
    @EJB
    private TipoPrestacionFacade tipoPrestacionFacade;
    @EJB
    private TipoMovFacade tipoMovFacade;
    @EJB
    private TipoMejoraSalarialFacade tipoMejoraSalarialFacade;
    @EJB
    private TipoDescuentoFacade tipoDescuentoFacade;
    @EJB
    private ProveedorFacade proveedorFacade;
    @EJB
    private ProfOficiosFacade profOficiosFacade;
    @EJB
    private PaisesFacade paisesFacade;
    @EJB
    private ParentescoFacade parentescoFacade;
    @EJB
    private NivelFacade nivelFacade;
    @EJB
    private IdiomasFacade idiomasFacade;
    @EJB
    private InstBancariaFacade instBancariaFacade;
    @EJB
    private EstadosFacade estadosFacade;
    @EJB
    private CaracteristicasIdiomaFacade caracteristicasIdiomaFacade;
    @EJB
    private EstadoCivilFacade estadoCivilFacade;
    @EJB
    private AdministradoraPensionesFacade administradoraPensionesFacade;

    /* 3. Declaro la variable opcion y nuevo item para seleccionar del listado y agregar nuevo registro a los catálogos*/
    private int opcion;
    private String nuevo_item;
    
    /* Declaro la variables para seleccionar el id del registro seleccionado*/
    private AdministradoraPensiones pensionSeleccionada = new AdministradoraPensiones();
    private CaracteristicasIdioma caractSeleccionada = new CaracteristicasIdioma();
    private EstadoCivil estcivilSeleccionado = new EstadoCivil();
    private Estados estadoSeleccionado = new Estados();
    private GradoSancion gradosancionSeleccionado = new GradoSancion();
    private Idiomas idiomaSeleccionado = new Idiomas();
    private InstBancaria bancoSeleccionado = new InstBancaria();
    private Nivel nivelSeleccionado = new Nivel();
    private Paises paisSeleccionado = new Paises();
    private Parentesco parentescoSeleccionado =  new Parentesco();
    private ProfOficios profSeleccionado = new ProfOficios();
    private Proveedor proveedorSeleccionado = new Proveedor();
    private TipoDescuento tipodesSeleccionado = new TipoDescuento();
    private TipoMejoraSalarial tipomejoraSeleccionado = new TipoMejoraSalarial();
    private TipoMov tipomovSeleccionado = new TipoMov();
    private TipoPrestacion tipoprestacionSeleccionada = new TipoPrestacion();
    private TipoSangre tiposangreSeleccionado = new TipoSangre();
    

/* Getter y Setter las variables anteriores: pensionSeleccionada, etc.*/

    public AdministradoraPensiones getPensionSeleccionada() {
        return pensionSeleccionada;
    }

    public void setPensionSeleccionada(AdministradoraPensiones pensionSeleccionada) {
        this.pensionSeleccionada = pensionSeleccionada;
    }

    public CaracteristicasIdioma getCaractSeleccionada() {
        return caractSeleccionada;
    }

    public void setCaractSeleccionada(CaracteristicasIdioma caractSeleccionada) {
        this.caractSeleccionada = caractSeleccionada;
    }

    public EstadoCivil getEstcivilSeleccionado() {
        return estcivilSeleccionado;
    }

    public void setEstcivilSeleccionado(EstadoCivil estcivilSeleccionado) {
        this.estcivilSeleccionado = estcivilSeleccionado;
    }

    public Estados getEstadoSeleccionado() {
        return estadoSeleccionado;
    }

    public void setEstadoSeleccionado(Estados estadoSeleccionado) {
        this.estadoSeleccionado = estadoSeleccionado;
    }

    public GradoSancion getGradosancionSeleccionado() {
        return gradosancionSeleccionado;
    }

    public void setGradosancionSeleccionado(GradoSancion gradosancionSeleccionado) {
        this.gradosancionSeleccionado = gradosancionSeleccionado;
    }

    public Idiomas getIdiomaSeleccionado() {
        return idiomaSeleccionado;
    }

    public void setIdiomaSeleccionado(Idiomas idiomaSeleccionado) {
        this.idiomaSeleccionado = idiomaSeleccionado;
    }

    public InstBancaria getBancoSeleccionado() {
        return bancoSeleccionado;
    }

    public void setBancoSeleccionado(InstBancaria bancoSeleccionado) {
        this.bancoSeleccionado = bancoSeleccionado;
    }

    public Nivel getNivelSeleccionado() {
        return nivelSeleccionado;
    }

    public void setNivelSeleccionado(Nivel nivelSeleccionado) {
        this.nivelSeleccionado = nivelSeleccionado;
    }

    public Paises getPaisSeleccionado() {
        return paisSeleccionado;
    }

    public void setPaisSeleccionado(Paises paisSeleccionado) {
        this.paisSeleccionado = paisSeleccionado;
    }

    public Parentesco getParentescoSeleccionado() {
        return parentescoSeleccionado;
    }

    public void setParentescoSeleccionado(Parentesco parentescoSeleccionado) {
        this.parentescoSeleccionado = parentescoSeleccionado;
    }

    public ProfOficios getProfSeleccionado() {
        return profSeleccionado;
    }

    public void setProfSeleccionado(ProfOficios profSeleccionado) {
        this.profSeleccionado = profSeleccionado;
    }

    public Proveedor getProveedorSeleccionado() {
        return proveedorSeleccionado;
    }

    public void setProveedorSeleccionado(Proveedor proveedorSeleccionado) {
        this.proveedorSeleccionado = proveedorSeleccionado;
    }

    public TipoDescuento getTipodesSeleccionado() {
        return tipodesSeleccionado;
    }

    public void setTipodesSeleccionado(TipoDescuento tipodesSeleccionado) {
        this.tipodesSeleccionado = tipodesSeleccionado;
    }

    public TipoMejoraSalarial getTipomejoraSeleccionado() {
        return tipomejoraSeleccionado;
    }

    public void setTipomejoraSeleccionado(TipoMejoraSalarial tipomejoraSeleccionado) {
        this.tipomejoraSeleccionado = tipomejoraSeleccionado;
    }

    public TipoMov getTipomovSeleccionado() {
        return tipomovSeleccionado;
    }

    public void setTipomovSeleccionado(TipoMov tipomovSeleccionado) {
        this.tipomovSeleccionado = tipomovSeleccionado;
    }

    public TipoPrestacion getTipoprestacionSeleccionada() {
        return tipoprestacionSeleccionada;
    }

    public void setTipoprestacionSeleccionada(TipoPrestacion tipoprestacionSeleccionada) {
        this.tipoprestacionSeleccionada = tipoprestacionSeleccionada;
    }

    public TipoSangre getTiposangreSeleccionado() {
        return tiposangreSeleccionado;
    }

    public void setTiposangreSeleccionado(TipoSangre tiposangreSeleccionado) {
        this.tiposangreSeleccionado = tiposangreSeleccionado;
    }

    
    
    private AdministradoraPensiones actualizarPensiones = new AdministradoraPensiones();

    public AdministradoraPensiones getActualizarPensiones() {
        return actualizarPensiones;
    }

    public void setActualizarPensiones(AdministradoraPensiones actualizarPensiones) {
        this.actualizarPensiones = actualizarPensiones;
    }
            
    
    /* Declaro la variable txtnombre y administradoraPensionesmod con sus setter y getter para actualizar registros y variable idseleccionado el 09/jun/2015*/
    private String txtnombre;

    public String getTxtnombre() {
        return txtnombre;
    }

    public void setTxtnombre(String txtnombre) {
        this.txtnombre = txtnombre;
    }

  

       
        //private int idseleccionado;
    /****************************************/
    
       /**
     * Creates a new instance of manejadorCat-- 2. Inserto "getter" de los
     * facades. NOTA: los facades no llevan setter
     */
    public manejadorCat() {
    }

    public TipoSangreFacade getTipoSangreFacade() {
        return tipoSangreFacade;
    }

    public GradoSancionFacade getGradoSancionFacade() {
        return gradoSancionFacade;
    }

    public TipoPrestacionFacade getTipoPrestacionFacade() {
        return tipoPrestacionFacade;
    }

    public TipoMovFacade getTipoMovFacade() {
        return tipoMovFacade;
    }

    public TipoMejoraSalarialFacade getTipoMejoraSalarialFacade() {
        return tipoMejoraSalarialFacade;
    }

    public TipoDescuentoFacade getTipoDescuentoFacade() {
        return tipoDescuentoFacade;
    }

    public ProveedorFacade getProveedorFacade() {
        return proveedorFacade;
    }

    public ProfOficiosFacade getProfOficiosFacade() {
        return profOficiosFacade;
    }

    public PaisesFacade getPaisesFacade() {
        return paisesFacade;
    }

    public ParentescoFacade getParentescoFacade() {
        return parentescoFacade;
    }

    public NivelFacade getNivelFacade() {
        return nivelFacade;
    }

    public IdiomasFacade getIdiomasFacade() {
        return idiomasFacade;
    }

    public InstBancariaFacade getInstBancariaFacade() {
        return instBancariaFacade;
    }

    public EstadosFacade getEstadosFacade() {
        return estadosFacade;
    }

    public CaracteristicasIdiomaFacade getCaracteristicasIdiomaFacade() {
        return caracteristicasIdiomaFacade;
    }

    public EstadoCivilFacade getEstadoCivilFacade() {
        return estadoCivilFacade;
    }

    public AdministradoraPensionesFacade getAdministradoraPensionesFacade() {
        return administradoraPensionesFacade;
    }

    /*4. Inserto código para get y set de las variables opcion y nuevo_item*/
    public int getOpcion() {
        return opcion;
    }

    public String getNuevo_item() {
        return nuevo_item;
    }

    public void setOpcion(int opcion) {
        this.opcion = opcion;
    }

    public void setNuevo_item(String nuevo_item) {
        this.nuevo_item = nuevo_item;
    }
    
    

    /* 5. Se crean métodos para visualizar en tablas todos los catálogos*/
    
    public List<AdministradoraPensiones> getAdministradoraPensiones() {
        return getAdministradoraPensionesFacade().findAll();
       
    }
    
    
   
    public List<CaracteristicasIdioma> getCaracteristicasIdioma() {
        return getCaracteristicasIdiomaFacade().findAll();
    }

    public List<EstadoCivil> getEstadoCivil() {
        return getEstadoCivilFacade().findAll();
    }

    public List<Estados> getEstados() {
        return getEstadosFacade().findAll();
    }

    public List<Idiomas> getIdiomas() {
        return getIdiomasFacade().findAll();
    }

    public List<InstBancaria> getInstBancaria() {
        return getInstBancariaFacade().findAll();
    }

    public List<Nivel> getNivel() {
        return getNivelFacade().findAll();
    }

    public List<Paises> getPaiseses() {
        return getPaisesFacade().findAll();
    }

    public List<Parentesco> getParentesco() {

        return getParentescoFacade().findAll();
    }
    public List<ProfOficios> getProfOficioses() {
        return getProfOficiosFacade().findAll();
    }

    public List<Proveedor> getProveedors() {
        return getProveedorFacade().findAll();
    }

    public List<TipoDescuento> getTipoDescuentos() {
        return getTipoDescuentoFacade().findAll();
    }

    public List<TipoMejoraSalarial> getTipoMejoraSalarials() {
        return getTipoMejoraSalarialFacade().findAll();
    }

    public List<TipoMov> getTipoMov() {
        return getTipoMovFacade().findAll();
    }

    public List<TipoPrestacion> getTipoPrestacions() {
        return getTipoPrestacionFacade().findAll();
    }

    public List<GradoSancion> getGradoSancions() {
        return getGradoSancionFacade().findAll();
    }

    public List<TipoSangre> getTipoSangres() {
        return getTipoSangreFacade().findAll();
    }

    /* 6. Inserto código para los métodos de agregar nuevo item*/
    public void guardarNuevo() {
        System.out.println("entrando guardar");
        switch (opcion) {
            case 1:
                System.out.println("probando primera opcion guardar");
                
                getAdministradoraPensionesFacade().create(new AdministradoraPensiones(nuevo_item));
                nuevo_item = "";
                break;
            case 2:
                getCaracteristicasIdiomaFacade().create(new CaracteristicasIdioma(nuevo_item));
                nuevo_item = "";
                break;
            case 3:
                getEstadoCivilFacade().create(new EstadoCivil(nuevo_item));
                nuevo_item = "";
                break;
            case 4:
                getEstadosFacade().create(new Estados(nuevo_item));
                nuevo_item = "";
                break;
            case 5:
                getGradoSancionFacade().create(new GradoSancion(nuevo_item));
                nuevo_item = "";
                break;
            case 6:
                getIdiomasFacade().create(new Idiomas(nuevo_item));
                nuevo_item = "";
                break;
            case 7:
                getInstBancariaFacade().create(new InstBancaria(nuevo_item));
                nuevo_item = "";
                break;
            case 8:
                getNivelFacade().create(new Nivel(nuevo_item));
                nuevo_item = "";
                break;
            case 9:
                getPaisesFacade().create(new Paises(nuevo_item));
                nuevo_item = "";
                break;
            case 10:
                getParentescoFacade().create(new Parentesco(nuevo_item));
                nuevo_item = "";
                break;
            case 11:
                getProfOficiosFacade().create(new ProfOficios(nuevo_item));
                nuevo_item = "";
                break;
            case 12:
                getProveedorFacade().create(new Proveedor(nuevo_item));
                nuevo_item = "";
                break;
            case 13:
                getTipoDescuentoFacade().create(new TipoDescuento(nuevo_item));
                nuevo_item = "";
                break;
            case 14:
                getTipoMejoraSalarialFacade().create(new TipoMejoraSalarial(nuevo_item));
                nuevo_item = "";
                break;
            case 15:
                getTipoMovFacade().create(new TipoMov(nuevo_item));
                nuevo_item = "";
                break;
            case 16:
                getTipoPrestacionFacade().create(new TipoPrestacion(nuevo_item));
                nuevo_item = "";
                break;
            case 17:
                getTipoSangreFacade().create(new TipoSangre(nuevo_item));
                nuevo_item = "";
                break;

            default:
                throw new AssertionError();
        }
    }

    public void actualizar(){
        switch (opcion) {
            case 1:
                getAdministradoraPensionesFacade().edit(pensionSeleccionada);
                break;
            case 2:
                getCaracteristicasIdiomaFacade().edit(caractSeleccionada);
                break;
            case 3:
                getEstadoCivilFacade().edit(estcivilSeleccionado);
                 break;
            case 4:
                getEstadosFacade().edit(estadoSeleccionado);
                 break;
            case 5:
                getGradoSancionFacade().edit(gradosancionSeleccionado);
                break;
            case 6:
                getIdiomasFacade().edit(idiomaSeleccionado);
                 break;
            case 7:
                getInstBancariaFacade().edit(bancoSeleccionado);
                 break;
            case 8:
                getNivelFacade().edit(nivelSeleccionado);
                 break;
            case 9:
                getPaisesFacade().edit(paisSeleccionado);
                 break;
            case 10:
                getParentescoFacade().edit(parentescoSeleccionado);
                 break;
            case 11:
                getProfOficiosFacade().edit(profSeleccionado);
                 break;
            case 12:
                getProveedorFacade().edit(proveedorSeleccionado);
                 break;
            case 13:
                getTipoDescuentoFacade().edit(tipodesSeleccionado);
                 break;
            case 14:
                getTipoMejoraSalarialFacade().edit(tipomejoraSeleccionado);
                 break;
            case 15:
                getTipoMovFacade().edit(tipomovSeleccionado);
                 break;
            case 16:
                getTipoPrestacionFacade().edit(tipoprestacionSeleccionada);
                break;
            case 17:
                getTipoSangreFacade().edit(tiposangreSeleccionado);
                 break;

            default:
                throw new AssertionError();
        }
    }
    
    public void eliminar(){
        System.out.println("entrando eliminar");
        switch (opcion) {
            case 1:
                System.out.println(opcion);
                getAdministradoraPensionesFacade().remove(pensionSeleccionada);
                break;
            case 2:
                System.out.println(opcion);
                System.out.println(caractSeleccionada);
                getCaracteristicasIdiomaFacade().remove(caractSeleccionada);
                break;
            case 3:
                getEstadoCivilFacade().remove(estcivilSeleccionado);
                 break;
            case 4:
                getEstadosFacade().remove(estadoSeleccionado);
                 break;
            case 5:
                getGradoSancionFacade().remove(gradosancionSeleccionado);
                break;
            case 6:
                getIdiomasFacade().remove(idiomaSeleccionado);
                 break;
            case 7:
                getInstBancariaFacade().remove(bancoSeleccionado);
                 break;
            case 8:
                getNivelFacade().remove(nivelSeleccionado);
                 break;
            case 9:
                getPaisesFacade().remove(paisSeleccionado);
                 break;
            case 10:
                getParentescoFacade().remove(parentescoSeleccionado);
                 break;
            case 11:
                getProfOficiosFacade().remove(profSeleccionado);
                 break;
            case 12:
                getProveedorFacade().remove(proveedorSeleccionado);
                 break;
            case 13:
                getTipoDescuentoFacade().remove(tipodesSeleccionado);
                 break;
            case 14:
                getTipoMejoraSalarialFacade().remove(tipomejoraSeleccionado);
                 break;
            case 15:
                getTipoMovFacade().remove(tipomovSeleccionado);
                 break;
            case 16:
                getTipoPrestacionFacade().remove(tipoprestacionSeleccionada);
                break;
            case 17:
                getTipoSangreFacade().remove(tiposangreSeleccionado);
                 break;

            default:
                throw new AssertionError();
        }
    }
    
//    public void onRowSelect(SelectEvent event) {
//        FacesMessage msg = new FacesMessage("Registro Seleccionado", ((AdministradoraPensiones) event.getObject()).getNombreAdminPension());
//        FacesContext.getCurrentInstance().addMessage(null, msg);
//    }
//    
//    public void onRowEdit(RowEditEvent event) {
//        FacesMessage msg = new FacesMessage("Registro Editado", ((AdministradoraPensiones) event.getObject()).getNombreAdminPension());
//        FacesContext.getCurrentInstance().addMessage(null, msg);
//    }
 
    
    
    /*PARA ELIMINAR REGISTROS*/    
public void eliminarAdminPensiones(){
    this.administradoraPensionesFacade.remove(pensionSeleccionada);
   }


/* ------------------  PARA ACTUALIZAR REGISTROS ----- */
public void actualizarPension(){
    getAdministradoraPensionesFacade().edit(pensionSeleccionada);
}

}


