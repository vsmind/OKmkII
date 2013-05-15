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
@Table(name = "Modules")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Modules.findAll", query = "SELECT m FROM Modules m"),
    @NamedQuery(name = "Modules.findById", query = "SELECT m FROM Modules m WHERE m.id = :id"),
    @NamedQuery(name = "Modules.findByMday", query = "SELECT m FROM Modules m WHERE m.mday = :mday"),
    @NamedQuery(name = "Modules.findByMweek", query = "SELECT m FROM Modules m WHERE m.mweek = :mweek"),
    @NamedQuery(name = "Modules.findByMmonth", query = "SELECT m FROM Modules m WHERE m.mmonth = :mmonth")})
public class Modules implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "MODULENAME")
    private String modulename;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "TYPE")
    private String type;
    @Column(name = "MDAY")
    private Boolean mday;
    @Column(name = "MWEEK")
    private Boolean mweek;
    @Column(name = "MMONTH")
    private Boolean mmonth;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "MODULEDESCRIPTION")
    private String moduledescription;
    @JoinColumn(name = "MODULESTATUS", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Modulesstatus modulestatus;

    public Modules() {
    }

    public Modules(Integer id) {
        this.id = id;
    }

    public Modules(Integer id, String modulename, String type, String moduledescription) {
        this.id = id;
        this.modulename = modulename;
        this.type = type;
        this.moduledescription = moduledescription;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getModulename() {
        return modulename;
    }

    public void setModulename(String modulename) {
        this.modulename = modulename;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getMday() {
        return mday;
    }

    public void setMday(Boolean mday) {
        this.mday = mday;
    }

    public Boolean getMweek() {
        return mweek;
    }

    public void setMweek(Boolean mweek) {
        this.mweek = mweek;
    }

    public Boolean getMmonth() {
        return mmonth;
    }

    public void setMmonth(Boolean mmonth) {
        this.mmonth = mmonth;
    }

    public String getModuledescription() {
        return moduledescription;
    }

    public void setModuledescription(String moduledescription) {
        this.moduledescription = moduledescription;
    }

    public Modulesstatus getModulestatus() {
        return modulestatus;
    }

    public void setModulestatus(Modulesstatus modulestatus) {
        this.modulestatus = modulestatus;
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
        if (!(object instanceof Modules)) {
            return false;
        }
        Modules other = (Modules) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Modules[ id=" + id + " ]";
    }
    
}
