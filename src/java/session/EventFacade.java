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
    
    public List<entity.Event> getEventsByUserID(int _userID)
    {
        System.out.print("TEST");
        List e;
        e = getEntityManager().createNamedQuery("Event.findByUserID").setParameter("userID", _userID).getResultList();
        
        //System.out.println(e);
        return e;
    }
    
    public List<entity.Event> getEventsByUserIDandDate(int _userID, int _month, int _day, int _year)
    {
        List<entity.Event> eventList;
        
        eventList = (List<entity.Event>)getEntityManager().createNativeQuery("SELECT * FROM Event WHERE Event.userID = ? AND DATE_FORMAT(Event.TIME_START, '%c') = ? AND DATE_FORMAT(Event.TIME_START, '%d') = ? AND DATE_FORMAT(Event.TIME_START, '%Y') = ? ORDER BY Event.TIME_START", entity.Event.class ).setParameter(1, _userID).setParameter(2, _month).setParameter(3, _day).setParameter(4, _year).getResultList();
        return eventList;
    }
    
    public List<entity.Event> getMonthEvents(int _userID, int _month, int _year)
    {
        List<entity.Event> eventList;
        eventList = (List<entity.Event>)getEntityManager().createNativeQuery("SELECT * FROM Event WHERE Event.userID = ? AND DATE_FORMAT(Event.TIME_START, '%c') = ? AND DATE_FORMAT(Event.TIME_START, '%Y') = ? ORDER BY Event.TIME_START", entity.Event.class ).setParameter(1, _userID).setParameter(2, _month).setParameter(3, _year).getResultList();
        return eventList;
    }
    
    
}
