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
@Table(name = "sanciones")
@NamedQueries({
    @NamedQuery(name = "Sanciones.findAll", query = "SELECT s FROM Sanciones s"),
    @NamedQuery(name = "Sanciones.findByIdSancion", query = "SELECT s FROM Sanciones s WHERE s.idSancion = :idSancion"),
    @NamedQuery(name = "Sanciones.findByDescripcionSancion", query = "SELECT s FROM Sanciones s WHERE s.descripcionSancion = :descripcionSancion"),
   @NamedQuery(name = "Sanciones.findByFechaSancion", query = "SELECT s FROM Sanciones s WHERE s.fechaSancion = :fechaSancion"),
   @NamedQuery(name = "Sanciones.findByUserCreaSancion", query = "SELECT s FROM Sanciones s WHERE s.userCreaSancion = :userCreaSancion"),
   @NamedQuery(name = "Sanciones.findByFechaCreaSancion", query = "SELECT s FROM Sanciones s WHERE s.fechaCreaSancion = :fechaCreaSancion"),
    @NamedQuery(name = "Sanciones.findByUserModSancion", query = "SELECT s FROM Sanciones s WHERE s.userModSancion = :userModSancion"),
    @NamedQuery(name = "Sanciones.findByFechaModSancion", query = "SELECT s FROM Sanciones s WHERE s.fechaModSancion = :fechaModSancion")
})
public class Sanciones implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_sancion")
    private Integer idSancion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1024)
    @Column(name = "descripcion_sancion")
    private String descripcionSancion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_sancion")
    @Temporal(TemporalType.DATE)
    private Date fechaSancion;
    @Column(name = "user_crea_sancion")
    private Integer userCreaSancion;
    @Column(name = "fecha_crea_sancion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreaSancion;
    @Column(name = "user_mod_sancion")
    private Integer userModSancion;
    @Column(name = "fecha_mod_sancion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModSancion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSancion")
    private List<ImgDoc> imgDocList;
    @JoinColumn(name = "id_tipo_sancion", referencedColumnName = "id_tipo_sancion")
    @ManyToOne(optional = false)
    private TipoSancion idTipoSancion;
    @JoinColumn(name = "id_empleado", referencedColumnName = "id_empleado")
    @ManyToOne(optional = false)
    private Empleados idEmpleado;

    public Sanciones() {
    }

    public Sanciones(Integer idSancion) {
        this.idSancion = idSancion;
    }

    public Sanciones(Integer idSancion, String descripcionSancion, Date fechaSancion) {
        this.idSancion = idSancion;
        this.descripcionSancion = descripcionSancion;
        this.fechaSancion = fechaSancion;
    }

    public Integer getIdSancion() {
        return idSancion;
    }

    public void setIdSancion(Integer idSancion) {
        this.idSancion = idSancion;
    }

    public String getDescripcionSancion() {
        return descripcionSancion;
    }

    public void setDescripcionSancion(String descripcionSancion) {
        this.descripcionSancion = descripcionSancion;
    }

    public Date getFechaSancion() {
        return fechaSancion;
    }

    public void setFechaSancion(Date fechaSancion) {
        this.fechaSancion = fechaSancion;
    }

    public List<ImgDoc> getImgDocList() {
        return imgDocList;
    }

    public void setImgDocList(List<ImgDoc> imgDocList) {
        this.imgDocList = imgDocList;
    }

    public TipoSancion getIdTipoSancion() {
        return idTipoSancion;
    }

    public void setIdTipoSancion(TipoSancion idTipoSancion) {
        this.idTipoSancion = idTipoSancion;
    }

    public Empleados getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Empleados idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public Integer getUserCreaSancion() {
        return userCreaSancion;
    }

    public void setUserCreaSancion(Integer userCreaSancion) {
        this.userCreaSancion = userCreaSancion;
    }

    public Date getFechaCreaSancion() {
        return fechaCreaSancion;
    }

    public void setFechaCreaSancion(Date fechaCreaSancion) {
        this.fechaCreaSancion = fechaCreaSancion;
    }

    public Integer getUserModSancion() {
        return userModSancion;
    }

    public void setUserModSancion(Integer userModSancion) {
        this.userModSancion = userModSancion;
    }

    public Date getFechaModSancion() {
        return fechaModSancion;
    }

    public void setFechaModSancion(Date fechaModSancion) {
        this.fechaModSancion = fechaModSancion;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSancion != null ? idSancion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sanciones)) {
            return false;
        }
        Sanciones other = (Sanciones) object;
        if ((this.idSancion == null && other.idSancion != null) || (this.idSancion != null && !this.idSancion.equals(other.idSancion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.gob.cultura.rrhh.entidades.Sanciones[ idSancion=" + idSancion + " ]";
    }
    
}
