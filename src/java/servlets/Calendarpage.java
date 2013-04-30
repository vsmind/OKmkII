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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Vitaly
 */
public class Calendarpage extends HttpServlet {

    private HttpSession httpsession;   
    private String action;
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
        
        if(action.equals("day"))
        {
            System.out.println(action);
            getDay(request, response);
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
        processRequest(request, response);
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
                        answer.append("<div id=\"hour").append(i).append("\" class=\"eventzone\" onclick=\"createevent()\">");
                            answer.append("");
                        answer.append("</div>");
                    answer.append("</td>");
                answer.append("</tr>");
            answer.append("</table>");
        }
        
        response.setContentType("text/plain");
        response.getWriter().write(answer.toString());
    }
    
    private void goBack()
    {
    
    }
}
