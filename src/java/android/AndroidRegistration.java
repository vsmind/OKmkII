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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.UserFacade;

/**
 * Servlet is responsible for registration from android client
 * @author Vitaly
 */
public class AndroidRegistration extends HttpServlet {
    //Variables
    private HttpSession httpsession;
    String action = "";
    @EJB
    UserFacade userFacade;
    
    // <editor-fold defaultstate="collapsed" desc="processRequest method.">
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
            out.println("<title>Servlet AndroidRegistration</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AndroidRegistration at " + request.getContextPath() + "</h1>");
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
     * method check action and use corresponding methods
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
        //variables from session, responsible for selecting actions
        action = (String)request.getParameter("instant");
        
        if(action.equals("registration"))
        {
            registerNewUser(request, response);//register new user
        }
        else if(action.equals("username"))
        {
            checkUsername(request, response);//check if username is free
        }
        else
        {
            Gson gson = new Gson();
            ConnectionResult result = new ConnectionResult(false);
            response.getWriter().write(gson.toJson(result));//fail
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
     * Method registerNewUser register new user in database
     * @param request servlet request
     * @param response servlet response
     * @throws IOException if an I/O error occurs
     */
    private void registerNewUser(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        //variables
        String registerName;
        String registerMail;
        String registerPassword;
        //get variables from request
        registerName = request.getParameter("username");
        registerMail = request.getParameter("email");
        registerPassword = request.getParameter("password");
        //create user
        User user = new User(1);
        user.setUsername(registerName);
        user.setEmail(registerMail);
        user.setPassword(registerPassword);
        user.setActive(false);
        user.setFacebook(false);
        //save user to database
        userFacade.create(user);
        //response type
        response.setContentType("application/json");
        //Google gson object
        Gson gson = new Gson();
        //converted object used as a response from the server
        ConnectionResult result = new ConnectionResult(true);
        //servlet response
        response.getWriter().write(gson.toJson(result));
    }
    
    /**
     * Method checkUsername method check if username is free on server
     * @param request servlet request
     * @param response servlet response
     * @throws IOException if an I/O error occurs
     */
    private void checkUsername(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        String username = (String)request.getParameter("name");
        //Google gson object
        Gson gson = new Gson();
        //converted object used as a response from the server
        ConnectionResult result = new ConnectionResult();
        //get user object from db
        User user = userFacade.getUserByUsername(username);
        if(user==null)
        {
            result.setResult(true);//name is free
        }
        else
        {
            result.setResult(false);//name is occupied
        }
        //responce type
        response.setContentType("application/json");
        //servlet responce
        response.getWriter().write(gson.toJson(result));
    }   
}
