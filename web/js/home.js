$(document).ready(function(){
    
});

function singin(){
    $("#registerform").attr("style","display:none");
    $("#loginform").attr("style","display:inline");
    $.get('homepage', {ac:'sing'}, function(responseText){
        $("#loginform").replaceWith("<div id=\"loginform\">" + responseText + "</div>");
    });
}

function register(){
    $("#loginform").attr("style","display:none");
    $("#registerform").attr("style","display:inline");
    $.get('homepage', {ac:'reg'}, function(responseText){
        $("#registerform").replaceWith("<div id=\"registerform\">" + responseText + "</div>");
    });
}

function checkusername(){
    var name = $("#uname").val();
    
    $.get('registration', {instant:'username', name:name}, function(responseText){
        $("#unamecheck").replaceWith("<div id=\"unamecheck\">" + responseText + "</div>");   
    });
}

function checkemail(){
    var email = $("#uemail").val();
    
    $.get('registration', {instant:'mail', mail:email}, function(responseText){
        $("#uemailcheck").replaceWith("<div id=\"uemailcheck\">" + responseText + "</div>");
    });
    
    
}

function checkpassword(){
    var pass = $("#upass").val();
    $("#upasswordcheck").replaceWith("<div id=\"upasswordcheck\">" + pass + "</div>");
    checkpasswordconf();
}

function checkpasswordconf(){
    var passone = $("#upass").val();
    var passtwo = $("#upassconf").val();
    if(passone == passtwo)
        $("#upasswordconf").replaceWith("<div id=\"upasswordconf\">" + "Passwords Correct" + "</div>")
    else
        $("#upasswordconf").replaceWith("<div id=\"upasswordconf\">" + "Passwords Inncorect" + "</div>")
}