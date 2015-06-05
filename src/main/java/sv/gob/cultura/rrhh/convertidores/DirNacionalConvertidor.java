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
import sv.gob.cultura.rrhh.entidades.DirNacional;
import sv.gob.cultura.rrhh.facades.DirNacionalFacade;

/**
 *
 * @author SOPORTE CULTURA
 */
@Named(value = "dirNacionalConvertidor")
@Dependent
public class DirNacionalConvertidor implements Converter {
    
    @EJB
    private DirNacionalFacade dirNacionalFacade;

    public DirNacionalFacade getDirNacionalFacade() {
        return dirNacionalFacade;
    }
    
    public DirNacionalConvertidor() {
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
         if (value.trim().equals("") || value.trim().equals("Seleccione uno...")) {
            return null;
        } else {
            try {
                int id = Integer.parseInt(value);
                DirNacional dirNacional = getDirNacionalFacade().find(id);
                return dirNacional;
            } catch (Exception e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de conversión", "No es Dirección Nacional Válida válido"));
            }
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (!(value instanceof DirNacional)) {
            return null;
        }
        return String.valueOf(((DirNacional) value).getIdDirNac());
    }
    
}
