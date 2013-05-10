/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

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
 *
 * @author Vitaly
 */
public class Calendarpage extends HttpServlet {

    private HttpSession httpsession;   
    private String action;
    private String period;
    @EJB
    private EventFacade eventFacade;
    
    @Deprecated
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
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        httpsession = request.getSession();
        response.setContentType("text/html");

	action = request.getParameter("instance");
        
        Calendar cal = Calendar.getInstance();
        httpsession.setAttribute("watchingDate", cal);
        
        if(action.equals("day"))
        {
            //System.out.println(action);
            getDay(request, response);
            httpsession.setAttribute("viewpoint", "day");
        }
        else if(action.equals("month"))
        {
            getMonth(request, response);
            httpsession.setAttribute("viewpoint", "month");
        }
        else if(action.equals("timeline"))
        {
            timeLine(request, response);
        }
        else
        {
            response.sendRedirect("feil.jsp");
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
        httpsession = request.getSession();
        response.setContentType("text/html");

	action = request.getParameter("instance");
        
        if(action.equals("past"))
        {
            past(request, response);
        }
        else if(action.equals("present"))
        {
            present(request, response);
        }
        else if(action.equals("future"))
        {
            future(request, response);
        }
        else
        {}
    }
  
    private void getDay(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        StringBuilder answer =  new StringBuilder();
        int today;
        Calendar calenar = Calendar.getInstance();
        today = calenar.get(Calendar.DATE);
        
        answer.append("<div class=\"span4 offset4\">"
                + "<h3>").append(today).append("</h3>"
                + "</div>");
        
        for(int i = 0; i < 24; i++)
        {
        answer.append("<div class=\"row-fluid span12\">");
        
            answer.append("<div class=\"eventzone span2\">");
                if(i < 10)
                    answer.append("0").append(i).append(":00");
                else
                    answer.append(i).append(":00");
            answer.append("</div>");
            
            answer.append("<div class=\"span10\">");
            answer.append("<div class=\"testdiv\">5</div>");
            for(int j = 0 ; j < 60; j=j+5)
            {
                answer.append("<div id=\"").append(i).append("m").append(j).append("\" class=\"minute \"value=\"").append(j).append("\"onclick=\"createevent(this)\">");
                                //answer.append(j);
                answer.append("</div>");
            }
            answer.append("</div>");
        answer.append("</div>");
        }
        
        
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
        response.setContentType("text/plain");
        response.getWriter().write(answer.toString());
    }
    
    private void getMonth(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        StringBuilder answer =  new StringBuilder();
        Calendar calenar = (Calendar) httpsession.getAttribute("watchingDate");
        //get the name of the current month in the required format 
        String month = new SimpleDateFormat("MMM").format(calenar.getTime());
        
        month = month + " " + calenar.get(Calendar.YEAR);
        
        int firstDayInMonth;
        
        answer.append("<div class=\"row-fluid span8\">");
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
                    calenar.set(Calendar.DAY_OF_MONTH,Calendar.getInstance().getActualMinimum(Calendar.DAY_OF_MONTH));
                    firstDayInMonth = calenar.get(Calendar.DAY_OF_WEEK);
                    
                    System.out.println(firstDayInMonth);
                    //change format of the week (start from monday)
                    if(firstDayInMonth == 1)
                        firstDayInMonth = 7;
                    else
                        firstDayInMonth = firstDayInMonth - 1;
                    
                    //number of weeks in month
                    int weeksInMonth = calenar.getActualMaximum(Calendar.WEEK_OF_MONTH);
                    //number of days in month
                    int daysInMonth = calenar.getActualMaximum(Calendar.DAY_OF_MONTH);
                    
                    System.out.println(firstDayInMonth);
                    System.out.println(weeksInMonth);
                    System.out.println(daysInMonth);
                    
                    int dayInMonth = 1;
                    
                    for (int i = 0; i <= daysInMonth; i = dayInMonth)
                    {
                    answer.append("<tbody>");
                        //first week
                        if(i == 0)
                        {
                            answer.append("<tr>");
                            for(int j = 1; j < 8; j++)
                            {
                                if(j < firstDayInMonth)
                                {
                                    answer.append("<td>");
                                        answer.append(" ");
                                    answer.append("</td>");
                                }
                                else
                                {
                                    //mark saturday og sunday
                                    if(j==6|j==7)
                                    {
                                        answer.append("<td class=\"weekend\">");
                                            answer.append("<div id=\"").append(dayInMonth).append("h").append(calenar.get(Calendar.MONTH)).append("h").append(calenar.get(Calendar.YEAR)).append("\" onclick=\"zoomDay(this);\">").append(dayInMonth).append("</div>");
                                        answer.append("</td>");
                                        dayInMonth++;
                                    }
                                    else
                                    {
                                        answer.append("<td>");
                                            answer.append("<div id=\"").append(dayInMonth).append("h").append(calenar.get(Calendar.MONTH)).append("h").append(calenar.get(Calendar.YEAR)).append("\" onclick=\"zoomDay(this);\">").append(dayInMonth).append("</div>");
                                        answer.append("</td>");
                                        dayInMonth++;
                                    }
                                }
                            }
                            answer.append("</tr>");
                        }
                        else
                        {
                            answer.append("<tr>");
                            for(int j = 0; j < 7; j++)
                            {
                                if(dayInMonth <= daysInMonth)
                                {
                                    //mark saturday og sunday
                                    if(j==5|j==6)
                                    {
                                        answer.append("<td class=\"weekend\">");
                                            answer.append("<div id=\"").append(dayInMonth).append("h").append(calenar.get(Calendar.MONTH)).append("h").append(calenar.get(Calendar.YEAR)).append("\" onclick=\"zoomDay(this);\">").append(dayInMonth).append("</div>");
                                        answer.append("</td>");
                                        dayInMonth++;
                                    }
                                    else
                                    {
                                        answer.append("<td>");
                                            answer.append("<div id=\"").append(dayInMonth).append("h").append(calenar.get(Calendar.MONTH)).append("h").append(calenar.get(Calendar.YEAR)).append("\" onclick=\"zoomDay(this);\">").append(dayInMonth).append("</div>");
                                        answer.append("</td>");
                                        dayInMonth++;
                                    }
                                    
                                }
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
        
        response.setContentType("text/plain");
        response.getWriter().write(answer.toString());
    }
    
    private void timeLine(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        period = (String)request.getParameter("period");
        if(period.equals("week"))
        {
            timeLineWeek(request, response);
            httpsession.setAttribute("viewpoint", "timeLineWeek");
        }
        else
        {
            response.setContentType("text/html");
            response.sendRedirect("feil.jsp");
        }
    }
    
    private void timeLineWeek(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        StringBuilder answer =  new StringBuilder();
        //Get userID from session
        Integer userID = (Integer)httpsession.getAttribute("userID");
        //List of events for week
        List weekEvents = new LinkedList();
        //List of events for day
        List userEventsList;

        int oneStep = 1;
        
        Calendar cal = (Calendar) httpsession.getAttribute("watchingDate");

        int maxiumNumberOfEvents = 0;
        cal.add(cal.DAY_OF_MONTH, -4);

        for(int i = 0; i < 7; i++)
        {
            cal.add(cal.DAY_OF_MONTH, oneStep);
            
            userEventsList = eventFacade.getEventsByUserIDandDate(userID,cal.get(cal.MONTH)+1,cal.get(cal.DAY_OF_MONTH),cal.get(cal.YEAR));
            weekEvents.add(userEventsList);
            
            if(userEventsList.size() > maxiumNumberOfEvents)
                maxiumNumberOfEvents = userEventsList.size();   
        }
        
        cal.add(cal.DAY_OF_MONTH, -7);
        
        answer.append("<table>");
            answer.append("<tr>");       
 
          
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
        
        cal.add(cal.DAY_OF_MONTH, -3);
        
        response.setContentType("text/plain");
        response.getWriter().write(answer.toString());
    }
    
    private void past(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        String viewpoint = (String)httpsession.getAttribute("viewpoint");
        
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
    }
    
    private void present(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        String viewpoint = (String)httpsession.getAttribute("viewpoint");
        
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
    }
    
    private void future(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        String viewpoint = (String)httpsession.getAttribute("viewpoint");
        
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
