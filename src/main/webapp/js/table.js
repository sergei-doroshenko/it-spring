var issue_names = ["Id", "Priority", "Assignee", "Type", "Status", "Summary"];

var issue_rowLink = { baseLinkUrl: 'issue'};

var issue_model = [
             { name: "id", index: 'id', width: 55, formatter:'showlink', formatoptions: issue_rowLink},
             { name: "priority", index: 'priority', width: 100, formatter: colorFormatter},
             { name: "assignee", index: 'assignee', width: 100},
             { name: "type", index: 'type', width: 100},
             { name: "status", index: 'status', width: 100},
             { name: "summary", index: 'summary', width: 200}
         ];

var jsonHandler = {
        root: "rows",
        page: "page",
        total: "total",
        records: "records",
        repeatitems: true,
        cell: "cell",
        id: "id",
        userdata: "userdata",
        subgrid: {root:"rows", 
            repeatitems: true, 
           cell:"cell"
        }
    };

function createIssueTable() {
    $("#list").jqGrid({
    	url: "issue/list",
		mtype: "GET",
        datatype: "json",
        jsonReader : jsonHandler,
        colNames: issue_names,
        colModel: issue_model,
        pager: "#pager",
        rowNum: 5,
        rowList: [5, 10, 15],
        sortname: "id",
        sortorder: "asc",
        viewrecords: true,
        gridview: true,
        autoencode: true,
        caption: "Issues",
        height: $(".table-container").height(),
		ondblClickRow: handleDoubleClick,
		loadError: function (jqXHR, textStatus, errorThrown) {
	        alert('HTTP status code: ' + jqXHR.status + '\n' +
	              'textStatus: ' + textStatus + '\n' +
	              'errorThrown: ' + errorThrown);
	        alert('HTTP message body (jqXHR.responseText): ' + '\n' + jqXHR.responseText);
	    }
        //loadComplete: handleLoadComplete
    });
    
    $('#list').jqGrid('navGrid', '#pager',{view:false, del:false, search:false, add: false, edit: false}, //
    		{closeAfterEdit: true}, // use default settings for edit
    		{closeAfterAdd: true}, // use default settings for add
    		{closeAfterDelete: true},  // delete instead that del:false we need this
    		{multipleSearch : true, // enable the advanced searching
    			multipleGroup:true, // searching using subgroups
    	        showQuery: true // show preview of search query
    		}, 
    		{closeOnEscape:true} /* allow the view dialog to be closed when user press ESC key*/
    		).jqGrid('navButtonAdd',"#pager",{// My custom button
				caption:'Search...', 
				buttonicon:'ui-icon-search', 
				onClickButton: function(){ 
					$('#searc-form').removeClass('hidden-block');
					$('#search-button').click(function (){
						$('#searc-form').addClass('hidden-block');
					});
					$('#list').jqGrid('setGridParam', { postData:{command: 'search'}});
					$('#list').trigger('reloadGrid',[{page:1}]);
				}, 
				position:"last",
				title:"",
				cursor: "pointer"
    		});
}

function colorFormatter (cellvalue, options, rowObject) {
	var styleClass;
	
	if (cellvalue == 'MINOR') {
		styleClass = 'minor-priority-highlight';
	} else if (cellvalue == 'IMPORTANT') {
		styleClass = 'important-priority-highlight';
	} else if (cellvalue == 'MAJOR') {
		styleClass = 'major-priority-highlight';
	} else if (cellvalue == 'CRITICAL') {
		styleClass = 'critical-priority-highlight';
	}
	
	return '<span class="' + styleClass +  '">' + cellvalue + '</span>';
}

function handleDoubleClick (rowid,iRow,iCol,e) {
	alert('double click was made');
}

function handleLoadComplete () {
	//var data = $("#list").getGridParam('userData');
	//handleUserData(data);
	var priorityCol = $('#list').jqGrid('getCol', 'priority', true);
	
	for (var i = 0, l = priorityCol.length; i < l; i++) {
		var id = priorityCol[i].id;
		var priority =  priorityCol[i].value;
		var styleClass;
		if (priority == 'MINOR') {
			styleClass = 'minor-priority-highlight';
		} else if (priority == 'IMPORTANT') {
			styleClass = 'important-priority-highlight';
		} else if (priority == 'MAJOR') {
			styleClass = 'major-priority-highlight';
		} else if (priority == 'CRITICAL') {
			styleClass = 'critical-priority-highlight';
		}
		$('#list').jqGrid('setCell',id,"priority","",styleClass);
	}
}
