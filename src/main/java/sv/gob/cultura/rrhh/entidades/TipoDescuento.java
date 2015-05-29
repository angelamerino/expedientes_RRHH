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
@Table(name = "tipo_descuento")
@NamedQueries({
    @NamedQuery(name = "TipoDescuento.findAll", query = "SELECT t FROM TipoDescuento t")})
public class TipoDescuento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_descuento")
    private Integer idDescuento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre_descuento")
    private String nombreDescuento;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDescuento")
    private List<DescuentosEmp> descuentosEmpList;

    public TipoDescuento() {
    }

    public TipoDescuento(Integer idDescuento) {
        this.idDescuento = idDescuento;
    }

    public TipoDescuento(String nombreDescuento) {
        this.nombreDescuento = nombreDescuento;
    }

    public TipoDescuento(Integer idDescuento, String nombreDescuento) {
        this.idDescuento = idDescuento;
        this.nombreDescuento = nombreDescuento;
    }


    public Integer getIdDescuento() {
        return idDescuento;
    }

    public void setIdDescuento(Integer idDescuento) {
        this.idDescuento = idDescuento;
    }

    public String getNombreDescuento() {
        return nombreDescuento;
    }

    public void setNombreDescuento(String nombreDescuento) {
        this.nombreDescuento = nombreDescuento;
    }

    public List<DescuentosEmp> getDescuentosEmpList() {
        return descuentosEmpList;
    }

    public void setDescuentosEmpList(List<DescuentosEmp> descuentosEmpList) {
        this.descuentosEmpList = descuentosEmpList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDescuento != null ? idDescuento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoDescuento)) {
            return false;
        }
        TipoDescuento other = (TipoDescuento) object;
        if ((this.idDescuento == null && other.idDescuento != null) || (this.idDescuento != null && !this.idDescuento.equals(other.idDescuento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.gob.cultura.rrhh.entidades.TipoDescuento[ idDescuento=" + idDescuento + " ]";
    }
    
}
