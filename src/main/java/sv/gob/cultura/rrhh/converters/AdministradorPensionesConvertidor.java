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
import sv.gob.cultura.rrhh.entities.AdministradoraPensiones;
import sv.gob.cultura.rrhh.facades.AdministradoraPensionesFacade;

/**
 *
 * @author SOPORTE CULTURA
 */
@Named(value = "administradorPensionesConvertidor")
@Dependent
public class AdministradorPensionesConvertidor implements Converter {
    
    @EJB
    private AdministradoraPensionesFacade administradoraPensionesFacade;

    public AdministradoraPensionesFacade getAdministradoraPensionesFacade() {
        return administradoraPensionesFacade;
    }
  
    public AdministradorPensionesConvertidor() {
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value.trim().equals("") || value.trim().equals("Seleccione uno...")) {
            return null;
        } else {
            try {
                int id = Integer.parseInt(value);
                AdministradoraPensiones administradoraPensiones = getAdministradoraPensionesFacade().find(id);
                return administradoraPensiones;
            } catch (Exception e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de conversión", "No es un tipo de Administradora de Pensiones válido"));
            }
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (!(value instanceof AdministradoraPensiones)) {
            return null;
        }
        return String.valueOf(((AdministradoraPensiones) value).getIdAdminPension());
    }
    
}
