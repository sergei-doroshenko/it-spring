
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

function createStatusesTable() {
    $("#statuses-table").jqGrid({
        url: "Main.do",
        editurl: 'Main.do?command=edit_status',
        postData: {	command: 'statuses_list' },
		mtype: "GET",
        datatype: "json",
        jsonReader : jsonHandler,
        colNames: ["Id", "Name"],
        colModel: [
                   { name: "id", index: 'id', width: 50},
                   { name: "name", index: 'name', width: 200, editable: true, editoptions:{size:"20",maxlength:"30"}}             
               ],
        pager: "#statuses-pager",
        rowNum: 10,
        sortname: "id",
        sortorder: "desc",
        viewrecords: true,
        gridview: true,
        autoencode: true,
        caption: "Statuses",
        height: 100,
		loadError: function (jqXHR, textStatus, errorThrown) {
	        alert('HTTP status code: ' + jqXHR.status + '\n' +
	              'textStatus: ' + textStatus + '\n' +
	              'errorThrown: ' + errorThrown);
	        alert('HTTP message body (jqXHR.responseText): ' + '\n' + jqXHR.responseText);
	    }
    });
    
    $("#statuses-table").jqGrid('navGrid', '#statuses-pager',{view:false, del:true, search:false}, //
    		{closeAfterEdit: true}, // use default settings for edit
    		{closeAfterAdd: true}, // use default settings for add
    		{closeAfterDelete: true},  // delete instead that del:false we need this
    		{multipleSearch : true}, // enable the advanced searching
    		{closeOnEscape:true} /* allow the view dialog to be closed when user press ESC key*/
    		);
}

