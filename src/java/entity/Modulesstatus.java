/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Vitaly
 */
@Entity
@Table(name = "Modules_status")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Modulesstatus.findAll", query = "SELECT m FROM Modulesstatus m"),
    @NamedQuery(name = "Modulesstatus.findById", query = "SELECT m FROM Modulesstatus m WHERE m.id = :id"),
    @NamedQuery(name = "Modulesstatus.findByMstatus", query = "SELECT m FROM Modulesstatus m WHERE m.mstatus = :mstatus")})
public class Modulesstatus implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "MSTATUS")
    private String mstatus;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "modulestatus")
    private Collection<Modules> modulesCollection;

    public Modulesstatus() {
    }

    public Modulesstatus(Integer id) {
        this.id = id;
    }

    public Modulesstatus(Integer id, String mstatus) {
        this.id = id;
        this.mstatus = mstatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMstatus() {
        return mstatus;
    }

    public void setMstatus(String mstatus) {
        this.mstatus = mstatus;
    }

    @XmlTransient
    public Collection<Modules> getModulesCollection() {
        return modulesCollection;
    }

    public void setModulesCollection(Collection<Modules> modulesCollection) {
        this.modulesCollection = modulesCollection;
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
        if (!(object instanceof Modulesstatus)) {
            return false;
        }
        Modulesstatus other = (Modulesstatus) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Modulesstatus[ id=" + id + " ]";
    }
    
}
