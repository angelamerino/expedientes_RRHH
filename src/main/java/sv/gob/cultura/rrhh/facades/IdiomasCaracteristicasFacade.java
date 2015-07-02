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
import sv.gob.cultura.rrhh.entidades.IdiomasCaracteristicas;

/**
 *
 * @author Angela
 */
@Stateless
public class IdiomasCaracteristicasFacade extends AbstractFacade<IdiomasCaracteristicas> {
    @PersistenceContext(unitName = "rrhhPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public IdiomasCaracteristicasFacade() {
        super(IdiomasCaracteristicas.class);
    }
    //IdiomasCaracteristicas.findByIdIdioma
    
    public List<IdiomasCaracteristicas> buscarIdiomasId(int idIdioma){
        return getEntityManager().createNamedQuery("IdiomasCaracteristicas.findByIdIdioma").setParameter("idIdioma", idIdioma).getResultList();
    }
}
