/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Modulesstatus;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Auto generated session bean class
 * that interacts with
 * Modulestatus entity
 * @author Vitaly
 */
@Stateless
public class ModulesstatusFacade extends AbstractFacade<Modulesstatus> {
    @PersistenceContext(unitName = "OKmkIIPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ModulesstatusFacade() {
        super(Modulesstatus.class);
    }
    
    /**
     * Method gets module status
     * @param _id - module status id
     * @return entity.Modulesstatus
     */
    public entity.Modulesstatus getModuleStatusByID(int _id)
    {
        entity.Modulesstatus l;
        l = (entity.Modulesstatus)getEntityManager().createNamedQuery("Modulesstatus.findById").setParameter("id", _id).getSingleResult();
        return l;
    }
    
}
