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
import sv.gob.cultura.rrhh.entities.CaracteristicasIdioma;
import sv.gob.cultura.rrhh.entities.Dependencias;
import sv.gob.cultura.rrhh.entities.DirNacional;
import sv.gob.cultura.rrhh.entities.Empleados;
import sv.gob.cultura.rrhh.entities.EstudiosEmp;
import sv.gob.cultura.rrhh.entities.Idiomas;
import sv.gob.cultura.rrhh.entities.IdiomasCaracteristicas;
import sv.gob.cultura.rrhh.entities.IdiomasCaracteristicasPK;
import sv.gob.cultura.rrhh.entities.ImgDoc;
import sv.gob.cultura.rrhh.entities.Nivel;
import sv.gob.cultura.rrhh.entities.ProfOficios;
import sv.gob.cultura.rrhh.facades.CaracteristicasIdiomaFacade;
import sv.gob.cultura.rrhh.facades.DependenciasFacade;
import sv.gob.cultura.rrhh.facades.DirNacionalFacade;
import sv.gob.cultura.rrhh.facades.EmpleadosFacade;
import sv.gob.cultura.rrhh.facades.EstudiosEmpFacade;
import sv.gob.cultura.rrhh.facades.IdiomasCaracteristicasFacade;
import sv.gob.cultura.rrhh.facades.IdiomasFacade;
import sv.gob.cultura.rrhh.facades.ImgDocFacade;
import sv.gob.cultura.rrhh.facades.NivelFacade;
import sv.gob.cultura.rrhh.facades.ProfOficiosFacade;

/**
 *
 * @author Edwin
 */
@Named(value = "managerStudies")
@ViewScoped
public class ManagerStudies implements Serializable {

    @EJB
    private ProfOficiosFacade profOficiosFacade;
    @EJB
    private CaracteristicasIdiomaFacade caracteristicasIdiomaFacade;
    @EJB
    private NivelFacade nivelFacade;
    @EJB
    private ImgDocFacade imgDocFacade;
    @EJB
    private IdiomasFacade idiomasFacade;
    @EJB
    private IdiomasCaracteristicasFacade idiomasCaracteristicasFacade;
    @EJB
    private EstudiosEmpFacade estudiosEmpFacade;
    @EJB
    private EmpleadosFacade empleadosFacade;
    @EJB
    private DependenciasFacade dependenciasFacade;
    @EJB
    private DirNacionalFacade dirNacionalFacade;

    private EstudiosEmp newEstudio = new EstudiosEmp(), selectedEstudio = new EstudiosEmp();
    private IdiomasCaracteristicas newLang = new IdiomasCaracteristicas(), selectedLang = new IdiomasCaracteristicas();
    private ImgDoc newAttachment = new ImgDoc();
    private Empleados selectedEmp = new Empleados();
    private int direccionNacional;
    private int dependecia;

    public ManagerStudies() {
    }

    public ImgDocFacade getImgDocFacade() {
        return imgDocFacade;
    }

    public void setImgDocFacade(ImgDocFacade imgDocFacade) {
        this.imgDocFacade = imgDocFacade;
    }

    public IdiomasFacade getIdiomasFacade() {
        return idiomasFacade;
    }

    public void setIdiomasFacade(IdiomasFacade idiomasFacade) {
        this.idiomasFacade = idiomasFacade;
    }

    public EstudiosEmpFacade getEstudiosEmpFacade() {
        return estudiosEmpFacade;
    }

    public void setEstudiosEmpFacade(EstudiosEmpFacade estudiosEmpFacade) {
        this.estudiosEmpFacade = estudiosEmpFacade;
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

    public IdiomasCaracteristicasFacade getIdiomasCaracteristicasFacade() {
        return idiomasCaracteristicasFacade;
    }

    public NivelFacade getNivelFacade() {
        return nivelFacade;
    }

    public CaracteristicasIdiomaFacade getCaracteristicasIdiomaFacade() {
        return caracteristicasIdiomaFacade;
    }

    public ProfOficiosFacade getProfOficiosFacade() {
        return profOficiosFacade;
    }

    public EstudiosEmp getNewEstudio() {
        return newEstudio;
    }

    public void setNewEstudio(EstudiosEmp newEstudio) {
        this.newEstudio = newEstudio;
    }

    public EstudiosEmp getSelectedEstudio() {
        return selectedEstudio;
    }

    public void setSelectedEstudio(EstudiosEmp selectedEstudio) {
        this.selectedEstudio = selectedEstudio;
    }


    public IdiomasCaracteristicas getNewLang() {
        return newLang;
    }

    public void setNewLang(IdiomasCaracteristicas newLang) {
        this.newLang = newLang;
    }

    public IdiomasCaracteristicas getSelectedLang() {
        return selectedLang;
    }

    public void setSelectedLang(IdiomasCaracteristicas selectedLang) {
        this.selectedLang = selectedLang;
    }

    public ImgDoc getNewAttachment() {
        return newAttachment;
    }

    public void setNewAttachment(ImgDoc newAttachment) {
        this.newAttachment = newAttachment;
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

    public Empleados getSelectedEmp() {
        return selectedEmp;
    }

    public void setSelectedEmp(Empleados selectedEmp) {
        this.selectedEmp = selectedEmp;
    }

    public List<DirNacional> allDirNacionales() {
        return getDirNacionalFacade().findAll();
    }

    public List<Dependencias> filteredDependencias() {
        return getDependenciasFacade().findByDirNac(direccionNacional);
    }

    public List<Empleados> filteredEmployees() {
        return getEmpleadosFacade().findByDependencia(dependecia);
    }

    public List<Nivel> allNiveles() {
        return getNivelFacade().findAll();
    }

    public List<Idiomas> allIdiomas() {
        return getIdiomasFacade().findAll();
    }

    public List<CaracteristicasIdioma> allCaracteristicasIdioma() {
        return getCaracteristicasIdiomaFacade().findAll();
    }

    public List<ProfOficios> fetchOficios() {
        return getProfOficiosFacade().findAll();
    }

    public List<IdiomasCaracteristicas> fetchIdiomaCaracts() {
        if (selectedEmp.getIdEmpleado() != null) {
            return getIdiomasCaracteristicasFacade().findAllByEmpId(selectedEmp.getIdEmpleado());
        } else {
            return null;
        }
    }

    public List<EstudiosEmp> fetchEstudios() {
        if (selectedEmp.getIdEmpleado() != null) {
            return getEstudiosEmpFacade().findByEmpId(selectedEmp.getIdEmpleado());
        } else {
            return null;
        }
    }

    public void saveStudy() {
        try {
            newEstudio.setFechaCreaEstudios(new Date());
            newEstudio.setIdEmpleado(selectedEmp);
            newEstudio.setUserCreaEstudios(1);
            getEstudiosEmpFacade().create(newEstudio);
            newEstudio = new EstudiosEmp();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Creado", "Registro Creado correctamente");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } catch (Exception e) {
        }
    }

    public void editStudy() {
        try {
            selectedEstudio.setFechaModEstudios(new Date());
            selectedEstudio.setUserModEstudios(1);
            getEstudiosEmpFacade().edit(selectedEstudio);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Modificado", "Registro Modificado correctamente");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } catch (Exception e) {

        }
    }

    public void removeStudy() {
        try {
            getEstudiosEmpFacade().remove(selectedEstudio);
            selectedEstudio = new EstudiosEmp();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Eliminado", "Registro Eliminado correctamente");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } catch (Exception e) {
        }
    }

    public void saveLanguage() {
//            Logger.getLogger(getClass().getName()).log(Level.INFO, "Objecto Idioma: {0}", newLang);
        int idCaracteristica = 0, idIdioma = 0;
        idCaracteristica = newLang.getCaracteristicasIdioma().getIdCaractIdioma();
        idIdioma = newLang.getIdiomas().getIdIdioma();
        try {
            newLang.setIdiomasCaracteristicasPK(new IdiomasCaracteristicasPK(idCaracteristica, idIdioma, selectedEmp.getIdEmpleado()));
            getIdiomasCaracteristicasFacade().create(newLang);
            newLang = new IdiomasCaracteristicas();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Creado", "Registro Creado correctamente");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } catch (Exception e) {
        }
    }

    public void editLanguage() {
//            Logger.getLogger(getClass().getName()).log(Level.INFO, "Objecto Idioma: {0}", newLang);
        int idCaracteristica = 0, idIdioma = 0;
        idCaracteristica = selectedLang.getCaracteristicasIdioma().getIdCaractIdioma();
        idIdioma = selectedLang.getIdiomas().getIdIdioma();
        try {
            selectedLang.setIdiomasCaracteristicasPK(new IdiomasCaracteristicasPK(idCaracteristica, idIdioma, selectedEmp.getIdEmpleado()));
            getIdiomasCaracteristicasFacade().edit(selectedLang);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Modificado", "Registro Modificado correctamente");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } catch (Exception e) {
        }
    }

    public void removeLanguage() {
        try {
            getIdiomasCaracteristicasFacade().remove(selectedLang);
            selectedLang = new IdiomasCaracteristicas();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Eliminado", "Registro Eliminado correctamente");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } catch (Exception e) {
        }
    }

}
