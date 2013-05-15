function showActiveModules(){
    $.get('Modulespage', {instance:'active'}, function(responseText){
        $("#moduleslist").replaceWith("<div id=\"moduleslist\" class=\"span12\">" + responseText + "</div>");
    });
    
    $("#modulescontrol").replaceWith("<div id=\"modulescontrol\" class=\"span12\"><h2 class=\"muted\">Here you can find description and management modules</h2></div>");
}

function showAvailableModules(){
    $.get('Modulespage', {instance:'available'}, function(responseText){
        $("#moduleslist").replaceWith("<div id=\"moduleslist\" class=\"span12\">" + responseText + "</div>");
    });
    
     $("#modulescontrol").replaceWith("<div id=\"modulescontrol\" class=\"span12\"><h2 class=\"muted\">Here you can find description and management modules</h2></div>");
}

function moduleInfo(e){
    var moduleID = e.id;
    
    $.get('Modulespage',{instance:'showmodule', id:moduleID}, function(responseText){
        $("#modulescontrol").replaceWith("<div id=\"modulescontrol\" class=\"span12\">" + responseText + "</div>");
    });
    
}