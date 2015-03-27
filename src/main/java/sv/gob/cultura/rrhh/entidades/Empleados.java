/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.cultura.rrhh.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Angela
 */
@Entity
@Table(name = "empleados")
@NamedQueries({
    @NamedQuery(name = "Empleados.findAll", query = "SELECT e FROM Empleados e")})
public class Empleados implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "nr_empleado")
    private String nrEmpleado;
    @Size(max = 1024)
    @Column(name = "nombre_empleado")
    private String nombreEmpleado;
    @Size(max = 50)
    @Column(name = "url_foto_emp")
    private String urlFotoEmp;
    @Size(max = 1024)
    @Column(name = "nombre_isss")
    private String nombreIsss;
    @Size(max = 1024)
    @Column(name = "nombre_nit")
    private String nombreNit;
    @Size(max = 100)
    @Column(name = "correo_personal")
    private String correoPersonal;
    @Column(name = "fecha_nac")
    @Temporal(TemporalType.DATE)
    private Date fechaNac;
    @Size(max = 2)
    @Column(name = "naturalizado")
    private String naturalizado;
    @Size(max = 1024)
    @Column(name = "direccion")
    private String direccion;
    @Size(max = 9)
    @Column(name = "telefono_casa")
    private String telefonoCasa;
    @Size(max = 9)
    @Column(name = "telefono_celular")
    private String telefonoCelular;
    @Size(max = 13)
    @Column(name = "num_dui")
    private String numDui;
    @Size(max = 13)
    @Column(name = "num_isss")
    private String numIsss;
    @Size(max = 20)
    @Column(name = "num_nit")
    private String numNit;
    @Size(max = 16)
    @Column(name = "num_nup")
    private String numNup;
    @Size(max = 20)
    @Column(name = "num_nip")
    private String numNip;
    @Size(max = 100)
    @Column(name = "doc_curriculum")
    private String docCurriculum;
    @Column(name = "fecha_ingreso_publico")
    @Temporal(TemporalType.DATE)
    private Date fechaIngresoPublico;
    @Size(max = 1024)
    @Column(name = "comentarios")
    private String comentarios;
    @Size(max = 2)
    @Column(name = "jefe")
    private String jefe;
    @Size(max = 200)
    @Column(name = "cargo_nominal")
    private String cargoNominal;
    @Size(max = 200)
    @Column(name = "cargo_funcional")
    private String cargoFuncional;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "salario_emp")
    private Double salarioEmp;
    @Size(max = 100)
    @Column(name = "correo_institucional")
    private String correoInstitucional;
    @Column(name = "fecha_ingreso_insti")
    @Temporal(TemporalType.DATE)
    private Date fechaIngresoInsti;
    @Size(max = 100)
    @Column(name = "doc_desc_puesto")
    private String docDescPuesto;
    @JoinTable(name = "emp_prestacion", joinColumns = {
        @JoinColumn(name = "nr_empleado", referencedColumnName = "nr_empleado")}, inverseJoinColumns = {
        @JoinColumn(name = "id_prestacion", referencedColumnName = "id_prestacion")})
    @ManyToMany
    private List<Prestacion> prestacionList;
    @JoinTable(name = "emp_idiomas", joinColumns = {
        @JoinColumn(name = "nr_empleado", referencedColumnName = "nr_empleado")}, inverseJoinColumns = {
        @JoinColumn(name = "id_idioma", referencedColumnName = "id_idioma")})
    @ManyToMany
    private List<Idiomas> idiomasList;
    @JoinTable(name = "emp_estudios", joinColumns = {
        @JoinColumn(name = "nr_empleado", referencedColumnName = "nr_empleado")}, inverseJoinColumns = {
        @JoinColumn(name = "id_estudio", referencedColumnName = "id_estudio")})
    @ManyToMany
    private List<EstudiosEmp> estudiosEmpList;
    @JoinTable(name = "emp_prof_oficios", joinColumns = {
        @JoinColumn(name = "nr_empleado", referencedColumnName = "nr_empleado")}, inverseJoinColumns = {
        @JoinColumn(name = "id_prof_oficio", referencedColumnName = "id_prof_oficio")})
    @ManyToMany
    private List<ProfOficios> profOficiosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nrEmpleado")
    private List<HistorialSalarial> historialSalarialList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nrEmpleado")
    private List<FamiliaDependientesEmp> familiaDependientesEmpList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nrEmpleado")
    private List<ImgDoc> imgDocList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nrEmpleado")
    private List<MovimientosEmp> movimientosEmpList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nrEmpleado")
    private List<ExperienciaLaboral> experienciaLaboralList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nrEmpleado")
    private List<ContactoEmergenciaEmp> contactoEmergenciaEmpList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nrEmpleado")
    private List<AsignarAsistenciaCap> asignarAsistenciaCapList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nrEmpleado")
    private List<DescuentosEmp> descuentosEmpList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nrEmpleado")
    private List<Sanciones> sancionesList;
    @JoinColumn(name = "id_tipo_sangre", referencedColumnName = "id_tipo_sangre")
    @ManyToOne(optional = false)
    private TipoSangre idTipoSangre;
    @JoinColumn(name = "id_pais", referencedColumnName = "id_pais")
    @ManyToOne(optional = false)
    private Paises idPais;
    @JoinColumn(name = "id_municipio", referencedColumnName = "id_municipio")
    @ManyToOne(optional = false)
    private Municipios idMunicipio;
    @JoinColumn(name = "id_genero", referencedColumnName = "id_genero")
    @ManyToOne(optional = false)
    private Genero idGenero;
    @JoinColumn(name = "id_estado", referencedColumnName = "id_estado")
    @ManyToOne(optional = false)
    private Estados idEstado;
    @JoinColumn(name = "id_estado_civil", referencedColumnName = "id_estado_civil")
    @ManyToOne(optional = false)
    private EstadoCivil idEstadoCivil;
    @OneToMany(mappedBy = "empNrEmpleado")
    private List<Empleados> empleadosList;
    @JoinColumn(name = "emp_nr_empleado", referencedColumnName = "nr_empleado")
    @ManyToOne
    private Empleados empNrEmpleado;
    @JoinColumn(name = "id_dependencia", referencedColumnName = "id_dependencia")
    @ManyToOne(optional = false)
    private Dependencias idDependencia;
    @JoinColumn(name = "id_admin_pension", referencedColumnName = "id_admin_pension")
    @ManyToOne(optional = false)
    private AdministradoraPensiones idAdminPension;

    public Empleados() {
    }

    public Empleados(String nrEmpleado) {
        this.nrEmpleado = nrEmpleado;
    }

    public String getNrEmpleado() {
        return nrEmpleado;
    }

    public void setNrEmpleado(String nrEmpleado) {
        this.nrEmpleado = nrEmpleado;
    }

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }

    public String getUrlFotoEmp() {
        return urlFotoEmp;
    }

    public void setUrlFotoEmp(String urlFotoEmp) {
        this.urlFotoEmp = urlFotoEmp;
    }

    public String getNombreIsss() {
        return nombreIsss;
    }

    public void setNombreIsss(String nombreIsss) {
        this.nombreIsss = nombreIsss;
    }

    public String getNombreNit() {
        return nombreNit;
    }

    public void setNombreNit(String nombreNit) {
        this.nombreNit = nombreNit;
    }

    public String getCorreoPersonal() {
        return correoPersonal;
    }

    public void setCorreoPersonal(String correoPersonal) {
        this.correoPersonal = correoPersonal;
    }

    public Date getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getNaturalizado() {
        return naturalizado;
    }

    public void setNaturalizado(String naturalizado) {
        this.naturalizado = naturalizado;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefonoCasa() {
        return telefonoCasa;
    }

    public void setTelefonoCasa(String telefonoCasa) {
        this.telefonoCasa = telefonoCasa;
    }

    public String getTelefonoCelular() {
        return telefonoCelular;
    }

    public void setTelefonoCelular(String telefonoCelular) {
        this.telefonoCelular = telefonoCelular;
    }

    public String getNumDui() {
        return numDui;
    }

    public void setNumDui(String numDui) {
        this.numDui = numDui;
    }

    public String getNumIsss() {
        return numIsss;
    }

    public void setNumIsss(String numIsss) {
        this.numIsss = numIsss;
    }

    public String getNumNit() {
        return numNit;
    }

    public void setNumNit(String numNit) {
        this.numNit = numNit;
    }

    public String getNumNup() {
        return numNup;
    }

    public void setNumNup(String numNup) {
        this.numNup = numNup;
    }

    public String getNumNip() {
        return numNip;
    }

    public void setNumNip(String numNip) {
        this.numNip = numNip;
    }

    public String getDocCurriculum() {
        return docCurriculum;
    }

    public void setDocCurriculum(String docCurriculum) {
        this.docCurriculum = docCurriculum;
    }

    public Date getFechaIngresoPublico() {
        return fechaIngresoPublico;
    }

    public void setFechaIngresoPublico(Date fechaIngresoPublico) {
        this.fechaIngresoPublico = fechaIngresoPublico;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public String getJefe() {
        return jefe;
    }

    public void setJefe(String jefe) {
        this.jefe = jefe;
    }

    public String getCargoNominal() {
        return cargoNominal;
    }

    public void setCargoNominal(String cargoNominal) {
        this.cargoNominal = cargoNominal;
    }

    public String getCargoFuncional() {
        return cargoFuncional;
    }

    public void setCargoFuncional(String cargoFuncional) {
        this.cargoFuncional = cargoFuncional;
    }

    public Double getSalarioEmp() {
        return salarioEmp;
    }

    public void setSalarioEmp(Double salarioEmp) {
        this.salarioEmp = salarioEmp;
    }

    public String getCorreoInstitucional() {
        return correoInstitucional;
    }

    public void setCorreoInstitucional(String correoInstitucional) {
        this.correoInstitucional = correoInstitucional;
    }

    public Date getFechaIngresoInsti() {
        return fechaIngresoInsti;
    }

    public void setFechaIngresoInsti(Date fechaIngresoInsti) {
        this.fechaIngresoInsti = fechaIngresoInsti;
    }

    public String getDocDescPuesto() {
        return docDescPuesto;
    }

    public void setDocDescPuesto(String docDescPuesto) {
        this.docDescPuesto = docDescPuesto;
    }

    public List<Prestacion> getPrestacionList() {
        return prestacionList;
    }

    public void setPrestacionList(List<Prestacion> prestacionList) {
        this.prestacionList = prestacionList;
    }

    public List<Idiomas> getIdiomasList() {
        return idiomasList;
    }

    public void setIdiomasList(List<Idiomas> idiomasList) {
        this.idiomasList = idiomasList;
    }

    public List<EstudiosEmp> getEstudiosEmpList() {
        return estudiosEmpList;
    }

    public void setEstudiosEmpList(List<EstudiosEmp> estudiosEmpList) {
        this.estudiosEmpList = estudiosEmpList;
    }

    public List<ProfOficios> getProfOficiosList() {
        return profOficiosList;
    }

    public void setProfOficiosList(List<ProfOficios> profOficiosList) {
        this.profOficiosList = profOficiosList;
    }

    public List<HistorialSalarial> getHistorialSalarialList() {
        return historialSalarialList;
    }

    public void setHistorialSalarialList(List<HistorialSalarial> historialSalarialList) {
        this.historialSalarialList = historialSalarialList;
    }

    public List<FamiliaDependientesEmp> getFamiliaDependientesEmpList() {
        return familiaDependientesEmpList;
    }

    public void setFamiliaDependientesEmpList(List<FamiliaDependientesEmp> familiaDependientesEmpList) {
        this.familiaDependientesEmpList = familiaDependientesEmpList;
    }

    public List<ImgDoc> getImgDocList() {
        return imgDocList;
    }

    public void setImgDocList(List<ImgDoc> imgDocList) {
        this.imgDocList = imgDocList;
    }

    public List<MovimientosEmp> getMovimientosEmpList() {
        return movimientosEmpList;
    }

    public void setMovimientosEmpList(List<MovimientosEmp> movimientosEmpList) {
        this.movimientosEmpList = movimientosEmpList;
    }

    public List<ExperienciaLaboral> getExperienciaLaboralList() {
        return experienciaLaboralList;
    }

    public void setExperienciaLaboralList(List<ExperienciaLaboral> experienciaLaboralList) {
        this.experienciaLaboralList = experienciaLaboralList;
    }

    public List<ContactoEmergenciaEmp> getContactoEmergenciaEmpList() {
        return contactoEmergenciaEmpList;
    }

    public void setContactoEmergenciaEmpList(List<ContactoEmergenciaEmp> contactoEmergenciaEmpList) {
        this.contactoEmergenciaEmpList = contactoEmergenciaEmpList;
    }

    public List<AsignarAsistenciaCap> getAsignarAsistenciaCapList() {
        return asignarAsistenciaCapList;
    }

    public void setAsignarAsistenciaCapList(List<AsignarAsistenciaCap> asignarAsistenciaCapList) {
        this.asignarAsistenciaCapList = asignarAsistenciaCapList;
    }

    public List<DescuentosEmp> getDescuentosEmpList() {
        return descuentosEmpList;
    }

    public void setDescuentosEmpList(List<DescuentosEmp> descuentosEmpList) {
        this.descuentosEmpList = descuentosEmpList;
    }

    public List<Sanciones> getSancionesList() {
        return sancionesList;
    }

    public void setSancionesList(List<Sanciones> sancionesList) {
        this.sancionesList = sancionesList;
    }

    public TipoSangre getIdTipoSangre() {
        return idTipoSangre;
    }

    public void setIdTipoSangre(TipoSangre idTipoSangre) {
        this.idTipoSangre = idTipoSangre;
    }

    public Paises getIdPais() {
        return idPais;
    }

    public void setIdPais(Paises idPais) {
        this.idPais = idPais;
    }

    public Municipios getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(Municipios idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    public Genero getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(Genero idGenero) {
        this.idGenero = idGenero;
    }

    public Estados getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Estados idEstado) {
        this.idEstado = idEstado;
    }

    public EstadoCivil getIdEstadoCivil() {
        return idEstadoCivil;
    }

    public void setIdEstadoCivil(EstadoCivil idEstadoCivil) {
        this.idEstadoCivil = idEstadoCivil;
    }

    public List<Empleados> getEmpleadosList() {
        return empleadosList;
    }

    public void setEmpleadosList(List<Empleados> empleadosList) {
        this.empleadosList = empleadosList;
    }

    public Empleados getEmpNrEmpleado() {
        return empNrEmpleado;
    }

    public void setEmpNrEmpleado(Empleados empNrEmpleado) {
        this.empNrEmpleado = empNrEmpleado;
    }

    public Dependencias getIdDependencia() {
        return idDependencia;
    }

    public void setIdDependencia(Dependencias idDependencia) {
        this.idDependencia = idDependencia;
    }

    public AdministradoraPensiones getIdAdminPension() {
        return idAdminPension;
    }

    public void setIdAdminPension(AdministradoraPensiones idAdminPension) {
        this.idAdminPension = idAdminPension;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nrEmpleado != null ? nrEmpleado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empleados)) {
            return false;
        }
        Empleados other = (Empleados) object;
        if ((this.nrEmpleado == null && other.nrEmpleado != null) || (this.nrEmpleado != null && !this.nrEmpleado.equals(other.nrEmpleado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.gob.cultura.rrhh.facades.Empleados[ nrEmpleado=" + nrEmpleado + " ]";
    }
    
}
