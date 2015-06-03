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
@Table(name = "tipo_mejora_salarial")
@NamedQueries({
    @NamedQuery(name = "TipoMejoraSalarial.findAll", query = "SELECT t FROM TipoMejoraSalarial t"),
    @NamedQuery(name = "TipoMejoraSalarial.findByIdMejoraSal", query = "SELECT t FROM TipoMejoraSalarial t WHERE t.idMejoraSal = :idMejoraSal"),
    @NamedQuery(name = "TipoMejoraSalarial.findByNombreMejoraSal", query = "SELECT t FROM TipoMejoraSalarial t WHERE t.nombreMejoraSal = :nombreMejoraSal")})
public class TipoMejoraSalarial implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_mejora_sal")
    private Integer idMejoraSal;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre_mejora_sal")
    private String nombreMejoraSal;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMejoraSal")
    private List<HistorialSalarial> historialSalarialList;

    public TipoMejoraSalarial() {
    }

    public TipoMejoraSalarial(Integer idMejoraSal) {
        this.idMejoraSal = idMejoraSal;
    }

    public TipoMejoraSalarial(Integer idMejoraSal, String nombreMejoraSal) {
        this.idMejoraSal = idMejoraSal;
        this.nombreMejoraSal = nombreMejoraSal;
    }

    public TipoMejoraSalarial(String nuevo_item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Integer getIdMejoraSal() {
        return idMejoraSal;
    }

    public void setIdMejoraSal(Integer idMejoraSal) {
        this.idMejoraSal = idMejoraSal;
    }

    public String getNombreMejoraSal() {
        return nombreMejoraSal;
    }

    public void setNombreMejoraSal(String nombreMejoraSal) {
        this.nombreMejoraSal = nombreMejoraSal;
    }

    public List<HistorialSalarial> getHistorialSalarialList() {
        return historialSalarialList;
    }

    public void setHistorialSalarialList(List<HistorialSalarial> historialSalarialList) {
        this.historialSalarialList = historialSalarialList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMejoraSal != null ? idMejoraSal.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoMejoraSalarial)) {
            return false;
        }
        TipoMejoraSalarial other = (TipoMejoraSalarial) object;
        if ((this.idMejoraSal == null && other.idMejoraSal != null) || (this.idMejoraSal != null && !this.idMejoraSal.equals(other.idMejoraSal))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.gob.cultura.rrhh.entidades.TipoMejoraSalarial[ idMejoraSal=" + idMejoraSal + " ]";
    }
    
}
