

function fncFormRequest(formname, reqUrl) {
	$(document).ready(function() { 
	    // bind form using ajaxForm 
	    $(formname).ajaxForm({ 
	        url : reqUrl,
	        dataType:  'json', 
	        contentType : 'text/plain',
	        type: 'POST',
	        beforeSerialize : beforeSerialize,
//	        beforeSubmit : beforeSubmit,
	        success:   processSuccess,
	        error : processError
	    }); 
	});
}

