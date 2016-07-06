/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.cultura.rrhh.facades;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import sv.gob.cultura.rrhh.entities.EstadosUsuarios;
import sv.gob.cultura.rrhh.entities.UsuariosSistema;

/**
 *
 * @author Angela
 */
@Stateless
public class EstadosUsuariosFacade extends AbstractFacade<EstadosUsuarios> {
    @PersistenceContext(unitName = "rrhhPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EstadosUsuariosFacade() {
        super(EstadosUsuarios.class);
    }

    public void edit(UsuariosSistema usuariosis) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
