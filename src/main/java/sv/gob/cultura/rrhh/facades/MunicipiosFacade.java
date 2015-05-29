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
import sv.gob.cultura.rrhh.entidades.Municipios;

/**
 *
 * @author Angela
 */
@Stateless
public class MunicipiosFacade extends AbstractFacade<Municipios> {
    @PersistenceContext(unitName = "rrhhPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MunicipiosFacade() {
        super(Municipios.class);
    }
    
    public List<Municipios> buscarDep(int depto){
        return getEntityManager().createNamedQuery("Municipios.buscarDep").setParameter("depto", depto).getResultList();
    }

    public List<Municipios> buscarDep() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
