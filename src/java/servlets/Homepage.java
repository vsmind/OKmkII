/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Vitaly
 */
public class Homepage extends HttpServlet {

    private HttpSession httpsession;
    private String action = null;
    
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
            out.println("<title>Servlet homepage</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet homepage at " + request.getContextPath() + "</h1>");
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
        StringBuilder answer =  new StringBuilder();
        
        action = request.getParameter("ac");
        if(action.equals("sing"))
        {
            answer.append("<form method=\"post\" action=\"Login\">");
                
                answer.append("<div class=\"span12\">");
                    answer.append("<h2>Sign in to your account</h2>");
                answer.append("</div>");
            
                answer.append("<div class=\"row-fluid\">");
                    answer.append("<div class=\"span4\">");
                        answer.append("Username");
                    answer.append("</div>");
                    answer.append("<div class=\"span4\">");
                        answer.append("<input name=\"username\" type=\"text\">");
                    answer.append("</div>");
                answer.append("</div>");
                
                answer.append("<div class=\"row-fluid\">");
                    answer.append("<div class=\"span4\">");
                        answer.append("Password");
                    answer.append("</div>");
                    answer.append("<div class=\"span4\">");
                        answer.append("<input name=\"password\" type=\"password\">");
                    answer.append("</div>");
                answer.append("</div>");
                
                answer.append("<div class=\"span8 offset2\">");
                    answer.append("<button type=\"submit\" class=\"btn btn-primary btn-large span=8\">Login</button>");
                answer.append("</div>");
 
            answer.append("</form>");
            
                answer.append("<div class=\"span8 offset2\">");
                    answer.append("<button class=\"btn btn-info span=6\" onclick=\"restorePassword()\">Restore Password</button>");
                answer.append("</div>");
                
            answer.append("<div id=\"restoreform\" class=\"span12\"></div>");
            
            response.setContentType("text/plain");
            response.getWriter().write(answer.toString());
        }
        else if(action.equals("reg"))
        {
            answer.append("<form method=\"post\" action=\"registration\">");
            
                answer.append("<table>");
                    answer.append("<tr>");
                        answer.append("<td>");
                            answer.append("Register new account");
                        answer.append("</td>");
                        answer.append("<td>");

                        answer.append("</td>");
                    answer.append("</tr>");
                    
                    answer.append("</tr>");
                        answer.append("<td>");
                            answer.append("Username");
                        answer.append("</td>");
                        answer.append("<td>");
                            answer.append("<input name=\"username\" type=\"text\" id=\"uname\" onkeyup=\"checkusername()\">");
                        answer.append("</td>");
                        answer.append("<td>");
                            answer.append("<div id=\"unamecheck\"></div>");
                        answer.append("</td>");
                    answer.append("</tr>");
                    
                    answer.append("</tr>");
                        answer.append("<td>");
                            answer.append("Email");
                        answer.append("</td>");
                        answer.append("<td>");
                            answer.append("<input name=\"email\" type=\"text\" id=\"uemail\" onkeyup=\"checkemail()\">");
                        answer.append("</td>");
                        answer.append("<td>");
                            answer.append("<div id=\"uemailcheck\"></div>");
                        answer.append("</td>");
                    answer.append("</tr>");
                    
                    answer.append("</tr>");
                        answer.append("<td>");
                            answer.append("Password");
                        answer.append("</td>");
                        answer.append("<td>");
                            answer.append("<input name=\"password\" type=\"password\" id=\"upass\" onkeyup=\"checkpassword()\">");
                        answer.append("</td>");
                        answer.append("<td>");
                            answer.append("<div id=\"upasswordcheck\"></div>");
                        answer.append("</td>");
                    answer.append("</tr>");
                    
                    answer.append("</tr>");
                        answer.append("<td>");
                            answer.append("Password confirmation");
                        answer.append("</td>");
                        answer.append("<td>");
                            answer.append("<input name=\"passwordconfirmation\" type=\"password\" id=\"upassconf\" onkeyup=\"checkpasswordconf()\">");
                        answer.append("</td>");
                        answer.append("<td>");
                            answer.append("<div id=\"upasswordconf\"></div>");
                        answer.append("</td>");
                    answer.append("</tr>");
                    
                    answer.append("</tr>");
                        answer.append("<td>");
                            
                        answer.append("</td>");
                        answer.append("<td>");
                            answer.append("<input type=\"submit\" id=\"regbutton\" value=\"Register\" style=\"display:none\"/>");
                        answer.append("</td>");
                    answer.append("</tr>");
                                      
                answer.append("</table>");
            answer.append("</form>");
            
            
            response.setContentType("text/plain");
            response.getWriter().write(answer.toString());
        }
        else if(action.equals("rest"))
        {
            
            answer.append("<div class=\"alert alert-error\">");
                answer.append("<button type=\"button\" class=\"close\" data-dismiss=\"alert\">&times;</button>");
                answer.append("Functionality is turned off before the launch of mail server");
            answer.append("</div>");
            
            response.setContentType("text/plain");
            response.getWriter().write(answer.toString());
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
