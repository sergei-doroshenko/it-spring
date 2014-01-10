$(document).ready(bindSubmit);
		
function bindSubmit () {
	var options = {
			cache: false,
			target: "#authform",
			url: "Login.do",
			type: "get",
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

		$('#authform').submit(function() {
			$('#password').css('border','none');
			$('#login').css('border','none');
			$(this).ajaxSubmit(options);
			return false;
		});
}

function showRequest(formData, jqForm, options) {
	var queryString = $.param(formData);
	//alert('We send this: \n\n' + queryString + options.url);
	return true;
}
                                
function showResponse(responseText, statusText) {
	$("#authform:first").css("color", "white");
	$('#error').empty();
	$('<a>',{
		text:'log out',
		href:'Login.do',
		class: 'logout'
	}).appendTo("#authform");
	location.reload();
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