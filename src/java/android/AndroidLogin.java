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
 *
 * @author Vitaly
 */
public class AndroidLogin extends HttpServlet {

    private HttpSession httpsession;   
    private String username = null;
    private String password = null;
    @EJB
    private UserFacade userFacade;
    
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
        processRequest(request, response);
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
        
        username = request.getParameter("username");
        password = request.getParameter("password");
        
        ConnectionResult result = new ConnectionResult();
        Gson gson = new Gson();
        response.setContentType("application/json");
        
        if ((!username.equals(""))&(!password.equals("")))
        {
            if (checkUser())
            {
                result.setResult(true);
                response.getWriter().write(gson.toJson(result));    
            }
            else
            {
                result.setResult(false);
                response.getWriter().write(gson.toJson(result));
            }
        }
        else
        {
        	result.setResult(false);
                response.getWriter().write(gson.toJson(result));
        }
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
    
    
    protected boolean checkUser()
    {
        boolean access = false;
        
        User user = userFacade.getUserByUsername(username);
        
        if(user!=null)
        {
            if(user.getPassword().equals(password))
            {
                access = true;
                httpsession.setAttribute("user", user);
                httpsession.setAttribute("username", username);
                httpsession.setAttribute("userID", user.getId());
            }
        }
        
        return access;
    }
}
