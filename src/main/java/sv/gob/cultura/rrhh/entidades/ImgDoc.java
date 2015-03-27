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
@Table(name = "img_doc")
@NamedQueries({
    @NamedQuery(name = "ImgDoc.findAll", query = "SELECT i FROM ImgDoc i")})
public class ImgDoc implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_img_doc")
    private Integer idImgDoc;
    @Size(max = 25)
    @Column(name = "ref_img_doc")
    private String refImgDoc;
    @Size(max = 100)
    @Column(name = "nombre_archivo")
    private String nombreArchivo;
    @Column(name = "size_archivo")
    private Integer sizeArchivo;
    @Size(max = 1024)
    @Column(name = "descripcion")
    private String descripcion;
    @Size(max = 200)
    @Column(name = "ruta_archivo")
    private String rutaArchivo;
    @Size(max = 200)
    @Column(name = "tipo_archivo")
    private String tipoArchivo;
    @JoinColumn(name = "nr_empleado", referencedColumnName = "nr_empleado")
    @ManyToOne(optional = false)
    private Empleados nrEmpleado;

    public ImgDoc() {
    }

    public ImgDoc(Integer idImgDoc) {
        this.idImgDoc = idImgDoc;
    }

    public Integer getIdImgDoc() {
        return idImgDoc;
    }

    public void setIdImgDoc(Integer idImgDoc) {
        this.idImgDoc = idImgDoc;
    }

    public String getRefImgDoc() {
        return refImgDoc;
    }

    public void setRefImgDoc(String refImgDoc) {
        this.refImgDoc = refImgDoc;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public Integer getSizeArchivo() {
        return sizeArchivo;
    }

    public void setSizeArchivo(Integer sizeArchivo) {
        this.sizeArchivo = sizeArchivo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getRutaArchivo() {
        return rutaArchivo;
    }

    public void setRutaArchivo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    public String getTipoArchivo() {
        return tipoArchivo;
    }

    public void setTipoArchivo(String tipoArchivo) {
        this.tipoArchivo = tipoArchivo;
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
        hash += (idImgDoc != null ? idImgDoc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ImgDoc)) {
            return false;
        }
        ImgDoc other = (ImgDoc) object;
        if ((this.idImgDoc == null && other.idImgDoc != null) || (this.idImgDoc != null && !this.idImgDoc.equals(other.idImgDoc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.gob.cultura.rrhh.facades.ImgDoc[ idImgDoc=" + idImgDoc + " ]";
    }
    
}
