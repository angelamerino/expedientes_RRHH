/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.cultura.rrhh.converters;

import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import sv.gob.cultura.rrhh.entities.EstadosUsuarios;
import sv.gob.cultura.rrhh.facades.EstadosUsuariosFacade;

/**
 *
 * @author SOPORTE CULTURA
 */
@Named(value = "estadoUsuariosConvertidor")
@Dependent
public class EstadoUsuariosConvertidor implements Converter {
    
    @EJB
    private EstadosUsuariosFacade estadosUsuariosFacade;
    
    public EstadoUsuariosConvertidor() {
    }

    
    
    public EstadosUsuariosFacade getEstadosUsuariosFacade() {
        return estadosUsuariosFacade;
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
         if (value.trim().equals("") || value.trim().equals("Seleccione uno...")) {
            return null;
        } else {
            try {
                int id = Integer.parseInt(value);
                EstadosUsuarios estadoUsuarios = getEstadosUsuariosFacade().find(id);
                return estadoUsuarios;
            } catch (Exception e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de conversión", "No es Estado de Usuario válido"));
            }
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (!(value instanceof EstadosUsuarios)) {
            return null;
        }
        return String.valueOf(((EstadosUsuarios) value).getIdEstadoUsuario());
    }
    
}
