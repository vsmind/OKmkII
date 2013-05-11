/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.UserFacade;

/**
 * Servlet is responsible for user login
 * @author Vitaly
 */
@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {
    //variables
    private HttpSession httpsession;   
    private String username = null;
    private String password = null;
    @EJB
    private UserFacade userFacade;
    
    // <editor-fold defaultstate="collapsed" desc="processRequest()">
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
            out.println("<title>Servlet Login</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Login at " + request.getContextPath() + "</h1>");
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
     * 
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * post processing
     * check user name and password of user and redirect forward if all correct
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
        //response type
	response.setContentType("text/html");
        //values from request
	username = request.getParameter("username");
        password = request.getParameter("password");
        
        //check username and password and choose action
        if ((!username.equals(""))&(!password.equals("")))
        {
            if (checkUser())//correct
            {
                response.sendRedirect("calendar.jsp");
            }
            else//feil
            {
            	response.sendRedirect("index.jsp");
                httpsession.setAttribute("loginerror", "Password incorrect");
            }
        }
        else//feil
        {
        	response.sendRedirect("index.jsp");
                httpsession.setAttribute("loginerror", "User doesn't exist");
        }
        
    }

    // <editor-fold defaultstate="collapsed" desc="getServletInfo()">
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
     * Method checkUser checks username and password
     * @return boolean 
     */
    protected boolean checkUser()
    {
        boolean access = false;
        //get user object from DB
        User user = userFacade.getUserByUsername(username);
        
        if(user!=null)//check if user exists
        {
            if(user.getPassword().equals(password))//checks password
            {
                access = true;
                //save user attributes to session for further work
                httpsession.setAttribute("user", user);
                httpsession.setAttribute("username", username);
                httpsession.setAttribute("userID", user.getId());
            }
        }
        return access;
    }
}
