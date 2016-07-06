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
import sv.gob.cultura.rrhh.entities.CaracteristicasIdioma;
import sv.gob.cultura.rrhh.facades.CaracteristicasIdiomaFacade;

/**
 *
 * @author SOPORTE CULTURA
 */
@Named(value = "caracteristicasIdiomaConvertidor")
@Dependent
public class CaracteristicasIdiomaConvertidor implements Converter{
    
    @EJB
    private CaracteristicasIdiomaFacade caracteristicasIdiomaFacade;

    public CaracteristicasIdiomaFacade getCaracteristicasIdiomaFacade() {
        return caracteristicasIdiomaFacade;
    }
    
    public CaracteristicasIdiomaConvertidor() {
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value.trim().equals("") || value.trim().equals("Seleccione uno...")) {
            return null;
        } else {
            try {
                int id = Integer.parseInt(value);
                CaracteristicasIdioma caracteristicasIdioma = getCaracteristicasIdiomaFacade().find(id);
                return caracteristicasIdioma;
            } catch (Exception e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de conversión", "No es un Característica de Idioma válido"));
            }
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (!(value instanceof CaracteristicasIdioma)) {
            return null;
        }
        return String.valueOf(((CaracteristicasIdioma) value).getIdCaractIdioma());
    }
    
}
