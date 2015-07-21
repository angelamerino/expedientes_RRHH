/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.cultura.rrhh.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Angela
 */
@Embeddable
public class ProductoPrestacionPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_prestacion")
    private int idPrestacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_producto")
    private int idProducto;

    public ProductoPrestacionPK() {
    }

    public ProductoPrestacionPK(int idPrestacion, int idProducto) {
        this.idPrestacion = idPrestacion;
        this.idProducto = idProducto;
    }

    public int getIdPrestacion() {
        return idPrestacion;
    }

    public void setIdPrestacion(int idPrestacion) {
        this.idPrestacion = idPrestacion;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idPrestacion;
        hash += (int) idProducto;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductoPrestacionPK)) {
            return false;
        }
        ProductoPrestacionPK other = (ProductoPrestacionPK) object;
        if (this.idPrestacion != other.idPrestacion) {
            return false;
        }
        if (this.idProducto != other.idProducto) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.gob.cultura.rrhh.entidades.ProductoPrestacionPK[ idPrestacion=" + idPrestacion + ", idProducto=" + idProducto + " ]";
    }
    
}
