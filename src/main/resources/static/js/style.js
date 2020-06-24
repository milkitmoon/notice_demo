

function disableElement(elementId) {
	// border 왼쪽 오른쪽 1px씩 더해줌....
	var orgWidth =  $(elementId).width() + 2;
	
	if( $(elementId).is(':text') ) {
		$(elementId).attr("readOnly", true);
//		$(elementId).attr("disabled", true);
		$(elementId).attr('style', " color:black;border:none;border-right:0px; border-top:0px; boder-left:0px; boder-bottom:0px; width: " + orgWidth + "px; ");
	} else {
		$(elementId).attr("disabled", true);
	}
}


function enableElement(elementId) {
	if( $(elementId).is(':text') ) {
		$(elementId).attr("readOnly", false);
		$(elementId).attr('style', " color:black;border:solid;border-style:1px;");
	} else {
		$(elementId).attr("disabled", false);
	}
}