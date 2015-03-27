/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.cultura.rrhh.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author Angela
 */
@Entity
@Table(name = "prof_oficios")
@NamedQueries({
    @NamedQuery(name = "ProfOficios.findAll", query = "SELECT p FROM ProfOficios p")})
public class ProfOficios implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_prof_oficio")
    private Integer idProfOficio;
    @Size(max = 1024)
    @Column(name = "nombre_prof_oficio")
    private String nombreProfOficio;
    @ManyToMany(mappedBy = "profOficiosList")
    private List<Empleados> empleadosList;

    public ProfOficios() {
    }

    public ProfOficios(Integer idProfOficio) {
        this.idProfOficio = idProfOficio;
    }

    public Integer getIdProfOficio() {
        return idProfOficio;
    }

    public void setIdProfOficio(Integer idProfOficio) {
        this.idProfOficio = idProfOficio;
    }

    public String getNombreProfOficio() {
        return nombreProfOficio;
    }

    public void setNombreProfOficio(String nombreProfOficio) {
        this.nombreProfOficio = nombreProfOficio;
    }

    public List<Empleados> getEmpleadosList() {
        return empleadosList;
    }

    public void setEmpleadosList(List<Empleados> empleadosList) {
        this.empleadosList = empleadosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProfOficio != null ? idProfOficio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProfOficios)) {
            return false;
        }
        ProfOficios other = (ProfOficios) object;
        if ((this.idProfOficio == null && other.idProfOficio != null) || (this.idProfOficio != null && !this.idProfOficio.equals(other.idProfOficio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.gob.cultura.rrhh.facades.ProfOficios[ idProfOficio=" + idProfOficio + " ]";
    }
    
}
