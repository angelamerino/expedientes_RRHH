/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.cultura.rrhh.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author Angela
 */
@Entity
@Table(name = "capacitaciones")
@NamedQueries({
    @NamedQuery(name = "Capacitaciones.findAll", query = "SELECT c FROM Capacitaciones c")})
public class Capacitaciones implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_cap")
    private Integer idCap;
    @Size(max = 1024)
    @Column(name = "tema_cap")
    private String temaCap;
    @Size(max = 200)
    @Column(name = "facilitador_cap")
    private String facilitadorCap;
    @Size(max = 200)
    @Column(name = "institucion_cap")
    private String institucionCap;
    @Column(name = "num_horas_cap")
    private Integer numHorasCap;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCap")
    private List<HorariosCap> horariosCapList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCap")
    private List<AsignarAsistenciaCap> asignarAsistenciaCapList;

    public Capacitaciones() {
    }

    public Capacitaciones(Integer idCap) {
        this.idCap = idCap;
    }

    public Integer getIdCap() {
        return idCap;
    }

    public void setIdCap(Integer idCap) {
        this.idCap = idCap;
    }

    public String getTemaCap() {
        return temaCap;
    }

    public void setTemaCap(String temaCap) {
        this.temaCap = temaCap;
    }

    public String getFacilitadorCap() {
        return facilitadorCap;
    }

    public void setFacilitadorCap(String facilitadorCap) {
        this.facilitadorCap = facilitadorCap;
    }

    public String getInstitucionCap() {
        return institucionCap;
    }

    public void setInstitucionCap(String institucionCap) {
        this.institucionCap = institucionCap;
    }

    public Integer getNumHorasCap() {
        return numHorasCap;
    }

    public void setNumHorasCap(Integer numHorasCap) {
        this.numHorasCap = numHorasCap;
    }

    public List<HorariosCap> getHorariosCapList() {
        return horariosCapList;
    }

    public void setHorariosCapList(List<HorariosCap> horariosCapList) {
        this.horariosCapList = horariosCapList;
    }

    public List<AsignarAsistenciaCap> getAsignarAsistenciaCapList() {
        return asignarAsistenciaCapList;
    }

    public void setAsignarAsistenciaCapList(List<AsignarAsistenciaCap> asignarAsistenciaCapList) {
        this.asignarAsistenciaCapList = asignarAsistenciaCapList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCap != null ? idCap.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Capacitaciones)) {
            return false;
        }
        Capacitaciones other = (Capacitaciones) object;
        if ((this.idCap == null && other.idCap != null) || (this.idCap != null && !this.idCap.equals(other.idCap))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.gob.cultura.rrhh.facades.Capacitaciones[ idCap=" + idCap + " ]";
    }
    
}
