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
import sv.gob.cultura.rrhh.entidades.Genero;
import sv.gob.cultura.rrhh.facades.GeneroFacade;

/**
 *
 * @author sic-sv
 */
@Named(value = "generoConvertidor")
@Dependent
public class GeneroConvertidor implements Converter {

    @EJB
    private GeneroFacade generoFacade;

    public GeneroConvertidor() {
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value.trim().equals("") || value.trim().equals("Seleccione uno...")) {
            return null;
        } else {
            try {
                int id = Integer.parseInt(value);
                Genero genero = getGeneroFacade().find(id);
                return genero;
            } catch (Exception e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de conversión", "No es resgistro válido de genero"));
            }
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (!(value instanceof Genero)) {
            return null;
        }
        return String.valueOf(((Genero) value).getIdGenero());
    }

    public GeneroFacade getGeneroFacade() {
        return generoFacade;
    }

}
