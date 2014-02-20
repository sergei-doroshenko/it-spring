function builUserForm() {
	
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
		        'command' : $('#send-command').val(),
		        'oper' : $('#oper').val(),
		        'first_name' : first_name.val(),
		        'last_name' : last_name.val(),
		        'email' : email.val(),
		        'password' : password.val(),
		        'id' : $('#user-id').val()
	    	  };
	    	  
	    	 $.ajax({
              url: 'user',
              data: formdata,
              dataType: 'text',
              type: 'post',
              success: function (data) {
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
}

function buildUserView () {
    $('#dialog-confirm').dialog({
      autoOpen: false,
      resizable: false,
      height:400,
      modal: true,
      buttons: {
        'Edit': function() {
        	var formdata = {
		        //'command' : $('#view-user-command').val(),
		        'id' : $('#user-id').val()
	    	};
        	
        	$.ajax({
                url: 'user',
                data: formdata,
                dataType: 'json',
                type: 'get',
                success: function (data) {
                	alert('hello');
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