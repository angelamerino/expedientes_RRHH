/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.cultura.rrhh.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Angela
 */
@Entity
@Table(name = "producto")
@NamedQueries({
    @NamedQuery(name = "Producto.findAll", query = "SELECT p FROM Producto p")})
public class Producto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_producto")
    private Integer idProducto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1024)
    @Column(name = "nombre_prod")
    private String nombreProd;
    @Size(max = 1024)
    @Column(name = "descripcion_prod")
    private String descripcionProd;
    @Basic(optional = false)
    @NotNull
    @Column(name = "costo_unit")
    private double costoUnit;
    @Column(name = "user_crea_prod")
    private Integer userCreaProd;
    @Column(name = "fecha_crea_prod")
    @Temporal(TemporalType.DATE)
    private Date fechaCreaProd;
    @Column(name = "user_mod_prod")
    private Integer userModProd;
    @Column(name = "fecha_mod_prod")
    @Temporal(TemporalType.DATE)
    private Date fechaModProd;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "producto")
    private List<ProductoPrestacion> productoPrestacionList;
    @JoinColumn(name = "id_proveedor", referencedColumnName = "id_proveedor")
    @ManyToOne(optional = false)
    private Proveedor idProveedor;

    public Producto() {
    }

    public Producto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public Producto(Integer idProducto, String nombreProd, double costoUnit) {
        this.idProducto = idProducto;
        this.nombreProd = nombreProd;
        this.costoUnit = costoUnit;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProd() {
        return nombreProd;
    }

    public void setNombreProd(String nombreProd) {
        this.nombreProd = nombreProd;
    }

    public String getDescripcionProd() {
        return descripcionProd;
    }

    public void setDescripcionProd(String descripcionProd) {
        this.descripcionProd = descripcionProd;
    }

    public double getCostoUnit() {
        return costoUnit;
    }

    public void setCostoUnit(double costoUnit) {
        this.costoUnit = costoUnit;
    }

    public Integer getUserCreaProd() {
        return userCreaProd;
    }

    public void setUserCreaProd(Integer userCreaProd) {
        this.userCreaProd = userCreaProd;
    }

    public Date getFechaCreaProd() {
        return fechaCreaProd;
    }

    public void setFechaCreaProd(Date fechaCreaProd) {
        this.fechaCreaProd = fechaCreaProd;
    }

    public Integer getUserModProd() {
        return userModProd;
    }

    public void setUserModProd(Integer userModProd) {
        this.userModProd = userModProd;
    }

    public Date getFechaModProd() {
        return fechaModProd;
    }

    public void setFechaModProd(Date fechaModProd) {
        this.fechaModProd = fechaModProd;
    }

    public List<ProductoPrestacion> getProductoPrestacionList() {
        return productoPrestacionList;
    }

    public void setProductoPrestacionList(List<ProductoPrestacion> productoPrestacionList) {
        this.productoPrestacionList = productoPrestacionList;
    }

    public Proveedor getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Proveedor idProveedor) {
        this.idProveedor = idProveedor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProducto != null ? idProducto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Producto)) {
            return false;
        }
        Producto other = (Producto) object;
        if ((this.idProducto == null && other.idProducto != null) || (this.idProducto != null && !this.idProducto.equals(other.idProducto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.gob.cultura.rrhh.entidades.Producto[ idProducto=" + idProducto + " ]";
    }
    
}
