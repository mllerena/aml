/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.aeopensolutions.model.entities;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Convert;
import javax.persistence.FetchType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.aeopensolutions.model.converters.YesNoConverter;
import org.aeopensolutions.model.enums.YesNo;

/**
 *
 * @author mllerena
 */
@Entity
@Table(name = "ad_role")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AdRole.findAll", query = "SELECT a FROM AdRole a"),
    @NamedQuery(name = "AdRole.findByAdRoleId", query = "SELECT a FROM AdRole a WHERE a.id = :id"),
    @NamedQuery(name = "AdRole.findByIsactive", query = "SELECT a FROM AdRole a WHERE a.isactive = :isactive"),
    @NamedQuery(name = "AdRole.findByCreated", query = "SELECT a FROM AdRole a WHERE a.created = :created"),
    @NamedQuery(name = "AdRole.findByCreatedby", query = "SELECT a FROM AdRole a WHERE a.createdby = :createdby"),
    @NamedQuery(name = "AdRole.findByUpdated", query = "SELECT a FROM AdRole a WHERE a.updated = :updated"),
    @NamedQuery(name = "AdRole.findByName", query = "SELECT a FROM AdRole a WHERE a.name = :name and a.isactive = :isactive"),
    @NamedQuery(name = "AdRole.findByUpdatedby", query = "SELECT a FROM AdRole a WHERE a.updatedby = :updatedby"),
    @NamedQuery(name = "AdRole.findByDescription", query = "SELECT a FROM AdRole a WHERE a.description = :description"),
    @NamedQuery(name = "AdRole.findByUserlevel", query = "SELECT a FROM AdRole a WHERE a.userlevel = :userlevel"),
    @NamedQuery(name = "AdRole.findByClientlist", query = "SELECT a FROM AdRole a WHERE a.clientlist = :clientlist"),
    @NamedQuery(name = "AdRole.findByOrglist", query = "SELECT a FROM AdRole a WHERE a.orglist = :orglist"),
    @NamedQuery(name = "AdRole.findByAmtapproval", query = "SELECT a FROM AdRole a WHERE a.amtapproval = :amtapproval"),
    @NamedQuery(name = "AdRole.findByIsmanual", query = "SELECT a FROM AdRole a WHERE a.ismanual = :ismanual"),
    @NamedQuery(name = "AdRole.findByProcessing", query = "SELECT a FROM AdRole a WHERE a.processing = :processing"),
    @NamedQuery(name = "AdRole.findByIsClientAdmin", query = "SELECT a FROM AdRole a WHERE a.isClientAdmin = :isClientAdmin")})
public class AdRole extends AbstractEntityModel implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @SequenceGenerator(name = "AdRole_seq",
            sequenceName = "ad_role_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AdRole_seq")
    @Column(name = "id")
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
    @Size(min = 1, max = 60)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "updatedby")
    private String updatedby;
    @Size(max = 255)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "userlevel")
    private String userlevel;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2000)
    @Column(name = "clientlist")
    private String clientlist;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2000)
    @Column(name = "orglist")
    private String orglist;
    @Column(name = "amtapproval")
    private BigInteger amtapproval;
    @Column(name = "ismanual")
    private Character ismanual;
    @Column(name = "processing")
    private Character processing;
    @Basic(optional = false)
    @NotNull
    @Column(name = "is_client_admin")
    private Character isClientAdmin;

    @OneToMany(fetch = javax.persistence.FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "adRoleId")
    private List<AdUserRoles> adUserRolesList; 

    public AdRole() {
    }
    
    public AdRole(BigInteger id) {
        this.id = id;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUpdatedby() {
        return updatedby;
    }

    public void setUpdatedby(String updatedby) {
        this.updatedby = updatedby;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUserlevel() {
        return userlevel;
    }

    public void setUserlevel(String userlevel) {
        this.userlevel = userlevel;
    }

    public String getClientlist() {
        return clientlist;
    }

    public void setClientlist(String clientlist) {
        this.clientlist = clientlist;
    }

    public String getOrglist() {
        return orglist;
    }

    public void setOrglist(String orglist) {
        this.orglist = orglist;
    }

    public BigInteger getAmtapproval() {
        return amtapproval;
    }

    public void setAmtapproval(BigInteger amtapproval) {
        this.amtapproval = amtapproval;
    }

    public Character getIsmanual() {
        return ismanual;
    }

    public void setIsmanual(Character ismanual) {
        this.ismanual = ismanual;
    }

    public Character getProcessing() {
        return processing;
    }

    public void setProcessing(Character processing) {
        this.processing = processing;
    }

    public Character getIsClientAdmin() {
        return isClientAdmin;
    }

    public void setIsClientAdmin(Character isClientAdmin) {
        this.isClientAdmin = isClientAdmin;
    }

    @XmlTransient
    public List<AdUserRoles> getAdUserRolesList() {
        return adUserRolesList;
    }

    public void setAdUserRolesList(List<AdUserRoles> adUserRolesList) {
        this.adUserRolesList = adUserRolesList;
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
        if (!(object instanceof AdRole)) {
            return false;
        }
        AdRole other = (AdRole) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true; 
    }

    @Override
    public String toString() {
        return "org.aeopensolutions.model.entities.AdRole[ id=" + id + " ]";
    }

}
