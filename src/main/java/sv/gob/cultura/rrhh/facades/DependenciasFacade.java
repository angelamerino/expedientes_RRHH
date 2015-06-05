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
import sv.gob.cultura.rrhh.entidades.Dependencias;

/**
 *
 * @author Angela
 */
@Stateless
public class DependenciasFacade extends AbstractFacade<Dependencias> {
    @PersistenceContext(unitName = "rrhhPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DependenciasFacade() {
        super(Dependencias.class);
    }
    
    public List<Dependencias> buscarDependencias(int dirNacional){
        return getEntityManager().createNamedQuery("Dependencias.findByIdDirNacional").setParameter("idDirNac", dirNacional).getResultList();
    }

}
