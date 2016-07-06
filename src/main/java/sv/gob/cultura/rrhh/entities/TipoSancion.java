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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "tipo_sancion")
@NamedQueries({
    @NamedQuery(name = "TipoSancion.findAll", query = "SELECT t FROM TipoSancion t"),
    @NamedQuery(name = "TipoSancion.findByIdTipoSancion", query = "SELECT t FROM TipoSancion t WHERE t.idTipoSancion = :idTipoSancion"),
    @NamedQuery(name = "TipoSancion.findByIdGradoSancion", query = "SELECT t FROM TipoSancion t WHERE t.idGradoSancion.idGradoSancion = :idGradoSancion"),
    @NamedQuery(name = "TipoSancion.findByNombreTipoSancion", query = "SELECT t FROM TipoSancion t WHERE t.nombreTipoSancion = :nombreTipoSancion")})
public class TipoSancion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_sancion")
    private Integer idTipoSancion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre_tipo_sancion")
    private String nombreTipoSancion;
    @JoinColumn(name = "id_grado_sancion", referencedColumnName = "id_grado_sancion")
    @ManyToOne(optional = false)
    private GradoSancion idGradoSancion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoSancion")
    private List<Sanciones> sancionesList;

    public TipoSancion() {
    }

    public TipoSancion(Integer idTipoSancion) {
        this.idTipoSancion = idTipoSancion;
    }

    public TipoSancion(Integer idTipoSancion, String nombreTipoSancion) {
        this.idTipoSancion = idTipoSancion;
        this.nombreTipoSancion = nombreTipoSancion;
    }

    public Integer getIdTipoSancion() {
        return idTipoSancion;
    }

    public void setIdTipoSancion(Integer idTipoSancion) {
        this.idTipoSancion = idTipoSancion;
    }

    public String getNombreTipoSancion() {
        return nombreTipoSancion;
    }

    public void setNombreTipoSancion(String nombreTipoSancion) {
        this.nombreTipoSancion = nombreTipoSancion;
    }

    public GradoSancion getIdGradoSancion() {
        return idGradoSancion;
    }

    public void setIdGradoSancion(GradoSancion idGradoSancion) {
        this.idGradoSancion = idGradoSancion;
    }

    public List<Sanciones> getSancionesList() {
        return sancionesList;
    }

    public void setSancionesList(List<Sanciones> sancionesList) {
        this.sancionesList = sancionesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoSancion != null ? idTipoSancion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoSancion)) {
            return false;
        }
        TipoSancion other = (TipoSancion) object;
        if ((this.idTipoSancion == null && other.idTipoSancion != null) || (this.idTipoSancion != null && !this.idTipoSancion.equals(other.idTipoSancion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.gob.cultura.rrhh.entidades.TipoSancion[ idTipoSancion=" + idTipoSancion + " ]";
    }
    
}
