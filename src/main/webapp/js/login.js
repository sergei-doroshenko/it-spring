var mylib;
if (mylib == null) {
	mylib = {};
}

function bindLongin() {
	$('#auth-form').submit(function () {
        console.log('You submit the form!');
        var formdata = 'login=' + $('#login').val() + '&password=' + $('#password').val();
        console.log(formdata);
        var jqxhr = $.ajax({
                url: 'Login.do',
                data: formdata,
                dataType: 'json',
                type: 'get',
                beforeSubmit: showRequest,
                success: function (data) {
                        console.log('data=' + data);
                        handleUserData(data);
                        console.log('Status: ' + jqxhr.getAllResponseHeaders());
                },
                error:  handleError
        });
        return false;
	});
	
}

function handleUserData(data) {
	if(data.role != 'guest') {
		$("#auth-form").empty().append(data.name);
	    $("#auth-form:first").css("color", "white");
	    $('#error').empty();
	    $('<a>',{
	            text:'log out',
	            href:'Login.do',
	            class: 'logout'
	    }).appendTo("#auth-form");
	    if($("#list")){
	    	$("#list").trigger("reloadGrid"); 
	    }
	} else {
		$.cookie('user', null);
	}
}

function showRequest(formData, jqForm, options) {
	var queryString = $.param(formData);
	//alert('We send this: \n\n' + queryString + options.url);
	return true;
}

function handleError (response, status, err) {
	// This option doesn't catch any of the error below, 
	// everything is always 'OK' a.k.a 200
	if(response.status == 400){
		var errText = response.responseText;
		var errKey = errText.substring(0,1);      
		if (errKey == 'p') {
			$('#password').css('border','2px solid red');
		} else if (errKey == 'e') {
			$('#login').css('border','2px solid red');
		} else {
			$('#password').css('border','2px solid red');
			$('#login').css('border','2px solid red');
		}
		$('#error').empty().append(errText);     
	}
	
	if(response.status == 601){
		sessionTimedOut();
	}
}

function getCookie(name) {
	var matches = document.cookie.match(new RegExp("(?:^|; )" + name.replace(/([\.$?*|{}\(\)\[\]\\\/\+^])/g, '\\$1') + "=([^;]*)"));
	
	return matches ? decodeURIComponent(matches[1]) : undefined;
}
