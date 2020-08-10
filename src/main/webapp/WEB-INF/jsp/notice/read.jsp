<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/header.jsp" %>

<style type="text/css" media="screen">


</style>


<script type="text/javascript" src="../../js/app/notice/readApp.js" charset="utf-8"></script>

<script language="JavaScript" type="text/JavaScript">
initApp("<c:out value='${id}' />");


function fncUpdate() {
	var id = jQuery("#id").val();
	window.location.href = '/update?id='+id;
}

function fncDelete() {
	if( confirm("공지글을 삭제하시겠습니까?") ) {
		deleteNotice();
		window.location.href = '/main';
	}
}

function fncBack() {
	window.location.href = '/main';
}

</script>



<form name="listForm" commandName="listForm" method="GET">
	<input id="id" name="id" type="hidden" value ="-1"/>
</form>


<body>
	<main>
		<div class="tit-bar"><i class="fa fa-map-marker"></i> 공지사항 조회</div>

		<section class="inp_01">
			<div class="mem_nm">
				<div class="pl10">글제목</div>
				<div id="title" class="pl10" style="width:160px;  background: white;  font-weight: normal; text-align: left;"></div>
			</div>
			<div class="mem_nm">
				<div class="pl10">작성자</div>
				<div id="instUser" class="pl10" style="width:160px;  background: white;  font-weight: normal; text-align: left;"></div>
			</div>
		</section>
		<section class="inp_02">
			<div class="mem_nm">
				<div class="pl10">등록시간</div>
				<div id="instTime" class="pl10" style="width:160px;  background: white;  font-weight: normal; text-align: left;"></div>
			</div>
			<div class="mem_nm">
				<div class="pl10">최종수정시간</div>
				<div id="updTime" class="pl10" style="width:160px;  background: white; font-weight: normal; text-align: left;"></div>
			</div>
		</section>
		
		<section class="inp_02">
			<div class="mem_nm">
				<div class="pl10">첨부파일</div>
				<div id="attachFiles" class="pl10" style="width:800px;  background: white;  font-weight: normal; text-align: left;">

				</div>
			</div>
		</section>
		<section class="inp_02">
			<!-- <textarea name="content" id="content" rows="10" cols="20" style="width:100%; height:300px; overflow:hidden; border:0;" readonly></textarea> -->
			<textarea name="content" id="content" rows="10" cols="20" style="width:100%; height:230px; overflow:hidden; border: none;" readonly></textarea>
		</section>
		
		<div class="btn_group" style="padding-bottom:15px;margin-top:6px;">
			<button class="btn btn_red modalButton" onclick="javascript:fncUpdate();" data-popup="popupOne" style="width:75px">수정하기</button>
			<button class="btn btn_red modalButton" onclick="javascript:fncDelete();" data-popup="popupOne" style="width:75px">삭제하기</button>
			<button class="btn btn_default closeBtn" onclick="javascript:fncBack();" style="width:75px">돌아가기</button>
		</div>

						
	</main>
</body>


<%@ include file="/WEB-INF/jsp/common/footer.jsp" %>