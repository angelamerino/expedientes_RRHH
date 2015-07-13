/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.cultura.rrhh.manejadores;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.context.RequestContext;
import sv.gob.cultura.rrhh.entidades.Dependencias;
import sv.gob.cultura.rrhh.entidades.DirNacional;
import sv.gob.cultura.rrhh.entidades.Empleados;
import sv.gob.cultura.rrhh.entidades.FamiliaDependientesEmp;
import sv.gob.cultura.rrhh.entidades.Parentesco;
import sv.gob.cultura.rrhh.facades.DependenciasFacade;
import sv.gob.cultura.rrhh.facades.DirNacionalFacade;
import sv.gob.cultura.rrhh.facades.EmpleadosFacade;
import sv.gob.cultura.rrhh.facades.FamiliaDependientesEmpFacade;
import sv.gob.cultura.rrhh.facades.ParentescoFacade;

/**
 *
 * @author Edwin
 */
@Named(value = "manejadorDependientesFamilia")
@ViewScoped
public class manejadorDependientesFamilia implements Serializable {

    public manejadorDependientesFamilia() {
    }

// ************** LLAMADA A LOS ENTERPRICE JAVA BEANS **************************
    @EJB
    private FamiliaDependientesEmpFacade familiaDependientesEmpFacade;
    @EJB
    private ParentescoFacade parentescoFacade;
    @EJB
    private EmpleadosFacade empleadosFacade;
    @EJB
    private DependenciasFacade dependenciasFacade;
    @EJB
    private DirNacionalFacade dirNacionalFacade;

//*********************** OBJETOS DE LOS ENTIDADES ***************************** 
    FamiliaDependientesEmp familiaDependientesEmp = new FamiliaDependientesEmp();
    Parentesco parentesco = new Parentesco();
//****** VARIABLES QUE CONTRENDRAN ID´S O STRING DE FORMULARIOS ****************
    private Date fechaNacFamiliaDependiente = new Date();       // fecha naciemeinto familiares
    private int edadFamiliaDependiente;                         // edad familiares calculada a partir de fecha
    private int direccionNacional;
    private int dependecia;
    private int empleadoSelecionado;
    private String nombreEmp;
//********************** GET DE ENTERPRICE JAVA BEAN ***************************

    public FamiliaDependientesEmpFacade getFamiliaDependientesEmpFacade() {
        return familiaDependientesEmpFacade;
    }

    public ParentescoFacade getParentescoFacade() {
        return parentescoFacade;
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

//******************* GET y SET DE OBJETOS DE ENTIDADES ************************
    public FamiliaDependientesEmp getFamiliaDependientesEmp() {
        return familiaDependientesEmp;
    }

    public void setFamiliaDependientesEmp(FamiliaDependientesEmp familiaDependientesEmp) {
        this.familiaDependientesEmp = familiaDependientesEmp;
    }

    public Parentesco getParentesco() {
        return parentesco;
    }

    public void setParentesco(Parentesco parentesco) {
        this.parentesco = parentesco;
    }

    public Date getFechaNacFamiliaDependiente() {
        return fechaNacFamiliaDependiente;
    }

    public void setFechaNacFamiliaDependiente(Date fechaNacFamiliaDependiente) {
        this.fechaNacFamiliaDependiente = fechaNacFamiliaDependiente;
        this.setEdadFamiliaDependiente(edad(fechaNacFamiliaDependiente));
    }

    public int getEdadFamiliaDependiente() {
        return edadFamiliaDependiente;
    }

    public void setEdadFamiliaDependiente(int edadFamiliaDependiente) {
        this.edadFamiliaDependiente = edadFamiliaDependiente;
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

// **************** LISTA DE ELEMENTOS EN TABLAS *******************************  
    public List<Parentesco> todosParentesco() {
        return getParentescoFacade().findAll();
    }

    public List<FamiliaDependientesEmp> todosFamiliaDependientesEmp() {
        return getFamiliaDependientesEmpFacade().buscarDependientes(this.getEmpleadoSelecionado());
    }

    public List<DirNacional> todosDirNacional() {
        return getDirNacionalFacade().findAll();
    }

    public List<Dependencias> dependenciasFiltradas() {
        return getDependenciasFacade().buscarDependencias(this.getDireccionNacional());
    }

    public List<Empleados> empleadoFiltrado() {
        return getEmpleadosFacade().buscarEmp(this.getDependecia());
    }

//*************************** FUNCIONES DE GUARDAR *****************************
    public void guardarFamiliaDependiente() {
        familiaDependientesEmp.setIdEmpleado(new Empleados(this.getEmpleadoSelecionado()));
        familiaDependientesEmp.setEdadFamilia(getEdadFamiliaDependiente());
        familiaDependientesEmp.setFechaNacFamilia(getFechaNacFamiliaDependiente());
        
        //Fecha de Creación y usuario Id =1
        familiaDependientesEmp.setFechaCreaFam(new Date());
        familiaDependientesEmp.setUserCreaFam(1);
        
        getFamiliaDependientesEmpFacade().create(familiaDependientesEmp);
        familiaDependientesEmp = new FamiliaDependientesEmp();
        this.setEdadFamiliaDependiente(0);
        this.setFechaNacFamiliaDependiente(new Date());
    }

    public void editarFamiliaDependiente() {
        familiaDependientesEmp.setEdadFamilia(getEdadFamiliaDependiente());
        familiaDependientesEmp.setFechaNacFamilia(getFechaNacFamiliaDependiente());
        
        //Fecha de Modificación y usuario Id =1
        familiaDependientesEmp.setFechaModFam(new Date());
        familiaDependientesEmp.setUserModFam(1);
        
        getFamiliaDependientesEmpFacade().edit(familiaDependientesEmp);
        familiaDependientesEmp = new FamiliaDependientesEmp();
        familiaDependientesEmp = new FamiliaDependientesEmp();
        this.setEdadFamiliaDependiente(0);
        this.setFechaNacFamiliaDependiente(new Date());
    }

    public String eliminar(FamiliaDependientesEmp familiar) {
        getFamiliaDependientesEmpFacade().remove(familiar);
        return null;
    }

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

    public void empleadoSelecionadoValido(ActionEvent event) {
        if (this.getEmpleadoSelecionado() == 0) {
            familiaDependientesEmp = new FamiliaDependientesEmp();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Seleccione un Empleado"));
        } else {
            RequestContext.getCurrentInstance().execute("PF('familia').show()");
        }
    }
}
