package sv.gob.cultura.rrhh.manejadores;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import sv.gob.cultura.rrhh.entidades.*;
import sv.gob.cultura.rrhh.facades.AdministradoraPensionesFacade;
import sv.gob.cultura.rrhh.facades.AnioFacade;
import sv.gob.cultura.rrhh.facades.CaracteristicasIdiomaFacade;
import sv.gob.cultura.rrhh.facades.ContactoEmergenciaEmpFacade;
import sv.gob.cultura.rrhh.facades.DependenciasFacade;
import sv.gob.cultura.rrhh.facades.DeptosFacade;
import sv.gob.cultura.rrhh.facades.DirNacionalFacade;
import sv.gob.cultura.rrhh.facades.EmpleadosFacade;
import sv.gob.cultura.rrhh.facades.EstadoCivilFacade;
import sv.gob.cultura.rrhh.facades.EstadosFacade;
import sv.gob.cultura.rrhh.facades.EstudiosEmpFacade;
import sv.gob.cultura.rrhh.facades.ExperienciaLaboralFacade;
import sv.gob.cultura.rrhh.facades.FamiliaDependientesEmpFacade;
import sv.gob.cultura.rrhh.facades.IdiomasCaracteristicasFacade;
import sv.gob.cultura.rrhh.facades.IdiomasFacade;
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
@ViewScoped
public class manejadorGestionEmpleado implements Serializable {
    

//******************************************************************************
// ************** LLAMADA A LOS ENTERPRICE JAVA BEANS **************************
//******************************************************************************
    @EJB
    private ExperienciaLaboralFacade experienciaLaboralFacade;
    @EJB
    private EstudiosEmpFacade estudiosEmpFacade;
    @EJB
    private FamiliaDependientesEmpFacade familiaDependientesEmpFacade;
    @EJB
    private ContactoEmergenciaEmpFacade contactoEmergenciaEmpFacade;
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

//******************************************************************************
//*********************** OBJETOS DE LOS ENTIDADES *****************************
//******************************************************************************
    private ExperienciaLaboral experienciaLaboral = new ExperienciaLaboral();
    private EstudiosEmp estudiosEmp = new EstudiosEmp();
    private FamiliaDependientesEmp familiaDependientesEmp = new FamiliaDependientesEmp();
    private ContactoEmergenciaEmp contactoEmergenciaEmp = new ContactoEmergenciaEmp();
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

//******************************************************************************
//****** VARIABLES QUE CONTRENDRAN ID´S O STRING DE FORMULARIOS ****************
//******************************************************************************
    private String nr = "NR 0111-111111-111-2";     // nr para pruebas de guardar
    private String fechaNacFamiliaDependiente;      // fecha naciemeinto familiares
    private String fechaNacEmpleado;                // fehca nacimiento empleado
    private int dirDepto;                           // id departamento residencia
    private int dirMuni;                            // id municipio residencia
    private int parentescoId;                       // id Parentesco    
    private int empleadoId = 3;                     // id empleado Ahora prueba empleado id=3
    private int depto, deptonac;                    // id´s departamento municipio Nacimiento
    private int muni;                               // id municipio Nacimiento    
    private int edadFamiliaDependiente;             // edad familiares calculada a partir de fecha    
    private int edadEmpleado;                       // edad de empleado calculada a partir de fecha
    private int dirNacionalFiltrarJefe;             // id´s direccion nacional
    private int dependeciasFiltrarJefe;             // id´s dependencias
    private int anio;                               // Año de Estudios
    private int idIdioma;                           // Id Idioma
    private int idCaracteristicaIdioma;             // Id Caracteristica de idioma
// *****************************************************************************
//********************** GET DE ENTERPRICE JAVA BEAN ***************************
//******************************************************************************
    public ExperienciaLaboralFacade getExperienciaLaboralFacade() {
        return experienciaLaboralFacade;
    }

    public EstudiosEmpFacade getEstudiosEmpFacade() {
        return estudiosEmpFacade;
    }

    public FamiliaDependientesEmpFacade getFamiliaDependientesEmpFacade() {
        return familiaDependientesEmpFacade;
    }

    public ContactoEmergenciaEmpFacade getContactoEmergenciaEmpFacade() {
        return contactoEmergenciaEmpFacade;
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

// *****************************************************************************
//******************* GET y SET DE OBJETOS DE ENTIDADES ************************
//******************************************************************************
    public ExperienciaLaboral getExperienciaLaboral() {
        return experienciaLaboral;
    }

    public void setExperienciaLaboral(ExperienciaLaboral experienciaLaboral) {
        this.experienciaLaboral = experienciaLaboral;
    }

    public EstudiosEmp getEstudiosEmp() {
        return estudiosEmp;
    }

    public void setEstudiosEmp(EstudiosEmp estudiosEmp) {
        this.estudiosEmp = estudiosEmp;
    }

    public FamiliaDependientesEmp getFamiliaDependientesEmp() {
        return familiaDependientesEmp;
    }

    public void setFamiliaDependientesEmp(FamiliaDependientesEmp familiaDependientesEmp) {
        this.familiaDependientesEmp = familiaDependientesEmp;
    }

    public ContactoEmergenciaEmp getContactoEmergenciaEmp() {
        return contactoEmergenciaEmp;
    }

    public void setContactoEmergenciaEmp(ContactoEmergenciaEmp contactoEmergenciaEmp) {
        this.contactoEmergenciaEmp = contactoEmergenciaEmp;
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

    public String getFechaNacFamiliaDependiente() {
        return fechaNacFamiliaDependiente;
    }

    public void setFechaNacFamiliaDependiente(String fechaNacFamiliaDependiente) {
        this.fechaNacFamiliaDependiente = fechaNacFamiliaDependiente;
        this.setEdadFamiliaDependiente(edad(fechaNacFamiliaDependiente));
    }

    public int getEdadFamiliaDependiente() {
        return edadFamiliaDependiente;
    }

    public void setEdadFamiliaDependiente(int edadFamiliaDependiente) {
        this.edadFamiliaDependiente = edadFamiliaDependiente;
    }

    public String getFechaNacEmpleado() {
        return fechaNacEmpleado;
    }

    public void setFechaNacEmpleado(String fechaNacEmpleado) {
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

    public List<Parentesco> todosParentesco() {
        return getParentescoFacade().findAll();
    }

    public List<DirNacional> todosDirNacional() {
        return getDirNacionalFacade().findAll();
    }

    public List<ContactoEmergenciaEmp> todosContactoEmergenciaEmp() {
        return getContactoEmergenciaEmpFacade().findAll();
    }

    public List<FamiliaDependientesEmp> todosFamiliaDependientesEmp() {
        return getFamiliaDependientesEmpFacade().findAll();
    }

    public List<Municipios> municipiosFiltrados() {
        return getMunicipiosFacade().buscarDep(depto);
    }

    public List<Municipios> municipiosFiltradosNac() {
        return getMunicipiosFacade().buscarDep(dirDepto);
    }

    public List<Dependencias> dependenciasFiltradas() {
        return getDependenciasFacade().buscarDependencias(dirNacionalFiltrarJefe);
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

    public List<ExperienciaLaboral> experienciaLaboralPublico() {
        return getExperienciaLaboralFacade().experienciaLaboralSector("Público");
    }

    public List<ExperienciaLaboral> experienciaLaboralPrivado() {
        return getExperienciaLaboralFacade().experienciaLaboralSector("Privado");
    }

    public List<ProfOficios> todasProfOficios() {
        return getProfOficiosFacade().findAll();
    }
    
    public List<Idiomas> todosIdiomas(){
        return getIdiomasFacade().findAll();
    }
    
    public List<Nivel> todosNiveles(){
        return getNivelFacade().findAll();
    }
    
    public List<CaracteristicasIdioma> todasCaracIdiomas(){
        return getCaracteristicasIdiomaFacade().findAll();
    }

    public List<Anio> todosAnios() {
        return getAnioFacade().findAll();
    }

//******************************************************************************
//*************************** FUNCIONES DE GUARDAR *****************************
//******************************************************************************
    
    public void guardarContactosEmergencia() {
        contactoEmergenciaEmp.setIdEmpleado(new Empleados(empleadoId));
        getContactoEmergenciaEmpFacade().create(contactoEmergenciaEmp);
        contactoEmergenciaEmp = new ContactoEmergenciaEmp();
    }

    public void guardarFamiliaDependiente() {
        familiaDependientesEmp.setIdEmpleado(new Empleados(empleadoId));
        familiaDependientesEmp.setEdadFamilia(this.getEdadFamiliaDependiente());
        familiaDependientesEmp.setFechaNacFamilia(new Date(this.getFechaNacFamiliaDependiente()));
        getFamiliaDependientesEmpFacade().create(familiaDependientesEmp);
        familiaDependientesEmp = new FamiliaDependientesEmp();
    }

    public void guardarExpLaboralPublico() {
        experienciaLaboral.setIdEmpleado(new Empleados(empleadoId));
        experienciaLaboral.setSectorExpLab("Público");
        getExperienciaLaboralFacade().create(experienciaLaboral);
        experienciaLaboral = new ExperienciaLaboral();
    }

    public void guardarExpLaboralPrivado() {
        experienciaLaboral.setIdEmpleado(new Empleados(empleadoId));
        experienciaLaboral.setSectorExpLab("Privado");
        getExperienciaLaboralFacade().create(experienciaLaboral);
        experienciaLaboral = new ExperienciaLaboral();
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

        // Setea el nuevo listado de Estudios y edita la informacion
        empEst.setEstudiosEmpList(estudiosIngresados);
        getEmpleadosFacade().edit(empEst);

        estudiosEmp = new EstudiosEmp();
    }

    public void guardarEstudioNoFormal() {
        // Igual a Estudio Formal
        estudiosEmp.setTipoEstudio("No Formal");
        estudiosEmp.setAnioEstudio(fechaAnio(anio));
        getEstudiosEmpFacade().create(estudiosEmp);
        Empleados empEst = getEmpleadosFacade().find(empleadoId);
        List<EstudiosEmp> estudiosIngresados = empEst.getEstudiosEmpList();
        estudiosIngresados.add(estudiosEmp);
        empEst.setEstudiosEmpList(estudiosIngresados);
        getEmpleadosFacade().edit(empEst);
        estudiosEmp = new EstudiosEmp();
    }
    
    public void guardarIdioma(){
        
        System.out.println("HOLA MUNDO");
        //idiomasCaracteristicas.setIdiomas(new Idiomas(idIdioma));
        //idiomasCaracteristicas.setCaracteristicasIdioma(new CaracteristicasIdioma(idCaracteristicaIdioma));
        System.out.println(idiomasCaracteristicas.getIdiomas());
        //System.out.println(idiomasCaracteristicas.getCaracteristicasIdioma());
        System.out.println(idiomasCaracteristicas.getIdNivel());
        //idiomasCaracteristicas = new IdiomasCaracteristicas();
    }

// *****************************************************************************
// ************ FUNCIONES EXTRA QUE SE UTLIZAN LOS FORMULARIOS *****************
//******************************************************************************
    
    //Devuelve edad apartir de fecha de nacimeinto
    public int edad(String fecha_nac) {

        Date fechaNac = new Date(fecha_nac);
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
        return anios;
    }

    // Devuelve una fecha a partir de un año dado (Estudios Empleados)    
    public Date fechaAnio(int anio) {
        anio = anio - 1900;
        Date fecha = new Date(anio, 11, 31);
        return fecha;
    }
//******************************************************************************
// *****************************************************************************    
}
