/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.cultura.rrhh.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author Angela
 */
@Entity
@Table(name = "estudios_emp")
@NamedQueries({
    @NamedQuery(name = "EstudiosEmp.findAll", query = "SELECT e FROM EstudiosEmp e")})
public class EstudiosEmp implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_estudio")
    private Integer idEstudio;
    @Size(max = 200)
    @Column(name = "institucion_estudio")
    private String institucionEstudio;
    @Size(max = 1024)
    @Column(name = "descripcion_estudio")
    private String descripcionEstudio;
    @Column(name = "anio_estudio")
    @Temporal(TemporalType.DATE)
    private Date anioEstudio;
    @Size(max = 1024)
    @Column(name = "documento_estudio")
    private String documentoEstudio;
    @Size(max = 9)
    @Column(name = "tipo_estudio")
    private String tipoEstudio;
    @ManyToMany(mappedBy = "estudiosEmpList")
    private List<Empleados> empleadosList;

    public EstudiosEmp() {
    }

    public EstudiosEmp(Integer idEstudio) {
        this.idEstudio = idEstudio;
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

    public String getDescripcionEstudio() {
        return descripcionEstudio;
    }

    public void setDescripcionEstudio(String descripcionEstudio) {
        this.descripcionEstudio = descripcionEstudio;
    }

    public Date getAnioEstudio() {
        return anioEstudio;
    }

    public void setAnioEstudio(Date anioEstudio) {
        this.anioEstudio = anioEstudio;
    }

    public String getDocumentoEstudio() {
        return documentoEstudio;
    }

    public void setDocumentoEstudio(String documentoEstudio) {
        this.documentoEstudio = documentoEstudio;
    }

    public String getTipoEstudio() {
        return tipoEstudio;
    }

    public void setTipoEstudio(String tipoEstudio) {
        this.tipoEstudio = tipoEstudio;
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
        return "sv.gob.cultura.rrhh.facades.EstudiosEmp[ idEstudio=" + idEstudio + " ]";
    }
    
}
