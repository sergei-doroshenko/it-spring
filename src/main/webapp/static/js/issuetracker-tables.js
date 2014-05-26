var issue_names = new Array();
issue_names['en'] = ["Id", "Create Date", "Create By", "Modify Date", "Modify By", "Summary", "Status", "Resolution", "Type", "Priority", "Project", "Bild", "Assignee"];
issue_names['ru'] = ["Ид", "Дата Создания", "Кем Создано", "Дата Измениения", "Кем Изменено", "Кратко", "Статус", "Резолюция", "Тип", "Приоритет", "Проект", "Билд", "Исполнитель"];
var lang = $.cookie('org.springframework.web.servlet.i18n.CookieLocaleResolver.LOCALE');

var issue_rowLink = { baseLinkUrl: 'issue/view'};

function d (cellValue, rowId, rowData)  {
	return '/Store/AddToCart?id=' + rowId;
};

var issue_model = [
             {name: "id", index: 'id', width: 20, formatter:'showlink', formatoptions: issue_rowLink,
            	 searchrules: { "required": true, "number": true, "maxValue": 100 }, searchoptions: { sopt: ['eq', 'ne', 'lt', 'le', 'gt', 'ge']} },
             {name: "createDate", index: "createDate", width: 75, 
            		searchrules: { "required": true, "date": true}, searchoptions: {sopt: ['eq', 'ne', 'lt', 'le', 'gt', 'ge']}},
             {name: "createBy", index: "createBy", width: 75,
            		searchrules: { "required": true}, stype: 'select', searchoptions: {sopt: ['eq', 'ne'], dataUrl: '/issuetracker/user/options'}},
             {name: "modifyDate", index: "modifyDate", width: 75,
            		searchrules: { "required": true, "date": true}, searchoptions: {sopt: ['eq', 'ne', 'lt', 'le', 'gt', 'ge']}},
             {name: "modifyBy", index: "modifyBy", width: 75,
            		searchrules: { "required": true}, stype: 'select', searchoptions: {sopt: ['eq', 'ne'], dataUrl: '/issuetracker/user/options'}},
             {name: "summary", index: 'summary', width: 100,
            		searchrules: { "required": true}, searchoptions: {sopt: ['bw', 'ew', 'cn']}},//hidden: true, searchoptions: {searchhidden: true}, 
             {name: "status", index: 'status', width: 70,
            		searchrules: { "required": true}, stype: 'select', searchoptions: {sopt: ['eq', 'ne'], dataUrl: '/issuetracker/prop/options/STATUS'}},
             {name: "resolution", index: "resolution", width: 70,
            		searchrules: { "required": true}, stype: 'select', searchoptions: {sopt: ['eq', 'ne'], dataUrl: '/issuetracker/prop/options/RESOLUTION'}},
             {name: "type", index: 'type', width: 70,
            		searchrules: { "required": true}, stype: 'select', searchoptions: {sopt: ['eq', 'ne'], dataUrl: '/issuetracker/prop/options/TYPE'}},
             {name: "priority", index: 'priority', width: 70, formatter: colorFormatter,
            		searchrules: { "required": true}, stype: 'select', searchoptions: {sopt: ['eq', 'ne'], dataUrl: '/issuetracker/prop/options/PRIORITY'}},
             {name: "project", index: "project", width: 150,
            	 searchrules: { "required": true}, searchoptions: {sopt: ['bw', 'ew', 'cn']}}, 
             {name: "build", index: "build", width: 70,
            	 searchrules: { "required": true}, searchoptions: {sopt: ['bw', 'ew', 'cn']}},
             {name: "assignee", index: 'assignee', width: 100,
            	 searchrules: { "required": true}, stype: 'select', searchoptions: {sopt: ['eq', 'ne'], dataUrl: '/issuetracker/user/options'}}
         ];

var datePick = function(elem) {
	   jQuery(elem).datepicker();
	};

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
	var locale = $.cookie('org.springframework.web.servlet.i18n.CookieLocaleResolver.LOCALE');
    $("#list").jqGrid({
    	url: "issue/list",
		mtype: "GET",
        datatype: "json",
        jsonReader : jsonHandler,
        colNames: issue_names[locale],
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
        scrollOffset: 0,
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
    		{multipleSearch : true, // enable the advanced searching
    			multipleGroup:false, // searching using subgroups
    	        showQuery: true // show preview of search query  
    		},
    		{closeOnEscape:true} /* allow the view dialog to be closed when user press ESC key*/
    		);
    
//    		.jqGrid('navButtonAdd',"#pager",{// My custom button
//				caption:'Search...', 
//				buttonicon:'ui-icon-search', 
//				onClickButton: function(){ 
//					$('#searc-form').removeClass('hidden-block');
//					$('#search-button').click(function (){
//						$('#searc-form').addClass('hidden-block');
//					});
//					$('#list').jqGrid('setGridParam', { postData:{command: 'search'}});
//					$('#list').trigger('reloadGrid',[{page:1}]);
//				}, 
//				position:"last",
//				title:"",
//				cursor: "pointer"
//    		});
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

//***************************************** Properties Tables *****************************************

var prop_url = "/issuetracker/prop";
var user_url = '/issuetracker/user';
var project_url = '/issuetracker/project';
var buil_url = '/issuetracker/build';
var edit_url = '/edit';

var jsonHandlerProp = {
        root: "rows",
        page: "page",
        total: "total",
        records: "records",
        repeatitems: true,
        cell: "cell",
        id: "id",
    };

function handleLoadError (jqXHR, textStatus, errorThrown) {
	alert('HTTP status code: ' + jqXHR.status + '\n' +
            'textStatus: ' + textStatus + '\n' +
            'errorThrown: ' + errorThrown);
      alert('HTTP message body (jqXHR.responseText): ' + '\n' + jqXHR.responseText);
}

var myErrorTextFormat = function (data) {
	var message = 'Errors! ';
	try {
		var responseText = JSON.parse(data.responseText);
		for (var i = 0, l = responseText.length; i < l; i++) {
			message += responseText[i].defaultMessage + '\r\n';
		}
	} catch (DataNotFoundEcxeption) {
		message = "";
	}
	return message;
};

function createStatusesTable() {
	var send_data = {type: 'STATUS'};
    $("#statuses-table").jqGrid({
        url: prop_url,
        editurl: prop_url + edit_url,
        postData: send_data,
		mtype: "GET",
        datatype: "json",
        jsonReader : jsonHandlerProp,
        colNames: ["Id", "Name"],
        colModel: [
                   { name: "id", index: 'id', width: 50},
                   { name: "name", index: 'name', width: 200, editable: true, editoptions:{size:"20",maxlength:"30"}}             
               ],
        pager: "#statuses-pager",
        rowNum: 5,
        sortname: "id",
        sortorder: "asc",
        viewrecords: true,
        gridview: true,
        autoencode: true,
        caption: "Statuses",
        height: 100,
		loadError: handleLoadError
    });
    
    $("#statuses-table").jqGrid('navGrid', '#statuses-pager',{view:false, del:true, search:false}, //
    		{url: '/issuetracker/prop/edit', closeAfterEdit: true, editData: send_data}, // use default settings for edit
    		{url: '/issuetracker/prop/add', closeAfterAdd: true, editData: send_data}, // use default settings for add
    		{url: '/issuetracker/prop/del', closeAfterDelete: true, delData: send_data},  // delete instead that del:false we need this
    		{multipleSearch : true}, // enable the advanced searching
    		{closeOnEscape:true} /* allow the view dialog to be closed when user press ESC key*/
    		);
}

function createResolutionsTable() {
	var send_data = {type: 'RESOLUTION'};
    $("#resolutions-table").jqGrid({
        url: prop_url,
        editurl: prop_url + edit_url,
        postData: send_data,
		mtype: "GET",
        datatype: "json",
        jsonReader : jsonHandlerProp,
        colNames: ["Id", "Name"],
        colModel: [
                   { name: "id", index: 'id', width: 50},
                   { name: "name", index: 'name', width: 200, editable: true, editoptions:{size:"20",maxlength:"30"}}             
               ],
        pager: "#resolutions-pager",
        rowNum: 5,
        sortname: "id",
        sortorder: "asc",
        viewrecords: true,
        gridview: true,
        autoencode: true,
        caption: "Resolutions",
        height: 100,
		loadError: handleLoadError
    });
    
    $("#resolutions-table").jqGrid('navGrid', '#resolutions-pager',{view:false, del:true, search:false}, //
    		{url: '/issuetracker/prop/edit', closeAfterEdit: true, editData: send_data}, // use default settings for edit
    		{url: '/issuetracker/prop/add', closeAfterAdd: true, editData: send_data}, // use default settings for add
    		{url: '/issuetracker/prop/del', closeAfterDelete: true, delData: send_data},  // delete instead that del:false we need this
    		{multipleSearch : true}, // enable the advanced searching
    		{closeOnEscape:true} /* allow the view dialog to be closed when user press ESC key*/
    		);
}

function createPrioritiesTable() {
	var send_data = {type: 'PRIORITY'};
    $("#priority-table").jqGrid({
        url: prop_url,
        editurl: prop_url + edit_url,
        postData: send_data,
		mtype: "GET",
        datatype: "json",
        jsonReader : jsonHandlerProp,
        colNames: ["Id", "Name"],
        colModel: [
                   { name: "id", index: 'id', width: 50},
                   { name: "name", index: 'name', width: 200, editable: true, editoptions:{size:"20",maxlength:"30"}}             
               ],
        pager: "#priority-pager",
        rowNum: 5,
        sortname: "id",
        sortorder: "asc",
        viewrecords: true,
        gridview: true,
        autoencode: true,
        caption: "Priorities",
        height: 100,
		loadError: handleLoadError
    });
    
    $("#priority-table").jqGrid('navGrid', '#priority-pager',{view:false, del:true, search:false}, //
    		{url: '/issuetracker/prop/edit', closeAfterEdit: true, editData: send_data}, // use default settings for edit
    		{url: '/issuetracker/prop/add', closeAfterAdd: true, editData: send_data}, // use default settings for add
    		{url: '/issuetracker/prop/del', closeAfterDelete: true, delData: send_data},  
    		{multipleSearch : true}, // enable the advanced searching
    		{closeOnEscape:true} /* allow the view dialog to be closed when user press ESC key*/
    		);
}

function createTypesTable() {
	var send_data = {type: 'TYPE'};
    $("#types-table").jqGrid({
        url: prop_url,
        editurl: prop_url + edit_url,
        postData: send_data,
		mtype: "GET",
        datatype: "json",
        jsonReader : jsonHandlerProp,
        colNames: ["Id", "Name"],
        colModel: [
                   { name: "id", index: 'id', width: 50},
                   { name: "name", index: 'name', width: 200, editable: true, editoptions:{size:"20",maxlength:"30"}}             
               ],
        pager: "#types-pager",
        rowNum: 5,
        sortname: "id",
        sortorder: "asc",
        viewrecords: true,
        gridview: true,
        autoencode: true,
        caption: "Types",
        height: 100,
		loadError: handleLoadError
    });
    
    $("#types-table").jqGrid('navGrid', '#types-pager',{view:false, del:true, search:false}, //
    		{url: '/issuetracker/prop/edit', closeAfterEdit: true, editData: send_data}, // use default settings for edit
    		{url: '/issuetracker/prop/add', closeAfterAdd: true, editData: send_data}, // use default settings for add
    		{url: '/issuetracker/prop/del', closeAfterDelete: true, delData: send_data},  
    		{multipleSearch : true}, // enable the advanced searching
    		{closeOnEscape:true} /* allow the view dialog to be closed when user press ESC key*/
    		);
}

function createRolesTable() {
	var send_data = {type: 'ROLE'};
    $("#roles-table").jqGrid({
        url: prop_url,
        editurl: prop_url + edit_url,
        postData: send_data,
		mtype: "GET",
        datatype: "json",
        jsonReader : jsonHandlerProp,
        colNames: ["Id", "Name"],
        colModel: [
                   { name: "id", index: 'id', width: 50},
                   { name: "name", index: 'name', width: 200, editable: true, editoptions:{size:"20",maxlength:"30"}}             
               ],
        pager: "#roles-pager",
        rowNum: 10,
        sortname: "id",
        sortorder: "asc",
        viewrecords: true,
        gridview: true,
        autoencode: true,
        caption: "Roles",
        height: 200,
		loadError: handleLoadError
    });
    
    $("#roles-table").jqGrid('navGrid', '#roles-pager',{view:false, del:true, search:false}, //
    		{url: '/issuetracker/prop/edit', closeAfterEdit: true, editData: send_data}, // use default settings for edit
    		{url: '/issuetracker/prop/add', closeAfterAdd: true, editData: send_data}, // use default settings for add
    		{url: '/issuetracker/prop/del', closeAfterDelete: true, delData: send_data},  
    		{multipleSearch : true}, // enable the advanced searching
    		{closeOnEscape:true} /* allow the view dialog to be closed when user press ESC key*/
    		);
}

function createProjectsTable() {
    $("#projects-table").jqGrid({
        url: project_url,
        editurl: project_url + edit_url,
		mtype: "GET",
        datatype: "json",
        jsonReader : {
            root: "rows",
            page: "page",
            total: "total",
            records: "records",
            repeatitems: true,
            cell: function(obj) {
            	var manager = JSON.parse(obj.manager);
            	var manager_name = manager.firstName + ' ' + manager.lastName;
            	obj.manager = manager_name;
            	return obj;
            	},
            id: "id"
        },
        colNames: ["Id", "Name", "Description", "Manager"],
        colModel: [
                   { name: "id", index: 'id', width: 50},
                   { name: "name", index: 'name', width: 150, editable: true, editoptions:{size:"20",maxlength:"30"}},
                   { name: "description", index: 'description', width: 300, editable: true, editoptions:{size:"20",maxlength:"30"}},
                   { name: "manager", index: 'manager', width: 200, editable: true, edittype:"select", editoptions: {dataUrl: '/issuetracker/user/options'}, editrules:{required:true}}
               ],
        pager: "#projects-pager",
        rowNum: 5,
        sortname: "id",
        sortorder: "asc",
        viewrecords: true,
        gridview: true,
        autoencode: true,
        caption: "Projects",
        height: 100,
		loadError: handleLoadError
    });
    
    $("#projects-table").jqGrid('navGrid', '#projects-pager',{view:false, del:true, search:false}, //
    		{url: '/issuetracker/project/edit', closeAfterEdit: true}, // use default settings for edit
    		{url: '/issuetracker/project/add', closeAfterAdd: true}, // use default settings for add
    		{url: '/issuetracker/project/del', closeAfterDelete: true},  // delete instead that del:false we need this
    		{multipleSearch : true}, // enable the advanced searching
    		{closeOnEscape:true} /* allow the view dialog to be closed when user press ESC key*/
    );
}

function createBuildsTable() {
    $("#builds-table").jqGrid({
        url: buil_url,
        editurl: buil_url + edit_url,
		mtype: "GET",
        datatype: "json",
        jsonReader : jsonHandlerProp,
        colNames: ["Id", "Name", "Project"],
        colModel: [
                   { name: "id", index: 'id', width: 50},
                   { name: "name", index: 'name', width: 100, editable: true, editoptions:{size:"20",maxlength:"30"}},
                   { name: "project", index: 'project', width: 200, editable: true, edittype:"select", editoptions: {dataUrl: '/issuetracker/project/options'}, editrules:{required:true}}
               ],  
        pager: "#builds-pager",
        rowNum: 4,
        sortname: "id",
        sortorder: "asc",
        viewrecords: true,
        gridview: true,
        autoencode: true,
        caption: "Roles",
        height: 100,
		loadError: handleLoadError
    });
    
    $("#builds-table").jqGrid('navGrid', '#builds-pager',{view:false, del:true, search:false}, //
    		{url: '/issuetracker/build/edit', closeAfterEdit: true}, // use default settings for edit
    		{url: '/issuetracker/build/add', closeAfterAdd: true}, // use default settings for add
    		{url: '/issuetracker/build/del', closeAfterDelete: true},  // delete instead that del:false we need this
    		{multipleSearch : true}, // enable the advanced searching
    		{closeOnEscape:true} /* allow the view dialog to be closed when user press ESC key*/
    		);
    
    jQuery.extend(jQuery.jgrid.edit, {
    	errorTextFormat: myErrorTextFormat
    });
}

var jsonHandlerUsers = {
    root: "rows",
    page: "page",
    total: "total",
    records: "records",
    repeatitems: true,
    cell: function(obj) {
    	var role = JSON.parse(obj.role);
    	obj.role = role.name;
    	return obj;
    	},
    id: "id"
};

function createUsersTable() {
    $("#users-table").jqGrid({
        url: user_url,
        editurl: user_url + edit_url,
		mtype: "GET",
        datatype: "json",
        jsonReader : jsonHandlerUsers,
        colNames: ["Id", "First Name", "Last Name", "E-Mail", "Password", "Role"],
        colModel: [
                   { name: "id", index: 'id', width: 50},
                   { name: "firstName", index: 'firstName', width: 150, editable: true, editoptions:{size:"20",maxlength:"30"}},
                   { name: "lastName", index: 'lastName', width: 150, editable: true, editoptions:{size:"20",maxlength:"30"}},
                   { name: "email", index: 'email', width: 150, editable: true, editoptions:{size:"20",maxlength:"30"}},
                   { name: "password", index: 'password', width: 100, editable: true, editoptions:{size:"20",maxlength:"30"}},
                   { name: "role", index: 'role', width: 100, editable: true, edittype:"select", editoptions: {dataUrl: '/issuetracker/prop/options/ROLE'}, }
               ],
        pager: "#users-pager",
        rowNum: 10,
        sortname: "id",
        sortorder: "asc",
        viewrecords: true,
        gridview: true,
        autoencode: true,
        caption: "Users",
        height: 300,
		loadError: handleLoadError
    });
    
    $("#users-table").jqGrid('navGrid', '#users-pager',{view:true, del:true, search:true}, //
    		{url: '/issuetracker/user/edit', closeAfterEdit: true}, // use default settings for edit
    		{url: '/issuetracker/user/add', closeAfterAdd: true}, // use default settings for add
    		{url: '/issuetracker/user/del', closeAfterDelete: true},  // delete instead that del:false we need this
    		{multipleSearch : true}, // enable the advanced searching
    		{closeOnEscape:true} /* allow the view dialog to be closed when user press ESC key*/
    );
        
    jQuery.extend(jQuery.jgrid.edit, {
    	errorTextFormat: myErrorTextFormat
    });
    
}