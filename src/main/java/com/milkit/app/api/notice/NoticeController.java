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
import com.milkit.app.common.exception.handler.RestResponseEntityExceptionHandler;
import com.milkit.app.common.response.GenericResponse;
import com.milkit.app.common.response.GridResponse;
import com.milkit.app.domain.notice.Notice;
import com.milkit.app.domain.notice.service.NoticeServiceImpl;
import com.milkit.app.domain.userinfo.UserInfo;

import com.milkit.app.domain.userinfo.service.UserInfoServiceImpl;

import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/api/notice")
@Slf4j
public class NoticeController {

    @Autowired
    private NoticeServiceImpl noticeService;

    @GetMapping
    public @ResponseBody GridResponse<Notice> noticeAll(
			@RequestParam(value="page", defaultValue="1", required=false) String page,
			@RequestParam(value="rows", defaultValue="10", required=false) String limit,
			@RequestParam(value="useYN", defaultValue="Y", required=false) String useYN
			) throws Exception {
    	Pageable pageable = PageRequest.of(Integer.parseInt(page)-1, Integer.parseInt(limit));
    	Page<Notice> pages = noticeService.selectAll(useYN, pageable);
    	
    	GridResponse<Notice> response = new GridResponse<Notice>();
    	response.setRows(pages.getContent());
		response.setPage(page);
		response.setTotal(pages.getTotalPages());
		response.setRecords(pages.getNumberOfElements());
    	
		return response;
    }
    
    @GetMapping(value = "/{id}")
    public @ResponseBody GenericResponse<Notice> notice(
    		@PathVariable(value="id") String id
			) throws Exception {
   	
    	Notice notice = noticeService.select(Long.valueOf(id));
    	
		return new GenericResponse<Notice>(notice);
    }
    
	@PostMapping
	@SuppressWarnings("rawtypes")
    public @ResponseBody GenericResponse<?> insert(
			@RequestBody final Notice notice,
			@AuthenticationPrincipal UserInfo userInfo
			) throws Exception {
    	
    	GenericResponse<Notice> response = new GenericResponse<Notice>();
    	
   		if(userInfo != null) {
   	        notice.initNotice(userInfo.getUsername());
       	}
    		
    	Long id = noticeService.insert(notice);
    	
		return new GenericResponse();
    }
    
    @PutMapping
    @SuppressWarnings("rawtypes")
    public @ResponseBody GenericResponse<?> update(
			@RequestBody final Notice notice,
			@AuthenticationPrincipal UserInfo userInfo
			) throws Exception {
    	
    	if(userInfo != null) {
    		notice.setUpdUser(userInfo.getUsername());
    	}
    		
    	Integer updateCnt = noticeService.update(notice);
    	
    	return new GenericResponse();
    }
    
    @DeleteMapping(value = "/{id}")
    @SuppressWarnings("rawtypes")
    public @ResponseBody GenericResponse<?> delete(
    		@PathVariable(value="id") String id,
			@AuthenticationPrincipal UserInfo userInfo
			) throws Exception {
    	
   		String updUser = userInfo.getUsername();
    		
    	noticeService.disable(Long.valueOf(id), updUser);
    	
    	return new GenericResponse();
    }
    
}