package sv.gob.cultura.rrhh.manejadores;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import sv.gob.cultura.rrhh.entidades.*;
import sv.gob.cultura.rrhh.facades.AdministradoraPensionesFacade;
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
import sv.gob.cultura.rrhh.facades.InstBancariaFacade;
import sv.gob.cultura.rrhh.facades.MunicipiosFacade;
import sv.gob.cultura.rrhh.facades.PaisesFacade;
import sv.gob.cultura.rrhh.facades.ParentescoFacade;
import sv.gob.cultura.rrhh.facades.TipoSangreFacade;

//1. implements Serializable
//2. Insertar Facade con Call Bean
//3. Generar solo Get al Bean(2)
//4. Crear un objeto de la entidad
//5. Get y Set del objeto (all+insert)
@Named(value = "manejadorGestionEmpleado")
@ViewScoped
public class manejadorGestionEmpleado implements Serializable {

    //*** LLAMADA A LOS ENTERPRICE JAVA BEANS *****
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

    //***** OBJETOS DE LOS ENTIDADES ******
    private int dirDepto;
    private int dirMuni;
    private int parentescoId;
    private String nr = "NR 0111-111111-111-0";
    private int empleadoId = 3; // Agregado por Angela el 01/Junio/2015
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
    private int depto, deptonac;
    private int muni;
    private String fechaNacFamiliaDependiente;
    private int edadFamiliaDependiente;
    private String fechaNacEmpleado;
    private int edadEmpleado;
    private int dirNacionalFiltrarJefe, dependeciasFiltrarJefe;

    public int getDeptonac() {
        return deptonac;
    }

    //****** GET DE ENTERPRICE JAVA BEAN *********
    public void setDeptonac(int deptonac) {
        this.deptonac = deptonac;
    }

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

    //********** GET y SET DE OBJETOS DE ENTIDADES ****
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

    /////agregado por Angela el 01/Junio/2015/////////////
    public int getempleadoId() {
        return empleadoId;
    }

    public void setempleadoId(int empleadoId) {
        this.empleadoId = empleadoId;
    }
////////////////////////////////////////////////////////

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
    
    

    // ********* LLENADO DE SELECIONABLES *****
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
        System.out.println(dirNacionalFiltrarJefe);
        return getDependenciasFacade().buscarDependencias(dirNacionalFiltrarJefe);
    }
    
    public List<Empleados> empleadoJefeDependencia() {
        System.out.println(dependeciasFiltrarJefe);
        return getEmpleadosFacade().buscarEmp(dependeciasFiltrarJefe);
    }
   

    //********* FUNCIONES DE GUARDAR ****** 
    public int edad(String fecha_nac) {     //fecha_nac debe tener el formato dd/MM/yyyy

    Date fechaNac = new Date(fecha_nac);
    Date fechaActual = new Date();
    SimpleDateFormat formato = new SimpleDateFormat("MM/dd/yyyy");
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

}
