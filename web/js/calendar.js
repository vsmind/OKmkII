$(document).ready(function(){
    $.get('Calendarpage', {instance:'day'}, function(responseText){
        $("#mainpanel").replaceWith("<div id=\"mainpanel\" class=\"span8\">" + responseText + "</div>");
    });
    showhour();
    $('.datepicker').datepicker();
    
    $('.timeset').timepicker({
        minuteStep: 5,
        template: 'modal',
        showSeconds: false,
        showMeridian: false
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
    $("#calendararea").replaceWith("<div id=\"calendararea\" class=\"row-fluid span12\"><div id=\"mainpanel\" class=\"span8\"></div><div id=\"eventpanel\" class=\"span4\"></div></div>");
    $.get('Calendarpage', {instance:'day'}, function(responseText){
        $("#mainpanel").replaceWith("<div id=\"mainpanel\" class=\"span8\">" + responseText + "</div>");
    });
    //showhour();
}

function weekview(){
    alert("TO_DO weekview");
    $("#calendararea").replaceWith("<div id=\"calendararea\" class=\"row-fluid span12\"><div id=\"mainpanel\" class=\"span12\"></div></div>");
}

function monthview(){
    alert("TO_DO monthview");
    $("#calendararea").replaceWith("<div id=\"calendararea\" class=\"row-fluid span12\"><div id=\"mainpanel\" class=\"span8\"></div><div id=\"eventpanel\" class=\"span4\"></div></div>");
}

function timelineview(){
    $("#calendararea").replaceWith("<div id=\"calendararea\" class=\"row-fluid span12\"><div id=\"mainpanel\" class=\"span12\"></div></div>");
    $.get('Calendarpage', {instance:'timeline', period:'week'}, function(responseText){
        $("#mainpanel").replaceWith("<div id=\"mainpanel\" class=\"span12\">" + responseText + "</div>");
    });
}

function listview(){
    alert("TO_DO listview");
    $("#calendararea").replaceWith("<div id=\"calendararea\" class=\"row-fluid span12\"><div id=\"mainpanel\" class=\"span12\"></div></div>");
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
    //$("#" + clicked_id.id).attr("class", "past");
    var startTime = test.split('m');
    $("#testlabel1").text(startTime[0]);
    $("#testlabel2").text(startTime[1]);
    
    $.get('Eventspage', {instance:'createeventform'}, function(responseText){
        $("#dynamicevents").replaceWith("<div id=\"dynamicevents\">" + responseText + "</div>");
    });
    
    
    
    $('#createEventModal').modal();
    $('.datepicker').datepicker();
    $('#timepickerS').timepicker({
        minuteStep: 5,
        showInputs: false,
        template: 'modal',
        modalBackdrop: true,
        showSeconds: false,
        showMeridian: false
});
}

function createEventPanel(){
    $.get('Eventspage', {instance:'createeventform'}, function(responseText){
        $("#dynamicevents").replaceWith("<div id=\"dynamicevents\">" + responseText + "</div>");
    });
    
    $("#estartdate").datepicker();
       
    setTimeout((function() {
        $( "#eventStartDatePicker" ).datepicker({ dateFormat: "dd/mm/yy" });
    }), 100);
    
    setTimeout((function() {
        $( "#eventEndDatePicker" ).datepicker({ dateFormat: "dd/mm/yy" });
    }), 100)
    
    
}

function createModulePanel(){
    alert("TO_DO");
}

function saveEvent()
{
    var evtitle = $("#eventtitle").val();
    var evtype = $("#eventtype").val();
    var evdescription = $("#eventdescription").val();
    var evstarthour = $("#eventStartHourSelector").val();
    var evstartminute = $("#eventStartMinuteSelector").val();
    var evstartdate = $("#eventStartDatePicker").val();
    var evendhour = $("#eventEndHourSelector").val();
    var evendminute = $("#eventEndMinuteSelector").val();
    var evenddate = $("#eventEndDatePicker").val();
    
    $.get('Event', {eventtitle:evtitle,eventtype:evtype,eventdescription:evdescription, eventstarthour:evstarthour, eventstartminute:evstartminute, eventstartdate:evstartdate, eventendhour:evendhour, eventendminute:evendminute, eventenddate:evenddate}, function(responseText){
        if(responseText === "OK")
            $("#eventtitle").val("");
            $("#eventtype").val(1);
            $("#eventdescription").val("");
    });
}

function showhour(){
    timeForDay();
    setInterval(function() {
        $.get('Time', function(responseText){$("#" + responseText).attr("class", "past");});
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
                    $("#" + i + "m" + j).attr("class", "past");    
                }
            }
            else if(i == json.hourOfDay)
            {
                for(var z = 0; z < json.minute; z++)
                {
                    $("#" + i + "m" + z).attr("class", "past");    
                }
            }
        }
        
        $("#" + json.hourOfDay + "m" + json.minute).attr("class", "past");
    });
}

function createNewEvent()
{
    alert('TO_DO');
    //$.get('Event', {instance:'newEventWindow'}, function(responseText){});
}

function showEvent()
{
    alert('TO_DO');
}

function setTimePickers()
{
     $('#timepickerS').timepicker({
        minuteStep: 5,
        showInputs: false,
        template: 'modal',
        modalBackdrop: true,
        showSeconds: false,
        showMeridian: false
});
}