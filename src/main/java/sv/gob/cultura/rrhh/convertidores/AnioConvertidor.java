/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.cultura.rrhh.convertidores;

import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import sv.gob.cultura.rrhh.entidades.Anio;
import sv.gob.cultura.rrhh.facades.AnioFacade;

/**
 *
 * @author SOPORTE CULTURA
 */
@Named(value = "anioConvertidor")
@Dependent
public class AnioConvertidor implements Converter{
    
    @EJB
    private AnioFacade anioFacade;

    public AnioFacade getAnioFacade() {
        return anioFacade;
    }
    
    public AnioConvertidor() {
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value.trim().equals("") || value.trim().equals("Seleccione uno...")) {
            return null;
        } else {
            try {
                int id = Integer.parseInt(value);
                Anio anio = getAnioFacade().find(id);
                return anio;
            } catch (Exception e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de conversión", "No es un Año válido"));
            }
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (!(value instanceof Anio)) {
            return null;
        }
        return String.valueOf(((Anio) value).getIdAnio());
    }
    
}
