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
import sv.gob.cultura.rrhh.entidades.TipoSancion;
import sv.gob.cultura.rrhh.facades.TipoSancionFacade;

/**
 *
 * @author SOPORTE CULTURA
 */
@Named(value = "tipoSancionConvertidor")
@Dependent
public class TipoSancionConvertidor implements Converter{
    
    @EJB
    private TipoSancionFacade tipoSancionFacade;

    public TipoSancionFacade getTipoSancionFacade() {
        return tipoSancionFacade;
    }

    public TipoSancionConvertidor() {
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value.trim().equals("") || value.trim().equals("Seleccione uno...")) {
            return null;
        } else {
            try {
                int id = Integer.parseInt(value);
                TipoSancion tipoSancion = getTipoSancionFacade().find(id);
                return tipoSancion;
            } catch (Exception e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de conversión", "No es un Tipo de Sanción válido"));
            }
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (!(value instanceof TipoSancion)) {
            return null;
        }
        return String.valueOf(((TipoSancion) value).getIdTipoSancion());
    }
    
}
