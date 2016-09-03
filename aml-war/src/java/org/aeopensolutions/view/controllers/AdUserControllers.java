/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.aeopensolutions.view.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.inject.Inject;
import org.aeopensolutions.model.ejb.facades.AdRoleFacade;
import org.aeopensolutions.model.ejb.facades.AdUserFacade;
import org.aeopensolutions.model.ejb.facades.AdUserRolesFacade;
import org.aeopensolutions.model.entities.AdRole;
import org.aeopensolutions.model.entities.AdUser;
import org.aeopensolutions.model.entities.AdUserRoles;
import org.aeopensolutions.view.components.DataList;
import org.aeopensolutions.view.utils.JsfUtils;

/**
 *
 * @author Usuario
 */
@ManagedBean(name = "adUser")
@ViewScoped
public class AdUserControllers implements Serializable {

    @Inject
    private AdUserFacade adUserFacade;

    private String pass1;
    private String pass2;

    @Inject
    private AdUserRolesFacade adUserRolesFacade;

    @Inject
    private AdRoleFacade adRoleFacade;
    
    private List<AdUser> listaUsuario;

    @PostConstruct
    public void initialize() {
        listaUsuarios.load();
        listaUsuario = listaUsuarios.getDataTable();
    }

    public List<AdUser> getListaUsuario() {
        return listaUsuario;
    }

    public void setListaUsuario(List<AdUser> listaUsuario) {
        this.listaUsuario = listaUsuario;
    }
    
    

    private DataList<AdUser> listaUsuarios = new DataList<AdUser>() {
        @Override
        protected void initialize() {
            System.out.println("initialize DataList AdUser");
        }

        @Override
        public List<AdUser> loadDataList() {
            //entidad para filtrar
            //AdUser user = new AdUser();
            //user.setIsactive(YesNo.SI);
            return adUserFacade.findAll();
        }

        @Override
        protected AdUser create() {
            System.out.println("create aduser");
            setPass1(null);
            setPass2(null);
            return new AdUser();
        }
        
        @Override
        protected void createLast() {
            listaUsuarioRoles.load();
            UIComponent foundComponent = JsfUtils.getUIComponentOfId(JsfUtils.getCurrentContext().getViewRoot(),"dlUsuarioRoles");
            JsfUtils.update(foundComponent.getClientId());
        }


        @Override
        protected AdUser edit(AdUser item) {
            System.out.println("edit aduser: " + item);
            //setPass1(item.getPassword());
            //setPass2(item.getPassword());
            setPass1(null);
            setPass2(null);
            
            //JsfUtils.update("frmUsuarios:dlUsuarioRoles");
            
            listaUsuarioRoles.load();
            
            
            
            return item;
        }
        
        @Override
        protected AdUser editLast(AdUser item) {
             UIComponent foundComponent = JsfUtils.getUIComponentOfId(JsfUtils.getCurrentContext().getViewRoot(),"dlUsuarioRoles");
            JsfUtils.update(foundComponent.getClientId());
            return item;
        }

        @Override
        protected AdUser save(AdUser item) {
            System.out.println("save aduser: " + item);
            try {
                adUserFacade.save(item, getPass1(), getPass2());
            } catch (Exception e) {
                JsfUtils.messageError(null, e.getMessage(), null);
                return null;
            }

            JsfUtils.messageInfo(null, "Usuario guardado correctamente.", null);

            return item;
        }
        
         @Override
        protected AdUser saveLast(AdUser item) {
            UIComponent foundComponent = JsfUtils.getUIComponentOfId(JsfUtils.getCurrentContext().getViewRoot(),"dlUsuarioRoles");
            JsfUtils.update(foundComponent.getClientId());
            return item; 
        }

        

        @Override
        protected void delete(List<AdUser> items) {
            System.out.println("delete aduser: " + items);
            try {
                adUserFacade.delete(items);
            } catch (Exception e) {
                JsfUtils.messageError(null, e.getMessage(), null);
                return;
            }

            JsfUtils.messageInfo(null, "Usuario eliminado correctamente.", null);
        }
        
        @Override
        protected void deleteLast(List<AdUser> items) {
            UIComponent foundComponent = JsfUtils.getUIComponentOfId(JsfUtils.getCurrentContext().getViewRoot(),"dlUsuarioRoles");
            JsfUtils.update(foundComponent.getClientId());
        }

        @Override
        protected void cancel() {
        }
        
        @Override
        protected void cancelLast() {
            UIComponent foundComponent = JsfUtils.getUIComponentOfId(JsfUtils.getCurrentContext().getViewRoot(),"dlUsuarioRoles");
            JsfUtils.update(foundComponent.getClientId());
        }
        
        

    };

    public DataList<AdUser> getListaUsuarios() {
        return listaUsuarios;
    }

    private DataList<AdUserRoles> listaUsuarioRoles = new DataList<AdUserRoles>() {
        @Override
        protected void initialize() {
            System.out.println("initialize DataList AdUserRoles");
        }

        @Override
        public List<AdUserRoles> loadDataList() {
            return adUserRolesFacade.findByAdUser(listaUsuarios.getActiveItem());
        }

        @Override
        protected AdUserRoles create() {
            System.out.println("create AdUserRoles");
            return new AdUserRoles();
        }

        @Override
        protected AdUserRoles edit(AdUserRoles item) {
            System.out.println("edit AdUserRoles: " + item);
            return item;
        }

        @Override
        protected AdUserRoles save(AdUserRoles item) {
            System.out.println("save AdUserRoles: " + item);
            try {
                adUserRolesFacade.save(item, listaUsuarios.getActiveItem());
            } catch (Exception e) {
                JsfUtils.messageError(null, e.getMessage(), null);
                return null;
            }

            JsfUtils.messageInfo(null, "Rol asignado correctamente.", null);

            return item;
        }

        @Override
        protected void delete(List<AdUserRoles> items) {
            System.out.println("delete AdUserRoles: " + items);
            try {
                adUserRolesFacade.delete(items);
            } catch (Exception e) {
                JsfUtils.messageError(null, e.getMessage(), null);
                return;
            }

            JsfUtils.messageInfo(null, "Rol eliminado correctamente.", null);
        }

        @Override
        protected void cancel() {
            System.out.println("cancel AdUserRoles");
        }

    };

    public DataList<AdUserRoles> getListaUsuarioRoles() {
        return listaUsuarioRoles;
    }

    public List<AdRole> completeRol(String query) {

        System.out.println("query: " + query);

        List<AdRole> allRol = adRoleFacade.findAll();
        List<AdRole> filteredRoles = new ArrayList<AdRole>();

        if (allRol != null && !allRol.isEmpty()) {
            for (int i = 0; i < allRol.size(); i++) {
                AdRole rol = allRol.get(i);
                if (rol.getName().toUpperCase().contains(query.toUpperCase())) {
                    filteredRoles.add(rol);
                }
            }
        }

        return filteredRoles;
    }

    public String getPass1() {
        return pass1;
    }

    public void setPass1(String pass1) {
        this.pass1 = pass1;
    }

    public String getPass2() {
        return pass2;
    }

    public void setPass2(String pass2) {
        this.pass2 = pass2;
    }

}
