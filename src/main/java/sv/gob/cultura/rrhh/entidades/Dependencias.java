/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.cultura.rrhh.entidades;

import java.io.Serializable;//
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
@Table(name = "dependencias")
@NamedQueries({
    @NamedQuery(name = "Dependencias.findAll", query = "SELECT d FROM Dependencias d"),
    @NamedQuery(name = "Dependencias.findByIdDependencia", query = "SELECT d FROM Dependencias d WHERE d.idDependencia = :idDependencia"),
    @NamedQuery(name = "Dependencias.findByIdDirNacional", query = "SELECT d FROM Dependencias d WHERE d.idDirNac.idDirNac = :idDirNac"),
    @NamedQuery(name = "Dependencias.findByNombreDependencia", query = "SELECT d FROM Dependencias d WHERE d.nombreDependencia = :nombreDependencia"),
    @NamedQuery(name = "Dependencias.findByLatitud", query = "SELECT d FROM Dependencias d WHERE d.latitud = :latitud"),
    @NamedQuery(name = "Dependencias.findByLongitud", query = "SELECT d FROM Dependencias d WHERE d.longitud = :longitud"),
    @NamedQuery(name = "Dependencias.findByUserCreaDependencia", query = "SELECT d FROM Dependencias d WHERE d.userCreaDependencia = :userCreaDependencia"),
    @NamedQuery(name = "Dependencias.findByFechaCreaDependencia", query = "SELECT d FROM Dependencias d WHERE d.fechaCreaDependencia = :fechaCreaDependencia"),
    @NamedQuery(name = "Dependencias.findByUserModDependencia", query = "SELECT d FROM Dependencias d WHERE d.userModDependencia = :userModDependencia"),
    @NamedQuery(name = "Dependencias.findByFechaModDependencia", query = "SELECT d FROM Dependencias d WHERE d.fechaModDependencia = :fechaModDependencia")})
public class Dependencias implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_dependencia")
    private Integer idDependencia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1024)
    @Column(name = "nombre_dependencia")
    private String nombreDependencia;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "latitud")
    private Double latitud;
    @Column(name = "longitud")
    private Double longitud;
    @Column(name = "user_crea_dependencia")
    private Integer userCreaDependencia;
    @Column(name = "fecha_crea_dependencia")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreaDependencia;
    @Column(name = "user_mod_dependencia")
    private Integer userModDependencia;
    @Column(name = "fecha_mod_dependencia")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModDependencia;
    @JoinColumn(name = "id_municipio", referencedColumnName = "id_municipio")
    @ManyToOne(optional = false)
    private Municipios idMunicipio;
    @JoinColumn(name = "id_dir_nac", referencedColumnName = "id_dir_nac")
    @ManyToOne(optional = false)
    private DirNacional idDirNac;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "depIdDependencia")
    private List<MovimientosEmp> movimientosEmpList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDependencia")
    private List<MovimientosEmp> movimientosEmpList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDependencia")
    private List<UsuariosSistema> usuariosSistemaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDependenciaF")
    private List<Empleados> empleadosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDependenciaN")
    private List<Empleados> empleadosList1;

    public Dependencias() {
    }

    public Dependencias(Integer idDependencia) {
        this.idDependencia = idDependencia;
    }

    public Dependencias(Integer idDependencia, String nombreDependencia) {
        this.idDependencia = idDependencia;
        this.nombreDependencia = nombreDependencia;
    }

    public Integer getIdDependencia() {
        return idDependencia;
    }

    public void setIdDependencia(Integer idDependencia) {
        this.idDependencia = idDependencia;
    }

    public String getNombreDependencia() {
        return nombreDependencia;
    }

    public void setNombreDependencia(String nombreDependencia) {
        this.nombreDependencia = nombreDependencia;
    }

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    public Integer getUserCreaDependencia() {
        return userCreaDependencia;
    }

    public void setUserCreaDependencia(Integer userCreaDependencia) {
        this.userCreaDependencia = userCreaDependencia;
    }

    public Date getFechaCreaDependencia() {
        return fechaCreaDependencia;
    }

    public void setFechaCreaDependencia(Date fechaCreaDependencia) {
        this.fechaCreaDependencia = fechaCreaDependencia;
    }

    public Integer getUserModDependencia() {
        return userModDependencia;
    }

    public void setUserModDependencia(Integer userModDependencia) {
        this.userModDependencia = userModDependencia;
    }

    public Date getFechaModDependencia() {
        return fechaModDependencia;
    }

    public void setFechaModDependencia(Date fechaModDependencia) {
        this.fechaModDependencia = fechaModDependencia;
    }

    public Municipios getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(Municipios idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    public DirNacional getIdDirNac() {
        return idDirNac;
    }

    public void setIdDirNac(DirNacional idDirNac) {
        this.idDirNac = idDirNac;
    }

    public List<MovimientosEmp> getMovimientosEmpList() {
        return movimientosEmpList;
    }

    public void setMovimientosEmpList(List<MovimientosEmp> movimientosEmpList) {
        this.movimientosEmpList = movimientosEmpList;
    }

    public List<MovimientosEmp> getMovimientosEmpList1() {
        return movimientosEmpList1;
    }

    public void setMovimientosEmpList1(List<MovimientosEmp> movimientosEmpList1) {
        this.movimientosEmpList1 = movimientosEmpList1;
    }

    public List<UsuariosSistema> getUsuariosSistemaList() {
        return usuariosSistemaList;
    }

    public void setUsuariosSistemaList(List<UsuariosSistema> usuariosSistemaList) {
        this.usuariosSistemaList = usuariosSistemaList;
    }

    public List<Empleados> getEmpleadosList() {
        return empleadosList;
    }

    public void setEmpleadosList(List<Empleados> empleadosList) {
        this.empleadosList = empleadosList;
    }

    public List<Empleados> getEmpleadosList1() {
        return empleadosList1;
    }

    public void setEmpleadosList1(List<Empleados> empleadosList1) {
        this.empleadosList1 = empleadosList1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDependencia != null ? idDependencia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dependencias)) {
            return false;
        }
        Dependencias other = (Dependencias) object;
        if ((this.idDependencia == null && other.idDependencia != null) || (this.idDependencia != null && !this.idDependencia.equals(other.idDependencia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.gob.cultura.rrhh.entidades.Dependencias[ idDependencia=" + idDependencia + " ]";
    }
    
}
