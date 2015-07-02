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
import sv.gob.cultura.rrhh.entidades.FamiliaDependientesEmp;

/**
 *
 * @author Angela
 */
@Stateless
public class FamiliaDependientesEmpFacade extends AbstractFacade<FamiliaDependientesEmp> {
    @PersistenceContext(unitName = "rrhhPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FamiliaDependientesEmpFacade() {
        super(FamiliaDependientesEmp.class);
    }
    
    public List<FamiliaDependientesEmp> buscarDependientes(int idEmpleado){
        return getEntityManager().createNamedQuery("FamiliaDependientesEmp.findByIdEmpleado").setParameter("idEmpleado", idEmpleado).getResultList();
    }
}
