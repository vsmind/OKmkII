/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Vitaly
 */
@Entity
@Table(name = "Event")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Event.findAll", query = "SELECT e FROM Event e"),
    @NamedQuery(name = "Event.findById", query = "SELECT e FROM Event e WHERE e.id = :id"),
    @NamedQuery(name = "Event.findByUserID", query = "SELECT e FROM Event e WHERE e.userID = :userID"),
    @NamedQuery(name = "Event.findByTitle", query = "SELECT e FROM Event e WHERE e.title = :title"),
    @NamedQuery(name = "Event.findByDescription", query = "SELECT e FROM Event e WHERE e.description = :description"),
    @NamedQuery(name = "Event.findByTimeStart", query = "SELECT e FROM Event e WHERE e.timeStart = :timeStart"),
    @NamedQuery(name = "Event.findByTimeEnd", query = "SELECT e FROM Event e WHERE e.timeEnd = :timeEnd"),
    @NamedQuery(name = "Event.findByLocationLat", query = "SELECT e FROM Event e WHERE e.locationLat = :locationLat"),
    @NamedQuery(name = "Event.findByLocationLong", query = "SELECT e FROM Event e WHERE e.locationLong = :locationLong"),
    @NamedQuery(name = "Event.findByReminder", query = "SELECT e FROM Event e WHERE e.reminder = :reminder")})
public class Event implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "UserID")
    private int userID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "TITLE")
    private String title;
    @Size(max = 256)
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "TIME_START")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeStart;
    @Column(name = "TIME_END")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeEnd;
    @Column(name = "LOCATION_LAT")
    private Long locationLat;
    @Column(name = "LOCATION_LONG")
    private Long locationLong;
    @Column(name = "REMINDER")
    private Boolean reminder;
    @JoinColumn(name = "TYPE", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Eventtype type;
    @JoinColumn(name = "TIME_REPEAT", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private TimeRepeat timeRepeat;

    public Event() {
    }

    public Event(Integer id) {
        this.id = id;
    }

    public Event(Integer id, int userID, String title) {
        this.id = id;
        this.userID = userID;
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(Date timeStart) {
        this.timeStart = timeStart;
    }

    public Date getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(Date timeEnd) {
        this.timeEnd = timeEnd;
    }

    public Long getLocationLat() {
        return locationLat;
    }

    public void setLocationLat(Long locationLat) {
        this.locationLat = locationLat;
    }

    public Long getLocationLong() {
        return locationLong;
    }

    public void setLocationLong(Long locationLong) {
        this.locationLong = locationLong;
    }

    public Boolean getReminder() {
        return reminder;
    }

    public void setReminder(Boolean reminder) {
        this.reminder = reminder;
    }

    public Eventtype getType() {
        return type;
    }

    public void setType(Eventtype type) {
        this.type = type;
    }

    public TimeRepeat getTimeRepeat() {
        return timeRepeat;
    }

    public void setTimeRepeat(TimeRepeat timeRepeat) {
        this.timeRepeat = timeRepeat;
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
        if (!(object instanceof Event)) {
            return false;
        }
        Event other = (Event) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Event[ id=" + id + " ]";
    }
    
}
