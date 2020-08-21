

function initApp(wmode, id) {

	$(document).ready(function() {

		fncFormRequest('#listForm', contextPath+'/api/noticeattach/upload', 
			function($form, options) {
				return insertResourceData()
			},
			function(result, statusText, xhr, $form) {
				$("#mlist").jqGrid("clearGridData", true);

				if(result.code == RESULT_CODE_OK) {
				   	alert("파일이 업로드되었습니다.");

console.log(result);				   	
				   	
				   	var resultData = result.value;

				   	fncSearchResourceDataInfo(resultData.noticeID);
				   	if(resultData.resourceType == '1') {
				   		renderImage("#posImg", resultData.url, resultData.thumbUrl, "#imgDialog", null);
				   	} else {
				   		renderImage("#posImg", '../../images/blank_img.png', '../../images/blank_img.png', "#imgDialog", null);
				   	}
				} else {
					alert(result.message);
				}

				fncClearFileNode();
			},
			function(request, status, error) {
			    if(request.status == 0) {
			        alert("파일 업로드가 실패하였습니다.");
			    } else {
			        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			    }

			    fncClearFileNode();
			}
		);

		jQuery("#wmode").val(wmode);
		jQuery("#id").val(id);
		jQuery("#noticeID").val(id);

		if(typeof wmode != 'undefined' && wmode == 'u') {
			initInfo(id);
		}

		initGrid();
	});

}

/*
function beforeSerialize($form, options) {
	return insertResourceData();
}

function processSuccess(result, statusText, xhr, $form) {
	$("#mlist").jqGrid("clearGridData", true);

	if(result.resultCode == RESULT_CODE_OK) {
	   	alert("파일이 업로드되었습니다.");

	   	var resultData = result.resultValue;

	   	fncSearchResourceDataInfo(resultData.noticeID);
	   	if(resultData.resourceType == '1') {
	   		renderImage("#posImg", resultData.url, resultData.thumbUrl, "#imgDialog", null);
	   	} else {
	   		renderImage("#posImg", '../../images/blank_img.png', '../../images/blank_img.png', "#imgDialog", null);
	   	}
	} else {
		alert(result.resultMessage);
	}

	fncClearFileNode();
}

function processError(request, status, error) {
    if(request.status == 0) {
        alert("파일 업로드가 실패하였습니다.");
    } else {
        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
    }

    fncClearFileNode();
}
*/

function initInfo(id) {

	fncJsonRequest2('GET', contextPath+'/api/notice/'+id, true, null,
		function(resultCode, resultMessage, resultValue) {
			if (resultCode == RESULT_CODE_OK) {
				$("#id").val(resultValue.id);

				$("#title").val(resultValue.title);
				$("#content").val(resultValue.content);
			} else {
				alert(resultMessage);
			}
		}
	);

}


function initGrid() {

    $('#mlist').jqGrid({
    	url: contextPath+"/api/noticeattach/"+jQuery("#id").val(),
        datatype: 'json',
        mtype: 'GET',
        colNames: ['ID', '게시물ID', '파일명', '리소스구분코드', '리소스구분', '경로', 'THUMB경로', '사용유무', '삭제'],
        colModel: [
            {name: 'id', index: 'id', align: 'center', hidden:true},
            {name: 'noticeID', index: 'noticeID', hidden:true},
            {name: 'filename', index: 'filename', align: 'center', width: 80},
            {name: 'resourceType', index: 'resourceType', hidden:true},
            {name: 'resourceTypeNM', index: 'resourceTypeNM', align: 'center', width: 80, formatter:resourceTypeNMFormatter},
            {name: 'url', index: 'url', hidden:true},
            {name: 'thumbUrl', index: 'thumbUrl', hidden:true},
            {name: 'useYN', index: 'useYN', hidden:true},
            {name: 'delForm', index: 'delForm', align: 'center', width: 40, formatter:delFormatter}
        ],
//	        height: initGridHeight,
        height: 184,
        width: 300,
        rownumbers: true,
        rowNum: 10,
        viewrecords: true,
        loadonce: false,
		cellEdit: true,
		cellsubmit: 'clientArray',
//	        shrinkToFit: true,
        pager: '#mpager',
        pgbuttons: false,
	   	pgtext: false,
	   	pginput:false,
        beforeProcessing : function(data) {},
        loadComplete : function (data) {
	    	setLocalDataType(this, data);
	    },
	    onCellSelect: function(rowid,icol,cellcontent,e) {
	    	var td = e.target;
	        var index = $.jgrid.getCellIndex(td);
	        if(index != 1) {
		    	var rowData = jQuery("#mlist").jqGrid('getRowData',rowid);

//			    	jQuery("#slist").jqGrid('setGridParam', {cmpCD: rowData.cmpCD, brandCD: rowData.brandCD, storeCD: rowData.storeCD});
//			    	jQuery("#caption").html('상품 목록  ['+rowData.storeNM+' - '+rowData.itemSeq+']');

		    	if(rowData.resourceType == '1') {
		    		renderImage("#posImg", rowData.url, rowData.thumbUrl, "#imgDialog", null);
		    	} else {
		    		renderImage("#posImg", '../../images/blank_img.png', '../../images/blank_img.png', "#imgDialog", null);
		    	}
	        }
	    },
	    jsonReader : {
            root: 'value.rows',
            page: 'value.page',
            total: 'value.total',
            records: 'value.records',
            repeatitems: false,
            cell: 'cell',
            id: 'seq'
        }

    });

    $("#mlist").jqGrid('navGrid','#mpager',{edit:false,add:false,del:false,search:false},
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

    $("#btnFilter").click(function(){
        $("#mlist").jqGrid('searchGrid',
            {multipleSearch: false,
                sopt:['cn']}
        );
    });

}


function validateNotice() {
	var title = jQuery("#title").val();
	var content = jQuery("#content").val();

	if (title == "") {
		alert("제목을 입력해 주세요");
		$("#title").focus();
		return false;
	}

	if (content == "") {
		alert("내용을 입력해 주세요");
		$("#content").focus();
		return false;
	}

	return true;
}


function write() {
	if( validateNotice() ) {
		if(confirm("공지사항을 등록/수정 하시겠습니까 ?")) {

			var wmode = jQuery("#wmode").val();
			var id = jQuery("#id").val();
			var title = jQuery("#title").val();
			var content = jQuery("#content").val();

			var obj = new Object();
			obj.id = id;
			obj.title = title;
			obj.content = content;
			var userJSON = JSON.stringify(obj);

//console.log(userJSON);
			var gridUrl = contextPath+"/api/notice";

			if(typeof wmode != 'undefined' && wmode == 'i') {
				fncJsonPost(gridUrl, userJSON);
			} else {
				fncJsonPut(gridUrl, userJSON);
			}

			return true;
		}
	}

	return false;
}


function insertResourceData() {
	var isInsertOK = false;

	if( fncInsertReady() ) {
		if(confirm("파일을 업로드 하시겠습니까 ?")) {
			isInsertOK = true;
		};
	}

	return isInsertOK;
}


function fncInsertReady() {
	var isInsertReady = true;

	if($('#uploadfile').val() == '') {
		alert("파일을 먼저 등록 하여야 합니다.");
		$('#uploadfile').focus();

		isInsertReady = false;
	}

	return isInsertReady;
}

function fncClearFileNode() {
	$("#fileDiv").html("<input style='height:20px' path='uploadfile' type='file' id='uploadfile' name='uploadfile' onchange='fncUploadReady()'/>");

    $(function(){
      	 $("input[type=file]").filestyle({
      	 image: "../../images/btn_file.gif",

      	 imagewidth : 80,
      	 marginleft : 10,
      	 width : 180,
      	 border:"1px solid #000000"
      	 });
      });
}

function renderImage(imgID, imagePath, imageTmbPath, dialogID, imageCallbackFnc) {
	if (imagePath != null && imagePath != ''
			&& typeof imagePath != 'undefined') {
		var image = $('<img/>', {
			id : 'Myid',
			src : imageTmbPath,
			alt : 'MyAlt',
			width : 240,
			height : 240,
			onclick : 'javascript:imageDialog("' + imagePath + '","'+ dialogID + '")'
		});

		$(imgID).html(image);
	}

	if (imageCallbackFnc == null || (imageCallbackFnc && typeof(imageCallbackFnc) !== 'function')) {
		imageCallbackFnc = function() {};
	}

	imageCallbackFnc();
}

function imageDialog(resourceURL, dialogID) {

	if (dialogID != null && dialogID != '' && typeof dialogID != 'undefined') {

		$("" + dialogID).hide().empty();
		this.$OuterDiv = $("" + dialogID).hide().append(
				$('<img style="position:absolute; top:0; bottom:0; left:0; right:0; margin: auto;"></img>').attr({
					src : resourceURL,
					width : 368,
					height : 392
				}));
	} else {
		this.$OuterDiv = $('<div/>').hide().append($('<img style="position:absolute; top:0; bottom:0; left:0; right:0; margin: auto;"></img>').attr({
			src : resourceURL,
			width : 368,
			height : 392
		}));
	}

	// this.$OuterDiv =
	// parent.$("#imgDialog").hide().append($('<img></img>').attr({src:
	// resourceURL,width: 500,height: 350}));

	this.$OuterDiv.dialog({
		autoOpen : true,
		width : 386,
		height : 454,
		resizable : false
	});
}

function resourceTypeNMFormatter(cellValue, options, rowdata, action) {
	var formatHtml = '';

	if(rowdata.resourceType == "1") {
		formatHtml = "<a style= 'color: #0040FF;'>이미지</a>";
	} else {
		formatHtml =  "<a style= 'color: #DC143C;'>기타</a>";
	}

	return formatHtml;
}

function delFormatter(cellValue, options, rowdata, action) {
	return "<a href='#' onclick='javascript:deleteBtn("+rowdata.id+"); return false;'><img src='../../images/delete_btn.gif' alt='삭제' title='삭제' /></a>";
}

function deleteBtn(selectedID) {
	var gridUrl = contextPath+'/api/noticeattach/'+selectedID;

	if(confirm("삭제하시겠습니까?")) {
		fncJsonRequest2('DELETE', gridUrl, false, '', function(resultCode, resultMessage, resultValue) {
			alert(resultMessage);

			if (resultCode == RESULT_CODE_OK) {
				fncSearchResourceDataInfo($("#id").val());
			}
		});

		return true;
	}

	return false;
}


function fncSearchResourceDataInfo(noticeID) {
	var gridUrl = contextPath+"/api/noticeattach/"+noticeID;

	if(noticeID == null || noticeID == '' || typeof noticeID == 'undefined') {
		return;
	}

	var param = 'noticeID='+noticeID+'&useYN=Y';

	$("#mlist").jqGrid("clearGridData", true);
	$("#mlist").jqGrid('setGridParam',{datatype:'json', url:gridUrl+'?'+param,page:1}).trigger('reloadGrid');
}
