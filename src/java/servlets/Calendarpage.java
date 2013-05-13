/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import entity.Event;
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

/**
 * Servlet Calendarpage is responsible for displaying dynamic information on the calendar page
 * @author Vitaly
 */
public class Calendarpage extends HttpServlet {

    private HttpSession httpsession;   
    private String action;
    private String period;
    @EJB
    private EventFacade eventFacade;
    
    // <editor-fold defaultstate="collapsed" desc="processRequest.">
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
            out.println("<title>Servlet Calendarpage</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Calendarpage at " + request.getContextPath() + "</h1>");
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
     * processing of the request and call the methods 
     * responsible for the output of various calendar views
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
        response.setContentType("text/html");
        //variable is responsible for selecting actions
	action = request.getParameter("instance");
        //initialize the Calender object
        Calendar cal = Calendar.getInstance();
        //writes an Calendar object to the session
        httpsession.setAttribute("watchingDate", cal);
        
        //choice of actions depending on the request parameter
        if(action.equals("day"))
        {
            //System.out.println(action);
            getDay(request, response);//day view
            httpsession.setAttribute("viewpoint", "day");//save viewpoint object
        }
        else if(action.equals("month"))
        {
            getMonth(request, response);//month view
            httpsession.setAttribute("viewpoint", "month");//save viewpoint object
        }
        else if(action.equals("timeline"))
        {
            timeLine(request, response);//week view
        }
        else
        {
            response.sendRedirect("feil.jsp");//getting invalid parameters from the request
        }
        
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * post processing
     * responsible for navigation on the calendar
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
        response.setContentType("text/html");
        //variable is responsible for selecting actions
	action = request.getParameter("instance");
        //choice of actions depending on the request parameter
        if(action.equals("past"))
        {
            past(request, response);//moving back on the calendar
        }
        else if(action.equals("present"))
        {
            present(request, response);//go to current date on the calendar
        }
        else if(action.equals("future"))
        {
            future(request, response);//moving forward on the calendar
        }
        else
        {}
    }
  
    /**
     * Dynamic day output in calendar area
     * 
     * @param request servlet request
     * @param response servlet response
     * @throws IOException if an I/O error occurs
     */
    private void getDay(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        //html code which is returned in response to a request
        StringBuilder answer =  new StringBuilder();
        int today;//Day
        int month;//month
        int year;
        //initialize the Calender object
        Calendar calendar = (Calendar) httpsession.getAttribute("watchingDate");
        //today date
        today = calendar.get(Calendar.DATE);
        month = calendar.get(Calendar.MONTH) + 1;
        year = calendar.get(Calendar.YEAR);
        
        //output header with date
        answer.append("<div class=\"span4 offset4\">"
                + "<h3>").append(today)
                .append("/")
                .append(month)
                .append("/")
                .append(year)
                .append("</h3>"
                + "</div>");
        
        //output hour element
        //TO_DO
        answer.append("<div class=\"span8\">");
        answer.append("<div class=\"row-fluid span12\">");
        
            answer.append("<div class=\"span2\">");
                for(int i = 0; i < 24; i++)
                {
                if(i < 10)
                    answer.append("<div class=\"eventzone span12\">")
                            .append("0")
                            .append(i)
                            .append(":00")
                            .append("</div>");
                else
                    answer.append("<div class=\"eventzone span12\">")
                            .append(i)
                            .append(":00")
                            .append("</div>");
                }
            answer.append("</div>");
            
            answer.append("<div class=\"span10\">");
                answer.append("<div id=\"eventarea\" STYLE=\"position:inherit;\" class=\"eventarea\">");
                
                    //import
                
                    //userid from session
                    Integer userID = (Integer)httpsession.getAttribute("userID");

                    //list with entity.Events for selected day
                    List dayEvents;
                    dayEvents = eventFacade.getEventsByUserIDandDate(userID, month, today, year);
                    //event object
                    entity.Event dayEvnt;
                    //list with information of the number of events beginning within one hour
                    List hourEventList = new LinkedList();


                    for(int i = 0; i < 24; i++)
                    {
                        hourEventList.add(0);
                    }

                    //Calendar instance
                    Calendar cal = Calendar.getInstance();
                    //event counter
                    int counter = 0;
                    for(int i = 0; i < dayEvents.size(); i++)
                    {
                        dayEvnt = (entity.Event)dayEvents.get(i);
                        //event start date
                        Date startdate = dayEvnt.getTimeStart();
                        cal.setTime(startdate);
                        int hour = cal.get(Calendar.HOUR_OF_DAY);
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
                        cal.setTime(startdate);
                        System.out.println(startdate);
                        int hour = cal.get(Calendar.HOUR_OF_DAY);
                        int eventCouner = (Integer)hourEventList.get(hour);
                        int minute = cal.get(Calendar.MINUTE);

                        int timeStart = (hour * 60 - correction) + minute;

                        //event start date
                        Date endDate = dayEvnt.getTimeEnd();
                        cal.setTime(endDate);
                        hour = cal.get(Calendar.HOUR_OF_DAY);
                        minute = cal.get(Calendar.MINUTE);

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
                
                    //end of import
                
                answer.append("</div>");
            /*
            for(int j = 0 ; j < 60; j=j+5)
            {
                answer.append("<div id=\"").append(i).append("m").append(j).append("\" class=\"minute \"value=\"").append(j).append("\"onclick=\"createevent(this)\">");
                                //answer.append(j);
                answer.append("</div>");
            }
            */
            answer.append("</div>");
        answer.append("</div>");
        answer.append("</div>");
        
        
        //Create a time field
        /*
        for(int i = 0; i < 24; i++)
        {
            answer.append("<table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" margin=\"0\">");
                answer.append("<tr bgcolor=\"red\">");
                    answer.append("<td>");
                        answer.append("<div class=\"hours\">");
                            answer.append(i).append(":00");
                        answer.append("</div>");
                    answer.append("</td>");  
                    answer.append("<td>");
                        answer.append("<div id=\"hour").append(i).append("\" class=\"eventzone\">");
                            for(int j = 0; j < 60; j++)
                            {
                                answer.append("<div id=\"").append(i).append("minute").append(j).append("\" class=\"minute\" value=\"").append(j).append("\"onclick=\"createevent(this)\">");
                                //answer.append(j);
                                answer.append("</div>");
                            }
                        answer.append("</div>");
                    answer.append("</td>");
                answer.append("</tr>");
            answer.append("</table>");
        }
        */
        //responce type
        response.setContentType("text/plain");
        //send response from servlet
        response.getWriter().write(answer.toString());
    }
    
    /**
     * Dynamic month output in calendar area
     * @param request servlet request
     * @param response servlet response
     * @throws IOException if an I/O error occurs
     */
    private void getMonth(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        //html code which is returned in response to a request
        StringBuilder answer =  new StringBuilder();
        //get wathcing date from session
        Calendar calendar = (Calendar) httpsession.getAttribute("watchingDate");
        //get the name of the current month in the required format 
        String month = new SimpleDateFormat("MMM").format(calendar.getTime());
        //variable used in the header to display the month and year
        month = month + " " + calendar.get(Calendar.YEAR);
        //Get userID from session
        Integer userID = (Integer)httpsession.getAttribute("userID");
        //first day in month (0 Sunday - ... - 6 Saturday)
        int firstDayInMonth;
        //month events
        List monthEventsList;
        //get a list of events of the month from the database
        monthEventsList = eventFacade.getMonthEvents(userID,calendar.get(Calendar.MONTH)+1, calendar.get(Calendar.YEAR));
        //System.out.println("***************************************************");
        
        //html code generation
        answer.append("<div class=\"span8\">");
        
        answer.append("<div class=\"span12\">");
            //show current month and year
            answer.append("<div class=\"span2 offset5\">").append("<h4>").append(month).append("</h4>").append("</div>");
                //month view
                answer.append("<table class=\"table table-bordered\">");
                    answer.append("<thead>");
                        answer.append("<tr>");
                            answer.append("<th class=\"span2\">").append("Monday").append("</th>");
                            answer.append("<th class=\"span2\">").append("Tuesday").append("</th>");
                            answer.append("<th class=\"span2\">").append("Wednesday").append("</th>");
                            answer.append("<th class=\"span2\">").append("Thursday").append("</th>");
                            answer.append("<th class=\"span2\">").append("Friday").append("</th>");
                            answer.append("<th class=\"span2\">").append("Saturday").append("</th>");
                            answer.append("<th class=\"span2\">").append("Sunday").append("</th>");
                        answer.append("</tr>");
                    answer.append("</thead>");
                    //get the first day of a month
                    calendar.set(Calendar.DAY_OF_MONTH,Calendar.getInstance().getActualMinimum(Calendar.DAY_OF_MONTH));
                    firstDayInMonth = calendar.get(Calendar.DAY_OF_WEEK);
                    
                    //change format of the week (start from monday)
                    if(firstDayInMonth == 1)
                        firstDayInMonth = 7;
                    else
                        firstDayInMonth = firstDayInMonth - 1;
                    
                    //number of weeks in month
                    int weeksInMonth = calendar.getActualMaximum(Calendar.WEEK_OF_MONTH);
                    //number of days in month
                    int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
                    //an information list with days some have events, is used to mark the event on month view
                    LinkedList<Boolean> daysWithEventsList = new LinkedList<Boolean>();
                    //list filling
                    for(int i = 0; i < daysInMonth + 1; i++)
                    {
                        daysWithEventsList.add(false);
                    }
                    //entity.Event object
                    entity.Event dayEvent;
                    //event date
                    Date eventDate;
                    //calendar instance
                    Calendar eventCalendar = Calendar.getInstance();
                    //event date(day)
                    int eventDay;
                    //find the date of the event began and note the date in the list
                    //change false to true
                    for(int i = 0; i < monthEventsList.size(); i++)
                    {
                        dayEvent = (Event) monthEventsList.get(i);
                        eventDate = dayEvent.getTimeStart();
                        eventCalendar.setTime(eventDate);
                        eventDay = eventCalendar.get(Calendar.DAY_OF_MONTH);                        
                        daysWithEventsList.set(eventDay, true);
                    }
                    
                    //day number in  month
                    int dayInMonth = 1;
                    
                    //dynamisk output of the month
                    for (int i = 0; i <= daysInMonth; i = dayInMonth)
                    {
                    answer.append("<tbody>");
                        //first week
                        if(i == 0)
                        {
                            answer.append("<tr>");
                            for(int j = 1; j < 8; j++)
                            {
                                //placeholders (days are not included in a rolling month)
                                if(j < firstDayInMonth)
                                {
                                    answer.append("<td>");
                                        answer.append(" ");
                                    answer.append("</td>");
                                }
                                //first week of the month
                                else
                                {
                                    //mark saturday og sunday
                                    if(j==6|j==7)
                                    {
                                        //check for events in day
                                        if(daysWithEventsList.get(dayInMonth) == true)
                                        {
                                        answer.append("<td class=\"weekend\">");
                                            answer.append("<div id=\"").append(dayInMonth).append("h")
                                                    .append(calendar.get(Calendar.MONTH))
                                                    .append("h")
                                                    .append(calendar.get(Calendar.YEAR))
                                                    .append("\" class=\"span12\" onclick=\"zoomDay(this);\">")
                                                    .append("<div class=\"span3\">")
                                                    .append(dayInMonth)
                                                    .append("</div>")
                                                    .append("<div class=\"span3 eventm\">")
                                                    .append("</div>")
                                                    .append("</div>");
                                        answer.append("</td>");
                                        dayInMonth++;
                                        }
                                        //dont have any events
                                        else
                                        {
                                        answer.append("<td class=\"weekend\">");
                                            answer.append("<div id=\"").append(dayInMonth)
                                                    .append("h")
                                                    .append(calendar.get(Calendar.MONTH))
                                                    .append("h")
                                                    .append(calendar.get(Calendar.YEAR))
                                                    .append("\" class=\"span12\" onclick=\"zoomDay(this);\">")
                                                    .append("<div class=\"span3\">")
                                                    .append(dayInMonth)
                                                    .append("</div>")
                                                    .append("</div>");
                                        answer.append("</td>");
                                        dayInMonth++;
                                        }
                                    }
                                    //days from monday do friday
                                    else
                                    {
                                        //check for events in day
                                        if(daysWithEventsList.get(dayInMonth) == true)
                                        {
                                        answer.append("<td>");
                                            answer.append("<div id=\"").append(dayInMonth).append("h")
                                                    .append(calendar.get(Calendar.MONTH))
                                                    .append("h")
                                                    .append(calendar.get(Calendar.YEAR))
                                                    .append("\" class=\"span12\" onclick=\"zoomDay(this);\">")
                                                    .append("<div class=\"span3\">")
                                                    .append(dayInMonth)
                                                    .append("</div>")
                                                    .append("<div class=\"span3 eventm\">")
                                                    .append("</div>")
                                                    .append("</div>");
                                        answer.append("</td>");
                                        dayInMonth++;
                                        }
                                        //dont have any events
                                        else
                                        {
                                        answer.append("<td>");
                                            answer.append("<div id=\"").append(dayInMonth)
                                                    .append("h")
                                                    .append(calendar.get(Calendar.MONTH))
                                                    .append("h")
                                                    .append(calendar.get(Calendar.YEAR))
                                                    .append("\" class=\"span12\" onclick=\"zoomDay(this);\">")
                                                    .append(dayInMonth)
                                                    .append("</div>");
                                        answer.append("</td>");
                                        dayInMonth++;
                                        }
                                    }
                                }
                            }
                            answer.append("</tr>");
                        }
                        //all weeks exept the first one
                        else
                        {
                            answer.append("<tr>");
                            //start week from monday
                            for(int j = 0; j < 7; j++)
                            {
                                //if there is any days in month
                                if(dayInMonth <= daysInMonth)
                                {
                                    //mark saturday og sunday
                                    if(j==5|j==6)
                                    {
                                        //check for events
                                        if(daysWithEventsList.get(dayInMonth)==true)
                                        {
                                        answer.append("<td class=\"weekend\">");
                                            answer.append("<div id=\"")
                                                    .append(dayInMonth)
                                                    .append("h")
                                                    .append(calendar.get(Calendar.MONTH))
                                                    .append("h")
                                                    .append(calendar.get(Calendar.YEAR))
                                                    .append("\" class=\"span12\" onclick=\"zoomDay(this);\">")
                                                    .append("<div class=\"span3\">")
                                                    .append(dayInMonth)
                                                    .append("</div>")
                                                    .append("<div class=\"span3 eventm\">")
                                                    .append("</div>")
                                                    
                                                    .append("</div>");
                                        answer.append("</td>");
                                        dayInMonth++;
                                        }
                                        //dont have any events
                                        else
                                        {
                                        answer.append("<td class=\"weekend\">");
                                            answer.append("<div id=\"")
                                                    .append(dayInMonth)
                                                    .append("h")
                                                    .append(calendar.get(Calendar.MONTH))
                                                    .append("h")
                                                    .append(calendar.get(Calendar.YEAR))
                                                    .append("\" class=\"span12\" onclick=\"zoomDay(this);\">")
                                                    .append("<div class=\"span3\">")
                                                    .append(dayInMonth)
                                                    .append("</div>")
                                                    .append("</div>");
                                        answer.append("</td>");
                                        dayInMonth++;
                                        }
                                    }
                                    //days from monday to friday
                                    else
                                    {
                                        //check for events
                                        if(daysWithEventsList.get(dayInMonth)==true)
                                        {
                                        answer.append("<td>");
                                            answer.append("<div id=\"")
                                                    .append(dayInMonth)
                                                    .append("h")
                                                    .append(calendar.get(Calendar.MONTH))
                                                    .append("h")
                                                    .append(calendar.get(Calendar.YEAR))
                                                    .append("\" class=\"span12\" onclick=\"zoomDay(this);\">")
                                                    .append("<div class=\"span3\">")
                                                    .append(dayInMonth)
                                                    .append("</div>")
                                                    .append("<div class=\"span3 eventm\">")
                                                    .append("</div>")
                                                    .append("</div>");
                                        answer.append("</td>");
                                        dayInMonth++;
                                        }
                                        //dont have events
                                        else
                                        {
                                        answer.append("<td>");
                                            answer.append("<div id=\"")
                                                    .append(dayInMonth)
                                                    .append("h")
                                                    .append(calendar.get(Calendar.MONTH))
                                                    .append("h")
                                                    .append(calendar.get(Calendar.YEAR))
                                                    .append("\" class=\"span12\" onclick=\"zoomDay(this);\">")
                                                    .append(dayInMonth)
                                                    .append("</div>");
                                        answer.append("</td>");
                                        dayInMonth++;
                                        }
                                    }
                                    
                                }
                                //dont have any days
                                else
                                {
                                    answer.append("<td>");
                                        answer.append(" ");
                                    answer.append("</td>");
                                }
                            }
                            answer.append("</tr>");
                        }
                    answer.append("</tbody>");
                    }
                    
                answer.append("</table>");
            
        answer.append("</div>");
        
        answer.append("<div id=\"selectedDayEvents\" class=\"span11\">");
        answer.append("</div>");
        
        answer.append("</div>");
        //response type
        response.setContentType("text/plain");
        //send response from servlet
        response.getWriter().write(answer.toString());
    }
    
    /**
     * timeline type definition
     * method is simplified 
     * @param request servlet request
     * @param response servlet response
     * @throws IOException if an I/O error occurs
     */
    private void timeLine(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        //get view period from session
        period = (String)request.getParameter("period");
        if(period.equals("week"))
        {
            timeLineWeek(request, response);//dynamic week view
            httpsession.setAttribute("viewpoint", "timeLineWeek");//save view point in session
        }
        else
        {
            response.setContentType("text/html");
            response.sendRedirect("feil.jsp");
        }
    }
    
    /**
     * Dynamic week output in calendar area
     * @param request servlet request
     * @param response servlet response
     * @throws IOException if an I/O error occurs 
     */
    private void timeLineWeek(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        //html code which is returned in response to a request
        StringBuilder answer =  new StringBuilder();
        //Get userID from session
        Integer userID = (Integer)httpsession.getAttribute("userID");
        //List of events for week
        List weekEvents = new LinkedList();
        //List of events for day
        List userEventsList;

        int oneStep = 1;
        //get wathcing date from session
        Calendar cal = (Calendar) httpsession.getAttribute("watchingDate");
        
        //maximum number of events per day, it is necessary 
        //to determine the number of rows in the generated table
        int maxiumNumberOfEvents = 0;
        //date changing for 4 days before wathing dte because we make a step in the begin
        cal.add(cal.DAY_OF_MONTH, -4);
        
        //Define the maximum number of events
        for(int i = 0; i < 7; i++)
        {
            //starting for 3 days before wathing date
            cal.add(cal.DAY_OF_MONTH, oneStep);
            //get the list of the events for a day
            userEventsList = eventFacade.getEventsByUserIDandDate(userID,cal.get(cal.MONTH)+1,cal.get(cal.DAY_OF_MONTH),cal.get(cal.YEAR));
            weekEvents.add(userEventsList);
            //change the maxium number of events
            if(userEventsList.size() > maxiumNumberOfEvents)
                maxiumNumberOfEvents = userEventsList.size();   
        }
        //change the date by 7 days ago for week view generation
        cal.add(cal.DAY_OF_MONTH, -7);
        
        answer.append("<table>");
            answer.append("<tr>");       
        //week cycle
        for(int i = 0; i < 7; i++)      
        {   
            cal.add(cal.DAY_OF_MONTH, oneStep);
            
                answer.append("<td valign=\"top\">");
                    answer.append("<table>");
                        answer.append("<tr>");
                            answer.append("<td <td align=\"center\" valign=\"middle\">");
                                answer.append("<div id=\"").append(cal.get(cal.DAY_OF_MONTH)).append(cal.get(cal.MONTH) + 1).append("\" class=\"lineDayOfWeek\" onclick=\"timelineWeekCreateNewEvent()\">").append(cal.get(cal.DAY_OF_MONTH)).append("/").append(cal.get(cal.MONTH) + 1).append("</div>");//
                            answer.append("</td>");
                        answer.append("</tr>");
                        
                        userEventsList = (List) weekEvents.get(i);
                        //Print events from day
                        for (int j = 0; j < userEventsList.size(); j++)
                        {
                        entity.Event dayEvent = (entity.Event) userEventsList.get(j);
                        
                        answer.append("<tr>");
                            answer.append("<td>");
                                answer.append("<div class=\"lineDayOfWeek\" onclick=\"showEvent()\">").append(dayEvent.getTitle()).append("</div>");
                            answer.append("</td>");
                        answer.append("</tr>");
                        }
                        
                    answer.append("</table>");
                answer.append("</td>");
            
        }        
                
            answer.append("</tr>");        
        answer.append("</table>");
        //return date in the initial position
        cal.add(cal.DAY_OF_MONTH, -3);
        //responce type
        response.setContentType("text/plain");
        //send responce from servlet
        response.getWriter().write(answer.toString());
    }
    
    /**
     * Method past is responsible for the change of calendar date and 
     * calling redrawing method (go back in time)
     * @param request servlet request
     * @param response servlet response
     * @throws IOException if an I/O error occurs 
     */
    private void past(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        //getting viewpoint from session
        String viewpoint = (String)httpsession.getAttribute("viewpoint");
        //date change
        if(viewpoint.equals("timeLineWeek"))
        {
            Calendar cal = (Calendar) httpsession.getAttribute("watchingDate");
            
            cal.add(cal.DAY_OF_MONTH, -3);
            timeLineWeek(request, response);
        }
        else if(viewpoint.equals("month"))
        {
            Calendar cal = (Calendar) httpsession.getAttribute("watchingDate");
            cal.add(cal.MONTH, -1);
            getMonth(request, response);
        }
        else if(viewpoint.equals("day"))
        {
            Calendar cal = (Calendar) httpsession.getAttribute("watchingDate");
            cal.add(cal.DAY_OF_MONTH, -1);
            getDay(request, response);
        }
    }
    
    /**
     * Method past is responsible for the change of calendar date and 
     * calling redrawing method (go to the current date)
     * @param request servlet request
     * @param response servlet response
     * @throws IOException if an I/O error occurs 
     */
    private void present(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        //getting viewpoint frokm session
        String viewpoint = (String)httpsession.getAttribute("viewpoint");
        //date change
        if(viewpoint.equals("timeLineWeek"))
        {
            Calendar cal = Calendar.getInstance();
            httpsession.setAttribute("watchingDate", cal);
            timeLineWeek(request, response);
        }
        else if(viewpoint.equals("month"))
        {
            Calendar cal = Calendar.getInstance();
            httpsession.setAttribute("watchingDate", cal);
            getMonth(request, response);
        }
        else if(viewpoint.equals("day"))
        {
            Calendar cal = Calendar.getInstance();
            httpsession.setAttribute("watchingDate", cal);
            getDay(request, response);
        }
    }
    
    /**
     * Method past is responsible for the change of calendar date and 
     * calling redrawing method (go to the future)
     * @param request servlet request
     * @param response servlet response
     * @throws IOException if an I/O error occurs 
     */
    private void future(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        //getting viewpoint from session
        String viewpoint = (String)httpsession.getAttribute("viewpoint");
        //date change
        if(viewpoint.equals("timeLineWeek"))
        {
            Calendar cal = (Calendar) httpsession.getAttribute("watchingDate");
            cal.add(cal.DAY_OF_MONTH, 3);
            timeLineWeek(request, response);
        }
        else if(viewpoint.equals("month"))
        {
            Calendar cal = (Calendar) httpsession.getAttribute("watchingDate");
            cal.add(cal.MONTH, 1);
            getMonth(request, response);
        }
        else if(viewpoint.equals("day"))
        {
            Calendar cal = (Calendar) httpsession.getAttribute("watchingDate");
            cal.add(cal.DAY_OF_MONTH, 1);
            getDay(request, response);
        }
    }
    
    // <editor-fold defaultstate="collapsed" desc="getServletInfo.">
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
