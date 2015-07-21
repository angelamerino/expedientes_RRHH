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
import sv.gob.cultura.rrhh.entidades.TipoPrestacion;
import sv.gob.cultura.rrhh.facades.TipoPrestacionFacade;

/**
 *
 * @author SOPORTE CULTURA
 */
@Named(value = "tipoPrestacionConvertidor")
@Dependent
public class TipoPrestacionConvertidor implements Converter{
    
    @EJB
    private TipoPrestacionFacade tipoPrestacionFacade;

    public TipoPrestacionFacade getTipoPrestacionFacade() {
        return tipoPrestacionFacade;
    }
    
    public TipoPrestacionConvertidor() {
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value.trim().equals("") || value.trim().equals("Seleccione uno...")) {
            return null;
        } else {
            try {
                int id = Integer.parseInt(value);
                TipoPrestacion tipoPrestacion = getTipoPrestacionFacade().find(id);
                return tipoPrestacion;
            } catch (Exception e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de conversión", "No es un Tipo de Prestación válido"));
            }
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (!(value instanceof TipoPrestacion)) {
            return null;
        }
        return String.valueOf(((TipoPrestacion) value).getIdTipoPrestacion());
    }
    
}
