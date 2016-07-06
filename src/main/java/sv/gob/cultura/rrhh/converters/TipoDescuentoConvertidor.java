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
import sv.gob.cultura.rrhh.entities.TipoDescuento;
import sv.gob.cultura.rrhh.facades.TipoDescuentoFacade;

/**
 *
 * @author SOPORTE CULTURA
 */
@Named(value = "tipoDescuentoConvertidor")
@Dependent
public class TipoDescuentoConvertidor implements Converter{
    
    @EJB
    private TipoDescuentoFacade tipoDescuentoFacade;

    public TipoDescuentoFacade getTipoDescuentoFacade() {
        return tipoDescuentoFacade;
    }
   
    public TipoDescuentoConvertidor() {
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value.trim().equals("") || value.trim().equals("Seleccione uno...")) {
            return null;
        } else {
            try {
                int id = Integer.parseInt(value);
                TipoDescuento tipoDes = getTipoDescuentoFacade().find(id);
                return tipoDes;
            } catch (Exception e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de conversión", "No es un Tipo de Descuento válido"));
            }
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (!(value instanceof TipoDescuento)) {
            return null;
        }
        return String.valueOf(((TipoDescuento) value).getIdDescuento());
    }
    
}
