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
import sv.gob.cultura.rrhh.entidades.AdministradoraPensiones;
import sv.gob.cultura.rrhh.manejadores.manejadorCat;

/**
 *
 * @author Angela
 */
@Stateless
public class AdministradoraPensionesFacade extends AbstractFacade<AdministradoraPensiones> {
    @PersistenceContext(unitName = "rrhhPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AdministradoraPensionesFacade() {
        super(AdministradoraPensiones.class);
    }

    public List<AdministradoraPensiones> getAdministradoraPensiones() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
