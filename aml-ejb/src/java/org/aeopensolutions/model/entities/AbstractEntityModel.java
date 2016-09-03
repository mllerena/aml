package org.aeopensolutions.model.entities;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import org.aeopensolutions.model.enums.YesNo;
import org.aeopensolutions.model.listeners.EntityModelListener;

@MappedSuperclass
@EntityListeners(value = EntityModelListener.class)
public abstract class AbstractEntityModel {

    public abstract BigInteger getId();

    public abstract void setId(BigInteger id);

    public abstract YesNo getIsactive();

    public abstract void setIsactive(YesNo isactive); 

    public abstract Date getCreated();

    public abstract void setCreated(Date created);

    public abstract String getCreatedby();

    public abstract void setCreatedby(String createdby);

    public abstract Date getUpdated();

    public abstract void setUpdated(Date updated);

    public abstract String getUpdatedby();

    public abstract void setUpdatedby(String updatedby);

    @Transient
    private boolean change;

    public boolean isChange() {
        return change;
    }

    public void setChange(boolean change) {
        this.change = change;
    }

    @Transient
    private Date fromDate;
    @Transient
    private Date toDate; 

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

}
