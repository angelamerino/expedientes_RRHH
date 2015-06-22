package sv.gob.cultura.rrhh.manejadores;

import java.io.*;
import java.text.DecimalFormat;
import java.util.*;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;
import sv.gob.cultura.rrhh.entidades.*;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import sv.gob.cultura.rrhh.facades.AdministradoraPensionesFacade;
import sv.gob.cultura.rrhh.facades.AnioFacade;
import sv.gob.cultura.rrhh.facades.CaracteristicasIdiomaFacade;
import sv.gob.cultura.rrhh.facades.DependenciasFacade;
import sv.gob.cultura.rrhh.facades.DeptosFacade;
import sv.gob.cultura.rrhh.facades.DirNacionalFacade;
import sv.gob.cultura.rrhh.facades.EmpleadosFacade;
import sv.gob.cultura.rrhh.facades.EstadoCivilFacade;
import sv.gob.cultura.rrhh.facades.EstadosFacade;
import sv.gob.cultura.rrhh.facades.EstudiosEmpFacade;
import sv.gob.cultura.rrhh.facades.IdiomasCaracteristicasFacade;
import sv.gob.cultura.rrhh.facades.IdiomasFacade;
import sv.gob.cultura.rrhh.facades.ImgDocFacade;
import sv.gob.cultura.rrhh.facades.InstBancariaFacade;
import sv.gob.cultura.rrhh.facades.MunicipiosFacade;
import sv.gob.cultura.rrhh.facades.NivelFacade;
import sv.gob.cultura.rrhh.facades.PaisesFacade;
import sv.gob.cultura.rrhh.facades.ParentescoFacade;
import sv.gob.cultura.rrhh.facades.ProfOficiosFacade;
import sv.gob.cultura.rrhh.facades.TipoSangreFacade;

//1. implements Serializable
//2. Insertar Facade con Call Bean
//3. Generar solo Get al Bean(2)
//4. Crear un objeto de la entidad
//5. Get y Set del objeto (all+insert)
@Named(value = "manejadorGestionEmpleado")
@SessionScoped

public class manejadorGestionEmpleado implements Serializable {
    manejadorGestionEmpleado(){    
    }
//******************************************************************************
// ************** LLAMADA A LOS ENTERPRICE JAVA BEANS **************************
//******************************************************************************
    
    @EJB
    private EstudiosEmpFacade estudiosEmpFacade;    
    @EJB    
    private EmpleadosFacade empleadosFacade;
    @EJB
    private EstadosFacade estadosFacade;
    @EJB
    private MunicipiosFacade municipiosFacade;
    @EJB
    private DeptosFacade deptosFacade;
    @EJB
    private PaisesFacade paisesFacade;
    @EJB
    private TipoSangreFacade tipoSangreFacade;
    @EJB
    private EstadoCivilFacade estadoCivilFacade;
    @EJB
    private InstBancariaFacade instBancariaFacade;
    @EJB
    private AdministradoraPensionesFacade administradoraPensionesFacade;
    @EJB
    private ParentescoFacade parentescoFacade;
    @EJB
    private DependenciasFacade dependenciasFacade;
    @EJB
    private DirNacionalFacade dirNacionalFacade;
    @EJB
    private ProfOficiosFacade profOficiosFacade;
    @EJB
    private IdiomasFacade idiomasFacade;
    @EJB
    private CaracteristicasIdiomaFacade caracteristicasIdiomaFacade;
    @EJB
    private NivelFacade nivelFacade;
    @EJB
    private IdiomasCaracteristicasFacade idiomasCaracteristicasFacade;
    @EJB
    private AnioFacade anioFacade;
    @EJB
    private ImgDocFacade imgDocFacade;
//******************************************************************************
//*********************** OBJETOS DE LOS ENTIDADES *****************************
//******************************************************************************
    
    private EstudiosEmp estudiosEmp = new EstudiosEmp();    
    private Empleados empleado = new Empleados();
    private Estados estados = new Estados();
    private Paises paises = new Paises();
    private Deptos deptos = new Deptos();
    private Municipios municipios = new Municipios();
    private TipoSangre tipoSangre = new TipoSangre();
    private EstadoCivil estadoCivil = new EstadoCivil();
    private InstBancaria instBancaria = new InstBancaria();
    private AdministradoraPensiones administradoraPensiones = new AdministradoraPensiones();
    private Parentesco parentesco = new Parentesco();
    private Dependencias dependencias = new Dependencias();
    private DirNacional dirNacional = new DirNacional();
    private ProfOficios profOficos = new ProfOficios();
    private Idiomas idiomas = new Idiomas();
    private CaracteristicasIdioma caracteristicasIdioma = new CaracteristicasIdioma();
    private Nivel nivel = new Nivel();
    private IdiomasCaracteristicas idiomasCaracteristicas = new IdiomasCaracteristicas();
    private IdiomasCaracteristicasPK idiomasCaracteristicasPK = new IdiomasCaracteristicasPK();
    private ImgDoc imagenDocumento = new ImgDoc();

//******************************************************************************
//****** VARIABLES QUE CONTRENDRAN ID´S O STRING DE FORMULARIOS ****************
//******************************************************************************
    private String nr = "NR 0111-111111-111-2";     // nr para pruebas de guardar
    private final String[] path = new String[10];                                     // contiene url de documentos
    private final String[] nombreImgDoc = new String[10];
    private final String[] tipoImgDoc = new String[10];
    private final int[] sizeImgDoc = new int[10];    
    private String pathFoto = "C:\\Users\\SOPORTE CULTURA\\AppData\\Roaming\\NetBeans\\8.0.1\\config\\GF_4.1\\domain1\\var\\webapp\\upload\\userx.png";
    private String nombreImagen;
    private Date fechaNacEmpleado = new Date();                // fehca nacimiento empleado
    private int dirDepto;                           // id departamento residencia
    private int dirMuni;                            // id municipio residencia
    private int parentescoId;                       // id Parentesco    
    private int empleadoId = 3;                     // id empleado Ahora prueba empleado id=3
    private int empJefe;
    private int depto, deptonac;                    // id´s departamento municipio Nacimiento
    private int muni;                               // id municipio Nacimiento           
    private int edadEmpleado;                       // edad de empleado calculada a partir de fecha
    private int dirNacionalFiltrarJefe;             // id´s direccion nacional
    private int dependeciasFiltrarJefe;             // id´s dependencias
    private int dirNacionalNominal;
    private int dependenciaNominal;
    private int dirNacionalFuncional;
    private int dependenciaFuncional;
    private int anio;                               // Año de Estudios
    private int idIdioma;                           // Id Idioma
    private int idCaracteristicaIdioma;             // Id Caracteristica de idioma
// *****************************************************************************
//********************** GET DE ENTERPRICE JAVA BEAN ***************************
//******************************************************************************

    public EstudiosEmpFacade getEstudiosEmpFacade() {
        return estudiosEmpFacade;
    }

    public EmpleadosFacade getEmpleadosFacade() {
        return empleadosFacade;
    }

    public EstadosFacade getEstadosFacade() {
        return estadosFacade;
    }

    public PaisesFacade getPaisesFacade() {
        return paisesFacade;
    }

    public MunicipiosFacade getMunicipiosFacade() {
        return municipiosFacade;
    }

    public DeptosFacade getDeptosFacade() {
        return deptosFacade;
    }

    public TipoSangreFacade getTipoSangreFacade() {
        return tipoSangreFacade;
    }

    public EstadoCivilFacade getEstadoCivilFacade() {
        return estadoCivilFacade;
    }

    public InstBancariaFacade getInstBancariaFacade() {
        return instBancariaFacade;
    }

    public AdministradoraPensionesFacade getAdministradoraPensionesFacade() {
        return administradoraPensionesFacade;
    }

    public ParentescoFacade getParentescoFacade() {
        return parentescoFacade;
    }

    public DependenciasFacade getDependenciasFacade() {
        return dependenciasFacade;
    }

    public DirNacionalFacade getDirNacionalFacade() {
        return dirNacionalFacade;
    }

    public ProfOficiosFacade getProfOficiosFacade() {
        return profOficiosFacade;
    }

    public IdiomasFacade getIdiomasFacade() {
        return idiomasFacade;
    }

    public CaracteristicasIdiomaFacade getCaracteristicasIdiomaFacade() {
        return caracteristicasIdiomaFacade;
    }

    public NivelFacade getNivelFacade() {
        return nivelFacade;
    }

    public IdiomasCaracteristicasFacade getIdiomasCaracteristicasFacade() {
        return idiomasCaracteristicasFacade;
    }

    public AnioFacade getAnioFacade() {
        return anioFacade;
    }

    public ImgDocFacade getImgDocFacade() {
        return imgDocFacade;
    }

// *****************************************************************************
//******************* GET y SET DE OBJETOS DE ENTIDADES ************************
//******************************************************************************
    

    public EstudiosEmp getEstudiosEmp() {
        return estudiosEmp;
    }

    public void setEstudiosEmp(EstudiosEmp estudiosEmp) {
        this.estudiosEmp = estudiosEmp;
    }

    public Empleados getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleados empleado) {
        this.empleado = empleado;
    }

    public Estados getEstados() {
        return estados;
    }

    public void setEstados(Estados estados) {
        this.estados = estados;
    }

    public Paises getPaises() {
        return paises;
    }

    public void setPaises(Paises paises) {
        this.paises = paises;
    }

    public Deptos getDeptos() {
        return deptos;
    }

    public void setDeptos(Deptos deptos) {
        this.deptos = deptos;
    }

    public Municipios getMunicipios() {
        return municipios;
    }

    public void setMunicipios(Municipios municipios) {
        this.municipios = municipios;
    }

    public TipoSangre getTipoSangre() {
        return tipoSangre;
    }

    public void setTipoSangre(TipoSangre tipoSangre) {
        this.tipoSangre = tipoSangre;
    }

    public EstadoCivil getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(EstadoCivil estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public InstBancaria getInstBancaria() {
        return instBancaria;
    }

    public void setInstBancaria(InstBancaria instBancaria) {
        this.instBancaria = instBancaria;
    }

    public AdministradoraPensiones getAdministradoraPensiones() {
        return administradoraPensiones;
    }

    public void setAdministradoraPensiones(AdministradoraPensiones administradoraPensiones) {
        this.administradoraPensiones = administradoraPensiones;
    }

    public ProfOficios getProfOficos() {
        return profOficos;
    }

    public void setProfOficos(ProfOficios profOficos) {
        this.profOficos = profOficos;
    }

    public int getDirDepto() {
        return dirDepto;
    }

    public void setDirDepto(int dirDepto) {
        this.dirDepto = dirDepto;
    }

    public int getDirMuni() {
        return dirMuni;
    }

    public void setDirMuni(int dirMuni) {
        this.dirMuni = dirMuni;
    }

    public Parentesco getParentesco() {
        return parentesco;
    }

    public void setParentesco(Parentesco parentesco) {
        this.parentesco = parentesco;
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

    public int getParentescoId() {
        return parentescoId;
    }

    public void setParentescoId(int parentescoId) {
        this.parentescoId = parentescoId;
    }

    public String getNr() {
        return nr;
    }

    public void setNr(String nr) {
        this.nr = nr;
    }

    public void setDeptonac(int deptonac) {
        this.deptonac = deptonac;
    }

    public int getDeptonac() {
        return deptonac;
    }

    public int getEmpleadoId() {
        return empleadoId;
    }

    public void setEmpleadoId(int empleadoId) {
        this.empleadoId = empleadoId;
    }

    public int getDepto() {
        return depto;
    }

    public void setDepto(int depto) {
        this.depto = depto;
    }

    public int getMuni() {
        return muni;
    }

    public void setMuni(int muni) {
        this.muni = muni;
    }

    public Date getFechaNacEmpleado() {
        return fechaNacEmpleado;
    }

    public void setFechaNacEmpleado(Date fechaNacEmpleado) {
        this.fechaNacEmpleado = fechaNacEmpleado;
        this.setEdadEmpleado(edad(fechaNacEmpleado));
    }

    public int getEdadEmpleado() {
        return edadEmpleado;
    }

    public void setEdadEmpleado(int edadEmpleado) {
        this.edadEmpleado = edadEmpleado;
    }

    public int getDirNacionalFiltrarJefe() {
        return dirNacionalFiltrarJefe;
    }

    public void setDirNacionalFiltrarJefe(int dirNacionalFiltrarJefe) {
        this.dirNacionalFiltrarJefe = dirNacionalFiltrarJefe;
    }

    public int getDependeciasFiltrarJefe() {
        return dependeciasFiltrarJefe;
    }

    public void setDependeciasFiltrarJefe(int dependeciasFiltrarJefe) {
        this.dependeciasFiltrarJefe = dependeciasFiltrarJefe;
    }

    public Idiomas getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(Idiomas idiomas) {
        this.idiomas = idiomas;
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

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public int getIdIdioma() {
        return idIdioma;
    }

    public void setIdIdioma(int idIdioma) {
        this.idIdioma = idIdioma;
    }

    public int getIdCaracteristicaIdioma() {
        return idCaracteristicaIdioma;
    }

    public void setIdCaracteristicaIdioma(int idCaracteristicaIdioma) {
        this.idCaracteristicaIdioma = idCaracteristicaIdioma;
    }

    public ImgDoc getImagenDocumento() {
        return imagenDocumento;
    }

    public void setImagenDocumento(ImgDoc imagenDocumento) {
        this.imagenDocumento = imagenDocumento;
    }

    public int getEmpJefe() {
        return empJefe;
    }

    public void setEmpJefe(int empJefe) {
        this.empJefe = empJefe;
    }

    public int getDirNacionalNominal() {
        return dirNacionalNominal;
    }

    public void setDirNacionalNominal(int dirNacionalNominal) {
        this.dirNacionalNominal = dirNacionalNominal;
    }

    public int getDependenciaNominal() {
        return dependenciaNominal;
    }

    public void setDependenciaNominal(int dependenciaNominal) {
        this.dependenciaNominal = dependenciaNominal;
    }

    public int getDirNacionalFuncional() {
        return dirNacionalFuncional;
    }

    public void setDirNacionalFuncional(int dirNacionalFuncional) {
        this.dirNacionalFuncional = dirNacionalFuncional;
    }

    public int getDependenciaFuncional() {
        return dependenciaFuncional;
    }

    public void setDependenciaFuncional(int dependenciaFuncional) {
        this.dependenciaFuncional = dependenciaFuncional;
    }

    public String getPathFoto() {
        return pathFoto;
    }

    public void setPathFoto(String pathFoto) {
        this.pathFoto = pathFoto;
    }

    public String getNombreImagen() {
        return nombreImagen;
    }

    public void setNombreImagen(String nombreImagen) {
        this.nombreImagen = nombreImagen;
    }
    
//******************************************************************************
// **************** LISTA DE ELEMENTOS EN TABLAS *******************************
//******************************************************************************
    public List<Estados> todosEstados() {
        return getEstadosFacade().findAll();
    }

    public List<Paises> todosPaises() {
        return getPaisesFacade().findAll();
    }

    public List<Deptos> todosDeptos() {
        return getDeptosFacade().findAll();
    }

    public List<Municipios> todosMunicipios() {
        return getMunicipiosFacade().findAll();
    }

    public List<EstadoCivil> todosEstadoCivil() {
        return getEstadoCivilFacade().findAll();
    }

    public List<TipoSangre> todosTipoSangre() {
        return getTipoSangreFacade().findAll();
    }

    public List<InstBancaria> todosInstBancaria() {
        return getInstBancariaFacade().findAll();
    }

    public List<AdministradoraPensiones> todosAdministradoraPensiones() {
        return getAdministradoraPensionesFacade().findAll();
    }

    public List<DirNacional> todosDirNacional() {
        return getDirNacionalFacade().findAll();
    }

    public List<Municipios> municipiosFiltrados() {
        return getMunicipiosFacade().buscarDep(depto);
    }

    public List<Municipios> municipiosFiltradosNac() {
        return getMunicipiosFacade().buscarDep(dirDepto);
    }

    public List<Dependencias> dependenciasFiltradas(int filtro) {

        switch (filtro) {
            case 1:
                return getDependenciasFacade().buscarDependencias(dirNacionalFiltrarJefe);
            case 2:
                return getDependenciasFacade().buscarDependencias(dirNacionalNominal);
            case 3:
                return getDependenciasFacade().buscarDependencias(dirNacionalFuncional);
            default:
                return null;
        }
    }

    public List<Empleados> empleadoJefeDependencia() {
        return getEmpleadosFacade().buscarEmp(dependeciasFiltrarJefe);
    }

    public List<Empleados> empleadoId() {
        return getEmpleadosFacade().buscarEmpId(empleadoId);
    }

    public List<EstudiosEmp> estudiosEmpId(int idEstudio) {
        return getEstudiosEmpFacade().buscarEstudioEmpId(idEstudio);
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

    public List<Anio> todosAnios() {
        return getAnioFacade().findAll();
    }

    public List<EstudiosEmp> todosEstudiosFomales() {
        return getEstudiosEmpFacade().buscarEstudioTipo("Formal");
    }

    public List<EstudiosEmp> todosEstudiosNoFomales() {
        return getEstudiosEmpFacade().buscarEstudioTipo("No Formal");
    }

    public List<IdiomasCaracteristicas> todosIdiomasCarac() {
        return getIdiomasCaracteristicasFacade().findAll();
    }

//******************************************************************************
//*************************** FUNCIONES DE GUARDAR *****************************
//******************************************************************************
    public void guardarEmpleado() {

        empleado.setDeptoNac(this.getDepto());
        empleado.setMunicipioNac(this.getMuni());
        empleado.setFechaNac(this.getFechaNacEmpleado());
        empleado.setEdadEmp(this.getEdadEmpleado());
        empleado.setDeptoResidencia(this.getDirDepto());
        empleado.setMunicipioResidencia(this.getDirMuni());
        empleado.setUrlFotoEmp(this.path[0]);
        empleado.setIdEmpleadoJefe(new Empleados(this.getEmpJefe()));
        empleado.setIdDependenciaN(new Dependencias(this.getDependenciaNominal()));
        empleado.setIdDependenciaF(new Dependencias(this.getDependenciaFuncional()));

        Dependencias depenNominal = getDependenciasFacade().find(this.getDependenciaNominal());
        Dependencias depenFuncional = getDependenciasFacade().find(this.getDependenciaFuncional());

        empleado.setIdMunicipioN(depenNominal.getIdMunicipio());
        empleado.setIdMunicipioF(depenFuncional.getIdMunicipio());
        empleado.setEdadEmp(this.getEdadEmpleado());

        getEmpleadosFacade().create(empleado);

        this.setEmpleadoId(empleado.getIdEmpleado());

        empleado = new Empleados();
    }

    public void guardarEstudioFormal() {

        // Persiste el nuevo estudio ingresado
        estudiosEmp.setTipoEstudio("Formal");
        estudiosEmp.setAnioEstudio(fechaAnio(anio));
        getEstudiosEmpFacade().create(estudiosEmp);

        // Obtiene Empleado al cual se agregara un nuevo estudio (empleadoId) contendra el id de Empleado
        Empleados empEst = getEmpleadosFacade().find(empleadoId);

        // Obtine Listado de Estudios ya ingresados y agrega el que se acaba de persistir
        List<EstudiosEmp> estudiosIngresados = empEst.getEstudiosEmpList();
        estudiosIngresados.add(estudiosEmp);

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
        Empleados empEst = getEmpleadosFacade().find(empleadoId);
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

        //Setea Id idioma y caracteristicas a IdiomasCaracteristicasPK
        idiomasCaracteristicasPK.setIdCaractIdioma(getIdCaracteristicaIdioma());
        idiomasCaracteristicasPK.setIdIdioma(getIdIdioma());

        //Setea IdIdiomasCaracteristicasPK y Id Idiomas a IdiomasCaracteristicas
        idiomasCaracteristicas.setIdiomasCaracteristicasPK(idiomasCaracteristicasPK);
        idiomasCaracteristicas.setIdiomas(new Idiomas(getIdIdioma()));
        idiomasCaracteristicas.setCaracteristicasIdioma(new CaracteristicasIdioma(getIdCaracteristicaIdioma()));

        /*  Guardamos(Persite)IdiomasCaracteristicas
         Crea un objeto del tipo empleados para obtener list de Idiomas
         agrega el nuevo idioma y actualiza el listado con edit  */
        getIdiomasCaracteristicasFacade().create(idiomasCaracteristicas);
        Empleados empIdioma = getEmpleadosFacade().find(empleadoId);

        imagenDocumento.setIdiomasCaracteristicas(idiomasCaracteristicas);
        imagenDocumento.setIdEmpleado(empIdioma);
        imagenDocumento.setRefImgDoc(empIdioma.getNrEmpleado());
        imagenDocumento.setNombreArchivo(this.nombreImgDoc[5]);
        imagenDocumento.setSizeArchivo(this.sizeImgDoc[5]);
        imagenDocumento.setDescripcion("Documento de Idiomas");
        imagenDocumento.setRutaArchivo(this.path[5]);
        imagenDocumento.setTipoArchivo(this.tipoImgDoc[5]);
        getImgDocFacade().create(imagenDocumento);

        List<Idiomas> IdiomasIngresados = empIdioma.getIdiomasList();
        IdiomasIngresados.add(idiomasCaracteristicas.getIdiomas());
        empIdioma.setIdiomasList(IdiomasIngresados);
        getEmpleadosFacade().edit(empIdioma);

        idiomasCaracteristicasPK = new IdiomasCaracteristicasPK();
        idiomasCaracteristicas = new IdiomasCaracteristicas();
        imagenDocumento = new ImgDoc();
    }

// *****************************************************************************
// ************ FUNCIONES EXTRA QUE SE UTLIZAN LOS FORMULARIOS *****************
//******************************************************************************
    //Devuelve edad apartir de fecha de nacimeinto
    public int edad(Date fecha_nac) {

        Date fechaNac = fecha_nac;
        Date fechaActual = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String hoy = formato.format(fechaActual);
        String nacimiento = formato.format(fechaNac);
        //String fechaNac = formato.format(fecha_nac);
        String[] dat1 = nacimiento.split("/");
        String[] dat2 = hoy.split("/");
        int anios = Integer.parseInt(dat2[2]) - Integer.parseInt(dat1[2]);
        int mes = Integer.parseInt(dat2[1]) - Integer.parseInt(dat1[1]);
        if (mes < 0) {
            anios = anios - 1;
        } else if (mes == 0) {
            int dia = Integer.parseInt(dat2[0]) - Integer.parseInt(dat1[0]);
            if (dia > 0) {
                anios = anios - 1;
            }
        }
        if (anios == -1) {
            anios = 0;
        }
        return anios;
    }

    // Devuelve una fecha a partir de un año dado (Estudios Empleados)    
    public Date fechaAnio(int anio) {
        anio = anio - 1900;
        Date fecha = new Date(anio, 11, 31);
        return fecha;
    }

    public void handleFileUpload(FileUploadEvent event) {

        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();
        File result = new File(externalContext.getRealPath("/upload/") + File.separator + event.getFile().getFileName());

         int tipoDoc = Integer.parseInt((String) event.getComponent().getAttributes().get("tipoDoc"));
        int var=0;
        switch (tipoDoc) {
            case 1: //Fotografia de empleado                
                var = 0;                
                System.out.println("Foto");                
                break;
            case 2: //Curriculum Vitae
                var = 1;
                System.out.println("Vitae");
                break;
            case 3: //Docuemto Descrior de puestos
                var = 2;
                System.out.println("puesto");
                break;
            case 4: //Estudios Formales
                var = 3;
                System.out.println("Estudio Formal");
                break;
            case 5: //Estudios No Formales
                var = 4;
                System.out.println("Estudio No Formal");
                break;
            case 6: //Estudios de Idiomas
                var = 5;
                System.out.println("Idiomas");
                break;
        }
        this.setNombreImagen(event.getFile().getFileName());
        this.nombreImgDoc[var] = event.getFile().getFileName();
        this.sizeImgDoc[var] = Integer.parseInt(new DecimalFormat("#.##").format(event.getFile().getSize()));
        this.path[var] = externalContext.getRealPath("/upload/") + "\\" + event.getFile().getFileName();
        this.tipoImgDoc[var] = event.getFile().getContentType();
        
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(result);
            byte[] buffer = new byte[51200];
            int bulk;
            // Here you get uploaded picture bytes, while debugging you can see that 34818
            InputStream inputStream = event.getFile().getInputstream();
            while (true) {
                bulk = inputStream.read(buffer);
                if (bulk < 0) {
                    break;
                } //end of if
                fileOutputStream.write(buffer, 0, bulk);
                fileOutputStream.flush();
            } //end fo while(true)

            fileOutputStream.close();
            inputStream.close();
            //FacesMessage msg = new FacesMessage("Succesful",event.getFile().getFileName() + " is uploaded.");
            //FacesContext.getCurrentInstance().addMessage(null, msg);
        } catch (IOException e) {
            //e.printStackTrace();
            //FacesMessage error = new FacesMessage("The files were not uploaded!");
            //FacesContext.getCurrentInstance().addMessage(null, error);
        }
    }
    
    private StreamedContent graphicImage;

    public void prepararImagen(){

        //String pathImagen = "C:\\Users\\SOPORTE CULTURA\\AppData\\Roaming\\NetBeans\\8.0.1\\config\\GF_4.1\\domain1\\var\\webapp\\upload\\"+nombreImgDoc[0]; 
        
        String pathImagen = pathFoto;
        
//        if(path[0]!=null){
//            pathImagen = path[0];
//        }
       System.out.println(pathImagen);
        try {
            graphicImage = new DefaultStreamedContent(new FileInputStream(pathImagen), "image/png");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(manejadorGestionEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }
  }

  public StreamedContent getGraphicImage() {  
        
        prepararImagen();        
        System.out.println(graphicImage);
        return graphicImage;
        
    }

    public void setGraphicImage(StreamedContent graphicImage) {
        this.graphicImage = graphicImage;
    }
//******************************************************************************
// *****************************************************************************    
}
