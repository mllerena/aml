package org.aeopensolutions.view.utils;

import java.util.Collection;
import java.util.Set;

import javax.el.ELContext;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;
import org.primefaces.util.ComponentUtils;

public class JsfUtils {

    public static UIComponent findComponent(String id) {

        return getCurrentContext().getViewRoot().findComponent(id);

    }

    public static UIComponent getUIComponentOfId(UIComponent root, String id) {
        if (root.getId().contains(id)) {
            return root;
        }
        if (root.getChildCount() > 0) {
            for (UIComponent subUiComponent : root.getChildren()) {
                UIComponent returnComponent = getUIComponentOfId(subUiComponent, id);
                if (returnComponent != null) {
                    return returnComponent;
                }
            }
        }
        return null;
    }

    public static RequestContext currentPFContext() {
        return RequestContext.getCurrentInstance();
    }

    public static void executeJS(String js) {
        RequestContext rc = RequestContext.getCurrentInstance();
        rc.execute(js);
    }

    public static void update(String target) {
        RequestContext rc = RequestContext.getCurrentInstance();
        rc.update(target);
    }

    public static void update(Collection<String> targets) {
        RequestContext rc = RequestContext.getCurrentInstance();
        rc.update(targets);
    }

    public static FacesContext getCurrentContext() {
        return FacesContext.getCurrentInstance();
    }

    public static ExternalContext getExternalContext() {
        return getCurrentContext().getExternalContext();
    }

    public static ELContext getElContext() {
        return getCurrentContext().getELContext();
    }

    public static Application getApplication() {
        return getCurrentContext().getApplication();
    }

    public static HttpServletRequest getRequest() {
        return (HttpServletRequest) getExternalContext().getRequest();
    }
    
    public static HttpSession getSession() {
        return (HttpSession) getExternalContext().getSession(true);
    }
    
    public static HttpServletResponse getResponse() {
        return (HttpServletResponse) getExternalContext().getResponse();
    }

    public static void removeViewScope(String view) throws Exception {

        if (view == null) {
            throw new Exception("removeViewScope, Parametro view no debe ser null.");
        }

        if (view.isEmpty()) {
            throw new Exception("removeViewScope, Parametro view no debe estar vacio.");
        }

        //System.out.println("map: "+FacesContext.getCurrentInstance().getViewRoot().getViewMap());
        System.out.println("ViewScopeContext: " + FacesContext.getCurrentInstance().getViewRoot().getViewMap());

        Set<String> keys = getCurrentContext().getViewRoot().getViewMap().keySet();

        String keyFinal = null;

        for (String key : keys) {

            if (view.toUpperCase().equals(key.toUpperCase())) {
                keyFinal = key;
            }

        }

        getCurrentContext().getViewRoot().getViewMap().remove(keyFinal);

        System.out.println("ViewScopeContext final: " + FacesContext.getCurrentInstance().getViewRoot().getViewMap());

    }

    public static void removeManagedBean(final String beanName) {
        FacesContext fc = FacesContext.getCurrentInstance();
        fc.getELContext().getELResolver().setValue(fc.getELContext(), null, beanName, null);
    }

    public static void openDialog(String outcome) {
        RequestContext.getCurrentInstance().openDialog(outcome);
    }

    public static void closeDialog(Object object) {
        RequestContext.getCurrentInstance().closeDialog(object);
    }

    public static void messageInfo(String id, String main, String desc) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(id, new FacesMessage(FacesMessage.SEVERITY_INFO, main, desc));
    }

    public static void messageWarning(String id, String main, String desc) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(id, new FacesMessage(FacesMessage.SEVERITY_WARN, main, desc));
    }

    public static void messageError(String id, String main, String desc) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(id, new FacesMessage(FacesMessage.SEVERITY_ERROR, main, desc));
    }

    public static void messageFatal(String id, String main, String desc) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(id, new FacesMessage(FacesMessage.SEVERITY_FATAL, main, desc));
    }

}
