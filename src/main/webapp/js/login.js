function getDetailsUrl() {
	var url = window.location.href;
	var pathname = window.location.pathname;
	console.log(pathname);
	var ind = pathname.lastIndexOf('/') + 1;
	console.log(ind);
	
	url = pathname.substring(0,ind) + 'Main.do';
	console.log(pathname);
	return pathname;
}

function getMainUrl() {
	var pathname = window.location.pathname;
	console.log(pathname);
	var ind = pathname.lastIndexOf('/') + 1;
	console.log(ind);
	
	pathname = pathname.substring(0,ind) + 'index.jsp';
	console.log(pathname);
	return pathname;
}

function bindLongin() {
	$('#auth-form').submit(function () {
        console.log('You submit the form!');
        alert('You submit the form!');
        var formdata = 'command=login&login=' + $('#login').val() + '&password=' + $('#password').val();
        console.log(formdata);
        //alert(formdata);
        var jqxhr = $.ajax({
                url: 'Main.do',
                data: formdata,
                dataType: 'json',
                type: 'GET',
                success: function (data) {
                        //console.log('data=' + data);
                        //handleUserData(data);
                        //console.log('Status: ' + jqxhr.getAllResponseHeaders());
                        //alert('Status: ' + jqxhr.getAllResponseHeaders());
                        //handleUserOnLoad();
                		alert('Request succes!');
                		alert('Jump to url: ' + getMainUrl());
                        window.location.href = getMainUrl();
                },
                error:  handleError
        });
        return false;
	});	
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
		console.log(errKey);
		if (errKey == 'p') {
			$('#password').css('border','2px solid red');
		} else if (errKey == 'e' || errKey == 'l') {
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
