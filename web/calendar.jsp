<%-- 
    Document   : calendar
    Created on : Apr 29, 2013, 2:07:46 PM
    Author     : Vitaly
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Object calendar</title>
        <script src="js/jquery-2.0.0.js"></script>
        <script src="js/jquery.mousewheel.js"></script>
        <script src="js/jquery-ui.js"></script>
        <script src="js/calendar.js"></script>
        <link rel="stylesheet" href="style/jquery-ui.css" type="text/css"/>
        <link rel="stylesheet" href="style/calendar.css" type="text/css"/>
       
    </head>
    <body>
        <%@ include file="jspf/checklogin.jspf" %>
        <div id="viewpanel" class="viewp">
            <table>
                <tr>
                    <td nowrap height="10" colspan="6">
                        Hello <%= session.getAttribute("username") %>, and welcome to your calender.
                    </td>
                </tr>
                <tr>
                    <td>
                        <button id="buttonday" class="buttime" onclick="dayview()">Day</button>
                    </td>
                    <td>
                        <button id="buttonweek" class="buttime" onclick="weekview()">Week</button>
                    </td>
                    <td>
                        <button id="buttonmonth" class="buttime" onclick="monthview()">Month</button>
                    </td>
                    <td>
                        <button id="buttonyear" class="buttime" onclick="yearview()">Year</button>
                    </td>
                    <td>
                        <button id="buttontimeline" class="buttime" onclick="timelineview()">Timeline</button>
                    </td>
                    <td>
                        <button id="buttonlist" class="buttime" onclick="listview()">List</button>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <button id="buttonpast" class="butchangedate" onclick="past()">Back</button>
                    </td>
                    <td colspan="2">
                        <button id="buttontoday" class="butchangedate" onclick="today()">Today</button> 
                    </td>
                    <td colspan="2">
                        <button id="buttonfuture" class="butchangedate" onclick="future()">Forward</button>
                    </td>
                </tr>
            </table>
       
        </div>
        
        <div id="mainpanel" class="mainp">
            
        </div>
        
        <div id="infopanel" class="infop">
            
            <table>
                <tr>
                    <td>
                        <form method="get" action="Administration">
                            <button id="buttonadm" class="butadmin" onclick="admin()">Administration</button>
                        </form>
                    </td>
                    <td>
                        <form method="get" action="Logout">
                            <button id="buttonlogout" class="butlogout" onclick="logout()">Log Out</button>
                        </form>
                    </td>
                </tr>  
            </table>
            
        </div>
        
        <div id="eventpanel" class="eventp">
            <table>
                <tr>
                    <td>
                        <button onclick="createEventPanel()">Event</button>
                    </td>
                    <td>
                        <button onclick="createModulePanel()">Module</button>
                    </td>
                </tr>
                <tr>
                    <td>
                        <div id="dynamicevents"> </div>
                    </td>
                </tr>            
            </table>
            
        </div>
        
    </body>
</html>
