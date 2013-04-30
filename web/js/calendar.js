$(document).ready(function(){
    $.get('Calendarpage', {instance:'day'}, function(responseText){
        $("#mainpanel").replaceWith("<div id=\"mainpanel\" class=\"mainp\">" + responseText + "</div>");
    });
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
    alert("TO_DO timelineview");
}

function listview(){
    alert("TO_DO listview");
}

function past(){
    alert("TO_DO past");
}

function today(){
    alert("TO_DO today");
}

function future(){
    alert("TO_DO future");
}

function createevent(){
    alert("TO_DO createevenr");
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
    $.get('Event', {eventtitle:evtitle,eventtype:evtype,eventdescription:evdescription}, function(){
       
    });
}