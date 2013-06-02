/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package android;

import com.google.gson.Gson;
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
public class AndroidModules extends HttpServlet {

     private HttpSession httpsession; 
     private String action;
     @EJB
     ModulesusersFacade modulesUserFacade;
     @EJB
     YrlinksFacade yrlinksFacade;
     
     //Serializeble help class
     List<List<help.YrForecast>> yrForecastList;
    
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
            out.println("<title>Servlet AndroidModules</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AndroidModules at " + request.getContextPath() + "</h1>");
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
        if(action.equals("showweather"))
        {
            weather(request, response);
        }
        
        
        
    }
    
    private void weather(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        int userID = (Integer)httpsession.getAttribute("userID");
        //list initialization
        yrForecastList = new LinkedList();
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
            if(tempModule.getId() == 1)
            {
                moduleData = userModules.getModuleData();
                
                showWeather(moduleData, request, response);
            }   
        }
        
        //response type
        response.setContentType("application/json");
        //Google gson object
        Gson gson = new Gson();
        //servlet response
        response.getWriter().write(gson.toJson(yrForecastList));
    }
    
    
    private void showWeather(String placeID, HttpServletRequest request, HttpServletResponse response)
    {
        
        String url;
        url = yrlinksFacade.getURLByPlaceID(placeID);
        
        //variable is responsible for selecting actions
	String wDate = request.getParameter("wdate");
        
        YrDataParser yr = new YrDataParser(url);
        
        LinkedList<YrForecast> forecast = new LinkedList();
        forecast = yr.getWeather(wDate);
        yrForecastList.add(forecast);
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
