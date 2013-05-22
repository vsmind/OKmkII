/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.ModulesFacade;
import session.ModulesstatusFacade;
import session.YrlinksFacade;
import session.ModulesusersFacade;

/**
 * Servlet is responsible for module page dynamic elements
 * @author Vitaly
 */
public class Modulespage extends HttpServlet {
    //variables
    private HttpSession httpsession;   
    private String action;
    
    @EJB
    ModulesFacade modulesFacade;
    @EJB
    ModulesstatusFacade modulesStatusFacade;
    @EJB
    ModulesusersFacade modulesUserFacade;
    @EJB
    YrlinksFacade yrlinksFacade;
    // <editor-fold defaultstate="collapsed" desc="Processes requests for both HTTP">
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
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Modulespage</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Modulespage at " + request.getContextPath() + "</h1>");
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
     * responsible for dynamic elements on modules page
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
	action = request.getParameter("instance");
        //choice of actions depending on the request parameter
        if(action.equals("active"))
        {
            getActiveModules(request, response);//generate code for show users active modules
        }
        else if(action.equals("available"))
        {
            getAvailableModules(request, response);//generate code for show available modules
        }
        else if(action.equals("showmodule"))
        {
           showModule(request, response);//generates code with module options
        }
        else
        {
            response.setContentType("text/html");
            response.sendRedirect("feil.jsp");//feil
        }
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * post processing
     * redirects to modules page
     * 
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        response.sendRedirect("modules.jsp");
    }
    
    /**
     * Method getActiveModules generates html code for user active modules demonstration
     * @param request
     * @param response
     * @throws IOException 
     */
    private void getActiveModules(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        //variable is responsible for selecting actions
        StringBuilder answer =  new StringBuilder();
        //html code generation
        answer.append("<table class=\"table table-hover\">");
        
            answer.append("<thead>");
                answer.append("<tr>");
                    answer.append("<th class=\"span2\">")
                            .append("Module name")
                            .append("</th>")
                    .append("<th class=\"span2\">")
                        .append("About")
                    .append("</th>")
                    .append("<th class=\"span2\">")
                        .append("Type")
                    .append("</th>")
                    .append("<th class=\"span1\">")
                        .append("D")
                    .append("</th>")
                    .append("<th class=\"span1\">")
                        .append("W")
                    .append("</th>")
                    .append("<th class=\"span1\">")
                        .append("M")
                    .append("</th>")
                    .append("<th class=\"span2\">")
                        .append("Status")
                    .append("</th>")
                    .append("<th class=\"span1\">")
                        .append("")
                    .append("</th>")
                    .append("<th class=\"span1\">")
                        .append("")
                    .append("</th>");
                answer.append("</tr>");
            answer.append("</thead>");
            
            answer.append("<tbody>");
            
            int userID = (Integer)httpsession.getAttribute("userID");
            
            //Get all modules for user
            List modulesList = modulesUserFacade.getModulesByUserUD(userID);
            
            //Module id
            int moduleID;
            entity.Modules tempModule;
            for(int i = 0; i < modulesList.size(); i++)
            {
                entity.Modulesusers userModules = (entity.Modulesusers) modulesList.get(i);
                //get module id
                tempModule = userModules.getModuleId();
                //Module info 
                entity.Modules module = modulesFacade.getModulebyID(tempModule.getId());
                //Module status
                entity.Modulesstatus moduleStatus = module.getModulestatus();
                if(tempModule.getId() == 1)
                {
                    
                    String place = yrlinksFacade.getLinkByPlaceID(userModules.getModuleData());
                    
                    answer.append("<tr>")
                            .append("<td>")
                                .append(tempModule.getModulename())
                            .append("</td>")
                            .append("<td>")
                                .append(place)
                            .append("</td>")
                            .append("<td>")
                                .append(tempModule.getType())
                            .append("</td>")
                            .append("<td>")
                                .append(tempModule.getMday().toString())
                            .append("</td>")
                            .append("<td>")
                                .append(tempModule.getMweek().toString())
                            .append("</td>")
                            .append("<td>")
                                .append(tempModule.getMmonth().toString())
                            .append("</td>")
                            .append("<td>")
                                .append(moduleStatus.getMstatus())
                            .append("</td>")
                            .append("<td>")
                                .append("<button id=\"")
                                    .append(userModules.getId())
                                    .append("\" class=\"span11\" onlick=\"moduleInfo(this)\">x</button>")
                            .append("</td>")
                            .append("<td>")
                                .append("<button id=\"")
                                    .append(userModules.getId())
                                    .append("\" class=\"span11\" onlick=\"moduleInfo(this)\">e</button>")
                            .append("</td>")
                        .append("</tr>");
                }
                
                
                
            }
            answer.append("</tbody>");
                    
        
        answer.append("</table>");
        //response type
        response.setContentType("text/plain");
        //send response from servlet
        response.getWriter().write(answer.toString());
    }
    
    /**
     * Method getAvailableModules generates html code for available modules demonstration
     * @param request
     * @param response
     * @throws IOException 
     */
    private void getAvailableModules(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        //variable is responsible for selecting actions
        StringBuilder answer =  new StringBuilder();
        //html code generation
        answer.append("<table class=\"table table-hover\">");
        
            answer.append("<thead>");
                answer.append("<tr>");
                    answer.append("<th class=\"span3\">")
                            .append("Module name")
                            .append("</th>")
                    .append("<th class=\"span2\">")
                        .append("Type")
                    .append("</th>")
                    .append("<th class=\"span1\">")
                        .append("D")
                    .append("</th>")
                    .append("<th class=\"span1\">")
                        .append("W")
                    .append("</th>")
                    .append("<th class=\"span1\">")
                        .append("M")
                    .append("</th>")
                    .append("<th class=\"span2\">")
                        .append("Status")
                    .append("</th>")
                    .append("<th class=\"span2\">")
                        .append("")
                    .append("</th>");
                answer.append("</tr>");
            answer.append("</thead>");
            
            answer.append("<tbody>");
                         
            
            //Get all available modules          
            List modulesList = modulesFacade.findAll();
            entity.Modules module;
            entity.Modulesstatus moduleStatus;
            for(int i = 0; i < modulesList.size(); i++)
            {
                module = (entity.Modules)modulesList.get(i);
                moduleStatus = (module.getModulestatus());
                answer.append("<tr>")
                            .append("<td>")
                                .append(module.getModulename())
                            .append("</td>")
                            .append("<td>")
                                .append(module.getType())
                            .append("</td>")
                            .append("<td>")
                                .append(module.getMday().toString())
                            .append("</td>")
                            .append("<td>")
                                .append(module.getMweek().toString())
                            .append("</td>")
                            .append("<td>")
                                .append(module.getMmonth().toString())
                            .append("</td>")
                            .append("<td>")
                                .append(moduleStatus.getMstatus())
                            .append("</td>")
                            .append("<td>")
                                .append("<button id=\"")
                                    .append(module.getId())
                                    .append("\" class=\"btn btn-info span11\" onclick=\"moduleInfo(this)\">Open</button>")
                            .append("</td>")
                        .append("</tr>");
            }
   
            answer.append("</tbody>");
                    
        
        answer.append("</table>");
        //response type
        response.setContentType("text/plain");
        //send response from servlet
        response.getWriter().write(answer.toString());
    }
    
    private void showModule(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        //variable is responsible for selecting actions
        StringBuilder answer =  new StringBuilder();
        //html code generation
        int moduleID = Integer.parseInt(request.getParameter("id"));
        
        
        entity.Modules module = modulesFacade.getModulebyID(moduleID);
        
        answer.append("<h2 class=\"muted\">")
                .append(module.getModuledescription())
                .append("</h2>");
        
        
        answer.append("<div class=\"span10\">")
                .append("<input name=\"city\" type=\"text\" id=\"cityname\" onkeyup=\"checkcity()\" placeholder=\"City name\"></input>")
                .append("</div>");
        
        answer.append("<div id=\"cityarea\" class=\"span12\">")
                .append("</div>");
        
        //response type
        response.setContentType("text/plain");
        //send response from servlet
        response.getWriter().write(answer.toString());
    }

    // <editor-fold defaultstate="collapsed" desc="Short description of the servlet.">
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }
    // </editor-fold>
}
