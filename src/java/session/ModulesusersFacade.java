/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Modulesusers;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Auto generated session bean class
 * that interacts with
 * Modulesusers entity
 * @author Vitaly
 */
@Stateless
public class ModulesusersFacade extends AbstractFacade<Modulesusers> {
    @PersistenceContext(unitName = "OKmkIIPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ModulesusersFacade() {
        super(Modulesusers.class);
    }
    
    /**
     * Method returns list with all user modules
     * @param _userID - user id
     * @return List<entity.Modulesusers>
     */
    public List<entity.Modulesusers> getModulesByUserUD(int _userID)
    {
        List l;
        l = (List<entity.Modulesusers>)getEntityManager().createNamedQuery("Modulesusers.findByUserId").setParameter("userId", _userID).getResultList();
        return l;
    }
    
}
