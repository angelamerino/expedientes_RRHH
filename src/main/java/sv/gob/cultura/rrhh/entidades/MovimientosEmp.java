/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.cultura.rrhh.entidades;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author Angela
 */
@Entity
@Table(name = "movimientos_emp")
@NamedQueries({
    @NamedQuery(name = "MovimientosEmp.findAll", query = "SELECT m FROM MovimientosEmp m")})
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
    @Size(max = 200)
    @Column(name = "nuevo_cargo_mov")
    private String nuevoCargoMov;
    @Size(max = 1024)
    @Column(name = "dependencia_actual")
    private String dependenciaActual;
    @Size(max = 1024)
    @Column(name = "nueva_dependencia")
    private String nuevaDependencia;
    @Size(max = 1024)
    @Column(name = "dependencia_temp")
    private String dependenciaTemp;
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
    @Size(max = 25)
    @Column(name = "doc_respaldo_mov")
    private String docRespaldoMov;
    @JoinColumn(name = "id_tipo_mov", referencedColumnName = "id_tipo_mov")
    @ManyToOne(optional = false)
    private TipoMov idTipoMov;
    @JoinColumn(name = "nr_empleado", referencedColumnName = "nr_empleado")
    @ManyToOne(optional = false)
    private Empleados nrEmpleado;

    public MovimientosEmp() {
    }

    public MovimientosEmp(Integer idMovEmp) {
        this.idMovEmp = idMovEmp;
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

    public String getNuevaDependencia() {
        return nuevaDependencia;
    }

    public void setNuevaDependencia(String nuevaDependencia) {
        this.nuevaDependencia = nuevaDependencia;
    }

    public String getDependenciaTemp() {
        return dependenciaTemp;
    }

    public void setDependenciaTemp(String dependenciaTemp) {
        this.dependenciaTemp = dependenciaTemp;
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

    public String getDocRespaldoMov() {
        return docRespaldoMov;
    }

    public void setDocRespaldoMov(String docRespaldoMov) {
        this.docRespaldoMov = docRespaldoMov;
    }

    public TipoMov getIdTipoMov() {
        return idTipoMov;
    }

    public void setIdTipoMov(TipoMov idTipoMov) {
        this.idTipoMov = idTipoMov;
    }

    public Empleados getNrEmpleado() {
        return nrEmpleado;
    }

    public void setNrEmpleado(Empleados nrEmpleado) {
        this.nrEmpleado = nrEmpleado;
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
        return "sv.gob.cultura.rrhh.facades.MovimientosEmp[ idMovEmp=" + idMovEmp + " ]";
    }
    
}
