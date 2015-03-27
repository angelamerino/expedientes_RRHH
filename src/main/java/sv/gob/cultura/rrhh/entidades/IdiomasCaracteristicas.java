/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.cultura.rrhh.entidades;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Angela
 */
@Entity
@Table(name = "idiomas_caracteristicas")
@NamedQueries({
    @NamedQuery(name = "IdiomasCaracteristicas.findAll", query = "SELECT i FROM IdiomasCaracteristicas i")})
public class IdiomasCaracteristicas implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected IdiomasCaracteristicasPK idiomasCaracteristicasPK;
    @JoinColumn(name = "id_nivel", referencedColumnName = "id_nivel")
    @ManyToOne(optional = false)
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
        return "sv.gob.cultura.rrhh.facades.IdiomasCaracteristicas[ idiomasCaracteristicasPK=" + idiomasCaracteristicasPK + " ]";
    }
    
}
