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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Angela
 */
@Entity
@Table(name = "prestacion")
@NamedQueries({
    @NamedQuery(name = "Prestacion.findAll", query = "SELECT p FROM Prestacion p"),
    @NamedQuery(name = "Prestacion.findByIdPrestacion", query = "SELECT p FROM Prestacion p WHERE p.idPrestacion = :idPrestacion"),
    @NamedQuery(name = "Prestacion.findByCantidad", query = "SELECT p FROM Prestacion p WHERE p.cantidad = :cantidad"),
    @NamedQuery(name = "Prestacion.findByUserCreaPrestacion", query = "SELECT p FROM Prestacion p WHERE p.userCreaPrestacion = :userCreaPrestacion"),
    @NamedQuery(name = "Prestacion.findByFechaCreaPrestacion", query = "SELECT p FROM Prestacion p WHERE p.fechaCreaPrestacion = :fechaCreaPrestacion"),
    @NamedQuery(name = "Prestacion.findByUserModPrestacion", query = "SELECT p FROM Prestacion p WHERE p.userModPrestacion = :userModPrestacion"),
    @NamedQuery(name = "Prestacion.findByFechaModPrestacion", query = "SELECT p FROM Prestacion p WHERE p.fechaModPrestacion = :fechaModPrestacion")})
public class Prestacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_prestacion")
    private Integer idPrestacion;
    @Column(name = "cantidad")
    private Integer cantidad;
    @Column(name = "user_crea_prestacion")
    private Integer userCreaPrestacion;
    @Column(name = "fecha_crea_prestacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreaPrestacion;
    @Column(name = "user_mod_prestacion")
    private Integer userModPrestacion;
    @Column(name = "fecha_mod_prestacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModPrestacion;
    @JoinTable(name = "producto_prestacion", joinColumns = {
        @JoinColumn(name = "id_prestacion", referencedColumnName = "id_prestacion")}, inverseJoinColumns = {
        @JoinColumn(name = "id_producto", referencedColumnName = "id_producto")})
    @ManyToMany
    private List<Producto> productoList;
    @ManyToMany(mappedBy = "prestacionList")
    private List<Empleados> empleadosList;
    @JoinColumn(name = "id_tipo_prestacion", referencedColumnName = "id_tipo_prestacion")
    @ManyToOne(optional = false)
    private TipoPrestacion idTipoPrestacion;
    @JoinColumn(name = "id_anio", referencedColumnName = "id_anio")
    @ManyToOne(optional = false)
    private Anio idAnio;

    public Prestacion() {
    }

    public Prestacion(Integer idPrestacion) {
        this.idPrestacion = idPrestacion;
    }

    public Integer getIdPrestacion() {
        return idPrestacion;
    }

    public void setIdPrestacion(Integer idPrestacion) {
        this.idPrestacion = idPrestacion;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getUserCreaPrestacion() {
        return userCreaPrestacion;
    }

    public void setUserCreaPrestacion(Integer userCreaPrestacion) {
        this.userCreaPrestacion = userCreaPrestacion;
    }

    public Date getFechaCreaPrestacion() {
        return fechaCreaPrestacion;
    }

    public void setFechaCreaPrestacion(Date fechaCreaPrestacion) {
        this.fechaCreaPrestacion = fechaCreaPrestacion;
    }

    public Integer getUserModPrestacion() {
        return userModPrestacion;
    }

    public void setUserModPrestacion(Integer userModPrestacion) {
        this.userModPrestacion = userModPrestacion;
    }

    public Date getFechaModPrestacion() {
        return fechaModPrestacion;
    }

    public void setFechaModPrestacion(Date fechaModPrestacion) {
        this.fechaModPrestacion = fechaModPrestacion;
    }

    public List<Producto> getProductoList() {
        return productoList;
    }

    public void setProductoList(List<Producto> productoList) {
        this.productoList = productoList;
    }

    public List<Empleados> getEmpleadosList() {
        return empleadosList;
    }

    public void setEmpleadosList(List<Empleados> empleadosList) {
        this.empleadosList = empleadosList;
    }

    public TipoPrestacion getIdTipoPrestacion() {
        return idTipoPrestacion;
    }

    public void setIdTipoPrestacion(TipoPrestacion idTipoPrestacion) {
        this.idTipoPrestacion = idTipoPrestacion;
    }

    public Anio getIdAnio() {
        return idAnio;
    }

    public void setIdAnio(Anio idAnio) {
        this.idAnio = idAnio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPrestacion != null ? idPrestacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Prestacion)) {
            return false;
        }
        Prestacion other = (Prestacion) object;
        if ((this.idPrestacion == null && other.idPrestacion != null) || (this.idPrestacion != null && !this.idPrestacion.equals(other.idPrestacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.gob.cultura.rrhh.entidades.Prestacion[ idPrestacion=" + idPrestacion + " ]";
    }
    
}
