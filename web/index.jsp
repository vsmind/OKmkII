<%-- 
    Document   : index
    Created on : Apr 25, 2013, 4:01:51 PM
    Author     : Vitaly
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome to Object Calendar</title>
        <script src="js/jquery-2.0.0.js"></script>
        <script src="js/home.js"></script>
    </head>
    <body>
        <h1>Welcome to Object Calendar</h1>
        <table>
            <tr>
                <td>Hello:</td>
            </tr>
            <tr>
                <td>
                    <input type="button" id="loginbutton" value="login" onclick="singin()" />
                </td>
                <td>
                    <input type="button" id="registerbutton" value="register" onclick="register()"/>
                </td>
            </tr>
            <tr>
                <td>
                    <div id="loginform"></div>
                </td>
                <td>
                    <div id="registerform"></div> 
                </td>
            </tr>              
        </table>
        
    </body>
</html>
