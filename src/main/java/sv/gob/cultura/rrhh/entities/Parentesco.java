/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.cultura.rrhh.entities;

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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Angela
 */
@Entity
@Table(name = "parentesco")
@NamedQueries({
    @NamedQuery(name = "Parentesco.findAll", query = "SELECT p FROM Parentesco p"),
    @NamedQuery(name = "Parentesco.findByIdParentesco", query = "SELECT p FROM Parentesco p WHERE p.idParentesco = :idParentesco"),
    @NamedQuery(name = "Parentesco.findByNombreParentesco", query = "SELECT p FROM Parentesco p WHERE p.nombreParentesco = :nombreParentesco")})
public class Parentesco implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_parentesco")
    private Integer idParentesco;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre_parentesco")
    private String nombreParentesco;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idParentesco")
    private List<FamiliaDependientesEmp> familiaDependientesEmpList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idParentesco")
    private List<ContactoEmergenciaEmp> contactoEmergenciaEmpList;

    public Parentesco() {
    }

    public Parentesco(Integer idParentesco) {
        this.idParentesco = idParentesco;
    }

    public Parentesco(Integer idParentesco, String nombreParentesco) {
        this.idParentesco = idParentesco;
        this.nombreParentesco = nombreParentesco;
    }

    public Parentesco(String nombreParentesco) {
        this.nombreParentesco = nombreParentesco;
    }
    
//    public Parentesco(String nuevo_item) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

    public Integer getIdParentesco() {
        return idParentesco;
    }

    public void setIdParentesco(Integer idParentesco) {
        this.idParentesco = idParentesco;
    }

    public String getNombreParentesco() {
        return nombreParentesco;
    }

    public void setNombreParentesco(String nombreParentesco) {
        this.nombreParentesco = nombreParentesco;
    }

    public List<FamiliaDependientesEmp> getFamiliaDependientesEmpList() {
        return familiaDependientesEmpList;
    }

    public void setFamiliaDependientesEmpList(List<FamiliaDependientesEmp> familiaDependientesEmpList) {
        this.familiaDependientesEmpList = familiaDependientesEmpList;
    }

    public List<ContactoEmergenciaEmp> getContactoEmergenciaEmpList() {
        return contactoEmergenciaEmpList;
    }

    public void setContactoEmergenciaEmpList(List<ContactoEmergenciaEmp> contactoEmergenciaEmpList) {
        this.contactoEmergenciaEmpList = contactoEmergenciaEmpList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idParentesco != null ? idParentesco.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Parentesco)) {
            return false;
        }
        Parentesco other = (Parentesco) object;
        if ((this.idParentesco == null && other.idParentesco != null) || (this.idParentesco != null && !this.idParentesco.equals(other.idParentesco))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.gob.cultura.rrhh.entidades.Parentesco[ idParentesco=" + idParentesco + " ]";
    }
    
}
