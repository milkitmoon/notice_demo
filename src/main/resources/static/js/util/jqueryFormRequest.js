

function fncFormRequest(formname, reqUrl, beforeSerialize, processSuccess, processError) {
	$(document).ready(function() { 

	    $(formname).ajaxForm({ 
	        url : reqUrl,
	        dataType:  'json', 
	        contentType : 'text/plain',
	        type: 'POST',
	        beforeSerialize : function($form, options) {
   				if (typeof beforeSerialize != 'undefined' && beforeSerialize != null && typeof(beforeSerialize) == 'function') {
   					beforeSerialize($form, options);
   	    		}
	    	},
//	        beforeSubmit : beforeSubmit,
	        success : function(result, statusText, xhr, $form) {
   				if (typeof processSuccess != 'undefined' && processSuccess != null && typeof(processSuccess) == 'function') {
   					processSuccess(result, statusText, xhr, $form);
   	    		}
	    	},
	        error : function(request, status, error) {
   				if (typeof processError != 'undefined' && processError != null && typeof(processError) == 'function') {
   					processError(request, status, error);
   	    		}
	    	}
	    }); 
	});
}

