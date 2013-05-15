/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modules.weather;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import entity.Yrlinks;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.ModulesusersFacade;
import session.ModulesFacade;
import session.ModulesstatusFacade;
import session.YrlinksFacade;

/**
 *
 * @author Vitaly
 */
public class Yr extends HttpServlet {

     //variables
    private HttpSession httpsession; 
    private String action;
    @EJB
    YrlinksFacade yrlinksFacade;
    @EJB
    ModulesusersFacade moduleUsersFacade;
    @EJB
    ModulesFacade modulesFacade;
    @EJB
    ModulesstatusFacade moduleStatusFacade;
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
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet yr</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet yr at " + request.getContextPath() + "</h1>");
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
        if(action.equals("search"))
        {
            getCity(request, response);
        }
        else if(action.equals("save"))
        {
            saveUserModule(request, response);
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
    
    private void getCity(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        //html code which is returned in response to a request
        StringBuilder answer =  new StringBuilder();
        String cityName = request.getParameter("city");       

        List yrLinks;
        LinkedList<help.Yrlinks> cityList = new LinkedList<help.Yrlinks>();
        //Variables for object convertation
        Integer id;
        Integer kommunenummer;
        String stadnamn;
        Integer prioritet;
        String stadtypenynorsk;
        String stadtypebokmaal;
        String stadtypeengelsk;
        String kommune;
        String fylke;
        Double lat;
        Double lon;
        String hoegd;
        String nynorsk;
        String bokmaal;
        String engelsk;
        
        if(!cityName.equals(""))
        {
            cityName = cityName + "%";
            yrLinks = yrlinksFacade.getLinkByName(cityName);
            
            help.Yrlinks ylink; 
            
            for(int i = 0 ; i < yrLinks.size(); i++)
            {
                Object[] obj;
                obj = (Object[]) yrLinks.get(i);
                ylink = new help.Yrlinks();
                
                id = (Integer) obj[0];
                kommunenummer = (Integer) obj[1];
                stadnamn = (String) obj[2];
                prioritet = (Integer) obj[3];
                stadtypenynorsk = (String)obj[4];
                stadtypebokmaal = (String)obj[5];
                stadtypeengelsk = (String)obj[6];
                kommune = (String)obj[7];
                fylke = (String)obj[8];
                lat = (Double)obj[9];
                lon = (Double)obj[10];
                hoegd = (String)obj[11];
                nynorsk = (String)obj[12];
                bokmaal = (String)obj[13];
                engelsk = (String)obj[14];
                
                ylink.setId(id);
                ylink.setKommunenummer(kommunenummer);
                ylink.setStadnamn(stadnamn);
                ylink.setPrioritet(prioritet);
                ylink.setStadtypenynorsk(stadtypenynorsk);
                ylink.setStadtypebokmaal(stadtypebokmaal);
                ylink.setStadtypeengelsk(stadtypeengelsk);
                ylink.setKommune(kommune);
                ylink.setFylke(fylke);
                ylink.setLat(lat);
                ylink.setLon(lon);
                ylink.setHoegd(hoegd);
                ylink.setNynorsk(nynorsk);
                ylink.setBokmaal(bokmaal);
                ylink.setEngelsk(engelsk);
                
                cityList.add(ylink);
            }
            
            answer.append("<table class=\"table table-hover span11\">");
            
            answer.append("<thead>");
                        answer.append("<tr>");
                            answer.append("<th class=\"span3\">").append("Place").append("</th>");
                            answer.append("<th class=\"span3\">").append("Municipality").append("</th>");
                            answer.append("<th class=\"span3\">").append("County").append("</th>");
                            answer.append("<th class=\"span3\">").append("").append("</th>");
                        answer.append("</tr>");
            answer.append("</thead>");
            
            answer.append("<tbody>");
            
            for(int i = 0; i < cityList.size(); i++)
            {
                answer.append("<tr>");
                    answer.append("<td>")
                            .append(cityList.get(i)
                            .getStadnamn())
                            .append(" ")
                            .append("</td>");
                    answer.append("<td>")
                            .append(cityList.get(i)
                            .getKommune())
                            .append(" ")
                            .append("</td>");
                    answer.append("<td>")
                            .append(cityList.get(i)
                            .getFylke())
                            .append(" ")
                            .append("</td>");
                    answer.append("<td>")
                            .append("<button id=\"").append(cityList.get(i).getId()).append("\" class=\"btn btn-success span12\" onclick=\"saveYr(this);\">Select").append("</button>")
                            .append("</td>");
                answer.append("</tr>");
            }
            
            answer.append("</tbody>");
            
            answer.append("</table>");

        }
        
        //response type
        response.setContentType("text/plain");
        //send response from servlet
        response.getWriter().write(answer.toString());
    }
    
    private void saveUserModule(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        //html code which is returned in response to a request
        StringBuilder answer =  new StringBuilder();
        //id of city in DB
        int cityID = Integer.parseInt(request.getParameter("cityid"));
        
        entity.Modulesusers moduleUser = new entity.Modulesusers(1);
        
        entity.Modules moduleYr = modulesFacade.getModulebyID(1);
        moduleUser.setModuleId(moduleYr);
        int userID;
        userID = (Integer)httpsession.getAttribute("userID");
        moduleUser.setUserId(userID);
        
        moduleUser.setModuleData(String.valueOf(cityID));
        
        moduleUsersFacade.create(moduleUser);
        
        answer.append("<h2 class=\"muted\">");
            answer.append("Success, now you can continue to choose new modules for your profile.");
        answer.append("</h2>");
        //response type
        response.setContentType("text/plain");
        //send response from servlet
        response.getWriter().write(answer.toString());
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
}
