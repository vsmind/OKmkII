/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package help;

import java.io.Serializable;
import java.util.Date;

/**
 * Help class which is used to create the jSON object for Android client
 * that used for events sending
 * @author Vitaly
 */
public class AndroidEvent implements Serializable
{
    private Integer id;
    private int userID;
    private String title;
    private String description;
    private Date timeStart;
    private Date timeEnd;
    private Long locationLat;
    private Long locationLong;
    private Boolean reminder;
    private int type;
    private int timeRepeat;

    public AndroidEvent() {
    }

    /*
     * Getter or Setter Methods
     */
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getTimeRepeat() {
        return timeRepeat;
    }

    public void setTimeRepeat(int timeRepeat) {
        this.timeRepeat = timeRepeat;
    }
    
    
}
