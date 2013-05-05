$(document).ready(function(){
    
});

function singin(){
    $("#registerform").attr("style","display:none");
    $("#loginform").attr("style","display:inline");
    $.get('homepage', {ac:'sing'}, function(responseText){
        $("#loginform").replaceWith("<div id=\"loginform\" class=\"span12\">" + responseText + "</div>");
    });
}

function register(){
    $("#loginform").attr("style","display:none");
    $("#registerform").attr("style","display:inline");
    $.get('homepage', {ac:'reg'}, function(responseText){
        $("#registerform").replaceWith("<div id=\"registerform\" class=\"span12\">" + responseText + "</div>");
    });
}

function checkusername(){
    var name = $("#uname").val();
    
    $.get('registration', {instant:'username', name:name}, function(responseText){
        $("#unamecheck").replaceWith("<div id=\"unamecheck\" class=\"span12\">" + responseText + "</div>");   
    });
    checkall();
}

function checkemail(){
    var email = $("#uemail").val();
    
    $.get('registration', {instant:'mail', mail:email}, function(responseText){
        $("#uemailcheck").replaceWith("<div id=\"uemailcheck\" class=\"span12\>" + responseText + "</div>");
    });
    checkall();
    
}

function checkpassword(){
    var pass = $("#upass").val();
    
    $.get('registration', {instant:'pass', pass:pass}, function(responseText){
        $("#upasswordcheck").replaceWith("<div id=\"upasswordcheck\" class=\"span12\">" + responseText + "</div>");
    });
    
    
    checkpasswordconf();
    checkall();
}

function checkpasswordconf(){
    var passone = $("#upass").val();
    var passtwo = $("#upassconf").val();
    
    $.get('registration', {instant:'passcheck', passone:passone, passtwo:passtwo}, function(responseText){
        $("#upasswordconf").replaceWith("<div id=\"upasswordconf\" class=\"span12\">" + responseText + "</div>");
    });
    
    checkall();
}

function checkall(){
    $.get('registration', {instant:'checkall'}, function(responseText){
        var action = responseText;
        //alert(action);
        if(action === "true")
        {
            $("#regbutton").attr("style","display:inline"); 
            //alert("true " + action);
        }
        else
        {
            $("#regbutton").attr("style","display:none");
            //alert("false " + action);
        }
            
      });
}

function restorePassword(){
    $.get('homepage', {ac:'rest'}, function(responseText){
        $("#restoreform").replaceWith("<div id=\"restoreform\" class=\"span12\">" + responseText + "</div>");
    });
}