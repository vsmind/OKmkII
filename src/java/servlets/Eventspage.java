/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import entity.Eventtype;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.EventtypeFacade;

/**
 *
 * @author Vitaly
 */
public class Eventspage extends HttpServlet {

    private HttpSession httpsession;   
    private String action;
    @EJB
    EventtypeFacade eventtypeFacade;
    
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
        response.setContentType("text/plain");

	action = request.getParameter("instance");
        
        if(action.equals("createeventform"))
        {
            createEventForm(request, response);
        }
        else
        {
            response.setContentType("text/html");
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
    
    private void createEventForm(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        StringBuilder answer =  new StringBuilder();
        
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
        
        
        response.setContentType("text/plain");
        response.getWriter().write(answer.toString());
    }
    
    private List<Eventtype> getAllEventsType()
    {
        List<Eventtype> eventList;
        
        eventList = eventtypeFacade.findAll();
        
        return eventList;
    }
}
