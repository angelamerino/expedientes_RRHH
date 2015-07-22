/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.cultura.rrhh.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Angela
 */
@Entity
@Table(name = "prof_oficios")
@NamedQueries({
    @NamedQuery(name = "ProfOficios.findAll", query = "SELECT p FROM ProfOficios p"),
    @NamedQuery(name = "ProfOficios.findByIdProfOficio", query = "SELECT p FROM ProfOficios p WHERE p.idProfOficio = :idProfOficio"),
    @NamedQuery(name = "ProfOficios.findByNombreProfOficio", query = "SELECT p FROM ProfOficios p WHERE p.nombreProfOficio = :nombreProfOficio")})
public class ProfOficios implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_prof_oficio")
    private Integer idProfOficio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1024)
    @Column(name = "nombre_prof_oficio")
    private String nombreProfOficio;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProfOficio")
    private List<EstudiosEmp> estudiosEmpList;

    public ProfOficios() {
    }

    public ProfOficios(Integer idProfOficio) {
        this.idProfOficio = idProfOficio;
    }

    public ProfOficios(Integer idProfOficio, String nombreProfOficio) {
        this.idProfOficio = idProfOficio;
        this.nombreProfOficio = nombreProfOficio;
    }

    public ProfOficios(String nombreProfOficio) {
        this.nombreProfOficio = nombreProfOficio;
    }
//    public ProfOficios(String nuevo_item) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

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

    public List<EstudiosEmp> getEstudiosEmpList() {
        return estudiosEmpList;
    }

    public void setEstudiosEmpList(List<EstudiosEmp> estudiosEmpList) {
        this.estudiosEmpList = estudiosEmpList;
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
        return "sv.gob.cultura.rrhh.entidades.ProfOficios[ idProfOficio=" + idProfOficio + " ]";
    }
    
}
