var url =window.location;
var search =url.search;
var str = url.toString();

if (!search) {
  str += "?";
}

//************************************ Utils ***********************************************************

function changeLocaleUrl (ev) {
	
	var expr = new RegExp("lang=\\w{1,2}");
	
	var countVal = "lang="+ ev.currentTarget.innerHTML.substring(0, 2);

	
	if (expr.test(str)) {
		str = str.replace(expr, countVal);
	} else {
		str += "&" + countVal;
	}
	
	document.location.href = str;
	
}

function getCurrentDate () {
    var currentDate = new Date();    
    var day = (currentDate.getDate()) > 10 ? (currentDate.getDate()) : ("0" + currentDate.getDate());
  	var month = (currentDate.getMonth() + 1) > 10 ? (currentDate.getMonth() + 1) : ("0" + (currentDate.getMonth() + 1));
  	var year = currentDate.getFullYear();
    var my_date = year + '-' + month + '-' + day;
    
    return my_date;
    
}

function getParameterByName(name) {
	return decodeURIComponent((new RegExp('[?|&]' + name + '=' + '([^&;]+?)(&|#|;|$)').exec(location.search)||[,""])[1].replace(/\+/g, '%20'))||null;
}

function getProjectBuilds (id) {
	var select = $('#projectbuild');
	select.empty();
	if (id) {
//		var url = window.location.pathname + '/build/project/' + id;
		var url = '/issuetracker/build/project/' + id;
        var jqxhr = $.ajax({
            url: url,
            dataType: 'json',
            type: 'get',
            success: function (data) {
                    		
        		for (var i = 0, l = data.length; i < l; i++) {
        			select.append('<option value="' + data[i].id+ '">' + data[i].name + '</option>');
				}
            },
            error:  handleError
        });
	}
    return false;
}

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

//**************************************** Login ***************************************************

//function bindLongin() {
//	$('#auth-form').on('submit', function(event) {
//		event.preventDefault();
//		var formdata = $(this).serialize(); 
//		console.log(formdata);
//		
//		$.ajax({
//			url: 'j_spring_security_check',
//			data: formdata,
//			dataType: 'text',
//			type: 'post',
//			beforeSend: function(xhr) {
//				xhr.setRequestHeader('Referer','http://localhost:8080/issuetracker/header.jsp');
//			},
//			success: function (data) {
//	             window.location.reload();
//	        },
//	        error:  handleError
//		});
//		return false;
//	});
//	
//	$('#auth-form').submit(function () {
//        
//        var formdata = {
//        	'j_username': $('#login').val(),
//	        'j_password' : $('#password').val()
//	    };
//                
//        var jqxhr = $.ajax({
//                url: 'j_spring_security_check',
//        		data: formdata,
//                dataType: 'text',
//                type: 'post',
//                success: function (data) {
//                       
//                        window.location.reload();
//                },
//                error:  handleError
//        });
//        return false;
//	});	
//};

function showRequest(formData, jqForm, options) {
	var queryString = $.param(formData);
	//alert('We send this: \n\n' + queryString + options.url);
	return true;
};

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
};

//************************************************ User Forms ***********************************************
var user_form_url = 'user/edit';
var user_view_url = 'user';
var user_url_tail =  $('#oper').val();

function builUserForm() {
	var user_url_tail = $('#oper').val();
	
	var first_name = $('#first_name'), last_name = $('#last_name'),
	  email = $('#email'),
	  password = $('#password'),
	  pass_conf = $('#pass-conf'),
	  allFields = $( [] ).add( name ).add( email ).add( password ).add(pass_conf),
	  tips = $('.validateTips');
	 
	function updateTips( t ) {
	  tips.text( t ).addClass('ui-state-highlight');
	  setTimeout(function() {
	    tips.removeClass('ui-state-highlight', 1500 );
	  }, 500 );
	}
	 
	function checkLength( o, n, min, max ) {
	  if ( o.val().length > max || o.val().length < min ) {
	    o.addClass('ui-state-error');
	updateTips('Length of ' + n + ' must be between ' +
	  min + ' and ' + max + '.' );
	    return false;
	  } else {
	    return true;
	  }
	}
	 
	function checkRegexp( o, regexp, n ) {
	  if ( !( regexp.test( o.val() ) ) ) {
	    o.addClass('ui-state-error');
	    updateTips( n );
	    return false;
	  } else {
	    return true;
	  }
	}
	    
	function checkEquality( o, p, n ) {
		  if ( !( o.val() == p.val()  ) ) {
		    o.addClass('ui-state-error');
		    updateTips( n );
		    return false;
		  } else {
		    return true;
		  }
		}
	
	function sendFormData () {
		var bValid = true;
	      allFields.removeClass('ui-state-error');
	 
	      bValid = bValid && checkLength( first_name, 'first name', 3, 10 );
	      bValid = bValid && checkLength( last_name, 'last name', 3, 10 );
	      bValid = bValid && checkLength( email, 'email', 6, 80 );
	      bValid = bValid && checkLength( password, 'password', 3, 10 );
	 
	      bValid = bValid && checkRegexp( first_name, /^[a-z]([0-9a-z_])+$/i, 'First name may consist of a-z, 0-9, underscores, begin with a letter.' );
	      bValid = bValid && checkRegexp( last_name, /^[a-z]([0-9a-z_])+$/i, 'Last name may consist of a-z, 0-9, underscores, begin with a letter.' );
	      // From jquery.validate.js (by joern), contributed by Scott Gonzalez: http://projects.scottsplayground.com/email_address_validation/
	      bValid = bValid && checkRegexp( email, /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i, 'eg. ui@jquery.com');
	      bValid = bValid && checkRegexp( password, /^([0-9a-zA-Z])+$/, 'Password field only allow : a-z 0-9' );
	      bValid = bValid && checkEquality(pass_conf, password, 'Password confirmation error');
	      
	      if (bValid) {
	    	  
	    	  var formdata = {
		        'oper' : $('#oper').val(),
		        'firstName' : first_name.val(),
		        'lastName' : last_name.val(),
		        'email' : email.val(),
		        'password' : password.val(),
		        'id' : $('#user-id').val()
	    	  };
	    	  
	    	 $.ajax({
              url: user_form_url,
              url: 'user/' + user_url_tail,
              data: formdata,
              dataType: 'text',
              type: 'post',
              success: function (data) {
            	  //$('#error').empty().append(data);
                  //window.location.href = data;
            	  window.location.reload();
              },
              error:  function (response, status, err) {	
            		if(response.status == 400){
            			updateTips( response.responseText );   
            		}
            	} 
	        });
	    	 
	      }
	}
	
	var create_buttons = {
		'Create account' : sendFormData,
		Cancel: function() {
			$(this).dialog('close');
		}	
	};
	
	var buttons_set = create_buttons; 
	
	var edit_buttons = {
			'Save changes' : sendFormData,
			Cancel: function() {
				$(this).dialog('close');
			}	
	};
	
	if($('#send-command').val() == 'edit_user') {
		buttons_set = edit_buttons;
	} 
	
	$('#dialog-form').dialog({
	  autoOpen: false,
	  height: 450,
	  width: 350,
	  modal: true,
	  buttons: buttons_set,
	  close: function() {
	    allFields.val('').removeClass('ui-state-error');
	  }
	});
};

function buildUserView () {
    $('#dialog-confirm').dialog({
      autoOpen: false,
      resizable: false,
      height:400,
      modal: true,
      buttons: {
        'Edit': function() {
        	var formdata = {
		        'id' : $('#user-id').val()
	    	};
        	
        	$.ajax({
                url: user_view_url,
                data: formdata,
                dataType: 'json',
                type: 'get',
                success: function (data) {
                	$('#dialog-form').dialog('open');                	
                	$('#first_name').val(data.firstName);
                	$('#last_name').val(data.lastName);
                	$('#email').val(data.email);              	
                },
                error:  handleUserError
  	        });
        	$(this).dialog('close');
        },
        Cancel: function() {
          $(this).dialog('close');
        }
      }
    });
 }

function handleUserError (response, status, err) {
	
	if(response.status == 400){
		var errText = response.responseText;
		$('.validateTips').text( errText ).addClass('ui-state-highlight');
	}
	
	if(response.status == 601){
		sessionTimedOut();
	}
}

//*********************************************** Issue Forms ************************************************

function bindEditIssueForm() {
	$('#edit-issue-form').submit(function () {
        
        var post_data = {
        	'id' : $('#id').val(),
	        'type': $('#type').val(),
	        'priority' : $('#priority').val(),
	        'status' : $('#status').val(),
	        'resolution' : $('#resolution').val(),
	        'project' : $('#project').val(),
	        'build' : $('#projectbuild').val(),
	        'summary' : $('#summary').val(),
	        'description' : $('#description').val(),
	        'assignee' : $('#assignee').val()
	    };
    
        $.ajax({
            url: '\\issuetracker\\issue\\update',
            data: post_data,
            dataType: 'text',
            type: 'post',
            success: function (data) {
        		window.location.href = data;
            },
            error:  handleIssueError
        });
        return false;
	});	
}

function bindNewIssueForm() {
	$('#new-issue-form').submit(function () {
        
        var post_data = {
        	'status': $('#status').val(),
        	'type': $('#type').val(),
	        'priority' : $('#priority').val(),
	        'project' : $('#project').val(),
	        'build' : $('#projectbuild').val(),
	        'summary' : $('#summary').val(),
	        'description' : $('#description').val()
//	        'assignee' : $('#assignee').val()
	    };
        
        var assingnee = $('#assignee').val();
        
        if(assingnee != null && assingnee != '') {
        	post_data.assignee = assingnee;
        }
        
        $.ajax({
            url: '\\issuetracker\\issue\\add',
            data: post_data,
            dataType: 'text',
            type: 'post',
            success: function (data) {
            	window.location.href = data;
            },
            error:  handleIssueError
        });
        return false;
	});	
}

function executeDeleteIssue() {
        
        var post_data = {
        	'id' : $('#id').val()
	    };
    
        $.ajax({
            url: '\\issuetracker\\issue\\' + $('#id').val(),
            data: post_data,
            dataType: 'text',
            type: 'delete',
            success: function (data) {
        		window.location.href = data;
            },
            error:  handleIssueError
        });
        return false;	
}

function handleIssueError (response, status, err) {
	
	if(response.status == 400){
		var errText = response.responseText;
		$('#error-issue').empty().append('<span>').append(errText);
		$('#error-issue').addClass('ui-state-error'); 
	}
	
	if(response.status == 601){
		sessionTimedOut();
	}
}
//***************************************** COMMENTS **************************************************
function sendComment() {
	var post_data = {'text' : $('#comment').val()};
	
	$.ajax({
        url: '\\issuetracker\\comment\\' + $('#id').val(),
        data: {text: $('#comment').val()},
        dataType: 'text',
        type: 'post',
        success: function (data) {
        	window.location.href = data;
        },
        error:  handleIssueError
    });
    return false;
}
