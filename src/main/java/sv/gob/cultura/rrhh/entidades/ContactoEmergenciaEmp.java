/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.cultura.rrhh.entidades;

import java.io.Serializable;
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
import javax.validation.constraints.Size;

/**
 *
 * @author Angela
 */
@Entity
@Table(name = "contacto_emergencia_emp")
@NamedQueries({
    @NamedQuery(name = "ContactoEmergenciaEmp.findAll", query = "SELECT c FROM ContactoEmergenciaEmp c")})
public class ContactoEmergenciaEmp implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_contacto_emer")
    private Integer idContactoEmer;
    @Size(max = 200)
    @Column(name = "nombre_contacto")
    private String nombreContacto;
    @Size(max = 9)
    @Column(name = "tel_casa_contacto")
    private String telCasaContacto;
    @Size(max = 9)
    @Column(name = "tel_celular_contacto")
    private String telCelularContacto;
    @Size(max = 9)
    @Column(name = "tel_trabajo_contacto")
    private String telTrabajoContacto;
    @JoinColumn(name = "nr_empleado", referencedColumnName = "nr_empleado")
    @ManyToOne(optional = false)
    private Empleados nrEmpleado;

    public ContactoEmergenciaEmp() {
    }

    public ContactoEmergenciaEmp(Integer idContactoEmer) {
        this.idContactoEmer = idContactoEmer;
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

    public String getTelCasaContacto() {
        return telCasaContacto;
    }

    public void setTelCasaContacto(String telCasaContacto) {
        this.telCasaContacto = telCasaContacto;
    }

    public String getTelCelularContacto() {
        return telCelularContacto;
    }

    public void setTelCelularContacto(String telCelularContacto) {
        this.telCelularContacto = telCelularContacto;
    }

    public String getTelTrabajoContacto() {
        return telTrabajoContacto;
    }

    public void setTelTrabajoContacto(String telTrabajoContacto) {
        this.telTrabajoContacto = telTrabajoContacto;
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
        return "sv.gob.cultura.rrhh.facades.ContactoEmergenciaEmp[ idContactoEmer=" + idContactoEmer + " ]";
    }
    
}
