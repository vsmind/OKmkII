/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package android;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import help.ConnectionResult;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import servlets.Event;
import session.EventFacade;
import session.EventtypeFacade;
import session.TimeRepeatFacade;

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
    
    @EJB
    private EventFacade eventFacade;
    @EJB
    private EventtypeFacade eventTypeFacade;
    @EJB
    private TimeRepeatFacade timeRepeatFacade;
    
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
        else if(action.equals("save"))
        {
            saveNewEvent(request, response);
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
        eventDay = Integer.parseInt((String)request.getParameter("day"));
        eventMonth = Integer.parseInt((String)request.getParameter("month"));
        eventYear = Integer.parseInt((String)request.getParameter("year"));
        //list with events
        List<entity.Event> eventList;
        eventList = eventsFacade.getEventsByUserIDandDate(userID, eventMonth, eventDay, eventYear);
        //Serializeble help class
        List<help.AndroidEvent> androidEventList = new LinkedList<help.AndroidEvent>();
        entity.Event ev;
        
        for(int i = 0; i < eventList.size(); i++)
        {
            help.AndroidEvent ae = new help.AndroidEvent();
            ev = eventList.get(i);
            ae.setId(ev.getId());
            ae.setUserID(userID);
            ae.setTitle(ev.getTitle());
            ae.setType(ev.getType().getId());
            ae.setDescription(ev.getDescription());
            ae.setTimeStart(ev.getTimeStart());
            ae.setTimeEnd(ev.getTimeEnd());
            ae.setLocationLat(ev.getLocationLat());
            ae.setLocationLong(ev.getLocationLong());
            ae.setReminder(ev.getReminder());
            ae.setTimeRepeat(ev.getTimeRepeat().getId()); 
            androidEventList.add(ae);
        }
        //response type
        response.setContentType("application/json");
        //Google gson object
        Gson gson = new Gson();
        //servlet response
        response.getWriter().write(gson.toJson(androidEventList));
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
        eventMonth = Integer.parseInt((String)request.getParameter("month"));
        eventYear = Integer.parseInt((String)request.getParameter("year"));
        //list with events
        List<entity.Event> eventList;
        eventList = eventsFacade.getMonthEvents(userID, eventMonth, eventYear);
        //Serializeble help class
        List<help.AndroidEvent> androidEventList = new LinkedList<help.AndroidEvent>();
        entity.Event ev;
        
        for(int i = 0; i < eventList.size(); i++)
        {
            help.AndroidEvent ae = new help.AndroidEvent();
            ev = eventList.get(i);
            ae.setId(ev.getId());
            ae.setUserID(userID);
            ae.setTitle(ev.getTitle());
            ae.setType(ev.getType().getId());
            ae.setDescription(ev.getDescription());
            ae.setTimeStart(ev.getTimeStart());
            ae.setTimeEnd(ev.getTimeEnd());
            ae.setLocationLat(ev.getLocationLat());
            ae.setLocationLong(ev.getLocationLong());
            ae.setReminder(ev.getReminder());
            ae.setTimeRepeat(ev.getTimeRepeat().getId()); 
            androidEventList.add(ae);
        }
        //response type
        response.setContentType("application/json");
        //Google gson object
        Gson gson = new Gson();
        //servlet response
        response.getWriter().write(gson.toJson(androidEventList));
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
    
    private void saveNewEvent(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        //Event object
        Object[] evnt;
        //get variables from request
        String events = (String)request.getParameter("event");
        //Google gson object
        Gson gson = new Gson();
        evnt = gson.fromJson(events, Object[].class);
        JsonArray jarray;
        jarray = new JsonParser().parse(events).getAsJsonArray();
        
        System.out.println(jarray);
        
        
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
        
        //getting userID from session
        userID = ((Integer)httpsession.getAttribute("userID"));
        //getting variables from json
        title = (String)evnt[0];
        type = Integer.parseInt((String)evnt[10]);
        description = (String)evnt[2];
        
        //lead to the required date format
        formatedStartDate = (String)evnt[3];
        formatedEndDate = (String)evnt[5];
        
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
