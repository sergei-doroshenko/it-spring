function createIssueTable () {    
    $("#list").jqGrid({
        url: "Main.do",
		mtype: "GET",
        datatype: "json",
        jsonReader : {
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
            },
        },
        colNames: ["Id", "Create Date", "Create By", "Modify Date", "Modify By",
                   "Summary", "Status", "Resolution", "Type", "Priority", "Project", "Assignee"],
        colModel: [
            { name: "id", width: 55, formatter:'showlink', formatoptions:{ baseLinkUrl:'issuedetails.html', addParam: '&action=openDocument', idName:'issueId'}},
            { name: "createdate", width: 90,sortable:true},
            { name: "createby", width: 80},
            { name: "modifydate", width: 80},
            { name: "modifyby", width: 80},
            { name: "summary", width: 100},
            { name: "status", width: 100},
            { name: "resolution", width: 100},
            { name: "type", width: 100},
            { name: "priority", width: 100},
            { name: "project", width: 100},
            { name: "assignee", width: 100}
        ],
        pager: "#pager",
        rowNum: 10,
        rowList: [10, 20, 30],
        sortname: "invdate",
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
	var data = $("#list").getGridParam('userData');
		if ('guest' != data.role) {
			$("#authform").empty().append(data.name);
			$("#authform:first").css("color", "white");
			$('#error').empty();
			$('<a>',{
				text:'log out',
				href:'Login.do',
				class: 'logout'
			}).appendTo("#authform");
		}
}

