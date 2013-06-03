/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package quicktest;

import entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.EventFacade;


/**
 * Test class, used for fast testing 
 * @author Vitaly
 */
public class WorkingWithEvents {
    
    @EJB
    private EventFacade eventFacade;
    
    public WorkingWithEvents()
    {

        
        //eventFacade.getEventsByUserID(2);
        
        System.out.println("test");
        
    }
    
}
