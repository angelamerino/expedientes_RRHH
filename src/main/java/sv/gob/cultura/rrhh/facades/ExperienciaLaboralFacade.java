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
import sv.gob.cultura.rrhh.entities.ExperienciaLaboral;

/**
 *
 * @author Angela
 */
@Stateless
public class ExperienciaLaboralFacade extends AbstractFacade<ExperienciaLaboral> {

    @PersistenceContext(unitName = "rrhhPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ExperienciaLaboralFacade() {
        super(ExperienciaLaboral.class);
    }

    public List<ExperienciaLaboral> findByEmpId(int idEmpleado) {
        return getEntityManager().createNamedQuery("ExperienciaLaboral.findByEmpId").setParameter("idEmpleado", idEmpleado).getResultList();
    }
}
