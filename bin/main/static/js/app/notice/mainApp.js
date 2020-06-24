

function initApp() {

	$(document).ready(function() {
		initGrid();
	});
	

}


function getColModel() {	
	return [
	           	{name:'id', index:'id', align: 'center', width:60},
	           	{name:'title', index:'title', align: 'left', edittype:'select', formatter:editFormatter, width:120},
           		{name:'instUser', index:'instUser', align: 'center', width:100}, 
           		{name:'instTime',index:'instTime', align: 'center', hidden:true},
	           	{name:'formInstTime', index:'formInstTime', align: 'center', width:130},
           		{name:'readNum',index:'readNum', align: 'center', width:70}
           	];
}

function initGrid() {
	jQuery(document).ready(function () {
		
		jQuery("#clist").jqGrid({
			url: contextPath+"/api/notice",
			datatype: 'json',
			mtype: 'GET',
			colNames: ['ID', '제목', '작성자', '등록시간', '작성시간', '조회수'],
			colModel: getColModel(),
			height: 290,
			//width: 1024,
			autowidth: true,
			rownumbers: true,
			rowNum: 10,
			rowList : [10, 20],
			viewrecords: true,
			loadonce: false,
			pager: '#pager',
		   	shrinkToFit : false,
		   	
		   	beforeProcessing : function(data) {},

		    loadComplete : function (data) {
		    	setLocalDataType(this, data);
		    },

		    onPaging: function(pgButton) {
		    	$(this).setGridParam({datatype:'json'});
		    },

			jsonReader : {
		          root: 'rows',
		          page: 'page',
		          total: 'total',
		          records: 'records',
		          repeatitems: false,
		          cell: 'cell',
		          id: 'id'
			}

		});

		jQuery("#clist").jqGrid('navGrid','#pager',{edit:false,add:false,del:false,search:false},
				{ },
		        { },
		        { }, 
				{ 
			    	sopt:['cn'],
			        closeOnEscape: true, 
		        	multipleSearch: true, 
		         	closeAfterSearch: true 
			    }
		);
		
		jQuery("#btnFilter").click(function(){
			jQuery("#clist").jqGrid('searchGrid',
				{multipleSearch: false, 
					sopt:['cn']}
			);
		});

/*
		$(window).bind('resize', function() {
		    resizeJqGridWidth("clist", "grid_container");
		    getInitGridHeight(initWindowHeight, initGridHeight);
		}).trigger('resize');
*/	
	});
}


function editFormatter(cellValue, options, rowdata, action) {
	return "<a href='javascript:fncUpdateForm("+rowdata.id+")' style='text-decoration:underline;color: #e87122;'>"+cellValue+"</a>";
}


function search() {

	var gridUrl = contextPath+"/api/notice";
	
	var parmam = 'useYN=Y';

	jQuery("#clist").jqGrid("clearGridData", true);
	jQuery("#clist").jqGrid('setGridParam',{datatype:'json', url:gridUrl+'?'+parmam,page:1}).trigger('reloadGrid');
}

