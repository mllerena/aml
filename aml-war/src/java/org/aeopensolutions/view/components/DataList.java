package org.aeopensolutions.view.components;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;
import org.aeopensolutions.model.entities.AbstractEntityModel;
import org.aeopensolutions.view.utils.JsfUtils;

public abstract class DataList<E extends AbstractEntityModel> {

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

    protected List<E> dataTable;

    protected List<E> selectItems;

    protected E activeItem;

    protected List<E> modifiedItems;

    private String updateComponents;

    public DataList() {
        init();
    }

    public void init() {
        setEnabledCreate(true);
        setEnabledEdit(false);//
        setEnabledSave(false);//
        setEnabledDelete(false);
        setEnabledCancel(false);
        setSelectItems(new ArrayList<E>());
        setModifiedItems(new ArrayList<E>());
        System.out.println("initialize DataList.. ");
        initialize();
    }

    protected abstract void initialize();

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

    public List<E> getDataTable() {
        return dataTable;
    }

    public void setDataTable(List<E> dataTable) {
        this.dataTable = dataTable;
    }

    public List<E> getSelectItems() {
        return selectItems;
    }

    public void setSelectItems(List<E> selectItems) {
        this.selectItems = selectItems;
    }

    public E getActiveItem() {
        return activeItem;
    }

    public void setActiveItem(E activeItem) {
        this.activeItem = activeItem;
    }

    public List<E> getModifiedItems() {
        return modifiedItems;
    }

    public void setModifiedItems(List<E> modifiedItems) {
        this.modifiedItems = modifiedItems;
    }

    public void put(E dataReg, List<E> items) {
        // lista actual de registros
        List<E> col = items;
        // buscar el registro a insertar
        int posicion = col.indexOf(dataReg);
        // (ltw) si no se encuentra el elemento, se lo agrega
        if (posicion == -1) {
            col.add(dataReg);
        } else {
            col.set(posicion, dataReg);
        }
    }

    public DataList<E> load() {

        setDataTable(loadDataList());
        return this;
    }

    public DataList<E> clear() {
        System.out.println("esta entrando a clear()");
        setDataTable(new ArrayList<E>());
        setSelectItems(new ArrayList<E>());
        setModifiedItems(new ArrayList<E>());
        return this;
    }

    public abstract List<E> loadDataList();

    public int getRowCountTotal() {
        return getDataTable() != null ? getDataTable().size() : 0;
    }

    public int getRowSelectCountTotal() {
        System.out.println("getRowSelectCountTotal: " + getSelectItems());
        return getSelectItems() != null ? getSelectItems().size() : 0;
    }

    public int getRowModifiedCountTotal() {
        return getModifiedItems() != null ? getModifiedItems().size() : 0;
    }

    public void onCellEdit(E row, String listId) {

        System.out.println("onCellEdit entity : " + row);

        row.setChange(true);
        put(row, getDataTable());

        if (modifiedItems == null) {
            modifiedItems = new ArrayList<E>();
        }

        put(row, getModifiedItems());

        setEnabledSave(true);
        setEnabledCancel(true);

        String js = "onCellEditEvent" + listId + "();";

        JsfUtils.executeJS(js);

    }

    public void onRowEditCancel(E row) {
        System.out.println("onRowEditCancel : " + row);
    }

    public void onRowSelect() {

        System.out.println("onRowSelect items: " + getSelectItems());

        if (getSelectItems() != null && getSelectItems().size() == 1) {
            setActiveItem(getSelectItems().get(0));

            /*getActiveItem().setChange(true);
			put(getActiveItem());*/
            setEnabledEdit(true);
            setEnabledCancel(true);
            setEnabledProcess(true);
        } else {
            setActiveItem(null);
            setEnabledEdit(false);
            setEnabledCancel(false);
            setEnabledProcess(false);
        }

        if (getSelectItems() != null && getSelectItems().size() >= 1) {
            setEnabledDelete(true);
            setEnabledCancel(true);
        } else {
            setEnabledDelete(false);
            setEnabledCancel(false);
        }

        select(getSelectItems());

    }

    protected void select(List<E> items) {
        return;
    }

    public void onRowUnselect() {

        System.out.println("onRowUnselect items: " + getSelectItems());

        if (getSelectItems() != null && getSelectItems().size() == 1) {
            setActiveItem(getSelectItems().get(0));
            setEnabledEdit(true);
            setEnabledCancel(true);
            setEnabledProcess(true);
        }

        if (getSelectItems() != null && getSelectItems().size() == 0) {
            setActiveItem(null);
            setEnabledEdit(false);
            setEnabledCancel(false);
            setEnabledProcess(false);
        }

        if (getSelectItems() != null && getSelectItems().size() >= 1) {
            setEnabledDelete(true);
            setEnabledCancel(true);
        } else {
            setEnabledDelete(false);
            setEnabledCancel(false);
        }

        unSelect();

    }

    protected void unSelect() {
        return;
    }

    public final void actionCreate(ActionEvent action) {
        System.out.println("actionCreate datalist: " + action);
        setActiveItem(create());
        //init();

        setEnabledCreate(false);
        setEnabledEdit(false);

        setEnabledSave(true);//activo guardar
        setEnabledDelete(false);//este se debe activar cuando el item existe
        setEnabledCancel(true); //activo cancelar

        setSelectItems(new ArrayList<E>());
        setModifiedItems(new ArrayList<E>());
        initialize();

        clear();
        load();
        
        createLast();

    }

    protected E create() {
        throw new UnsupportedOperationException();
    }
    
    protected void createLast() {
    }
    
    public final void actionRefresh(ActionEvent action) {
        System.out.println("actionRefresh datalist: " + action);
        load();
    }

    public final void actionEdit(ActionEvent action) {
        System.out.println("actionEdit datalist: " + getSelectItems());
        
        edit(activeItem);
        
        setEnabledCreate(false);
        setEnabledEdit(true);
        setEnabledSave(true);//activo guardar
        setEnabledDelete(true);//este se debe activar cuando el item existe
        setEnabledCancel(true); //activo cancelar

        setSelectItems(new ArrayList<E>());
        setModifiedItems(new ArrayList<E>());
        initialize();

        editLast(activeItem);

    }

    public final void actionEdit(E row, String listId) {
        setActiveItem(row);
        System.out.println("actionEdit listener datalist: " + activeItem);
        
        edit(activeItem);
        
        setEnabledCreate(false);
        setEnabledEdit(true);
        setEnabledSave(true);//activo guardar
        setEnabledDelete(true);//este se debe activar cuando el item existe
        setEnabledCancel(true); //activo cancelar

        setSelectItems(new ArrayList<E>());
        setModifiedItems(new ArrayList<E>());
        initialize();
        
        

        String js = "onEditEvent" + listId + "();";

        JsfUtils.executeJS(js);

        editLast(activeItem);
        

    }

    protected E edit(E item) {
        return item;
    }
    
    protected E editLast(E item) {
        return item;
    }

    /*
	public final void actionDelete(String modal) { 
		System.out.println("actionDelete datalist: " + getSelectItems());
		delete(getSelectItems());
		init();
		clear();
		load();
		
		JsfUtils.executeJS("PF('wvEdit_" + modal+  "').hide();");
		
	}
     */
    public final void actionDelete(ActionEvent action) {
        System.out.println("actionDelete datalist: " + getSelectItems());

        List<E> deleteList = new ArrayList<E>();
        deleteList.add(getActiveItem());
        delete(deleteList);
        init();
        clear();
        load();
        
        deleteLast(deleteList);

    }

    protected void delete(List<E> items) {
        throw new UnsupportedOperationException();
    }
    
    protected void deleteLast(List<E> items) {
    }

    public final void actionSave(ActionEvent action) {
        System.out.println("actionSave activeItem: " + this.activeItem);
        // save(getSelectItems());

        E newItem = save(this.activeItem);

        if (newItem == null) {
            return;
        }

        /*
		 * if (newItem.getId() != null) { // definirlo como seleccionado
		 * put(newItem); setActiveItem(newItem); }
         */
        init();
        clear();
        load();
        
        saveLast(this.activeItem);

        //JsfUtils.executeJS("PF('wvEdit_" + modal+  "').hide();");
    }

    protected E save(E item) {
        return item;
    }
    
    protected E saveLast(E item) {
        return item;
    }

    public final void actionMultiSave(ActionEvent action) {
        System.out.println("actionMultiSave datalist: " + getModifiedItems());
        multiSave(getModifiedItems());
        init();
        clear();
        load();
        multiSaveLast(getModifiedItems());
    }

    protected void multiSave(List<E> items) {
        throw new UnsupportedOperationException();
    }
    
    protected void multiSaveLast(List<E> items) {
        throw new UnsupportedOperationException();
    }

    public final void actionCancel(ActionEvent action) {
        System.out.println("actionCancel datalist: " + getSelectItems());
        
        cancel();
        
        init();
        clear();
        load();
        
        
        
        cancelLast();
        
        //JsfUtils.executeJS("PF('wvEdit_" + modal+  "').hide();");
    }

    protected void cancel() {
        throw new UnsupportedOperationException();
    }
    
    protected void cancelLast() {
    }

    public final void actionProcess(ActionEvent action) {
        System.out.println("actionProcess datalist: " + getSelectItems());
        process(getSelectItems());
        init();
        clear();
        load();

    }

    protected void process(List<E> items) {
        throw new UnsupportedOperationException();
    }

    public final void actionGenerate(ActionEvent action) {
        System.out.println("actionGenerate datalist: " + getSelectItems());
        generate(getSelectItems());
        init();
        clear();
        load();
    }

    protected void generate(List<E> items) {
        throw new UnsupportedOperationException();
    }

    public String getUpdateComponents() {
        return updateComponents;
    }

    public void setUpdateComponents(String updateComponents) {
        this.updateComponents = updateComponents;
    }

}
