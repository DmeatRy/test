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
@Table(name = "vrttempbacket")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vrttempbacket.findAll", query = "SELECT v FROM Vrttempbacket v")
    , @NamedQuery(name = "Vrttempbacket.findByDedit", query = "SELECT v FROM Vrttempbacket v WHERE v.dedit = :dedit")
    , @NamedQuery(name = "Vrttempbacket.findById", query = "SELECT v FROM Vrttempbacket v WHERE v.id = :id")})
public class Vrttempbacket implements Serializable {

    private static final long serialVersionUID = 1L;
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

    public Vrttempbacket() {
    }

    public Vrttempbacket(Long id) {
        this.id = id;
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
        if (!(object instanceof Vrttempbacket)) {
            return false;
        }
        Vrttempbacket other = (Vrttempbacket) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.mavenproject1.entitys.Vrttempbacket[ id=" + id + " ]";
    }
    
}
