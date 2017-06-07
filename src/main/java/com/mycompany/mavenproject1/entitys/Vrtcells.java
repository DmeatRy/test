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
@Table(name = "vrtcells")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vrtcells.findAll", query = "SELECT v FROM Vrtcells v")
    , @NamedQuery(name = "Vrtcells.findById", query = "SELECT v FROM Vrtcells v WHERE v.id = :id")
    , @NamedQuery(name = "Vrtcells.findByName", query = "SELECT v FROM Vrtcells v WHERE v.name = :name")
    , @NamedQuery(name = "Vrtcells.findByCode", query = "SELECT v FROM Vrtcells v WHERE v.code = :code")
    , @NamedQuery(name = "Vrtcells.findByLocation", query = "SELECT v FROM Vrtcells v WHERE v.location = :location")
    , @NamedQuery(name = "Vrtcells.findByNum", query = "SELECT v FROM Vrtcells v WHERE v.num = :num")})
public class Vrtcells implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "CODE")
    private String code;
    @Column(name = "LOCATION")
    private String location;
    @Column(name = "NUM")
    private Integer num;
    @OneToMany(mappedBy = "idcells")
    private List<Vrtcellcontents> vrtcellcontentsList;

    public Vrtcells() {
    }

    public Vrtcells(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    @XmlTransient
    public List<Vrtcellcontents> getVrtcellcontentsList() {
        return vrtcellcontentsList;
    }

    public void setVrtcellcontentsList(List<Vrtcellcontents> vrtcellcontentsList) {
        this.vrtcellcontentsList = vrtcellcontentsList;
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
        if (!(object instanceof Vrtcells)) {
            return false;
        }
        Vrtcells other = (Vrtcells) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.mavenproject1.entitys.Vrtcells[ id=" + id + " ]";
    }
    
}
