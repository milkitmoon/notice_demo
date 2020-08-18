/**
 * 
 */

/*var windowHeight = $(window).height();
var windowGapSize = initWindowHeight - windowHeight; //최초 브라우저 높이(고정) - 현재 브라우저 높이(가변)
var initWindowHeight = windowHeight;
var initGridHeight = initGridHeight-windowGapSize; // 최초 그리드 높이(고정) - 줄어고 늘어난 브라우저 높이(가변)
*/

function getInitGridHeight(initWindowHeight, initGridHeight){
	var windowGapSize = initWindowHeight - $(window).height(); 
	var initWindowHeight = $(window).height();
	var initGridHeight = initGridHeight- windowGapSize;
	
	$('.ui-jqgrid-bdiv').css('height', initGridHeight-70); //-resizeHeight
	//$('#gview_ulist .ui-jqgrid-bdiv').css('height', initGridHeight); //-resizeHeight
	//$('#gview_mlist .ui-jqgrid-bdiv').css('height', initGridHeight); //-resizeHeight
}

function reloadGrid(targetGrid) {
	$(targetGrid).trigger("reloadGrid"); 
}

function clearGrid(targetGrid) {
	$(targetGrid).jqGrid("clearGridData", true);
}

function updateSize(){
    var lines = $("tr", this),
        flines = $("tr", "#"+$(this).attr("id")+"_frozen" );

    flines.each(function(i, item){
        $(item).height( $(lines[i]).innerHeight() - (i%2?1:0) );
    });
}

function hideCheckAll(targetGrid) {
	var myGrid = $(targetGrid);
	$("#cb_"+myGrid[0].id).hide();
}


function adjustHeight(grid, maxHeight) {
	var initMarginWidth = 80;
	var headerHeight = $("thead:first tr.ui-jqgrid-labels", $(grid)[0].grid.hDiv).height();
	
	marginHeight = Math.abs(headerHeight-(headerHeight*0.290));
	jQuery(grid).jqGrid('setGridHeight', Math.min( maxHeight, (parseInt(jQuery(".ui-jqgrid-btable").css('height'))+marginHeight) ));
}

function onChangeGrid(){
   var frozen = $("#grid_frozen tr", this),
       rows = $("#grid tr", this);

   frozen.each(function(i, item){
     var fEl = $(item);
     var h = $(rows[i]).height();

     if( fEl.height() < h ){
       fEl.height(h); 
     } else {
       fEl.css("height", "auto");
     }
   });
}


function treatGridCheckbox(grid, rowID, columnNM, columnValue, isCheck) {
	$(grid).jqGrid("setCell", rowID, columnNM, columnValue);
	
	$checkbox = $ ($(grid).jqGrid('getGridRowById', rowID) )
			.children('td[aria-describedby="grdOptionsGrid_'+columnNM+'"]')
			.find('input');
	if ($checkbox.is(":checked") !== isCheck) {
		$checkbox.prop('checked', isCheck);
	}
}

/*
function resizeJqGridWidth(grid_id, div_id, isShrinkToFit) {
	var fittingOpt = true;
	
	if(typeof grid_id != 'undefined' && typeof div_id != 'undefined') {
		if(typeof isShrinkToFit != 'undefined') {
			isShrinkToFit = isShrinkToFit && true;
			fittingOpt = isShrinkToFit;
	
//alert("isShrinkToFit:"+isShrinkToFit+"		fittingOpt:"+fittingOpt);
			
			$('#' + grid_id).jqGrid('setGridParam',{shrinkToFit:isShrinkToFit});
		}
		
	   	var resizingWidth = $('#' + div_id).width();
	   	if(typeof resizingWidth != 'undefined' && fittingOpt == true) {
	        // 그리드의 width를 div 에 맞춰서 적용
alert("fittingOpt:"+fittingOpt+"	div_id:"+div_id+"	resizingWidth:"+resizingWidth);
	//   		$('#' + grid_id).setGridWidth(resizingWidth, fittingOpt); //Resized to new width as per window
//	   		$('#' + grid_id).setGridWidth(resizingWidth, true); //Resized to new width as per window
	   	} else {
alert("fittingOpt:"+fittingOpt+"	div_id:"+div_id+"	resizingWidth:"+resizingWidth);
	   		$('#' + grid_id).setGridWidth(resizingWidth, false); //Resized to new width as per window
			$('#' + grid_id).jqGrid('setGridParam',{shrinkToFit:false});
	   	}
	}
}
*/

function resizeJqGridWidth(grid_id, div_id, isShrinkToFit) {
	if( (grid_id != null && typeof grid_id != 'undefined') && (div_id != null && typeof div_id != 'undefined') ) {
		if(typeof isShrinkToFit == 'undefined' || isShrinkToFit != false) {
			var resizingWidth = $('#' + div_id).width();
		   	if( resizingWidth != null && (typeof resizingWidth != 'undefined') ) {
		   		$('#' + grid_id).setGridWidth(resizingWidth, true);
		   	}
		} else {
			$('#' + grid_id).jqGrid('setGridParam',{shrinkToFit:isShrinkToFit});
			
		   	var resizingWidth = $('#' + div_id).width();
		   	if( resizingWidth != null && (typeof resizingWidth != 'undefined') ) {
		   		$('#' + grid_id).setGridWidth(resizingWidth, false);
		   	}
		}
	}
}


function hasRecords(targetGrid) {
	var hasRecords = false;
	var gridReccount = jQuery(targetGrid).getGridParam("reccount");
	if(gridReccount != null && gridReccount != '' && gridReccount > 0) {
		hasRecords = true;
	}
	
	return hasRecords;
}

//loadonce:false 상태 일때는, " $('#grid').jqGrid('getGridParam','data'); " 방식이 동작하지 않음
function getGridData(curGrid) {
	var gridIDs = $(curGrid).jqGrid('getDataIDs');
	var gridData = new Array(gridIDs.length);
	
	for(var i=0; i<gridIDs.length; i++) {
		gridData[i] = jQuery(curGrid).jqGrid('getRowData', gridIDs[i]);
	}
	
	return gridData;
}

function getGridJsonExport(curGrid) {
	return JSON.stringify( getGridData(curGrid) );
}

function getFooterData(curGrid) {
	return $(curGrid).jqGrid('footerData','get');
}

function getColumnFooterData(curGrid, columnName) {
	var footerData = $(curGrid).footerData('get');
	
	return footerData [columnName];
}

function getGridString(gridArray) {
	var gridStr = new String();
	
	for(var i=0; i<gridArray.length; i++) {
		var subArray = gridArray[i];
		
		if(subArray != null && subArray != '') {
			for(var j=0; j<subArray.length; j++) {
				gridStr += subArray[j]+'  ,';
			}
		}
		gridStr += '\n';
	}
	
	return gridStr;
}

function getGridDataByIds(curGrid, gridIDs) {
	var gridData = null;
	
	if( gridIDs != null && gridIDs != '' && typeof gridIDs != 'undefined') {
		gridData = new Array(gridIDs.length);
		
		for(var i=0; i<gridIDs.length; i++) {
			gridData[i] = jQuery(curGrid).jqGrid('getRowData', gridIDs[i]);
		}
	}
	
	return gridData;
}

function setLocalDataType(grid, data) {
    var $this = $(grid);
    
    if (data.rows ==  null) data.rows = "";
    
    if ($this.jqGrid('getGridParam', 'datatype') === 'json') {
        $this.jqGrid('setGridParam', {
            datatype: 'local',
            data: data.rows,
            pageServer: data.page,
            recordsServer: data.records,
            lastpageServer: data.total
        });

        grid.refreshIndex();

        if ($this.jqGrid('getGridParam', 'sortname') !== '') {
            $this.triggerHandler('reloadGrid');
        }
    } else {
        $this.jqGrid('setGridParam', {
            page: $this.jqGrid('getGridParam', 'pageServer'),
            records: $this.jqGrid('getGridParam', 'recordsServer'),
            lastpage: $this.jqGrid('getGridParam', 'lastpageServer')
        });
        grid.updatepager(false, true);
    }
	
}

function setLocalDataType2(grid, data) {
    var $this = $(grid);

    if (data.rows ==  null) data.rows = "";
    
    if ($this.jqGrid('getGridParam', 'datatype') === 'json') {
        $this.jqGrid('setGridParam', {
            datatype: 'local',
            data: data.rows,
            pageServer: data.page,
            recordsServer: data.records,
            lastpageServer: data.total
        });

        grid.refreshIndex();

    } else {
        $this.jqGrid('setGridParam', {
            page: $this.jqGrid('getGridParam', 'pageServer'),
            records: $this.jqGrid('getGridParam', 'recordsServer'),
            lastpage: $this.jqGrid('getGridParam', 'lastpageServer')
        });
        grid.updatepager(false, true);
    }
	
}

/*
function footerSummary(grid, hapColname) {
    var colnames = $(grid).jqGrid('getGridParam','colModel');
    
    for (var i = 0; i < colnames.length; i ++){
        if(typeof(colnames[i]['summaryType']) === 'string' && colnames[i]['summaryType'].indexOf("sum") != -1) {
            var tot = $(grid).jqGrid('getCol',colnames[i]['name'],false,'sum');

            var ob = [];
			
            if( !isNaN(tot) ) {
           		ob[colnames[i]['name']] = tot;
            } else {
           		ob[colnames[i]['name']] = $.jgrid.format('{0}', 0);
            }
            $(grid).jqGrid('footerData','set',ob);
        }
        if(typeof(colnames[i]['summaryType']) === 'string' && colnames[i]['summaryType'].indexOf("count") != -1 ) {
            var recordsCount = jQuery(grid).jqGrid('getGridParam', 'records');
            
            var obCount = [];            
            if( !isNaN(recordsCount) ) {
	        	obCount[colnames[i]['name']] = recordsCount;
	            $(grid).jqGrid('footerData','set',obCount);
            } else {
            	obCount[colnames[i]['name']] = '0';
            	$(grid).jqGrid('footerData','set', obCount);
            }
        }
        if(typeof(colnames[i]['summaryType']) === 'string' && colnames[i]['summaryType'].indexOf("avg") != -1) {
        	var recordsCount = jQuery(grid).jqGrid('getGridParam', 'records');
            var tot = $(grid).jqGrid('getCol',colnames[i]['name'],false,'sum');

            var obAvg = [];
            if( !isNaN(tot) ) {
	        	obAvg[colnames[i]['name']] = tot/recordsCount;
	            $(grid).jqGrid('footerData','set',obAvg);
            } else {
            	obAvg[colnames[i]['name']] = '0';
            	$(grid).jqGrid('footerData','set', obAvg);
            }
        }
    }
    
    $(grid).jqGrid('footerData','set',hapColname);
}
*/

function footerSummary(grid, hapColname) {
    var colnames = $(grid).jqGrid('getGridParam','colModel');
    
    for (var i = 0; i < colnames.length; i ++) {
        var summaryTpl = colnames[i]['summaryTpl'];
        
        if(typeof(colnames[i]['summaryType']) === 'string' && colnames[i]['summaryType'].indexOf("sum") != -1) {
            var tot = $(grid).jqGrid('getCol',colnames[i]['name'],false,'sum');

            var ob = [];
			
            if( !isNaN(tot) ) {
            	if(summaryTpl != null && summaryTpl != '' && summaryTpl != 'undefined') {
            		ob[colnames[i]['name']] = $.jgrid.format(summaryTpl, tot);
            	} else {
            		ob[colnames[i]['name']] = $.jgrid.format('{0}', tot);
            	}
            } else {
           		ob[colnames[i]['name']] = $.jgrid.format('{0}', 0);
            }
            
            $(grid).jqGrid('footerData','set',ob);
        }
        if(typeof(colnames[i]['summaryType']) === 'string' && colnames[i]['summaryType'].indexOf("count") != -1 ) {
            var recordsCount = jQuery(grid).jqGrid('getGridParam', 'records');
            
            var obCount = [];            
            if( !isNaN(recordsCount) ) {
            	if(summaryTpl != null && summaryTpl != '' && summaryTpl != 'undefined') {
            		obCount[colnames[i]['name']] =$.jgrid.format(summaryTpl, recordsCount);
            	} else {
            		obCount[colnames[i]['name']] = $.jgrid.format('{0}', recordsCount);
            	}
            } else {
            	obCount[colnames[i]['name']] = '0';
            }
            $(grid).jqGrid('footerData','set',obCount);
        }
        if(typeof(colnames[i]['summaryType']) === 'string' && colnames[i]['summaryType'].indexOf("avg") != -1) {
        	var recordsCount = jQuery(grid).jqGrid('getGridParam', 'records');
            var tot = $(grid).jqGrid('getCol',colnames[i]['name'],false,'sum');

            var obAvg = [];
            if( !isNaN(tot) ) {
            	var avgVar = tot/recordsCount;
            	if( isNaN(avgVar) )  avgVar = 0;
            	if(summaryTpl != null && summaryTpl != '' && summaryTpl != 'undefined') {
            		obAvg[colnames[i]['name']] =$.jgrid.format(summaryTpl, avgVar);
            	} else {
            		obAvg[colnames[i]['name']] = $.jgrid.format('{0}', avgVar);
            	}
            } else {
            	obAvg[colnames[i]['name']] = '0';
            }
            $(grid).jqGrid('footerData','set',obAvg);
        }
    }
    
    $(grid).jqGrid('footerData','set',hapColname);
}

function footerSummaryExcludeSubTotal(grid, hapColname,multiple) {
    var colnames = $(grid).jqGrid('getGridParam','colModel');
    
    for (var i = 0; i < colnames.length; i ++) {
    	
        var summaryTpl = colnames[i]['summaryTpl'];
        
        if(typeof(colnames[i]['summaryType']) === 'string' && colnames[i]['summaryType'].indexOf("sum") != -1) {
            var tot = $(grid).jqGrid('getCol',colnames[i]['name'],false,'sum')/multiple;

            var ob = [];
			
            if( !isNaN(tot) ) {
            	if(summaryTpl != null && summaryTpl != '' && summaryTpl != 'undefined') {
            		ob[colnames[i]['name']] = $.jgrid.format(summaryTpl, tot);
            	} else {
            		ob[colnames[i]['name']] = $.jgrid.format('{0}', tot);
            	}
            } else {
           		ob[colnames[i]['name']] = $.jgrid.format('{0}', 0);
            }
            
            $(grid).jqGrid('footerData','set',ob);
        }
        if(typeof(colnames[i]['summaryType']) === 'string' && colnames[i]['summaryType'].indexOf("count") != -1 ) {
            var recordsCount = jQuery(grid).jqGrid('getGridParam', 'records');
            
            var obCount = [];            
            if( !isNaN(recordsCount) ) {
            	if(summaryTpl != null && summaryTpl != '' && summaryTpl != 'undefined') {
            		obCount[colnames[i]['name']] =$.jgrid.format(summaryTpl, recordsCount);
            	} else {
            		obCount[colnames[i]['name']] = $.jgrid.format('{0}', recordsCount);
            	}
            } else {
            	obCount[colnames[i]['name']] = '0';
            }
            $(grid).jqGrid('footerData','set',obCount);
        }
        if(typeof(colnames[i]['summaryType']) === 'string' && colnames[i]['summaryType'].indexOf("avg") != -1) {
        	var recordsCount = jQuery(grid).jqGrid('getGridParam', 'records');
            var tot = $(grid).jqGrid('getCol',colnames[i]['name'],false,'sum');

            var obAvg = [];
            if( !isNaN(tot) ) {
            	var avgVar = tot/recordsCount;
            	if( isNaN(avgVar) )  avgVar = 0;
            	if(summaryTpl != null && summaryTpl != '' && summaryTpl != 'undefined') {
            		obAvg[colnames[i]['name']] =$.jgrid.format(summaryTpl, avgVar);
            	} else {
            		obAvg[colnames[i]['name']] = $.jgrid.format('{0}', avgVar);
            	}
            } else {
            	obAvg[colnames[i]['name']] = '0';
            }
            $(grid).jqGrid('footerData','set',obAvg);
        }
    }
    
    $(grid).jqGrid('footerData','set',hapColname);
}

//색 및 % 추가
function percentFormat(cellval, opts, rwdat, act) {
	var color = '"color:gray"';
    if (cellval > 0) {
     	color = '"color:red"';
    } else if (cellval < 0){
    	color = '"color:blue"';
    }
     
    var percentVar = round(cellval, 2);

     if(percentVar > 100) {
    	 percentVar = 100;
     } else if(percentVar == '99.99') {	//--;;
    	 percentVar = 100;
     }

     return '<span style='+color+'>'+
     			$.fn.fmatter('string', percentVar, opts, rwdat, act) +' %'+
     		'</span>';
}

function percentUnFormat(cellvalue, options, cell) {
	return $('span', cell).text().replace( /%/g, '' ).replace(/(^\s*)|(\s*$)/gi, "");
}

function formatPhoneNumber(cellvalue, options, rowObject) { 
	
	if(cellvalue != null && cellvalue != '' && cellvalue != 'null') {
		cellvalue=cellvalue.replace(/(^02.{0}|^01.{1}|[0-9]{3})([0-9,*]+)([0-9]{4})/,"$1-$2-$3");
	} else {
		cellvalue = '<span></span>';
	}
	
	return cellvalue;
}

function unformatPhoneNumber(cellvalue, options, rowObject) { 
   cellvalue=cellvalue.replace( /-/g, '' ).replace(/(^\s*)|(\s*$)/gi, "");  
   return cellvalue;
}

function formatDateString(cellvalue, options, rowObject) { 
	if( !isNaN(cellvalue) ) {
		return timeStrToFormatTimeString2(cellvalue);
	} else {
		return '<span>'+cellvalue+'</span>';
	}
}
function unformatDateString(cellvalue, options, rowObject) { 
	cellvalue=cellvalue.replace( /-/g, '' ).replace(/(^\s*)|(\s*$)/gi, "");
	cellvalue=cellvalue.replace( /:/g, '' ).replace(/(^\s*)|(\s*$)/gi, "");
	
	return cellvalue;  
}


//카드번호에 하이픈을 포함
function cardNumberHyphenFormat(cellval, opts, rwdat, act) {
	var strTempThisValue = '-';
	if(cellval != null && cellval != '' && cellval != 'undefined') {
		var pattern1 = /(^\d{4}|^\d{4}-\d{3}|^\d{4}-\d{3}-\d{7})(\d+)/;
		
		strTempThisValue = cellval.split("-").join(""); // remove hyphens
		strTempThisValue = strTempThisValue.match(new RegExp('.{1,4}', 'g')).join("-");
	}
	
	return strTempThisValue;
}


//카드번호에서 하이픈을 빼고 반환.
function cardNumberHyphenUnFormat(cellvalue, options, cell){
	cellvalue=cellvalue.replace(/-/g,"");
	if(isNaN(cellvalue)){return 0;} else{return cellvalue;}
}

function blankFormat(cellval, opts, rwdat, act) {
	var strTempThisValue = '-';
	if(cellval != null && cellval != '' && typeof cellval != 'undefined') {
		strTempThisValue = cellval;
	}
	return strTempThisValue;
}

function blankUnFormat(cellvalue, options, cell) {
	return "";
}


function pointerCursorFormat(cellvalue, options, rowObject) { 
	var strTempThisValue = '-';
	if(cellvalue != null && cellvalue != '' && typeof cellvalue != 'undefined') {
		strTempThisValue = cellvalue;
	}
	
	return '<span onmouseover="this.className=\'pointerCursor\'" onmouseout="this.className=\'pointerCursorOut\'">' + strTempThisValue + '</span>'; 
} 

function pointerCursorUnFormat(cellvalue, options, cell) {
	var retVal = $(cell).text();
	
	if(retVal == '-') {
		retVal = '';
	}
	
	return retVal;
}

function commonUnFormat(cellvalue, options, cell) {
	var retVal = cellvalue;
	
	if($(cell).text() == '-') {
		retVal = '';
	}
	return retVal;
}

function editCellFormatter(cellValue, options, rowdata, action) {
	return '<span style="text-decoration:underline; color: blue;">'+cellValue+"</span>";
}
function editCellUnFormatter(cellvalue, options, cell) {
	return $(cell).text();
}

function editInputCellFormatter(cellValue, options, rowdata, action) {
	var borderWidth = 100;
	
	var columnProp = jQuery(this).jqGrid('getColProp', options.colModel.index);
	if(columnProp.width != null && columnProp.width != '' && typeof columnProp.width != 'undefined') {
		borderWidth = columnProp.width-10;
	}
	
	return '<span style="display:block; border:1px solid #e0e0e0; border-style: solid; background:#f0f0f0; width:'+borderWidth+'px; padding-left: 10px; padding-right: 10px; " >'+cellValue+'</span>';
}

function editInputCellUnFormatter(cellvalue, options, cell) {
	return $(cell).text();
}


function editCellTSFormatter(cellValue, options, rowdata, action) {
	return "<span style='color: blue;'>"+addComma(cellValue)+"</span>";
}

function editCellTSUnFormatter(cellvalue, options, cell) {
	return removeComma($(cell).text());
}


function emphFormatter(cellValue, options, rowdata, action) {
	return "<span style='color: red;'>"+cellValue+"</span>";
}

function emphUNFormatter(cellvalue, options, cell) {
	return $(cell).text();
}
