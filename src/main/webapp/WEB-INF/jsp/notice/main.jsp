<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@ include file="/WEB-INF/jsp/common/header.jsp" %>

<!DOCTYPE HTML>
<!-- <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>Main</title>

<style type="text/css" media="screen">

.ui-jqgrid .ui-jqgrid-htable th div {
    height:auto;
    overflow:hidden;
    padding-right:4px;
    padding-top:2px;
    position:relative;
    vertical-align:text-top;
    white-space:normal !important;
}

</style>


<script type="text/javascript" src="../../js/app/notice/mainApp.js" charset="utf-8"></script>

<script language="JavaScript" type="text/JavaScript">
initApp();

function fncSearchForm() {
	search();
}

function fncInsertForm(){
	window.location.href = '/write';
}

function fncUpdateForm(id) {
	window.location.href = '/read?id='+id;
}


</script>




<form name="listForm" commandName="listForm" method="GET">
</form>

<body>
	<main>
		<div class="tit-bar"><i class="fa fa-map-marker"></i> 공지사항 관리
			<a href="javascript:logOut()" class="logout"><img src="../../images/ic_exit_to_app_white_24dp_1x.png"></a>
			<button class="btn_search" onclick="javascript:fncSearchForm();"><i class="fa fa-search"> </i> 조회</button>
		</div>
<!-- 		
		<div id='storeSelect' style="display:none; width: 100%;"></div>
		<section class="inp_01">
			<div class="mem_nm">
				<div>장비구분</div>
					<select class="form-control input-xs" name="deviceCD" id="deviceCD" ></select>
			</div>
			<div class="mem_cd">
				<div>권한</div>
					<select class="form-control input-xs" style="width:110px; height:20px;" name="authorityCD" id="authorityCD" ></select>
			</div>
		</section>
 -->
 	
		<section class="sec chart last">
			<div class="mem" id="grid_chart">
				<strong>공지사항 목록</strong>
				<div class="btn_group" style="height: 50px; text-align: right; margin-top: -50px;">
	                <button id="download" class="btn btn_red" onclick="javascript:logOut(); return false;"><i class="fa fa-table"></i> 로그아웃</button>
	           </div>
				<div id="grid_container">
					<table id="clist"></table>
					<div id="pager" ></div>
				</div>
				<div class="btn_group">
					<button class="btn btn_submit modalButton" onclick="javascript:fncInsertForm();" data-slidepanel='panel'><i class="fa fa-plus"></i> 등록</button>
				</div>
			</div>
		</section>
	</main>
</body>


<%@ include file="/WEB-INF/jsp/common/footer.jsp" %>