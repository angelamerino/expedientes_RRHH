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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "descuentos_emp")
@NamedQueries({
    @NamedQuery(name = "DescuentosEmp.findAll", query = "SELECT d FROM DescuentosEmp d"),
    @NamedQuery(name = "DescuentosEmp.findByIdDescuentoEmp", query = "SELECT d FROM DescuentosEmp d WHERE d.idDescuentoEmp = :idDescuentoEmp"),
    @NamedQuery(name = "DescuentosEmp.findByNombreInstitucionDesc", query = "SELECT d FROM DescuentosEmp d WHERE d.nombreInstitucionDesc = :nombreInstitucionDesc"),
    @NamedQuery(name = "DescuentosEmp.findByNumRefCredito", query = "SELECT d FROM DescuentosEmp d WHERE d.numRefCredito = :numRefCredito"),
    @NamedQuery(name = "DescuentosEmp.findByNumCuotas", query = "SELECT d FROM DescuentosEmp d WHERE d.numCuotas = :numCuotas"),
    @NamedQuery(name = "DescuentosEmp.findByCuotaMensual", query = "SELECT d FROM DescuentosEmp d WHERE d.cuotaMensual = :cuotaMensual"),
    @NamedQuery(name = "DescuentosEmp.findByMontoTotal", query = "SELECT d FROM DescuentosEmp d WHERE d.montoTotal = :montoTotal"),
    @NamedQuery(name = "DescuentosEmp.findByFechaIniDesc", query = "SELECT d FROM DescuentosEmp d WHERE d.fechaIniDesc = :fechaIniDesc"),
    @NamedQuery(name = "DescuentosEmp.findByFechaFinDesc", query = "SELECT d FROM DescuentosEmp d WHERE d.fechaFinDesc = :fechaFinDesc"),
    @NamedQuery(name = "DescuentosEmp.findByUserCreaDesc", query = "SELECT d FROM DescuentosEmp d WHERE d.userCreaDesc = :userCreaDesc"),
    @NamedQuery(name = "DescuentosEmp.findByFechaCreaDesc", query = "SELECT d FROM DescuentosEmp d WHERE d.fechaCreaDesc = :fechaCreaDesc"),
    @NamedQuery(name = "DescuentosEmp.findByUserModDesc", query = "SELECT d FROM DescuentosEmp d WHERE d.userModDesc = :userModDesc"),
    @NamedQuery(name = "DescuentosEmp.findByFechaModDesc", query = "SELECT d FROM DescuentosEmp d WHERE d.fechaModDesc = :fechaModDesc")})
public class DescuentosEmp implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_descuento_emp")
    private Integer idDescuentoEmp;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1024)
    @Column(name = "nombre_institucion_desc")
    private String nombreInstitucionDesc;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "num_ref_credito")
    private String numRefCredito;
    @Basic(optional = false)
    @NotNull
    @Column(name = "num_cuotas")
    private int numCuotas;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cuota_mensual")
    private double cuotaMensual;
    @Basic(optional = false)
    @NotNull
    @Column(name = "monto_total")
    private double montoTotal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_ini_desc")
    @Temporal(TemporalType.DATE)
    private Date fechaIniDesc;
    @Column(name = "fecha_fin_desc")
    @Temporal(TemporalType.DATE)
    private Date fechaFinDesc;
    @Column(name = "user_crea_desc")
    private Integer userCreaDesc;
    @Column(name = "fecha_crea_desc")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreaDesc;
    @Column(name = "user_mod_desc")
    private Integer userModDesc;
    @Column(name = "fecha_mod_desc")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModDesc;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDescuentoEmp")
    private List<ImgDoc> imgDocList;
    @JoinColumn(name = "id_descuento", referencedColumnName = "id_descuento")
    @ManyToOne(optional = false)
    private TipoDescuento idDescuento;
    @JoinColumn(name = "id_empleado", referencedColumnName = "id_empleado")
    @ManyToOne
    private Empleados idEmpleado;

    public DescuentosEmp() {
    }

    public DescuentosEmp(Integer idDescuentoEmp) {
        this.idDescuentoEmp = idDescuentoEmp;
    }

    public DescuentosEmp(Integer idDescuentoEmp, String nombreInstitucionDesc, String numRefCredito, int numCuotas, double cuotaMensual, double montoTotal, Date fechaIniDesc) {
        this.idDescuentoEmp = idDescuentoEmp;
        this.nombreInstitucionDesc = nombreInstitucionDesc;
        this.numRefCredito = numRefCredito;
        this.numCuotas = numCuotas;
        this.cuotaMensual = cuotaMensual;
        this.montoTotal = montoTotal;
        this.fechaIniDesc = fechaIniDesc;
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

    public int getNumCuotas() {
        return numCuotas;
    }

    public void setNumCuotas(int numCuotas) {
        this.numCuotas = numCuotas;
    }

    public double getCuotaMensual() {
        return cuotaMensual;
    }

    public void setCuotaMensual(double cuotaMensual) {
        this.cuotaMensual = cuotaMensual;
    }

    public double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(double montoTotal) {
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

    public Integer getUserCreaDesc() {
        return userCreaDesc;
    }

    public void setUserCreaDesc(Integer userCreaDesc) {
        this.userCreaDesc = userCreaDesc;
    }

    public Date getFechaCreaDesc() {
        return fechaCreaDesc;
    }

    public void setFechaCreaDesc(Date fechaCreaDesc) {
        this.fechaCreaDesc = fechaCreaDesc;
    }

    public Integer getUserModDesc() {
        return userModDesc;
    }

    public void setUserModDesc(Integer userModDesc) {
        this.userModDesc = userModDesc;
    }

    public Date getFechaModDesc() {
        return fechaModDesc;
    }

    public void setFechaModDesc(Date fechaModDesc) {
        this.fechaModDesc = fechaModDesc;
    }

    public List<ImgDoc> getImgDocList() {
        return imgDocList;
    }

    public void setImgDocList(List<ImgDoc> imgDocList) {
        this.imgDocList = imgDocList;
    }

    public TipoDescuento getIdDescuento() {
        return idDescuento;
    }

    public void setIdDescuento(TipoDescuento idDescuento) {
        this.idDescuento = idDescuento;
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
        return "sv.gob.cultura.rrhh.entidades.DescuentosEmp[ idDescuentoEmp=" + idDescuentoEmp + " ]";
    }
    
}
