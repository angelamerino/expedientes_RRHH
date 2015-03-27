/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.cultura.rrhh.entidades;

import java.io.Serializable;
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
import javax.validation.constraints.Size;

/**
 *
 * @author Angela
 */
@Entity
@Table(name = "caracteristicas_idioma")
@NamedQueries({
    @NamedQuery(name = "CaracteristicasIdioma.findAll", query = "SELECT c FROM CaracteristicasIdioma c")})
public class CaracteristicasIdioma implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_caract_idioma")
    private Integer idCaractIdioma;
    @Size(max = 25)
    @Column(name = "caracteristica_idioma")
    private String caracteristicaIdioma;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "caracteristicasIdioma")
    private List<IdiomasCaracteristicas> idiomasCaracteristicasList;

    public CaracteristicasIdioma() {
    }

    public CaracteristicasIdioma(Integer idCaractIdioma) {
        this.idCaractIdioma = idCaractIdioma;
    }

    public Integer getIdCaractIdioma() {
        return idCaractIdioma;
    }

    public void setIdCaractIdioma(Integer idCaractIdioma) {
        this.idCaractIdioma = idCaractIdioma;
    }

    public String getCaracteristicaIdioma() {
        return caracteristicaIdioma;
    }

    public void setCaracteristicaIdioma(String caracteristicaIdioma) {
        this.caracteristicaIdioma = caracteristicaIdioma;
    }

    public List<IdiomasCaracteristicas> getIdiomasCaracteristicasList() {
        return idiomasCaracteristicasList;
    }

    public void setIdiomasCaracteristicasList(List<IdiomasCaracteristicas> idiomasCaracteristicasList) {
        this.idiomasCaracteristicasList = idiomasCaracteristicasList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCaractIdioma != null ? idCaractIdioma.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CaracteristicasIdioma)) {
            return false;
        }
        CaracteristicasIdioma other = (CaracteristicasIdioma) object;
        if ((this.idCaractIdioma == null && other.idCaractIdioma != null) || (this.idCaractIdioma != null && !this.idCaractIdioma.equals(other.idCaractIdioma))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.gob.cultura.rrhh.facades.CaracteristicasIdioma[ idCaractIdioma=" + idCaractIdioma + " ]";
    }
    
}
