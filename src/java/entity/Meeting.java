/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Vitaly
 */
@Entity
@Table(name = "Meeting")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Meeting.findAll", query = "SELECT m FROM Meeting m"),
    @NamedQuery(name = "Meeting.findById", query = "SELECT m FROM Meeting m WHERE m.id = :id"),
    @NamedQuery(name = "Meeting.findByParticipantID", query = "SELECT m FROM Meeting m WHERE m.participantID = :participantID"),
    @NamedQuery(name = "Meeting.findByParticipantname", query = "SELECT m FROM Meeting m WHERE m.participantname = :participantname")})
public class Meeting implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Column(name = "Participant_ID")
    private Integer participantID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "Participant_name")
    private String participantname;

    public Meeting() {
    }

    public Meeting(Integer id) {
        this.id = id;
    }

    public Meeting(Integer id, String participantname) {
        this.id = id;
        this.participantname = participantname;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParticipantID() {
        return participantID;
    }

    public void setParticipantID(Integer participantID) {
        this.participantID = participantID;
    }

    public String getParticipantname() {
        return participantname;
    }

    public void setParticipantname(String participantname) {
        this.participantname = participantname;
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
        if (!(object instanceof Meeting)) {
            return false;
        }
        Meeting other = (Meeting) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Meeting[ id=" + id + " ]";
    }
    
}
