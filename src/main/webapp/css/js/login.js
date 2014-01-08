$(document).ready(function(){
	var options = {
        cache: false,
        target: "#authform",
        url: "Login.do",
        type: "POST",
        beforeSubmit: showRequest,
        success: showResponse,
        timeout: 3000,
        clearForm: true,
        resetForm: true,
        complete: function(xhr, statusText) {
            //alert(xhr.statusText);
        },
        error: handleError
    };
    
    $("#authform").submit(function() {
        $('#password').css('border','none');
        $('#login').css('border','none');
        $(this).ajaxSubmit(options);
        return false;
    });
    
});

function showRequest(formData, jqForm, options) {
    var queryString = $.param(formData);
    alert('We send this: \n\n' + queryString);
    return true;
}

function showResponse(responseText, statusText) {
    //alert('Server response status: ' + statusText +
    //'\n\nServer response text: \n' + responseText +
    //'\n\nwill placed in element #user-info.');
    
    $("#authform:first").css("color", "white");
    $('#error').empty();
    $('<a>',{
        text:'log out',
        href:'Login.do',
        class: 'logout'
        //click:function(){alert('test');return true;}
    }).appendTo("#authform");
}

function handleError (response, status, err) {
    // This option doesn't catch any of the error below, 
    // everything is always 'OK' a.k.a 200
    if(response.status == 400){
        var errText = response.responseText;
        var errKey = errText.substring(0,1);
        
        if (errKey == 'p') {
            $('#password').css('border','2px solid red');
        } else if (errKey == 'e'|| errKey == 'l') {
            $('#login').css('border','2px solid red');
        } else {
            $('#password').css('border','2px solid red');
            $('#login').css('border','2px solid red');
        }
        //console.log("Sorry, this is bad request!");
        //alert(response.responseText + '!!' + err + '!! ' + status);
        $('#error').empty().append(errText);
        
    }
    if(response.status == 601){
        sessionTimedOut();
    }
}
				