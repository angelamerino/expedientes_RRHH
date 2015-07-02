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
import sv.gob.cultura.rrhh.entidades.ContactoEmergenciaEmp;

/**
 *
 * @author Angela
 */
@Stateless
public class ContactoEmergenciaEmpFacade extends AbstractFacade<ContactoEmergenciaEmp> {
    @PersistenceContext(unitName = "rrhhPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ContactoEmergenciaEmpFacade() {
        super(ContactoEmergenciaEmp.class);
    }
    
    public List<ContactoEmergenciaEmp> buscarContactos(int idEmpleado){
        return getEntityManager().createNamedQuery("ContactoEmergenciaEmp.findByIdEmpleado").setParameter("idEmpleado", idEmpleado).getResultList();
    }
}
