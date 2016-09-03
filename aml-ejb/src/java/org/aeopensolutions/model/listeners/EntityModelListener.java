package org.aeopensolutions.model.listeners;

import java.util.Date;

import javax.ejb.EJB;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.aeopensolutions.model.ejb.services.AdPackageService;
import org.aeopensolutions.model.entities.AbstractEntityModel;
import org.aeopensolutions.model.enums.YesNo;
import org.aeopensolutions.model.utils.SecurityUtils;

public class EntityModelListener {

    @EJB
    private SecurityUtils security;

    @EJB
    private AdPackageService packageService;

    @PrePersist
    public void prePersist(AbstractEntityModel e) {
        //e.setId(packageService.nextSequence(e));
        e.setCreated(new Date());
        e.setCreatedby(security.getCurrentUser());
        e.setUpdated(new Date());
        e.setUpdatedby(security.getCurrentUser());
        e.setIsactive(YesNo.SI);

    }

    @PreUpdate
    public void preUpdate(AbstractEntityModel e) {
        e.setUpdated(new Date());
        e.setUpdatedby(security.getCurrentUser());
    }

}
