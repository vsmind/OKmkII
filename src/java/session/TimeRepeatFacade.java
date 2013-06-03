/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.TimeRepeat;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Auto generated session bean class
 * that interacts with TimeRepeat entity
 * @author Vitaly
 */
@Stateless
public class TimeRepeatFacade extends AbstractFacade<TimeRepeat> {
    @PersistenceContext(unitName = "OKmkIIPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TimeRepeatFacade() {
        super(TimeRepeat.class);
    }
    
    /**
     * Method returns TimeRepeat object
     * @param _id id
     * @return TimeRepeat
     */
    public TimeRepeat getEventTypeById(int _id)
    {
        TimeRepeat tRepeat;
        tRepeat = (TimeRepeat)getEntityManager().createNamedQuery("TimeRepeat.findById").setParameter("id", _id).getSingleResult();
        return tRepeat;
    }
    
}
