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
 *
 * @author Vitaly
 */
public class AndroidRegistration extends HttpServlet {

    private HttpSession httpsession;
    String action = "";
    @EJB
    UserFacade userFacade;
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
        action = (String)request.getParameter("instant");
        
        if(action.equals("registration"))
        {
            registerNewUser(request, response);
        }
        else if(action.equals("username"))
        {
            checkUsername(request, response);
        }
        else
        {
            Gson gson = new Gson();
            ConnectionResult result = new ConnectionResult(false);
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

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
    private void registerNewUser(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        String registerName;
        String registerMail;
        String registerPassword;
        
        registerName = request.getParameter("username");
        registerMail = request.getParameter("email");
        registerPassword = request.getParameter("password");
        
        User user = new User(1);
        user.setUsername(registerName);
        user.setEmail(registerMail);
        user.setPassword(registerPassword);
        user.setActive(false);
        user.setFacebook(false);
        
        userFacade.create(user);
             
        response.setContentType("application/json");
        Gson gson = new Gson();
        ConnectionResult result = new ConnectionResult(true);
        response.getWriter().write(gson.toJson(result));
    }
    
    private void checkUsername(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        String username = (String)request.getParameter("name");
        Gson gson = new Gson();
        ConnectionResult result = new ConnectionResult();
        
        User user = userFacade.getUserByUsername(username);
        if(user==null)
        {
            result.setResult(true);
        }
        else
        {
            result.setResult(false);
        }

        response.setContentType("application/json");
        response.getWriter().write(gson.toJson(result));
    }
    
    
}
