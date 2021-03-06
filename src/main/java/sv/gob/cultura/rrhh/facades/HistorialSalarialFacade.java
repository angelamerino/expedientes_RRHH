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
import sv.gob.cultura.rrhh.entities.HistorialSalarial;

/**
 *
 * @author Angela
 */
@Stateless
public class HistorialSalarialFacade extends AbstractFacade<HistorialSalarial> {
    @PersistenceContext(unitName = "rrhhPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public HistorialSalarialFacade() {
        super(HistorialSalarial.class);
    }
    
    public List<HistorialSalarial> findByIdEmp(int idEmp){
        return getEntityManager().createNamedQuery("HistorialSalarial.findByIdEmpleado").setParameter("idEmpleado", idEmp).getResultList();
    }
    
}
