/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.cultura.rrhh.facades;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import sv.gob.cultura.rrhh.entities.DirNacional;

/*****
 *
 * @author Angela
 */
@Stateless
public class DirNacionalFacade extends AbstractFacade<DirNacional> {
    @PersistenceContext(unitName = "rrhhPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DirNacionalFacade() {
        super(DirNacional.class);
    }
    
}
