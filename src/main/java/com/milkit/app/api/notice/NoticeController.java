package com.milkit.app.api.notice;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.milkit.app.common.ErrorCode;
import com.milkit.app.common.exception.ServiceException;
import com.milkit.app.common.response.GenericResponse;
import com.milkit.app.common.response.GridResponse;
import com.milkit.app.domain.notice.Notice;
import com.milkit.app.domain.notice.service.NoticeServiceImpl;
import com.milkit.app.domain.userinfo.UserInfo;

import com.milkit.app.domain.userinfo.service.UserInfoServiceImpl;


@RestController
@RequestMapping("/api/notice")
public class NoticeController {
	
	private static final Logger logger  = LoggerFactory.getLogger(NoticeController.class);

    @Autowired
    private NoticeServiceImpl noticeService;

    @GetMapping
    public @ResponseBody GridResponse<Notice> noticeAll(
			@RequestParam(value="page", defaultValue="1", required=false) String page,
			@RequestParam(value="rows", defaultValue="10", required=false) String limit,
			@RequestParam(value="useYN", defaultValue="Y", required=false) String useYN
			) throws Exception {
    	Pageable pageable = PageRequest.of(Integer.parseInt(page)-1, Integer.parseInt(limit));
    	
    	GridResponse<Notice> response = new GridResponse<Notice>();
    	
    	try {
	    	Page<Notice> pages = noticeService.selectAll(useYN, pageable);
	    	
	    	response.setRows(pages.getContent());
			response.setPage(page);
			response.setTotal(pages.getTotalPages());
			response.setRecords(pages.getNumberOfElements());
    	} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			
			response.setResultCode( Integer.toString(e.getCode()) );
			response.setResultMessage( e.getMessage() );
		} catch (Throwable th) {
			logger.error(th.getMessage(), th);
			
			response.setResultCode( Integer.toString(ErrorCode.SystemError) );
			response.setResultMessage( "시스템 오류입니다." );
		}
    	
		return response;
    }
    
    @GetMapping(value = "/{id}")
    public @ResponseBody GenericResponse<Notice> notice(
    		@PathVariable(value="id") String id
			) throws Exception {
    	GenericResponse<Notice> response = new GenericResponse<Notice>();
    	
    	try {
	    	Notice notice = noticeService.select(Long.valueOf(id));
	    	response.setResultValue(notice);
	    	
    	} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			
			response.setResultCode( Integer.toString(e.getCode()) );
			response.setResultMessage( e.getMessage() );
		} catch (Throwable th) {
			logger.error(th.getMessage(), th);
			
			response.setResultCode( Integer.toString(ErrorCode.SystemError) );
			response.setResultMessage( "시스템 오류입니다." );
		}
    	
		return response;
    }
    
    @PostMapping
    public @ResponseBody GenericResponse<Notice> insert(
			@RequestBody final Notice notice,
			@AuthenticationPrincipal UserInfo userInfo
			) throws Exception {
    	
    	GenericResponse<Notice> response = new GenericResponse<Notice>();
    	
//logger.debug(notice.toString());
    	
    	try {
    		if(userInfo != null) {
    	        notice.initNotice(userInfo.getUsername());
        	}
    		
//notice.initNotice("test");
    		
	    	Long id = noticeService.insert(notice);
	    	
    	} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			
			response.setResultCode( Integer.toString(e.getCode()) );
			response.setResultMessage( e.getMessage() );
		} catch (Throwable th) {
			logger.error(th.getMessage(), th);
			
			response.setResultCode( Integer.toString(ErrorCode.SystemError) );
			response.setResultMessage( "시스템 오류입니다." );
		}
    	
		return response;
    }
    
    @PutMapping
    public @ResponseBody GenericResponse<Notice> update(
			@RequestBody final Notice notice,
			@AuthenticationPrincipal UserInfo userInfo
			) throws Exception {
    	
    	GenericResponse<Notice> response = new GenericResponse<Notice>();
    	
//logger.debug(notice.toString());
    	
    	try {
    		if(userInfo != null) {
    	        notice.setUpdUser(userInfo.getUsername());
        	}
    		
//notice.initNotice("test");
    		
	    	Integer updateCnt = noticeService.update(notice);
	    	
    	} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			
			response.setResultCode( Integer.toString(e.getCode()) );
			response.setResultMessage( e.getMessage() );
		} catch (Throwable th) {
			logger.error(th.getMessage(), th);
			
			response.setResultCode( Integer.toString(ErrorCode.SystemError) );
			response.setResultMessage( "시스템 오류입니다." );
		}
    	
		return response;
    }
    
    @DeleteMapping(value = "/{id}")
    public @ResponseBody GenericResponse<?> delete(
    		@PathVariable(value="id") String id,
			@AuthenticationPrincipal UserInfo userInfo
			) throws Exception {
    	
    	GenericResponse<Notice> response = new GenericResponse<Notice>();
    	
    	try {
    		String updUser = "test";
    		if(userInfo != null) {
    	        updUser = userInfo.getUsername();
        	}
    		
	    	noticeService.disable(Long.valueOf(id), updUser);
	    	
    	} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			
			response.setResultCode( Integer.toString(e.getCode()) );
			response.setResultMessage( e.getMessage() );
		} catch (Throwable th) {
			logger.error(th.getMessage(), th);
			
			response.setResultCode( Integer.toString(ErrorCode.SystemError) );
			response.setResultMessage( "시스템 오류입니다." );
		}
    	
		return response;
    }
    
}