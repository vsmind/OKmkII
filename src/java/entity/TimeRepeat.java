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
 *
 * @author Vitaly
 */
@Entity
@Table(name = "TimeRepeat")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TimeRepeat.findAll", query = "SELECT t FROM TimeRepeat t"),
    @NamedQuery(name = "TimeRepeat.findById", query = "SELECT t FROM TimeRepeat t WHERE t.id = :id"),
    @NamedQuery(name = "TimeRepeat.findByInterval", query = "SELECT t FROM TimeRepeat t WHERE t.interval = :interval")})
public class TimeRepeat implements Serializable {
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
    @Column(name = "INTERVAL")
    private String interval;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "timeRepeat")
    private Collection<Event> eventCollection;

    public TimeRepeat() {
    }

    public TimeRepeat(Integer id) {
        this.id = id;
    }

    public TimeRepeat(Integer id, String interval) {
        this.id = id;
        this.interval = interval;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInterval() {
        return interval;
    }

    public void setInterval(String interval) {
        this.interval = interval;
    }

    @XmlTransient
    public Collection<Event> getEventCollection() {
        return eventCollection;
    }

    public void setEventCollection(Collection<Event> eventCollection) {
        this.eventCollection = eventCollection;
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
    
}
