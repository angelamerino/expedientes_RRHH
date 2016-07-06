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
import sv.gob.cultura.rrhh.entities.HorariosCap;

/**
 *
 * @author Angela
 */
@Stateless
public class HorariosCapFacade extends AbstractFacade<HorariosCap> {
    @PersistenceContext(unitName = "rrhhPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public HorariosCapFacade() {
        super(HorariosCap.class);
    }
    
    public List<HorariosCap> buscarHorasDiasCap(int cap){
        return getEntityManager().createNamedQuery("HorariosCap.findByIdCap").setParameter("idCap", cap).getResultList();
    }
}
