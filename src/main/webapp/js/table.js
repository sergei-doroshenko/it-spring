var issue_names = ["Id", "Priority", "Assignee", "Type", "Status", "Summary"];

var issue_rowLink = { baseLinkUrl: 'issue'};//'/issuetracker/Main.do', addParam: '&command=issue'

var issue_model = [
             { name: "id", index: 'id', width: 55, formatter:'showlink', formatoptions: issue_rowLink},
             { name: "priority", index: 'property', width: 100, formatter: colorFormatter},
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
        //url: "Main.do",
    	url: "issue/list",
        //postData: {	command: 'issuelist' },
		mtype: "GET",
        datatype: "json",
        jsonReader : jsonHandler,
        colNames: issue_names,
        colModel: issue_model,
        pager: "#pager",
        rowNum: 10,
        rowList: [10, 20, 30],
        sortname: "id",
        sortorder: "desc",
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
    
    $('#list').jqGrid('navGrid', '#pager',{view:false, del:false, search:true, add: false, edit: false}, //
    		{closeAfterEdit: true}, // use default settings for edit
    		{closeAfterAdd: true}, // use default settings for add
    		{closeAfterDelete: true},  // delete instead that del:false we need this
    		{multipleSearch : true}, // enable the advanced searching
    		{closeOnEscape:true} /* allow the view dialog to be closed when user press ESC key*/
    		);
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
