

function fncJsonPost(url, userJSON, handleResponse) {
	fncJsonRequest(url, "POST", userJSON, handleResponse);
}

function fncJsonPut(url, userJSON, handleResponse) {
	fncJsonRequest(url, "PUT", userJSON, handleResponse);
}

function fncJsonDelete(url, handleResponse) {
	fncJsonRequest(url, "DELETE", null, handleResponse);
}

function fncJsonRequest(url, method, userJSON, handleResponse) {
	var xmlHttp = createXMLHttpRequest();
	xmlHttp.open(method, url, true);
	xmlHttp.onreadystatechange = function() {
		if(xmlHttp.readyState == 4) {
			try { 
				var responseJson = eval("("+xmlHttp.responseText+")");
				if (typeof handleResponse != 'undefined' && handleResponse != null && typeof(handleResponse) == 'function') {
					handleResponse(responseJson);
	    		}
			} catch(e) {
				alert("EXCEPTION:"+xmlHttp.responseText);
			}
		}
	};
	xmlHttp.setRequestHeader("Content-type", "application/json;charset=UTF-8");
	
	xmlHttp.send(userJSON);
}

function fncJsonRequest2(method, url, async, userJSON, handleResponse) {
	var xmlHttp = createXMLHttpRequest();
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
					alert("EXCEPTION:"+xmlHttp.responseText);
				}
			}
		};
	}
	xmlHttp.setRequestHeader("Content-type", "application/json;charset=UTF-8");
	
	if(userJSON == null || typeof userJSON == 'undefined') {
		userJSON = '';
	}
	xmlHttp.send(userJSON);
	
	if(async == false) {
		return eval("("+xmlHttp.responseText+")");
	}
}

function jsonResponse(resultCode, resultMessage, resultValue) {

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


function createXMLHttpRequest(){
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