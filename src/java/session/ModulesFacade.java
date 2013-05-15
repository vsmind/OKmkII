/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Modules;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Vitaly
 */
@Stateless
public class ModulesFacade extends AbstractFacade<Modules> {
    @PersistenceContext(unitName = "OKmkIIPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ModulesFacade() {
        super(Modules.class);
    }
    
    public entity.Modules getModulebyID(int _id)
    {
        entity.Modules m;
        m = (entity.Modules)getEntityManager().createNamedQuery("Modules.findById").setParameter("id", _id).getSingleResult();
        return m;
    }
    
}
