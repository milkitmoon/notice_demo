package com.milkit.app.common.response;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.milkit.app.common.ErrorCode;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@ApiModel
public class GenericResponse<T> implements Serializable {

	@ApiModelProperty(value="결과코드", notes="형식 (0:성공, others:실패)")
	private String resultCode;
	@ApiModelProperty(value="결과메시지")
	private String resultMessage;
	@ApiModelProperty(value="결과값", notes="형식 (template으로 정의된 값)")
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
	
	public GenericResponse(T t) {
		this();
		this.resultValue = t;
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
	

    public static GenericResponse<?> success() {
        return new GenericResponse();
    }

}
