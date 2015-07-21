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
@Table(name = "tipo_prestacion")
@NamedQueries({
    @NamedQuery(name = "TipoPrestacion.findAll", query = "SELECT t FROM TipoPrestacion t"),
    @NamedQuery(name = "TipoPrestacion.findByIdTipoPrestacion", query = "SELECT t FROM TipoPrestacion t WHERE t.idTipoPrestacion = :idTipoPrestacion"),
    @NamedQuery(name = "TipoPrestacion.findByNombreTipoPrestacion", query = "SELECT t FROM TipoPrestacion t WHERE t.nombreTipoPrestacion = :nombreTipoPrestacion")})
public class TipoPrestacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_prestacion")
    private Integer idTipoPrestacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1024)
    @Column(name = "nombre_tipo_prestacion")
    private String nombreTipoPrestacion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoPrestacion")
    private List<Prestacion> prestacionList;

    public TipoPrestacion() {
    }

    public TipoPrestacion(Integer idTipoPrestacion) {
        this.idTipoPrestacion = idTipoPrestacion;
    }

    public TipoPrestacion(Integer idTipoPrestacion, String nombreTipoPrestacion) {
        this.idTipoPrestacion = idTipoPrestacion;
        this.nombreTipoPrestacion = nombreTipoPrestacion;
    }

    public TipoPrestacion(String nuevo_item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Integer getIdTipoPrestacion() {
        return idTipoPrestacion;
    }

    public void setIdTipoPrestacion(Integer idTipoPrestacion) {
        this.idTipoPrestacion = idTipoPrestacion;
    }

    public String getNombreTipoPrestacion() {
        return nombreTipoPrestacion;
    }

    public void setNombreTipoPrestacion(String nombreTipoPrestacion) {
        this.nombreTipoPrestacion = nombreTipoPrestacion;
    }

    public List<Prestacion> getPrestacionList() {
        return prestacionList;
    }

    public void setPrestacionList(List<Prestacion> prestacionList) {
        this.prestacionList = prestacionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoPrestacion != null ? idTipoPrestacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoPrestacion)) {
            return false;
        }
        TipoPrestacion other = (TipoPrestacion) object;
        if ((this.idTipoPrestacion == null && other.idTipoPrestacion != null) || (this.idTipoPrestacion != null && !this.idTipoPrestacion.equals(other.idTipoPrestacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.gob.cultura.rrhh.entidades.TipoPrestacion[ idTipoPrestacion=" + idTipoPrestacion + " ]";
    }
    
}
