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
@Table(name = "roles_usuario")
@NamedQueries({
    @NamedQuery(name = "RolesUsuario.findAll", query = "SELECT r FROM RolesUsuario r"),
    @NamedQuery(name = "RolesUsuario.findByIdRolUsuario", query = "SELECT r FROM RolesUsuario r WHERE r.idRolUsuario = :idRolUsuario"),
    @NamedQuery(name = "RolesUsuario.findByNombreRolUsuario", query = "SELECT r FROM RolesUsuario r WHERE r.nombreRolUsuario = :nombreRolUsuario")})
public class RolesUsuario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_rol_usuario")
    private Integer idRolUsuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "nombre_rol_usuario")
    private String nombreRolUsuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRolUsuario")
    private List<UsuariosSistema> usuariosSistemaList;

    public RolesUsuario() {
    }

    public RolesUsuario(Integer idRolUsuario) {
        this.idRolUsuario = idRolUsuario;
    }

    public RolesUsuario(Integer idRolUsuario, String nombreRolUsuario) {
        this.idRolUsuario = idRolUsuario;
        this.nombreRolUsuario = nombreRolUsuario;
    }

    public Integer getIdRolUsuario() {
        return idRolUsuario;
    }

    public void setIdRolUsuario(Integer idRolUsuario) {
        this.idRolUsuario = idRolUsuario;
    }

    public String getNombreRolUsuario() {
        return nombreRolUsuario;
    }

    public void setNombreRolUsuario(String nombreRolUsuario) {
        this.nombreRolUsuario = nombreRolUsuario;
    }

    public List<UsuariosSistema> getUsuariosSistemaList() {
        return usuariosSistemaList;
    }

    public void setUsuariosSistemaList(List<UsuariosSistema> usuariosSistemaList) {
        this.usuariosSistemaList = usuariosSistemaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRolUsuario != null ? idRolUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RolesUsuario)) {
            return false;
        }
        RolesUsuario other = (RolesUsuario) object;
        if ((this.idRolUsuario == null && other.idRolUsuario != null) || (this.idRolUsuario != null && !this.idRolUsuario.equals(other.idRolUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.gob.cultura.rrhh.entidades.RolesUsuario[ idRolUsuario=" + idRolUsuario + " ]";
    }
    
}
