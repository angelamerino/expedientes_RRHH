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
import javax.validation.constraints.NotNull;

/**
 *
 * @author Angela
 */
@Entity
@Table(name = "horarios_cap")
@NamedQueries({
    @NamedQuery(name = "HorariosCap.findAll", query = "SELECT h FROM HorariosCap h")})
public class HorariosCap implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_horario_cap")
    private Integer idHorarioCap;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_cap")
    @Temporal(TemporalType.DATE)
    private Date fechaCap;
    @Basic(optional = false)
    @NotNull
    @Column(name = "hora_inicio_cap")
    @Temporal(TemporalType.TIME)
    private Date horaInicioCap;
    @Basic(optional = false)
    @NotNull
    @Column(name = "hora_fin_cap")
    @Temporal(TemporalType.TIME)
    private Date horaFinCap;
    @JoinColumn(name = "id_cap", referencedColumnName = "id_cap")
    @ManyToOne(optional = false)
    private Capacitaciones idCap;

    public HorariosCap() {
    }

    public HorariosCap(Integer idHorarioCap) {
        this.idHorarioCap = idHorarioCap;
    }

    public HorariosCap(Integer idHorarioCap, Date fechaCap, Date horaInicioCap, Date horaFinCap) {
        this.idHorarioCap = idHorarioCap;
        this.fechaCap = fechaCap;
        this.horaInicioCap = horaInicioCap;
        this.horaFinCap = horaFinCap;
    }

    public Integer getIdHorarioCap() {
        return idHorarioCap;
    }

    public void setIdHorarioCap(Integer idHorarioCap) {
        this.idHorarioCap = idHorarioCap;
    }

    public Date getFechaCap() {
        return fechaCap;
    }

    public void setFechaCap(Date fechaCap) {
        this.fechaCap = fechaCap;
    }

    public Date getHoraInicioCap() {
        return horaInicioCap;
    }

    public void setHoraInicioCap(Date horaInicioCap) {
        this.horaInicioCap = horaInicioCap;
    }

    public Date getHoraFinCap() {
        return horaFinCap;
    }

    public void setHoraFinCap(Date horaFinCap) {
        this.horaFinCap = horaFinCap;
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
        hash += (idHorarioCap != null ? idHorarioCap.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HorariosCap)) {
            return false;
        }
        HorariosCap other = (HorariosCap) object;
        if ((this.idHorarioCap == null && other.idHorarioCap != null) || (this.idHorarioCap != null && !this.idHorarioCap.equals(other.idHorarioCap))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.gob.cultura.rrhh.entidades.HorariosCap[ idHorarioCap=" + idHorarioCap + " ]";
    }
    
}
