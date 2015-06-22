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
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import sv.gob.cultura.rrhh.entidades.Empleados;
import sv.gob.cultura.rrhh.entidades.FamiliaDependientesEmp;
import sv.gob.cultura.rrhh.entidades.Parentesco;
import sv.gob.cultura.rrhh.facades.FamiliaDependientesEmpFacade;
import sv.gob.cultura.rrhh.facades.ParentescoFacade;

/**
 *
 * @author Edwin
 */
@Named(value = "manejadorDependientesFamilia")
@ViewScoped
public class manejadorDependientesFamilia implements Serializable{
    
    

    
    public manejadorDependientesFamilia() {
    }
    
// ************** LLAMADA A LOS ENTERPRICE JAVA BEANS **************************
    @EJB
    private FamiliaDependientesEmpFacade familiaDependientesEmpFacade;
    @EJB
    private ParentescoFacade parentescoFacade;
    
    
//*********************** OBJETOS DE LOS ENTIDADES ***************************** 
    FamiliaDependientesEmp familiaDependientesEmp = new FamiliaDependientesEmp();
    Parentesco parentesco = new Parentesco();
//****** VARIABLES QUE CONTRENDRAN ID´S O STRING DE FORMULARIOS ****************
    private int empleadoId = 3;                     // id empleado Ahora prueba empleado id=3
    private Date fechaNacFamiliaDependiente = new Date();      // fecha naciemeinto familiares
    private int edadFamiliaDependiente;             // edad familiares calculada a partir de fecha
//********************** GET DE ENTERPRICE JAVA BEAN ***************************
    public FamiliaDependientesEmpFacade getFamiliaDependientesEmpFacade() {
        return familiaDependientesEmpFacade;
    }

    public ParentescoFacade getParentescoFacade() {
        return parentescoFacade;
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

    public int getEmpleadoId() {
        return empleadoId;
    }

    public void setEmpleadoId(int empleadoId) {
        this.empleadoId = empleadoId;
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
    
    
    
// **************** LISTA DE ELEMENTOS EN TABLAS *******************************  
    public List<Parentesco> todosParentesco() {
        return getParentescoFacade().findAll();
    }
    
    public List<FamiliaDependientesEmp> todosFamiliaDependientesEmp() {
        return getFamiliaDependientesEmpFacade().findAll();
    }
    
//*************************** FUNCIONES DE GUARDAR *****************************

    public void guardarFamiliaDependiente() {
        familiaDependientesEmp.setIdEmpleado(new Empleados(empleadoId));
        familiaDependientesEmp.setEdadFamilia(getEdadFamiliaDependiente());
        familiaDependientesEmp.setFechaNacFamilia(getFechaNacFamiliaDependiente());
        getFamiliaDependientesEmpFacade().create(familiaDependientesEmp);
        familiaDependientesEmp = new FamiliaDependientesEmp();
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
}
