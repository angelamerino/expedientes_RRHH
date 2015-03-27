/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.cultura.rrhh.facades;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import sv.gob.cultura.rrhh.entidades.TipoMejoraSalarial;

/**
 *
 * @author Angela
 */
@Stateless
public class TipoMejoraSalarialFacade extends AbstractFacade<TipoMejoraSalarial> {
    @PersistenceContext(unitName = "rrhhPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoMejoraSalarialFacade() {
        super(TipoMejoraSalarial.class);
    }
    
}
