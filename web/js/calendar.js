$(document).ready(function(){
    $.get('Calendarpage', {instance:'day'}, function(responseText){
        $("#mainpanel").replaceWith("<div id=\"mainpanel\" class=\"mainp\">" + responseText + "</div>");
    });
    showhour();
});

function admin(){
    alert("TO_DO administration");
}

function logout(){
    //alert("TO_DO logout");
    $.get('Logout');
}

function dayview(){
    alert("TO_DO dayview");
}

function weekview(){
    alert("TO_DO weekview");
}

function monthview(){
    alert("TO_DO monthview");
}

function yearview(){
    alert("TO_DO yearview");
}

function timelineview(){
    $.get('Calendarpage', {instance:'timeline', period:'week'}, function(responseText){
        $("#mainpanel").replaceWith("<div id=\"mainpanel\" class=\"mainp\">" + responseText + "</div>");
    });
}

function listview(){
    alert("TO_DO listview");
}

function past(){
    $.post('Calendarpage',  {instance:'past'}, function(responseText){
        $("#mainpanel").replaceWith("<div id=\"mainpanel\" class=\"mainp\">" + responseText + "</div>");
    });
}

function today(){ 
    $.post('Calendarpage',  {instance:'present'}, function(responseText){
        $("#mainpanel").replaceWith("<div id=\"mainpanel\" class=\"mainp\">" + responseText + "</div>");
    });
}

function future(){
    $.post('Calendarpage',  {instance:'future'}, function(responseText){
        $("#mainpanel").replaceWith("<div id=\"mainpanel\" class=\"mainp\">" + responseText + "</div>");
    });
}

function createevent(clicked_id){
    var test = clicked_id.id;
    $("#" + clicked_id.id).attr("class", "past");
    alert(test); 
}

function createEventPanel(){
    $.get('Eventspage', {instance:'createeventform'}, function(responseText){
        $("#dynamicevents").replaceWith("<div id=\"dynamicevents\">" + responseText + "</div>");
    });
}

function createModulePanel(){
    alert("TO_DO");
}

function saveEvent()
{
    var evtitle = $("#eventtitle").val();
    var evtype = $("#eventtype").val();
    var evdescription = $("#eventdescription").val();
    $.get('Event', {eventtitle:evtitle,eventtype:evtype,eventdescription:evdescription}, function(responseText){
        if(responseText == "OK")
            $("#eventtitle").val("");
            $("#eventtype").val(1);
            $("#eventdescription").val("");
    });
}

function showhour(){
    timeForDay();
    setInterval(function() {
    //$.get('Time', function(responseText){$("#" + responseText).attr("class", "past");});
    timeForDay();
}, 60 * 100);
}

function timeForDay(){
$.getJSON('Time', function(json){
        //alert(json.hourOfDay);
        
        for(var i = 0; i < 24; i++){
            if(i < json.hourOfDay)
            {
                for(var j= 0; j < 60; j++)
                {
                    $("#" + i + "minute" + j).attr("class", "past");    
                }
            }
            else if(i == json.hourOfDay)
            {
                for(var z = 0; z < json.minute; z++)
                {
                    $("#" + i + "minute" + z).attr("class", "past");    
                }
            }
        }
        
        $("#" + json.hourOfDay + "minute" + json.minute).attr("class", "past");
    });
}
