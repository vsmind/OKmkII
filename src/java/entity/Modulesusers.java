/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Vitaly
 */
@Entity
@Table(name = "Modules_users")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Modulesusers.findAll", query = "SELECT m FROM Modulesusers m"),
    @NamedQuery(name = "Modulesusers.findById", query = "SELECT m FROM Modulesusers m WHERE m.id = :id"),
    @NamedQuery(name = "Modulesusers.findByUserId", query = "SELECT m FROM Modulesusers m WHERE m.userId = :userId")})
public class Modulesusers implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "USER_ID")
    private int userId;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "MODULE_DATA")
    private String moduleData;
    @JoinColumn(name = "MODULE_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Modules moduleId;

    public Modulesusers() {
    }

    public Modulesusers(Integer id) {
        this.id = id;
    }

    public Modulesusers(Integer id, int userId, String moduleData) {
        this.id = id;
        this.userId = userId;
        this.moduleData = moduleData;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getModuleData() {
        return moduleData;
    }

    public void setModuleData(String moduleData) {
        this.moduleData = moduleData;
    }

    public Modules getModuleId() {
        return moduleId;
    }

    public void setModuleId(Modules moduleId) {
        this.moduleId = moduleId;
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
        if (!(object instanceof Modulesusers)) {
            return false;
        }
        Modulesusers other = (Modulesusers) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Modulesusers[ id=" + id + " ]";
    }
    
}
