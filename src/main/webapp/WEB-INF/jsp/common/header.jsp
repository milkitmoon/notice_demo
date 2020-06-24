<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<!DOCTYPE HTML>
<!-- <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>Main</title>

<link rel="stylesheet" href="../../css/style.css" type="text/css">
<link rel="stylesheet" href="../../css/font.css" type="text/css">
<link rel="stylesheet" href="../../css/font-awesome.min.css" type="text/css">
<link rel="stylesheet" href="../../css/jquery-ui.css" type="text/css">
<link rel="stylesheet" href="../../css/ui.jqgrid.css" type="text/css">


<script type="text/javascript" src="../../js/util/jsapi.js"></script>
<script type="text/javascript" src="../../js/util/map.js"></script>
<script type="text/javascript" src="../../js/util/date.js"></script>
<script type="text/javascript" src="../../js/util/CommonGrid.js"></script>

<script type="text/javascript" src="../../js/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="../../js/jquery/jquery-migrate-1.1.1.js"></script>

<script type="text/javascript" src="../../js/jquery/plugins/jquery-ui-1.10.2.custom.min.js"></script>
<script type="text/javascript" src="../../js/jquery/plugins/jquery.jqGrid.min.js"></script>
<script type="text/javascript" src="../../js/jquery/plugins/i18n/grid.locale-kr.js"></script>

<script type="text/javascript" src="../../js/jquery/plugins/jquery.form.js"></script>
<script type="text/javascript" src="../../js/jquery/plugins/jquery.filestyle.mini.js"></script>

<script type="text/JavaScript" src="../../js/style.js"></script>

<script type="text/javascript" src="../../js/util/jsonRequest.js" charset="UTF-8"></script>


<style type="text/css">
	html {
	     overflow-y: hidden;
	}
</style>


<script language="JavaScript" type="text/JavaScript">

var serverName = '${pageContext.request.serverName}';
var contextPath = '${pageContext.request.contextPath}';

var USERINFO_USER_ID = '${UserInfo.userID}';
var USERINFO_USER_NM = '${UserInfo.userNM}';
var USERINFO_AUTH_ROLE = '${UserInfo.authRole}';

var RESULT_CODE_OK = "0";

var $ = jQuery.noConflict();

function logOut() {
	//$.session.clear();
//	location.href = contextPath + "/login/logout.do";
	location.href = contextPath + "/logout";
}


</script>

</head>


<body class="skin-blue">
