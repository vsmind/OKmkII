/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import entity.User;
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
public class Registration extends HttpServlet {

    private HttpSession httpsession;  
    String action = "";
    StringBuilder answer;
    
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
            out.println("<title>Servlet registration</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet registration at " + request.getContextPath() + "</h1>");
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
        response.setContentType("text/plain");
        
        if(action.equals("mail"))
        {
            chechEmail(request, response);
        }
        else if(action.equals("username"))
        {
            checkUsername(request, response);
        }
        else if(action.equals("pass"))
        {
            checkPass(request, response);
        }
        else if(action.equals("checkall"))
        {
            checkAll(request, response);
        }
        else if(action.equals("passcheck"))
        {
            checkPasswords(request, response);
        }
        else
        {
            answer =  new StringBuilder();
            answer.append("Something happened, but it's not what you expected");
            response.getWriter().write(answer.toString());
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
        
        httpsession.removeAttribute("regformname");
        httpsession.removeAttribute("regformemail");
        httpsession.removeAttribute("regformpass");
        httpsession.removeAttribute("regformpasspass");
        
        
        response.setContentType("text/html");
        response.sendRedirect("calendar.jsp");
        
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
    
    /**
     * This function was created with help from: 
     * http://www.mkyong.com/regular-expressions/how-to-validate-email-address-with-regular-expression/
     * @param request
     * @param response
     * @throws IOException 
     */
    private void chechEmail(HttpServletRequest request, HttpServletResponse response) throws IOException
    {     
        Pattern pattern;
	Matcher matcher;
 
        answer =  new StringBuilder();
        
	final String EMAIL_PATTERN = 
		"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
		+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        
        pattern = Pattern.compile(EMAIL_PATTERN);
        
        
        String email = (String)request.getParameter("mail");
        matcher = pattern.matcher(email);
        
        
        if(matcher.matches())
        {
            answer.append("<p class=\"checkok\">e-mail format is correct</p>");
            httpsession.setAttribute("regformemail", "true");
        }  
        else
        {
            answer.append("<p class=\"checkfeil\">e-mail format is incorrect</p>");
            httpsession.setAttribute("regformemail", "false");
        }
        response.getWriter().write(answer.toString());
    }
    
    private void checkUsername(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        Pattern pattern;
	Matcher matcher;
       
        answer =  new StringBuilder();
        String username = (String)request.getParameter("name");
        
        final String NAME_PATERN = "^[A-Za-z0-9]+$";
        
        pattern = Pattern.compile(NAME_PATERN);
        
        matcher = pattern.matcher(username);
        
        if(matcher.matches())
        {           
            if(username.length() > 3)
            {
                User user = userFacade.getUserByUsername(username);
                if(user==null)
                {
                    answer.append("<p class=\"checkok\">Username is free for use</p>");
                    httpsession.setAttribute("regformname", "true");
                }
                else
                {
                    answer.append("<p class=\"checkfeil\">Username is already taken, try another one</p>");
                    httpsession.setAttribute("regformname", "false");
                }
            }
            else
            {
                answer.append("<p class=\"checkfeil\">Username is to short, you have to use at least 4 characters</p>");
                httpsession.setAttribute("regformname", "false");
            } 
        }
        else
        {
            answer.append("<p class=\"checkfeil\">Username can innhold just letters and numbers</p>");
            httpsession.setAttribute("regformname", "false");
        }
        
        
        response.getWriter().write(answer.toString());
    }
    
    /**
     * http://www.zorched.net/2009/05/08/password-strength-validation-with-regular-expressions/
     * @param request
     * @param response
     * @throws IOException 
     */
    private void checkPass(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        Pattern pattern;
	Matcher matcher;
        
        int passwordScore = 0;
        String criteria = "";
         
        answer =  new StringBuilder();
        String pass = (String)request.getParameter("pass");
        
        
        if(pass.length()>5)
        {
            criteria = "^.*(?=.*[a-z]).*$";//line with at least 1 lowercase character
            pattern = Pattern.compile(criteria);
            matcher = pattern.matcher(pass);
            if(matcher.matches())
                passwordScore++;
            
            criteria = "^.*(?=.*[A-Z]).*$";//Assert a string contains at least 1 uppercase letter
            pattern = Pattern.compile(criteria);
            matcher = pattern.matcher(pass);
            if(matcher.matches())
                passwordScore++;
            
            criteria = "^.*(?=.*[\\d]).*$";//Assert a string contains at least 1 digit:
            pattern = Pattern.compile(criteria);
            matcher = pattern.matcher(pass);
            if(matcher.matches())
                passwordScore++;
            
            criteria = "^.*(?=.*[\\W]).*$";//Assert a string contains at least 1 special character
            pattern = Pattern.compile(criteria);
            matcher = pattern.matcher(pass);
            if(matcher.matches())
                passwordScore++;
            
            switch(passwordScore)
            {
                case 1:
                    answer.append("<p class=\"checkfeil\">").append("Week password").append("</p>");
                    httpsession.setAttribute("regformpass", "true");
                    break;
                case 2:
                    answer.append("<p class=\"checklow\">").append("Easy password").append("</p>");
                    httpsession.setAttribute("regformpass", "true");
                    break;
                case 3:
                    answer.append("<p class=\"checkhigh\">").append("OK password").append("</p>");
                    httpsession.setAttribute("regformpass", "true");
                    break;
                case 4:
                    answer.append("<p class=\"checkok\">").append("Good password").append("</p>");
                    httpsession.setAttribute("regformpass", "true");
                    break;
            }
                    
           
        }
        else
        {
            answer.append("<p class=\"checkfeil\">Password are too short</p>");
            httpsession.setAttribute("regformpass", "false");
        }
            
        
        response.getWriter().write(answer.toString());
    }
    
    private void checkAll(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        Boolean namecheck = Boolean.FALSE;
        Boolean mailcheck = Boolean.FALSE;
        Boolean passcheck = Boolean.FALSE;
        Boolean passpasscheck = Boolean.FALSE;
        
        if(httpsession.getAttribute("regformname")!= null)
            namecheck = Boolean.valueOf((String)httpsession.getAttribute("regformname"));
        if(httpsession.getAttribute("regformemail")!= null)
            mailcheck = Boolean.valueOf((String)httpsession.getAttribute("regformemail"));
        if(httpsession.getAttribute("regformpass")!= null)
            passcheck = Boolean.valueOf((String)httpsession.getAttribute("regformpass"));
        if(httpsession.getAttribute("regformpasspass")!=null)
            passpasscheck = Boolean.valueOf((String)httpsession.getAttribute("regformpasspass"));
        
        if(namecheck==true && mailcheck == true && passcheck == true && passpasscheck == true)
        {
            response.getWriter().write("true");
            //System.out.println("true" + namecheck.toString() + mailcheck.toString() + passcheck.toString());
        }
        else
        {
            response.getWriter().write("false");
            //System.out.println("false" + namecheck.toString() + mailcheck.toString() + passcheck.toString());
        }
        
    }
    
    private void checkPasswords(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        String passone = (String)request.getParameter("passone");
        String passtwo = (String)request.getParameter("passtwo");
        
        if(passone.equals(passtwo))
        {
            httpsession.setAttribute("regformpasspass", "true");
            response.getWriter().write("Passwords Correct");
        }
        else
        {
            httpsession.setAttribute("regformpasspass", "false");
            response.getWriter().write("Passwords Inncorect");
        }
        
    }
}
