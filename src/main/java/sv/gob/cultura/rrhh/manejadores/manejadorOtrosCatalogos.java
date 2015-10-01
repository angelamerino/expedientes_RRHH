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
import javax.faces.context.Flash;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import sv.gob.cultura.rrhh.entidades.Dependencias;
import sv.gob.cultura.rrhh.entidades.Deptos;
import sv.gob.cultura.rrhh.entidades.DirNacional;
import sv.gob.cultura.rrhh.entidades.GradoSancion;
import sv.gob.cultura.rrhh.entidades.Municipios;
import sv.gob.cultura.rrhh.entidades.Sanciones;
import sv.gob.cultura.rrhh.entidades.TipoSancion;
import sv.gob.cultura.rrhh.entidades.UsuariosSistema;
import sv.gob.cultura.rrhh.facades.DependenciasFacade;
import sv.gob.cultura.rrhh.facades.DeptosFacade;
import sv.gob.cultura.rrhh.facades.DirNacionalFacade;
import sv.gob.cultura.rrhh.facades.GradoSancionFacade;
import sv.gob.cultura.rrhh.facades.MunicipiosFacade;
import sv.gob.cultura.rrhh.facades.TipoSancionFacade;

/**
 *
 * @author Edwin
 */
@Named(value = "manejadorOtrosCatalogos")
@ViewScoped
public class manejadorOtrosCatalogos implements Serializable {
// ************** LLAMADA A LOS ENTERPRICE JAVA BEANS **************************
//******************************************************************************

    @EJB
    private TipoSancionFacade tipoSancionFacade;
    @EJB
    private DependenciasFacade dependenciasFacade;
    @EJB
    private DirNacionalFacade dirNacionalFacade;
    @EJB
    private MunicipiosFacade municipiosFacade;
    @EJB
    private DeptosFacade deptosFacade;
    @EJB
    private GradoSancionFacade gradoSancionFacade;

//*********************** OBJETOS DE LOS ENTIDADES *****************************
//******************************************************************************
    TipoSancion tipoSancion = new TipoSancion();
    Dependencias dependencias = new Dependencias();
    DirNacional dirNacional = new DirNacional();
//****** VARIABLES QUE CONTRENDRAN ID´S O STRING DE FORMULARIOS ****************
//******************************************************************************
    private int municipio;
    private int departamento;
//********************** GET DE ENTERPRICE JAVA BEAN ***************************
//******************************************************************************

    public TipoSancionFacade getTipoSancionFacade() {
        return tipoSancionFacade;
    }

    public DependenciasFacade getDependenciasFacade() {
        return dependenciasFacade;
    }

    public DirNacionalFacade getDirNacionalFacade() {
        return dirNacionalFacade;
    }

    public MunicipiosFacade getMunicipiosFacade() {
        return municipiosFacade;
    }

    public DeptosFacade getDeptosFacade() {
        return deptosFacade;
    }

    public GradoSancionFacade getGradoSancionFacade() {
        return gradoSancionFacade;
    }

//******************* GET y SET DE OBJETOS DE ENTIDADES ************************
//******************************************************************************
    public TipoSancion getTipoSancion() {
        return tipoSancion;
    }

    public void setTipoSancion(TipoSancion tipoSancion) {
        this.tipoSancion = tipoSancion;
    }

    public Dependencias getDependencias() {
        return dependencias;
    }

    public void setDependencias(Dependencias dependencias) {
        this.dependencias = dependencias;
    }

    public DirNacional getDirNacional() {
        return dirNacional;
    }

    public void setDirNacional(DirNacional dirNacional) {
        this.dirNacional = dirNacional;
    }

    public int getMunicipio() {
        return municipio;
    }

    public void setMunicipio(int municipio) {
        this.municipio = municipio;
    }

    public int getDepartamento() {
        return departamento;
    }

    public void setDepartamento(int departamento) {
        this.departamento = departamento;
    }

//******************************* LISTADOS *************************************
//******************************************************************************
    public List<DirNacional> todasDirNacionales() {
        return getDirNacionalFacade().findAll();
    }

    public List<Dependencias> todasDependencias() {
        return getDependenciasFacade().findAll();
    }

    public List<TipoSancion> todosTipoSancion() {
        return getTipoSancionFacade().findAll();
    }

    public List<Deptos> todosDepatamentos() {
        return getDeptosFacade().findAll();
    }

    public List<GradoSancion> todosGradosSancion() {
        return getGradoSancionFacade().findAll();
    }

    public List<Municipios> municipiosFiltrados() {
        return getMunicipiosFacade().buscarDep(this.getDepartamento());
    }
//******************************* FUNCIONES ************************************
//******************************************************************************

    public String guardarDireccion() {
        try {
            dirNacional.setFechaCreaDir(new Date());
            dirNacional.setUserCreaDir(1);
            getDirNacionalFacade().create(dirNacional);
            dirNacional = new DirNacional();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Ingresado", "Registro Ingresado");
            FacesContext.getCurrentInstance().addMessage(null, message);
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().getFlash().setKeepMessages(true);
            flashScope().put("manejadorOtrosCatalogos", this);
            return "cat_direcciones";
        } catch (Exception e) {
            return "cat_direcciones";
        }
    }

    public String editarDireccion() {
        try {
            dirNacional.setFechaModDir(new Date());
            dirNacional.setUserModDir(1);
            getDirNacionalFacade().edit(dirNacional);
            dirNacional = new DirNacional();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Modificado", "Registro Modificado");
            FacesContext.getCurrentInstance().addMessage(null, message);
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().getFlash().setKeepMessages(true);
            flashScope().put("manejadorOtrosCatalogos", this);
            return "cat_direcciones";
        } catch (Exception e) {
            return "cat_direcciones";
        }
    }

    public String eliminarDireccion() {
        try {
            List<Dependencias> depen = dirNacional.getDependenciasList();

            if (depen.isEmpty()) {
                getDirNacionalFacade().remove(dirNacional);
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Eliminado", "Registro Eliminado");
                FacesContext.getCurrentInstance().addMessage(null, message);
                FacesContext context = FacesContext.getCurrentInstance();
                context.getExternalContext().getFlash().setKeepMessages(true);
                flashScope().put("manejadorOtrosCatalogos", this);
            } else {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Registro NO Eliminado, existen Dependencias", "Registro NO Eliminado, existen Dependencias");
                FacesContext.getCurrentInstance().addMessage(null, message);
                FacesContext context = FacesContext.getCurrentInstance();
                context.getExternalContext().getFlash().setKeepMessages(true);
                flashScope().put("manejadorOtrosCatalogos", this);
            }
            dirNacional = new DirNacional();
            return "cat_direcciones";
        } catch (Exception e) {
            return "cat_direcciones";
        }
    }

    public String cancelarDireccion() {
        return "cat_direcciones";
    }

    public String guardarDependencia() {
        try {
            dependencias.setFechaCreaDependencia(new Date());
            dependencias.setUserCreaDependencia(1);
            getDependenciasFacade().create(dependencias);
            dependencias = new Dependencias();
            this.setDepartamento(0);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Ingresado", "Registro Ingresado");
            FacesContext.getCurrentInstance().addMessage(null, message);
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().getFlash().setKeepMessages(true);
            flashScope().put("manejadorOtrosCatalogos", this);
            return "cat_dependencias";
        } catch (Exception e) {
            return "cat_dependencias";
        }
    }

    public String editarDependencia() {
        try {
            dependencias.setFechaModDependencia(new Date());
            dependencias.setUserModDependencia(1);
            getDependenciasFacade().edit(dependencias);
            dependencias = new Dependencias();
            this.setDepartamento(0);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Modificado", "Registro Modificado");
            FacesContext.getCurrentInstance().addMessage(null, message);
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().getFlash().setKeepMessages(true);
            flashScope().put("manejadorOtrosCatalogos", this);
            return "cat_dependencias";
        } catch (Exception e) {
            return "cat_dependencias";
        }
    }

    public String eliminarDependencia() {
        try {
            List<UsuariosSistema> user = dependencias.getUsuariosSistemaList();

            if (user.isEmpty()) {
                getDependenciasFacade().remove(dependencias);
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Eliminado", "Registro Eliminado");
                FacesContext.getCurrentInstance().addMessage(null, message);
                FacesContext context = FacesContext.getCurrentInstance();
                context.getExternalContext().getFlash().setKeepMessages(true);
                flashScope().put("manejadorOtrosCatalogos", this);
            } else {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Registro NO Eliminado, existen Usuarios", "Registro NO Eliminado, existen Usuarios");
                FacesContext.getCurrentInstance().addMessage(null, message);
                FacesContext context = FacesContext.getCurrentInstance();
                context.getExternalContext().getFlash().setKeepMessages(true);
                flashScope().put("manejadorOtrosCatalogos", this);
            }
            dependencias = new Dependencias();
            return "cat_dependencias";
        } catch (Exception e) {
            return "cat_dependencias";
        }
    }

    public String cancelarDependencia() {
        return "cat_dependencias";
    }

    public String guardarTipoSancion() {
        try {
            getTipoSancionFacade().create(tipoSancion);
            tipoSancion = new TipoSancion();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Ingresado", "Registro Ingresado");
            FacesContext.getCurrentInstance().addMessage(null, message);
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().getFlash().setKeepMessages(true);
            flashScope().put("manejadorOtrosCatalogos", this);
            return "cat_sanciones";
        } catch (Exception e) {
            return "cat_sanciones";
        }
    }

    public String editarTipoSancion() {
        try {
            getTipoSancionFacade().edit(tipoSancion);
            tipoSancion = new TipoSancion();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Ingresado", "Registro Ingresado");
            FacesContext.getCurrentInstance().addMessage(null, message);
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().getFlash().setKeepMessages(true);
            flashScope().put("manejadorOtrosCatalogos", this);
            return "cat_sanciones";
        } catch (Exception e) {
            return "cat_sanciones";
        }
    }

    public String eliminarTipoSancion() {
        try {
            List<Sanciones> sanciones = tipoSancion.getSancionesList();

            if (sanciones.isEmpty()) {
                getTipoSancionFacade().remove(tipoSancion);
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Eliminado", "Registro Eliminado");
                FacesContext.getCurrentInstance().addMessage(null, message);
                FacesContext context = FacesContext.getCurrentInstance();
                context.getExternalContext().getFlash().setKeepMessages(true);
                flashScope().put("manejadorOtrosCatalogos", this);
            } else {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Registro NO Eliminado, Existen Amonestaciones de este tipo", "Registro NO Eliminado, Existen Amonestaciones de este tipo");
                FacesContext.getCurrentInstance().addMessage(null, message);
                FacesContext context = FacesContext.getCurrentInstance();
                context.getExternalContext().getFlash().setKeepMessages(true);
                flashScope().put("manejadorOtrosCatalogos", this);
            }
            tipoSancion = new TipoSancion();
            return "cat_sanciones";
        } catch (Exception e) {
            return "cat_sanciones";
        }
    }

    public String cancelarTipoSancion() {
        return "cat_sanciones";
    }

    public void direccionSeleccionada(DirNacional dir) {
        dirNacional = dir;
    }

    public void dependenciaSeleccionada(Dependencias dep) {
        dependencias = dep;
    }

    public void tipoSancionSeleccionada(TipoSancion tipo) {
        tipoSancion = tipo;
    }

    public manejadorOtrosCatalogos() {
    }

    //Mostrar Mensajes en la siguiente peticion!
    public static Flash flashScope() {
        return (FacesContext.getCurrentInstance().getExternalContext().getFlash());
    }

}
