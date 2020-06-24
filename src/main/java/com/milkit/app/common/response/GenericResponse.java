package com.milkit.app.common.response;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.milkit.app.common.ErrorCode;




public class GenericResponse<T> implements Serializable {

	private String resultCode;
	private String resultMessage;
	
	private T resultValue;
	
	
	public GenericResponse() {
		this(Integer.toString(ErrorCode.OK), "성공했습니다");
	}

	public GenericResponse(int resultCode, String resultMessage) {
		this(Integer.toString(resultCode), resultMessage);
	}
	
	public GenericResponse(String resultCode, String resultMessage) {
		this.resultCode = resultCode;
		this.resultMessage = resultMessage;
	}
	
	
	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	
	public String getResultMessage() {
		return resultMessage;
	}

	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}

	public T getResultValue() {
		return resultValue;
	}

	public void setResultValue(T resultValue) {
		this.resultValue = (T) resultValue;
	}

}
