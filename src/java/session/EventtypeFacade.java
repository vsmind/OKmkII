/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Eventtype;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Auto generated session bean class
 * that interacts with
 * Eventtype entity
 * @author Vitaly
 */
@Stateless
public class EventtypeFacade extends AbstractFacade<Eventtype> {
    @PersistenceContext(unitName = "OKmkIIPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EventtypeFacade() {
        super(Eventtype.class);
    }
    
    /**
     * Method getEventTypeById return Eventtype 
     * @param _id int eventtype id
     * @return Eventtype
     */
    public Eventtype getEventTypeById(int _id)
    {
        Eventtype etype;
        etype = (Eventtype)getEntityManager().createNamedQuery("Eventtype.findById").setParameter("id", _id).getSingleResult();
        return etype;
    }
    
}
