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
    @NamedQuery(name = "AsignarAsistenciaCap.findAll", query = "SELECT a FROM AsignarAsistenciaCap a")})
public class AsignarAsistenciaCap implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_asig_asis")
    private Integer idAsigAsis;
    @Size(max = 2)
    @Column(name = "cap_asignada")
    private String capAsignada;
    @Size(max = 2)
    @Column(name = "cap_asistida")
    private String capAsistida;
    @Column(name = "user_crea_asig_asis")
    private Integer userCreaAsigAsis;
    @Column(name = "fecha_crea_asig_asis")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreaAsigAsis;
    @Column(name = "user_mod_asig_asis")
    private Integer userModAsigAsis;
    @Column(name = "fecha_mod_aisg_asis")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModAisgAsis;
    @JoinColumn(name = "nr_empleado", referencedColumnName = "nr_empleado")
    @ManyToOne(optional = false)
    private Empleados nrEmpleado;
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

    public String getCapAsignada() {
        return capAsignada;
    }

    public void setCapAsignada(String capAsignada) {
        this.capAsignada = capAsignada;
    }

    public String getCapAsistida() {
        return capAsistida;
    }

    public void setCapAsistida(String capAsistida) {
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

    public Date getFechaModAisgAsis() {
        return fechaModAisgAsis;
    }

    public void setFechaModAisgAsis(Date fechaModAisgAsis) {
        this.fechaModAisgAsis = fechaModAisgAsis;
    }

    public Empleados getNrEmpleado() {
        return nrEmpleado;
    }

    public void setNrEmpleado(Empleados nrEmpleado) {
        this.nrEmpleado = nrEmpleado;
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
    
}
