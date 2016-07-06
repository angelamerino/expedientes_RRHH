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
import sv.gob.cultura.rrhh.entities.Parentesco;
import sv.gob.cultura.rrhh.facades.ParentescoFacade;

/**
 *
 * @author SOPORTE CULTURA
 */
@Named(value = "parentescoConvertidor")
@Dependent
public class ParentescoConvertidor implements Converter {
   
    @EJB
    private ParentescoFacade parentescoFacade;

    public ParentescoFacade getParentescoFacade() {
        return parentescoFacade;
    }

    public ParentescoConvertidor() {
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value.trim().equals("") || value.trim().equals("Seleccione uno...")) {
            return null;
        } else {
            try {
                int id = Integer.parseInt(value);
                Parentesco parentesco = getParentescoFacade().find(id);
                return parentesco;
            } catch (Exception e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de conversión", "No es un tipo de parentesco válido"));
            }
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (!(value instanceof Parentesco)) {
            return null;
        }
        return String.valueOf(((Parentesco) value).getIdParentesco());
    }

}
