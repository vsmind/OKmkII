 function timelineWeekCreateNewEvent()
 {
     var id = $(this).attr('id');
     alert(id);
     $( "#dialog-form-create-new-event" ).dialog("open");
     $("#dialog").dialog({modal:true});
     alert($( "#dialog" ).dialog( "option", "modal" ));
 }
 
  $( "#dialog-form-create-new-event" ).dialog({
    autoOpen: true,
    height: 600,
    width: 600,
    modal: true,
    buttons: {
        "Save": function() {
            alert('save');
            },
        Cancel: function() {
            $( this ).dialog( "close" );
            }
        },
    close: function() {
        alert('close');
    }
  });
  
  
  
  
