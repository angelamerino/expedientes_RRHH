/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.cultura.rrhh.manejadores;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.context.RequestContext;
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
    private int anio;                                               // Año de Estudios
    private Date anioedit;                                          // fecha del para estudios solo se necesita año
    private final String[] path = new String[10];                   // contiene url de documentos
    private final String[] nombreImgDoc = new String[10];           // nombre de documento
    private final String[] tipoImgDoc = new String[10];             // tipo de documento
    private final int[] sizeImgDoc = new int[10];                   // tamaño de archivo
    private int idiomaSelecionado;                                  // id de idioma selecinado
    private String instIdioma;                                      // Nombre de la institucion donde se curso un idioma
    private String idiomaSelecionadoNombre;                         // Nombre del idioma selecionado
    private int idCaracteristicaIdioma;                             // Id Caracteristica de idioma
    private int direccionNacional;                                  // id de dirección nacional para filtrar dependencias
    private int dependecia;                                         // id dependencias para filtrar empleado
    private int empleadoSelecionado;                                // id de empleado seleccinado
    private String nombreEmp;                                       // Nombre de empleado seleccionado
    private String NR;                                              // NR para busqueda de empleado
    
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
    }

    public String getIdiomaSelecionadoNombre() {
        return idiomaSelecionadoNombre;
    }

    public void setIdiomaSelecionadoNombre(String idiomaSelecionadoNombre) {
        this.idiomaSelecionadoNombre = idiomaSelecionadoNombre;
    }

    public Date getAnioedit() {
        return anioedit;
    }

    public void setAnioedit(Date anioedit) {
        this.anioedit = anioedit;
        this.setAnio(obtenerAnio(anioedit));        
    }

    public String getNR() {
        return NR;
    }

    public void setNR(String NR) {
        this.NR = NR;
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
            return getIdiomasCaracteristicasFacade().buscarIdiomasId(this.idiomas.getIdIdioma(), this.getEmpleadoSelecionado());
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
        
        //Fecaha de Creacion y Usuario id =1
        estudiosEmp.setFechaCreaEstudios(new Date());
        estudiosEmp.setUserCreaEstudios(1);
        
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
        this.setAnio(0);
        imagenDocumento = new ImgDoc();
    }
    
    public void editarEstudio(){
        //Fecaha de Creacion y Usuario id =1
        estudiosEmp.setFechaModEstudios(new Date());
        estudiosEmp.setUserModEstudios(1);
        estudiosEmp.setAnioEstudio(fechaAnio(anio));
        getEstudiosEmpFacade().edit(estudiosEmp);
        estudiosEmp = new EstudiosEmp();
        this.setAnio(0);
    }

    public void guardarEstudioNoFormal() {
        // Igual a Estudio Formal
        estudiosEmp.setTipoEstudio("No Formal");
        estudiosEmp.setAnioEstudio(fechaAnio(anio));
        
        //Fecaha de Creacion y Usuario id =1
        estudiosEmp.setFechaCreaEstudios(new Date());
        estudiosEmp.setUserCreaEstudios(1);
        
        getEstudiosEmpFacade().create(estudiosEmp);
        Empleados empEst = getEmpleadosFacade().find(this.getEmpleadoSelecionado());
        List<EstudiosEmp> estudiosIngresados = empEst.getEstudiosEmpList();
        estudiosIngresados.add(estudiosEmp);

        if (this.path[3] != null) {
            imagenDocumento.setIdEstudio(estudiosEmp);
            imagenDocumento.setIdEmpleado(empEst);
            imagenDocumento.setRefImgDoc(empEst.getNrEmpleado());
            imagenDocumento.setNombreArchivo(this.nombreImgDoc[4]);
            imagenDocumento.setSizeArchivo(this.sizeImgDoc[4]);
            imagenDocumento.setDescripcion("Documento de Estudio No Formal");
            imagenDocumento.setRutaArchivo(this.path[4]);
            imagenDocumento.setTipoArchivo(this.tipoImgDoc[4]);
            getImgDocFacade().create(imagenDocumento);
        }

        empEst.setEstudiosEmpList(estudiosIngresados);
        getEmpleadosFacade().edit(empEst);
        estudiosEmp = new EstudiosEmp();
        this.setAnio(0);
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

        boolean existe = false;
        List<IdiomasCaracteristicas> todosIdiomasId = todosIdiomasId();
        for (IdiomasCaracteristicas caracteristica : todosIdiomasId) {
            if (caracteristica.getIdiomas().equals(this.getIdiomas())) {
                if (caracteristica.getCaracteristicasIdioma().equals(new CaracteristicasIdioma(this.getIdCaracteristicaIdioma()))) {
                    existe = true;
                    System.out.println("Ya Existe un registro de esa caracteristica para este idioma");
                }
            }
        }

        if (existe==false) {
            //Setea IdIdiomasCaracteristicasPK y Id Idiomas a IdiomasCaracteristicas
            idiomasCaracteristicas.setInstitucionIdioma(this.getInstIdioma());
            idiomasCaracteristicas.setIdiomasCaracteristicasPK(idiomasCaracteristicasPK);
            idiomasCaracteristicas.setIdiomas(new Idiomas(this.getIdiomas().getIdIdioma()));
            idiomasCaracteristicas.setCaracteristicasIdioma(new CaracteristicasIdioma(getIdCaracteristicaIdioma()));
            idiomasCaracteristicas.setIdEmpleado(this.empleadoSelecionado);
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

    }

    public String eliminarEstudio(EstudiosEmp estudio) {
        Empleados emp = getEmpleadosFacade().find(this.getEmpleadoSelecionado());
        List<EstudiosEmp> estudiosEmpList = emp.getEstudiosEmpList();
        estudiosEmpList.remove(estudio);
        emp.setEstudiosEmpList(estudiosEmpList);
        getEmpleadosFacade().edit(emp);
        getEstudiosEmpFacade().remove(estudio);
        return null;
    }

    public String eliminarCaracteristica(IdiomasCaracteristicas caracteristicas) {
        getIdiomasCaracteristicasFacade().remove(caracteristicas);
        return null;
    }

    public String eliminarIdioma(Idiomas idioma) {

        //Eliminar en cascada Obtenemos las caracteriticas del idioma a elimnar
        List<IdiomasCaracteristicas> todosIdiomasId = todosIdiomasId();
        //Elimina cada una de las caracteristicas
        Iterator<IdiomasCaracteristicas> nombreIterator = todosIdiomasId.iterator();
        while (nombreIterator.hasNext()) {
            IdiomasCaracteristicas elemento = nombreIterator.next();
            getIdiomasCaracteristicasFacade().remove(elemento);
        }

        //Elimina el idioma de la lista de idiomas.Emplado
        Empleados empIdioma = getEmpleadosFacade().find(this.getEmpleadoSelecionado());
        List<Idiomas> IdiomasIngresados = empIdioma.getIdiomasList();

        IdiomasIngresados.remove(idioma);
        empIdioma.setIdiomasList(IdiomasIngresados);
        getEmpleadosFacade().edit(empIdioma);
        return null;
    }

    // Devuelve una fecha a partir de un aÃ±o dado (Estudios Empleados)    
    public Date fechaAnio(int anio) {
        anio = anio - 1900;
        Date fecha = new Date(anio, 11, 31);
        return fecha;
    }
    
    public int obtenerAnio(Date date){ // obtiene año de una fecha dada
    if (null == date){
        return 0;
    }
    else{        
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
        return Integer.parseInt(dateFormat.format(date));
    }

}

    public void empleadoSelecionadoValidoF(ActionEvent event) {
        //verifica que se seleciono o se busco un empleado
        if (this.getEmpleadoSelecionado() == 0) {
            estudiosEmp = new EstudiosEmp();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Debe Buscar un Empleado"));
        } else {
            RequestContext.getCurrentInstance().execute("PF('formal').show()");
        }
    }

    public void empleadoSelecionadoValidoNF(ActionEvent event) {
        //verifica que se seleciono o se busco un empleado
        if (this.getEmpleadoSelecionado() == 0) {
            estudiosEmp = new EstudiosEmp();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Debe Buscar un Empleado"));
        } else {
            RequestContext.getCurrentInstance().execute("PF('noformal').show()");
        }
    }

    public void empleadoSelecionadoValidoI(ActionEvent event) {
        //verifica que se seleciono o se busco un empleado
        if (this.getEmpleadoSelecionado() == 0) {
            estudiosEmp = new EstudiosEmp();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Debe Buscar un Empleado"));
        } else {
            RequestContext.getCurrentInstance().execute("PF('idiomas').show()");
        }
    }
    
    public void buscarNR(ActionEvent event){
        //Busca empleado por NR
        Empleados emp = getEmpleadosFacade().buscarEmpNR(this.getNR());
        if (emp == null) {
            this.setNombreEmp("");
        } else {
            this.setEmpleadoSelecionado(emp.getIdEmpleado());
            this.setNombreEmp(emp.getNombreEmpleado());
        }
    }
}
