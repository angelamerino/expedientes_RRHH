/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.cultura.rrhh.manejadores;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import sv.gob.cultura.rrhh.entidades.Empleados;
import sv.gob.cultura.rrhh.entidades.ExperienciaLaboral;
import sv.gob.cultura.rrhh.facades.ExperienciaLaboralFacade;

/**
 *
 * @author Edwin
 */
@Named(value = "manejadorExperienciaLaboral")
@ViewScoped
public class manejadorExperienciaLaboral implements Serializable{
    

    public manejadorExperienciaLaboral() {
    }
//******************************************************************************
// ************** LLAMADA A LOS ENTERPRICE JAVA BEANS **************************
//******************************************************************************
    @EJB
    private ExperienciaLaboralFacade experienciaLaboralFacade;
//******************************************************************************
//*********************** OBJETOS DE LOS ENTIDADES *****************************
//******************************************************************************    
    ExperienciaLaboral experienciaLaboral = new ExperienciaLaboral();
//******************************************************************************
//****** VARIABLES QUE CONTRENDRAN ID´S O STRING DE FORMULARIOS ****************
//******************************************************************************
    private int empleadoId = 3;                     // id empleado Ahora prueba empleado id=3
// *****************************************************************************
//********************** GET DE ENTERPRICE JAVA BEAN ***************************
//******************************************************************************
    public ExperienciaLaboralFacade getExperienciaLaboralFacade() {
        return experienciaLaboralFacade;
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
    
    public int getEmpleadoId() {
        return empleadoId;
    }

    public void setEmpleadoId(int empleadoId) {
        this.empleadoId = empleadoId;
    }
//******************************************************************************
// **************** LISTA DE ELEMENTOS EN TABLAS *******************************
//******************************************************************************
    public List<ExperienciaLaboral> experienciaLaboralPublico() {
        return getExperienciaLaboralFacade().experienciaLaboralSector("Público");
    }

    public List<ExperienciaLaboral> experienciaLaboralPrivado() {
        return getExperienciaLaboralFacade().experienciaLaboralSector("Privado");
    }
//******************************************************************************
//*************************** FUNCIONES DE GUARDAR *****************************
//******************************************************************************
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
    

   

    

}
