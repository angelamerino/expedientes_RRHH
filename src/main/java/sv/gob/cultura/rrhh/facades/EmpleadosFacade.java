/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.cultura.rrhh.facades;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import sv.gob.cultura.rrhh.entidades.Empleados;

/**
 *
 * @author Angela
 */
@Stateless
public class EmpleadosFacade extends AbstractFacade<Empleados> {
    @PersistenceContext(unitName = "rrhhPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EmpleadosFacade() {
        super(Empleados.class);
    }
    
    public List<Empleados> buscarEmp(int idDependencia){
        return getEntityManager().createNamedQuery("Empleados.findByDependencia").setParameter("dependencia", idDependencia).getResultList();
    }
    
    public List<Empleados> buscarEmpId(int idEmpleado){
        return getEntityManager().createNamedQuery("Empleados.findByIdEmpleado").setParameter("idEmpleado", idEmpleado).getResultList();
    }
    
}
