/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.cultura.rrhh.facades;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import sv.gob.cultura.rrhh.entities.Empleados;

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

    public List<Empleados> findByDependencia(int idDependencia) {
        return getEntityManager().createNamedQuery("Empleados.findByDependencia").setParameter("dependencia", idDependencia).getResultList();
    }

    public Empleados buscarEmpNR(String NREmpleado) {
        try {
            return (Empleados) getEntityManager().createNamedQuery("Empleados.findByNrEmpleado").setParameter("nrEmpleado", NREmpleado).getSingleResult();
        } catch (NoResultException e) { //Por si no encuentra ningun resultado que retorne nulo
            return null;
        }
    }

    public List<Empleados> findByIdEmp(int idEmpleado) {
        return getEntityManager().createNamedQuery("Empleados.findByIdEmpleado").setParameter("idEmpleado", idEmpleado).getResultList();
    }

}
