var mylib;
if (mylib == null) {
	mylib = {};
}

function bindLongin() {
	$('#auth-form').submit(function () {
        console.log('You submit the form!');
        var formdata = 'command=login&login=' + $('#login').val() + '&password=' + $('#password').val();
        console.log(formdata);
        var jqxhr = $.ajax({
                url: 'Main.do',
                data: formdata,
                dataType: 'json',
                type: 'get',
                beforeSubmit: showRequest,
                success: function (data) {
                        //console.log('data=' + data);
                        //handleUserData(data);
                        //console.log('Status: ' + jqxhr.getAllResponseHeaders());
                        handleUserOnLoad();
                        document.location.href = 'index.jsp';
                },
                error:  handleError
        });
        return false;
	});
	
}

function handleUserData(data) {
	if(data.role != 'guest') {
		$("#auth-form").empty();
		$('<a>', {
			text: userdata.name,
			href:'Main.do?id=' + userdata.id + '&command=user',
			class: 'logout'
		}).appendTo('#auth-form');
	    $('#error').empty();
	    $('<a>',{
	            text:'log out',
	            href: 'Main.do?command=logout',
	            class: 'logout'
	    }).appendTo("#auth-form");
	    if($("#list")){
	    	$("#list").trigger("reloadGrid"); 
	    }
	} 
}

function handleUserOnLoad() {
	try {
		var userdata = JSON.parse($.cookie('user'));
		console.log(userdata);
		if (userdata) {
			$("#auth-form").empty();
			$('<a>', {
				text: userdata.name,
				href:'Main.do?id=' + userdata.id + '&command=user',
				class: 'logout'
			}).appendTo('#auth-form');
		    $('#error').empty();
		    $('<a>',{
		            text:'log out',
		            href: 'Main.do?command=logout',
		            class: 'logout'
		    }).appendTo("#auth-form");
		}
	} catch (exception_var) {
		console.log('User null!');
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
