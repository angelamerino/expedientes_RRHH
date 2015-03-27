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
@Table(name = "experiencia_laboral")
@NamedQueries({
    @NamedQuery(name = "ExperienciaLaboral.findAll", query = "SELECT e FROM ExperienciaLaboral e")})
public class ExperienciaLaboral implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_exp_lab")
    private Integer idExpLab;
    @Size(max = 1024)
    @Column(name = "institucion_empresa")
    private String institucionEmpresa;
    @Size(max = 200)
    @Column(name = "cargo_exp_lab")
    private String cargoExpLab;
    @Column(name = "fecha_desde_exp_lab")
    @Temporal(TemporalType.DATE)
    private Date fechaDesdeExpLab;
    @Column(name = "fecha_hasta_exp_lab")
    @Temporal(TemporalType.DATE)
    private Date fechaHastaExpLab;
    @Size(max = 25)
    @Column(name = "sector_exp_lab")
    private String sectorExpLab;
    @JoinColumn(name = "nr_empleado", referencedColumnName = "nr_empleado")
    @ManyToOne(optional = false)
    private Empleados nrEmpleado;

    public ExperienciaLaboral() {
    }

    public ExperienciaLaboral(Integer idExpLab) {
        this.idExpLab = idExpLab;
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

    public Empleados getNrEmpleado() {
        return nrEmpleado;
    }

    public void setNrEmpleado(Empleados nrEmpleado) {
        this.nrEmpleado = nrEmpleado;
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
        return "sv.gob.cultura.rrhh.facades.ExperienciaLaboral[ idExpLab=" + idExpLab + " ]";
    }
    
}
