$(document).ready(function(){
    $("#calendararea").replaceWith("<div id=\"calendararea\" class=\"row-fluid span12\"><div id=\"mainpanel\" class=\"span8\"></div><div id=\"eventpanel\" class=\"span4\"></div></div>");
    $.get('Calendarpage', {instance:'day'}, function(responseText){
        $("#mainpanel").replaceWith("<div id=\"mainpanel\" class=\"span8\">" + responseText + "</div>");
    });
    //showhour();
    $('.datepicker').datepicker();
    
    $('.timeset').timepicker({
        minuteStep: 5,
        template: 'modal',
        showSeconds: false,
        showMeridian: false
    });
    
    $("#buttonpast").html("Yesterday");
    $("#buttontoday").html("Today");
    $("#buttonfuture").html("Tomorrow");
    
    dayPosition();
});

function admin(){
    alert("TO_DO administration");
}

function logout(){
    //alert("TO_DO logout");
    $.get('Logout');
}

function modules(){
    
}

function dayview(){
    $("#calendararea").replaceWith("<div id=\"calendararea\" class=\"row-fluid span12\"><div id=\"mainpanel\" class=\"span8\"></div><div id=\"eventpanel\" class=\"span4\"></div></div>");
    $.get('Calendarpage', {instance:'day'}, function(responseText){
        $("#mainpanel").replaceWith("<div id=\"mainpanel\" class=\"span8\">" + responseText + "</div>");
    });
    $("#buttonpast").html("Yesterday");
    $("#buttontoday").html("Today");
    $("#buttonfuture").html("Tomorrow");
    
    dayPosition();
    //showhour();
    
}

function weekview(){
    alert("TO_DO weekview");
    $("#calendararea").replaceWith("<div id=\"calendararea\" class=\"row-fluid span12\"><div id=\"mainpanel\" class=\"span12\"></div></div>");
    
}

function monthview(){
    $("#calendararea").replaceWith("<div id=\"calendararea\" class=\"row-fluid span12\"><div id=\"mainpanel\" class=\"span8\"></div><div id=\"eventpanel\" class=\"span4\"></div></div>");
    $.get('Calendarpage', {instance:'month'}, function(responseText){
        $("#mainpanel").replaceWith("<div id=\"mainpanel\">" + responseText + "</div>");
    });
    $("#buttonpast").html("Last Month");
    $("#buttontoday").html("Current Month");
    $("#buttonfuture").html("Next Month");
}

function timelineview(){
    $("#calendararea").replaceWith("<div id=\"calendararea\" class=\"row-fluid span12\"><div id=\"mainpanel\" class=\"span12\"></div></div>");
    $.get('Calendarpage', {instance:'timeline', period:'week'}, function(responseText){
        $("#mainpanel").replaceWith("<div id=\"mainpanel\" class=\"span12\">" + responseText + "</div>");
    });
    $("#buttonpast").html("Last Week");
    $("#buttontoday").html("Current Week");
    $("#buttonfuture").html("Next Week");
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
    
    $.get('Eventspage', {instance:'createeventform'}, function(responseText){
        $("#dynamicevents").replaceWith("<div id=\"dynamicevents\">" + responseText + "</div>");
    });
    
    $('#createEventModal').modal();
    var stime = startTime[0] + ":"+ startTime[1];
    var timeplus = parseInt(startTime[0]) + 1;
    timeplus = timeplus + '';
    var etime = (timeplus) + ":"+ startTime[1];
    $("#timepickerOne").timepicker('setTime', stime);
    $("#timepickerTwo").timepicker('setTime', etime);
    
    $('.datepicker').datepicker();
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
    }), 100);
    
    
}

function createModulePanel(){
    alert("TO_DO");
}

function saveEvent()
{
    var evtitle = $("#eventtitle").val();
    var evtype = $("#eventtype").val();
    var evdescription = $("#eventdescription").val();
    var evstarttime = $("#timepickerOne").val();
    var evstartdate = $("#datepickerOne").val();
    var evendtime = $("#timepickerTwo").val();
    var evenddate = $("#datepickerTwo").val();
    
    alert(evstarttime);
    alert(evstartdate);
    alert(evendtime);
    alert(evenddate);
    
    $.get('Event', {eventtitle:evtitle,eventtype:evtype,eventdescription:evdescription,eventstarttime:evstarttime,eventstartdate:evstartdate,eventendtime:evendtime,eventenddate:evenddate}, function(responseText){
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

function zoomDay(day)
{
    var eventday = day.id;
    var selectedDay = eventday.split('h');
    
    var sday = selectedDay[0];
    var smonth = selectedDay[1];
    var syear = selectedDay[2];
    
     $.get('Eventspage', {instance:'dayinmonth', day:sday, month:smonth, year:syear}, function(responseText){
        $("#selectedDayEvents").replaceWith("<div id=\"selectedDayEvents\" class=\"span11\">" + responseText + "</div>");
    });   
}

function dayPosition(){
    //alert('start');
    setTimeout((function() {
        jQuery(document).ready(function(){
         $("#eventarea").click(function(e){
             var y = e.pageY - this.offsetTop;
             alert(y);
             
             $.get('Eventspage', {instance:'createeventform'}, function(responseText){
                $("#dynamicevents").replaceWith("<div id=\"dynamicevents\">" + responseText + "</div>");
             });
    
            $('#createEventModal').modal();
            
            var hour = y / 60;
            hour = Math.floor(hour);
            var minute = y - (hour * 60);
            hourplusone = hour + 1;
            hour = hour + '';
            hourplusone = hourplusone + '';
            minute = minute + '';
            
            var stime = hour + ":"+ minute;
            var etime = hourplusone + ":"+ minute;
            $("#timepickerOne").timepicker('setTime', stime);
            $("#timepickerTwo").timepicker('setTime', etime);
    
        $('.datepicker').datepicker();
             
        }); 
    });
    
    
    
 
 }), 100);
    
}

function dayclick(ee){
   
    //$(document).mousemove(function(e){
  //    alert(e.pageX +', '+ e.pageY);
  // });
   //jQuery(document).ready(function(){
   //$("#eventarea").click(function(e){
  //    alert(e.pageX +', '+ e.pageY);
 //  }); 
//});

    jQuery(document).ready(function(){
         $("#eventarea").click(function(e){
             var x = e.pageX - this.offsetLeft;
             var y = e.pageY - this.offsetTop;
             alert(x +', '+ y);
        }); 
    });

}

function eventinfo()
{
    alert('to_do eventinfo');
}