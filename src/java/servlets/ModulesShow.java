/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import help.YrForecast;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modules.weather.YrDataParser;
import session.ModulesusersFacade;
import session.YrlinksFacade;

/**
 *
 * @author Vitaly
 */
public class ModulesShow extends HttpServlet {

    private HttpSession httpsession; 
    private String action;
    private StringBuilder answer;
    @EJB
    ModulesusersFacade modulesUserFacade;
    @EJB
    YrlinksFacade yrlinksFacade;
    // <editor-fold defaultstate="collapsed" desc="processRequest methods.">
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
            out.println("<title>Servlet Modules</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Modules at " + request.getContextPath() + "</h1>");
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
        if(action.equals("showm"))
        {
            showMonthModulesForUser(request, response);
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
    
    private void showMonthModulesForUser(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        //html code which is returned in response to a request
        answer = new StringBuilder();
        
        int userID = (Integer)httpsession.getAttribute("userID");
            
        //Get all modules for user
        List modulesList = modulesUserFacade.getModulesByUserUD(userID);
        
        String moduleData;
        
        entity.Modules tempModule;
        for(int i = 0; i < modulesList.size(); i++)
        {
            entity.Modulesusers userModules = (entity.Modulesusers) modulesList.get(i);
            //get module id
            tempModule = userModules.getModuleId();
            //check if user using yr.no module
            if(tempModule.getId() == 1 && tempModule.getMmonth() == true)
            {
                moduleData = userModules.getModuleData();
                
                showWeather(moduleData, request, response);
            }   
        }
        
        //response type
        response.setContentType("text/plain");
        //send response from servlet
        response.getWriter().write(answer.toString());
    }
    
    private void showWeather(String placeID, HttpServletRequest request, HttpServletResponse response)
    {
        answer.append("<p class=\"muted\">yr.no -")
                .append(yrlinksFacade.getLinkByPlaceID(placeID))
                .append("</p>");
        String url;
        url = yrlinksFacade.getURLByPlaceID(placeID);
        
        //modules.weather.YrDataParser yrDataParser = new YrDataParser(url);
        
        //answer.append("<p>").append(yrDataParser.getWeather()).append("</p>");
        
        YrDataParser yr = new YrDataParser(url);
        
        LinkedList<YrForecast> forecast = new LinkedList();
        
        Calendar cal = (Calendar) httpsession.getAttribute("watchingDate");
        SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
        
        String wDate = dFormat.format(cal.getTime());
        
        forecast = yr.getWeather(wDate);
        
        System.out.println(wDate);
        
        answer.append("<table>")
                .append("<tbody>");
        
        for (int i = 0 ; i < forecast.size();i++)
        {
            YrForecast showingForcast = forecast.get(i);
            System.out.println(showingForcast.getTime());
            System.out.println(showingForcast.getIcon());
            System.out.println(showingForcast.getTemp());
            
            String iconLink = showingForcast.getIcon();
            if(iconLink.length()>3)
                iconLink = iconLink.substring(3, 6);
            
            answer.append("<tr>")
                    .append("<td>")
                        .append(showingForcast.getTime())
                    .append("</td>")
                    .append("<td>")
                        .append("<img src=\"img/yr/")
                        .append(iconLink)
                        .append(".png")
                        .append("\"")
                    .append("</td>")
                    .append("<td>")
                        .append(showingForcast.getTemp())
                    .append("</td>")
                  .append("</tr>");    
        }
        
        answer.append("</tbody>").append("</table>");
        
        System.out.println(answer);
    }

    // <editor-fold defaultstate="collapsed" desc="getServletInfo methods.">
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
