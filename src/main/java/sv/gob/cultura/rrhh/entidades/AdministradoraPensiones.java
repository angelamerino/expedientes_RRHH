/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.cultura.rrhh.entidades;

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
@Table(name = "administradora_pensiones")
@NamedQueries({
    @NamedQuery(name = "AdministradoraPensiones.findAll", query = "SELECT a FROM AdministradoraPensiones a"),
    @NamedQuery(name = "AdministradoraPensiones.findByIdAdminPension", query = "SELECT a FROM AdministradoraPensiones a WHERE a.idAdminPension = :idAdminPension"),
    @NamedQuery(name = "AdministradoraPensiones.findByNombreAdminPension", query = "SELECT a FROM AdministradoraPensiones a WHERE a.nombreAdminPension = :nombreAdminPension")})
public class AdministradoraPensiones implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_admin_pension")
    private Integer idAdminPension;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "nombre_admin_pension")
    private String nombreAdminPension;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAdminPension")
    private List<Empleados> empleadosList;

    public AdministradoraPensiones() {
    }

    public AdministradoraPensiones(Integer idAdminPension) {
        this.idAdminPension = idAdminPension;
    }

    public AdministradoraPensiones(Integer idAdminPension, String nombreAdminPension) {
        this.idAdminPension = idAdminPension;
        this.nombreAdminPension = nombreAdminPension;
    }

    public AdministradoraPensiones(String nuevo_item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Integer getIdAdminPension() {
        return idAdminPension;
    }

    public void setIdAdminPension(Integer idAdminPension) {
        this.idAdminPension = idAdminPension;
    }

    public String getNombreAdminPension() {
        return nombreAdminPension;
    }

    public void setNombreAdminPension(String nombreAdminPension) {
        this.nombreAdminPension = nombreAdminPension;
    }

    public List<Empleados> getEmpleadosList() {
        return empleadosList;
    }

    public void setEmpleadosList(List<Empleados> empleadosList) {
        this.empleadosList = empleadosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAdminPension != null ? idAdminPension.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AdministradoraPensiones)) {
            return false;
        }
        AdministradoraPensiones other = (AdministradoraPensiones) object;
        if ((this.idAdminPension == null && other.idAdminPension != null) || (this.idAdminPension != null && !this.idAdminPension.equals(other.idAdminPension))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.gob.cultura.rrhh.entidades.AdministradoraPensiones[ idAdminPension=" + idAdminPension + " ]";
    }
    
}
