/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.cultura.rrhh.manejadores;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import sv.gob.cultura.rrhh.entidades.Dependencias;
import sv.gob.cultura.rrhh.entidades.DirNacional;
import sv.gob.cultura.rrhh.entidades.EstadosUsuarios;
import sv.gob.cultura.rrhh.entidades.RolesUsuario;
import sv.gob.cultura.rrhh.entidades.UsuariosSistema;
import sv.gob.cultura.rrhh.facades.DependenciasFacade;
import sv.gob.cultura.rrhh.facades.EstadosUsuariosFacade;
import sv.gob.cultura.rrhh.facades.RolesUsuarioFacade;
import sv.gob.cultura.rrhh.facades.UsuariosSistemaFacade;
import sv.gob.cultura.rrhh.facades.DirNacionalFacade;
import javax.faces.application.FacesMessage;
/**
 *
 * @author Angela
 */
@Named(value = "manejadorUsuarios")
@ViewScoped
public class manejadorUsuarios implements Serializable {

    private static final long serialVersionUID = 1L;
    @EJB
    private RolesUsuarioFacade rolesUsuarioFacade;
    @EJB
    private EstadosUsuariosFacade estadosUsuariosFacade;
    @EJB
    private DependenciasFacade dependenciasFacade;
    @EJB
    private UsuariosSistemaFacade usuariosSistemaFacade;
    @EJB
    private DirNacionalFacade dirNacionalFacade;

////////////VARIABLE UTILIZADAS///////////////
    private UsuariosSistema nuevoUsuario = new UsuariosSistema(), usuarioSeleccionado = new UsuariosSistema(), usuariosistema = new UsuariosSistema();

    private List<UsuariosSistema> usuariosFiltrados;

    private DirNacional dirNacional = new DirNacional();

    private int estado, tipoUsuario, dependencia;
    private int dirNacionalFuncional;

    private int dirNacionalFiltrar;             // id´s direccion nacional
    private int dependeciasFiltrar;             // id´s dependencias

    private int estadoUsuarioEditar;
    private int usuarioEditar;                  //id del usuario a editar
    private String nombreUsuarioEditar;         //nombre del usuario a editar
    private int rolesUsuarioEditar;             //id del rol para editar
    private int idDir;
    private String clave;


    ////////////LISTAS UTILIZADAS///////////////
    public List<EstadosUsuarios> todosEstadosUsuarios() {
        return getEstadosUsuariosFacade().findAll();
    }

    public List<RolesUsuario> todosRoles() {
        return getRolesUsuarioFacade().findAll();
    }

    public List<Dependencias> dependenciasFiltradas() {

        return getDependenciasFacade().buscarDependencias(dirNacionalFuncional);

    }

    public List<UsuariosSistema> getTodosUsuarios() {
        return getUsuariosSistemaFacade().findAll();
    }

    public List<DirNacional> todosDirNacional() {
        return getDirNacionalFacade().findAll();
    }

    public DirNacional getDirNacional() {
        return dirNacional;
    }

    public List<UsuariosSistema> getUsuariosFiltrados() {
        return usuariosFiltrados;
    }
    
    
    
    

//////////// GET Y SET ///////////////
    public int getIdDir() {
        return idDir;
    }
    
    public void setIdDir(int idDir) {
        this.idDir = idDir;
    }

    public int getRolesUsuarioEditar() {
        return rolesUsuarioEditar;
    }

    public void setRolesUsuarioFacade(RolesUsuarioFacade rolesUsuarioFacade) {
        this.rolesUsuarioFacade = rolesUsuarioFacade;
    }
    
    public void setRolesUsuarioEditar(int rolesUsuarioEditar) {
        this.rolesUsuarioEditar = rolesUsuarioEditar;
    }

    public int getEstadoUsuarioEditar() {
        return estadoUsuarioEditar;
    }

    public void setEstadoUsuarioEditar(int estadoUsuarioEditar) {
        this.estadoUsuarioEditar = estadoUsuarioEditar;
    }

    public void setDirNacional(DirNacional dirNacional) {
        this.dirNacional = dirNacional;
    }

    public UsuariosSistema getUsuariosistema() {
        return usuariosistema;
    }

    public void setUsuariosistema(UsuariosSistema usuariosistema) {
        this.usuariosistema = usuariosistema;
    }

    public UsuariosSistema getUsuarioSeleccionado() {
        return usuarioSeleccionado;
    }

    public void setUsuarioSeleccionado(UsuariosSistema usuarioSeleccionado) {
        this.usuarioSeleccionado = usuarioSeleccionado;
    }

    public void setUsuariosFiltrados(List<UsuariosSistema> usuariosFiltrados) {
        this.usuariosFiltrados = usuariosFiltrados;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(int tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public int getDependencia() {
        return dependencia;
    }

    public void setDependencia(int dependencia) {
        this.dependencia = dependencia;
    }

    public int getDirNacionalFuncional() {
        return dirNacionalFuncional;
    }

    public void setDirNacionalFuncional(int dirNacionalFuncional) {
        this.dirNacionalFuncional = dirNacionalFuncional;
    }

    public DependenciasFacade getDependenciasFacade() {
        return dependenciasFacade;
    }

    public UsuariosSistemaFacade getUsuariosSistemaFacade() {
        return usuariosSistemaFacade;
    }

    public RolesUsuarioFacade getRolesUsuarioFacade() {
        return rolesUsuarioFacade;
    }

    public EstadosUsuariosFacade getEstadosUsuariosFacade() {
        return estadosUsuariosFacade;
    }

    //txtfechaCreaUsistema.setText(FechaActual);
    public UsuariosSistema getNuevoUsuario() {
        return nuevoUsuario;
    }

    public void setNuevoUsuario(UsuariosSistema nuevoUsuario) {
        this.nuevoUsuario = nuevoUsuario;
    }

    public DirNacionalFacade getDirNacionalFacade() {
        return dirNacionalFacade;
    }

    public int getDirNacionalFiltrar() {
        return dirNacionalFiltrar;
    }

    public void setDirNacionalFiltrar(int dirNacionalFiltrar) {
        this.dirNacionalFiltrar = dirNacionalFiltrar;
    }

    public int getDependeciasFiltrar() {
        return dependeciasFiltrar;
    }

    public void setDependeciasFiltrar(int dependeciasFiltrar) {
        this.dependeciasFiltrar = dependeciasFiltrar;
    }

    public int getUsuarioEditar() {
        return usuarioEditar;
    }

    public void setUsuarioEditar(int usuarioEditar) {
        this.usuarioEditar = usuarioEditar;
    }

    public String getNombreUsuarioEditar() {
        return nombreUsuarioEditar;
    }

    public void setNombreUsuarioEditar(String nombreUsuarioEditar) {
        this.nombreUsuarioEditar = nombreUsuarioEditar;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

   

    
    
    
    /**
     * ////////////////// METODOS /////////////// Creates a new instance of
     * manejadorUsuarios
     */
    public manejadorUsuarios() {

    }

    public void mensajeGuardar() {
        FacesContext context = FacesContext.getCurrentInstance();
         
        context.addMessage(null, new FacesMessage("Registro Guardado", "La información fue guardada exitosamente"));
    }
    
    public void guardarUsuario() {
//        System.out.println("entrando guardar");
//            nuevoUsuario.setUserCreaUsistema(1);
//            nuevoUsuario.setFechaCreaUsistema(new Date());
//            System.out.println(nuevoUsuario.getClave());
//            System.out.println(nuevoUsuario.getFechaCreaUsistema());
//            System.out.println(nuevoUsuario.getIdDependencia());
//            System.out.println(nuevoUsuario.getIdEstadoUsuario());
//            System.out.println(nuevoUsuario.getIdRolUsuario());
//            System.out.println(nuevoUsuario.getNombreCompleto());
//            System.out.println(nuevoUsuario.getUserCreaUsistema());
//            System.out.println(nuevoUsuario.getUsuario());
        nuevoUsuario.setIdDependencia(new Dependencias(dependencia));
        nuevoUsuario.setIdRolUsuario(new RolesUsuario(tipoUsuario));
        nuevoUsuario.setIdEstadoUsuario(new EstadosUsuarios(estado));
        nuevoUsuario.setFechaCreaUsistema(new Date());
        nuevoUsuario.setUserCreaUsistema(1); //guardará el id del usuario logeado

        getUsuariosSistemaFacade().create(nuevoUsuario);
        nuevoUsuario = new UsuariosSistema();
    }

    public String editarUsuario() {
//        try {
        usuarioSeleccionado.setFechaModUsistema(new Date());
        usuarioSeleccionado.setUserModUsistema(1);//guardará el id del usuario logeado

        getUsuariosSistemaFacade().edit(usuarioSeleccionado);
        usuarioSeleccionado = new UsuariosSistema();
        return "gestion_usuarios";
//            todosUsuarios = getUsuariosSistemaFacade().findAll();
//            addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Modificación de Usuario:", "El registro se modificó exitosamente."));
//        } catch (Exception e) {
//        }
    }

    public String cancelarUsuario() {
        return "gestion_usuarios";
    }

    public void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void cambiarEstado() {
        UsuariosSistema usuariosis = getUsuariosSistemaFacade().find(this.getUsuarioEditar());
        usuariosis.setIdEstadoUsuario(new EstadosUsuarios(this.getEstadoUsuarioEditar()));
        getUsuariosSistemaFacade().edit(usuariosis);

    }

    /*PARA ELIMINAR REGISTROS*/
    public void eliminarUsuario() {
        this.usuariosSistemaFacade.remove(usuarioSeleccionado);
    }

    //Se crea el siguiente clase para fechad del sistema
    public class FechaActual {

        String cadenaFecha;

        public FechaActual() {
            Date fechaActual = new Date();
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            cadenaFecha = formato.format(fechaActual);
        }

        public String getFechaActual() {
            return cadenaFecha;
        }
    }

    private Date mostrarFecha;

    public manejadorUsuarios(Date mostrarFecha) {
        this.mostrarFecha = mostrarFecha;
    }

    public Date getMostrarFecha() {
        return mostrarFecha;
    }

    public void setMostrarFecha(Date mostrarFecha) {
        this.mostrarFecha = mostrarFecha;
    }

    public static String fechaSistema() {
        Date fecha = new Date();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatoFecha.format(fecha);

    }

// public class Fechas {
//    public static void main(String[] args) {
//        //Instanciamos el objeto Calendar
//        //en fecha obtenemos la fecha y hora del sistema
//        Calendar fecha = new GregorianCalendar();
//        //Obtenemos el valor del año, mes, día,
//        //hora, minuto y segundo del sistema
//        //usando el método get y el parámetro correspondiente
//        int año = fecha.get(Calendar.YEAR);
//        int mes = fecha.get(Calendar.MONTH);
//        int dia = fecha.get(Calendar.DAY_OF_MONTH);
//        int hora = fecha.get(Calendar.HOUR_OF_DAY);
//        int minuto = fecha.get(Calendar.MINUTE);
//        int segundo = fecha.get(Calendar.SECOND);
//        System.out.println("Fecha Actual: "
//                           + dia + "/" + (mes+1) + "/" + año);
//        System.out.printf("Hora Actual: %02d:%02d:%02d %n",
//                                              hora, minuto, segundo);
//    }
//}
}
