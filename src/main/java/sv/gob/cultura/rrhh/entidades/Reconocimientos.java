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
@Table(name = "reconocimientos")
@NamedQueries({
    @NamedQuery(name = "Reconocimientos.findAll", query = "SELECT r FROM Reconocimientos r"),
    @NamedQuery(name = "Reconocimientos.findByIdReconocimiento", query = "SELECT r FROM Reconocimientos r WHERE r.idReconocimiento = :idReconocimiento"),
    @NamedQuery(name = "Reconocimientos.findByDescripcionReconocimiento", query = "SELECT r FROM Reconocimientos r WHERE r.descripcionReconocimiento = :descripcionReconocimiento"),
    @NamedQuery(name = "Reconocimientos.findByFechaReconocimiento", query = "SELECT r FROM Reconocimientos r WHERE r.fechaReconocimiento = :fechaReconocimiento"),
    @NamedQuery(name = "Reconocimientos.findByUserCreaRec", query = "SELECT r FROM Reconocimientos r WHERE r.userCreaRec = :userCreaRec"),
    @NamedQuery(name = "Reconocimientos.findByFechaCreaRec", query = "SELECT r FROM Reconocimientos r WHERE r.fechaCreaRec = :fechaCreaRec"),
    @NamedQuery(name = "Reconocimientos.findByUserModRec", query = "SELECT r FROM Reconocimientos r WHERE r.userModRec = :userModRec"),
    @NamedQuery(name = "Reconocimientos.findByFechaModRec", query = "SELECT r FROM Reconocimientos r WHERE r.fechaModRec = :fechaModRec")})
public class Reconocimientos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_reconocimiento")
    private Integer idReconocimiento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1024)
    @Column(name = "descripcion_reconocimiento")
    private String descripcionReconocimiento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_reconocimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaReconocimiento;
    @Column(name = "user_crea_rec")
    private Integer userCreaRec;
    @Column(name = "fecha_crea_rec")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreaRec;
    @Column(name = "user_mod_rec")
    private Integer userModRec;
    @Column(name = "fecha_mod_rec")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModRec;
    @OneToMany(mappedBy = "idReconocimiento")
    private List<ImgDoc> imgDocList;
    @JoinColumn(name = "id_empleado", referencedColumnName = "id_empleado")
    @ManyToOne
    private Empleados idEmpleado;

    public Reconocimientos() {
    }

    public Reconocimientos(Integer idReconocimiento) {
        this.idReconocimiento = idReconocimiento;
    }

    public Reconocimientos(Integer idReconocimiento, String descripcionReconocimiento, Date fechaReconocimiento) {
        this.idReconocimiento = idReconocimiento;
        this.descripcionReconocimiento = descripcionReconocimiento;
        this.fechaReconocimiento = fechaReconocimiento;
    }

    public Integer getIdReconocimiento() {
        return idReconocimiento;
    }

    public void setIdReconocimiento(Integer idReconocimiento) {
        this.idReconocimiento = idReconocimiento;
    }

    public String getDescripcionReconocimiento() {
        return descripcionReconocimiento;
    }

    public void setDescripcionReconocimiento(String descripcionReconocimiento) {
        this.descripcionReconocimiento = descripcionReconocimiento;
    }

    public Date getFechaReconocimiento() {
        return fechaReconocimiento;
    }

    public void setFechaReconocimiento(Date fechaReconocimiento) {
        this.fechaReconocimiento = fechaReconocimiento;
    }

    public Integer getUserCreaRec() {
        return userCreaRec;
    }

    public void setUserCreaRec(Integer userCreaRec) {
        this.userCreaRec = userCreaRec;
    }

    public Date getFechaCreaRec() {
        return fechaCreaRec;
    }

    public void setFechaCreaRec(Date fechaCreaRec) {
        this.fechaCreaRec = fechaCreaRec;
    }

    public Integer getUserModRec() {
        return userModRec;
    }

    public void setUserModRec(Integer userModRec) {
        this.userModRec = userModRec;
    }

    public Date getFechaModRec() {
        return fechaModRec;
    }

    public void setFechaModRec(Date fechaModRec) {
        this.fechaModRec = fechaModRec;
    }

    public List<ImgDoc> getImgDocList() {
        return imgDocList;
    }

    public void setImgDocList(List<ImgDoc> imgDocList) {
        this.imgDocList = imgDocList;
    }

    public Empleados getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Empleados idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idReconocimiento != null ? idReconocimiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reconocimientos)) {
            return false;
        }
        Reconocimientos other = (Reconocimientos) object;
        if ((this.idReconocimiento == null && other.idReconocimiento != null) || (this.idReconocimiento != null && !this.idReconocimiento.equals(other.idReconocimiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.gob.cultura.rrhh.entidades.Reconocimientos[ idReconocimiento=" + idReconocimiento + " ]";
    }
    
}
