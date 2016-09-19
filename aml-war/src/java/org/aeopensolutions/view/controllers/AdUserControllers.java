/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.aeopensolutions.view.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.Part;
import org.aeopensolutions.model.ejb.facades.AdRoleFacade;
import org.aeopensolutions.model.ejb.facades.AdUserFacade;
import org.aeopensolutions.model.entities.AdRole;
import org.aeopensolutions.model.entities.AdUser;
import org.aeopensolutions.view.components.DataTable;
import org.aeopensolutions.view.components.DataView;
import org.aeopensolutions.view.components.DataViewType;
import org.aeopensolutions.view.utils.JsfUtils;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author Usuario
 */
@ManagedBean(name = "adUser")
@SessionScoped
public class AdUserControllers implements Serializable {

    @Inject
    private AdUserFacade adUserFacade;
    
     private AdUser activeItem;

    private String pass1;
    private String pass2;
    
    private Part image;
    private boolean imageModified;

    @Inject
    private AdRoleFacade adRoleFacade;

    @PostConstruct
    public void initialize() {
        listaUsuarios.load();
    }

    public Part getImage() {
        return image;
    }

    public void setImage(Part image) {
        if (image != null) {
            try {
                InputStream input = image.getInputStream();
                activeItem.setImage(IOUtils.toByteArray(input));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else if (image == null && activeItem.getImage() != null && imageModified == true) {
            activeItem.setImage(null);
        }
        System.out.println("setImage: "+image);
    }

    public boolean isImageModified() {
        return imageModified;
    }

    public void setImageModified(boolean imageModified) {
        this.imageModified = imageModified;
    }
    
    public void validateImage(FacesContext ctx, UIComponent comp, Object value) {
        if (value != null) {
            Part file = (Part) value;

            if (!file.getContentType().startsWith("image")) {
                JsfUtils.messageWarning(null, "No hay imagen", null);
            }
            if (file.getSize() > 200024) {
                JsfUtils.messageWarning(null, "Imagen muy grande", null);
            }
        }
    }
    
   

    public AdUser getActiveItem() {
        return this.activeItem;
    }

    public void setActiveItem(AdUser activeItem) {
        this.activeItem = activeItem;
    }
    
    
    

    private DataView<AdUser> listaUsuarios = new DataView<AdUser>() {
        
         @Override
        protected void initialize() {
            System.out.println("initialize DataView AdUser");
        }
        
        @Override
        protected List<AdUser> filterGrid(String searchKey,List<AdUser> filteredValues) {
            List<AdUser> results = new ArrayList<>();
            for (AdUser user : filteredValues) {
                    if (user.getName().toUpperCase().contains(searchKey.toUpperCase()) ) {
                        results.add(user);
                    }
                }
            System.out.println("filtrado: "+results);
            return results;
        }
        
        
         @Override
        public List<DataViewType> viewTypes() { 
            List<DataViewType> list =  new ArrayList<>();
            list.add(DataViewType.TABLE);
            list.add(DataViewType.GRID);
            list.add(DataViewType.ROW);
            return list;
        }

        @Override
        protected void rowSelected(AdUser item) {
            setPass1(item.getPassword());    
            setActiveItem(item);
        }
        
        

       

        @Override
        public List<AdUser> findAll() {
            return adUserFacade.findAll();
        }

        @Override
        protected AdUser create() {
            System.out.println("create aduser");
            setPass1(null);
            setPass2(null);
            setActiveItem(new AdUser());
            return getActiveItem();
        }

        @Override
        protected AdUser save(AdUser item) {
            
            item.setImage(getActiveItem().getImage());
            
            System.out.println("save aduser: " + item+ " pass1: "+getPass1()+" pass2: "+getPass2()+" image: "+Arrays.toString(item.getImage()));
            try {
                
                if( getPass1() == null || getPass2() == null){
                    JsfUtils.messageWarning(null, "Debe ingresar la contraseña.", null);        
                    return null;
                }
                
                if( !getPass1().trim().equals(getPass2().trim())){
                    JsfUtils.messageWarning(null, "No coinciden las contraseñas.", null);        
                    return null;
                }
                
                item.setPassword(getPass1());
                adUserFacade.save(item);
                setSelectedItem(item);
                setPass1(item.getPassword());
                setPass2(null);
            } catch (Exception e) {
                JsfUtils.messageError(null, e.getMessage(), null);
                return null;
            }

            JsfUtils.messageInfo(null, "Usuario guardado correctamente.", null);

            return item;
        }

        @Override
        protected void cancel() {
            
        }

        @Override
        protected void delete(List<AdUser> items) {
            try {
                adUserFacade.delete(items.get(0));
                
            } catch (Exception e) {
                JsfUtils.messageError(null, e.getMessage(), null);
            }

            JsfUtils.messageInfo(null, "Usuario eliminado correctamente.", null);
        }
        
        
        
        

       

    };

    public DataView<AdUser> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(DataView<AdUser> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

//    private DataList<AdUserRoles> listaUsuarioRoles = new DataList<AdUserRoles>() {
//        @Override
//        protected void initialize() {
//            System.out.println("initialize DataList AdUserRoles");
//        }
//
//        @Override
//        public List<AdUserRoles> loadDataList() {
//            return adUserRolesFacade.findByAdUser(listaUsuarios.getActiveItem());
//        }
//
//        @Override
//        protected AdUserRoles create() {
//            System.out.println("create AdUserRoles");
//            return new AdUserRoles();
//        }
//
//        @Override
//        protected AdUserRoles edit(AdUserRoles item) {
//            System.out.println("edit AdUserRoles: " + item);
//            return item;
//        }
//
//        @Override
//        protected AdUserRoles save(AdUserRoles item) {
//            System.out.println("save AdUserRoles: " + item);
//            try {
//                adUserRolesFacade.save(item, listaUsuarios.getActiveItem());
//            } catch (Exception e) {
//                JsfUtils.messageError(null, e.getMessage(), null);
//                return null;
//            }
//
//            JsfUtils.messageInfo(null, "Rol asignado correctamente.", null);
//
//            return item;
//        }
//
//        @Override
//        protected void delete(List<AdUserRoles> items) {
//            System.out.println("delete AdUserRoles: " + items);
//            try {
//                adUserRolesFacade.delete(items);
//            } catch (Exception e) {
//                JsfUtils.messageError(null, e.getMessage(), null);
//                return;
//            }
//
//            JsfUtils.messageInfo(null, "Rol eliminado correctamente.", null);
//        }
//
//        @Override
//        protected void cancel() {
//            System.out.println("cancel AdUserRoles");
//        }
//
//    };
//
//    public DataList<AdUserRoles> getListaUsuarioRoles() {
//        return listaUsuarioRoles;
//    }
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
