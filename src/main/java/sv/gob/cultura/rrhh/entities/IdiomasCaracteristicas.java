/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.cultura.rrhh.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Table(name = "idiomas_caracteristicas")
@NamedQueries({
    @NamedQuery(name = "IdiomasCaracteristicas.findAll", query = "SELECT i FROM IdiomasCaracteristicas i"),
    @NamedQuery(name = "IdiomasCaracteristicas.findByIdCaractIdioma", query = "SELECT i FROM IdiomasCaracteristicas i WHERE i.idiomasCaracteristicasPK.idCaractIdioma = :idCaractIdioma"),
    @NamedQuery(name = "IdiomasCaracteristicas.findByIdIdioma", query = "SELECT i FROM IdiomasCaracteristicas i WHERE i.idiomasCaracteristicasPK.idIdioma = :idIdioma"),
    @NamedQuery(name = "IdiomasCaracteristicas.findByIdIdiomaIdEmpleado", query = "SELECT i FROM IdiomasCaracteristicas i WHERE i.idiomasCaracteristicasPK.idIdioma = :idIdioma AND i.idEmpleado = :idEmpleado"),
    @NamedQuery(name = "IdiomasCaracteristicas.findByInstitucionIdioma", query = "SELECT i FROM IdiomasCaracteristicas i WHERE i.institucionIdioma = :institucionIdioma")})
public class IdiomasCaracteristicas implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected IdiomasCaracteristicasPK idiomasCaracteristicasPK;
    @Size(max = 1024)
    @Column(name = "institucion_idioma")
    private String institucionIdioma;
    @Column(name = "id_empleado")
    private Integer idEmpleado;
    @JoinColumn(name = "id_nivel", referencedColumnName = "id_nivel")
    @ManyToOne
    private Nivel idNivel;
    @JoinColumn(name = "id_idioma", referencedColumnName = "id_idioma", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Idiomas idiomas;
    @JoinColumn(name = "id_caract_idioma", referencedColumnName = "id_caract_idioma", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private CaracteristicasIdioma caracteristicasIdioma;

    public IdiomasCaracteristicas() {
    }

    public IdiomasCaracteristicas(IdiomasCaracteristicasPK idiomasCaracteristicasPK) {
        this.idiomasCaracteristicasPK = idiomasCaracteristicasPK;
    }

    public IdiomasCaracteristicas(int idCaractIdioma, int idIdioma) {
        this.idiomasCaracteristicasPK = new IdiomasCaracteristicasPK(idCaractIdioma, idIdioma);
    }

    public IdiomasCaracteristicasPK getIdiomasCaracteristicasPK() {
        return idiomasCaracteristicasPK;
    }

    public void setIdiomasCaracteristicasPK(IdiomasCaracteristicasPK idiomasCaracteristicasPK) {
        this.idiomasCaracteristicasPK = idiomasCaracteristicasPK;
    }

    public String getInstitucionIdioma() {
        return institucionIdioma;
    }

    public void setInstitucionIdioma(String institucionIdioma) {
        this.institucionIdioma = institucionIdioma;
    }

    public Nivel getIdNivel() {
        return idNivel;
    }

    public void setIdNivel(Nivel idNivel) {
        this.idNivel = idNivel;
    }

    public Idiomas getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(Idiomas idiomas) {
        this.idiomas = idiomas;
    }

    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    
    public CaracteristicasIdioma getCaracteristicasIdioma() {
        return caracteristicasIdioma;
    }

    public void setCaracteristicasIdioma(CaracteristicasIdioma caracteristicasIdioma) {
        this.caracteristicasIdioma = caracteristicasIdioma;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idiomasCaracteristicasPK != null ? idiomasCaracteristicasPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IdiomasCaracteristicas)) {
            return false;
        }
        IdiomasCaracteristicas other = (IdiomasCaracteristicas) object;
        if ((this.idiomasCaracteristicasPK == null && other.idiomasCaracteristicasPK != null) || (this.idiomasCaracteristicasPK != null && !this.idiomasCaracteristicasPK.equals(other.idiomasCaracteristicasPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.gob.cultura.rrhh.entidades.IdiomasCaracteristicas[ idiomasCaracteristicasPK=" + idiomasCaracteristicasPK + " ]";
    }

   
    
}
