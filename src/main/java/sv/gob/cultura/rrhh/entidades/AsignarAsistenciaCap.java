/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.cultura.rrhh.entidades;

import java.io.Serializable;
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
        return "sv.gob.cultura.rrhh.facades.AsignarAsistenciaCap[ idAsigAsis=" + idAsigAsis + " ]";
    }
    
}
