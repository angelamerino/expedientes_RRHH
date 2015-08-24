/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.cultura.rrhh.manejadores;

import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.application.ViewHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
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
    private int porcentaje;                         //Porcentaje de Aumento en mejora salarial
    private int porcentajeEditar;                   //Porcentaje de Aumento en Mejora salarial Editar
    private int porcentajeAumento;                  //Porcentaje de Aumento en Aumento salarial
    private int porcentajeAumentoEditar;            //Porcentaje de Aunemto en Aumento salaria Editar
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

    public int getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(int porcentaje) {
        //Al ingresar el porcentaje se procede ha realizar el aumento de sueldo
        double por = porcentaje / 100.00;               // Porcentaje real
        double sal = this.getSalarioActual() * por;     // Aumento
        double nsal = sal + this.getSalarioActual();    // Nuevo salario
        double rsal = Math.rint(nsal * 100) / 100;      // Redondeo a 2 digitos
        this.setSalarioNuevo(rsal);                     // Setea Nuevo salario
        this.porcentaje = porcentaje;
    }

    public int getPorcentajeAumento() {
        return porcentajeAumento;
    }

    public void setPorcentajeAumento(int porcentajeAumento) {
        this.porcentajeAumento = porcentajeAumento;
    }

    public double getSalarioAnteriorEditar() {
        return salarioAnteriorEditar;
    }

    public void setSalarioAnteriorEditar(double salarioAnteriorEditar) {
        this.salarioAnteriorEditar = salarioAnteriorEditar;
    }

    public int getPorcentajeEditar() {
        return porcentajeEditar;
    }

    public void setPorcentajeEditar(int porcentajeEditar) {
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

    public int getPorcentajeAumentoEditar() {
        return porcentajeAumentoEditar;
    }

    public void setPorcentajeAumentoEditar(int porcentajeAumentoEditar) {
        this.porcentajeAumentoEditar = porcentajeAumentoEditar;
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

    public List<HistorialSalarial> todosHistorialSalarial() {
        return getHistorialSalarialFacade().findAll();
    }
//******************************* FUNCIONES ************************************
//******************************************************************************

    public String guardarMejoraSalarial() {
        if (this.getEmpleadoSelecionado() == 0) { //verifica si se seleccino un empleado
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Seleccione un Empleado"));
            return null;
        } else {

            //Guarda los datos del formulario Setea los valores que no se guardan directamente
            historialSalarial.setIdEmpleado(new Empleados(this.getEmpleadoSelecionado()));
            historialSalarial.setSalarioActualHsalarial(this.getSalarioActual());
            historialSalarial.setNuevoSalarioHsalarial(this.getSalarioNuevo());
            historialSalarial.setPorcentajeHsalarial(this.getPorcentaje());
            historialSalarial.setUserCreaHsal(1);
            historialSalarial.setFechaCreaHsal(new Date());

            getHistorialSalarialFacade().create(historialSalarial);
            historialSalarial = new HistorialSalarial();

            Empleados emp = getEmpleadosFacade().find(this.getEmpleadoSelecionado());
            emp.setSalarioEmp(this.getSalarioNuevo());
            emp.setUserModEmp(1);
            emp.setFechaModEmp(new Date());
            getEmpleadosFacade().edit(emp);

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

        return "gestion_mejora_salarial";

    }

    public String guardarAumentoSalarial() {

        //DEBERIAN DE SER NULOS PARA AUMENTO NO APLICAN
        historialSalarial.setIdEmpleado(new Empleados(1));
        historialSalarial.setSalarioActualHsalarial(0.0);
        historialSalarial.setNuevoSalarioHsalarial(0.0);
        historialSalarial.setIdMejoraSal(new TipoMejoraSalarial(1));

        //Guarda los datos del formulario Setea los valores que no se guardan directamente
        historialSalarial.setPorcentajeHsalarial(this.getPorcentajeAumento());
        historialSalarial.setUserCreaHsal(1);
        historialSalarial.setFechaCreaHsal(new Date());

        List<Empleados> empAumento = getEmpleadosFacade().findAll();
        Iterator<Empleados> iteradorEmpleados = empAumento.iterator();
        while (iteradorEmpleados.hasNext()) {
            Empleados emp = iteradorEmpleados.next();
            Double salarioEmp = emp.getSalarioEmp();                        //System.out.println(salarioEmp);
            Double porAum = this.getPorcentajeAumento() / 100.00;             //System.out.println(porAum);
            Double salNue = salarioEmp * porAum;                            //System.out.println(salNue);
            Double salTot = salNue + salarioEmp;                            //System.out.println(salTot);
            Double realSa = Math.rint(salTot * 100) / 100;                  //System.out.println(realSa);    
            emp.setSalarioEmp(realSa);
            emp.setUserModEmp(1); //USUARIO MODIFICA
            emp.setFechaModEmp(new Date());
            getEmpleadosFacade().edit(emp);
        }

        getHistorialSalarialFacade().create(historialSalarial);
        historialSalarial = new HistorialSalarial();

        return "gestion_aumento_salarial";
    }

    public String editarAumentoSalarial() {

        //Regresa a sueldo anterior descontanto el aumento en % que se habia realizado
        //Con el fin de que no se haga un nuevo aumento sobre el aumento ingresado con anteriodidad
        List<Empleados> empAumentoRegresar = getEmpleadosFacade().findAll();
        Iterator<Empleados> iteradorEmpleadosRegresar = empAumentoRegresar.iterator();
        while (iteradorEmpleadosRegresar.hasNext()) {
            Empleados empRegreso = iteradorEmpleadosRegresar.next();
            Double salarioEmp = empRegreso.getSalarioEmp();                        //System.out.println(salarioEmp);
            Double porAum = this.getPorcentajeAumentoEditar() / 100.00;             //System.out.println(porAum);
            Double salNue = salarioEmp * porAum;                            //System.out.println(salNue);
            Double salTot = salarioEmp - salNue;                            //System.out.println(salTot);
            Double realSa = Math.rint(salTot * 100) / 100;                  //System.out.println(realSa);    
            empRegreso.setSalarioEmp(realSa);
            empRegreso.setUserModEmp(1); //USUARIO MODIFICA
            empRegreso.setFechaModEmp(new Date());
            getEmpleadosFacade().edit(empRegreso);
        }

        //DEBERIAN DE SER NULOS PARA AUMENTO NO APLICAN
        //historialSalarial.setIdEmpleado(new Empleados(1));
        //historialSalarial.setSalarioActualHsalarial(0.0);
        //historialSalarial.setNuevoSalarioHsalarial(0.0);
        //historialSalarial.setIdMejoraSal(new TipoMejoraSalarial(1));
        //Guarda los datos del formulario Setea los valores que no se guardan directamente
        historialSalarial.setPorcentajeHsalarial(this.getPorcentajeAumento());
        historialSalarial.setUserModHsal(1);
        historialSalarial.setFechaModHsal(new Date());

        //Reingresa nuevos salarios a todos los empleados con el nuevo porcentaje editado
        List<Empleados> empAumento = getEmpleadosFacade().findAll();
        Iterator<Empleados> iteradorEmpleados = empAumento.iterator();
        while (iteradorEmpleados.hasNext()) {
            Empleados emp = iteradorEmpleados.next();
            Double salarioEmp = emp.getSalarioEmp();                        //System.out.println(salarioEmp);
            Double porAum = this.getPorcentajeAumento() / 100.00;             //System.out.println(porAum);
            Double salNue = salarioEmp * porAum;                            //System.out.println(salNue);
            Double salTot = salNue + salarioEmp;                            //System.out.println(salTot);
            Double realSa = Math.rint(salTot * 100) / 100;                  //System.out.println(realSa);    
            emp.setSalarioEmp(realSa);
            emp.setUserModEmp(1); //USUARIO MODIFICA
            emp.setFechaModEmp(new Date());
            getEmpleadosFacade().edit(emp);
        }

        getHistorialSalarialFacade().edit(historialSalarial);
        historialSalarial = new HistorialSalarial();

        return "gestion_aumento_salarial";
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

    public String eliminarMejora(HistorialSalarial mejoraSal) {
        getHistorialSalarialFacade().remove(mejoraSal);
        return "gestion_mejora_salarial";
    }

    public String eliminarAumento(HistorialSalarial mejoraSal) {
        getHistorialSalarialFacade().remove(mejoraSal);
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
