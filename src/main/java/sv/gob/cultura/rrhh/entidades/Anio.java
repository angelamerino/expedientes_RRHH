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
import javax.validation.constraints.NotNull;

/**
 *
 * @author Angela
 */
@Entity
@Table(name = "anio")
@NamedQueries({
    @NamedQuery(name = "Anio.findAll", query = "SELECT a FROM Anio a"),
    @NamedQuery(name = "Anio.findByIdAnio", query = "SELECT a FROM Anio a WHERE a.idAnio = :idAnio"),
    @NamedQuery(name = "Anio.findByAnio", query = "SELECT a FROM Anio a WHERE a.anio = :anio")})
public class Anio implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_anio")
    private Integer idAnio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "anio")
    private int anio;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAnio")
    private List<Prestacion> prestacionList;

    public Anio() {
    }

    public Anio(Integer idAnio) {
        this.idAnio = idAnio;
    }

    public Anio(Integer idAnio, int anio) {
        this.idAnio = idAnio;
        this.anio = anio;
    }

    public Integer getIdAnio() {
        return idAnio;
    }

    public void setIdAnio(Integer idAnio) {
        this.idAnio = idAnio;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public List<Prestacion> getPrestacionList() {
        return prestacionList;
    }

    public void setPrestacionList(List<Prestacion> prestacionList) {
        this.prestacionList = prestacionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAnio != null ? idAnio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Anio)) {
            return false;
        }
        Anio other = (Anio) object;
        if ((this.idAnio == null && other.idAnio != null) || (this.idAnio != null && !this.idAnio.equals(other.idAnio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.gob.cultura.rrhh.entidades.Anio[ idAnio=" + idAnio + " ]";
    }
    
}
