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
import sv.gob.cultura.rrhh.entities.EstudiosEmp;

/**
 *
 * @author Angela
 */
@Stateless
public class EstudiosEmpFacade extends AbstractFacade<EstudiosEmp> {

    @PersistenceContext(unitName = "rrhhPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EstudiosEmpFacade() {
        super(EstudiosEmp.class);
    }

    public List<EstudiosEmp> buscarEstudioEmpId(int idEstudioEmp) {
        return getEntityManager().createNamedQuery("EstudiosEmp.findByIdEstudio").setParameter("idEstudio", idEstudioEmp).getResultList();
    }

    public List<EstudiosEmp> buscarEstudioTipo(String tipo) {
        return getEntityManager().createNamedQuery("EstudiosEmp.findByTipoEstudio").setParameter("tipoEstudio", tipo).getResultList();
    }

}
