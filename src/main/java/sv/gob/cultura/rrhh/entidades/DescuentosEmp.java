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
@Table(name = "descuentos_emp")
@NamedQueries({
    @NamedQuery(name = "DescuentosEmp.findAll", query = "SELECT d FROM DescuentosEmp d")})
public class DescuentosEmp implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_descuento_emp")
    private Integer idDescuentoEmp;
    @Size(max = 1024)
    @Column(name = "nombre_institucion_desc")
    private String nombreInstitucionDesc;
    @Size(max = 25)
    @Column(name = "num_ref_credito")
    private String numRefCredito;
    @Column(name = "num_cuotas")
    private Integer numCuotas;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "cuota_mensual")
    private Double cuotaMensual;
    @Column(name = "monto_total")
    private Double montoTotal;
    @Column(name = "fecha_ini_desc")
    @Temporal(TemporalType.DATE)
    private Date fechaIniDesc;
    @Column(name = "fecha_fin_desc")
    @Temporal(TemporalType.DATE)
    private Date fechaFinDesc;
    @Size(max = 25)
    @Column(name = "doc_desc")
    private String docDesc;
    @JoinColumn(name = "id_descuento", referencedColumnName = "id_descuento")
    @ManyToOne(optional = false)
    private TipoDescuento idDescuento;
    @JoinColumn(name = "nr_empleado", referencedColumnName = "nr_empleado")
    @ManyToOne(optional = false)
    private Empleados nrEmpleado;

    public DescuentosEmp() {
    }

    public DescuentosEmp(Integer idDescuentoEmp) {
        this.idDescuentoEmp = idDescuentoEmp;
    }

    public Integer getIdDescuentoEmp() {
        return idDescuentoEmp;
    }

    public void setIdDescuentoEmp(Integer idDescuentoEmp) {
        this.idDescuentoEmp = idDescuentoEmp;
    }

    public String getNombreInstitucionDesc() {
        return nombreInstitucionDesc;
    }

    public void setNombreInstitucionDesc(String nombreInstitucionDesc) {
        this.nombreInstitucionDesc = nombreInstitucionDesc;
    }

    public String getNumRefCredito() {
        return numRefCredito;
    }

    public void setNumRefCredito(String numRefCredito) {
        this.numRefCredito = numRefCredito;
    }

    public Integer getNumCuotas() {
        return numCuotas;
    }

    public void setNumCuotas(Integer numCuotas) {
        this.numCuotas = numCuotas;
    }

    public Double getCuotaMensual() {
        return cuotaMensual;
    }

    public void setCuotaMensual(Double cuotaMensual) {
        this.cuotaMensual = cuotaMensual;
    }

    public Double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(Double montoTotal) {
        this.montoTotal = montoTotal;
    }

    public Date getFechaIniDesc() {
        return fechaIniDesc;
    }

    public void setFechaIniDesc(Date fechaIniDesc) {
        this.fechaIniDesc = fechaIniDesc;
    }

    public Date getFechaFinDesc() {
        return fechaFinDesc;
    }

    public void setFechaFinDesc(Date fechaFinDesc) {
        this.fechaFinDesc = fechaFinDesc;
    }

    public String getDocDesc() {
        return docDesc;
    }

    public void setDocDesc(String docDesc) {
        this.docDesc = docDesc;
    }

    public TipoDescuento getIdDescuento() {
        return idDescuento;
    }

    public void setIdDescuento(TipoDescuento idDescuento) {
        this.idDescuento = idDescuento;
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
        hash += (idDescuentoEmp != null ? idDescuentoEmp.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DescuentosEmp)) {
            return false;
        }
        DescuentosEmp other = (DescuentosEmp) object;
        if ((this.idDescuentoEmp == null && other.idDescuentoEmp != null) || (this.idDescuentoEmp != null && !this.idDescuentoEmp.equals(other.idDescuentoEmp))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.gob.cultura.rrhh.facades.DescuentosEmp[ idDescuentoEmp=" + idDescuentoEmp + " ]";
    }
    
}
