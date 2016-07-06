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
import sv.gob.cultura.rrhh.entities.GradoSancion;
import sv.gob.cultura.rrhh.facades.GradoSancionFacade;

/**
 *
 * @author SOPORTE CULTURA
 */
@Named(value = "gradoSancionConvertidor")
@Dependent
public class GradoSancionConvertidor implements Converter{
    
    @EJB
    private GradoSancionFacade gradoSancionFacade;

    public GradoSancionFacade getGradoSancionFacade() {
        return gradoSancionFacade;
    }

    public GradoSancionConvertidor() {
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value.trim().equals("") || value.trim().equals("Seleccione uno...")) {
            return null;
        } else {
            try {
                int id = Integer.parseInt(value);
                GradoSancion gradoSancion = getGradoSancionFacade().find(id);
                return gradoSancion;
            } catch (Exception e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de conversión", "No es un grado de Sanción válido"));
            }
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (!(value instanceof GradoSancion)) {
            return null;
        }
        return String.valueOf(((GradoSancion) value).getIdGradoSancion());
    }
    
}
