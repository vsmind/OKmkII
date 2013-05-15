/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Yrlinks;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Vitaly
 */
@Stateless
public class YrlinksFacade extends AbstractFacade<Yrlinks> {
    @PersistenceContext(unitName = "OKmkIIPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public YrlinksFacade() {
        super(Yrlinks.class);
    }
    
    public List<help.Yrlinks> getLinkByName(String _name)
    {
        List<help.Yrlinks> l;
        l = (List<help.Yrlinks>)getEntityManager().createNativeQuery("SELECT * FROM `Yrlinks` WHERE `stadnamn` like ?").setParameter(1, _name).getResultList();
        return l;
    }
    
}
