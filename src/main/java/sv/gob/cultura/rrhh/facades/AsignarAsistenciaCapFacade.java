/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.cultura.rrhh.facades;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import sv.gob.cultura.rrhh.entities.AsignarAsistenciaCap;

/**
 *
 * @author Angela
 */
@Stateless
public class AsignarAsistenciaCapFacade extends AbstractFacade<AsignarAsistenciaCap> {
    @PersistenceContext(unitName = "rrhhPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AsignarAsistenciaCapFacade() {
        super(AsignarAsistenciaCap.class);
    }
    
    public List<AsignarAsistenciaCap> todasAsignaidCap(int idCapacitacion){
        return getEntityManager().createNamedQuery("AsignarAsistenciaCap.findByidCap").setParameter("idCap", idCapacitacion).getResultList();
    }
    
    public List<AsignarAsistenciaCap> repCapacitacionesEmp(int idEmpleado){
        return getEntityManager().createNamedQuery("AsignarAsistenciaCap.findByidEmpleado").setParameter("idEmpleado", idEmpleado).getResultList();
    }
    
    public AsignarAsistenciaCap asignaidCapEmpleado(int emp){
        try {
            return (AsignarAsistenciaCap) getEntityManager().createNamedQuery("AsignarAsistenciaCap.findByidEmpleado").setParameter("idEmpleado", emp).getSingleResult();
        }catch(NoResultException e){ //Por si no encuentra ningun resultado que retorne nulo
            return null;
        }
    }
    
    
    
}
