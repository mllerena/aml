package org.aeopensolutions.view.converters;

import java.io.Serializable;
import java.math.BigInteger;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;

import org.aeopensolutions.model.ejb.facades.AdRoleFacade;
import org.aeopensolutions.model.entities.AdRole;

@ManagedBean
@ApplicationScoped
public class AdRoleConverter implements Converter, Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Inject
    AdRoleFacade adRoleFacade;

    // ESTE CONVIERTE EL SELECTITEM SELECCIONADO EN OBJETO PARA ENVIAR AL OBEJETO PARA PERSISTIR
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        AdRole objRes = null;
        if (!string.trim().equals("") && string != null) {
            BigInteger id = new BigInteger(string);
            objRes = (AdRole) adRoleFacade.find(id);
        }
//        return (string.trim().equals("") || string == null) ? null : objRes;
        return objRes;
    }

    // ESTE ES EL QUE SE EJECUTA AL INICIO Y HACE QUE EL CATALOGO MUESTRE LOS IDS
    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        return (o == null || o.equals("")) ? "" : ((AdRole) o).getId() + "";

    }
}
