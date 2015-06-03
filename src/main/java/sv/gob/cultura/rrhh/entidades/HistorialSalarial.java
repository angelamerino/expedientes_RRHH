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
@Table(name = "historial_salarial")
@NamedQueries({
    @NamedQuery(name = "HistorialSalarial.findAll", query = "SELECT h FROM HistorialSalarial h"),
    @NamedQuery(name = "HistorialSalarial.findByIdHsalarial", query = "SELECT h FROM HistorialSalarial h WHERE h.idHsalarial = :idHsalarial"),
    @NamedQuery(name = "HistorialSalarial.findBySalarioActualHsalarial", query = "SELECT h FROM HistorialSalarial h WHERE h.salarioActualHsalarial = :salarioActualHsalarial"),
    @NamedQuery(name = "HistorialSalarial.findByNuevoSalarioHsalarial", query = "SELECT h FROM HistorialSalarial h WHERE h.nuevoSalarioHsalarial = :nuevoSalarioHsalarial"),
    @NamedQuery(name = "HistorialSalarial.findByPorcentajeHsalarial", query = "SELECT h FROM HistorialSalarial h WHERE h.porcentajeHsalarial = :porcentajeHsalarial"),
    @NamedQuery(name = "HistorialSalarial.findByFechaHsalarial", query = "SELECT h FROM HistorialSalarial h WHERE h.fechaHsalarial = :fechaHsalarial"),
    @NamedQuery(name = "HistorialSalarial.findByNumDocDecreto", query = "SELECT h FROM HistorialSalarial h WHERE h.numDocDecreto = :numDocDecreto"),
    @NamedQuery(name = "HistorialSalarial.findByUserCreaHsal", query = "SELECT h FROM HistorialSalarial h WHERE h.userCreaHsal = :userCreaHsal"),
    @NamedQuery(name = "HistorialSalarial.findByFechaCreaHsal", query = "SELECT h FROM HistorialSalarial h WHERE h.fechaCreaHsal = :fechaCreaHsal"),
    @NamedQuery(name = "HistorialSalarial.findByUserModHsal", query = "SELECT h FROM HistorialSalarial h WHERE h.userModHsal = :userModHsal"),
    @NamedQuery(name = "HistorialSalarial.findByFechaModHsal", query = "SELECT h FROM HistorialSalarial h WHERE h.fechaModHsal = :fechaModHsal")})
public class HistorialSalarial implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_hsalarial")
    private Integer idHsalarial;
    @Basic(optional = false)
    @NotNull
    @Column(name = "salario_actual_hsalarial")
    private double salarioActualHsalarial;
    @Basic(optional = false)
    @NotNull
    @Column(name = "nuevo_salario_hsalarial")
    private double nuevoSalarioHsalarial;
    @Basic(optional = false)
    @NotNull
    @Column(name = "porcentaje_hsalarial")
    private int porcentajeHsalarial;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_hsalarial")
    @Temporal(TemporalType.DATE)
    private Date fechaHsalarial;
    @Size(max = 1024)
    @Column(name = "num_doc_decreto")
    private String numDocDecreto;
    @Column(name = "user_crea_hsal")
    private Integer userCreaHsal;
    @Column(name = "fecha_crea_hsal")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreaHsal;
    @Column(name = "user_mod_hsal")
    private Integer userModHsal;
    @Column(name = "fecha_mod_hsal")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModHsal;
    @JoinColumn(name = "id_mejora_sal", referencedColumnName = "id_mejora_sal")
    @ManyToOne(optional = false)
    private TipoMejoraSalarial idMejoraSal;
    @JoinColumn(name = "id_empleado", referencedColumnName = "id_empleado")
    @ManyToOne
    private Empleados idEmpleado;
    @OneToMany(mappedBy = "idHsalarial")
    private List<ImgDoc> imgDocList;

    public HistorialSalarial() {
    }

    public HistorialSalarial(Integer idHsalarial) {
        this.idHsalarial = idHsalarial;
    }

    public HistorialSalarial(Integer idHsalarial, double salarioActualHsalarial, double nuevoSalarioHsalarial, int porcentajeHsalarial, Date fechaHsalarial) {
        this.idHsalarial = idHsalarial;
        this.salarioActualHsalarial = salarioActualHsalarial;
        this.nuevoSalarioHsalarial = nuevoSalarioHsalarial;
        this.porcentajeHsalarial = porcentajeHsalarial;
        this.fechaHsalarial = fechaHsalarial;
    }

    public Integer getIdHsalarial() {
        return idHsalarial;
    }

    public void setIdHsalarial(Integer idHsalarial) {
        this.idHsalarial = idHsalarial;
    }

    public double getSalarioActualHsalarial() {
        return salarioActualHsalarial;
    }

    public void setSalarioActualHsalarial(double salarioActualHsalarial) {
        this.salarioActualHsalarial = salarioActualHsalarial;
    }

    public double getNuevoSalarioHsalarial() {
        return nuevoSalarioHsalarial;
    }

    public void setNuevoSalarioHsalarial(double nuevoSalarioHsalarial) {
        this.nuevoSalarioHsalarial = nuevoSalarioHsalarial;
    }

    public int getPorcentajeHsalarial() {
        return porcentajeHsalarial;
    }

    public void setPorcentajeHsalarial(int porcentajeHsalarial) {
        this.porcentajeHsalarial = porcentajeHsalarial;
    }

    public Date getFechaHsalarial() {
        return fechaHsalarial;
    }

    public void setFechaHsalarial(Date fechaHsalarial) {
        this.fechaHsalarial = fechaHsalarial;
    }

    public String getNumDocDecreto() {
        return numDocDecreto;
    }

    public void setNumDocDecreto(String numDocDecreto) {
        this.numDocDecreto = numDocDecreto;
    }

    public Integer getUserCreaHsal() {
        return userCreaHsal;
    }

    public void setUserCreaHsal(Integer userCreaHsal) {
        this.userCreaHsal = userCreaHsal;
    }

    public Date getFechaCreaHsal() {
        return fechaCreaHsal;
    }

    public void setFechaCreaHsal(Date fechaCreaHsal) {
        this.fechaCreaHsal = fechaCreaHsal;
    }

    public Integer getUserModHsal() {
        return userModHsal;
    }

    public void setUserModHsal(Integer userModHsal) {
        this.userModHsal = userModHsal;
    }

    public Date getFechaModHsal() {
        return fechaModHsal;
    }

    public void setFechaModHsal(Date fechaModHsal) {
        this.fechaModHsal = fechaModHsal;
    }

    public TipoMejoraSalarial getIdMejoraSal() {
        return idMejoraSal;
    }

    public void setIdMejoraSal(TipoMejoraSalarial idMejoraSal) {
        this.idMejoraSal = idMejoraSal;
    }

    public Empleados getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Empleados idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public List<ImgDoc> getImgDocList() {
        return imgDocList;
    }

    public void setImgDocList(List<ImgDoc> imgDocList) {
        this.imgDocList = imgDocList;
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
        return "sv.gob.cultura.rrhh.entidades.HistorialSalarial[ idHsalarial=" + idHsalarial + " ]";
    }
    
}
