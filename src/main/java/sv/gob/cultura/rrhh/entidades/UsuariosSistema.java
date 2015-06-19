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
import sv.gob.cultura.rrhh.facades.UsuariosSistemaFacade;

/**
 *
 * @author Angela
 */
@Entity
@Table(name = "usuarios_sistema")
@NamedQueries({
    @NamedQuery(name = "UsuariosSistema.findAll", query = "SELECT u FROM UsuariosSistema u"),
    @NamedQuery(name = "UsuariosSistema.findByIdUsuario", query = "SELECT u FROM UsuariosSistema u WHERE u.idUsuario = :idUsuario"),
    @NamedQuery(name = "UsuariosSistema.findByNombreCompleto", query = "SELECT u FROM UsuariosSistema u WHERE u.nombreCompleto = :nombreCompleto"),
    @NamedQuery(name = "UsuariosSistema.findByUsuario", query = "SELECT u FROM UsuariosSistema u WHERE u.usuario = :usuario"),
    @NamedQuery(name = "UsuariosSistema.findByClave", query = "SELECT u FROM UsuariosSistema u WHERE u.clave = :clave"),
    @NamedQuery(name = "UsuariosSistema.findByUserCreaUsistema", query = "SELECT u FROM UsuariosSistema u WHERE u.userCreaUsistema = :userCreaUsistema"),
    @NamedQuery(name = "UsuariosSistema.findByFechaCreaUsistema", query = "SELECT u FROM UsuariosSistema u WHERE u.fechaCreaUsistema = :fechaCreaUsistema"),
    @NamedQuery(name = "UsuariosSistema.findByUserModUsistema", query = "SELECT u FROM UsuariosSistema u WHERE u.userModUsistema = :userModUsistema"),
    @NamedQuery(name = "UsuariosSistema.findByFechaModUsistema", query = "SELECT u FROM UsuariosSistema u WHERE u.fechaModUsistema = :fechaModUsistema")})
public class UsuariosSistema implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_usuario")
    private Integer idUsuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1024)
    @Column(name = "nombre_completo")
    private String nombreCompleto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "usuario")
    private String usuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "clave")
    private String clave;
    @Column(name = "user_crea_usistema")
    private Integer userCreaUsistema;
    @Column(name = "fecha_crea_usistema")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreaUsistema;
    @Column(name = "user_mod_usistema")
    private Integer userModUsistema;
    @Column(name = "fecha_mod_usistema")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModUsistema;
    @JoinColumn(name = "id_rol_usuario", referencedColumnName = "id_rol_usuario")
    @ManyToOne(optional = false)
    private RolesUsuario idRolUsuario;
    @JoinColumn(name = "id_estado_usuario", referencedColumnName = "id_estado_usuario")
    @ManyToOne(optional = false)
    private EstadosUsuarios idEstadoUsuario;
    @JoinColumn(name = "id_dependencia", referencedColumnName = "id_dependencia")
    @ManyToOne(optional = false)
    private Dependencias idDependencia;

    public UsuariosSistema() {
    }

    public UsuariosSistema(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public UsuariosSistema(Integer idUsuario, String nombreCompleto, String usuario, String clave) {
        this.idUsuario = idUsuario;
        this.nombreCompleto = nombreCompleto;
        this.usuario = usuario;
        this.clave = clave;
    }

    public UsuariosSistema(UsuariosSistemaFacade nuevo_user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Integer getUserCreaUsistema() {
        return userCreaUsistema;
    }

    public void setUserCreaUsistema(Integer userCreaUsistema) {
        this.userCreaUsistema = userCreaUsistema;
    }

    public Date getFechaCreaUsistema() {
        return fechaCreaUsistema;
    }

    public void setFechaCreaUsistema(Date fechaCreaUsistema) {
        this.fechaCreaUsistema = fechaCreaUsistema;
    }

    public Integer getUserModUsistema() {
        return userModUsistema;
    }

    public void setUserModUsistema(Integer userModUsistema) {
        this.userModUsistema = userModUsistema;
    }

    public Date getFechaModUsistema() {
        return fechaModUsistema;
    }

    public void setFechaModUsistema(Date fechaModUsistema) {
        this.fechaModUsistema = fechaModUsistema;
    }

    public RolesUsuario getIdRolUsuario() {
        return idRolUsuario;
    }

    public void setIdRolUsuario(RolesUsuario idRolUsuario) {
        this.idRolUsuario = idRolUsuario;
    }

    public EstadosUsuarios getIdEstadoUsuario() {
        return idEstadoUsuario;
    }

    public void setIdEstadoUsuario(EstadosUsuarios idEstadoUsuario) {
        this.idEstadoUsuario = idEstadoUsuario;
    }

    public Dependencias getIdDependencia() {
        return idDependencia;
    }

    public void setIdDependencia(Dependencias idDependencia) {
        this.idDependencia = idDependencia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuario != null ? idUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuariosSistema)) {
            return false;
        }
        UsuariosSistema other = (UsuariosSistema) object;
        if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.gob.cultura.rrhh.entidades.UsuariosSistema[ idUsuario=" + idUsuario + " ]";
    }
    
}
