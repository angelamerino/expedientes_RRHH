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
@Table(name = "contacto_emergencia_emp")
@NamedQueries({
    @NamedQuery(name = "ContactoEmergenciaEmp.findAll", query = "SELECT c FROM ContactoEmergenciaEmp c"),
    @NamedQuery(name = "ContactoEmergenciaEmp.findByIdContactoEmer", query = "SELECT c FROM ContactoEmergenciaEmp c WHERE c.idContactoEmer = :idContactoEmer"),
    @NamedQuery(name = "ContactoEmergenciaEmp.findByNombreContacto", query = "SELECT c FROM ContactoEmergenciaEmp c WHERE c.nombreContacto = :nombreContacto"),
    @NamedQuery(name = "ContactoEmergenciaEmp.findByTelFijoContacto", query = "SELECT c FROM ContactoEmergenciaEmp c WHERE c.telFijoContacto = :telFijoContacto"),
    @NamedQuery(name = "ContactoEmergenciaEmp.findByTelMovilContacto", query = "SELECT c FROM ContactoEmergenciaEmp c WHERE c.telMovilContacto = :telMovilContacto"),
    @NamedQuery(name = "ContactoEmergenciaEmp.findByUserCreaContac", query = "SELECT c FROM ContactoEmergenciaEmp c WHERE c.userCreaContac = :userCreaContac"),
    @NamedQuery(name = "ContactoEmergenciaEmp.findByFechaCreaContac", query = "SELECT c FROM ContactoEmergenciaEmp c WHERE c.fechaCreaContac = :fechaCreaContac"),
    @NamedQuery(name = "ContactoEmergenciaEmp.findByUserModContac", query = "SELECT c FROM ContactoEmergenciaEmp c WHERE c.userModContac = :userModContac"),
    @NamedQuery(name = "ContactoEmergenciaEmp.findByFechaModContac", query = "SELECT c FROM ContactoEmergenciaEmp c WHERE c.fechaModContac = :fechaModContac")})
public class ContactoEmergenciaEmp implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_contacto_emer")
    private Integer idContactoEmer;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "nombre_contacto")
    private String nombreContacto;
    @Size(max = 9)
    @Column(name = "tel_fijo_contacto")
    private String telFijoContacto;
    @Size(max = 9)
    @Column(name = "tel_movil_contacto")
    private String telMovilContacto;
    @Column(name = "user_crea_contac")
    private Integer userCreaContac;
    @Column(name = "fecha_crea_contac")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreaContac;
    @Column(name = "user_mod_contac")
    private Integer userModContac;
    @Column(name = "fecha_mod_contac")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModContac;
    @JoinColumn(name = "id_parentesco", referencedColumnName = "id_parentesco")
    @ManyToOne(optional = false)
    private Parentesco idParentesco;
    @JoinColumn(name = "id_empleado", referencedColumnName = "id_empleado")
    @ManyToOne
    private Empleados idEmpleado;

    public ContactoEmergenciaEmp() {
    }

    public ContactoEmergenciaEmp(Integer idContactoEmer) {
        this.idContactoEmer = idContactoEmer;
    }

    public ContactoEmergenciaEmp(Integer idContactoEmer, String nombreContacto) {
        this.idContactoEmer = idContactoEmer;
        this.nombreContacto = nombreContacto;
    }

    public Integer getIdContactoEmer() {
        return idContactoEmer;
    }

    public void setIdContactoEmer(Integer idContactoEmer) {
        this.idContactoEmer = idContactoEmer;
    }

    public String getNombreContacto() {
        return nombreContacto;
    }

    public void setNombreContacto(String nombreContacto) {
        this.nombreContacto = nombreContacto;
    }

    public String getTelFijoContacto() {
        return telFijoContacto;
    }

    public void setTelFijoContacto(String telFijoContacto) {
        this.telFijoContacto = telFijoContacto;
    }

    public String getTelMovilContacto() {
        return telMovilContacto;
    }

    public void setTelMovilContacto(String telMovilContacto) {
        this.telMovilContacto = telMovilContacto;
    }

    public Integer getUserCreaContac() {
        return userCreaContac;
    }

    public void setUserCreaContac(Integer userCreaContac) {
        this.userCreaContac = userCreaContac;
    }

    public Date getFechaCreaContac() {
        return fechaCreaContac;
    }

    public void setFechaCreaContac(Date fechaCreaContac) {
        this.fechaCreaContac = fechaCreaContac;
    }

    public Integer getUserModContac() {
        return userModContac;
    }

    public void setUserModContac(Integer userModContac) {
        this.userModContac = userModContac;
    }

    public Date getFechaModContac() {
        return fechaModContac;
    }

    public void setFechaModContac(Date fechaModContac) {
        this.fechaModContac = fechaModContac;
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
        hash += (idContactoEmer != null ? idContactoEmer.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ContactoEmergenciaEmp)) {
            return false;
        }
        ContactoEmergenciaEmp other = (ContactoEmergenciaEmp) object;
        if ((this.idContactoEmer == null && other.idContactoEmer != null) || (this.idContactoEmer != null && !this.idContactoEmer.equals(other.idContactoEmer))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.gob.cultura.rrhh.entidades.ContactoEmergenciaEmp[ idContactoEmer=" + idContactoEmer + " ]";
    }
    
}
