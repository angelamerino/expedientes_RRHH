/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.cultura.rrhh.entities;

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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Angela
 */
@Entity
@Table(name = "experiencia_laboral")
@NamedQueries({
    @NamedQuery(name = "ExperienciaLaboral.findAll", query = "SELECT e FROM ExperienciaLaboral e"),
    @NamedQuery(name = "ExperienciaLaboral.findByIdExpLab", query = "SELECT e FROM ExperienciaLaboral e WHERE e.idExpLab = :idExpLab"),
    @NamedQuery(name = "ExperienciaLaboral.findByInstitucionEmpresa", query = "SELECT e FROM ExperienciaLaboral e WHERE e.institucionEmpresa = :institucionEmpresa"),
    @NamedQuery(name = "ExperienciaLaboral.findByCargoExpLab", query = "SELECT e FROM ExperienciaLaboral e WHERE e.cargoExpLab = :cargoExpLab"),
    @NamedQuery(name = "ExperienciaLaboral.findByFechaDesdeExpLab", query = "SELECT e FROM ExperienciaLaboral e WHERE e.fechaDesdeExpLab = :fechaDesdeExpLab"),
    @NamedQuery(name = "ExperienciaLaboral.findByFechaHastaExpLab", query = "SELECT e FROM ExperienciaLaboral e WHERE e.fechaHastaExpLab = :fechaHastaExpLab"),
    @NamedQuery(name = "ExperienciaLaboral.findBySectorExpLab", query = "SELECT e FROM ExperienciaLaboral e WHERE e.sectorExpLab = :sectorExpLab AND e.idEmpleado.idEmpleado = :idEmpleado"),
    @NamedQuery(name = "ExperienciaLaboral.findByUserCreaExp", query = "SELECT e FROM ExperienciaLaboral e WHERE e.userCreaExp = :userCreaExp"),
    @NamedQuery(name = "ExperienciaLaboral.findByFechaCreaExp", query = "SELECT e FROM ExperienciaLaboral e WHERE e.fechaCreaExp = :fechaCreaExp"),
    @NamedQuery(name = "ExperienciaLaboral.findByUserModExp", query = "SELECT e FROM ExperienciaLaboral e WHERE e.userModExp = :userModExp"),
    @NamedQuery(name = "ExperienciaLaboral.findByFechaModExp", query = "SELECT e FROM ExperienciaLaboral e WHERE e.fechaModExp = :fechaModExp")})
public class ExperienciaLaboral implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_exp_lab")
    private Integer idExpLab;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1024)
    @Column(name = "institucion_empresa")
    private String institucionEmpresa;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "cargo_exp_lab")
    private String cargoExpLab;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_desde_exp_lab")
    @Temporal(TemporalType.DATE)
    private Date fechaDesdeExpLab;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_hasta_exp_lab")
    @Temporal(TemporalType.DATE)
    private Date fechaHastaExpLab;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "sector_exp_lab")
    private String sectorExpLab;
    @Column(name = "user_crea_exp")
    private Integer userCreaExp;
    @Column(name = "fecha_crea_exp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreaExp;
    @Column(name = "user_mod_exp")
    private Integer userModExp;
    @Column(name = "fecha_mod_exp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModExp;
    @JoinColumn(name = "id_empleado", referencedColumnName = "id_empleado")
    @ManyToOne(optional = false)
    private Empleados idEmpleado;

    public ExperienciaLaboral() {
    }

    public ExperienciaLaboral(Integer idExpLab) {
        this.idExpLab = idExpLab;
    }

    public ExperienciaLaboral(Integer idExpLab, String institucionEmpresa, String cargoExpLab, Date fechaDesdeExpLab, Date fechaHastaExpLab, String sectorExpLab) {
        this.idExpLab = idExpLab;
        this.institucionEmpresa = institucionEmpresa;
        this.cargoExpLab = cargoExpLab;
        this.fechaDesdeExpLab = fechaDesdeExpLab;
        this.fechaHastaExpLab = fechaHastaExpLab;
        this.sectorExpLab = sectorExpLab;
    }

    public Integer getIdExpLab() {
        return idExpLab;
    }

    public void setIdExpLab(Integer idExpLab) {
        this.idExpLab = idExpLab;
    }

    public String getInstitucionEmpresa() {
        return institucionEmpresa;
    }

    public void setInstitucionEmpresa(String institucionEmpresa) {
        this.institucionEmpresa = institucionEmpresa;
    }

    public String getCargoExpLab() {
        return cargoExpLab;
    }

    public void setCargoExpLab(String cargoExpLab) {
        this.cargoExpLab = cargoExpLab;
    }

    public Date getFechaDesdeExpLab() {
        return fechaDesdeExpLab;
    }

    public void setFechaDesdeExpLab(Date fechaDesdeExpLab) {
        this.fechaDesdeExpLab = fechaDesdeExpLab;
    }

    public Date getFechaHastaExpLab() {
        return fechaHastaExpLab;
    }

    public void setFechaHastaExpLab(Date fechaHastaExpLab) {
        this.fechaHastaExpLab = fechaHastaExpLab;
    }

    public String getSectorExpLab() {
        return sectorExpLab;
    }

    public void setSectorExpLab(String sectorExpLab) {
        this.sectorExpLab = sectorExpLab;
    }

    public Integer getUserCreaExp() {
        return userCreaExp;
    }

    public void setUserCreaExp(Integer userCreaExp) {
        this.userCreaExp = userCreaExp;
    }

    public Date getFechaCreaExp() {
        return fechaCreaExp;
    }

    public void setFechaCreaExp(Date fechaCreaExp) {
        this.fechaCreaExp = fechaCreaExp;
    }

    public Integer getUserModExp() {
        return userModExp;
    }

    public void setUserModExp(Integer userModExp) {
        this.userModExp = userModExp;
    }

    public Date getFechaModExp() {
        return fechaModExp;
    }

    public void setFechaModExp(Date fechaModExp) {
        this.fechaModExp = fechaModExp;
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
        hash += (idExpLab != null ? idExpLab.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ExperienciaLaboral)) {
            return false;
        }
        ExperienciaLaboral other = (ExperienciaLaboral) object;
        if ((this.idExpLab == null && other.idExpLab != null) || (this.idExpLab != null && !this.idExpLab.equals(other.idExpLab))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.gob.cultura.rrhh.entidades.ExperienciaLaboral[ idExpLab=" + idExpLab + " ]";
    }
    
}
