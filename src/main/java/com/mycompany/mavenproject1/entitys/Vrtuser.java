/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1.entitys;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Дмитрий
 */
@Entity
@Table(name = "vrtuser")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vrtuser.findAll", query = "SELECT v FROM Vrtuser v")
    , @NamedQuery(name = "Vrtuser.findById", query = "SELECT v FROM Vrtuser v WHERE v.id = :id")
    
    , @NamedQuery(name = "Vrtuser.findByFirstname", query = "SELECT v FROM Vrtuser v WHERE v.firstname = :firstname")
    , @NamedQuery(name = "Vrtuser.findByLastname", query = "SELECT v FROM Vrtuser v WHERE v.lastname = :lastname")
    , @NamedQuery(name = "Vrtuser.findByMiddlename", query = "SELECT v FROM Vrtuser v WHERE v.middlename = :middlename")
    , @NamedQuery(name = "Vrtuser.findByStaffname", query = "SELECT v FROM Vrtuser v WHERE v.staffname = :staffname")})
public class Vrtuser implements Serializable {

    @Column(name = "USRNAME")
    private String usrname;
    @Column(name = "USRPASSWORD")
    private String usrpassword;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;

    @Column(name = "FIRSTNAME")
    private String firstname;
    @Column(name = "LASTNAME")
    private String lastname;
    @Column(name = "MIDDLENAME")
    private String middlename;
    @Column(name = "STAFFNAME")
    private String staffname;
    @OneToMany(mappedBy = "iduser")
    private List<Vrttempbacket> vrttempbacketList;
    @OneToMany(mappedBy = "iduser")
    private List<Vrtmove> vrtmoveList;
    @OneToMany(mappedBy = "iduser")
    private List<Vrtcellcontents> vrtcellcontentsList;
    @OneToMany(mappedBy = "iduser")
    private List<Vrtbacket> vrtbacketList;
    @OneToMany(mappedBy = "iduser")
    private List<Vrtmetal> vrtmetalList;

    public Vrtuser() {
    }

    public Vrtuser(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getStaffname() {
        return staffname;
    }

    public void setStaffname(String staffname) {
        this.staffname = staffname;
    }

    @XmlTransient
    public List<Vrttempbacket> getVrttempbacketList() {
        return vrttempbacketList;
    }

    public void setVrttempbacketList(List<Vrttempbacket> vrttempbacketList) {
        this.vrttempbacketList = vrttempbacketList;
    }

    @XmlTransient
    public List<Vrtmove> getVrtmoveList() {
        return vrtmoveList;
    }

    public void setVrtmoveList(List<Vrtmove> vrtmoveList) {
        this.vrtmoveList = vrtmoveList;
    }

    @XmlTransient
    public List<Vrtcellcontents> getVrtcellcontentsList() {
        return vrtcellcontentsList;
    }

    public void setVrtcellcontentsList(List<Vrtcellcontents> vrtcellcontentsList) {
        this.vrtcellcontentsList = vrtcellcontentsList;
    }

    @XmlTransient
    public List<Vrtbacket> getVrtbacketList() {
        return vrtbacketList;
    }

    public void setVrtbacketList(List<Vrtbacket> vrtbacketList) {
        this.vrtbacketList = vrtbacketList;
    }

    @XmlTransient
    public List<Vrtmetal> getVrtmetalList() {
        return vrtmetalList;
    }

    public void setVrtmetalList(List<Vrtmetal> vrtmetalList) {
        this.vrtmetalList = vrtmetalList;
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
        if (!(object instanceof Vrtuser)) {
            return false;
        }
        Vrtuser other = (Vrtuser) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.mavenproject1.entitys.Vrtuser[ id=" + id + " ]";
    }

    public String getUsrname() {
        return usrname;
    }

    public void setUsrname(String usrname) {
        this.usrname = usrname;
    }

    public String getUsrpassword() {
        return usrpassword;
    }

    public void setUsrpassword(String usrpassword) {
        this.usrpassword = usrpassword;
    }
    
}
