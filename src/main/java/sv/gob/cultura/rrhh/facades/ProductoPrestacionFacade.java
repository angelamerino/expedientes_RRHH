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
import sv.gob.cultura.rrhh.entities.ProductoPrestacion;

/**
 *
 * @author SOPORTE CULTURA
 */
@Stateless
public class ProductoPrestacionFacade extends AbstractFacade<ProductoPrestacion> {
    @PersistenceContext(unitName = "rrhhPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductoPrestacionFacade() {
        super(ProductoPrestacion.class);
    }
    
    public List<ProductoPrestacion> buscarProdIdPrestacion(int idPrestacion){
        return getEntityManager().createNamedQuery("ProductoPrestacion.findByIdPrestacion").setParameter("idPrestacion", idPrestacion).getResultList();
    }
    
}
