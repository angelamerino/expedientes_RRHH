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
@Table(name = "asignar_asistencia_cap")
@NamedQueries({
    @NamedQuery(name = "AsignarAsistenciaCap.findAll", query = "SELECT a FROM AsignarAsistenciaCap a"),
    @NamedQuery(name = "AsignarAsistenciaCap.findByIdAsigAsis", query = "SELECT a FROM AsignarAsistenciaCap a WHERE a.idAsigAsis = :idAsigAsis"),
    @NamedQuery(name = "AsignarAsistenciaCap.findByidCap", query = "SELECT a FROM AsignarAsistenciaCap a WHERE a.idCap.idCap = :idCap"),
    @NamedQuery(name = "AsignarAsistenciaCap.findByidEmpleado", query = "SELECT a FROM AsignarAsistenciaCap a WHERE a.idEmpleado.idEmpleado = :idEmpleado"),
    @NamedQuery(name = "AsignarAsistenciaCap.findByCapAsignada", query = "SELECT a FROM AsignarAsistenciaCap a WHERE a.capAsignada = :capAsignada"),
    @NamedQuery(name = "AsignarAsistenciaCap.findByCapAsistida", query = "SELECT a FROM AsignarAsistenciaCap a WHERE a.capAsistida = :capAsistida"),
    @NamedQuery(name = "AsignarAsistenciaCap.findByUserCreaAsigAsis", query = "SELECT a FROM AsignarAsistenciaCap a WHERE a.userCreaAsigAsis = :userCreaAsigAsis"),
    @NamedQuery(name = "AsignarAsistenciaCap.findByFechaCreaAsigAsis", query = "SELECT a FROM AsignarAsistenciaCap a WHERE a.fechaCreaAsigAsis = :fechaCreaAsigAsis"),
    @NamedQuery(name = "AsignarAsistenciaCap.findByUserModAsigAsis", query = "SELECT a FROM AsignarAsistenciaCap a WHERE a.userModAsigAsis = :userModAsigAsis")
    //@NamedQuery(name = "AsignarAsistenciaCap.findByFechaModAisgAsis", query = "SELECT a FROM AsignarAsistenciaCap a WHERE a.fechaModAisgAsis = :fechaModAisgAsis")
})
public class AsignarAsistenciaCap implements Serializable {
    @Size(max = 1024)
    @Column(name = "justificacion")
    private String justificacion;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_asig_asis")
    private Integer idAsigAsis;
    @Column(name = "cap_asignada")
    private Boolean capAsignada;
    @Column(name = "cap_asistida")
    private Boolean capAsistida;
    @Column(name = "user_crea_asig_asis")
    private Integer userCreaAsigAsis;
    @Column(name = "fecha_crea_asig_asis")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreaAsigAsis;
    @Column(name = "user_mod_asig_asis")
    private Integer userModAsigAsis;
    @Column(name = "fecha_mod_asig_asis")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModAsigAsis;
    @JoinColumn(name = "id_empleado", referencedColumnName = "id_empleado")
    @ManyToOne(optional = false)
    private Empleados idEmpleado;
    @JoinColumn(name = "id_cap", referencedColumnName = "id_cap")
    @ManyToOne(optional = false)
    private Capacitaciones idCap;

    public AsignarAsistenciaCap() {
    }

    public AsignarAsistenciaCap(Integer idAsigAsis) {
        this.idAsigAsis = idAsigAsis;
    }

    public Integer getIdAsigAsis() {
        return idAsigAsis;
    }

    public void setIdAsigAsis(Integer idAsigAsis) {
        this.idAsigAsis = idAsigAsis;
    }

    public Boolean getCapAsignada() {
        return capAsignada;
    }

    public void setCapAsignada(Boolean capAsignada) {
        this.capAsignada = capAsignada;
    }

    public Boolean getCapAsistida() {
        return capAsistida;
    }

    public void setCapAsistida(Boolean capAsistida) {
        this.capAsistida = capAsistida;
    }

    public Integer getUserCreaAsigAsis() {
        return userCreaAsigAsis;
    }

    public void setUserCreaAsigAsis(Integer userCreaAsigAsis) {
        this.userCreaAsigAsis = userCreaAsigAsis;
    }

    public Date getFechaCreaAsigAsis() {
        return fechaCreaAsigAsis;
    }

    public void setFechaCreaAsigAsis(Date fechaCreaAsigAsis) {
        this.fechaCreaAsigAsis = fechaCreaAsigAsis;
    }

    public Integer getUserModAsigAsis() {
        return userModAsigAsis;
    }

    public void setUserModAsigAsis(Integer userModAsigAsis) {
        this.userModAsigAsis = userModAsigAsis;
    }

    public Date getFechaModAsigAsis() {
        return fechaModAsigAsis;
    }

    public void setFechaModAsigAsis(Date fechaModAsigAsis) {
        this.fechaModAsigAsis = fechaModAsigAsis;
    }

    public Empleados getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Empleados idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public Capacitaciones getIdCap() {
        return idCap;
    }

    public void setIdCap(Capacitaciones idCap) {
        this.idCap = idCap;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAsigAsis != null ? idAsigAsis.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AsignarAsistenciaCap)) {
            return false;
        }
        AsignarAsistenciaCap other = (AsignarAsistenciaCap) object;
        if ((this.idAsigAsis == null && other.idAsigAsis != null) || (this.idAsigAsis != null && !this.idAsigAsis.equals(other.idAsigAsis))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.gob.cultura.rrhh.entidades.AsignarAsistenciaCap[ idAsigAsis=" + idAsigAsis + " ]";
    }

    public String getJustificacion() {
        return justificacion;
    }

    public void setJustificacion(String justificacion) {
        this.justificacion = justificacion;
    }
    
}
