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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
    @NamedQuery(name = "Producto.findAll", query = "SELECT p FROM Producto p"),
    @NamedQuery(name = "Producto.findByIdProducto", query = "SELECT p FROM Producto p WHERE p.idProducto = :idProducto"),
    @NamedQuery(name = "Producto.findByNombreProd", query = "SELECT p FROM Producto p WHERE p.nombreProd = :nombreProd"),
    @NamedQuery(name = "Producto.findByDescripcionProd", query = "SELECT p FROM Producto p WHERE p.descripcionProd = :descripcionProd"),
    @NamedQuery(name = "Producto.findByCostoUnit", query = "SELECT p FROM Producto p WHERE p.costoUnit = :costoUnit"),
    @NamedQuery(name = "Producto.findByUserCreaProd", query = "SELECT p FROM Producto p WHERE p.userCreaProd = :userCreaProd"),
    @NamedQuery(name = "Producto.findByFechaCreaProd", query = "SELECT p FROM Producto p WHERE p.fechaCreaProd = :fechaCreaProd"),
    @NamedQuery(name = "Producto.findByUserModProd", query = "SELECT p FROM Producto p WHERE p.userModProd = :userModProd"),
    @NamedQuery(name = "Producto.findByFechaModProd", query = "SELECT p FROM Producto p WHERE p.fechaModProd = :fechaModProd")})
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
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreaProd;
    @Column(name = "user_mod_prod")
    private Integer userModProd;
    @Column(name = "fecha_mod_prod")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModProd;
    @ManyToMany(mappedBy = "productoList")
    private List<Prestacion> prestacionList;
    @JoinTable(name = "prod_prov", joinColumns = {
        @JoinColumn(name = "id_producto", referencedColumnName = "id_producto")}, inverseJoinColumns = {
        @JoinColumn(name = "id_proveedor", referencedColumnName = "id_proveedor")})
    @ManyToMany
    private List<Proveedor> proveedorList;

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

    public List<Prestacion> getPrestacionList() {
        return prestacionList;
    }

    public void setPrestacionList(List<Prestacion> prestacionList) {
        this.prestacionList = prestacionList;
    }

    public List<Proveedor> getProveedorList() {
        return proveedorList;
    }

    public void setProveedorList(List<Proveedor> proveedorList) {
        this.proveedorList = proveedorList;
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