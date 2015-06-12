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
import sv.gob.cultura.rrhh.entidades.ProfOficios;
import sv.gob.cultura.rrhh.facades.ProfOficiosFacade;

/**
 *
 * @author SOPORTE CULTURA
 */
@Named(value = "profOficioConvertidor")
@Dependent
public class ProfOficioConvertidor implements Converter{
    @EJB
    private ProfOficiosFacade profOficiosFacade;

    public ProfOficiosFacade getProfOficiosFacade() {
        return profOficiosFacade;
    }

    /**
     * Creates a new instance of ProfOficioConvertidor
     */
    
    public ProfOficioConvertidor() {
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value.trim().equals("") || value.trim().equals("Seleccione uno...")) {
            return null;
        } else {
            try {
                int id = Integer.parseInt(value);
                ProfOficios profOficios = getProfOficiosFacade().find(id);
                return profOficios;
            } catch (Exception e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de conversión", "No es una profecion u oficio válido"));
            }
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (!(value instanceof ProfOficios)) {
            return null;
        }
        return String.valueOf(((ProfOficios) value).getIdProfOficio());
    }
    
}
