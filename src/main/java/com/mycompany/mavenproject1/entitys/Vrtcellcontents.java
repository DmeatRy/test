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
@Table(name = "vrtcellcontents")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vrtcellcontents.findAll", query = "SELECT v FROM Vrtcellcontents v")
    , @NamedQuery(name = "Vrtcellcontents.findById", query = "SELECT v FROM Vrtcellcontents v WHERE v.id = :id")
    , @NamedQuery(name = "Vrtcellcontents.findByPosition", query = "SELECT v FROM Vrtcellcontents v WHERE v.position = :position")
    , @NamedQuery(name = "Vrtcellcontents.findByIsexist", query = "SELECT v FROM Vrtcellcontents v WHERE v.isexist = :isexist")})
public class Vrtcellcontents implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "POSITION")
    private Integer position;
    @Column(name = "ISEXIST")
    private Boolean isexist;
    @OneToMany(mappedBy = "idcellcontents")
    private List<Vrttempbacket> vrttempbacketList;
    @OneToMany(mappedBy = "idcellcontents")
    private List<Vrtmove> vrtmoveList;
    @JoinColumn(name = "IDCELLS", referencedColumnName = "ID")
    @ManyToOne
    private Vrtcells idcells;
    @JoinColumn(name = "IDMETAL", referencedColumnName = "ID")
    @ManyToOne
    private Vrtmetal idmetal;
    @JoinColumn(name = "IDUSER", referencedColumnName = "ID")
    @ManyToOne
    private Vrtuser iduser;

    public Vrtcellcontents() {
    }

    public Vrtcellcontents(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Boolean getIsexist() {
        return isexist;
    }

    public void setIsexist(Boolean isexist) {
        this.isexist = isexist;
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

    public Vrtcells getIdcells() {
        return idcells;
    }

    public void setIdcells(Vrtcells idcells) {
        this.idcells = idcells;
    }

    public Vrtmetal getIdmetal() {
        return idmetal;
    }

    public void setIdmetal(Vrtmetal idmetal) {
        this.idmetal = idmetal;
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
        if (!(object instanceof Vrtcellcontents)) {
            return false;
        }
        Vrtcellcontents other = (Vrtcellcontents) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.mavenproject1.entitys.Vrtcellcontents[ id=" + id + " ]";
    }
    
}
