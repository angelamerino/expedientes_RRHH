/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.cultura.rrhh.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Angela
 */
@Entity
@Table(name = "producto_prestacion")
@NamedQueries({
    @NamedQuery(name = "ProductoPrestacion.findAll", query = "SELECT p FROM ProductoPrestacion p"),
    @NamedQuery(name = "ProductoPrestacion.findByIdPrestacion", query = "SELECT p FROM ProductoPrestacion p WHERE p.prestacion.idPrestacion = :idPrestacion")
})
public class ProductoPrestacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ProductoPrestacionPK productoPrestacionPK;
    @Column(name = "cantidad")
    private Integer cantidad;
    @JoinColumn(name = "id_producto", referencedColumnName = "id_producto", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Producto producto;
    @JoinColumn(name = "id_prestacion", referencedColumnName = "id_prestacion", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Prestacion prestacion;

    public ProductoPrestacion() {
    }

    public ProductoPrestacion(ProductoPrestacionPK productoPrestacionPK) {
        this.productoPrestacionPK = productoPrestacionPK;
    }

    public ProductoPrestacion(int idPrestacion, int idProducto) {
        this.productoPrestacionPK = new ProductoPrestacionPK(idPrestacion, idProducto);
    }

    public ProductoPrestacionPK getProductoPrestacionPK() {
        return productoPrestacionPK;
    }

    public void setProductoPrestacionPK(ProductoPrestacionPK productoPrestacionPK) {
        this.productoPrestacionPK = productoPrestacionPK;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Prestacion getPrestacion() {
        return prestacion;
    }

    public void setPrestacion(Prestacion prestacion) {
        this.prestacion = prestacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productoPrestacionPK != null ? productoPrestacionPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductoPrestacion)) {
            return false;
        }
        ProductoPrestacion other = (ProductoPrestacion) object;
        if ((this.productoPrestacionPK == null && other.productoPrestacionPK != null) || (this.productoPrestacionPK != null && !this.productoPrestacionPK.equals(other.productoPrestacionPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.gob.cultura.rrhh.entidades.ProductoPrestacion[ productoPrestacionPK=" + productoPrestacionPK + " ]";
    }

}
