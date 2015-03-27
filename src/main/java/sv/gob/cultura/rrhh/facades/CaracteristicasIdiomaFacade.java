/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.cultura.rrhh.facades;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import sv.gob.cultura.rrhh.entidades.CaracteristicasIdioma;

/**
 *
 * @author Angela
 */
@Stateless
public class CaracteristicasIdiomaFacade extends AbstractFacade<CaracteristicasIdioma> {
    @PersistenceContext(unitName = "rrhhPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CaracteristicasIdiomaFacade() {
        super(CaracteristicasIdioma.class);
    }
    
}
