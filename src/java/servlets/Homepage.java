package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet is responsible for dynamic redrawing of the home page
 * @author Vitaly
 */
public class Homepage extends HttpServlet {

    //Variables
    private HttpSession httpsession;
    private String action = null;
    
    // <editor-fold defaultstate="collapsed" desc="processReques">
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
    // </editor-fold>
    
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * get processing
     * generate dynamic code for user login, user registration and restore password
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
        //html code which is returned in response to a request
        StringBuilder answer =  new StringBuilder();
        //variable is responsible for selecting actions
        action = request.getParameter("ac");
        //choice of action, depending on the variable being passed
        if(action.equals("sing"))//create a login form for registered user
        {
            //html code generation
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
            //responce type
            response.setContentType("text/plain");
            //send response from servlet
            response.getWriter().write(answer.toString());
        }
        else if(action.equals("reg"))//create a registration form for new user
        {
            answer.append("<form method=\"post\" action=\"registration\">");
            
                answer.append("<div class=\"span12\">");
                    answer.append("<h2>Register new account</h2>");
                answer.append("</div>");
            
                answer.append("<div class=\"row-fluid\">");
                    answer.append("<div class=\"span4\">");
                        answer.append("Username");
                    answer.append("</div>");
                    answer.append("<div class=\"span4\">");
                        answer.append("<input name=\"username\" type=\"text\" id=\"uname\" onkeyup=\"checkusername()\">");
                    answer.append("</div>");
                answer.append("</div>");
                answer.append("<div id=\"unamecheck\" class=\"span12\"></div>");
                    
                answer.append("<div class=\"row-fluid\">");
                    answer.append("<div class=\"span4\">");
                        answer.append("Email");
                    answer.append("</div>");
                    answer.append("<div class=\"span4\">");
                        answer.append("<input name=\"email\" type=\"text\" id=\"uemail\" onkeyup=\"checkemail()\">");
                    answer.append("</div>");
                answer.append("</div>");
                answer.append("<div id=\"uemailcheck\" class=\"span12\"></div>");    

                answer.append("<div class=\"row-fluid\">");
                    answer.append("<div class=\"span4\">");
                        answer.append("Password");
                    answer.append("</div>");
                    answer.append("<div class=\"span4\">");
                        answer.append("<input name=\"password\" type=\"password\" id=\"upass\" onkeyup=\"checkpassword()\">");
                    answer.append("</div>");
                answer.append("</div>");
                answer.append("<div id=\"upasswordcheck\" class=\"span12\"></div>");   
                
                answer.append("<div class=\"row-fluid\">");
                    answer.append("<div class=\"span4\">");
                        answer.append("Password confirmation");
                    answer.append("</div>");
                    answer.append("<div class=\"span4\">");
                        answer.append("<input name=\"passwordconfirmation\" type=\"password\" id=\"upassconf\" onkeyup=\"checkpasswordconf()\">");
                    answer.append("</div>");
                answer.append("</div>");
                answer.append("<div id=\"upasswordconf\" class=\"span12\"></div>"); 
                    
                answer.append("<div class=\"span8 offset2\">");
                    answer.append("<button id=\"regbutton\" type=\"submit\" class=\"btn btn-primary btn-large span=8\" style=\"display:none\">Register</button>");
                answer.append("</div>");
                  
            answer.append("</form>");
            
            //response type
            response.setContentType("text/plain");
            //send response from servlet
            response.getWriter().write(answer.toString());
        }
        else if(action.equals("rest"))//create a restore password form for registered user
        {
            
            answer.append("<div class=\"alert alert-error\">");
                answer.append("<button type=\"button\" class=\"close\" data-dismiss=\"alert\">&times;</button>");
                answer.append("Functionality is turned off before the launch of mail server");
            answer.append("</div>");
            //responce type
            response.setContentType("text/plain");
            //send response from servlet
            response.getWriter().write(answer.toString());
        }
        else//action in the case of transfer of an incorrect argument
        {
            //TO_DO
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
        processRequest(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="getServletInfo">
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
