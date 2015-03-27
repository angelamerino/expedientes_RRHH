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
@Table(name = "historial_salarial")
@NamedQueries({
    @NamedQuery(name = "HistorialSalarial.findAll", query = "SELECT h FROM HistorialSalarial h")})
public class HistorialSalarial implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_hsalarial")
    private Integer idHsalarial;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "salario_actual_hsalarial")
    private Double salarioActualHsalarial;
    @Column(name = "nuevo_salario_hsalarial")
    private Double nuevoSalarioHsalarial;
    @Column(name = "porcentaje_hsalarial")
    private Integer porcentajeHsalarial;
    @Column(name = "fecha_hsalarial")
    @Temporal(TemporalType.DATE)
    private Date fechaHsalarial;
    @Size(max = 25)
    @Column(name = "doc_hsalarial")
    private String docHsalarial;
    @JoinColumn(name = "id_mejora_sal", referencedColumnName = "id_mejora_sal")
    @ManyToOne(optional = false)
    private TipoMejoraSalarial idMejoraSal;
    @JoinColumn(name = "nr_empleado", referencedColumnName = "nr_empleado")
    @ManyToOne(optional = false)
    private Empleados nrEmpleado;

    public HistorialSalarial() {
    }

    public HistorialSalarial(Integer idHsalarial) {
        this.idHsalarial = idHsalarial;
    }

    public Integer getIdHsalarial() {
        return idHsalarial;
    }

    public void setIdHsalarial(Integer idHsalarial) {
        this.idHsalarial = idHsalarial;
    }

    public Double getSalarioActualHsalarial() {
        return salarioActualHsalarial;
    }

    public void setSalarioActualHsalarial(Double salarioActualHsalarial) {
        this.salarioActualHsalarial = salarioActualHsalarial;
    }

    public Double getNuevoSalarioHsalarial() {
        return nuevoSalarioHsalarial;
    }

    public void setNuevoSalarioHsalarial(Double nuevoSalarioHsalarial) {
        this.nuevoSalarioHsalarial = nuevoSalarioHsalarial;
    }

    public Integer getPorcentajeHsalarial() {
        return porcentajeHsalarial;
    }

    public void setPorcentajeHsalarial(Integer porcentajeHsalarial) {
        this.porcentajeHsalarial = porcentajeHsalarial;
    }

    public Date getFechaHsalarial() {
        return fechaHsalarial;
    }

    public void setFechaHsalarial(Date fechaHsalarial) {
        this.fechaHsalarial = fechaHsalarial;
    }

    public String getDocHsalarial() {
        return docHsalarial;
    }

    public void setDocHsalarial(String docHsalarial) {
        this.docHsalarial = docHsalarial;
    }

    public TipoMejoraSalarial getIdMejoraSal() {
        return idMejoraSal;
    }

    public void setIdMejoraSal(TipoMejoraSalarial idMejoraSal) {
        this.idMejoraSal = idMejoraSal;
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
        hash += (idHsalarial != null ? idHsalarial.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HistorialSalarial)) {
            return false;
        }
        HistorialSalarial other = (HistorialSalarial) object;
        if ((this.idHsalarial == null && other.idHsalarial != null) || (this.idHsalarial != null && !this.idHsalarial.equals(other.idHsalarial))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.gob.cultura.rrhh.facades.HistorialSalarial[ idHsalarial=" + idHsalarial + " ]";
    }
    
}
