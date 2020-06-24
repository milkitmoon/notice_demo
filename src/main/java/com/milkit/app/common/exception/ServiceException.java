package com.milkit.app.common.exception;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.milkit.app.common.ErrorCode;


@SuppressWarnings("serial")
public class ServiceException extends Exception {
	/** 오류코드 */
	private int code = 900;
	
	private Object[] objs;
	

	/** log4j logger */
    
	private static final Logger logger  = LoggerFactory.getLogger(ServiceException.class);
	/**
	 * 생성자
	 */
	public ServiceException() {
		super();
//		doLog(); 
	}
	
	/**
	 * 생성자
	 * @param code 오류코드
	 */
	public ServiceException(int code) {
//		super( ErrorCode.getMessage(code) );
		super("서비스 오류입니다.");
		this.code = code;
	}
	
	public ServiceException(int code, Object[] objs) {
//		super( ErrorCode.getMessage(code) );
		super("서비스 오류입니다.");
		this.code = code;
		this.objs = objs;
	}
	
	/**
	 * 생성자
	 * @param message 오류메시지
	 */
	public ServiceException(String message) {
		super(message);
	}
	
    /**
     * 생성자
     * @param code 오류코드
     * @param message 오류메시지
     */
    public ServiceException(int code, String message) {
    	super(message);
        this.code = code;
    }
    
	public ServiceException(int code, String message, Object[] objs) {
		super( message );
		this.code = code;
		this.objs = objs;
	}
    
    /**
     * 생성자
     * @param code 오류코드
     * @param message 오류메시지
     * @param cause 오류 시 발생한 Exception
     */
    public ServiceException(int code, String message, Throwable cause) {
    	super(message, cause);
    	this.code = code;
    }
    
    /**
     * 생성자
     * @param cause 오류 시 발생한 Exception
     */
    public ServiceException(Throwable cause) {
    	super(cause.getMessage(), cause);
    }
    
    public ServiceException(Throwable cause, int code) {
//    	super(ErrorCode.getMessage(code), cause);
    	super(cause.getMessage(), cause);
		this.code = code;
    }
    
    public void setCode(int code) {
    	this.code = code;
    }
    
    /**
     * getCode 오류코드 얻어옴
     * @return 오류코드
     * @see 참고링크
     */
    public int getCode() {
		return this.code;
	}
    
	public Object[] getObjs() {
		return objs;
	}

	public void setObjs(Object[] objs) {
		this.objs = objs;
	}
    
    public String getMessage() {
/*    	
    	String errMessage = null;
    	if (getObjs() != null) {
       		errMessage = ErrorCode.getMessage(getCode(), getObjs());

    	} else {
    		String argMessage = super.getMessage();
    		if(argMessage != null && !argMessage.equals("")) {
    			errMessage = argMessage;
    		} else {
    			errMessage = ErrorCode.getMessage(getCode());
    		}
    	}

    	return errMessage;
*/
    	return super.getMessage();
    }
    
	/**
	 * getCodeString 오류코드 스트링형태로 얻어옴
	 * @return 스트링으로 변환된 오류코드
	 * @see 참고링크
	 */
	public String getCodeString() {
		return Integer.toString(this.code);
	}
	

	/**
	 * getCauseMessage Exception메시지가 포함된 메시지 얻어옴
	 * @return 오류 메시지
	 * @see 참고링크
	 */
	public String getCauseMessage() {
		String retMessage = "[" + this.getCode() + "]";
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(baos);
		super.printStackTrace(ps);
		
		retMessage += baos.toString().replaceAll("\n", " ");
		
		return retMessage;
	}
	
	/**
	 * doLog 오류 로그 남김
	 * @see 참고링크
	 */
	private void doLog() {
		logger.error("[" + this.getCode() + "] " + this.getCauseMessage());
	}

}
