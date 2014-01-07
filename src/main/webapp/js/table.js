$( document ).ready(createTable);

function createTable () {    
    $("#list").jqGrid({
        url: "http://localhost:8080/issuetracker/Main.do",
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
            ondblClickRow: function(rowid,iRow,iCol,e){alert('double clicked');}
        },
        mtype: "GET",
        colNames: ["Id", "Create Date", "Create By", "Modify Date", "Modify By",
                   "Summary", "Status", "Resolution", "Type", "Priority", "Project"],
        colModel: [
            { name: "id", width: 55},
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
        height: $(".table-container").height()
    });
 
    $("#list").jqGrid('setGridParam', {
    	ondblClickRow: function(rowid,iRow,iCol,e) {
    		var rowData = $(this).getRowData(rowid);
    		var issueId = rowData['id'];
    		document.location.href = 'http://localhost:8080/issuetracker/error.html' + '?id=' + issueId;
    		//alert('double clicked' + issueId);
    		$.ajax({
    		    url: 'http://localhost:8080/issuetracker/error.html',
    		    data: 'id=' + issueId,
    		    type: 'GET',
    		    dataType : "json",                     
    		    success: function (data, textStatus) { 
    		        $('.table-container').empty();
    		        var templ = $('.issue-container').clone();
    		        $(templ).find('#id').append(data.id);
    		        $(templ).find('#createdate').append(data.createdate);
    		        $(templ).find('#createby').append(data.createby);
    		        $(templ).find('#modifydate').append(data.modifydate);
    		        $(templ).find('#modifyby').append(data.modifyby);
    		        $(templ).find('#summary').append(data.summary);
    		        $(templ).find('#description').append(data.description);
    		        $(templ).find('#status').append(data.status);
    		        $(templ).find('#resolution').append(data.resolution);
    		        $(templ).find('#type').append(data.type);
    		        $(templ).find('#priority').append(data.priority);
    		        $(templ).find('#project').append(data.project);
    		        $(templ).find('#projectbuild').append(data.projectbuild);
    		        $(templ).find('#assignee').append(data.assignee);
    		        templ.appendTo('.content');
    		        $('.menu-obj').append('<li class="menu-obj-item"><a>Back</a></li>');
    		        
    		    } 
    		});
    	}
    });
}