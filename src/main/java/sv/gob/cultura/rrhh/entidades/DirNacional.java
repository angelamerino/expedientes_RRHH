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
@Table(name = "dir_nacional")
@NamedQueries({
    @NamedQuery(name = "DirNacional.findAll", query = "SELECT d FROM DirNacional d"),
    @NamedQuery(name = "DirNacional.findByIdDirNac", query = "SELECT d FROM DirNacional d WHERE d.idDirNac = :idDirNac"),
    @NamedQuery(name = "DirNacional.findByNombreDirNac", query = "SELECT d FROM DirNacional d WHERE d.nombreDirNac = :nombreDirNac"),
    @NamedQuery(name = "DirNacional.findByUserCreaDir", query = "SELECT d FROM DirNacional d WHERE d.userCreaDir = :userCreaDir"),
    @NamedQuery(name = "DirNacional.findByFechaCreaDir", query = "SELECT d FROM DirNacional d WHERE d.fechaCreaDir = :fechaCreaDir"),
    @NamedQuery(name = "DirNacional.findByUserModDir", query = "SELECT d FROM DirNacional d WHERE d.userModDir = :userModDir"),
    @NamedQuery(name = "DirNacional.findByFechaModDir", query = "SELECT d FROM DirNacional d WHERE d.fechaModDir = :fechaModDir")})
public class DirNacional implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_dir_nac")
    private Integer idDirNac;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1024)
    @Column(name = "nombre_dir_nac")
    private String nombreDirNac;
    @Column(name = "user_crea_dir")
    private Integer userCreaDir;
    @Column(name = "fecha_crea_dir")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreaDir;
    @Column(name = "user_mod_dir")
    private Integer userModDir;
    @Column(name = "fecha_mod_dir")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModDir;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDirNac")
    private List<Dependencias> dependenciasList;

    public DirNacional() {
    }

    public DirNacional(Integer idDirNac) {
        this.idDirNac = idDirNac;
    }

    public DirNacional(Integer idDirNac, String nombreDirNac) {
        this.idDirNac = idDirNac;
        this.nombreDirNac = nombreDirNac;
    }

    public Integer getIdDirNac() {
        return idDirNac;
    }

    public void setIdDirNac(Integer idDirNac) {
        this.idDirNac = idDirNac;
    }

    public String getNombreDirNac() {
        return nombreDirNac;
    }

    public void setNombreDirNac(String nombreDirNac) {
        this.nombreDirNac = nombreDirNac;
    }

    public Integer getUserCreaDir() {
        return userCreaDir;
    }

    public void setUserCreaDir(Integer userCreaDir) {
        this.userCreaDir = userCreaDir;
    }

    public Date getFechaCreaDir() {
        return fechaCreaDir;
    }

    public void setFechaCreaDir(Date fechaCreaDir) {
        this.fechaCreaDir = fechaCreaDir;
    }

    public Integer getUserModDir() {
        return userModDir;
    }

    public void setUserModDir(Integer userModDir) {
        this.userModDir = userModDir;
    }

    public Date getFechaModDir() {
        return fechaModDir;
    }

    public void setFechaModDir(Date fechaModDir) {
        this.fechaModDir = fechaModDir;
    }

    public List<Dependencias> getDependenciasList() {
        return dependenciasList;
    }

    public void setDependenciasList(List<Dependencias> dependenciasList) {
        this.dependenciasList = dependenciasList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDirNac != null ? idDirNac.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DirNacional)) {
            return false;
        }
        DirNacional other = (DirNacional) object;
        if ((this.idDirNac == null && other.idDirNac != null) || (this.idDirNac != null && !this.idDirNac.equals(other.idDirNac))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.gob.cultura.rrhh.entidades.DirNacional[ idDirNac=" + idDirNac + " ]";
    }
    
}
