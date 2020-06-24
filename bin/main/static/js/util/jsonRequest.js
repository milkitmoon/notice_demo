
var xmlHttp;
var RESULT_OK = 0;

function fncJsonPost(url, userJSON) {
	xmlHttp = createXMLHttpRequest();
	xmlHttp.open("POST", url, true);
	xmlHttp.onreadystatechange = handleStateChange;
//	xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xmlHttp.setRequestHeader("Content-type", "application/json;charset=UTF-8");
//	xmlHttp.setRequestHeader("Content-type", "application/json");
	
	xmlHttp.send(userJSON);
}

function fncJsonPut(url, userJSON) {
	xmlHttp = createXMLHttpRequest();
	xmlHttp.open("PUT", url, true);
	xmlHttp.onreadystatechange = handleStateChange;
//	xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xmlHttp.setRequestHeader("Content-type", "application/json;charset=UTF-8");
//	xmlHttp.setRequestHeader("Content-type", "application/json");
	
	xmlHttp.send(userJSON);
}

function fncJsonDelete(url) {
	xmlHttp = createXMLHttpRequest();
	xmlHttp.open("DELETE", url, true);
	xmlHttp.onreadystatechange = handleStateChange;
//	xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xmlHttp.setRequestHeader("Content-type", "application/json;charset=UTF-8");
//	xmlHttp.setRequestHeader("Content-type", "application/json");
	
	xmlHttp.send();
}

function fncJsonRequest(url, method, userJSON) {
	xmlHttp = createXMLHttpRequest();
	xmlHttp.open(method, url, true);
	xmlHttp.onreadystatechange = handleStateChange;
//	xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xmlHttp.setRequestHeader("Content-type", "application/json;charset=UTF-8");
//	xmlHttp.setRequestHeader("Content-type", "application/json");
	
	xmlHttp.send(userJSON);
}

function fncJsonRequest2(method, url, async, userJSON, handleResponse) {
	xmlHttp = createXMLHttpRequest();
	xmlHttp.open(method, url, async);
	
	if(async == true || typeof async == 'undefined' || async == '') {
		xmlHttp.onreadystatechange = function() {
			if(xmlHttp.readyState == 4) {
				try { 
					var responseJson = eval("("+xmlHttp.responseText+")");
					var resultCode = responseJson.resultCode;
					var resultMessage = responseJson.resultMessage;
					var resultValue = responseJson.resultValue;
	
					if (handleResponse != null && typeof(handleResponse) == 'function') {
						handleResponse(resultCode, resultMessage, resultValue);
		    		} else {
						jsonResponse(resultCode, resultMessage, resultValue);
		    		}
				} catch(e) {
				}
			}
		};
	}
//	xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xmlHttp.setRequestHeader("Content-type", "application/json;charset=UTF-8");
//	xmlHttp.setRequestHeader("Content-type", "application/json");
	
	if(userJSON == null || typeof userJSON == 'undefined') {
		userJSON = '';
	}
	
	xmlHttp.send(userJSON);
	
	if(async == false) {
		return eval("("+xmlHttp.responseText+")");
	}
}

function jsonResponse(resultCode, resultMessage, resultValue) {
//	alert('jsonResponse');	
}

function handleStateChange(handleResponse) {
	if(xmlHttp.readyState == 4) {
		try { 
			var responseJson = eval("("+xmlHttp.responseText+")");
			var resultCode = responseJson.resultCode;
			var resultMessage = responseJson.resultMessage;
			var resultMap = responseJson.resultMap;
			
			if (handleResponse != null && typeof(handleResponse) == 'function') {
				jsonResponse = handleResponse;
    		}
			
			jsonResponse(resultCode, resultMessage, resultMap);
		} catch(e) {
		}
	}
}

function parseResults() {
//	var responseText = document.createTextNode(xmlHttp.responseText);
	try { 
		var responseJson = eval("("+xmlHttp.responseText+")");
		var resultCode = responseJson.resultCode;
		var resultMessage = responseJson.resultMessage;
		var resultMap = responseJson.resultMap;
		
		jsonResponse(resultCode, resultMessage, resultMap);
	} catch(e) {
//		alert("EXCEPTION:"+xmlHttp.responseText);
	}

}


function createXMLHttpRequest(){
	  // See http://en.wikipedia.org/wiki/XMLHttpRequest
	  // Provide the XMLHttpRequest class for IE 5.x-6.x:
	  if( typeof XMLHttpRequest == "undefined" ) XMLHttpRequest = function() {
	    try { return new ActiveXObject("Msxml2.XMLHTTP.6.0") } catch(e) {}
	    try { return new ActiveXObject("Msxml2.XMLHTTP.3.0") } catch(e) {}
	    try { return new ActiveXObject("Msxml2.XMLHTTP") } catch(e) {}
	    try { return new ActiveXObject("Microsoft.XMLHTTP") } catch(e) {}
	    throw new Error( "This browser does not support XMLHttpRequest." )
	  };
	  return new XMLHttpRequest();
}


function createXMLHttpRequest2() {
	var xmlRequest;
	
	if (window.ActiveXObject) {
		xmlRequest = new ActiveXObject("Microsoft.XMLHTTP");
	} else if (window.XMLHttpRequest) {
		xmlRequest = new XMLHttpRequest();
	}
	
	return xmlRequest;
}