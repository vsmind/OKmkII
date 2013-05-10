<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Object calendar, modules:</title>
        <script src="js/jquery-2.0.0.js"></script>
        <script src="js/bootstrap.js"></script>
        <script src="js/modules.js"></script>
        <link rel="stylesheet" href="style/jquery-ui.css" type="text/css"/>
        <link rel="stylesheet" href="style/bootstrap.css" type="text/css"/>
        <link rel="stylesheet" href="style/bootstrap-responsive.css" type="text/css"/>
    </head>
    <body>
        <%@ include file="jspf/checklogin.jspf" %>
        
        <div class="span12">
            <h5>Hello <em><%= session.getAttribute("username") %></em>, this page will help you with modules.</h5>
        </div>
        
        <div class="container-fluid">
            <div class="row-fluid">
                <div class="span6">
                    <button id="buttonActiveModules" class="btn btn-success btn-large span10 offset1" onclick="showActiveModules();">Active Modules</button>
                </div>
                <div class="span6">
                    <button id="buttonAvailableModules" class="btn btn-info btn-large span10 offset1" onclick="showAvailableModules();">Available Modules</button>
                </div>
            </div>
        </div>
        
        <div class="container-fluid">
            <div class="row-fluid">
                <div class="span6">
                    <div id="moduleslist" class="span12">
                        <h2 class="muted">It will provide information about the already attached or available to connect to your profile modules</h2>
                    </div>
                </div>
                <div class="span6">
                    <div id="modulescontrol" class="span12">
                        <h2 class="muted">Here you can find description and management modules</h2>
                    </div>
                </div>
            </div>
        </div>
        
    </body>
</html>
