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
    
    protected List<E> filteredValues;
    
    private String searchKey;

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
        
        if( viewTypeActive ==  DataViewType.ROW){
            return;
        }
        
        setViewTypeActive(viewTypeActive);
        
         setEnabledCreate(true);
        setEnabledEdit(false);//
        setEnabledSave(false);//
        setEnabledDelete(false);
        setEnabledCancel(false);
        
        load();
        
        
    }

    public List<E> getFilteredValues() {
        return filteredValues;
    }

    public void setFilteredValues(List<E> filteredValues) {
        this.filteredValues = filteredValues;
    }

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }
    
    public void searchByName() {
        
        System.out.println("searchByName: "+searchKey);

        if (value != null && filteredValues != null) {
            if (searchKey != null && !searchKey.isEmpty()) {

                value = new ArrayList<>();
                
                System.out.println("searchByName filteredValues: "+filteredValues);
                value = filterGrid(searchKey,filteredValues);
                
                /*
                for (E part : filteredPartners) {
                    if (StringUtils.containsIgnoreCase(part.getName(), searchKey)) {
                        partners.add(part);
                    }
                }*/
                
            } else if (searchKey != null && searchKey.isEmpty()) {
                System.out.println("searchByName searchKey vacio: "+searchKey);
                value = new ArrayList<>();
                value.addAll(filteredValues);
            }
        }
    }
    
    protected List<E> filterGrid(String searchKey,List<E> filteredValues) {
        throw new UnsupportedOperationException();
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
        setFilteredValues(getValue());
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
        //JsfUtils.messageInfo(null, ((E) event.getObject()).getId() +" selected", null);
        setViewTypeActive(DataViewType.ROW);
        setEnabledEdit(true);
        setEnabledDelete(true);
        rowSelected(selectedItem);
    }
    
    public void onRowGridSelect(ActionEvent action) {
        E selected = (E) action.getComponent().getAttributes().get("rowGrid");
        setSelectedItem(selected);
        
        setViewTypeActive(DataViewType.ROW);
        setEnabledEdit(true);
        setEnabledDelete(true);
        rowSelected(selectedItem);
    }
    
    protected void rowSelected(E item) {
        
    } 
    
    public final void actionCreate(ActionEvent action) {
        System.out.println("actionCreate selectedItem: " + action);
        setSelectedItem(create());

        
        setViewTypeActive(DataViewType.ROW);
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
    
    public final void actionEdit(ActionEvent action) {
        
        edit(selectedItem);
        
        setEnabledCreate(false);
        setEnabledEdit(false);
        setEnabledSave(true);//activo guardar
        setEnabledDelete(false);//
        setEnabledCancel(true); //activo cancelar

    }
    
     protected E edit(E item) {
        return item;
    }
     
     public final void actionDelete(ActionEvent action) {

        List<E> deleteList = new ArrayList<E>();
        deleteList.add(getSelectedItem());
        delete(deleteList);
        load();
        setViewTypeActive(DataViewType.TABLE);

    }

    protected void delete(List<E> items) {
        throw new UnsupportedOperationException();
    }
    
    

    public final void actionSave(ActionEvent action) {

        E newItem = save(this.selectedItem);
        load();
    }

    protected E save(E item) {
        return item;
    }
    
    public final void actionCancel(ActionEvent action) {
        setEnabledCreate(true);
        setEnabledEdit(true);
        setEnabledDelete(true);
         setEnabledSave(false);
        setEnabledCancel(false);

        
        cancel();
        load();
        
        if( getSelectedItem().getId() == null ){
            
            if( getViewTypesAvailable() ==null){
                return;
            }
            
            setViewTypeActive(getViewTypesAvailable().get(0));
        }else{
            setViewTypeActive(DataViewType.ROW);
        }
        
    }

    protected void cancel() {
        throw new UnsupportedOperationException();
    }
    
  

}
