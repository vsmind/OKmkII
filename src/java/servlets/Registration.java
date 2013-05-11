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
 * Servlet is responsible for various activities related to the registration of a new user
 * @author Vitaly
 */
public class Registration extends HttpServlet {

    //Variables
    private HttpSession httpsession;  
    String action = "";
    StringBuilder answer;
    //Declare Enterprise JavaBean
    @EJB
    UserFacade userFacade;
    
    @Deprecated
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
    // </editor-fold>

    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * get processing
     * method responsible for action choice with checking 
     * (username, email, password, repeat password)
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
        //variable is responsible for selecting actions
        action = (String)request.getParameter("instant");
        //response type
        response.setContentType("text/plain");
        
        //choice of action
        if(action.equals("mail"))
        {
            chechEmail(request, response);//check email
        }
        else if(action.equals("username"))
        {
            checkUsername(request, response);//check username
        }
        else if(action.equals("pass"))
        {
            checkPass(request, response);//check password
        }
        else if(action.equals("checkall"))
        {
            checkAll(request, response);//check all parametrs
        }
        else if(action.equals("passcheck"))
        {
            checkPasswords(request, response);//check password repeat
        }
        else
        {
            answer =  new StringBuilder();
            answer.append("Something happened, but it's not what you expected");
            response.getWriter().write(answer.toString());//feil
        }
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     * Post request is responsible for maintaining the new user in the database
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //variables
        String registerName;
        String registerMail;
        String registerPassword;
        //obtaining values
        registerName = request.getParameter("username");
        registerMail = request.getParameter("email");
        registerPassword = request.getParameter("password");
        //create new user
        User user = new User(1);
        user.setUsername(registerName);
        user.setEmail(registerMail);
        user.setPassword(registerPassword);
        user.setActive(false);
        user.setFacebook(false);
        //save new user
        userFacade.create(user);
        //remove from the session information used at registration
        httpsession.removeAttribute("regformname");
        httpsession.removeAttribute("regformemail");
        httpsession.removeAttribute("regformpass");
        httpsession.removeAttribute("regformpasspass");
        
        response.setContentType("text/html");
        response.sendRedirect("calendar.jsp");
    }
 
    /**
     * Method chechEmail is responsible for checking the format of e-mail addresses
     * This function was created with help from: 
     * http://www.mkyong.com/regular-expressions/how-to-validate-email-address-with-regular-expression/
     * @param request
     * @param response
     * @throws IOException 
     */
    private void chechEmail(HttpServletRequest request, HttpServletResponse response) throws IOException
    {     
        Pattern pattern;//A compiled representation of a regular expression. 
	Matcher matcher;//creates a matcher that will match the given input against this pattern. 
 
        answer =  new StringBuilder();
        //regex
	final String EMAIL_PATTERN = 
		"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
		+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        
        //compilate regex
        pattern = Pattern.compile(EMAIL_PATTERN);
        //obtain email value
        String email = (String)request.getParameter("mail");
        // match the given input against pattern. 
        matcher = pattern.matcher(email);
        
        //html code generation
        if(matcher.matches())
        {
            answer.append("<p class=\"label label-success\">e-mail format is correct</p>");
            httpsession.setAttribute("regformemail", "true");//email registration condition
        }  
        else
        {
            answer.append("<p class=\"label label-important\">e-mail format is incorrect</p>");
            httpsession.setAttribute("regformemail", "false");//email registration condition
        }
        //send response from servlet
        response.getWriter().write(answer.toString());
    }
    
    /**
     * Method checkUsername is responsible for checking the format of the user name, its length and its availability
     * @param request
     * @param response
     * @throws IOException 
     */
    private void checkUsername(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        Pattern pattern;//A compiled representation of a regular expression.
	Matcher matcher;//creates a matcher that will match the given input against this pattern. 
       
        answer =  new StringBuilder();
        //obtain username value
        String username = (String)request.getParameter("name");
        //username regex (user can use )
        final String NAME_PATERN = "^[A-Za-z0-9]+$";
        //compilate regex
        pattern = Pattern.compile(NAME_PATERN);
        // match the given input against pattern. 
        matcher = pattern.matcher(username);
        
        //checks username contained characters
        if(matcher.matches())
        {           
            //checks username length 
            if(username.length() > 3)
            {
                //checks name avaibility
                User user = userFacade.getUserByUsername(username);
                if(user==null)
                {
                    answer.append("<p class=\"label label-success\">Username is free for use</p>");
                    httpsession.setAttribute("regformname", "true");//username registration condition
                }
                else
                {
                    answer.append("<p class=\"label label-important\">Username is already taken, try another one</p>");
                    httpsession.setAttribute("regformname", "false");//username registration condition
                }
            }
            else
            {
                answer.append("<p class=\"label label-important\">Username is to short, you have to use at least 4 characters</p>");
                httpsession.setAttribute("regformname", "false");//username registration condition
            } 
        }
        else
        {
            answer.append("<p class=\"label label-important\">Username can innhold just letters and numbers</p>");
            httpsession.setAttribute("regformname", "false");//username registration condition
        }
        //send response from servlet
        response.getWriter().write(answer.toString());
    }
    
    /**
     * Method checkPass is responsible for checking the password complexity
     * http://www.zorched.net/2009/05/08/password-strength-validation-with-regular-expressions/
     * @param request
     * @param response
     * @throws IOException 
     */
    private void checkPass(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        Pattern pattern;//A compiled representation of a regular expression.
	Matcher matcher;//creates a matcher that will match the given input against this pattern. 
        
        //Variable determines the complexity of the password, depending on the characters contained in it.
        //Each match with the code gives one point. 
        //Complexity is defined on a scale from 1 to 4. 
        //Where 1 is a lightweight and password 4 is the most difficult
        int passwordScore = 0;
        String criteria = "";
         
        answer =  new StringBuilder();
        //obtain password value
        String pass = (String)request.getParameter("pass");
        //check the minimum length available
        if(pass.length()>5)
        {
            //check according to the criteria
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
            
            //code generation according to accumulated points
            switch(passwordScore)
            {
                case 1:
                    answer.append("<p class=\"label label-important\">").append("Week password").append("</p>");
                    httpsession.setAttribute("regformpass", "true");//password registration condition
                    break;
                case 2:
                    answer.append("<p class=\"label label-warning\">").append("Easy password").append("</p>");
                    httpsession.setAttribute("regformpass", "true");//password registration condition
                    break;
                case 3:
                    answer.append("<p class=\"label\">").append("OK password").append("</p>");
                    httpsession.setAttribute("regformpass", "true");//password registration condition
                    break;
                case 4:
                    answer.append("<p class=\"label label-success\">").append("Good password").append("</p>");
                    httpsession.setAttribute("regformpass", "true");//password registration condition
                    break;
            }  
        }
        else
        {
            answer.append("<p class=\"label label-important\">Password are too short</p>");
            httpsession.setAttribute("regformpass", "false");//password registration condition
        } 
        //send response from servlet
        response.getWriter().write(answer.toString());
    }
    
    /**
     * Method checkAll verifies all registration conditions
     * @param request
     * @param response
     * @throws IOException 
     */
    private void checkAll(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        //registration condition variables
        Boolean namecheck = Boolean.FALSE;
        Boolean mailcheck = Boolean.FALSE;
        Boolean passcheck = Boolean.FALSE;
        Boolean passpasscheck = Boolean.FALSE;
        
        //obtain registration conditions from session
        if(httpsession.getAttribute("regformname")!= null)
            namecheck = Boolean.valueOf((String)httpsession.getAttribute("regformname"));
        if(httpsession.getAttribute("regformemail")!= null)
            mailcheck = Boolean.valueOf((String)httpsession.getAttribute("regformemail"));
        if(httpsession.getAttribute("regformpass")!= null)
            passcheck = Boolean.valueOf((String)httpsession.getAttribute("regformpass"));
        if(httpsession.getAttribute("regformpasspass")!=null)
            passpasscheck = Boolean.valueOf((String)httpsession.getAttribute("regformpasspass"));
        
        //check conditions
        if(namecheck==true && mailcheck == true && passcheck == true && passpasscheck == true)
        {
            //send response from servlet
            response.getWriter().write("true");
            //System.out.println("true" + namecheck.toString() + mailcheck.toString() + passcheck.toString());
        }
        else
        {
            //send response from servlet
            response.getWriter().write("false");
            //System.out.println("false" + namecheck.toString() + mailcheck.toString() + passcheck.toString());
        }
        
    }
    
    /**
     * Method checkPasswords compares the first and re-enter the password
     * @param request
     * @param response
     * @throws IOException 
     */
    private void checkPasswords(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        //obtain password
        String passone = (String)request.getParameter("passone");
        String passtwo = (String)request.getParameter("passtwo");
        
        if(passone.equals(passtwo))
        {
            httpsession.setAttribute("regformpasspass", "true");//password checking registration condition
            //send response from servlet
            response.getWriter().write("<p class=\"label label-success\">Passwords Correct</p>");
        }
        else
        {
            httpsession.setAttribute("regformpasspass", "false");//password checking registration condition
            //send response from servlet
            response.getWriter().write("<p class=\"label label-important\">Passwords Inncorect</p>");
        }
        
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
