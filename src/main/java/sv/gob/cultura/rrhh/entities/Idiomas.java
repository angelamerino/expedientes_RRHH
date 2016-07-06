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
import javax.persistence.ManyToMany;
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
@Table(name = "idiomas")
@NamedQueries({
    @NamedQuery(name = "Idiomas.findAll", query = "SELECT i FROM Idiomas i"),
    @NamedQuery(name = "Idiomas.findByIdIdioma", query = "SELECT i FROM Idiomas i WHERE i.idIdioma = :idIdioma"),
    @NamedQuery(name = "Idiomas.findByNombreIdioma", query = "SELECT i FROM Idiomas i WHERE i.nombreIdioma = :nombreIdioma")})
public class Idiomas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_idioma")
    private Integer idIdioma;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre_idioma")
    private String nombreIdioma;
    @ManyToMany(mappedBy = "idiomasList")
    private List<Empleados> empleadosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idiomas")
    private List<IdiomasCaracteristicas> idiomasCaracteristicasList;

    public Idiomas() {
    }

    public Idiomas(Integer idIdioma) {
        this.idIdioma = idIdioma;
    }

    public Idiomas(Integer idIdioma, String nombreIdioma) {
        this.idIdioma = idIdioma;
        this.nombreIdioma = nombreIdioma;
    }
    
    public Idiomas(String nombreIdioma){
        this.nombreIdioma = nombreIdioma;
    }

//    public Idiomas(String nuevo_item) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

    public Integer getIdIdioma() {
        return idIdioma;
    }

    public void setIdIdioma(Integer idIdioma) {
        this.idIdioma = idIdioma;
    }

    public String getNombreIdioma() {
        return nombreIdioma;
    }

    public void setNombreIdioma(String nombreIdioma) {
        this.nombreIdioma = nombreIdioma;
    }

    public List<Empleados> getEmpleadosList() {
        return empleadosList;
    }

    public void setEmpleadosList(List<Empleados> empleadosList) {
        this.empleadosList = empleadosList;
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
        hash += (idIdioma != null ? idIdioma.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Idiomas)) {
            return false;
        }
        Idiomas other = (Idiomas) object;
        if ((this.idIdioma == null && other.idIdioma != null) || (this.idIdioma != null && !this.idIdioma.equals(other.idIdioma))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.gob.cultura.rrhh.entidades.Idiomas[ idIdioma=" + idIdioma + " ]";
    }
    
}
