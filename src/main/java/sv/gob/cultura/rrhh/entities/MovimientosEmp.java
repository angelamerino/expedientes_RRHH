/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.cultura.rrhh.entities;

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
@Table(name = "movimientos_emp")
@NamedQueries({
    @NamedQuery(name = "MovimientosEmp.findAll", query = "SELECT m FROM MovimientosEmp m"),
    @NamedQuery(name = "MovimientosEmp.findByIdMovEmp", query = "SELECT m FROM MovimientosEmp m WHERE m.idMovEmp = :idMovEmp"),
    @NamedQuery(name = "MovimientosEmp.findByCargoActualMov", query = "SELECT m FROM MovimientosEmp m WHERE m.cargoActualMov = :cargoActualMov"),
    @NamedQuery(name = "MovimientosEmp.findByNuevoCargoMov", query = "SELECT m FROM MovimientosEmp m WHERE m.nuevoCargoMov = :nuevoCargoMov"),
    @NamedQuery(name = "MovimientosEmp.findByFechaMov", query = "SELECT m FROM MovimientosEmp m WHERE m.fechaMov = :fechaMov"),
    @NamedQuery(name = "MovimientosEmp.findByFechaNoti", query = "SELECT m FROM MovimientosEmp m WHERE m.fechaNoti = :fechaNoti"),
    @NamedQuery(name = "MovimientosEmp.findByFechaIniTemp", query = "SELECT m FROM MovimientosEmp m WHERE m.fechaIniTemp = :fechaIniTemp"),
    @NamedQuery(name = "MovimientosEmp.findByFechaFinTemp", query = "SELECT m FROM MovimientosEmp m WHERE m.fechaFinTemp = :fechaFinTemp"),
    @NamedQuery(name = "MovimientosEmp.findByUserCreaMov", query = "SELECT m FROM MovimientosEmp m WHERE m.userCreaMov = :userCreaMov"),
    @NamedQuery(name = "MovimientosEmp.findByFechaCreaMov", query = "SELECT m FROM MovimientosEmp m WHERE m.fechaCreaMov = :fechaCreaMov"),
    @NamedQuery(name = "MovimientosEmp.findByUserModMov", query = "SELECT m FROM MovimientosEmp m WHERE m.userModMov = :userModMov"),
    @NamedQuery(name = "MovimientosEmp.findByFechaModMov", query = "SELECT m FROM MovimientosEmp m WHERE m.fechaModMov = :fechaModMov")})
public class MovimientosEmp implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_mov_emp")
    private Integer idMovEmp;
    @Size(max = 200)
    @Column(name = "cargo_actual_mov")
    private String cargoActualMov;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "nuevo_cargo_mov")
    private String nuevoCargoMov;
    @Size(max = 1024)
    @Column(name = "dependencia_actual")
    private String dependenciaActual;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_mov")
    @Temporal(TemporalType.DATE)
    private Date fechaMov;
    @Column(name = "fecha_noti")
    @Temporal(TemporalType.DATE)
    private Date fechaNoti;
    @Column(name = "fecha_ini_temp")
    @Temporal(TemporalType.DATE)
    private Date fechaIniTemp;
    @Column(name = "fecha_fin_temp")
    @Temporal(TemporalType.DATE)
    private Date fechaFinTemp;
    @Column(name = "user_crea_mov")
    private Integer userCreaMov;
    @Column(name = "fecha_crea_mov")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreaMov;
    @Column(name = "user_mod_mov")
    private Integer userModMov;
    @Column(name = "fecha_mod_mov")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModMov;
    @OneToMany(mappedBy = "idMovEmp")
    private List<ImgDoc> imgDocList;
    @JoinColumn(name = "id_tipo_mov", referencedColumnName = "id_tipo_mov")
    @ManyToOne(optional = false)
    private TipoMov idTipoMov;
    @JoinColumn(name = "id_empleado", referencedColumnName = "id_empleado")
    @ManyToOne(optional = false)
    private Empleados idEmpleado;

    public MovimientosEmp() {
    }

    public MovimientosEmp(Integer idMovEmp) {
        this.idMovEmp = idMovEmp;
    }

    public MovimientosEmp(Integer idMovEmp, String nuevoCargoMov, Date fechaMov) {
        this.idMovEmp = idMovEmp;
        this.nuevoCargoMov = nuevoCargoMov;
        this.fechaMov = fechaMov;
    }

    public Integer getIdMovEmp() {
        return idMovEmp;
    }

    public void setIdMovEmp(Integer idMovEmp) {
        this.idMovEmp = idMovEmp;
    }

    public String getCargoActualMov() {
        return cargoActualMov;
    }

    public void setCargoActualMov(String cargoActualMov) {
        this.cargoActualMov = cargoActualMov;
    }

    public String getNuevoCargoMov() {
        return nuevoCargoMov;
    }

    public void setNuevoCargoMov(String nuevoCargoMov) {
        this.nuevoCargoMov = nuevoCargoMov;
    }

    public String getDependenciaActual() {
        return dependenciaActual;
    }

    public void setDependenciaActual(String dependenciaActual) {
        this.dependenciaActual = dependenciaActual;
    }

    public Date getFechaMov() {
        return fechaMov;
    }

    public void setFechaMov(Date fechaMov) {
        this.fechaMov = fechaMov;
    }

    public Date getFechaNoti() {
        return fechaNoti;
    }

    public void setFechaNoti(Date fechaNoti) {
        this.fechaNoti = fechaNoti;
    }

    public Date getFechaIniTemp() {
        return fechaIniTemp;
    }

    public void setFechaIniTemp(Date fechaIniTemp) {
        this.fechaIniTemp = fechaIniTemp;
    }

    public Date getFechaFinTemp() {
        return fechaFinTemp;
    }

    public void setFechaFinTemp(Date fechaFinTemp) {
        this.fechaFinTemp = fechaFinTemp;
    }

    public Integer getUserCreaMov() {
        return userCreaMov;
    }

    public void setUserCreaMov(Integer userCreaMov) {
        this.userCreaMov = userCreaMov;
    }

    public Date getFechaCreaMov() {
        return fechaCreaMov;
    }

    public void setFechaCreaMov(Date fechaCreaMov) {
        this.fechaCreaMov = fechaCreaMov;
    }

    public Integer getUserModMov() {
        return userModMov;
    }

    public void setUserModMov(Integer userModMov) {
        this.userModMov = userModMov;
    }

    public Date getFechaModMov() {
        return fechaModMov;
    }

    public void setFechaModMov(Date fechaModMov) {
        this.fechaModMov = fechaModMov;
    }

    public List<ImgDoc> getImgDocList() {
        return imgDocList;
    }

    public void setImgDocList(List<ImgDoc> imgDocList) {
        this.imgDocList = imgDocList;
    }

    public TipoMov getIdTipoMov() {
        return idTipoMov;
    }

    public void setIdTipoMov(TipoMov idTipoMov) {
        this.idTipoMov = idTipoMov;
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
        hash += (idMovEmp != null ? idMovEmp.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MovimientosEmp)) {
            return false;
        }
        MovimientosEmp other = (MovimientosEmp) object;
        if ((this.idMovEmp == null && other.idMovEmp != null) || (this.idMovEmp != null && !this.idMovEmp.equals(other.idMovEmp))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.gob.cultura.rrhh.entidades.MovimientosEmp[ idMovEmp=" + idMovEmp + " ]";
    }

}
