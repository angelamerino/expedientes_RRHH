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
import sv.gob.cultura.rrhh.entidades.Idiomas;
import sv.gob.cultura.rrhh.facades.IdiomasFacade;

/**
 *
 * @author SOPORTE CULTURA
 */
@Named(value = "idiomasConvertidor")
@Dependent
public class IdiomasConvertidor implements Converter{
    
    @EJB
    private IdiomasFacade idiomasFacade;

    public IdiomasFacade getIdiomasFacade() {
        return idiomasFacade;
    }
    
    public IdiomasConvertidor() {
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value.trim().equals("") || value.trim().equals("Seleccione uno...")) {
            return null;
        } else {
            try {
                int id = Integer.parseInt(value);
                Idiomas idioma = getIdiomasFacade().find(id);
                return idioma;
            } catch (Exception e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de conversión", "No es un Idioma válido"));
            }
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (!(value instanceof Idiomas)) {
            return null;
        }
        return String.valueOf(((Idiomas) value).getIdIdioma());
    }
    
}
