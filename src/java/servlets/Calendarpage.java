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

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
    private void getDay(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        StringBuilder answer =  new StringBuilder();
        int today;
        Calendar calenar = Calendar.getInstance();
        today = calenar.get(Calendar.DATE);
        
        answer.append("<p align=\"center\">Today is ").append(today).append("</p>");
        
        //Create a time field
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
                                answer.append("<div class=\"lineDayOfWeek\" onclick=\"createNewEvent()\">").append(cal.get(cal.DAY_OF_MONTH)).append("/").append(cal.get(cal.MONTH) + 1).append("</div>");
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
    }
    
}
