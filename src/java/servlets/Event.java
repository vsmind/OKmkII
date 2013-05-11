/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.EventFacade;
import session.EventtypeFacade;
import session.TimeRepeatFacade;

/**
 * Servlet Event is responsible for event saving in database
 * @author Vitaly
 */
public class Event extends HttpServlet {
    //variables
    private HttpSession httpsession;   
    @EJB
    private EventFacade eventFacade;
    @EJB
    private EventtypeFacade eventTypeFacade;
    @EJB
    private TimeRepeatFacade timeRepeatFacade;
    
    // <editor-fold defaultstate="collapsed" desc="processRequest">
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
            /*
             * TODO output your page here. You may use following sample code.
             */
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Event</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Event at " + request.getContextPath() + "</h1>");
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
     * save event to database
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
        //Date format
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");//test
        Calendar cal = Calendar.getInstance();
        
        //variable
        int userID;//user id
        String title;//event title
        Integer type;//event type
        String description;//event description
        Date timeStart = cal.getTime();//event start
        Date timeEnd = cal.getTime();//event end
        
        String hourStart;
        String minuteStart;
        String dateStart;
        
        String hourEnd;
        String minuteEnd;
        String dateEnd;
        
        String startTime;
        String endTime;
        
        String formatedStartDate;
        String formatedEndDate;
        
        long location_lat;
        long location_long;
        //getting variables from request
        userID = ((Integer)httpsession.getAttribute("userID"));
        title = request.getParameter("eventtitle");
        type = Integer.parseInt(request.getParameter("eventtype"));
        description = request.getParameter("eventdescription");
        
        
        /*hourStart = request.getParameter("eventstarthour");
         * minuteStart = request.getParameter("eventstartminute");
         * hourEnd = request.getParameter("eventendhour");
         * minuteEnd = request.getParameter("eventendminute");*/
        
        
        dateStart = request.getParameter("eventstartdate");
        dateEnd = request.getParameter("eventenddate");
        startTime = request.getParameter("eventstarttime");
        endTime = request.getParameter("eventendtime");
        //lead to the required date format
        formatedStartDate = dateStart + " " + startTime;
        formatedEndDate = dateEnd + " " + endTime;
        
        //date parsing
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        try 
        {
            timeStart =  format.parse(formatedStartDate);
            timeEnd = format.parse(formatedEndDate);
        } 
        catch (ParseException ex) {
            
            Logger.getLogger(Event.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //set latitude and langitude
        location_lat = 1;
        location_long = 1;
        
        /*
        System.out.println(title);
        System.out.println(type);
        System.out.println(description);
        System.out.println(timeStart);
        System.out.println(timeEnd);
        */
        
        //create new event
        entity.Event event = new entity.Event(1);
        //set information to event
        event.setUserID(userID);
        event.setTitle(title);
        event.setType(eventTypeFacade.getEventTypeById(type));
        event.setDescription(description);
        
        event.setTimeStart(timeStart);
        event.setTimeEnd(timeEnd);
        
        event.setLocationLat(location_lat);
        event.setLocationLong(location_long);
        
        event.setReminder(Boolean.TRUE);
        event.setTimeRepeat(timeRepeatFacade.getEventTypeById(1));
        //Save event to DB
        eventFacade.create(event);
        //System.out.println(event);
        //send response from servlet
        response.getWriter().write("OK");
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * post processing
     * used for tests
     * 
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Returns the current HttpSession associated with this request
        httpsession = request.getSession();
        
        String title;
        Integer type;
        String description;
        //getting variables from request
        title = request.getParameter("eventtitle");
        type = Integer.parseInt(request.getParameter("eventtype"));
        description = request.getParameter("eventdescription");
        
        System.out.println(title);
        System.out.println(type);
        System.out.println(description);  
    }

    // <editor-fold defaultstate="collapsed" desc="getServletInfor()">
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
