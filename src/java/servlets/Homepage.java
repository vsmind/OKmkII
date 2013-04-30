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
            
                answer.append("<table>");
                    answer.append("<tr>");
                        answer.append("<td>");
                            answer.append("Sign in to your account");
                        answer.append("</td>");
                    answer.append("</tr>");
                    
                    answer.append("</tr>");
                        answer.append("<td>");
                            answer.append("Username");
                        answer.append("</td>");
                        answer.append("<td>");
                            answer.append("<input name=\"username\" type=\"text\">");
                        answer.append("</td>");
                    answer.append("</tr>");
                    
                    answer.append("</tr>");
                        answer.append("<td>");
                            answer.append("Password");
                        answer.append("</td>");
                        answer.append("<td>");
                            answer.append("<input name=\"password\" type=\"password\">");
                        answer.append("</td>");
                    answer.append("</tr>");
                    
                    answer.append("</tr>");
                        answer.append("<td>");
                            
                        answer.append("</td>");
                        answer.append("<td>");
                            answer.append("<input type=\"submit\" value=\"Login\">");
                        answer.append("</td>");
                    answer.append("</tr>");
                    

                    
                    
                answer.append("</table>");
            answer.append("</form>");
            
            answer.append("<button onclick=\"restorePassword()\">Restore Password</button>");
            answer.append("<div id=\"restoreform\"></div>");
            
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
            answer.append("<form method=\"post\" action=\"Restorepassword\">");
                answer.append("<table>");
                    answer.append("<tr>");
                        answer.append("<td>"); 
                            answer.append("<input name=\"restemail\" type=\"text\" id=\"remail\" />");
                        answer.append("</td>");
                        answer.append("<td>"); 
                            answer.append("<input type=\"submit\" value=\"Register\"/>");
                        answer.append("</td>");                  
                    answer.append("</tr>");                   
            answer.append("</table>");
            answer.append("</form>");
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
