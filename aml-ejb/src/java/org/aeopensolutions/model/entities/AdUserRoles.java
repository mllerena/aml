/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.aeopensolutions.model.entities;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import org.aeopensolutions.model.converters.YesNoConverter;
import org.aeopensolutions.model.enums.YesNo;

/**
 *
 * @author mllerena
 */
@Entity
@Table(name = "ad_user_roles")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AdUserRoles.findAll", query = "SELECT a FROM AdUserRoles a"),
    @NamedQuery(name = "AdUserRoles.findByAdUserRolesId", query = "SELECT a FROM AdUserRoles a WHERE a.id = :id"),
    @NamedQuery(name = "AdUserRoles.findByIsactive", query = "SELECT a FROM AdUserRoles a WHERE a.isactive = :isactive"),
    @NamedQuery(name = "AdUserRoles.findByCreated", query = "SELECT a FROM AdUserRoles a WHERE a.created = :created"),
    @NamedQuery(name = "AdUserRoles.findByCreatedby", query = "SELECT a FROM AdUserRoles a WHERE a.createdby = :createdby"),
    @NamedQuery(name = "AdUserRoles.findByUpdated", query = "SELECT a FROM AdUserRoles a WHERE a.updated = :updated"),
    @NamedQuery(name = "AdUserRoles.findByUpdatedby", query = "SELECT a FROM AdUserRoles a WHERE a.updatedby = :updatedby"),
    @NamedQuery(name = "AdUserRoles.findByIsRoleAdmin", query = "SELECT a FROM AdUserRoles a WHERE a.isRoleAdmin = :isRoleAdmin"),
    @NamedQuery(name = "AdUserRoles.findByAdUser", query = "SELECT a FROM AdUserRoles a WHERE a.adUserId = :adUserId and a.isactive = :isactive"),
    @NamedQuery(name = "AdUserRoles.findByAdUserAndAdRole", query = "SELECT a FROM AdUserRoles a WHERE a.adRoleId = :adRoleId and a.adUserId = :adUserId and a.isactive = :isactive")
})
public class AdUserRoles extends AbstractEntityModel implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "AdUserRoles_seq",
            sequenceName = "ad_user_roles_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AdUserRoles_seq")
    private BigInteger id;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "isactive")
    @Convert(converter = YesNoConverter.class)
    private YesNo isactive;

    @Basic(optional = false)
    @NotNull
    @Column(name = "created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "createdby")
    private String createdby;
    @Basic(optional = false)
    @NotNull
    @Column(name = "updated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updated;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "updatedby")
    private String updatedby;
    
    @Basic(optional = false)
    @Column(name = "is_role_admin")
    private Character isRoleAdmin;
    
    @JoinColumn(name = "ad_role_id", referencedColumnName = "id")
    @ManyToOne(fetch=javax.persistence.FetchType.EAGER,optional = false)
    private AdRole adRoleId; 
    
    @JoinColumn(name = "ad_user_id", referencedColumnName = "id")
    @ManyToOne(fetch = javax.persistence.FetchType.EAGER, optional = false)
    private AdUser adUserId;

    public AdUserRoles() {
    }

    public AdUserRoles(BigInteger id) {
        this.id = id;
    }
    
    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public BigInteger getAdUserRolesId() {
        return id;
    }

    public void setAdUserRolesId(BigInteger id) {
        this.id = id;
    }

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

    public Character getIsRoleAdmin() {
        return isRoleAdmin;
    }

    public void setIsRoleAdmin(Character isRoleAdmin) {
        this.isRoleAdmin = isRoleAdmin;
    }

    public AdRole getAdRoleId() { 
        return adRoleId;
    }

    public void setAdRoleId(AdRole adRoleId) {
        this.adRoleId = adRoleId;
    }

    public AdUser getAdUserId() {
        return adUserId;
    }

    public void setAdUserId(AdUser adUserId) {
        this.adUserId = adUserId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AdUserRoles)) {
            return false;
        }
        AdUserRoles other = (AdUserRoles) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.aeopensolutions.model.entities.AdUserRoles[ id=" + id + " ]";
    }


}
