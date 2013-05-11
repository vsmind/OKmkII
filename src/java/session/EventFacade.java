/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Event;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Vitaly
 */
@Stateless
public class EventFacade extends AbstractFacade<Event> {
    @PersistenceContext(unitName = "OKmkIIPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EventFacade() {
        super(Event.class);
    }
    
    /**
     * Method getEventsByUserID return all events for user
     * @param _userID int user id
     * @return List<entity.Event> events for user
     */
    public List<entity.Event> getEventsByUserID(int _userID)
    {
        List e;
        e = getEntityManager().createNamedQuery("Event.findByUserID").setParameter("userID", _userID).getResultList();
        return e;
    }
    
    /**
     * Method getEventsByUserIDandDate return all events from a given date to the day
     * @param _userID int user ID
     * @param _month int month date
     * @param _day int day date
     * @param _year int year date
     * @return List<entity.Event> events for user
     */
    public List<entity.Event> getEventsByUserIDandDate(int _userID, int _month, int _day, int _year)
    {
        List<entity.Event> eventList;
        eventList = (List<entity.Event>)getEntityManager().createNativeQuery("SELECT * FROM Event WHERE Event.userID = ? AND DATE_FORMAT(Event.TIME_START, '%c') = ? AND DATE_FORMAT(Event.TIME_START, '%d') = ? AND DATE_FORMAT(Event.TIME_START, '%Y') = ? ORDER BY Event.TIME_START", entity.Event.class ).setParameter(1, _userID).setParameter(2, _month).setParameter(3, _day).setParameter(4, _year).getResultList();
        return eventList;
    }
    
    /**
     * Method getEventsByUserIDandDate return all events from a given date to the month
     * @param _userID int user ID
     * @param _month int month date
     * @param _year int year date
     * @return List<entity.Event> events for user
     */
    public List<entity.Event> getMonthEvents(int _userID, int _month, int _year)
    {
        List<entity.Event> eventList;
        eventList = (List<entity.Event>)getEntityManager().createNativeQuery("SELECT * FROM Event WHERE Event.userID = ? AND DATE_FORMAT(Event.TIME_START, '%c') = ? AND DATE_FORMAT(Event.TIME_START, '%Y') = ? ORDER BY Event.TIME_START", entity.Event.class ).setParameter(1, _userID).setParameter(2, _month).setParameter(3, _year).getResultList();
        return eventList;
    }
    
    
}
