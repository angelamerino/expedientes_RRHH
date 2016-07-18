/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.cultura.rrhh.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
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
@Table(name = "estudios_emp")
@NamedQueries({
    @NamedQuery(name = "EstudiosEmp.findAll", query = "SELECT e FROM EstudiosEmp e"),
    @NamedQuery(name = "EstudiosEmp.findByIdEstudio", query = "SELECT e FROM EstudiosEmp e WHERE e.idEstudio = :idEstudio"),
    @NamedQuery(name = "EstudiosEmp.findByInstitucionEstudio", query = "SELECT e FROM EstudiosEmp e WHERE e.institucionEstudio = :institucionEstudio"),
    @NamedQuery(name = "EstudiosEmp.findByAnioEstudio", query = "SELECT e FROM EstudiosEmp e WHERE e.anioEstudio = :anioEstudio"),
    @NamedQuery(name = "EstudiosEmp.findByTipoEstudio", query = "SELECT e FROM EstudiosEmp e WHERE e.tipoEstudio = :tipoEstudio"),
    @NamedQuery(name = "EstudiosEmp.findByUserCreaEstudios", query = "SELECT e FROM EstudiosEmp e WHERE e.userCreaEstudios = :userCreaEstudios"),
    @NamedQuery(name = "EstudiosEmp.findByFechaCreaEstudios", query = "SELECT e FROM EstudiosEmp e WHERE e.fechaCreaEstudios = :fechaCreaEstudios"),
    @NamedQuery(name = "EstudiosEmp.findByUserModEstudios", query = "SELECT e FROM EstudiosEmp e WHERE e.userModEstudios = :userModEstudios"),
    //@NamedQuery(name = "EstudiosEmp.findByTipoEmp", query = "SELECT e FROM EstudiosEmp e WHERE e.tipoEstudio = :tipoEstudio AND e. = :idEmpleado"),
    @NamedQuery(name = "EstudiosEmp.findByFechaModEstudios", query = "SELECT e FROM EstudiosEmp e WHERE e.fechaModEstudios = :fechaModEstudios")})
public class EstudiosEmp implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_estudio")
    private Integer idEstudio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "institucion_estudio")
    private String institucionEstudio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "anio_estudio")
    @Temporal(TemporalType.DATE)
    private Date anioEstudio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "tipo_estudio")
    private String tipoEstudio;
    @Size(min = 1, max = 1024)
    @Column(name = "descripcion_no_formal")
    private String descripcionNoFormal;
    @Column(name = "user_crea_estudios")
    private Integer userCreaEstudios;
    @Column(name = "fecha_crea_estudios")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreaEstudios;
    @Column(name = "user_mod_estudios")
    private Integer userModEstudios;
    @Column(name = "fecha_mod_estudios")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModEstudios;
    @OneToMany(mappedBy = "idEstudio")
    private List<ImgDoc> imgDocList;
    @JoinColumn(name = "id_prof_oficio", referencedColumnName = "id_prof_oficio")
    @ManyToOne
    private ProfOficios idProfOficio;
    @JoinColumn(name = "id_empleado", referencedColumnName = "id_empleado")
    @ManyToOne(optional = false)
    private Empleados idEmpleado;

    public EstudiosEmp() {
    }

    public EstudiosEmp(Integer idEstudio) {
        this.idEstudio = idEstudio;
    }

    public String getDescripcionNoFormal() {
        return descripcionNoFormal;
    }

    public void setDescripcionNoFormal(String descripcionNoFormal) {
        this.descripcionNoFormal = descripcionNoFormal;
    }

    public EstudiosEmp(Integer idEstudio, String institucionEstudio, Date anioEstudio, String tipoEstudio) {
        this.idEstudio = idEstudio;
        this.institucionEstudio = institucionEstudio;
        this.anioEstudio = anioEstudio;
        this.tipoEstudio = tipoEstudio;
    }

    public Integer getIdEstudio() {
        return idEstudio;
    }

    public void setIdEstudio(Integer idEstudio) {
        this.idEstudio = idEstudio;
    }

    public String getInstitucionEstudio() {
        return institucionEstudio;
    }

    public void setInstitucionEstudio(String institucionEstudio) {
        this.institucionEstudio = institucionEstudio;
    }

    public Date getAnioEstudio() {
        return anioEstudio;
    }

    public void setAnioEstudio(Date anioEstudio) {
        this.anioEstudio = anioEstudio;
    }

    public String getTipoEstudio() {
        return tipoEstudio;
    }

    public void setTipoEstudio(String tipoEstudio) {
        this.tipoEstudio = tipoEstudio;
    }

    public Integer getUserCreaEstudios() {
        return userCreaEstudios;
    }

    public void setUserCreaEstudios(Integer userCreaEstudios) {
        this.userCreaEstudios = userCreaEstudios;
    }

    public Date getFechaCreaEstudios() {
        return fechaCreaEstudios;
    }

    public void setFechaCreaEstudios(Date fechaCreaEstudios) {
        this.fechaCreaEstudios = fechaCreaEstudios;
    }

    public Integer getUserModEstudios() {
        return userModEstudios;
    }

    public void setUserModEstudios(Integer userModEstudios) {
        this.userModEstudios = userModEstudios;
    }

    public Date getFechaModEstudios() {
        return fechaModEstudios;
    }

    public void setFechaModEstudios(Date fechaModEstudios) {
        this.fechaModEstudios = fechaModEstudios;
    }

    public List<ImgDoc> getImgDocList() {
        return imgDocList;
    }

    public void setImgDocList(List<ImgDoc> imgDocList) {
        this.imgDocList = imgDocList;
    }

    public ProfOficios getIdProfOficio() {
        return idProfOficio;
    }

    public void setIdProfOficio(ProfOficios idProfOficio) {
        this.idProfOficio = idProfOficio;
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
        hash += (idEstudio != null ? idEstudio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstudiosEmp)) {
            return false;
        }
        EstudiosEmp other = (EstudiosEmp) object;
        if ((this.idEstudio == null && other.idEstudio != null) || (this.idEstudio != null && !this.idEstudio.equals(other.idEstudio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.gob.cultura.rrhh.entidades.EstudiosEmp[ idEstudio=" + idEstudio + " ]";
    }

}
