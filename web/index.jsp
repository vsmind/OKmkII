<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome to Object Calendar</title>
        <script src="js/jquery-2.0.0.js"></script>
        <script src="js/bootstrap.js"></script>
        <script src="js/home.js"></script>
        <link rel="stylesheet" href="style/bootstrap.css" type="text/css"/>
        <link rel="stylesheet" href="style/bootstrap-responsive.css" type="text/css"/>
        <link rel="stylesheet" href="style/index.css" type="text/css"/>       
    </head>
    <body>
        <div class="container-fluid">
            <div class="row-fluid" align="center">
                <div class="span8">

                </div>
                <div class="span4" align="center">
                    <h1>Welcome to Object Calendar</h1>
                    <div class="row-fluid">
                        <div class="span6">
                            <button id="loginbutton" class="btn btn-large span12" onclick="singin();">Login</button>
                        </div>
                        <div class="span6" >
                            <button id="registerbutton" class="btn btn-success btn-large span12" onclick="register();">Register</button>
                        </div>
                    </div>
                    <div class="span12">
                        <div id="loginform" class="span12"></div>
                        <div id="registerform" class="span12"></div>  
                    </div>
                </div>
            </div>
        </div>
        
    </body>
</html>
