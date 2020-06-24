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


</style>


<script type="text/javascript" src="../../js/util/filestyle.js"></script>
<script type="text/javascript" src="../../js/util/jqueryFormRequest.js"></script>

<script type="text/javascript" src="../../js/app/notice/writeApp.js" charset="utf-8"></script>

<script language="JavaScript" type="text/JavaScript">

$(function(){  
	$("input[type=file]").filestyle({ 
		image: "../../images/btn_file.gif", 

		imagewidth : 80,
		marginleft : 10,
		width : 180,
		border:"1px solid #000000"
	}); 
});

function fncUploadReady() {
}


initApp("<c:out value='${wmode}' />", "<c:out value='${id}' />");

function fncRegist() {
	if(write() == true) {
		window.location.href = '/main';
	}
}

function fncBack() {
	if(confirm("공지사항을 작성하지 않고 돌아가시겠습니까 ?\n작성된 글은 저장되지 않습니다.")) {
		window.location.href = '/main';
	}
}

</script>




<input id="wmode" name="wmode" type="hidden" value ="i"/>
<input id="id" name="id" type="hidden" value ="-1"/>

<body>
	<main>
		<div class="tit-bar"><i class="fa fa-map-marker"></i> 공지사항 등록/갱신
		</div>
		
		<section class="inp_modal">
			<div class="mem_nm">
			<div class="pl10"><font style='color:#FF007F;font-weight:bold;'>*</font>글제목</div>
				<input name="title" id="title" type="text" class="input" style="width:460px;" maxlength="200" />
			</div>
		</section>
		<section class="inp_modal mt0">
			<!-- <textarea name="content" id="content" rows="10" cols="20" style="width:100%; height:300px; overflow:hidden; border:0;" readonly></textarea> -->
			<textarea name="content" id="content" rows="10" cols="20" style="width:100%; height:230px; overflow:hidden; border:1;"></textarea>
		</section>
		
		
		<form id="listForm" name="listForm" commandName="uploadFileForm" enctype="multipart/form-data" method="post" >
		<input id="noticeID" name="noticeID" type="hidden" value ="-1"/>

			<section class="inp_01">
	            <div class="mem_nm">
					<div>업로드파일</div>
					<span id="fileDiv">
						<input style="height:20px" path="uploadfile" type='file' id='uploadfile' name='uploadfile' onchange='fncUploadReady()'/>
					</span>
					<span style="display: inline-block; margin-left: 80px;">
						<input id="submitbutton" type="image" style="width:56px; height:20px; border:none;" src="../../images/btn_04.gif" />
					</span>
				</div>
			</section>
					 
			<section class="inp_01_exc">
				<section class="sec chart last q3_up" style="background: #eff2f5;">
					<div id="mparent" style="display:inline-block; float: left;">
						<div style="height: 50px; line-height: 50px; padding-left: 10px; text-align: left">
							<span style="color: #3C4A57; font-size: 16px; font-weight: 500;">업로드파일 정보</span>&nbsp;<span style="color: #FF007F; font-size: 13px; font-weight: 500;"></span>
						</div>
						<div id="mgrid">
							<table id="mlist"></table>
							<div id="mpager"></div>
						</div>
					</div>
					                        
					<div style="display: inline-block;" class="width-20"/>
					                        
					<div id="rparent" style="display: inline-block; ">
						<div style="text-align: left">
							<div style="height: 50px; line-height: 50px; padding-left: 10px; text-align: left">
								<span style="color: #3C4A57; font-size: 16px; font-weight: 500;">미리보기 (이미지파일일 경우)</span>
							</div>
							<div style="height:160px;">
								<div style="padding-left:10px;">
									<a id="posImg"><img src="../../images/blank_img.png" alt="productImg" width="184" height="196" style="display: inline-block;"></a>
								</div>
							</div>
						</div>
					</div>
				</section>
			</section>

		</form>
		
		
		<div class="btn_group" style="padding-bottom:15px;margin-top:6px;">
			<button class="btn btn_red modalButton" onclick="javascript:fncRegist();" data-popup="popupOne" style="width:75px">등록하기</button>
			<button class="btn btn_default closeBtn" onclick="javascript:fncBack();" style="width:75px">돌아가기</button>
		</div>
						
	</main>
</body>


<%@ include file="/WEB-INF/jsp/common/footer.jsp" %>