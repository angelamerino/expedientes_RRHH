/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.cultura.rrhh.manejadores;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import javax.ejb.EJB;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.application.ViewHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.context.RequestContext;
import sv.gob.cultura.rrhh.entidades.Dependencias;
import sv.gob.cultura.rrhh.entidades.DirNacional;
import sv.gob.cultura.rrhh.entidades.Empleados;
import sv.gob.cultura.rrhh.entidades.HistorialSalarial;
import sv.gob.cultura.rrhh.entidades.TipoMejoraSalarial;
import sv.gob.cultura.rrhh.facades.DependenciasFacade;
import sv.gob.cultura.rrhh.facades.DirNacionalFacade;
import sv.gob.cultura.rrhh.facades.EmpleadosFacade;
import sv.gob.cultura.rrhh.facades.HistorialSalarialFacade;
import sv.gob.cultura.rrhh.facades.TipoMejoraSalarialFacade;

/**
 *
 * @author Edwin
 */
@Named(value = "manejadorAumentoMejoraSalarial")
@ViewScoped
public class manejadorAumentoMejoraSalarial implements Serializable {

    private static final long serialVersionUID = 1L;
// ************** LLAMADA A LOS ENTERPRICE JAVA BEANS **************************
//******************************************************************************
    @EJB
    private HistorialSalarialFacade historialSalarialFacade;
    @EJB
    private TipoMejoraSalarialFacade tipoMejoraSalarialFacade;
    @EJB
    private EmpleadosFacade empleadosFacade;
    @EJB
    private DependenciasFacade dependenciasFacade;
    @EJB
    private DirNacionalFacade dirNacionalFacade;
//*********************** OBJETOS DE LOS ENTIDADES *****************************
//******************************************************************************
    HistorialSalarial historialSalarial = new HistorialSalarial();
    TipoMejoraSalarial tipoMejoraSalarial = new TipoMejoraSalarial();
    Empleados empleados = new Empleados();
//****** VARIABLES QUE CONTRENDRAN ID´S O STRING DE FORMULARIOS ****************
//******************************************************************************
    private int direccionNacional;                  //id Direccion Nacional Para filtrar dependencias
    private int dependecia;                         //id Dependencia para filtrar empleado
    private int empleadoSelecionado = 0;            //id Empleado seleccinado inicialmente en cero = ninguno
    private String nombreEmp;                       // nombre de empleado selecinado
    private String NR = "NR 0614-090179-134-0";                              // NR de empleado para hacer la busqueda
    private double porcentaje;                         //Porcentaje de Aumento en mejora salarial
    private double porcentajeEditar;                   //Porcentaje de Aumento en Mejora salarial Editar
    private double porcentajeAumento;                  //Porcentaje de Aumento en Aumento salarial
    private double porcentajeAumentoEditar;            //Porcentaje de Aunemto en Aumento salaria Editar
    private double salarioAnteriorEditar;           //Salario Anterior en Mejora salarial editar
    private double salarioNuevoEditar;              //Salario Nuevo en Mejora salarial Editar
    private double salarioActual;                   //Salario Actual en Mejora salarial
    private double salarioNuevo;                    //Nuevo salario en Mejora salarial

//********************** GET DE ENTERPRICE JAVA BEAN ***************************
//******************************************************************************
    public HistorialSalarialFacade getHistorialSalarialFacade() {
        return historialSalarialFacade;
    }

    public TipoMejoraSalarialFacade getTipoMejoraSalarialFacade() {
        return tipoMejoraSalarialFacade;
    }

    public EmpleadosFacade getEmpleadosFacade() {
        return empleadosFacade;
    }

    public DependenciasFacade getDependenciasFacade() {
        return dependenciasFacade;
    }

    public DirNacionalFacade getDirNacionalFacade() {
        return dirNacionalFacade;
    }

//******************* GET y SET DE OBJETOS DE ENTIDADES ************************
//******************************************************************************
    public HistorialSalarial getHistorialSalarial() {
        return historialSalarial;
    }

    public void setHistorialSalarial(HistorialSalarial historialSalarial) {
        this.historialSalarial = historialSalarial;
    }

    public TipoMejoraSalarial getTipoMejoraSalarial() {
        return tipoMejoraSalarial;
    }

    public void setTipoMejoraSalarial(TipoMejoraSalarial tipoMejoraSalarial) {
        this.tipoMejoraSalarial = tipoMejoraSalarial;
    }

    public Empleados getEmpleados() {
        return empleados;
    }

    public void setEmpleados(Empleados empleados) {
        this.empleados = empleados;
    }

    public int getDireccionNacional() {
        return direccionNacional;
    }

    public void setDireccionNacional(int direccionNacional) {
        this.direccionNacional = direccionNacional;
    }

    public int getDependecia() {
        return dependecia;
    }

    public void setDependecia(int dependecia) {
        this.dependecia = dependecia;
    }

    public int getEmpleadoSelecionado() {
        return empleadoSelecionado;
    }

    public void setEmpleadoSelecionado(int empleadoSelecionado) {
        //Al seleccinar empleado se obtiene el sueldo que tiene asignado en ese monento
        this.empleadoSelecionado = empleadoSelecionado;
        Empleados emp = getEmpleadosFacade().find(empleadoSelecionado);
        this.setSalarioActual(emp.getSalarioEmp());
        this.setNombreEmp(emp.getNombreEmpleado());
    }

    public double getSalarioActual() {
        return salarioActual;
    }

    public void setSalarioActual(double salarioActual) {
        this.salarioActual = salarioActual;
    }

    public double getSalarioNuevo() {
        return salarioNuevo;
    }

    public void setSalarioNuevo(double salarioNuevo) {
        this.salarioNuevo = salarioNuevo;
    }

    public double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(double porcentaje) {
        //Al ingresar el porcentaje se procede ha realizar el aumento de sueldo
        double por = porcentaje / 100.00;               // Porcentaje real
        double sal = this.getSalarioActual() * por;     // Aumento
        double nsal = sal + this.getSalarioActual();    // Nuevo salario
        double rsal = Math.rint(nsal * 100) / 100;      // Redondeo a 2 digitos
        this.setSalarioNuevo(rsal);                     // Setea Nuevo salario
        this.porcentaje = porcentaje;
    }

    public double getPorcentajeAumento() {
        return porcentajeAumento;
    }

    public void setPorcentajeAumento(double porcentajeAumento) {
        this.porcentajeAumento = porcentajeAumento;
    }

    public double getSalarioAnteriorEditar() {
        return salarioAnteriorEditar;
    }

    public void setSalarioAnteriorEditar(double salarioAnteriorEditar) {
        this.salarioAnteriorEditar = salarioAnteriorEditar;
    }

    public double getPorcentajeEditar() {
        return porcentajeEditar;
    }

    public void setPorcentajeEditar(double porcentajeEditar) {
        //Obtenido el porcentaje para editar se procede a realizar los cambios en el sueldo
        double por = porcentajeEditar / 100.00;                     // Porcentaje real 
        double sal = this.getSalarioAnteriorEditar() * por;         // Aumento
        double nsal = sal + this.getSalarioAnteriorEditar();        // Nuevo salario
        double rsal = Math.rint(nsal * 100) / 100;                  // Redondeo a 2 digitos
        this.setSalarioNuevo(rsal);                                 // Setea Nuevo Sueldo segun se Modifico

        this.setSalarioNuevoEditar(rsal);                           // Nuevo sueldo tambien se refleja en formulario
        RequestContext.getCurrentInstance().update("panelEditar");  // Actualiza el nuevo sueldo en el formulario

        this.porcentajeEditar = porcentajeEditar;
    }

    public double getSalarioNuevoEditar() {
        return salarioNuevoEditar;
    }

    public void setSalarioNuevoEditar(double salarioNuevoEditar) {
        this.salarioNuevoEditar = salarioNuevoEditar;
    }

    public double getPorcentajeAumentoEditar() {
        return porcentajeAumentoEditar;
    }

    public void setPorcentajeAumentoEditar(double porcentajeAumentoEditar) {
        this.porcentajeAumentoEditar = porcentajeAumentoEditar;
    }

    public String getNombreEmp() {
        return nombreEmp;
    }

    public void setNombreEmp(String nombreEmp) {
        this.nombreEmp = nombreEmp;
    }

    public String getNR() {
        return NR;
    }

    public void setNR(String NR) {
        this.NR = NR;
    }

// **************** LISTA DE ELEMENTOS EN TABLAS *******************************
//******************************************************************************
    public List<DirNacional> todosDirNacional() {
        return getDirNacionalFacade().findAll();
    }

    public List<Dependencias> dependenciasFiltradas() {
        return getDependenciasFacade().buscarDependencias(this.getDireccionNacional());
    }

    public List<Empleados> empleadoFiltrado() {
        return getEmpleadosFacade().buscarEmp(this.getDependecia());
    }

    public List<Empleados> todosEmpleados() {
        return getEmpleadosFacade().findAll();
    }

    public List<TipoMejoraSalarial> todosTiposMejora() {
        return getTipoMejoraSalarialFacade().findAll();
    }

    public List<HistorialSalarial> todosHistorialSalarialMejora() {
        return getHistorialSalarialFacade().buscarHistorialTipo(1);
    }

    public List<HistorialSalarial> todosHistorialSalarialAumento() {
        return getHistorialSalarialFacade().buscarHistorialTipo(2);
    }
//******************************* FUNCIONES ************************************
//******************************************************************************

    public String guardarMejoraSalarial() {
        if (this.getEmpleadoSelecionado() == 0) { //verifica si se seleccino un empleado
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Seleccione un Empleado", "Seleccione un Empleado"));
            return null;
        } else {

            //Guarda los datos del formulario Setea los valores que no se guardan directamente
            historialSalarial.setIdEmpleado(new Empleados(this.getEmpleadoSelecionado()));
            historialSalarial.setSalarioActualHsalarial(this.getSalarioActual());
            historialSalarial.setNuevoSalarioHsalarial(this.getSalarioNuevo());
            historialSalarial.setPorcentajeHsalarial(this.getPorcentaje());
            historialSalarial.setTipoHistorial(1); //TIPO 1 = MEJORA SALARIAL
            historialSalarial.setUserCreaHsal(1);
            historialSalarial.setFechaCreaHsal(new Date());

            getHistorialSalarialFacade().create(historialSalarial);

            Empleados emp = getEmpleadosFacade().find(this.getEmpleadoSelecionado());
            emp.setSalarioEmp(this.getSalarioNuevo());
            emp.setUserModEmp(1);
            emp.setFechaModEmp(new Date());
            getEmpleadosFacade().edit(emp);

            //VERIFICACION SI EXISTE OTRA MEJORA
            List<HistorialSalarial> histo = getHistorialSalarialFacade().buscarHistorialEmpleado(this.getEmpleadoSelecionado());
            if (histo == null) {
                historialSalarial.setVerificacionHistorial(1);  //UNICO RESULTADO
                getHistorialSalarialFacade().edit(historialSalarial);
            } else {
                //HAY MAS REGISTROS PARA EL EMPLEADO

                //VERIFICACION = 2 NO SE PODRAN ELIMINAR
                Iterator<HistorialSalarial> itearador = histo.iterator();
                while (itearador.hasNext()) {
                    HistorialSalarial historial = itearador.next();
                    historial.setVerificacionHistorial(2);
                    getHistorialSalarialFacade().edit(historial);
                }

                historialSalarial.setVerificacionHistorial(1); // VERIFICACION PARA ELIMINAR AL ULTIMO
                getHistorialSalarialFacade().edit(historialSalarial);
            }

            historialSalarial = new HistorialSalarial();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Ingresado", "Registro Ingresado");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return "gestion_mejora_salarial";
        }
    }

    public String editarMejoraSalarial() {

        //Se utiliza salario actual para editar y no alterar mejora de sueldo ya ingresado
        //Guarda los datos del formulario Setea los valores que no se guardan directamente
        //historialSalarial.setIdEmpleado(new Empleados(this.getEmpleadoSelecionado()));
        historialSalarial.setSalarioActualHsalarial(this.getSalarioAnteriorEditar());
        historialSalarial.setNuevoSalarioHsalarial(this.getSalarioNuevoEditar());
        historialSalarial.setPorcentajeHsalarial(this.getPorcentajeEditar());
        historialSalarial.setUserModHsal(1);
        historialSalarial.setFechaModHsal(new Date());

        getHistorialSalarialFacade().edit(historialSalarial);
        historialSalarial = new HistorialSalarial();

        Empleados emp = getEmpleadosFacade().find(this.getEmpleadoSelecionado());
        emp.setSalarioEmp(this.getSalarioNuevoEditar());
        emp.setUserModEmp(1);
        emp.setFechaModEmp(new Date());
        getEmpleadosFacade().edit(emp);

        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Modificado", "Registro Modificado");
        FacesContext.getCurrentInstance().addMessage(null, message);
        return "gestion_mejora_salarial";

    }

    public String guardarAumentoSalarial() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        //Rango de sueldo min y max
        double min = historialSalarial.getSalMin();
        System.out.println(min);
        double max = historialSalarial.getSalMax();
        System.out.println(max);

        //Guarda los datos del formulario Setea los valores que no se guardan directamente
        historialSalarial.setPorcentajeHsalarial(this.getPorcentajeAumento());
        historialSalarial.setTipoHistorial(2); //TIPO 1 = AUMENTO SALARIAL
        historialSalarial.setUserCreaHsal(1);
        historialSalarial.setFechaCreaHsal(new Date());

        //Validacion para que se pueda eliminar unicamente el ultimo
        List<HistorialSalarial> anterioresAumentos = todosHistorialSalarialAumento();
        if (anterioresAumentos == null) {
            historialSalarial.setVerificacionHistorial(3); //Aumento 3 = se puede eliminar
        } else {
            //VERIFICACION = 4 NO SE PODRAN ELIMINAR
            Iterator<HistorialSalarial> itearador = anterioresAumentos.iterator();
            while (itearador.hasNext()) {
                HistorialSalarial historial = itearador.next();
                historial.setVerificacionHistorial(4);
                getHistorialSalarialFacade().edit(historial);
            }

            historialSalarial.setVerificacionHistorial(3); // VERIFICACION PARA ELIMINAR AL ULTIMO
        }

        //Para validar fechas y no se haga aumento el mismo dia
        //Se podra aumentar solo si la fecha de aplicacion del nuevo aumento es posterior a la
        // a la fecha del nuevo ingreso
        String fechastring = sdf.format(historialSalarial.getFechaCreaHsal());
        Date fechaActualHistorial = new Date();

        try {
            fechaActualHistorial = sdf.parse(fechastring);
        } catch (ParseException ex) {
        }

        getHistorialSalarialFacade().create(historialSalarial);

        List<Empleados> empAumento = getEmpleadosFacade().findAll();
        Iterator<Empleados> iteradorEmpleados = empAumento.iterator();

        while (iteradorEmpleados.hasNext()) {
            boolean verificaFecha;
            Empleados emp = iteradorEmpleados.next();
            Double salarioEmp = emp.getSalarioEmp();
            Date fechaUltimoAu = emp.getFechaUltimoAumento();
            Date fechaUltimo = new Date();

            if (fechaUltimoAu == null) {
                verificaFecha = true;
            } else {

                String fechastring2 = sdf.format(fechaUltimoAu);

                try {
                    fechaUltimo = sdf.parse(fechastring2);
                } catch (ParseException ex) {
                }

                if (fechaUltimo.before(fechaActualHistorial)) {
                    //"La Fecha 1 es menor ";
                    verificaFecha = true;
                } else {
                    if (fechaActualHistorial.before(fechaUltimo)) {
                        //"La Fecha 1 es Mayor ";
                        verificaFecha = false;
                    } else {
                        //"Las Fechas Son iguales ";
                        verificaFecha = false;
                    }
                }
            }

            if (salarioEmp >= min && salarioEmp <= max && verificaFecha) {
                Double porAum = this.getPorcentajeAumento() / 100.00;           //System.out.println(porAum);
                Double salNue = salarioEmp * porAum;                            //System.out.println(salNue);
                Double salTot = salNue + salarioEmp;                            //System.out.println(salTot);
                Double realSa = Math.rint(salTot * 100) / 100;                  //System.out.println(realSa);    

                emp.setSalarioHistorialAumento(emp.getSalarioEmp());
                emp.setSalarioEmp(realSa);
                emp.setIdHistorialSalarial(historialSalarial.getIdHsalarial());
                emp.setFechaUltimoAumento(new Date());
                getEmpleadosFacade().edit(emp);
            }

        }

        historialSalarial = new HistorialSalarial();
        
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro ingresado", "Registro ingresado");
        FacesContext.getCurrentInstance().addMessage(null, message);
        
        return "gestion_aumento_salarial";
    }

    public String editarAumentoSalarial() {

        //Regresa a sueldo anterior descontanto el aumento en % que se habia realizado
        //Con el fin de que no se haga un nuevo aumento sobre el aumento ingresado con anteriodidad
        List<Empleados> empAumentoRegresar = getEmpleadosFacade().findAll();
        Iterator<Empleados> iteradorEmpleadosRegresar = empAumentoRegresar.iterator();
        while (iteradorEmpleadosRegresar.hasNext()) {
            Empleados empRegreso = iteradorEmpleadosRegresar.next();
            Integer idHistorial = empRegreso.getIdHistorialSalarial();

            Double salarioEmp = empRegreso.getSalarioHistorialAumento();                        //System.out.println(salarioEmp);

            if (Objects.equals(historialSalarial.getIdHsalarial(), idHistorial)) {
//                Double porAum = this.getPorcentajeAumentoEditar() / 100.00;             //System.out.println(porAum);
//                Double salNue = salarioEmp * porAum;                            //System.out.println(salNue);
//                Double salTot = salarioEmp - salNue;                            //System.out.println(salTot);
//                Double realSa = Math.rint(salTot * 100) / 100;                  //System.out.println(realSa);    
//                empRegreso.setSalarioEmp(realSa);
//                empRegreso.setUserModEmp(1); //USUARIO MODIFICA
//                empRegreso.setFechaModEmp(new Date());
                empRegreso.setSalarioEmp(salarioEmp);
                getEmpleadosFacade().edit(empRegreso);
            }
        }

        //Guarda los datos del formulario Setea los valores que no se guardan directamente
        historialSalarial.setPorcentajeHsalarial(this.getPorcentajeAumento());
        historialSalarial.setUserModHsal(1);
        historialSalarial.setFechaModHsal(new Date());

        //Reingresa nuevos salarios a todos los empleados con el nuevo porcentaje editado
        List<Empleados> empAumento = getEmpleadosFacade().findAll();
        Iterator<Empleados> iteradorEmpleados = empAumento.iterator();
        while (iteradorEmpleados.hasNext()) {
            Empleados emp = iteradorEmpleados.next();
            Integer idHistorial = emp.getIdHistorialSalarial();
            if (Objects.equals(historialSalarial.getIdHsalarial(), idHistorial)) {
                Double salarioEmp = emp.getSalarioEmp();                        //System.out.println(salarioEmp);
                Double porAum = this.getPorcentajeAumento() / 100.00;           //System.out.println(porAum);
                Double salNue = salarioEmp * porAum;                            //System.out.println(salNue);
                Double salTot = salNue + salarioEmp;                            //System.out.println(salTot);
                Double realSa = Math.rint(salTot * 100) / 100;                  //System.out.println(realSa);    
                emp.setSalarioHistorialAumento(emp.getSalarioEmp());
                emp.setSalarioEmp(realSa);
                getEmpleadosFacade().edit(emp);
            }
        }

        getHistorialSalarialFacade().edit(historialSalarial);
        historialSalarial = new HistorialSalarial();
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Modificado", "Registro Modificado");
        FacesContext.getCurrentInstance().addMessage(null, message);
        return "gestion_aumento_salarial";
    }

    public void buscarNR(ActionEvent event) {
        Empleados emp = getEmpleadosFacade().buscarEmpNR(this.getNR());
        if (emp == null) {
            this.setNombreEmp("");
        } else {
            this.setEmpleadoSelecionado(emp.getIdEmpleado());
            this.setNombreEmp(emp.getNombreEmpleado());
        }
    }

    public String cancelar() {
        refresh();
        return "reg_mejora_salarial";
    }

    public String cancelarAumento() {
        refresh();
        return "reg_aumento_salarial";
    }

    public String cancelarEditarMejora() {
        refresh();
        return "gestion_mejora_salarial";
    }

    public String cancelarEditarAumento() {
        refresh();
        return "gestion_aumento_salarial";
    }

    public String nuevaMejora() {
        return "reg_mejora_salarial";
    }

    public String nuevoAumento() {
        return "reg_aumento_salarial";
    }
    
    public void mejoraSeleccionada(HistorialSalarial mejoraSal){
        historialSalarial = mejoraSal;
    }

    public String eliminarMejora() {
        Integer verifacarELiminar = historialSalarial.getVerificacionHistorial();
        if (verifacarELiminar == 1) {

            //ELIMINA HISTORIAL
            getHistorialSalarialFacade().remove(historialSalarial);

            //CAMBIA LA VERIFICACION DEL ULTIMO REGISSTRO QUE QUEDO DE ESE EMPLEADO
            List<HistorialSalarial> histo = getHistorialSalarialFacade().buscarHistorialEmpleado(historialSalarial.getIdEmpleado().getIdEmpleado());
            int ultimo = histo.size();

            if (ultimo > 0) {
                HistorialSalarial ultimoHistorial = histo.get(ultimo - 1);
                ultimoHistorial.setVerificacionHistorial(1); // SE PODRA ELINAR EL ULTIMO
                getHistorialSalarialFacade().edit(ultimoHistorial);
            }

            //REGRESA ESTADO ANTERIOR
            Empleados emp = getEmpleadosFacade().find(historialSalarial.getIdEmpleado().getIdEmpleado());
            emp.setSalarioEmp(historialSalarial.getSalarioActualHsalarial());
            emp.setUserModEmp(1);
            emp.setFechaModEmp(new Date());
            getEmpleadosFacade().edit(emp);

            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Eliminado", "Registro Eliminado");
            FacesContext.getCurrentInstance().addMessage(null, message);

        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "No se puele eliminar, porque existe un registro mas reciente", "No se puele eliminar, porque existe un registro mas reciente");
            FacesContext.getCurrentInstance().addMessage(null, message);

            System.out.println("NO SE PUEDE ELIMAR PORQUE HAY REGISTROS POSTERIORES");
        }
        historialSalarial = new HistorialSalarial();
        return "gestion_mejora_salarial";
    }

    public void eliminar(HistorialSalarial mejoraSal) {
        historialSalarial = mejoraSal;
    }

    public String eliminarAumento() {
        Integer verifacarELiminar = historialSalarial.getVerificacionHistorial();

        if (verifacarELiminar == 3) {

            //ELIMINA HISTORIAL
            getHistorialSalarialFacade().remove(historialSalarial);

            //CAMBIA LA VERIFICACION DEL ULTIMO REGISSTRO QUE QUEDO DE ESE EMPLEADO
            List<HistorialSalarial> histo = todosHistorialSalarialAumento();
            int ultimo = histo.size();

            if (ultimo > 0) {
                HistorialSalarial ultimoHistorial = histo.get(ultimo - 1);
                ultimoHistorial.setVerificacionHistorial(3); // SE PODRA ELINAR EL ULTIMO
                getHistorialSalarialFacade().edit(ultimoHistorial);
            }

            //REGRESA ESTADO ANTERIOR
            List<Empleados> empAumentoRegresar = getEmpleadosFacade().findAll();
            Iterator<Empleados> iteradorEmpleadosRegresar = empAumentoRegresar.iterator();
            while (iteradorEmpleadosRegresar.hasNext()) {
                Empleados empRegreso = iteradorEmpleadosRegresar.next();
                Integer idHistorial = empRegreso.getIdHistorialSalarial();
                Date fechaUltimo = historialSalarial.getFechaCreaHsal();
                Double salarioEmp = empRegreso.getSalarioHistorialAumento();

                if (Objects.equals(historialSalarial.getIdHsalarial(), idHistorial)) {
                    empRegreso.setSalarioEmp(salarioEmp);
                    empRegreso.setFechaUltimoAumento(fechaUltimo);
                    getEmpleadosFacade().edit(empRegreso);
                }
            }

            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Eliminado", "Registro Eliminado");
            FacesContext.getCurrentInstance().addMessage(null, message);

        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "No se puele eliminar, porque exíste un registro más reciente", "No se puele eliminar, porque exíste un registro más reciente");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        historialSalarial = new HistorialSalarial();
        return "gestion_aumento_salarial";
    }

    public manejadorAumentoMejoraSalarial() {
    }

    public void refresh() { //Elimina el bean dejando limpio para nueva registro o cancelacion
        FacesContext context = FacesContext.getCurrentInstance();
        Application application = context.getApplication();
        ViewHandler viewHandler = application.getViewHandler();
        UIViewRoot viewRoot = viewHandler.createView(context, context.getViewRoot().getViewId());
        context.setViewRoot(viewRoot);
        context.renderResponse();
    }

}
