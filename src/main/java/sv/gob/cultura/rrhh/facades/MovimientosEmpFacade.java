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
import sv.gob.cultura.rrhh.entities.Empleados;
import sv.gob.cultura.rrhh.entities.MovimientosEmp;

/**
 *
 * @author Angela
 */
@Stateless
public class MovimientosEmpFacade extends AbstractFacade<MovimientosEmp> {

    @PersistenceContext(unitName = "rrhhPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MovimientosEmpFacade() {
        super(MovimientosEmp.class);
    }

    public List<MovimientosEmp> findByIdEmp(Empleados emp) {
        return getEntityManager().createNamedQuery("MovimientosEmp.findByIdEmp").setParameter("idEmpleado", emp).getResultList();
    }
}
