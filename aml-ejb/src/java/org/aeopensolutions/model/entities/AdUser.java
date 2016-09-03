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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.aeopensolutions.model.converters.YesNoConverter;
import org.aeopensolutions.model.enums.YesNo;

/**
 *
 * @author mllerena
 */
@Entity
@Table(name = "ad_user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AdUser.findAll", query = "SELECT a FROM AdUser a"),
    @NamedQuery(name = "AdUser.findByAdUserId", query = "SELECT a FROM AdUser a WHERE a.id = :id"),
    @NamedQuery(name = "AdUser.findByIsactive", query = "SELECT a FROM AdUser a WHERE a.isactive = :isactive"),
    @NamedQuery(name = "AdUser.findByCreated", query = "SELECT a FROM AdUser a WHERE a.created = :created"),
    @NamedQuery(name = "AdUser.findByCreatedby", query = "SELECT a FROM AdUser a WHERE a.createdby = :createdby"),
    @NamedQuery(name = "AdUser.findByUpdated", query = "SELECT a FROM AdUser a WHERE a.updated = :updated"),
    @NamedQuery(name = "AdUser.findByUpdatedby", query = "SELECT a FROM AdUser a WHERE a.updatedby = :updatedby"),
    @NamedQuery(name = "AdUser.findByName", query = "SELECT a FROM AdUser a WHERE a.name = :name"),
    @NamedQuery(name = "AdUser.findByDescription", query = "SELECT a FROM AdUser a WHERE a.description = :description"),
    @NamedQuery(name = "AdUser.findByPassword", query = "SELECT a FROM AdUser a WHERE a.password = :password"),
    @NamedQuery(name = "AdUser.findByEmail", query = "SELECT a FROM AdUser a WHERE a.email = :email"),
    @NamedQuery(name = "AdUser.findByProcessing", query = "SELECT a FROM AdUser a WHERE a.processing = :processing"),
    @NamedQuery(name = "AdUser.findByEmailuser", query = "SELECT a FROM AdUser a WHERE a.emailuser = :emailuser"),
    @NamedQuery(name = "AdUser.findByEmailuserpw", query = "SELECT a FROM AdUser a WHERE a.emailuserpw = :emailuserpw"),
    @NamedQuery(name = "AdUser.findByTitle", query = "SELECT a FROM AdUser a WHERE a.title = :title"),
    @NamedQuery(name = "AdUser.findByComments", query = "SELECT a FROM AdUser a WHERE a.comments = :comments"),
    @NamedQuery(name = "AdUser.findByPhone", query = "SELECT a FROM AdUser a WHERE a.phone = :phone"),
    @NamedQuery(name = "AdUser.findByPhone2", query = "SELECT a FROM AdUser a WHERE a.phone2 = :phone2"),
    @NamedQuery(name = "AdUser.findByFax", query = "SELECT a FROM AdUser a WHERE a.fax = :fax"),
    @NamedQuery(name = "AdUser.findByLastcontact", query = "SELECT a FROM AdUser a WHERE a.lastcontact = :lastcontact"),
    @NamedQuery(name = "AdUser.findByLastresult", query = "SELECT a FROM AdUser a WHERE a.lastresult = :lastresult"),
    @NamedQuery(name = "AdUser.findByBirthday", query = "SELECT a FROM AdUser a WHERE a.birthday = :birthday"),
    @NamedQuery(name = "AdUser.findByFirstname", query = "SELECT a FROM AdUser a WHERE a.firstname = :firstname"),
    @NamedQuery(name = "AdUser.findByLastname", query = "SELECT a FROM AdUser a WHERE a.lastname = :lastname"),
    @NamedQuery(name = "AdUser.findByUsername", query = "SELECT a FROM AdUser a WHERE a.username = :username and a.isactive = :isactive"),
    @NamedQuery(name = "AdUser.findByIslocked", query = "SELECT a FROM AdUser a WHERE a.islocked = :islocked")})
public class AdUser extends AbstractEntityModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "AdUser_seq",
            sequenceName = "ad_user_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AdUser_seq")
    private BigInteger id;

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

    @Basic(optional = false)
    @Column(name = "username")
    @NotNull
    private String username;

    @Column(name = "password")
    @NotNull
    private String password;

    @Basic(optional = false)
    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Column(name = "email")
    private String email;

    @Column(name = "processing")
    private Character processing;

    @Column(name = "emailuser")
    private String emailuser;

    @Column(name = "emailuserpw")
    private String emailuserpw;

    @Column(name = "title")
    private String title;

    @Column(name = "comments")
    private String comments;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation

    @Column(name = "phone")
    private String phone;

    @Column(name = "phone2")
    private String phone2;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation

    @Column(name = "fax")
    private String fax;
    @Column(name = "lastcontact")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastcontact;

    @Column(name = "lastresult")
    private String lastresult;
    @Column(name = "birthday")
    @Temporal(TemporalType.TIMESTAMP)
    private Date birthday;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Basic(optional = false)
    @Column(name = "islocked")
    private Character islocked;

    @JoinColumn(name = "default_ad_role_id", referencedColumnName = "id")
    @ManyToOne(fetch = javax.persistence.FetchType.LAZY)
    private AdRole defaultAdRoleId;

    @JoinColumn(name = "supervisor_id", referencedColumnName = "id")
    @ManyToOne(fetch = javax.persistence.FetchType.LAZY)
    private AdUser supervisorId;

    public AdUser() {
    }

    public AdUser(BigInteger id) {
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

    public String getUpdatedby() {
        return updatedby;
    }

    public void setUpdatedby(String updatedby) {
        this.updatedby = updatedby;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Character getProcessing() {
        return processing;
    }

    public void setProcessing(Character processing) {
        this.processing = processing;
    }

    public String getEmailuser() {
        return emailuser;
    }

    public void setEmailuser(String emailuser) {
        this.emailuser = emailuser;
    }

    public String getEmailuserpw() {
        return emailuserpw;
    }

    public void setEmailuserpw(String emailuserpw) {
        this.emailuserpw = emailuserpw;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public Date getLastcontact() {
        return lastcontact;
    }

    public void setLastcontact(Date lastcontact) {
        this.lastcontact = lastcontact;
    }

    public String getLastresult() {
        return lastresult;
    }

    public void setLastresult(String lastresult) {
        this.lastresult = lastresult;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Character getIslocked() {
        return islocked;
    }

    public void setIslocked(Character islocked) {
        this.islocked = islocked;
    }

    public AdRole getDefaultAdRoleId() {
        return defaultAdRoleId;
    }

    public void setDefaultAdRoleId(AdRole defaultAdRoleId) {
        this.defaultAdRoleId = defaultAdRoleId;
    }

    public AdUser getSupervisorId() {
        return supervisorId;
    }

    public void setSupervisorId(AdUser supervisorId) {
        this.supervisorId = supervisorId;
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
        if (!(object instanceof AdUser)) {
            return false;
        }
        AdUser other = (AdUser) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.aeopensolutions.model.entities.AdUser[ id=" + id + " ]";
    }

}
