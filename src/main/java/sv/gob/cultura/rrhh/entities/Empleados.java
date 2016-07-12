/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.cultura.rrhh.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Angela
 */
@Entity
@Table(name = "empleados")
@NamedQueries({
    @NamedQuery(name = "Empleados.findAll", query = "SELECT e FROM Empleados e"),
    @NamedQuery(name = "Empleados.findByNrEmpleado", query = "SELECT e FROM Empleados e WHERE e.nrEmpleado = :nrEmpleado"),
    @NamedQuery(name = "Empleados.findByNombreEmpleado", query = "SELECT e FROM Empleados e WHERE e.nombreEmpleado = :nombreEmpleado"),
    @NamedQuery(name = "Empleados.findByUrlFotoEmp", query = "SELECT e FROM Empleados e WHERE e.urlFotoEmp = :urlFotoEmp"),
    @NamedQuery(name = "Empleados.findByNombreIsss", query = "SELECT e FROM Empleados e WHERE e.nombreIsss = :nombreIsss"),
    @NamedQuery(name = "Empleados.findByNombreNit", query = "SELECT e FROM Empleados e WHERE e.nombreNit = :nombreNit"),
    @NamedQuery(name = "Empleados.findByCorreoPersonal", query = "SELECT e FROM Empleados e WHERE e.correoPersonal = :correoPersonal"),
    @NamedQuery(name = "Empleados.findByFechaNac", query = "SELECT e FROM Empleados e WHERE e.fechaNac = :fechaNac"),
    @NamedQuery(name = "Empleados.findByEdadEmp", query = "SELECT e FROM Empleados e WHERE e.edadEmp = :edadEmp"),
    @NamedQuery(name = "Empleados.findByNaturalizado", query = "SELECT e FROM Empleados e WHERE e.naturalizado = :naturalizado"),
    @NamedQuery(name = "Empleados.findByDireccion", query = "SELECT e FROM Empleados e WHERE e.direccion = :direccion"),
    @NamedQuery(name = "Empleados.findByTelefonoFijo", query = "SELECT e FROM Empleados e WHERE e.telefonoFijo = :telefonoFijo"),
    @NamedQuery(name = "Empleados.findByTelefonoMovil", query = "SELECT e FROM Empleados e WHERE e.telefonoMovil = :telefonoMovil"),
    @NamedQuery(name = "Empleados.findByNumDui", query = "SELECT e FROM Empleados e WHERE e.numDui = :numDui"),
    @NamedQuery(name = "Empleados.findByNumIsss", query = "SELECT e FROM Empleados e WHERE e.numIsss = :numIsss"),
    @NamedQuery(name = "Empleados.findByNumNit", query = "SELECT e FROM Empleados e WHERE e.numNit = :numNit"),
    @NamedQuery(name = "Empleados.findByNumNup", query = "SELECT e FROM Empleados e WHERE e.numNup = :numNup"),
    @NamedQuery(name = "Empleados.findByNumNip", query = "SELECT e FROM Empleados e WHERE e.numNip = :numNip"),
    @NamedQuery(name = "Empleados.findByNumCuenta", query = "SELECT e FROM Empleados e WHERE e.numCuenta = :numCuenta"),
    @NamedQuery(name = "Empleados.findByFechaIngresoPublico", query = "SELECT e FROM Empleados e WHERE e.fechaIngresoPublico = :fechaIngresoPublico"),
    @NamedQuery(name = "Empleados.findByComentarios", query = "SELECT e FROM Empleados e WHERE e.comentarios = :comentarios"),
    @NamedQuery(name = "Empleados.findByJefe", query = "SELECT e FROM Empleados e WHERE e.jefe = :jefe"),
    @NamedQuery(name = "Empleados.findByCargoNominal", query = "SELECT e FROM Empleados e WHERE e.cargoNominal = :cargoNominal"),
    @NamedQuery(name = "Empleados.findByCargoFuncional", query = "SELECT e FROM Empleados e WHERE e.cargoFuncional = :cargoFuncional"),
    @NamedQuery(name = "Empleados.findBySalarioEmp", query = "SELECT e FROM Empleados e WHERE e.salarioEmp = :salarioEmp"),
    @NamedQuery(name = "Empleados.findByCorreoInstitucional", query = "SELECT e FROM Empleados e WHERE e.correoInstitucional = :correoInstitucional"),
    @NamedQuery(name = "Empleados.findByFechaIngresoInsti", query = "SELECT e FROM Empleados e WHERE e.fechaIngresoInsti = :fechaIngresoInsti"),
    @NamedQuery(name = "Empleados.findByDocDescPuesto", query = "SELECT e FROM Empleados e WHERE e.docDescPuesto = :docDescPuesto"),
    @NamedQuery(name = "Empleados.findByUserCreaEmp", query = "SELECT e FROM Empleados e WHERE e.userCreaEmp = :userCreaEmp"),
    @NamedQuery(name = "Empleados.findByFechaCreaEmp", query = "SELECT e FROM Empleados e WHERE e.fechaCreaEmp = :fechaCreaEmp"),
    @NamedQuery(name = "Empleados.findByUserModEmp", query = "SELECT e FROM Empleados e WHERE e.userModEmp = :userModEmp"),
    @NamedQuery(name = "Empleados.findByFechaModEmp", query = "SELECT e FROM Empleados e WHERE e.fechaModEmp = :fechaModEmp"),
    @NamedQuery(name = "Empleados.findByGenero", query = "SELECT e FROM Empleados e WHERE e.genero = :genero"),
    @NamedQuery(name = "Empleados.findByCurriculum", query = "SELECT e FROM Empleados e WHERE e.curriculum = :curriculum"),
    @NamedQuery(name = "Empleados.findByIdEmpleado", query = "SELECT e FROM Empleados e WHERE e.idEmpleado = :idEmpleado"),
    @NamedQuery(name = "Empleados.findByMunicipioNac", query = "SELECT e FROM Empleados e WHERE e.municipioNac = :municipioNac"),
    @NamedQuery(name = "Empleados.findByMunicipioRes", query = "SELECT e FROM Empleados e WHERE e.municipioRes = :municipioRes"),
    @NamedQuery(name = "Empleados.findByDeptoNac", query = "SELECT e FROM Empleados e WHERE e.deptoNac = :deptoNac"),
    @NamedQuery(name = "Empleados.findByDeptoResidencia", query = "SELECT e FROM Empleados e WHERE e.deptoResidencia = :deptoResidencia"),
    @NamedQuery(name = "Empleados.findByDependencia", query = "SELECT e FROM Empleados e WHERE e.idDependenciaN.idDependencia = :dependencia"),
    @NamedQuery(name = "Empleados.findByDeptoResidencia", query = "SELECT e FROM Empleados e WHERE e.deptoResidencia = :deptoResidencia")
})
public class Empleados implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_empleado")
    private Integer idEmpleado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "nr_empleado")
    private String nrEmpleado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1024)
    @Column(name = "nombre_empleado")
    private String nombreEmpleado;
    @Size(max = 1024)
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
    @Column(name = "edad_emp")
    private Integer edadEmp;
    @Size(max = 2)
    @Column(name = "naturalizado")
    private String naturalizado;
    @Size(max = 1024)
    @Column(name = "direccion")
    private String direccion;
    @Size(max = 9)
    @Column(name = "telefono_fijo")
    private String telefonoFijo;
    @Size(max = 9)
    @Column(name = "telefono_movil")
    private String telefonoMovil;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 14)
    @Column(name = "num_dui")
    private String numDui;
    @Size(max = 14)
    @Column(name = "num_isss")
    private String numIsss;
    @Size(max = 21)
    @Column(name = "num_nit")
    private String numNit;
    @Size(max = 17)
    @Column(name = "num_nup")
    private String numNup;
    @Size(max = 20)
    @Column(name = "num_nip")
    private String numNip;
    @Size(max = 50)
    @Column(name = "num_cuenta")
    private String numCuenta;
    @Column(name = "fecha_ingreso_publico")
    @Temporal(TemporalType.DATE)
    private Date fechaIngresoPublico;
    @Size(max = 1024)
    @Column(name = "comentarios")
    private String comentarios;
    @Size(max = 2)
    @Column(name = "jefe")
    private String jefe;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "cargo_nominal")
    private String cargoNominal;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "cargo_funcional")
    private String cargoFuncional;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "salario_emp")
    private Double salarioEmp;
    @Column(name = "id_historial_salarial")
    private Integer idHistorialSalarial;
    @Column(name = "fecha_ultimo_aumento")
    @Temporal(TemporalType.DATE)
    private Date fechaUltimoAumento;
    @Size(max = 100)
    @Column(name = "correo_institucional")
    private String correoInstitucional;
    @Column(name = "fecha_ingreso_insti")
    @Temporal(TemporalType.DATE)
    private Date fechaIngresoInsti;
    @Size(max = 1024)
    @Column(name = "doc_desc_puesto")
    private String docDescPuesto;
    @Column(name = "user_crea_emp")
    private Integer userCreaEmp;
    @Column(name = "fecha_crea_emp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreaEmp;
    @Column(name = "user_mod_emp")
    private Integer userModEmp;
    @Column(name = "fecha_mod_emp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModEmp;
    @Size(max = 10)
    @Column(name = "genero")
    private String genero;
    @Size(max = 1024)
    @Column(name = "curriculum")
    private String curriculum;
    @Column(name = "municipio_nac")
    private Integer municipioNac;
    @Column(name = "municipio_res")
    private Integer municipioRes;
    @Column(name = "depto_nac")
    private Integer deptoNac;
    @Column(name = "depto_residencia")
    private Integer deptoResidencia;
    @Column(name = "salario_historial_aumento")
    private Double salarioHistorialAumento;
    @JoinTable(name = "emp_prestacion", joinColumns = {
        @JoinColumn(name = "id_empleado", referencedColumnName = "id_empleado")}, inverseJoinColumns = {
        @JoinColumn(name = "id_prestacion", referencedColumnName = "id_prestacion")})
    @ManyToMany
    private List<Prestacion> prestacionList;
    @JoinTable(name = "emp_estudios", joinColumns = {
        @JoinColumn(name = "id_empleado", referencedColumnName = "id_empleado")}, inverseJoinColumns = {
        @JoinColumn(name = "id_estudio", referencedColumnName = "id_estudio")})
    @ManyToMany
    private List<EstudiosEmp> estudiosEmpList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEmpleado")
    private List<HistorialSalarial> historialSalarialList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEmpleado")
    private List<FamiliaDependientesEmp> familiaDependientesEmpList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEmpleado")
    private List<ImgDoc> imgDocList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEmpleado")
    private List<MovimientosEmp> movimientosEmpList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEmpleado")
    private List<ExperienciaLaboral> experienciaLaboralList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEmpleado")
    private List<ContactoEmergenciaEmp> contactoEmergenciaEmpList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEmpleado")
    private List<AsignarAsistenciaCap> asignarAsistenciaCapList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEmpleado")
    private List<DescuentosEmp> descuentosEmpList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEmpleado")
    private List<Sanciones> sancionesList;
    @JoinColumn(name = "id_tipo_sangre", referencedColumnName = "id_tipo_sangre")
    @ManyToOne(optional = false)
    private TipoSangre idTipoSangre;
    @JoinColumn(name = "id_pais", referencedColumnName = "id_pais")
    @ManyToOne(optional = false)
    private Paises idPais;
    @JoinColumn(name = "id_municipio_n", referencedColumnName = "id_municipio")
    @ManyToOne(optional = false)
    private Municipios idMunicipioN;
    @JoinColumn(name = "id_municipio_f", referencedColumnName = "id_municipio")
    @ManyToOne(optional = false)
    private Municipios idMunicipioF;
    @JoinColumn(name = "id_banco", referencedColumnName = "id_banco")
    @ManyToOne(optional = false)
    private InstBancaria idBanco;
    @JoinColumn(name = "id_estado", referencedColumnName = "id_estado")
    @ManyToOne(optional = false)
    private Estados idEstado;
    @JoinColumn(name = "id_estado_civil", referencedColumnName = "id_estado_civil")
    @ManyToOne(optional = false)
    private EstadoCivil idEstadoCivil;
    @OneToMany(mappedBy = "idEmpleadoJefe")
    private List<Empleados> empleadosList;
    @JoinColumn(name = "id_empleado_jefe", referencedColumnName = "id_empleado")
    @ManyToOne
    private Empleados idEmpleadoJefe;
    @JoinColumn(name = "id_dependencia_f", referencedColumnName = "id_dependencia")
    @ManyToOne(optional = false)
    private Dependencias idDependenciaF;
    @JoinColumn(name = "id_dependencia_n", referencedColumnName = "id_dependencia")
    @ManyToOne(optional = false)
    private Dependencias idDependenciaN;
    @JoinColumn(name = "id_admin_pension", referencedColumnName = "id_admin_pension")
    @ManyToOne(optional = false)
    private AdministradoraPensiones idAdminPension;
    @OneToMany(mappedBy = "idEmpleado")
    private List<Reconocimientos> reconocimientosList;
    @JoinColumn(name = "id_genero", referencedColumnName = "id_genero")
    @ManyToOne(optional = false)
    private Genero idGenero;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empleados")
    private List<IdiomasCaracteristicas> idiomasCaracteristicasList;

    public Empleados() {
    }

    public Empleados(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public Empleados(Integer idEmpleado, String nrEmpleado, String nombreEmpleado, String numDui, String cargoNominal, String cargoFuncional) {
        this.idEmpleado = idEmpleado;
        this.nrEmpleado = nrEmpleado;
        this.nombreEmpleado = nombreEmpleado;
        this.numDui = numDui;
        this.cargoNominal = cargoNominal;
        this.cargoFuncional = cargoFuncional;
    }

    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
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

    public Integer getEdadEmp() {
        return edadEmp;
    }

    public void setEdadEmp(Integer edadEmp) {
        this.edadEmp = edadEmp;
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

    public String getTelefonoFijo() {
        return telefonoFijo;
    }

    public void setTelefonoFijo(String telefonoFijo) {
        this.telefonoFijo = telefonoFijo;
    }

    public String getTelefonoMovil() {
        return telefonoMovil;
    }

    public void setTelefonoMovil(String telefonoMovil) {
        this.telefonoMovil = telefonoMovil;
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

    public String getNumCuenta() {
        return numCuenta;
    }

    public void setNumCuenta(String numCuenta) {
        this.numCuenta = numCuenta;
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

    public Integer getUserCreaEmp() {
        return userCreaEmp;
    }

    public void setUserCreaEmp(Integer userCreaEmp) {
        this.userCreaEmp = userCreaEmp;
    }

    public Date getFechaCreaEmp() {
        return fechaCreaEmp;
    }

    public void setFechaCreaEmp(Date fechaCreaEmp) {
        this.fechaCreaEmp = fechaCreaEmp;
    }

    public Integer getUserModEmp() {
        return userModEmp;
    }

    public void setUserModEmp(Integer userModEmp) {
        this.userModEmp = userModEmp;
    }

    public Date getFechaModEmp() {
        return fechaModEmp;
    }

    public void setFechaModEmp(Date fechaModEmp) {
        this.fechaModEmp = fechaModEmp;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getCurriculum() {
        return curriculum;
    }

    public void setCurriculum(String curriculum) {
        this.curriculum = curriculum;
    }

    public Integer getMunicipioNac() {
        return municipioNac;
    }

    public void setMunicipioNac(Integer municipioNac) {
        this.municipioNac = municipioNac;
    }

    public Integer getMunicipioRes() {
        return municipioRes;
    }

    public void setMunicipioRes(Integer municipioRes) {
        this.municipioRes = municipioRes;
    }

    public Integer getDeptoNac() {
        return deptoNac;
    }

    public void setDeptoNac(Integer deptoNac) {
        this.deptoNac = deptoNac;
    }

    public Integer getDeptoResidencia() {
        return deptoResidencia;
    }

    public void setDeptoResidencia(Integer deptoResidencia) {
        this.deptoResidencia = deptoResidencia;
    }

    public List<Prestacion> getPrestacionList() {
        return prestacionList;
    }

    public void setPrestacionList(List<Prestacion> prestacionList) {
        this.prestacionList = prestacionList;
    }

    public List<EstudiosEmp> getEstudiosEmpList() {
        return estudiosEmpList;
    }

    public void setEstudiosEmpList(List<EstudiosEmp> estudiosEmpList) {
        this.estudiosEmpList = estudiosEmpList;
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

    public Municipios getIdMunicipioN() {
        return idMunicipioN;
    }

    public void setIdMunicipioN(Municipios idMunicipioN) {
        this.idMunicipioN = idMunicipioN;
    }

    public Municipios getIdMunicipioF() {
        return idMunicipioF;
    }

    public void setIdMunicipioF(Municipios idMunicipioF) {
        this.idMunicipioF = idMunicipioF;
    }

    public InstBancaria getIdBanco() {
        return idBanco;
    }

    public void setIdBanco(InstBancaria idBanco) {
        this.idBanco = idBanco;
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

    public Integer getIdHistorialSalarial() {
        return idHistorialSalarial;
    }

    public void setIdHistorialSalarial(Integer idHistorialSalarial) {
        this.idHistorialSalarial = idHistorialSalarial;
    }

    public double getSalarioHistorialAumento() {
        return salarioHistorialAumento;
    }

    public void setSalarioHistorialAumento(double salarioHistorialAumento) {
        this.salarioHistorialAumento = salarioHistorialAumento;
    }

    public Date getFechaUltimoAumento() {
        return fechaUltimoAumento;
    }

    public void setFechaUltimoAumento(Date fechaUltimoAumento) {
        this.fechaUltimoAumento = fechaUltimoAumento;
    }

    public List<Empleados> getEmpleadosList() {
        return empleadosList;
    }

    public void setEmpleadosList(List<Empleados> empleadosList) {
        this.empleadosList = empleadosList;
    }

    public Empleados getIdEmpleadoJefe() {
        return idEmpleadoJefe;
    }

    public void setIdEmpleadoJefe(Empleados idEmpleadoJefe) {
        this.idEmpleadoJefe = idEmpleadoJefe;
    }

    public Dependencias getIdDependenciaF() {
        return idDependenciaF;
    }

    public void setIdDependenciaF(Dependencias idDependenciaF) {
        this.idDependenciaF = idDependenciaF;
    }

    public Dependencias getIdDependenciaN() {
        return idDependenciaN;
    }

    public void setIdDependenciaN(Dependencias idDependenciaN) {
        this.idDependenciaN = idDependenciaN;
    }

    public AdministradoraPensiones getIdAdminPension() {
        return idAdminPension;
    }

    public void setIdAdminPension(AdministradoraPensiones idAdminPension) {
        this.idAdminPension = idAdminPension;
    }

    public List<Reconocimientos> getReconocimientosList() {
        return reconocimientosList;
    }

    public void setReconocimientosList(List<Reconocimientos> reconocimientosList) {
        this.reconocimientosList = reconocimientosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEmpleado != null ? idEmpleado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empleados)) {
            return false;
        }
        Empleados other = (Empleados) object;
        if ((this.idEmpleado == null && other.idEmpleado != null) || (this.idEmpleado != null && !this.idEmpleado.equals(other.idEmpleado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.gob.cultura.rrhh.entidades.Empleados[ idEmpleado=" + idEmpleado + " ]";
    }

    public void setSalarioHistorialAumento(Double salarioHistorialAumento) {
        this.salarioHistorialAumento = salarioHistorialAumento;
    }

    public Genero getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(Genero idGenero) {
        this.idGenero = idGenero;
    }

    @XmlTransient
    public List<IdiomasCaracteristicas> getIdiomasCaracteristicasList() {
        return idiomasCaracteristicasList;
    }

    public void setIdiomasCaracteristicasList(List<IdiomasCaracteristicas> idiomasCaracteristicasList) {
        this.idiomasCaracteristicasList = idiomasCaracteristicasList;
    }

}
