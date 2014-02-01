var names = ["Id", "Priority", "Assignee", "Type", "Status", "Summary"];

var rowLink = { baseLinkUrl: '/issuetracker/Main.do', addParam: '&command=issue'};// getDetailsUrl()

var model = [
             { name: "id", width: 55, formatter:'showlink', formatoptions: rowLink},
             { name: "priority", width: 100},
             { name: "assignee", width: 100},
             { name: "type", width: 100},
             { name: "status", width: 100},
             { name: "summary", width: 200}
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
        url: "Main.do",
        postData: {	command: 'issuelist' },
		mtype: "GET",
        datatype: "json",
        jsonReader : jsonHandler,
        colNames: names,
        colModel: model,
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
        loadComplete: handleLoadComplete
    });
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
		}
		$('#list').jqGrid('setCell',id,"priority","",styleClass);
	}
}
