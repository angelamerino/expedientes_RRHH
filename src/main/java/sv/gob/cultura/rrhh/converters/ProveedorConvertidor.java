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
import sv.gob.cultura.rrhh.entities.Proveedor;
import sv.gob.cultura.rrhh.facades.ProveedorFacade;

/**
 *
 * @author SOPORTE CULTURA
 */
@Named(value = "proveedorConvertidor")
@Dependent
public class ProveedorConvertidor implements Converter{
    
    @EJB
    private ProveedorFacade proveedorFacade;

    public ProveedorFacade getProveedorFacade() {
        return proveedorFacade;
    }

    public ProveedorConvertidor() {
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value.trim().equals("") || value.trim().equals("Seleccione uno...")) {
            return null;
        } else {
            try {
                int id = Integer.parseInt(value);
                Proveedor proveedor = getProveedorFacade().find(id);
                return proveedor;
            } catch (Exception e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de conversión", "No es un Proveedor válido"));
            }
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (!(value instanceof Proveedor)) {
            return null;
        }
        return String.valueOf(((Proveedor) value).getIdProveedor());
    }
    
}
