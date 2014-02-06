var url =window.location;
var search =url.search;
var str = url.toString();

if (!search) {
  str += "?";
}

function changeLocaleUrl (ev) {
	
	var expr = new RegExp("locale=\\w{1,2}");
	
	var countVal = "locale="+ ev.currentTarget.innerHTML;
	
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
	
	var sdata = {
	        'command': $('#get-builds-command').val(),
	        'id': id
	    };
                      
        var jqxhr = $.ajax({
                url: 'Main.do',
                data: sdata,
                dataType: 'json',
                type: 'get',
                success: function (data) {
                	var select = $('#projectbuild');
            		select.empty();
            		for (var i = 0, l = data.length; i < l; i++) {
            			select.append('<option value="' + data[i].id+ '">' + data[i].name + '</option>');
					}
                },
                error:  handleError
        });
        return false;
}

