/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package android;

import com.google.gson.Gson;
import help.ConnectionResult;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.EventFacade;

/**
 * Servlet responsible for events transfer for android client
 * @author Vitaly
 */
public class AndroidEvents extends HttpServlet {
    //Variables
    private String action;
    private HttpSession httpsession;
    @EJB
    EventFacade eventsFacade;
    
    // <editor-fold defaultstate="collapsed" desc="processRequest method.">
    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AndroidEvents</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AndroidEvents at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {            
            out.close();
        }
    }
    // </editor-fold>

    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * get processing
     * method checks action and run corresponding methods
     * 
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Returns the current HttpSession associated with this request
        httpsession = request.getSession();
        //variable is responsible for selecting actions
	action = request.getParameter("instance");
        
        if(action.equals("dayevents"))
        {
            getDayEvents(request, response);
        }
        else if(action.equals("monthevents"))
        {
            getMonthEvents(request, response);
        }
        else
        {
            
        }
        
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
    private void getDayEvents(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        //Variables
        int userID;
        int eventDay;
        int eventMonth;
        int eventYear;
        //get userID from session
        userID = (Integer)httpsession.getAttribute("userID");
        //get variables from request
        eventDay = Integer.parseInt((String)request.getAttribute("day"));
        eventMonth = Integer.parseInt((String)request.getAttribute("month"));
        eventYear = Integer.parseInt((String)request.getAttribute("year"));
        //list with events
        List<entity.Event> eventList;
        eventList = eventsFacade.getEventsByUserIDandDate(userID, eventMonth, eventDay, eventYear);
        //response type
        response.setContentType("application/json");
        //Google gson object
        Gson gson = new Gson();
        //servlet response
        response.getWriter().write(gson.toJson(eventList));
    }
    
    private void getMonthEvents(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        //Variables
        int userID;
        int eventMonth;
        int eventYear;
        //get userID from session
        userID = (Integer)httpsession.getAttribute("userID");
        //get variables from request
        eventMonth = Integer.parseInt((String)request.getAttribute("month"));
        eventYear = Integer.parseInt((String)request.getAttribute("year"));
        //list with events
        List<entity.Event> eventList;
        eventList = eventsFacade.getMonthEvents(userID, eventMonth, eventYear);
        //response type
        response.setContentType("application/json");
        //Google gson object
        Gson gson = new Gson();
        //servlet response
        response.getWriter().write(gson.toJson(eventList));
    }
    
    private void requestFeil(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        //Google gson object
        Gson gson = new Gson();
        //converted object used as a response from the server
        ConnectionResult result = new ConnectionResult(false);
        //servlet response
        response.getWriter().write(gson.toJson(result));
    }
    
    
    
    

    // <editor-fold defaultstate="collapsed" desc="getServletInfo method.">
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
