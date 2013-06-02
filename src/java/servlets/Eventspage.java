/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import entity.Eventtype;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.EventFacade;
import session.EventtypeFacade;

/**
 *
 * @author Vitaly
 */
public class Eventspage extends HttpServlet {
    //variables
    private HttpSession httpsession;   
    private String action;
    @EJB
    private EventtypeFacade eventtypeFacade;
    @EJB
    private EventFacade eventFacade;
    @EJB
    private EventtypeFacade eventTypeFacade;
    // <editor-fold defaultstate="collapsed" desc="processRequest method">
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
            out.println("<title>Servlet Eventspage</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Eventspage at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {            
            out.close();
        }
    }
    //</editor-fold>

    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *  
     * get processing
     * 
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
        //responce type
        response.setContentType("text/plain");
        //variable is responsible for selecting actions
	action = request.getParameter("instance");
        //choice of actions depending on the request parameter
        if(action.equals("createeventform"))
        {
            createEventForm(request, response);//add elements to event form
        }
        else if(action.equals("dayinmonth"))
        {
            getEventsForDay(request, response);//add days events on month view
        }
        else if(action.equals("eventsforday"))
        {
            getEventsForDayView(request, response);
        }
        else
        {
            response.setContentType("text/html");//feil
            response.sendRedirect("feil.jsp");
        }
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * post processing
     * 
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            
    }

    // <editor-fold defaultstate="collapsed" desc="getServletInfo() method">
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
    /**
     * Method createEventForm 
     * adds elements to the modal form to create a new event
     * @param request servlet request
     * @param response servlet response
     * @throws IOException if an I/O error occurs
     */
    private void createEventForm(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        //html code which is returned in response to a request
        StringBuilder answer =  new StringBuilder();
        //generate html code
            answer.append("<table>");
                answer.append("<tr>");
                    answer.append("<td>");
                        answer.append("Create new event:");
                    answer.append("</td>");
                answer.append("</tr>");
                
                answer.append("<tr>");
                    answer.append("<td>");
                        answer.append("Event title:");
                    answer.append("</td>");
                    answer.append("<td>");
                        answer.append("<input name=\"eventtitle\" type=\"text\" id=\"eventtitle\">");
                    answer.append("</td>");
                answer.append("</tr>");
                
                answer.append("<tr>");
                    answer.append("<td>");
                        answer.append("Event type:");
                    answer.append("</td>");
                    //event type selecter
                    answer.append("<td>");
                        List<Eventtype> eventList = getAllEventsType();
                        answer.append("<select name=\"eventtype\" id=\"eventtype\">");
                            for(int i = 0; i < eventList.size(); i++)
                            {
                                answer.append("<option value=\"").append(i+1).append("\">").append(eventList.get(i).getName()).append("</option>");
                            }
                        answer.append("</select>");
                    answer.append("</td>");
                answer.append("</tr>");
                
                //Displaying the items responsible for the selection of the start date of the event
                /*
                answer.append("<tr>");
                    answer.append("<td>");
                        answer.append("From:");
                    answer.append("</td>");
                    answer.append("<td>");
                        
                        answer.append("<select id=\"eventStartHourSelector\">");
                            for(int i = 0; i < 24; i++)
                            {
                                if(i == Calendar.getInstance().get(Calendar.HOUR_OF_DAY))
                                    answer.append("<option value=\"").append(i).append("\" selected>").append(i).append("</option>");
                                else
                                    answer.append("<option value=\"").append(i).append("\">").append(i).append("</option>");
                            }
                        answer.append("</select>");
                        answer.append("<select id=\"eventStartMinuteSelector\">");
                            for(int i = 0; i < 60; i++)
                            {
                                if(i == Calendar.MINUTE)
                                    answer.append("<option value=\"").append(i).append("\" selected>").append(i).append("</option>");
                                else
                                    answer.append("<option value=\"").append(i).append("\">").append(i).append("</option>");
                            }
                        answer.append("</select>"); 
                    answer.append("</td>");

                    
                    answer.append("<td>");
                        answer.append("<input type=\"text\" id=\"eventStartDatePicker\"/>");
                    answer.append("</td>");
                answer.append("</tr>");
                //Displaying the items responsible for the selection of the end date of the event
                answer.append("<tr>");
                    answer.append("<td>");
                        answer.append("To:");
                    answer.append("</td>");
                    answer.append("<td>");
                        answer.append("<select id=\"eventEndHourSelector\">");
                            for(int i = 0; i < 24; i++)
                            {
                                if(i == Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + 1)
                                    answer.append("<option value=\"").append(i).append("\" selected>").append(i).append("</option>");
                                else
                                    answer.append("<option value=\"").append(i).append("\">").append(i).append("</option>");
                            }
                        answer.append("</select>");
                        answer.append("<select id=\"eventEndMinuteSelector\">");
                            for(int i = 0; i < 60; i++)
                            {
                                if(i == Calendar.MINUTE)
                                    answer.append("<option value=\"").append(i).append("\" selected>").append(i).append("</option>");
                                else
                                    answer.append("<option value=\"").append(i).append("\">").append(i).append("</option>");
                            }
                        answer.append("</select>"); 
                    answer.append("</td>");
                    answer.append("<td>");
                        answer.append("<input type=\"text\" id=\"eventEndDatePicker\"/>");
                    answer.append("</td>");
                answer.append("</tr>");
                
                */
                
                answer.append("<tr>");
                    answer.append("<td>");
                        answer.append("Description:");
                    answer.append("</td>");
                    answer.append("<td>");
                        answer.append("<input name=\"eventdescription\" type=\"text\" id=\"eventdescription\">");
                    answer.append("</td>");
                answer.append("</tr>");
                
                /*
                answer.append("<tr>");
                    answer.append("<td>");
                        answer.append("<input type=\"submit\" id=\"regbutton\" value=\"Save Event\" onclick=\"saveEvent()\" />");
                    answer.append("</td>");
                answer.append("</tr>");
                */
                
            answer.append("</table>");
        
        //response type
        response.setContentType("text/plain");
        //send response from servlet
        response.getWriter().write(answer.toString());
    }
    
    /**
     * Method createEventForm 
     * adds elements add events to month view,
     * @param request servlet request
     * @param response servlet response
     * @throws IOException if an I/O error occurs
     */
    private void getEventsForDay(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        //variables from request
        int selectedDay = Integer.parseInt(request.getParameter("day"));
        int selectedMonth = Integer.parseInt(request.getParameter("month")) + 1;
        int selectefYear = Integer.parseInt(request.getParameter("year"));
        //userid from session
        Integer userID = (Integer)httpsession.getAttribute("userID");
        //html code which is returned in response to a request
        StringBuilder answer =  new StringBuilder();
        //list with entity.Events for selected day
        
        Calendar cal = (Calendar) httpsession.getAttribute("watchingDate");
        cal.set(selectefYear, selectedMonth - 1, selectedDay);
        
        
        List dayEvents;
        dayEvents = eventFacade.getEventsByUserIDandDate(userID, selectedMonth, selectedDay, selectefYear);
        //event object
        entity.Event dayEvnt;
        
        //Date formating
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");

        answer.append("<div class=\"row-fluid\">");
        answer.append("<div class=\"span4\">");
        answer.append("<h3><u>")
                    .append(selectedDay).append("/").append(selectedMonth).append("/").append(selectefYear)
                    .append("</u></h3>");
        answer.append("</div>");
        answer.append("<div class=\"span4\">");
        answer.append("<button class=\"btn btn-success btn-large\">Create New Event</button>");
        answer.append("</div>");
        answer.append("</div>");
        
        //generate html code
        answer.append("<table class=\"table-hover span12\">");
            answer.append("<thead class=\"info\">");
                answer.append("<tr>");
                    answer.append("<th class=\"span1\">");
                        answer.append("Start date");
                    answer.append("</th>");
                    answer.append("<th class=\"span1\">");
                        answer.append("End date");
                    answer.append("</th>");
                    answer.append("<th class=\"span2\">");
                        answer.append("Title");
                    answer.append("</th>");
                    answer.append("<th class=\"span1\">");
                        answer.append("Type");
                    answer.append("</th>");
                    answer.append("<th class=\"span7\">");
                        answer.append("Description");
                    answer.append("</th>");
                    answer.append("<th>");
                        answer.append("D");
                    answer.append("</th>");
                    answer.append("<th>");
                        answer.append("E");
                    answer.append("</th>");
                answer.append("</tr>");
            answer.append("</thead>");
            
            answer.append("<tbody>");
            
                for(int i = 0; i < dayEvents.size(); i++)
                {
                    dayEvnt = (entity.Event)dayEvents.get(i);
                    answer.append("<tr>");

                        answer.append("<td>");
                            answer.append(dateFormat.format(dayEvnt.getTimeStart()));
                        answer.append("</td>");
                        answer.append("<td>");
                            answer.append(dateFormat.format(dayEvnt.getTimeEnd()));
                        answer.append("</td>");
                        answer.append("<td>");
                            answer.append(dayEvnt.getTitle());
                        answer.append("</td>");
                        answer.append("<td>");
                            entity.Eventtype evtp = dayEvnt.getType();
                            answer.append(evtp.getName());
                        answer.append("</td>");   
                        answer.append("<td>");
                            answer.append(dayEvnt.getDescription());
                        answer.append("</td>");
                        answer.append("<td>");
                            answer.append("<i class=\"icon-remove\"></i>");
                        answer.append("</td>");
                        answer.append("<td>");
                            answer.append("<i class=\"icon-pencil\"></i>");
                        answer.append("</td>");
                    answer.append("</tr>");
                }
            answer.append("</tbody>");
            
            
        answer.append("</table>");
        //response type
        response.setContentType("text/plain");
        //send response from servlet
        response.getWriter().write(answer.toString());
    }
    
    /**
     * Method getEventsForDayView shows events on day view
     * @param request servlet request
     * @param response servlet response
     * @throws IOException if an I/O error occurs
     */
    private void getEventsForDayView(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        //userid from session
        Integer userID = (Integer)httpsession.getAttribute("userID");
        //html code which is returned in response to a request
        StringBuilder answer =  new StringBuilder();
        //Calendar object form session
        Calendar cal = (Calendar) httpsession.getAttribute("watchingDate");
        //Date
        int selectedDay = cal.get(Calendar.DATE);
        int selectedMonth = cal.get(Calendar.MONTH) + 1;
        int selectefYear = cal.get(Calendar.YEAR);
        
        //list with entity.Events for selected day
        List dayEvents;
        dayEvents = eventFacade.getEventsByUserIDandDate(userID, selectedMonth, selectedDay, selectefYear);
        //event object
        entity.Event dayEvnt;
        //list with information of the number of events beginning within one hour
        List hourEventList = new LinkedList();
        
        
        for(int i = 0; i < 24; i++)
        {
            hourEventList.add(0);
        }
        
        //Calendar instance
        Calendar calendar = Calendar.getInstance();
        //event counter
        int counter = 0;
        for(int i = 0; i < dayEvents.size(); i++)
        {
            dayEvnt = (entity.Event)dayEvents.get(i);
            //event start date
            Date startdate = dayEvnt.getTimeStart();
            calendar.setTime(startdate);
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            counter = (Integer)hourEventList.get(hour);
            counter++;
            hourEventList.set(i, counter);
        }
        
        //generate html code
        int correction = 0;
        for(int i = 0; i < dayEvents.size(); i++)
        {
            //event object
            dayEvnt = (entity.Event)dayEvents.get(i);
            
            //event start date
            Date startdate = dayEvnt.getTimeStart();
            calendar.setTime(startdate);
            System.out.println(startdate);
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            int eventCouner = (Integer)hourEventList.get(hour);
            int minute = calendar.get(Calendar.MINUTE);
            
            int timeStart = (hour * 60 - correction) + minute;
            
            //event start date
            Date endDate = dayEvnt.getTimeEnd();
            calendar.setTime(endDate);
            hour = calendar.get(Calendar.HOUR_OF_DAY);
            minute = calendar.get(Calendar.MINUTE);
            
            int timeStop = (hour * 60 - correction) + minute;
            
            if(eventCouner == 1)
            {
                answer.append("<div id=\"")
                        .append(dayEvnt.getId())
                        .append("\" STYLE=\"position: relative; top:")
                        .append(timeStart)
                        .append("px; left: 50%; height: ")
                        .append(timeStop - timeStart)
                        .append("px; width : 50%;\" class=\"dayevent")
                        .append("\" onclick=\"eventinfo();\">")
                        .append(dayEvnt.getTitle())
                        .append("</div>");
            }
            else
            {
                answer.append("<div id=\"")
                        .append(dayEvnt.getId())
                        .append("\" STYLE=\"position: relative; top:")
                        .append(timeStart)
                        .append("px; height: ")
                        .append(timeStop - timeStart)
                        .append("px; width : 50%;\" class=\"dayevent")
                        .append("\" onclick=\"eventinfo();\">")
                        .append(dayEvnt.getTitle())
                        .append("</div>");
            }
            correction = correction + (timeStop - timeStart);
        }
        
        
        //answer.append("<div id=\"GLAVNIY\" STYLE=\"position: relative; top: 60px; height: 1000px\" class=\"dayevent span6\">42</div>");
        //answer.append("<div id=\"GLAVNIY\" STYLE=\"position: relative; top: 400px\" class=\"dayevent span6\">43</div>");
        //response type
        response.setContentType("text/plain");
        //send response from servlet
        response.getWriter().write(answer.toString());
    }
    
    /**
     * Method getAllEventsType() 
     * @return List<Eventtype> from database
     */
    private List<Eventtype> getAllEventsType()
    {
        List<Eventtype> eventList;
        
        eventList = eventtypeFacade.findAll();
        
        return eventList;
    }
}
