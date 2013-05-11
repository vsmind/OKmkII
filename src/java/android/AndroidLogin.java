/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package android;

import com.google.gson.Gson;
import entity.User;
import help.ConnectionResult;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.UserFacade;

/**
 * Servlet responsible for Android connection
 * @author Vitaly
 */
public class AndroidLogin extends HttpServlet {
    //Variables
    private HttpSession httpsession;   
    private String username = null;
    private String password = null;
    @EJB
    private UserFacade userFacade;
    
    // <editor-fold defaultstate="collapsed" desc="processReques method.">
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
            out.println("<title>Servlet AndroidLogin</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AndroidLogin at " + request.getContextPath() + "</h1>");
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
     * method responsible for user login check
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
        //variables from session
        username = request.getParameter("username");
        password = request.getParameter("password");
        //converted object used as a response from the server
        ConnectionResult result = new ConnectionResult();
        //Gson object
        Gson gson = new Gson();
        //response type
        response.setContentType("application/json");
        //check if obtained variables not null
        if ((!username.equals(""))&(!password.equals("")))
        {
            if (checkUser())//check username and password
            {
                result.setResult(true);
                //servlet response
                response.getWriter().write(gson.toJson(result));    
            }
            else
            {
            	result.setResult(false);
                //servlet response
                response.getWriter().write(gson.toJson(result));
            }
        }
        else
        {
        	result.setResult(false);
                //servlet response
                response.getWriter().write(gson.toJson(result));
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

    // <editor-fold defaultstate="collapsed" desc="getServletInfo method.">
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
        //get user from database
        User user = userFacade.getUserByUsername(username);
        
        if(user!=null)//check if user exists
        {
            if(user.getPassword().equals(password))//checks password of existing user
            {
                access = true;
                //save variables to session
                httpsession.setAttribute("user", user);
                httpsession.setAttribute("username", username);
                httpsession.setAttribute("userID", user.getId());
            }
        }
        return access;
    }
}
