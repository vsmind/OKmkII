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
    
    public String getLinkByPlaceID(String _id)
    {
        String name;
        name = (String)getEntityManager().createNativeQuery("SELECT Stadnamn FROM `Yrlinks` WHERE id = ?").setParameter(1, _id).getSingleResult();
        return name;
    }
    
    public String getURLByPlaceID(String _id)
    {
        String url;
        url = (String)getEntityManager().createNativeQuery("SELECT Engelsk FROM `Yrlinks` WHERE id = ?").setParameter(1, _id).getSingleResult();
        return url;
    }
    
}
