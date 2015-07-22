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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Angela
 */
@Entity
@Table(name = "img_doc")
@NamedQueries({
    @NamedQuery(name = "ImgDoc.findAll", query = "SELECT i FROM ImgDoc i"),
    @NamedQuery(name = "ImgDoc.findByIdImgDoc", query = "SELECT i FROM ImgDoc i WHERE i.idImgDoc = :idImgDoc"),
    @NamedQuery(name = "ImgDoc.findByRefImgDoc", query = "SELECT i FROM ImgDoc i WHERE i.refImgDoc = :refImgDoc"),
    @NamedQuery(name = "ImgDoc.findByNombreArchivo", query = "SELECT i FROM ImgDoc i WHERE i.nombreArchivo = :nombreArchivo"),
    @NamedQuery(name = "ImgDoc.findBySizeArchivo", query = "SELECT i FROM ImgDoc i WHERE i.sizeArchivo = :sizeArchivo"),
    @NamedQuery(name = "ImgDoc.findByDescripcion", query = "SELECT i FROM ImgDoc i WHERE i.descripcion = :descripcion"),
    @NamedQuery(name = "ImgDoc.findByRutaArchivo", query = "SELECT i FROM ImgDoc i WHERE i.rutaArchivo = :rutaArchivo"),
    @NamedQuery(name = "ImgDoc.findByTipoArchivo", query = "SELECT i FROM ImgDoc i WHERE i.tipoArchivo = :tipoArchivo")})
public class ImgDoc implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_img_doc")
    private Integer idImgDoc;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "ref_img_doc")
    private String refImgDoc;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre_archivo")
    private String nombreArchivo;
    @Column(name = "size_archivo")
    private Integer sizeArchivo;
    @Size(max = 1024)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "ruta_archivo")
    private String rutaArchivo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "tipo_archivo")
    private String tipoArchivo;
    @JoinColumn(name = "id_sancion", referencedColumnName = "id_sancion")
    @ManyToOne
    private Sanciones idSancion;
    @JoinColumn(name = "id_reconocimiento", referencedColumnName = "id_reconocimiento")
    @ManyToOne
    private Reconocimientos idReconocimiento;
    @JoinColumn(name = "id_mov_emp", referencedColumnName = "id_mov_emp")
    @ManyToOne
    private MovimientosEmp idMovEmp;
    @JoinColumn(name = "id_hsalarial", referencedColumnName = "id_hsalarial")
    @ManyToOne
    private HistorialSalarial idHsalarial;
    @JoinColumn(name = "id_estudio", referencedColumnName = "id_estudio")
    @ManyToOne
    private EstudiosEmp idEstudio;
    @JoinColumn(name = "id_empleado", referencedColumnName = "id_empleado")
    @ManyToOne(optional = false)
    private Empleados idEmpleado;
    @JoinColumn(name = "id_descuento_emp", referencedColumnName = "id_descuento_emp")
    @ManyToOne
    private DescuentosEmp idDescuentoEmp;

    public ImgDoc() {
    }

    public ImgDoc(Integer idImgDoc) {
        this.idImgDoc = idImgDoc;
    }

    public ImgDoc(Integer idImgDoc, String refImgDoc, String nombreArchivo, String rutaArchivo, String tipoArchivo) {
        this.idImgDoc = idImgDoc;
        this.refImgDoc = refImgDoc;
        this.nombreArchivo = nombreArchivo;
        this.rutaArchivo = rutaArchivo;
        this.tipoArchivo = tipoArchivo;
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

    public Sanciones getIdSancion() {
        return idSancion;
    }

    public void setIdSancion(Sanciones idSancion) {
        this.idSancion = idSancion;
    }

    public Reconocimientos getIdReconocimiento() {
        return idReconocimiento;
    }

    public void setIdReconocimiento(Reconocimientos idReconocimiento) {
        this.idReconocimiento = idReconocimiento;
    }

    public MovimientosEmp getIdMovEmp() {
        return idMovEmp;
    }

    public void setIdMovEmp(MovimientosEmp idMovEmp) {
        this.idMovEmp = idMovEmp;
    }

    public HistorialSalarial getIdHsalarial() {
        return idHsalarial;
    }

    public void setIdHsalarial(HistorialSalarial idHsalarial) {
        this.idHsalarial = idHsalarial;
    }

    public EstudiosEmp getIdEstudio() {
        return idEstudio;
    }

    public void setIdEstudio(EstudiosEmp idEstudio) {
        this.idEstudio = idEstudio;
    }

    public Empleados getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Empleados idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public DescuentosEmp getIdDescuentoEmp() {
        return idDescuentoEmp;
    }

    public void setIdDescuentoEmp(DescuentosEmp idDescuentoEmp) {
        this.idDescuentoEmp = idDescuentoEmp;
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
        return "sv.gob.cultura.rrhh.entidades.ImgDoc[ idImgDoc=" + idImgDoc + " ]";
    }

    public void setIdiomasCaracteristicas(IdiomasCaracteristicas idiomasCaracteristicas) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
