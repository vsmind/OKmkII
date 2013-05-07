<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Object calendar</title>
        <script src="js/jquery-2.0.0.js"></script>
        <script src="js/bootstrap.js"></script>
        <script src="js/jquery.mousewheel.js"></script>
        <script src="js/jquery-ui.js"></script>
        <script src="js/calendar.js"></script>
        <script src="js/event_scripts.js"></script>
        <script src="js/bootstrap-timepicker.js"></script>
        <script src="js/bootstrap-datepicker.js"></script>
        <link rel="stylesheet" href="style/jquery-ui.css" type="text/css"/>
        <link rel="stylesheet" href="style/bootstrap.css" type="text/css">
        <link rel="stylesheet" href="style/bootstrap-responsive.css" type="text/css"/>
        <link rel="stylesheet" href="style/bootstrap-timepicker.css" type="text/css"/>
        <link rel="stylesheet" href="style/datepicker.css" type="text/css"/>
        
        <link rel="stylesheet" href="style/calendar.css" type="text/css"/>
        
       
    </head>
    <body>
        <%@ include file="jspf/checklogin.jspf" %>
        
        <div class="container-fluid">
            <div class="span12">
                <h5>Hello <em><%= session.getAttribute("username") %></em>, and welcome to your calendar.</h5>
            </div>
            <div class="row-fluid">
                <div class="span8">
                    <div class="span12">
                        <div class="span3">
                            <button id="buttonday" class="btn btn-info  btn-large span12" onclick="dayview();">Day</button>
                        </div>
                        <div class="span3">
                            <button id="buttontimeline" class="btn btn-info  btn-large span12" onclick="timelineview();">Week</button>    
                        </div>
                        <div class="span3">
                            <button id="buttonmonth" class="btn btn-info  btn-large span12" onclick="monthview();">Month</button>
                        </div>
                        <div class="span3">
                            <button id="buttonlist" class="btn btn-info  btn-large span12" onclick="listview();">List</button>
                        </div>
                        
                    </div>
                </div>
                <div class="span4">
                    <div class="row-fluid">
                        <div class="span6">
                            <form method="get" action="Administration">
                                <button id="buttonadm" class="buttonmainblue" onclick="admin();">Administration</button>
                            </form>
                        </div>
                        <div class="span6">
                            <form method="get" action="Logout">
                                <button id="buttonlogout" class="butlogout" onclick="logout();">Log Out</button>
                            </form>
                        </div>
                    
                    </div>
                </div>
                
            </div>
            <div class="row-fluid">
                <div class="span8">
                    <button id="buttonpast" class="butchangedate" onclick="past();">Back</button>
                    <button id="buttontoday" class="butchangedate" onclick="today();">Today</button> 
                    <button id="buttonfuture" class="butchangedate" onclick="future();">Forward</button>
                </div>
            </div>
        </div>

        <div class="container-fluid">
            <div id="calendararea" class="row-fluid span12">
            <div id="mainpanel" class="span8">
            
            </div>          
            
            <div id="eventpanel" class="span4">
                <button onclick="createEventPanel();">Event</button>
                <button onclick="createModulePanel();">Module</button>
                <input id="estartdate" class="span2" />
                <input type="text" value="02/16/12" data-date-format="mm/dd/yy" class="datepicker" >


                
            </div>
            </div>
        </div>
            
        <div id="createEventModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h3 id="myModalLabel">Create New Event</h3>
            </div>
            
            <div class="modal-body">
                <div id="dynamicevents">
                    
                </div>
                <table class="table table-striped">
                    <tr>
                        <td>
                            From:
                        </td>
                        <td>
                            <div class="input-append bootstrap-timepicker">
                                <input id="timepickerOne" type="text" class="input-small timeset" readonly="">
                                    <span class="add-on">
                                        <i class="icon-time"></i>
                                    </span>
                            </div>
                        </td>
                        <td>
                            <input type="text" data-date-format="mm/dd/yyyy" class="datepicker" readonly="" placeholder="mm/dd/yyyy"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            To:
                        </td>
                        <td>
                            <div class="input-append bootstrap-timepicker">
                                <input id="timepickerTo" type="text" class="input-small timeset" readonly="">
                                    <span class="add-on">
                                        <i class="icon-time"></i>
                                    </span>
                            </div>
                        </td>
                        <td>
                            <input type="text" data-date-format="mm/dd/yyyy" class="datepicker" readonly="" placeholder="mm/dd/yyyy"/>
                        </td>
                    </tr>
                    
                </table>
                
            </div>

            <div class="modal-footer">
                <button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
                <button class="btn btn-primary" onclick="saveEvent();">Save changes</button>
            </div>
        </div>
                        
    </body>
</html>
