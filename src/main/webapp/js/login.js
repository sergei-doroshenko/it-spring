function bindLongin() {
	$('#auth-form').submit(function () {
        console.log('You submit the form!');
        var formdata = 'command=login&login=' + $('#login').val() + '&password=' + $('#password').val();
        console.log(formdata);
        var jqxhr = $.ajax({
            url: 'Main.do',
            data: formdata,
            dataType: 'json',
            type: 'GET',
            success: function() { window.location.href = 'index.jsp'; },
            error:  handleError
        });
        return false;
	});
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
