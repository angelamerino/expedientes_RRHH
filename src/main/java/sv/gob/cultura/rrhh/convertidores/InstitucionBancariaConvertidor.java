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
import sv.gob.cultura.rrhh.entidades.InstBancaria;
import sv.gob.cultura.rrhh.facades.InstBancariaFacade;

/**
 *
 * @author SOPORTE CULTURA
 */
@Named(value = "institucionBancariaConvertidor")
@Dependent
public class InstitucionBancariaConvertidor implements Converter {
    
    @EJB
    private InstBancariaFacade instBancariaFacade;

    public InstBancariaFacade getInstBancariaFacade() {
        return instBancariaFacade;
    }
    
    public InstitucionBancariaConvertidor() {
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
         if (value.trim().equals("") || value.trim().equals("Seleccione uno...")) {
            return null;
        } else {
            try {
                int id = Integer.parseInt(value);
                InstBancaria instBancaria = getInstBancariaFacade().find(id);
                return instBancaria;
            } catch (Exception e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de conversión", "No es una Institucion Bancaria válido"));
            }
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (!(value instanceof InstBancaria)) {
            return null;
        }
        return String.valueOf(((InstBancaria) value).getIdBanco());
    }
    
}
