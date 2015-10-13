package sv.gob.cultura.rrhh.manejadores;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfTemplate;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.ExceptionConverter;
import com.lowagie.text.Header;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.pdf.ColumnText;

import java.awt.Color;
import java.io.*;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.*;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.jws.soap.SOAPBinding.Style;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.swing.GroupLayout;
import static javax.swing.text.StyleConstants.FontFamily;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.primefaces.context.RequestContext;
import sv.gob.cultura.rrhh.entidades.*;
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
import sv.gob.cultura.rrhh.facades.ImgDocFacade;
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
@SessionScoped
public class manejadorGestionEmpleado implements Serializable {

    manejadorGestionEmpleado() {
    }
//******************************************************************************
// ************** LLAMADA A LOS ENTERPRICE JAVA BEANS **************************
//******************************************************************************
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
//******************************************************************************
//*********************** OBJETOS DE LOS ENTIDADES *****************************
//******************************************************************************

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
    private ImgDoc imagenDocumento = new ImgDoc();

//******************************************************************************
//****** VARIABLES QUE CONTRENDRAN IDÂ´S O STRING DE FORMULARIOS ****************
//******************************************************************************
    private final String[] path = new String[10];               // contiene url de documentos
    private final String[] nombreImgDoc = new String[10];       // nombre de archivo
    private final String[] tipoImgDoc = new String[10];         // tipo de archivo
    private final int[] sizeImgDoc = new int[10];               // tamaño de archivo
    private String userx = "userx.png";                         // imagen por defecto en ingreso de empleado
    private String pathServer;                                  // path de servidor
    private String nombreImagen;                                // nombre de la imgen
    private Date fechaNacEmpleado = new Date();                 // fehca nacimiento empleado
    private int dirDepto;                                       // id departamento residencia
    private int dirMuni;                                        // id municipio residencia
    private int parentescoId;                                   // id Parentesco    
    private int empJefe;                                        // id de Jefe de empleado
    private int depto, deptonac;                                // id´s departamento municipio Nacimiento
    private int muni;                                           // id municipio Nacimiento           
    private int edadEmpleado;                                   // edad de empleado calculada a partir de fecha
    private int dirNacionalFiltrarJefe;                         // id´s direccion nacional
    private int dependeciasFiltrarJefe;                         // id´s dependencias
    private int dirNacionalNominal;                             // id direcion ncacional para filtrar dependencias
    private int dependenciaNominal;                             // id dependencia Nominal
    private int dirNacionalFuncional;                           // id direcion nacional para filtar dependencias
    private int dependenciaFuncional;                           // Id depenndecia Funcional
    private int estadoEditar;                                   // id de estado empleado
    private int empleadoEditar;                                 // id empleado a editar
    private String nombreEmpleadoEditar;                        // nombre de empleado a editar
    private int opcionsion = 0;
// *****************************************************************************
//********************** GET DE ENTERPRICE JAVA BEAN ***************************
//******************************************************************************

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

// *****************************************************************************
//******************* GET y SET DE OBJETOS DE ENTIDADES ************************
//******************************************************************************
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

    public List<Empleados> todosEmpleados() {
        return getEmpleadosFacade().findAll();
    }

//******************************************************************************
//*************************** FUNCIONES DE GUARDAR *****************************
//******************************************************************************
    public String guardarEmpleado() {
        try {

            //Seteo de datos de selecionables
            if (this.getDepto() != 0) {
                empleado.setDeptoNac(this.getDepto());
            }
            if (this.getMuni() != 0) {
                empleado.setMunicipioNac(this.getMuni());
            }
            if (this.getDirDepto() != 0) {
                empleado.setDeptoResidencia(this.getDirDepto());
            }
            if (this.getDirMuni() != 0) {
                empleado.setMunicipioRes(this.getDirMuni());
            }
            if (this.getFechaNacEmpleado() != new Date()) {
                empleado.setFechaNac(this.getFechaNacEmpleado());
            }
            if (this.getEdadEmpleado() != 0) {
                empleado.setEdadEmp(this.getEdadEmpleado());
            }
            if (this.getEmpJefe() != 0) {
                empleado.setIdEmpleadoJefe(new Empleados(this.getEmpJefe()));
                empleado.setJefe("SI");
            } else {
                empleado.setJefe("NO");
            }
            if (this.getDependenciaNominal() != 0) {
                empleado.setIdDependenciaN(new Dependencias(this.getDependenciaNominal()));
            }
            if (this.getDependenciaFuncional() != 0) {
                empleado.setIdDependenciaF(new Dependencias(this.getDependenciaFuncional()));
            }

            //Seteo de las url de foto, curriculum y doc descriptor de puesto
            if (this.path[0] != null) {
                empleado.setUrlFotoEmp(this.path[0]);
            }
            if (this.path[1] != null) {
                empleado.setCurriculum(this.path[1]);
            }
            if (this.path[2] != null) {
                empleado.setDocDescPuesto(this.path[2]);
            }

            //Seteo de Dependencias nominal y funcional
            Dependencias depenNominal = getDependenciasFacade().find(this.getDependenciaNominal());
            Dependencias depenFuncional = getDependenciasFacade().find(this.getDependenciaFuncional());
            empleado.setIdMunicipioN(depenNominal.getIdMunicipio());
            empleado.setIdMunicipioF(depenFuncional.getIdMunicipio());

            //Fecha de creacion y usuario id =1
            empleado.setFechaCreaEmp(new Date());
            empleado.setUserCreaEmp(1);

            getEmpleadosFacade().create(empleado);

            empleado = new Empleados();
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
            //Seteo de datos de selecionables
            if (this.getDepto() != 0) {
                empleado.setDeptoNac(this.getDepto());
            }
            if (this.getMuni() != 0) {
                empleado.setMunicipioNac(this.getMuni());
            }
            if (this.getDirDepto() != 0) {
                empleado.setDeptoResidencia(this.getDirDepto());
            }
            if (this.getDirMuni() != 0) {
                empleado.setMunicipioRes(this.getDirMuni());
            }
            if (this.getFechaNacEmpleado() != new Date()) {
                empleado.setFechaNac(this.getFechaNacEmpleado());
            }
            if (this.getEdadEmpleado() != 0) {
                empleado.setEdadEmp(this.getEdadEmpleado());
            }
            if (this.getEmpJefe() != 0) {
                empleado.setIdEmpleadoJefe(new Empleados(this.getEmpJefe()));
                empleado.setJefe("SI");
            } else {
                empleado.setJefe("NO");
            }
            if (this.getDependenciaNominal() != 0) {
                empleado.setIdDependenciaN(new Dependencias(this.getDependenciaNominal()));
            }
            if (this.getDependenciaFuncional() != 0) {
                empleado.setIdDependenciaF(new Dependencias(this.getDependenciaFuncional()));
            }

            //Seteo de las url de foto, curriculum y doc descriptor de puesto
            if (this.path[0] != null) {
                empleado.setUrlFotoEmp(this.path[0]);
            }
            if (this.path[1] != null) {
                empleado.setCurriculum(this.path[1]);
            }
            if (this.path[2] != null) {
                empleado.setDocDescPuesto(this.path[2]);
            }

            //Seteo de Dependencias nominal y funcional
            Dependencias depenNominal = getDependenciasFacade().find(this.getDependenciaNominal());
            Dependencias depenFuncional = getDependenciasFacade().find(this.getDependenciaFuncional());
            empleado.setIdMunicipioN(depenNominal.getIdMunicipio());
            empleado.setIdMunicipioF(depenFuncional.getIdMunicipio());

            empleado.setFechaModEmp(new Date());
            //empleado.setUserModEmp(1); ESTA COMO DATE
            getEmpleadosFacade().edit(empleado);

            empleado = new Empleados();
            restaurarSelecionables();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Modificado", "Registro Modificado");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return "gestion_empleados";
        } catch (Exception e) {
            return "gestion_empleados";
        }
    }

    public void cambiarEstado() {
        try {
            Empleados emp = getEmpleadosFacade().find(this.getEmpleadoEditar());
            emp.setIdEstado(new Estados(this.getEstadoEditar()));
            emp.setFechaModEmp(new Date());
            emp.setUserModEmp(1);
            getEmpleadosFacade().edit(emp);
            RequestContext.getCurrentInstance().execute("PF('estadoEditar').hide()");
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Modificado", "Registro Modificado");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } catch (Exception e) {

        }
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

    //Archivos al servidor
    public void handleFileUpload(FileUploadEvent event) {

        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();
        File result = new File(externalContext.getRealPath("/upload/") + File.separator + event.getFile().getFileName());

        int tipoDoc = Integer.parseInt((String) event.getComponent().getAttributes().get("tipoDoc"));
        int var = 0;
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

    //Para mostrar imagenes
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

    //restaura todos los selecionables de los formularios
    public void restaurarSelecionables() {
        this.setDepto(0);
        this.setMuni(0);
        this.setDirDepto(0);
        this.setDirMuni(0);
        this.setFechaNacEmpleado(new Date());
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

    //setea valores en los inputs para ser editados
    public String editarEmpleado() {
        DirNacional dirNominal = empleado.getIdDependenciaN().getIdDirNac();
        DirNacional dirFuncional = empleado.getIdDependenciaF().getIdDirNac();
        Dependencias dependenciaN = empleado.getIdDependenciaN();
        Dependencias dependenciaF = empleado.getIdDependenciaF();
        this.setDirNacionalNominal(dirNominal.getIdDirNac());
        this.setDirNacionalFuncional(dirFuncional.getIdDirNac());
        this.setDependenciaNominal(dependenciaN.getIdDependencia());
        this.setDependenciaFuncional(dependenciaF.getIdDependencia());

        String a = empleado.getJefe();
        if (a.equals("SI")) {
            Integer idEmp = empleado.getIdEmpleadoJefe().getIdEmpleado();
            Empleados emp = getEmpleadosFacade().find(idEmp);

            this.setOpcionsion(1);
            this.setEmpJefe(emp.getIdEmpleado());
            this.setDependeciasFiltrarJefe(emp.getIdDependenciaF().getIdDependencia());
            this.setDirNacionalFiltrarJefe(emp.getIdDependenciaF().getIdDirNac().getIdDirNac());
        } else {
            this.setOpcionsion(2);
        }

        if (empleado.getDeptoNac() != null) {
            this.setDepto(empleado.getDeptoNac());
        }
        if (empleado.getMunicipioNac() != null) {
            this.setMuni(empleado.getMunicipioNac());
        }
        if (empleado.getDeptoResidencia() != null) {
            this.setDirDepto(empleado.getDeptoResidencia());
        }
        if (empleado.getMunicipioRes() != null) {
            this.setDirMuni((int) empleado.getMunicipioRes());
        }

        return "editar_empleados";
    }

    public void refresh() {
        empleado = new Empleados();
        restaurarSelecionables();
    }

    public String nuevoEmpleado() {
        empleado = new Empleados();
        restaurarSelecionables();
        return "reg_empleados";
    }
//******************************************************************************
// *****************************************************************************

    /////agregado por Angela el 06/octubre/2015
    ////PARA EXPORTAR ARCHIVOS A PDF
//    public void preProcessPDF(Object document) throws IOException,
//            BadElementException, DocumentException {
//        Document pdf = (Document) document;
//        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
//        String logo = servletContext.getRealPath("") + File.separator + "resources/images"
//                + File.separator + "logo2_peq2.png";
//        pdf.add(Image.getInstance(logo));
//    }
//////////////////Agregado para header-encabezado ////////////////////////////////// 
    /**
     * Clase que maneja los eventos de pagina necesarios para agregar un
     * encabezado y conteo de paginas a un documento. El encabezado, definido en
     * onEndPage, consiste en una tabla con 3 celdas que contienen: Frase del
     * encabezado | pagina <numero de pagina> de | total de paginas, con una
     * linea horizontal separando el encabezado del texto
     *
     * Referencia: http://itextpdf.com/examples/iia.php?id=104
     *
     *
     */
//    public class Cabecera extends PdfPageEventHelper {
//    private String encabezado;
//    PdfTemplate total;
//    
//    /**
//     * Crea el objecto PdfTemplate el cual contiene el numero total de
//     * paginas en el documento
//     */
//    public void onOpenDocument(PdfWriter writer, Document document) {
//        total = writer.getDirectContent().createTemplate(30, 16);
//    }
//    
//    /**
//     * Esta es el metodo a llamar cuando ocurra el evento <b>onEndPage</b>, es en este evento
//     * donde crearemos el encabeazado de la pagina con los elementos indicados.
//     */
//    public void onEndPage(PdfWriter writer, Document document) {
//        PdfPTable table = new PdfPTable(3);
//        try {
//            // Se determina el ancho y altura de la tabla
//            table.setWidths(new int[]{24, 24, 2});
//            table.setTotalWidth(527);
//            table.setLockedWidth(true);
//            table.getDefaultCell().setFixedHeight(20);
//            
//            // Borde de la celda
//            table.getDefaultCell().setBorder(Rectangle.BOTTOM);
//            
//            table.addCell(encabezado);
//            table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
//            
//            table.addCell(String.format("Pagina %010d de", writer.getPageNumber()));
//            
//            PdfPCell cell = new PdfPCell(Image.getInstance(total));
//            
//            cell.setBorder(Rectangle.BOTTOM);
//            
//            table.addCell(cell);
//            // Esta linea escribe la tabla como encabezado
//            table.writeSelectedRows(0, -1, 34, 803, writer.getDirectContent());
//        }
//        catch(DocumentException de) {
//            throw new ExceptionConverter(de);
//        }
//    }
//    
//    
//    /**
//     * Realiza el conteo de paginas al momento de cerrar el documento
//     */
//    public void onCloseDocument(PdfWriter writer, Document document) {
//        ColumnText.showTextAligned(total, Element.ALIGN_LEFT, new Phrase(String.valueOf(writer.getPageNumber()-1)),2,2,0);
//    }    
//    
//    // Getter and Setters
//    
//    public String getEncabezado() {
//        return encabezado;
//    }
//    public void setEncabezado(String encabezado) {
//        this.encabezado = encabezado;
//    }
//}    
/////////////////////////////////////////////////// , String Documento
    public void preProcessPDF(Object document) throws IOException, DocumentException {
        final Document pdf = (Document) document;

        pdf.setPageSize(PageSize.LETTER.rotate());
        pdf.setMargins(10, 10, 10, 10); //para margenes de la página

//////////////agregado para encabezado el 08/10/2015///   
///////////////////////////////////////////////////////////////
        pdf.open();

//		PdfPTable pdfTable = new PdfPTable(2); //para dos columnas o celdas en la tabla que se está creando
//		pdfTable.addCell(getImage("resources/images/logo2_peq2.png"));
        //obtenemos una instacia de un objeto PdfPTable y asignamos 1 columna
        PdfPTable pdfTable = new PdfPTable(1);
        PdfPCell celda = new PdfPCell(getImage("resources/images/logo2_peq2.png")); //se crea la celda con el contenido que llevará
        celda.setBorder(Rectangle.NO_BORDER);                                       //celda creada sin bordes
        celda.setBackgroundColor(new Color(255, 255, 45));                          //celda creada sin color
        pdfTable.addCell(celda);
        //agregamos las celdas
//        pdfTable.addCell(""); //columna1
//        pdfTable.addCell(""); //columna2
        //pdfTable.addCell(getImage("resources/images/logo2_peq2.png"));      //contenido de la columna o celda

        //EJEMPLO CON UN CELDA CON BORDE
//        PdfPCell cellTwo = new PdfPCell(new Phrase("otra celda con rectangle box"));
//        cellTwo.setBorder(Rectangle.BOX);
//        pdfTable.addCell(cellTwo);
        pdfTable.setWidthPercentage(15f);                                   //ancho de la tabla al 30% de la pagina
        //pdfTable.setHorizontalAlignment(0);
        pdfTable.setHorizontalAlignment(Element.ALIGN_RIGHT);               //alineación de la tabla a la derecha

// agregamos la tabla al documento
        pdf.add(pdfTable);

        //agregado por mi
        pdf.add(new Paragraph("RRHH - Secretaría de Cultura de la Presidencia"));
        DateFormat formateer = new SimpleDateFormat("dd/MM/yy '-' hh:mm:ss");
        Date currentDate = new Date();
        String date = formateer.format(currentDate);
        pdf.add(new Paragraph("Fecha de Generación: " + date));
        pdf.add(new Paragraph("\n"));
//        pdf.add(new Paragraph("Reporte por Género, Dirección Nacional, Dependencia y Ubicación", 
//        FontFactory.getFont("garamond", //fuente
//                        12, //tamaño
//                        Font.BOLD, //estilo
//                        Color.DARK_GRAY //color de la letra
//                        )));
//        pdf.add(new Paragraph("\n"));

        Paragraph nombre_rep = new Paragraph("Reporte por Género, Dirección Nacional, Dependencia y Ubicación",
                FontFactory.getFont("garamond", //fuente
                        12, //tamaño
                        Font.BOLD, //estilo
                        //Color.DARK_GRAY //
                        //Color.getHSBColor(0.7f, 0.9f, 0f) //color de la letra Color.getHSBColor(4, 72, 139)-- HSB (Hue, Saturation, Brightness – Matiz, Saturación, Brillo)
                        Color.getHSBColor(0.6f, 0.9f, 0.7f)
                ));
        nombre_rep.setSpacingAfter(25);
        nombre_rep.setSpacingBefore(25);
        nombre_rep.setAlignment(Element.ALIGN_CENTER);
//                nombre_rep.setIndentationLeft(50);
//                nombre_rep.setIndentationRight(50);
        pdf.add(nombre_rep);

///fin de agregado por mi  
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
        //logo.append(File.separator).append(IMAGE_FOLDER);
        //logo.append(File.separator).append(LOGOS_FOLDER);
        logo.append(File.separator).append(imageName);
        return logo.toString();
    }
//////////////////TERMINA CODIGO PARA EXPORTAR A PDF /////////////////////////////

///////////PARA EXPORTAR ARCHIVOS A EXCEL///////////////////////////////////////
    public void postProcessXLS(Object document) {
        HSSFWorkbook wb = (HSSFWorkbook) document;
        HSSFSheet sheet = wb.getSheetAt(0);
        HSSFRow header = sheet.getRow(0);
        HSSFCellStyle cellStyle = wb.createCellStyle();
        HSSFFont hSSFFont = wb.createFont();
        cellStyle.setFillForegroundColor(HSSFColor.BLUE_GREY.index);
        //cellStyle.setFillBackgroundColor(new HSSFColor.BLACK().getIndex()); //probar esto para fondo negro

        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        hSSFFont.setColor(HSSFColor.RED.index);
//        hSSFFont.setColor(HSSFColor.WHITE.index); //probar para color de fuente blanca http://datojava.blogspot.com/2014/09/reporte-excel-con-java.html
//        for (int i = 0; i < header.getPhysicalNumberOfCells(); i++) {
//            header.getCell(i).setCellStyle(cellStyle);
//        }
    }
//******************************************************************************
// *****************************************************************************    
}
