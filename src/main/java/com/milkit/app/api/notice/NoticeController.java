package com.milkit.app.api.notice;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.milkit.app.api.AbstractApiController;
import com.milkit.app.common.response.GenericResponse;
import com.milkit.app.common.response.Grid;
import com.milkit.app.domain.notice.Notice;
import com.milkit.app.domain.notice.service.NoticeServiceImpl;
import com.milkit.app.domain.userinfo.UserInfo;


import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
@RequestMapping("/api/notice")
@Api(tags = "1. 공지사항", value = "NoticeController")
public class NoticeController extends AbstractApiController {

    @Autowired
    private NoticeServiceImpl noticeService;

    
    @GetMapping
    @ApiOperation(value = "공지사항 전체조회", notes = "공지사항 전체 목록을 조회한다.")
    public ResponseEntity<GenericResponse<Grid<Notice>>> noticeAll(
    		@ApiParam(value = "현재Page 번호", required = false, example = "1") @RequestParam(value="page", defaultValue="1", required=false) String page,
    		@ApiParam(value = "Page당 목록 수", required = false, example = "10") @RequestParam(value="rows", defaultValue="10", required=false) String limit,
    		@ApiParam(value = "조회 글목록의 useYN 여부(useYN='Y' 일 경우 useYN='Y'인 글목록 만, 그 외에는 전체)", required = false, example = "Y") @RequestParam(value="useYN", defaultValue="Y", required=false) String useYN
			) throws Exception {
    	
		return apiResponse(() -> {
	    	Pageable pageable = PageRequest.of(Integer.parseInt(page)-1, Integer.parseInt(limit));
	    	Page<Notice> pages = noticeService.selectAll(useYN, pageable);
	    	
	    	Grid<Notice> grid = new Grid<Notice>();
	    	grid.setRows(pages.getContent());
	    	grid.setPage(page);
	    	grid.setTotal(pages.getTotalPages());
	    	grid.setRecords(pages.getNumberOfElements());
			
			return grid;
		});
    }
    
    @GetMapping(value = "/{id}")
    @ApiOperation(value = "공지사항 조회", notes = "글번호로 공지사항 목록을 조회한다.")
    public ResponseEntity<GenericResponse<Notice>> notice(
    		@ApiParam(value = "조회할 글번호", required = true) @PathVariable(value="id", required=true) String id
			) throws Exception {
   	
    	return apiResponse(() -> {
    		return noticeService.select(Long.valueOf(id));
    	});
    }
    
    
	@PostMapping
	@ApiOperation(value = "공지사항 등록", notes = "신규 공지사항을 등록한다.")
    public ResponseEntity<GenericResponse<Object>> insert(
    		@ApiParam(value = "등록할 글번호", required = true) @RequestBody(required=true) final Notice notice,
			@AuthenticationPrincipal UserInfo userInfo
			) throws Exception {
    	
   		if(userInfo != null) {
   	        notice.initNotice(userInfo.getUsername());
       	}
    		
    	noticeService.insert(notice);
    	
    	return apiResponse(() -> null);
    }
    
    @PutMapping
    @ApiOperation(value = "공지사항 갱신", notes = "공지사항을 갱신한다.")
    public ResponseEntity<GenericResponse<Object>> update(
    		@ApiParam(value = "갱신할 공지사항 정보", required = true) @RequestBody(required=true) final Notice notice,
			@AuthenticationPrincipal UserInfo userInfo
			) throws Exception {
    	
    	if(userInfo != null) {
    		notice.setUpdUser(userInfo.getUsername());
    	}
    		
    	noticeService.update(notice);
    	
    	return apiResponse(() -> null);
    }
    
    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "공지사항 삭제", notes = "공지사항을 삭제한다.(삭제 시 useYN = 'N')")
    public ResponseEntity<GenericResponse<Object>> delete(
    		@ApiParam(value = "삭제할 글번호", required = true) @PathVariable(value="id", required=true) String id,
			@AuthenticationPrincipal UserInfo userInfo
			) throws Exception {
    	
   		String updUser = "UNAUTHORIZED";
   		if(userInfo != null) {
   			updUser = userInfo.getUsername();
       	}
    		
    	noticeService.disable(Long.valueOf(id), updUser);
    	
    	return apiResponse(() -> null);
    }
    
}