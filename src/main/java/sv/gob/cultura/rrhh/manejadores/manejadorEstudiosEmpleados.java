/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.cultura.rrhh.manejadores;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import sv.gob.cultura.rrhh.entidades.Anio;
import sv.gob.cultura.rrhh.entidades.CaracteristicasIdioma;
import sv.gob.cultura.rrhh.entidades.Dependencias;
import sv.gob.cultura.rrhh.entidades.DirNacional;
import sv.gob.cultura.rrhh.entidades.Empleados;
import sv.gob.cultura.rrhh.entidades.EstudiosEmp;
import sv.gob.cultura.rrhh.entidades.Idiomas;
import sv.gob.cultura.rrhh.entidades.IdiomasCaracteristicas;
import sv.gob.cultura.rrhh.entidades.IdiomasCaracteristicasPK;
import sv.gob.cultura.rrhh.entidades.ImgDoc;
import sv.gob.cultura.rrhh.entidades.Nivel;
import sv.gob.cultura.rrhh.entidades.ProfOficios;
import sv.gob.cultura.rrhh.facades.AnioFacade;
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
@Named(value = "manejadorEstudiosEmpleados")
@ViewScoped
public class manejadorEstudiosEmpleados implements Serializable {

    public manejadorEstudiosEmpleados() {
    }
// ************** LLAMADA A LOS ENTERPRICE JAVA BEANS **************************
    @EJB
    private ImgDocFacade imgDocFacade;
    @EJB
    private IdiomasFacade idiomasFacade;
    @EJB
    private IdiomasCaracteristicasFacade idiomasCaracteristicasFacade;
    @EJB
    private EstudiosEmpFacade estudiosEmpFacade;
    @EJB
    private CaracteristicasIdiomaFacade caracteristicasIdiomaFacade;
    @EJB
    private NivelFacade nivelFacade;
    @EJB
    private EmpleadosFacade empleadosFacade;
    @EJB
    private ProfOficiosFacade profOficiosFacade;
    @EJB
    private AnioFacade anioFacade;
    @EJB
    private DependenciasFacade dependenciasFacade;
    @EJB
    private DirNacionalFacade dirNacionalFacade;

//*********************** OBJETOS DE LOS ENTIDADES *****************************
    private EstudiosEmp estudiosEmp = new EstudiosEmp();
    private ImgDoc imagenDocumento = new ImgDoc();
    private ProfOficios profOficos = new ProfOficios();
    private Idiomas idiomas = new Idiomas();
    private CaracteristicasIdioma caracteristicasIdioma = new CaracteristicasIdioma();
    private Nivel nivel = new Nivel();
    private IdiomasCaracteristicas idiomasCaracteristicas = new IdiomasCaracteristicas();
    private IdiomasCaracteristicasPK idiomasCaracteristicasPK = new IdiomasCaracteristicasPK();
    private Dependencias dependencias = new Dependencias();
    private DirNacional DirNacional = new DirNacional();

//****** VARIABLES QUE CONTRENDRAN IDÂ´S O STRING DE FORMULARIOS ****************
    private int anio;                               // AÃ±o de Estudios
    private final String[] path = new String[10];                                     // contiene url de documentos
    private final String[] nombreImgDoc = new String[10];
    private final String[] tipoImgDoc = new String[10];
    private final int[] sizeImgDoc = new int[10];
    private int idiomaSelecionado;
    private String instIdioma;
    private String idiomaSelecionadoNombre;
    private int idCaracteristicaIdioma;             // Id Caracteristica de idioma
    private int direccionNacional;
    private int dependecia;
    private int empleadoSelecionado;
    private String nombreEmp;
    private ArrayList<IdiomasCaracteristicas> carac = new ArrayList<IdiomasCaracteristicas>();
//********************** GET DE ENTERPRICE JAVA BEAN ***************************

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

    public IdiomasCaracteristicasFacade getIdiomasCaracteristicasFacade() {
        return idiomasCaracteristicasFacade;
    }

    public void setIdiomasCaracteristicasFacade(IdiomasCaracteristicasFacade idiomasCaracteristicasFacade) {
        this.idiomasCaracteristicasFacade = idiomasCaracteristicasFacade;
    }

    public EstudiosEmpFacade getEstudiosEmpFacade() {
        return estudiosEmpFacade;
    }

    public void setEstudiosEmpFacade(EstudiosEmpFacade estudiosEmpFacade) {
        this.estudiosEmpFacade = estudiosEmpFacade;
    }

    public CaracteristicasIdiomaFacade getCaracteristicasIdiomaFacade() {
        return caracteristicasIdiomaFacade;
    }

    public void setCaracteristicasIdiomaFacade(CaracteristicasIdiomaFacade caracteristicasIdiomaFacade) {
        this.caracteristicasIdiomaFacade = caracteristicasIdiomaFacade;
    }

    public AnioFacade getAnioFacade() {
        return anioFacade;
    }

    public ProfOficiosFacade getProfOficiosFacade() {
        return profOficiosFacade;
    }

    public EmpleadosFacade getEmpleadosFacade() {
        return empleadosFacade;
    }

    public NivelFacade getNivelFacade() {
        return nivelFacade;
    }

    public DependenciasFacade getDependenciasFacade() {
        return dependenciasFacade;
    }

    public DirNacionalFacade getDirNacionalFacade() {
        return dirNacionalFacade;
    }

//******************* GET y SET DE OBJETOS DE ENTIDADES ************************
    public void setEstudiosEmp(EstudiosEmp estudiosEmp) {
        this.estudiosEmp = estudiosEmp;
    }

    public EstudiosEmp getEstudiosEmp() {
        return estudiosEmp;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public ImgDoc getImagenDocumento() {
        return imagenDocumento;
    }

    public void setImagenDocumento(ImgDoc imagenDocumento) {
        this.imagenDocumento = imagenDocumento;
    }

    public ProfOficios getProfOficos() {
        return profOficos;
    }

    public void setProfOficos(ProfOficios profOficos) {
        this.profOficos = profOficos;
    }

    public Idiomas getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(Idiomas idiomas) {
        this.idiomas = idiomas;
        System.out.println(this.idiomas.getNombreIdioma());
    }

    public CaracteristicasIdioma getCaracteristicasIdioma() {
        return caracteristicasIdioma;
    }

    public void setCaracteristicasIdioma(CaracteristicasIdioma caracteristicasIdioma) {
        this.caracteristicasIdioma = caracteristicasIdioma;
    }

    public Nivel getNivel() {
        return nivel;
    }

    public void setNivel(Nivel nivel) {
        this.nivel = nivel;
    }

    public IdiomasCaracteristicas getIdiomasCaracteristicas() {
        return idiomasCaracteristicas;
    }

    public void setIdiomasCaracteristicas(IdiomasCaracteristicas idiomasCaracteristicas) {
        this.idiomasCaracteristicas = idiomasCaracteristicas;
    }

    public IdiomasCaracteristicasPK getIdiomasCaracteristicasPK() {
        return idiomasCaracteristicasPK;
    }

    public void setIdiomasCaracteristicasPK(IdiomasCaracteristicasPK idiomasCaracteristicasPK) {
        this.idiomasCaracteristicasPK = idiomasCaracteristicasPK;
    }

    public int getIdCaracteristicaIdioma() {
        return idCaracteristicaIdioma;
    }

    public void setIdCaracteristicaIdioma(int idCaracteristicaIdioma) {
        this.idCaracteristicaIdioma = idCaracteristicaIdioma;
    }

    public Dependencias getDependencias() {
        return dependencias;
    }

    public void setDependencias(Dependencias dependencias) {
        this.dependencias = dependencias;
    }

    public DirNacional getDirNacional() {
        return DirNacional;
    }

    public void setDirNacional(DirNacional DirNacional) {
        this.DirNacional = DirNacional;
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

        System.out.println(this.getNombreEmp());
    }

    public String getNombreEmp() {
        return nombreEmp;
    }

    public void setNombreEmp(String nombreEmp) {
        this.nombreEmp = nombreEmp;
    }

    public int getIdiomaSelecionado() {
        return idiomaSelecionado;
    }

    public void setIdiomaSelecionado(int idiomaSelecionado) {
        this.idiomaSelecionado = idiomaSelecionado;
    }

    public String getInstIdioma() {
        return instIdioma;
    }

    public void setInstIdioma(String instIdioma) {
        this.instIdioma = instIdioma;
        System.out.println(this.getInstIdioma());
    }

    public String getIdiomaSelecionadoNombre() {
        return idiomaSelecionadoNombre;
    }

    public void setIdiomaSelecionadoNombre(String idiomaSelecionadoNombre) {
        this.idiomaSelecionadoNombre = idiomaSelecionadoNombre;
    }

// **************** LISTA DE ELEMENTOS EN TABLAS *******************************
    public List<EstudiosEmp> todosEstudiosFomales() {
        Empleados empEst = getEmpleadosFacade().find(this.getEmpleadoSelecionado());
        if (empEst == null) {
            return null;
        } else {
            List<EstudiosEmp> estudiosList = empEst.getEstudiosEmpList();
            ArrayList<EstudiosEmp> formales = new ArrayList<>();
            for (EstudiosEmp estudiosList1 : estudiosList) {
                if (estudiosList1.getTipoEstudio().equals("Formal")) {
                    formales.add(estudiosList1);
                }
            }

            return formales;
        }
    }

    public List<EstudiosEmp> todosEstudiosNoFomales() {
        Empleados empEst = getEmpleadosFacade().find(this.getEmpleadoSelecionado());
        if (empEst == null) {
            return null;
        } else {
            List<EstudiosEmp> estudiosList = empEst.getEstudiosEmpList();
            ArrayList<EstudiosEmp> noFormales = new ArrayList<>();
            for (EstudiosEmp estudiosList1 : estudiosList) {
                if (estudiosList1.getTipoEstudio().equals("No Formal")) {
                    noFormales.add(estudiosList1);
                }
            }

            return noFormales;
        }
    }

    public List<IdiomasCaracteristicas> todosIdiomasCarac() {
        return getIdiomasCaracteristicasFacade().findAll();
    }

    public List<IdiomasCaracteristicas> todosIdiomasId() {
        if (this.idiomas.getIdIdioma() == null) {
            return null;
        } else {
            return getIdiomasCaracteristicasFacade().buscarIdiomasId(this.idiomas.getIdIdioma());
        }
    }

    public List<Anio> todosAnios() {
        return getAnioFacade().findAll();
    }

    public List<ProfOficios> todasProfOficios() {
        return getProfOficiosFacade().findAll();
    }

    public List<Idiomas> todosIdiomas() {
        return getIdiomasFacade().findAll();
    }

    public List<Nivel> todosNiveles() {
        return getNivelFacade().findAll();
    }

    public List<CaracteristicasIdioma> todasCaracIdiomas() {
        return getCaracteristicasIdiomaFacade().findAll();
    }

    public List<DirNacional> todosDirNacional() {
        return getDirNacionalFacade().findAll();
    }

    public List<Dependencias> dependenciasFiltradas() {
        return getDependenciasFacade().buscarDependencias(this.getDireccionNacional());
    }

    public List<Idiomas> idiomasEmpleado() {
        Empleados empEst = getEmpleadosFacade().find(this.getEmpleadoSelecionado());
        if (empEst == null) {
            return null;
        } else {
            return empEst.getIdiomasList();
        }
    }

    public List<Empleados> empleadoFiltrado() {
        return getEmpleadosFacade().buscarEmp(this.getDependecia());
    }
//*************************** FUNCIONES DE GUARDAR ***************************** 

    public void guardarEstudioFormal() {

        // Persiste el nuevo estudio ingresado
        estudiosEmp.setTipoEstudio("Formal");
        estudiosEmp.setAnioEstudio(fechaAnio(anio));
        getEstudiosEmpFacade().create(estudiosEmp);

        // Obtiene Empleado al cual se agregara un nuevo estudio (empleadoId) contendra el id de Empleado
        Empleados empEst = getEmpleadosFacade().find(this.getEmpleadoSelecionado());

        // Obtine Listado de Estudios ya ingresados y agrega el que se acaba de persistir
        List<EstudiosEmp> estudiosIngresados = empEst.getEstudiosEmpList();
        estudiosIngresados.add(estudiosEmp);

        if (this.path[3] != null) {
            // Ingreso de documento que lo respalda
            imagenDocumento.setIdEstudio(estudiosEmp);
            imagenDocumento.setIdEmpleado(empEst);
            imagenDocumento.setRefImgDoc(empEst.getNrEmpleado());
            imagenDocumento.setNombreArchivo(this.nombreImgDoc[3]);
            imagenDocumento.setSizeArchivo(this.sizeImgDoc[3]);
            imagenDocumento.setDescripcion("Documento de Estudio Formal");
            imagenDocumento.setRutaArchivo(this.path[3]);
            imagenDocumento.setTipoArchivo(this.tipoImgDoc[3]);
            getImgDocFacade().create(imagenDocumento);
        }

        // Setea el nuevo listado de Estudios y edita la informacion
        empEst.setEstudiosEmpList(estudiosIngresados);
        getEmpleadosFacade().edit(empEst);

        estudiosEmp = new EstudiosEmp();
        imagenDocumento = new ImgDoc();
    }

    public void guardarEstudioNoFormal() {
        // Igual a Estudio Formal
        estudiosEmp.setTipoEstudio("No Formal");
        estudiosEmp.setAnioEstudio(fechaAnio(anio));
        getEstudiosEmpFacade().create(estudiosEmp);
        Empleados empEst = getEmpleadosFacade().find(this.getEmpleadoSelecionado());
        List<EstudiosEmp> estudiosIngresados = empEst.getEstudiosEmpList();
        estudiosIngresados.add(estudiosEmp);
        imagenDocumento.setIdEstudio(estudiosEmp);
        imagenDocumento.setIdEmpleado(empEst);
        imagenDocumento.setRefImgDoc(empEst.getNrEmpleado());
        imagenDocumento.setNombreArchivo(this.nombreImgDoc[4]);
        imagenDocumento.setSizeArchivo(this.sizeImgDoc[4]);
        imagenDocumento.setDescripcion("Documento de Estudio No Formal");
        imagenDocumento.setRutaArchivo(this.path[4]);
        imagenDocumento.setTipoArchivo(this.tipoImgDoc[4]);
        getImgDocFacade().create(imagenDocumento);
        empEst.setEstudiosEmpList(estudiosIngresados);
        getEmpleadosFacade().edit(empEst);
        estudiosEmp = new EstudiosEmp();
        imagenDocumento = new ImgDoc();
    }

    public void guardarIdioma() {
        Empleados empIdioma = getEmpleadosFacade().find(this.getEmpleadoSelecionado());
        List<Idiomas> IdiomasIngresados = empIdioma.getIdiomasList();
        IdiomasIngresados.add(new Idiomas(this.getIdiomaSelecionado()));
        empIdioma.setIdiomasList(IdiomasIngresados);
        getEmpleadosFacade().edit(empIdioma);
    }

    public void AgregadnoCaracteristicasIdiomas() {
        //Setea Id idioma y caracteristicas a IdiomasCaracteristicasPK
        idiomasCaracteristicasPK.setIdCaractIdioma(getIdCaracteristicaIdioma());
        idiomasCaracteristicasPK.setIdIdioma(this.getIdiomas().getIdIdioma());

        //Setea IdIdiomasCaracteristicasPK y Id Idiomas a IdiomasCaracteristicas
        idiomasCaracteristicas.setInstitucionIdioma(this.getInstIdioma());
        idiomasCaracteristicas.setIdiomasCaracteristicasPK(idiomasCaracteristicasPK);
        idiomasCaracteristicas.setIdiomas(new Idiomas(this.getIdiomas().getIdIdioma()));
        idiomasCaracteristicas.setCaracteristicasIdioma(new CaracteristicasIdioma(getIdCaracteristicaIdioma()));

        getIdiomasCaracteristicasFacade().create(idiomasCaracteristicas);

        if (this.path[5] != null) {
            Empleados empIdioma = getEmpleadosFacade().find(this.getEmpleadoSelecionado());
            imagenDocumento.setIdiomasCaracteristicas(idiomasCaracteristicas);
            imagenDocumento.setIdEmpleado(empIdioma);
            imagenDocumento.setRefImgDoc(empIdioma.getNrEmpleado());
            imagenDocumento.setNombreArchivo(this.nombreImgDoc[5]);
            imagenDocumento.setSizeArchivo(this.sizeImgDoc[5]);
            imagenDocumento.setDescripcion("Documento de Idiomas");
            imagenDocumento.setRutaArchivo(this.path[5]);
            imagenDocumento.setTipoArchivo(this.tipoImgDoc[5]);
            getImgDocFacade().create(imagenDocumento);
        }

        idiomasCaracteristicasPK = new IdiomasCaracteristicasPK();
        idiomasCaracteristicas = new IdiomasCaracteristicas();
        imagenDocumento = new ImgDoc();

    }

    public void eliminarEstudio() {
        getEstudiosEmpFacade().remove(estudiosEmp);
    }

    public void eliminarCaracteristica() {
        getIdiomasCaracteristicasFacade().remove(idiomasCaracteristicas);
    }

    public void eliminarIdioma() {

        //Eliminar Caracteristicas segun el id de empleado y de idioma selecionado
        System.out.println("carcateriticas eliminadas");
        //Eliminar idioma de empleado
        System.out.println("idioma eliminado");

        Empleados empIdioma = getEmpleadosFacade().find(this.getEmpleadoSelecionado());
        List<Idiomas> IdiomasIngresados = empIdioma.getIdiomasList();
        //IdiomasIngresados.add(new Idiomas(this.getIdiomaSelecionado()));
        IdiomasIngresados.remove(new Idiomas(this.getIdiomaSelecionado()));
        empIdioma.setIdiomasList(IdiomasIngresados);
        getEmpleadosFacade().edit(empIdioma);
    }

    // Devuelve una fecha a partir de un aÃ±o dado (Estudios Empleados)    
    public Date fechaAnio(int anio) {
        anio = anio - 1900;
        Date fecha = new Date(anio, 11, 31);
        return fecha;
    }

}
