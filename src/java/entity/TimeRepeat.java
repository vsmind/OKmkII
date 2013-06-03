/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Generated entity class for TimeRepeat table
 * @author Vitaly
 */
@Entity
@Table(name = "TimeRepeat")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TimeRepeat.findAll", query = "SELECT t FROM TimeRepeat t"),
    @NamedQuery(name = "TimeRepeat.findById", query = "SELECT t FROM TimeRepeat t WHERE t.id = :id"),
    @NamedQuery(name = "TimeRepeat.findByTimetorepeat", query = "SELECT t FROM TimeRepeat t WHERE t.timetorepeat = :timetorepeat")})
public class TimeRepeat implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "timeRepeat")
    private Collection<Event> eventCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "TIMETOREPEAT")
    private String timetorepeat;

    public TimeRepeat() {
    }

    public TimeRepeat(Integer id) {
        this.id = id;
    }

    public TimeRepeat(Integer id, String timetorepeat) {
        this.id = id;
        this.timetorepeat = timetorepeat;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTimetorepeat() {
        return timetorepeat;
    }

    public void setTimetorepeat(String timetorepeat) {
        this.timetorepeat = timetorepeat;
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
        if (!(object instanceof TimeRepeat)) {
            return false;
        }
        TimeRepeat other = (TimeRepeat) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.TimeRepeat[ id=" + id + " ]";
    }

    @XmlTransient
    public Collection<Event> getEventCollection() {
        return eventCollection;
    }

    public void setEventCollection(Collection<Event> eventCollection) {
        this.eventCollection = eventCollection;
    }
    
}
