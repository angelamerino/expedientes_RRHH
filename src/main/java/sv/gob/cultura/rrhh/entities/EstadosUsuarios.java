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
@Table(name = "estados_usuarios")
@NamedQueries({
    @NamedQuery(name = "EstadosUsuarios.findAll", query = "SELECT e FROM EstadosUsuarios e"),
    @NamedQuery(name = "EstadosUsuarios.findByIdEstadoUsuario", query = "SELECT e FROM EstadosUsuarios e WHERE e.idEstadoUsuario = :idEstadoUsuario"),
    @NamedQuery(name = "EstadosUsuarios.findByNombreEstadoUsuario", query = "SELECT e FROM EstadosUsuarios e WHERE e.nombreEstadoUsuario = :nombreEstadoUsuario")})
public class EstadosUsuarios implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_estado_usuario")
    private Integer idEstadoUsuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "nombre_estado_usuario")
    private String nombreEstadoUsuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEstadoUsuario")
    private List<UsuariosSistema> usuariosSistemaList;

    public EstadosUsuarios() {
    }

    public EstadosUsuarios(Integer idEstadoUsuario) {
        this.idEstadoUsuario = idEstadoUsuario;
    }

    public EstadosUsuarios(Integer idEstadoUsuario, String nombreEstadoUsuario) {
        this.idEstadoUsuario = idEstadoUsuario;
        this.nombreEstadoUsuario = nombreEstadoUsuario;
    }

//    public EstadosUsuarios(int estadoUsuarioEditar) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

    
    public Integer getIdEstadoUsuario() {
        return idEstadoUsuario;
    }

    public void setIdEstadoUsuario(Integer idEstadoUsuario) {
        this.idEstadoUsuario = idEstadoUsuario;
    }

    public String getNombreEstadoUsuario() {
        return nombreEstadoUsuario;
    }

    public void setNombreEstadoUsuario(String nombreEstadoUsuario) {
        this.nombreEstadoUsuario = nombreEstadoUsuario;
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
        hash += (idEstadoUsuario != null ? idEstadoUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadosUsuarios)) {
            return false;
        }
        EstadosUsuarios other = (EstadosUsuarios) object;
        if ((this.idEstadoUsuario == null && other.idEstadoUsuario != null) || (this.idEstadoUsuario != null && !this.idEstadoUsuario.equals(other.idEstadoUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.gob.cultura.rrhh.entidades.EstadosUsuarios[ idEstadoUsuario=" + idEstadoUsuario + " ]";
    }
    
}
