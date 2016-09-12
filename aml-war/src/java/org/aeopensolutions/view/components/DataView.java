package org.aeopensolutions.view.components;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;
import org.aeopensolutions.model.entities.AbstractEntityModel;
import org.aeopensolutions.view.utils.JsfUtils;
import org.primefaces.event.SelectEvent;

public abstract class DataView<E extends AbstractEntityModel> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private boolean enabledRefresh;

    private boolean enabledCreate;

    private boolean enabledEdit;

    private boolean enabledSave;

    private boolean enabledDelete;

    private boolean enabledCancel;

    private boolean enabledProcess;

    private boolean enabledGenerate;

    protected List<E> value;

    protected E selectedItem;
    
    private List<DataViewType> viewTypesAvailable;
    
    private DataViewType viewTypeActive;

    public DataView() {
        init();
    }

    public void init() {
        setEnabledCreate(true);
        setEnabledEdit(false);//
        setEnabledSave(false);//
        setEnabledDelete(false);
        setEnabledCancel(false);
        
        viewTypeActive = DataViewType.TABLE;
        
        initialize();
    }

    protected abstract void initialize();

    public DataViewType getViewTypeActive() {
        return viewTypeActive;
    }

    public void setViewTypeActive(DataViewType viewTypeActive) {
        this.viewTypeActive = viewTypeActive;
    }

    
    
    
    public List<DataViewType> getViewTypesAvailable() {
        if( viewTypesAvailable == null ){
            viewTypesAvailable = viewTypes();
        }
        return viewTypesAvailable;
    }

    public void setViewTypesAvailable(List<DataViewType> viewTypesAvailable) {
        this.viewTypesAvailable = viewTypesAvailable;
    }
    
    public abstract List<DataViewType> viewTypes();
    
    
    public final void actionSelectionViewType(ActionEvent action) {
        System.out.println("actionSelectionViewType: " + action);
        
        DataViewType viewTypeActive = (DataViewType) action.getComponent().getAttributes().get("viewType");
        System.out.println("viewTypeActive selected: " + viewTypeActive);
        
        setViewTypeActive(viewTypeActive);
        
        
    }

    public boolean isEnabledRefresh() {
        return enabledRefresh;
    }

    public void setEnabledRefresh(boolean enabledRefresh) {
        this.enabledRefresh = enabledRefresh;
    }

    public boolean isEnabledCreate() {
        return enabledCreate;
    }

    public void setEnabledCreate(boolean enabledCreate) {
        this.enabledCreate = enabledCreate;
    }

    public boolean isEnabledEdit() {
        return enabledEdit;
    }

    public void setEnabledEdit(boolean enabledEdit) {
        this.enabledEdit = enabledEdit;
    }

    public boolean isEnabledSave() {
        return enabledSave;
    }

    public void setEnabledSave(boolean enabledSave) {
        this.enabledSave = enabledSave;
    }

    public boolean isEnabledDelete() {
        return enabledDelete;
    }

    public void setEnabledDelete(boolean enabledDelete) {
        this.enabledDelete = enabledDelete;
    }

    public boolean isEnabledCancel() {
        return enabledCancel;
    }

    public void setEnabledCancel(boolean enabledCancel) {
        this.enabledCancel = enabledCancel;
    }

    public boolean isEnabledProcess() {
        return enabledProcess;
    }

    public void setEnabledProcess(boolean enabledProcess) {
        this.enabledProcess = enabledProcess;
    }

    public boolean isEnabledGenerate() {
        return enabledGenerate;
    }

    public void setEnabledGenerate(boolean enabledGenerate) {
        this.enabledGenerate = enabledGenerate;
    }

    public List<E> getValue() {
        return value;
    }

    public void setValue(List<E> value) {
        this.value = value;
    }

    public abstract List<E> findAll();
    
    public void load() {
        setValue(findAll());
    }

    public int getRowCountTotal() {
        return getValue()!= null ? getValue().size() : 0;
    }

    public E getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(E selectedItem) {
        this.selectedItem = selectedItem;
    }
    
    public void onRowSelect(SelectEvent event) {
        //FacesMessage msg = new FacesMessage("Row Selected", ((E) event.getObject()).getId());
        //FacesContext.getCurrentInstance().addMessage(null, msg);
        JsfUtils.messageInfo(null, ((E) event.getObject()).getId() +" selected", null);
        setViewTypeActive(DataViewType.ROW);
    }
    
    

    public final void actionCreate(ActionEvent action) {
        System.out.println("actionCreate selectedItem: " + action);
        setSelectedItem(create());

        setEnabledCreate(false);
        setEnabledEdit(false);

        setEnabledSave(true);//activo guardar
        setEnabledDelete(false);//este se debe activar cuando el item existe
        setEnabledCancel(true); //activo cancelar

        initialize();

    }

    protected E create() {
        throw new UnsupportedOperationException();
    }

    public final void actionSave(ActionEvent action) {
        
        System.out.println("actionSave selectedItem: " + this.selectedItem);

        E newItem = save(this.selectedItem);
        
         initialize();
        
    }

    protected E save(E item) {
        return item;
    }
    
  

}
