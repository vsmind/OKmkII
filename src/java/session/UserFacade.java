/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Auto generated session bean class
 * that interacts with TimeRepeat entity
 * UserFacade entity
 * @author Vitaly
 */
@Stateless
public class UserFacade extends AbstractFacade<User> {
    @PersistenceContext(unitName = "OKmkIIPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserFacade() {
        super(User.class);
    }
    
    /**
     * Method getUserByUsername returns User object
     * @param _username String username
     * @return User
     */
    public User getUserByUsername(String _username)
    {
        List <User> userList;
        User user;
               
        userList = getEntityManager().createNamedQuery("User.findByUsername").setParameter("username", _username).getResultList();
        
        if(!userList.isEmpty())
        {
            user = userList.get(0);
        }
        else
        {
            user = null;
        }
        
        return user;
    }
    
}
