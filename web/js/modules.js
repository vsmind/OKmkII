function showActiveModules(){
    $.get('Modulespage', {instance:'active'}, function(responseText){
        $("#moduleslist").replaceWith("<div id=\"moduleslist\" class=\"span12\">" + responseText + "</div>");
    });
}

function showAvailableModules(){
    $.get('Modulespage', {instance:'available'}, function(responseText){
        $("#moduleslist").replaceWith("<div id=\"moduleslist\" class=\"span12\">" + responseText + "</div>");
    });;
}