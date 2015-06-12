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
import sv.gob.cultura.rrhh.entidades.Nivel;
import sv.gob.cultura.rrhh.facades.NivelFacade;

/**
 *
 * @author SOPORTE CULTURA
 */
@Named(value = "nivelIdiomaConvertidor")
@Dependent
public class NivelIdiomaConvertidor implements Converter{
    
    @EJB
    private NivelFacade nivelFacade;

    public NivelFacade getNivelFacade() {
        return nivelFacade;
    }
   
    public NivelIdiomaConvertidor() {
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value.trim().equals("") || value.trim().equals("Seleccione uno...")) {
            return null;
        } else {
            try {
                int id = Integer.parseInt(value);
                Nivel nivel = getNivelFacade().find(id);
                return nivel;
            } catch (Exception e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de conversión", "No es un Nivel de Idioma válido"));
            }
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (!(value instanceof Nivel)) {
            return null;
        }
        return String.valueOf(((Nivel) value).getIdNivel());
    }
    
}
