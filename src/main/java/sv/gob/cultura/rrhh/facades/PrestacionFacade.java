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
import sv.gob.cultura.rrhh.entities.Anio;
import sv.gob.cultura.rrhh.entities.Prestacion;

/**
 *
 * @author Angela
 */
@Stateless
public class PrestacionFacade extends AbstractFacade<Prestacion> {
    @PersistenceContext(unitName = "rrhhPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PrestacionFacade() {
        super(Prestacion.class);
    }
    
    public List<Prestacion> buscarPrestacionAnio(int anio){
        return getEntityManager().createNamedQuery("Prestacion.findByAnio").setParameter("anio", anio).getResultList();
    }
}
