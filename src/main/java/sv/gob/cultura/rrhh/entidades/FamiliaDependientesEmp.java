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
import javax.validation.constraints.Size;

/**
 *
 * @author Angela
 */
@Entity
@Table(name = "familia_dependientes_emp")
@NamedQueries({
    @NamedQuery(name = "FamiliaDependientesEmp.findAll", query = "SELECT f FROM FamiliaDependientesEmp f"),
    @NamedQuery(name = "FamiliaDependientesEmp.findByIdFamilia", query = "SELECT f FROM FamiliaDependientesEmp f WHERE f.idFamilia = :idFamilia"),
    @NamedQuery(name = "FamiliaDependientesEmp.findByNombreFamilia", query = "SELECT f FROM FamiliaDependientesEmp f WHERE f.nombreFamilia = :nombreFamilia"),
    @NamedQuery(name = "FamiliaDependientesEmp.findByFechaNacFamilia", query = "SELECT f FROM FamiliaDependientesEmp f WHERE f.fechaNacFamilia = :fechaNacFamilia"),
    @NamedQuery(name = "FamiliaDependientesEmp.findByEdadFamilia", query = "SELECT f FROM FamiliaDependientesEmp f WHERE f.edadFamilia = :edadFamilia"),
    @NamedQuery(name = "FamiliaDependientesEmp.findByUserCreaFam", query = "SELECT f FROM FamiliaDependientesEmp f WHERE f.userCreaFam = :userCreaFam"),
    @NamedQuery(name = "FamiliaDependientesEmp.findByFechaCreaFam", query = "SELECT f FROM FamiliaDependientesEmp f WHERE f.fechaCreaFam = :fechaCreaFam"),
    @NamedQuery(name = "FamiliaDependientesEmp.findByUserModFam", query = "SELECT f FROM FamiliaDependientesEmp f WHERE f.userModFam = :userModFam"),
    @NamedQuery(name = "FamiliaDependientesEmp.findByIdEmpleado", query = "SELECT f FROM FamiliaDependientesEmp f WHERE f.idEmpleado.idEmpleado = :idEmpleado"),
    @NamedQuery(name = "FamiliaDependientesEmp.findByFechaModFam", query = "SELECT f FROM FamiliaDependientesEmp f WHERE f.fechaModFam = :fechaModFam")})
public class FamiliaDependientesEmp implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_familia")
    private Integer idFamilia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "nombre_familia")
    private String nombreFamilia;
    @Column(name = "fecha_nac_familia")
    @Temporal(TemporalType.DATE)
    private Date fechaNacFamilia;
    @Column(name = "edad_familia")
    private Integer edadFamilia;
    @Column(name = "user_crea_fam")
    private Integer userCreaFam;
    @Column(name = "fecha_crea_fam")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreaFam;
    @Column(name = "user_mod_fam")
    private Integer userModFam;
    @Column(name = "fecha_mod_fam")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModFam;
    @JoinColumn(name = "id_parentesco", referencedColumnName = "id_parentesco")
    @ManyToOne(optional = false)
    private Parentesco idParentesco;
    @JoinColumn(name = "id_empleado", referencedColumnName = "id_empleado")
    @ManyToOne
    private Empleados idEmpleado;

    public FamiliaDependientesEmp() {
    }

    public FamiliaDependientesEmp(Integer idFamilia) {
        this.idFamilia = idFamilia;
    }

    public FamiliaDependientesEmp(Integer idFamilia, String nombreFamilia) {
        this.idFamilia = idFamilia;
        this.nombreFamilia = nombreFamilia;
    }

    public Integer getIdFamilia() {
        return idFamilia;
    }

    public void setIdFamilia(Integer idFamilia) {
        this.idFamilia = idFamilia;
    }

    public String getNombreFamilia() {
        return nombreFamilia;
    }

    public void setNombreFamilia(String nombreFamilia) {
        this.nombreFamilia = nombreFamilia;
    }

    public Date getFechaNacFamilia() {
        return fechaNacFamilia;
    }

    public void setFechaNacFamilia(Date fechaNacFamilia) {
        this.fechaNacFamilia = fechaNacFamilia;
    }

    public Integer getEdadFamilia() {
        return edadFamilia;
    }

    public void setEdadFamilia(Integer edadFamilia) {
        this.edadFamilia = edadFamilia;
    }

    public Integer getUserCreaFam() {
        return userCreaFam;
    }

    public void setUserCreaFam(Integer userCreaFam) {
        this.userCreaFam = userCreaFam;
    }

    public Date getFechaCreaFam() {
        return fechaCreaFam;
    }

    public void setFechaCreaFam(Date fechaCreaFam) {
        this.fechaCreaFam = fechaCreaFam;
    }

    public Integer getUserModFam() {
        return userModFam;
    }

    public void setUserModFam(Integer userModFam) {
        this.userModFam = userModFam;
    }

    public Date getFechaModFam() {
        return fechaModFam;
    }

    public void setFechaModFam(Date fechaModFam) {
        this.fechaModFam = fechaModFam;
    }

    public Parentesco getIdParentesco() {
        return idParentesco;
    }

    public void setIdParentesco(Parentesco idParentesco) {
        this.idParentesco = idParentesco;
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
        hash += (idFamilia != null ? idFamilia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FamiliaDependientesEmp)) {
            return false;
        }
        FamiliaDependientesEmp other = (FamiliaDependientesEmp) object;
        if ((this.idFamilia == null && other.idFamilia != null) || (this.idFamilia != null && !this.idFamilia.equals(other.idFamilia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.gob.cultura.rrhh.entidades.FamiliaDependientesEmp[ idFamilia=" + idFamilia + " ]";
    }
    
}
