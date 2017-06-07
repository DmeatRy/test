/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1.entitys;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import javax.persistence.Basic;
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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Дмитрий
 */
@Entity
@Table(name = "vrtmetal")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vrtmetal.findAll", query = "SELECT v FROM Vrtmetal v")
    , @NamedQuery(name = "Vrtmetal.findById", query = "SELECT v FROM Vrtmetal v WHERE v.id = :id")
    , @NamedQuery(name = "Vrtmetal.findByWeight", query = "SELECT v FROM Vrtmetal v WHERE v.weight = :weight")
    , @NamedQuery(name = "Vrtmetal.findByAct", query = "SELECT v FROM Vrtmetal v WHERE v.act = :act")
    , @NamedQuery(name = "Vrtmetal.findByMaterial", query = "SELECT v FROM Vrtmetal v WHERE v.material = :material")
    , @NamedQuery(name = "Vrtmetal.findByGost", query = "SELECT v FROM Vrtmetal v WHERE v.gost = :gost")
    , @NamedQuery(name = "Vrtmetal.findByLength", query = "SELECT v FROM Vrtmetal v WHERE v.length = :length")
    , @NamedQuery(name = "Vrtmetal.findByWidth", query = "SELECT v FROM Vrtmetal v WHERE v.width = :width")
    , @NamedQuery(name = "Vrtmetal.findByThickness", query = "SELECT v FROM Vrtmetal v WHERE v.thickness = :thickness")
    , @NamedQuery(name = "Vrtmetal.findByHeatnum", query = "SELECT v FROM Vrtmetal v WHERE v.heatnum = :heatnum")
    , @NamedQuery(name = "Vrtmetal.findByExternalid", query = "SELECT v FROM Vrtmetal v WHERE v.externalid = :externalid")
    , @NamedQuery(name = "Vrtmetal.findByNtype", query = "SELECT v FROM Vrtmetal v WHERE v.ntype = :ntype")})
public class Vrtmetal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "WEIGHT")
    private BigInteger weight;
    @Column(name = "ACT")
    private String act;
    @Column(name = "MATERIAL")
    private String material;
    @Column(name = "GOST")
    private String gost;
    @Column(name = "LENGTH")
    private BigInteger length;
    @Column(name = "WIDTH")
    private BigInteger width;
    @Column(name = "THICKNESS")
    private BigInteger thickness;
    @Column(name = "HEATNUM")
    private String heatnum;
    @Column(name = "EXTERNALID")
    private BigInteger externalid;
    @Column(name = "NTYPE")
    private Integer ntype;
    @OneToMany(mappedBy = "idmetal")
    private List<Vrtcellcontents> vrtcellcontentsList;
    @OneToMany(mappedBy = "idmetal")
    private List<Vrtbacket> vrtbacketList;
    @JoinColumn(name = "IDUSER", referencedColumnName = "ID")
    @ManyToOne
    private Vrtuser iduser;

    public Vrtmetal() {
    }

    public Vrtmetal(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigInteger getWeight() {
        return weight;
    }

    public void setWeight(BigInteger weight) {
        this.weight = weight;
    }

    public String getAct() {
        return act;
    }

    public void setAct(String act) {
        this.act = act;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getGost() {
        return gost;
    }

    public void setGost(String gost) {
        this.gost = gost;
    }

    public BigInteger getLength() {
        return length;
    }

    public void setLength(BigInteger length) {
        this.length = length;
    }

    public BigInteger getWidth() {
        return width;
    }

    public void setWidth(BigInteger width) {
        this.width = width;
    }

    public BigInteger getThickness() {
        return thickness;
    }

    public void setThickness(BigInteger thickness) {
        this.thickness = thickness;
    }

    public String getHeatnum() {
        return heatnum;
    }

    public void setHeatnum(String heatnum) {
        this.heatnum = heatnum;
    }

    public BigInteger getExternalid() {
        return externalid;
    }

    public void setExternalid(BigInteger externalid) {
        this.externalid = externalid;
    }

    public Integer getNtype() {
        return ntype;
    }

    public void setNtype(Integer ntype) {
        this.ntype = ntype;
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

    public Vrtuser getIduser() {
        return iduser;
    }

    public void setIduser(Vrtuser iduser) {
        this.iduser = iduser;
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
        if (!(object instanceof Vrtmetal)) {
            return false;
        }
        Vrtmetal other = (Vrtmetal) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.mavenproject1.entitys.Vrtmetal[ id=" + id + " ]";
    }
    
}
