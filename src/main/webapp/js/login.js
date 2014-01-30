function getDetailsUrl() {
	var url = window.location.href;
	console.log(url);
	var ind = url.lastIndexOf('/') + 1;
	console.log(ind);
	
	url = url.substring(0,ind) + 'details.jsp';
	console.log(url);
	return url;
}

function getMainUrl() {
	var url = window.location.href;
	console.log(url);
	var ind = url.lastIndexOf('/') + 1;
	console.log(ind);
	
	url = url.substring(0,ind) + 'index.jsp';
	console.log(url);
	return url;
}

function bindLongin() {
	$('#auth-form').submit(function () {
        console.log('You submit the form!');
        //alert('You submit the form!');
        var formdata = 'command=login&login=' + $('#login').val() + '&password=' + $('#password').val();
        console.log(formdata);
        //alert(formdata);
        var jqxhr = $.ajax({
                url: 'Main.do',
                data: formdata,
                dataType: 'json',
                type: 'GET',
                beforeSubmit: showRequest,
                success: function (data) {
                        //console.log('data=' + data);
                        //handleUserData(data);
                        //console.log('Status: ' + jqxhr.getAllResponseHeaders());
                        //alert('Status: ' + jqxhr.getAllResponseHeaders());
                        //handleUserOnLoad();
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
