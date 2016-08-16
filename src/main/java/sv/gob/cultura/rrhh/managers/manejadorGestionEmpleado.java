package sv.gob.cultura.rrhh.managers;

import sv.gob.cultura.rrhh.entities.DirNacional;
import sv.gob.cultura.rrhh.entities.AdministradoraPensiones;
import sv.gob.cultura.rrhh.entities.Genero;
import sv.gob.cultura.rrhh.entities.ImgDoc;
import sv.gob.cultura.rrhh.entities.Parentesco;
import sv.gob.cultura.rrhh.entities.Paises;
import sv.gob.cultura.rrhh.entities.TipoSangre;
import sv.gob.cultura.rrhh.entities.Estados;
import sv.gob.cultura.rrhh.entities.InstBancaria;
import sv.gob.cultura.rrhh.entities.Deptos;
import sv.gob.cultura.rrhh.entities.Municipios;
import sv.gob.cultura.rrhh.entities.Empleados;
import sv.gob.cultura.rrhh.entities.Dependencias;
import sv.gob.cultura.rrhh.entities.EstadoCivil;
import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.Rectangle;
import java.awt.Color;
import java.io.*;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.*;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import sv.gob.cultura.rrhh.facades.AdministradoraPensionesFacade;
import sv.gob.cultura.rrhh.facades.DependenciasFacade;
import sv.gob.cultura.rrhh.facades.DeptosFacade;
import sv.gob.cultura.rrhh.facades.DirNacionalFacade;
import sv.gob.cultura.rrhh.facades.EmpleadosFacade;
import sv.gob.cultura.rrhh.facades.EstadoCivilFacade;
import sv.gob.cultura.rrhh.facades.EstadosFacade;
import sv.gob.cultura.rrhh.facades.GeneroFacade;
import sv.gob.cultura.rrhh.facades.ImgDocFacade;
import sv.gob.cultura.rrhh.facades.InstBancariaFacade;
import sv.gob.cultura.rrhh.facades.MunicipiosFacade;
import sv.gob.cultura.rrhh.facades.PaisesFacade;
import sv.gob.cultura.rrhh.facades.ParentescoFacade;
import sv.gob.cultura.rrhh.facades.TipoSangreFacade;

@Named(value = "manejadorGestionEmpleado")
@ViewScoped
public class manejadorGestionEmpleado implements Serializable {

    manejadorGestionEmpleado() {
    }

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
    private ImgDocFacade imgDocFacade;
    @EJB
    private GeneroFacade generoFacade;
    private Empleados newEmp = new Empleados(), selectedEmp = new Empleados();
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
    private ImgDoc imagenDocumento = new ImgDoc();
    private final String[] path = new String[10];               // contiene url de documentos
    private final String[] nombreImgDoc = new String[10];       // nombre de archivo
    private final String[] tipoImgDoc = new String[10];         // tipo de archivo
    private final int[] sizeImgDoc = new int[10];               // tamaño de archivo
    private String userx = "userx.png";                         // imagen por defecto en ingreso de newEmp
    private String pathServer;                                  // path de servidor
    private String nombreImagen;                                // nombre de la imgen
    private int dirDepto;                                       // id departamento residencia
    private int dirMuni;                                        // id municipio residencia
    private int parentescoId;                                   // id Parentesco    
    private int empJefe;                                        // id de Jefe de newEmp
    private int depto, deptonac;                                // id´s departamento municipio Nacimiento
    private int muni;                                           // id municipio Nacimiento           
    private int edadEmpleado;                                   // edad de newEmp calculada a partir de fecha
    private int dirNacionalFiltrarJefe;                         // id´s direccion nacional
    private int dependeciasFiltrarJefe;                         // id´s dependencias
    private int dirNacionalNominal;                             // id direcion ncacional para filtrar dependencias
    private int dependenciaNominal;                             // id dependencia Nominal
    private int dirNacionalFuncional;                           // id direcion nacional para filtar dependencias
    private int dependenciaFuncional;                           // Id depenndecia Funcional
    private int estadoEditar;                                   // id de estado newEmp
    private int empleadoEditar;                                 // id newEmp a editar
    private String nombreEmpleadoEditar;                        // nombre de newEmp a editar
    private int opcionsion = 0;
    private int idEmp;
    private Date today = new Date();

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

    public ImgDocFacade getImgDocFacade() {
        return imgDocFacade;
    }

    public GeneroFacade getGeneroFacade() {
        return generoFacade;
    }

    public Empleados getNewEmp() {
        return newEmp;
    }

    public void setNewEmp(Empleados newEmp) {
        this.newEmp = newEmp;
    }

    public Empleados getSelectedEmp() {
        return selectedEmp;
    }

    public void setSelectedEmp(Empleados selectedEmp) {
        this.selectedEmp = selectedEmp;
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

    public void setDeptonac(int deptonac) {
        this.deptonac = deptonac;
    }

    public int getDeptonac() {
        return deptonac;
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

    public String getNombreImagen() {
        return nombreImagen;
    }

    public void setNombreImagen(String nombreImagen) {
        this.nombreImagen = nombreImagen;
    }

    public String getUserx() {
        return userx;
    }

    public void setUserx(String userx) {
        this.userx = userx;
    }

    public String getPathServer() {
        return pathServer;
    }

    public void setPathServer(String pathServer) {
        this.pathServer = pathServer;
    }

    public int getEstadoEditar() {
        return estadoEditar;
    }

    public void setEstadoEditar(int estadoEditar) {
        this.estadoEditar = estadoEditar;
    }

    public int getEmpleadoEditar() {
        return empleadoEditar;
    }

    public void setEmpleadoEditar(int empleadoEditar) {
        this.empleadoEditar = empleadoEditar;
        Empleados emp = getEmpleadosFacade().find(this.empleadoEditar);
        this.setNombreEmpleadoEditar(emp.getNombreEmpleado());
    }

    public String getNombreEmpleadoEditar() {
        return nombreEmpleadoEditar;
    }

    public void setNombreEmpleadoEditar(String nombreEmpleadoEditar) {
        this.nombreEmpleadoEditar = nombreEmpleadoEditar;
    }

    public int getOpcionsion() {
        return opcionsion;
    }

    public void setOpcionsion(int opcionsion) {
        this.opcionsion = opcionsion;
    }

    public int getIdEmp() {
        return idEmp;
    }

    public void setIdEmp(int idEmp) {
        this.idEmp = idEmp;
    }

    public Date getToday() {
        return today;
    }

    public void setToday(Date today) {
        this.today = today;
    }

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
                return getDependenciasFacade().findByDirNac(dirNacionalFiltrarJefe);
            case 2:
                return getDependenciasFacade().findByDirNac(dirNacionalNominal);
            case 3:
                return getDependenciasFacade().findByDirNac(dirNacionalFuncional);
            default:
                return null;
        }
    }

    public List<Empleados> empleadoJefeDependencia() {
        return getEmpleadosFacade().findByDependencia(dependeciasFiltrarJefe);
    }

    public List<Empleados> fetchEmpleados() {
        return getEmpleadosFacade().findAll();
    }

    public List<Genero> todosGeneros() {
        return getGeneroFacade().findAll();
    }

    public void loadEmpleado() {
        try {
            selectedEmp = getEmpleadosFacade().find(idEmp);
            if (selectedEmp.getDeptoNac() != null || selectedEmp.getMunicipioNac() != null) {
                depto = selectedEmp.getDeptoNac();
                muni = selectedEmp.getMunicipioNac();
            } else {
                depto = muni = 0;
            }
            if (selectedEmp.getDeptoResidencia() != null || selectedEmp.getMunicipioRes() != null) {
                dirDepto = selectedEmp.getDeptoResidencia();
                dirMuni = selectedEmp.getMunicipioRes();
            } else {
                dirDepto = dirMuni = 0;
            }
            dirNacionalNominal = selectedEmp.getIdDependenciaN().getIdDirNac().getIdDirNac();
            dirNacionalFuncional = selectedEmp.getIdDependenciaF().getIdDirNac().getIdDirNac();
            dependenciaNominal = selectedEmp.getIdDependenciaN().getIdDependencia();
            dependenciaFuncional = selectedEmp.getIdDependenciaF().getIdDependencia();
            if (selectedEmp.getJefe().equals("SI")) {
                opcionsion = 1;
                dirNacionalFiltrarJefe = selectedEmp.getIdDependenciaF().getIdDirNac().getIdDirNac();
                dependeciasFiltrarJefe = selectedEmp.getIdDependenciaF().getIdDependencia();
            } else {
                opcionsion = 2;
                dirNacionalFiltrarJefe = dependeciasFiltrarJefe = 0;
            }

        } catch (Exception e) {
            addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "No se ha podido encontrar el registro solicitado.", ""));
        }
    }

    public String guardarEmpleado() {
        try {
            if (this.getDepto() != 0) {
                newEmp.setDeptoNac(this.getDepto());
            }
            if (this.getMuni() != 0) {
                newEmp.setMunicipioNac(this.getMuni());
            }
            if (this.getDirDepto() != 0) {
                newEmp.setDeptoResidencia(this.getDirDepto());
            }
            if (this.getDirMuni() != 0) {
                newEmp.setMunicipioRes(this.getDirMuni());
            }
            if (newEmp.getIdEmpleadoJefe() != null) {
                newEmp.setJefe("SI");
            } else {
                newEmp.setJefe("NO");
            }
            if (this.getDependenciaNominal() != 0) {
                newEmp.setIdDependenciaN(new Dependencias(this.getDependenciaNominal()));
            }
            if (this.getDependenciaFuncional() != 0) {
                newEmp.setIdDependenciaF(new Dependencias(this.getDependenciaFuncional()));
            }
            if (this.path[0] != null) {
                newEmp.setUrlFotoEmp(this.path[0]);
            }
            if (this.path[1] != null) {
                newEmp.setCurriculum(this.path[1]);
            }
            if (this.path[2] != null) {
                newEmp.setDocDescPuesto(this.path[2]);
            }
            Dependencias depenNominal = getDependenciasFacade().find(this.getDependenciaNominal());
            Dependencias depenFuncional = getDependenciasFacade().find(this.getDependenciaFuncional());
            newEmp.setIdMunicipioN(depenNominal.getIdMunicipio());
            newEmp.setIdMunicipioF(depenFuncional.getIdMunicipio());
            newEmp.setFechaCreaEmp(new Date());
            //Temporal user assigned
            newEmp.setUserCreaEmp(1);
            getEmpleadosFacade().create(newEmp);
            newEmp = new Empleados();
            restaurarSelecionables();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Ingresado", "Registro Ingresado");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return "gestion_empleados";
        } catch (Exception e) {
            return "reg_empleados";
        }
    }

    public String editEmpleado() {
        try {
            if (this.getDepto() != 0) {
                selectedEmp.setDeptoNac(this.getDepto());
            }
            if (this.getMuni() != 0) {
                selectedEmp.setMunicipioNac(this.getMuni());
            }
            if (this.getDirDepto() != 0) {
                selectedEmp.setDeptoResidencia(this.getDirDepto());
            }
            if (this.getDirMuni() != 0) {
                selectedEmp.setMunicipioRes(this.getDirMuni());
            }
            if (selectedEmp.getIdEmpleadoJefe() != null) {
                selectedEmp.setJefe("SI");
            } else {
                selectedEmp.setJefe("NO");
            }
            if (this.getDependenciaNominal() != 0) {
                selectedEmp.setIdDependenciaN(new Dependencias(this.getDependenciaNominal()));
            }
            if (this.getDependenciaFuncional() != 0) {
                selectedEmp.setIdDependenciaF(new Dependencias(this.getDependenciaFuncional()));
            }
            if (this.path[0] != null) {
                selectedEmp.setUrlFotoEmp(this.path[0]);
            }
            if (this.path[1] != null) {
                selectedEmp.setCurriculum(this.path[1]);
            }
            if (this.path[2] != null) {
                selectedEmp.setDocDescPuesto(this.path[2]);
            }

            Dependencias depenNominal = getDependenciasFacade().find(this.getDependenciaNominal());
            Dependencias depenFuncional = getDependenciasFacade().find(this.getDependenciaFuncional());
            selectedEmp.setIdMunicipioN(depenNominal.getIdMunicipio());
            selectedEmp.setIdMunicipioF(depenFuncional.getIdMunicipio());

            selectedEmp.setFechaModEmp(new Date());
            getEmpleadosFacade().edit(selectedEmp);

            selectedEmp = new Empleados();
            restaurarSelecionables();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Modificado", "Registro Modificado");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return "gestion_empleados";
        } catch (Exception e) {
            return "gestion_empleados";
        }
    }

    public void resetBossFields() {
        if (opcionsion != 1) {
            dirNacionalFiltrarJefe = dependeciasFiltrarJefe = 0;
        }
    }

    public void cambiarEstado() {
        try {
            selectedEmp.setFechaModEmp(new Date());
            selectedEmp.setUserModEmp(1);
            getEmpleadosFacade().edit(selectedEmp);
            RequestContext.getCurrentInstance().execute("PF('estadoEditar').hide()");
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Modificado", "Registro Modificado");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public void calculateEdadEditEmp() {
        Date fechaNac = selectedEmp.getFechaNac();
        Calendar dob = Calendar.getInstance();
        dob.setTime(fechaNac);
        Calendar now = Calendar.getInstance();
        int age = now.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
        if (now.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)) {
            age--;
        }
        selectedEmp.setEdadEmp(age);
    }

    public void calculateEdadCreateEmp() {
        Date fechaNac = newEmp.getFechaNac();
        Calendar dob = Calendar.getInstance();
        dob.setTime(fechaNac);
        Calendar now = Calendar.getInstance();
        int age = now.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
        if (now.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)) {
            age--;
        }
        newEmp.setEdadEmp(age);
    }

    public void handleFileUpload(FileUploadEvent event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();
        File result = new File(externalContext.getRealPath("/upload/") + File.separator + event.getFile().getFileName());
        int tipoDoc = Integer.parseInt((String) event.getComponent().getAttributes().get("tipoDoc"));
        int var = 0;
        switch (tipoDoc) {
            case 1:
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
        } catch (IOException e) {
        }
    }

    private StreamedContent graphicImage;

    public void prepararImagen() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        this.setPathServer(externalContext.getRealPath("/upload/") + "\\");

        String pathImagen = this.getPathServer() + this.getUserx();

        if (path[0] != null) {
            pathImagen = path[0];
        }

        try {
            graphicImage = new DefaultStreamedContent(new FileInputStream(pathImagen), "image/png");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(manejadorGestionEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public StreamedContent getGraphicImage() {
        prepararImagen();
        return graphicImage;
    }

    public void setGraphicImage(StreamedContent graphicImage) {
        this.graphicImage = graphicImage;
    }

    public void restaurarSelecionables() {
        this.setDepto(0);
        this.setMuni(0);
        this.setDirDepto(0);
        this.setDirMuni(0);
        this.setEdadEmpleado(0);
        this.setEmpJefe(0);
        this.setDependenciaNominal(0);
        this.setDependenciaFuncional(0);
        this.setDirNacionalFiltrarJefe(0);
        this.setDependeciasFiltrarJefe(0);
        this.setOpcionsion(0);
        this.path[0] = null;
        this.path[1] = null;
        this.path[2] = null;
    }

    public String editarEmpleado() {
        DirNacional dirNominal = newEmp.getIdDependenciaN().getIdDirNac();
        DirNacional dirFuncional = newEmp.getIdDependenciaF().getIdDirNac();
        Dependencias dependenciaN = newEmp.getIdDependenciaN();
        Dependencias dependenciaF = newEmp.getIdDependenciaF();
        this.setDirNacionalNominal(dirNominal.getIdDirNac());
        this.setDirNacionalFuncional(dirFuncional.getIdDirNac());
        this.setDependenciaNominal(dependenciaN.getIdDependencia());
        this.setDependenciaFuncional(dependenciaF.getIdDependencia());
        String a = newEmp.getJefe();
        if (a.equals("SI")) {
            Integer idEmp = newEmp.getIdEmpleadoJefe().getIdEmpleado();
            Empleados emp = getEmpleadosFacade().find(idEmp);
            this.setOpcionsion(1);
            this.setEmpJefe(emp.getIdEmpleado());
            this.setDependeciasFiltrarJefe(emp.getIdDependenciaF().getIdDependencia());
            this.setDirNacionalFiltrarJefe(emp.getIdDependenciaF().getIdDirNac().getIdDirNac());
        } else {
            this.setOpcionsion(2);
        }
        if (newEmp.getDeptoNac() != null) {
            this.setDepto(newEmp.getDeptoNac());
        }
        if (newEmp.getMunicipioNac() != null) {
            this.setMuni(newEmp.getMunicipioNac());
        }
        if (newEmp.getDeptoResidencia() != null) {
            this.setDirDepto(newEmp.getDeptoResidencia());
        }
        if (newEmp.getMunicipioRes() != null) {
            this.setDirMuni((int) newEmp.getMunicipioRes());
        }
        return "editar_empleados";
    }

    public void refresh() {
        newEmp = new Empleados();
        restaurarSelecionables();
    }

    public String nuevoEmpleado() {
        newEmp = new Empleados();
        restaurarSelecionables();
        return "reg_empleados";
    }

    public void preProcessPDF(Object document) throws IOException, DocumentException {
        final Document pdf = (Document) document;
        pdf.setPageSize(PageSize.LETTER.rotate());
        pdf.setMargins(10, 10, 10, 10);
        pdf.open();
        PdfPTable pdfTable = new PdfPTable(1);
        PdfPCell celda = new PdfPCell(getImage("resources/images/logo2_peq2.png"));
        celda.setBorder(Rectangle.NO_BORDER);
        celda.setBackgroundColor(new Color(255, 255, 45));
        pdfTable.addCell(celda);
        pdfTable.setWidthPercentage(15f);
        pdfTable.setHorizontalAlignment(Element.ALIGN_RIGHT);
        pdf.add(pdfTable);
        pdf.add(new Paragraph("RRHH - Secretaría de Cultura de la Presidencia"));
        DateFormat formateer = new SimpleDateFormat("dd/MM/yy '-' hh:mm:ss");
        Date currentDate = new Date();
        String date = formateer.format(currentDate);
        pdf.add(new Paragraph("Fecha de Generación: " + date));
        pdf.add(new Paragraph("\n"));
        Paragraph nombre_rep = new Paragraph("Reporte por Género, Dirección Nacional, Dependencia y Ubicación",
                FontFactory.getFont("garamond",
                        12,
                        Font.BOLD,
                        Color.getHSBColor(0.6f, 0.9f, 0.7f)
                ));
        nombre_rep.setSpacingAfter(25);
        nombre_rep.setSpacingBefore(25);
        nombre_rep.setAlignment(Element.ALIGN_CENTER);
        pdf.add(nombre_rep);
    }

    public void postProcessPDF(Object document) throws IOException, DocumentException {
        final Document pdf = (Document) document;
        pdf.setPageSize(PageSize.LETTER.rotate());

    }

    private Image getImage(String imageName) throws IOException, BadElementException {
        final Image image = Image.getInstance(getAbsolutePath(imageName));
        //image.scalePercent(90f);
        image.scalePercent(100f);
        return image;
    }

    private String getAbsolutePath(String imageName) {
        final ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        final StringBuilder logo = new StringBuilder().append(servletContext.getRealPath(""));
        logo.append(File.separator).append(imageName);
        return logo.toString();
    }

    public void postProcessXLS(Object document) {
        HSSFWorkbook wb = (HSSFWorkbook) document;
        HSSFSheet sheet = wb.getSheetAt(0);
        HSSFRow header = sheet.getRow(0);
        HSSFCellStyle cellStyle = wb.createCellStyle();
        HSSFFont hSSFFont = wb.createFont();
        cellStyle.setFillForegroundColor(HSSFColor.BLUE_GREY.index);

        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        hSSFFont.setColor(HSSFColor.RED.index);
    }

    public void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
