/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.cultura.rrhh.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Angela
 */
@Embeddable
public class IdiomasCaracteristicasPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_caract_idioma")
    private int idCaractIdioma;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_idioma")
    private int idIdioma;

    public IdiomasCaracteristicasPK() {
    }

    public IdiomasCaracteristicasPK(int idCaractIdioma, int idIdioma) {
        this.idCaractIdioma = idCaractIdioma;
        this.idIdioma = idIdioma;
    }

    public int getIdCaractIdioma() {
        return idCaractIdioma;
    }

    public void setIdCaractIdioma(int idCaractIdioma) {
        this.idCaractIdioma = idCaractIdioma;
    }

    public int getIdIdioma() {
        return idIdioma;
    }

    public void setIdIdioma(int idIdioma) {
        this.idIdioma = idIdioma;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idCaractIdioma;
        hash += (int) idIdioma;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IdiomasCaracteristicasPK)) {
            return false;
        }
        IdiomasCaracteristicasPK other = (IdiomasCaracteristicasPK) object;
        if (this.idCaractIdioma != other.idCaractIdioma) {
            return false;
        }
        if (this.idIdioma != other.idIdioma) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.gob.cultura.rrhh.facades.IdiomasCaracteristicasPK[ idCaractIdioma=" + idCaractIdioma + ", idIdioma=" + idIdioma + " ]";
    }
    
}
