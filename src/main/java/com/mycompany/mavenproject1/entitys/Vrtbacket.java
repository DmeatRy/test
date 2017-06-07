/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1.entitys;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Дмитрий
 */
@Entity
@Table(name = "vrtbacket")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vrtbacket.findAll", query = "SELECT v FROM Vrtbacket v")
    , @NamedQuery(name = "Vrtbacket.findByIsexist", query = "SELECT v FROM Vrtbacket v WHERE v.isexist = :isexist")
    , @NamedQuery(name = "Vrtbacket.findByDedit", query = "SELECT v FROM Vrtbacket v WHERE v.dedit = :dedit")
    , @NamedQuery(name = "Vrtbacket.findById", query = "SELECT v FROM Vrtbacket v WHERE v.id = :id")})
public class Vrtbacket implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "ISEXIST")
    private Boolean isexist;
    @Column(name = "DEDIT")
    @Temporal(TemporalType.DATE)
    private Date dedit;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @JoinColumn(name = "IDMETAL", referencedColumnName = "ID")
    @ManyToOne
    private Vrtmetal idmetal;
    @JoinColumn(name = "IDUSER", referencedColumnName = "ID")
    @ManyToOne
    private Vrtuser iduser;

    public Vrtbacket() {
    }

    public Vrtbacket(Long id) {
        this.id = id;
    }

    public Boolean getIsexist() {
        return isexist;
    }

    public void setIsexist(Boolean isexist) {
        this.isexist = isexist;
    }

    public Date getDedit() {
        return dedit;
    }

    public void setDedit(Date dedit) {
        this.dedit = dedit;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (!(object instanceof Vrtbacket)) {
            return false;
        }
        Vrtbacket other = (Vrtbacket) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.mavenproject1.entitys.Vrtbacket[ id=" + id + " ]";
    }
    
}
