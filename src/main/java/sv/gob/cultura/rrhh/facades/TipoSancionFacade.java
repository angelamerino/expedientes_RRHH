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
import sv.gob.cultura.rrhh.entities.TipoSancion;

/**
 *
 * @author Angela
 */
@Stateless
public class TipoSancionFacade extends AbstractFacade<TipoSancion> {
    @PersistenceContext(unitName = "rrhhPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoSancionFacade() {
        super(TipoSancion.class);
    }
    
    public List<TipoSancion> buscarTipoSancionByGradoSancion(int idGradoSancion){
        return getEntityManager().createNamedQuery("TipoSancion.findByIdGradoSancion").setParameter("idGradoSancion", idGradoSancion).getResultList();
    }
    
}
