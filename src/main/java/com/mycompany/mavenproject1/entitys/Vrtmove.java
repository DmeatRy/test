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
@Table(name = "vrtmove")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vrtmove.findAll", query = "SELECT v FROM Vrtmove v")
    , @NamedQuery(name = "Vrtmove.findByIsout", query = "SELECT v FROM Vrtmove v WHERE v.isout = :isout")
    , @NamedQuery(name = "Vrtmove.findByPosition", query = "SELECT v FROM Vrtmove v WHERE v.position = :position")
    , @NamedQuery(name = "Vrtmove.findByDedit", query = "SELECT v FROM Vrtmove v WHERE v.dedit = :dedit")
    , @NamedQuery(name = "Vrtmove.findById", query = "SELECT v FROM Vrtmove v WHERE v.id = :id")})
public class Vrtmove implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "ISOUT")
    private Boolean isout;
    @Column(name = "POSITION")
    private Integer position;
    @Column(name = "DEDIT")
    @Temporal(TemporalType.DATE)
    private Date dedit;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @JoinColumn(name = "IDCELLCONTENTS", referencedColumnName = "ID")
    @ManyToOne
    private Vrtcellcontents idcellcontents;
    @JoinColumn(name = "IDUSER", referencedColumnName = "ID")
    @ManyToOne
    private Vrtuser iduser;

    public Vrtmove() {
    }

    public Vrtmove(Long id) {
        this.id = id;
    }

    public Boolean getIsout() {
        return isout;
    }

    public void setIsout(Boolean isout) {
        this.isout = isout;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
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

    public Vrtcellcontents getIdcellcontents() {
        return idcellcontents;
    }

    public void setIdcellcontents(Vrtcellcontents idcellcontents) {
        this.idcellcontents = idcellcontents;
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
        if (!(object instanceof Vrtmove)) {
            return false;
        }
        Vrtmove other = (Vrtmove) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.mavenproject1.entitys.Vrtmove[ id=" + id + " ]";
    }
    
}
