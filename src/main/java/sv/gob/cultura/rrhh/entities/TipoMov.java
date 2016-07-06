/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.cultura.rrhh.entities;

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
@Table(name = "tipo_mov")
@NamedQueries({
    @NamedQuery(name = "TipoMov.findAll", query = "SELECT t FROM TipoMov t"),
    @NamedQuery(name = "TipoMov.findByIdTipoMov", query = "SELECT t FROM TipoMov t WHERE t.idTipoMov = :idTipoMov"),
    @NamedQuery(name = "TipoMov.findByNombreMov", query = "SELECT t FROM TipoMov t WHERE t.nombreMov = :nombreMov")})
public class TipoMov implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_mov")
    private Integer idTipoMov;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre_mov")
    private String nombreMov;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoMov")
    private List<MovimientosEmp> movimientosEmpList;

    public TipoMov() {
    }

    public TipoMov(Integer idTipoMov) {
        this.idTipoMov = idTipoMov;
    }

    public TipoMov(Integer idTipoMov, String nombreMov) {
        this.idTipoMov = idTipoMov;
        this.nombreMov = nombreMov;
    }
    
    public TipoMov(String nombreMov) {
        this.nombreMov = nombreMov;
    }

//    public TipoMov(String nuevo_item) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

    public Integer getIdTipoMov() {
        return idTipoMov;
    }

    public void setIdTipoMov(Integer idTipoMov) {
        this.idTipoMov = idTipoMov;
    }

    public String getNombreMov() {
        return nombreMov;
    }

    public void setNombreMov(String nombreMov) {
        this.nombreMov = nombreMov;
    }

    public List<MovimientosEmp> getMovimientosEmpList() {
        return movimientosEmpList;
    }

    public void setMovimientosEmpList(List<MovimientosEmp> movimientosEmpList) {
        this.movimientosEmpList = movimientosEmpList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoMov != null ? idTipoMov.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoMov)) {
            return false;
        }
        TipoMov other = (TipoMov) object;
        if ((this.idTipoMov == null && other.idTipoMov != null) || (this.idTipoMov != null && !this.idTipoMov.equals(other.idTipoMov))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.gob.cultura.rrhh.entidades.TipoMov[ idTipoMov=" + idTipoMov + " ]";
    }
    
}
