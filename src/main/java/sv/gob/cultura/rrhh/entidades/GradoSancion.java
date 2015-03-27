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
@Table(name = "grado_sancion")
@NamedQueries({
    @NamedQuery(name = "GradoSancion.findAll", query = "SELECT g FROM GradoSancion g")})
public class GradoSancion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_grado_sancion")
    private Integer idGradoSancion;
    @Size(max = 100)
    @Column(name = "nombre_grado_sancion")
    private String nombreGradoSancion;
    @JoinColumn(name = "id_tipo_sancion", referencedColumnName = "id_tipo_sancion")
    @ManyToOne(optional = false)
    private TipoSancion idTipoSancion;

    public GradoSancion() {
    }

    public GradoSancion(Integer idGradoSancion) {
        this.idGradoSancion = idGradoSancion;
    }

    public Integer getIdGradoSancion() {
        return idGradoSancion;
    }

    public void setIdGradoSancion(Integer idGradoSancion) {
        this.idGradoSancion = idGradoSancion;
    }

    public String getNombreGradoSancion() {
        return nombreGradoSancion;
    }

    public void setNombreGradoSancion(String nombreGradoSancion) {
        this.nombreGradoSancion = nombreGradoSancion;
    }

    public TipoSancion getIdTipoSancion() {
        return idTipoSancion;
    }

    public void setIdTipoSancion(TipoSancion idTipoSancion) {
        this.idTipoSancion = idTipoSancion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idGradoSancion != null ? idGradoSancion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GradoSancion)) {
            return false;
        }
        GradoSancion other = (GradoSancion) object;
        if ((this.idGradoSancion == null && other.idGradoSancion != null) || (this.idGradoSancion != null && !this.idGradoSancion.equals(other.idGradoSancion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.gob.cultura.rrhh.facades.GradoSancion[ idGradoSancion=" + idGradoSancion + " ]";
    }
    
}
