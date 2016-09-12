package org.aeopensolutions.model.entities;

import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Convert;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import org.aeopensolutions.model.converters.YesNoConverter;

import org.aeopensolutions.model.enums.YesNo;
import org.aeopensolutions.model.listeners.EntityModelListener;

@MappedSuperclass
@EntityListeners(value = EntityModelListener.class)
public abstract class AbstractEntityModel {

    public abstract BigInteger getId();

    public abstract void setId(BigInteger id);

    @Basic(optional = false)
    @Column(name = "isactive")
    @Convert(converter = YesNoConverter.class)
    @NotNull
    private YesNo isactive;

    @Basic(optional = false)
    @Column(name = "created")
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date created;

    @Basic(optional = false)
    @Column(name = "createdby")
    @NotNull
    private String createdby;

    @Basic(optional = false)
    @Column(name = "updated")
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date updated;

    @Basic(optional = false)
    @Column(name = "updatedby")
    @NotNull
    private String updatedby;

    public YesNo getIsactive() {
        return isactive;
    }

    public void setIsactive(YesNo isactive) {
        this.isactive = isactive;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getCreatedby() {
        return createdby;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public String getUpdatedby() {
        return updatedby;
    }

    public void setUpdatedby(String updatedby) {
        this.updatedby = updatedby;
    }
    
    

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
