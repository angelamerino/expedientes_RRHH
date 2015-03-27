/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.cultura.rrhh.manejadores;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import sv.gob.cultura.rrhh.entidades.Empleados;
import sv.gob.cultura.rrhh.facades.EmpleadosFacade;

/**
 *
 * @author Angela
 */
@Named(value = "manejadorGestionEmpleado")
@ViewScoped
public class manejadorGestionEmpleado implements Serializable{
// 3- Agregar arriba "implemets Serializable" y luego Insertar codigo y seleccionar Call Enterprice Beans para llamar el DAO el DAO o Facade
        //de la Entidad Empleados. 
    // Esto generará la variable @EJB Que es el DAO para persistir datos

    @EJB
    private EmpleadosFacade empleadosFacade;
        
    /**
     * Creates a new instance of manejadorGestionEmpleado
     */
    private Empleados nuevoEmpleado=new Empleados(); //1- Creo primero esta variable privada 
    
    public manejadorGestionEmpleado() { //Constructor
    }
// 2- inserto código para getter y setter

    public Empleados getNuevoEmpleado() {
        return nuevoEmpleado;
    }

    public void setNuevoEmpleado(Empleados nuevoEmpleado) {
        this.nuevoEmpleado = nuevoEmpleado;
    }
    
// 4- Hacer un Get para el Facade. NOTA: a los facade solo se les hace GET y no SET.
// Insertar código y seleccionar Getter y se genera este código.

    public EmpleadosFacade getEmpleadosFacade() {
        return empleadosFacade;
    }

  
// 5- Crear metodo con public void para guardar en bd
   public void guardarEmpleado(){
        
// 6- digitar rapido trycatch + tecla tab para que se genere este código
       try {
           getEmpleadosFacade().create(nuevoEmpleado); // Esto guarda en la BD
       } catch (Exception e) {
       }
   
   }
}
