/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.cultura.rrhh.facades;

import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import sv.gob.cultura.rrhh.entities.Capacitaciones;

/**
 *
 * @author Angela
 */
@Stateless
public class CapacitacionesFacade extends AbstractFacade<Capacitaciones> {
    @PersistenceContext(unitName = "rrhhPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CapacitacionesFacade() {
        super(Capacitaciones.class);
    }
    
    public List<Capacitaciones> buscarCapacitacionByAnio(Date anio){
        return getEntityManager().createNamedQuery("Capacitaciones.findByAnioCap").setParameter("anioCap", anio).getResultList();
    }
    
}
