
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
@Table(name = "capacitaciones")
@NamedQueries({
    @NamedQuery(name = "Capacitaciones.findAll", query = "SELECT c FROM Capacitaciones c"),
    @NamedQuery(name = "Capacitaciones.findByIdCap", query = "SELECT c FROM Capacitaciones c WHERE c.idCap = :idCap"),
    @NamedQuery(name = "Capacitaciones.findByTemaCap", query = "SELECT c FROM Capacitaciones c WHERE c.temaCap = :temaCap"),
    @NamedQuery(name = "Capacitaciones.findByFacilitadorCap", query = "SELECT c FROM Capacitaciones c WHERE c.facilitadorCap = :facilitadorCap"),
    @NamedQuery(name = "Capacitaciones.findByInstitucionCap", query = "SELECT c FROM Capacitaciones c WHERE c.institucionCap = :institucionCap"),
    @NamedQuery(name = "Capacitaciones.findByNumHorasCap", query = "SELECT c FROM Capacitaciones c WHERE c.numHorasCap = :numHorasCap"),
    @NamedQuery(name = "Capacitaciones.findByAnioCap", query = "SELECT c FROM Capacitaciones c WHERE c.anioCap = :anioCap"),
    @NamedQuery(name = "Capacitaciones.findByNumGrupo", query = "SELECT c FROM Capacitaciones c WHERE c.numGrupo = :numGrupo"),
    @NamedQuery(name = "Capacitaciones.findByUserCreaCap", query = "SELECT c FROM Capacitaciones c WHERE c.userCreaCap = :userCreaCap"),
    @NamedQuery(name = "Capacitaciones.findByFechaCreaCap", query = "SELECT c FROM Capacitaciones c WHERE c.fechaCreaCap = :fechaCreaCap"),
    @NamedQuery(name = "Capacitaciones.findByUserModCap", query = "SELECT c FROM Capacitaciones c WHERE c.userModCap = :userModCap"),
    @NamedQuery(name = "Capacitaciones.findByFechaModCap", query = "SELECT c FROM Capacitaciones c WHERE c.fechaModCap = :fechaModCap")})
public class Capacitaciones implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_cap")
    private Integer idCap;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1024)
    @Column(name = "tema_cap")
    private String temaCap;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "facilitador_cap")
    private String facilitadorCap;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "institucion_cap")
    private String institucionCap;
    @Column(name = "num_horas_cap")
    private Integer numHorasCap;
    @Basic(optional = false)
    @NotNull
    @Column(name = "anio_cap")
    @Temporal(TemporalType.DATE)
    private Date anioCap;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "num_grupo")
    private String numGrupo;
    @Column(name = "user_crea_cap")
    private Integer userCreaCap;
    @Column(name = "fecha_crea_cap")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreaCap;
    @Column(name = "user_mod_cap")
    private Integer userModCap;
    @Column(name = "fecha_mod_cap")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModCap;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCap")
    private List<HorariosCap> horariosCapList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCap")
    private List<AsignarAsistenciaCap> asignarAsistenciaCapList;

    public Capacitaciones() {
    }

    public Capacitaciones(Integer idCap) {
        this.idCap = idCap;
    }

    public Capacitaciones(Integer idCap, String temaCap, String facilitadorCap, String institucionCap, Date anioCap, String numGrupo) {
        this.idCap = idCap;
        this.temaCap = temaCap;
        this.facilitadorCap = facilitadorCap;
        this.institucionCap = institucionCap;
        this.anioCap = anioCap;
        this.numGrupo = numGrupo;
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

    public Date getAnioCap() {
        return anioCap;
    }

    public void setAnioCap(Date anioCap) {
        this.anioCap = anioCap;
    }

    public String getNumGrupo() {
        return numGrupo;
    }

    public void setNumGrupo(String numGrupo) {
        this.numGrupo = numGrupo;
    }

    public Integer getUserCreaCap() {
        return userCreaCap;
    }

    public void setUserCreaCap(Integer userCreaCap) {
        this.userCreaCap = userCreaCap;
    }

    public Date getFechaCreaCap() {
        return fechaCreaCap;
    }

    public void setFechaCreaCap(Date fechaCreaCap) {
        this.fechaCreaCap = fechaCreaCap;
    }

    public Integer getUserModCap() {
        return userModCap;
    }

    public void setUserModCap(Integer userModCap) {
        this.userModCap = userModCap;
    }

    public Date getFechaModCap() {
        return fechaModCap;
    }

    public void setFechaModCap(Date fechaModCap) {
        this.fechaModCap = fechaModCap;
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
        return "sv.gob.cultura.rrhh.entidades.Capacitaciones[ idCap=" + idCap + " ]";
    }
    
}
