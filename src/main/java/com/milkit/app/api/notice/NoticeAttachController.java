package com.milkit.app.api.notice;

import java.util.List;
import java.io.Writer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.milkit.app.api.AbstractApiController;
import com.milkit.app.common.ErrorCode;
import com.milkit.app.common.exception.ServiceException;
import com.milkit.app.common.exception.handler.ApiResponseEntityExceptionHandler;
import com.milkit.app.common.response.GenericResponse;
import com.milkit.app.common.response.Grid;
import com.milkit.app.domain.notice.Notice;
import com.milkit.app.domain.notice.NoticeAttach;
import com.milkit.app.domain.notice.ResourceDataForm;
import com.milkit.app.domain.notice.service.NoticeAttachServiceImpl;
import com.milkit.app.domain.notice.service.NoticeServiceImpl;
import com.milkit.app.domain.userinfo.UserInfo;

import com.milkit.app.domain.userinfo.service.UserInfoServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
@RequestMapping("/api/noticeattach")
@Api(tags = "2. 공지사항 첨부파일", value = "NoticeAttachController")
public class NoticeAttachController extends AbstractApiController {

    @Autowired
    private NoticeAttachServiceImpl noticeAttachService;

    @GetMapping(value = "/{noticeID}")
    @ApiOperation(value = "공지글 첨부파일 전체조회", notes = "공지글 첨부파일 전체 목록을 조회한다.")
    public ResponseEntity<GenericResponse<Grid<NoticeAttach>>> noticeattach(
    		@ApiParam(value = "현재Page 번호", required = false, example = "1") @RequestParam(value="page", defaultValue="1", required=false) String page,
    		@ApiParam(value = "Page당 목록 수", required = false, example = "10") @RequestParam(value="rows", defaultValue="10", required=false) String limit,
    		@ApiParam(value = "첨부파일을 조회할 공지글번호(noticeID)", required = true) @PathVariable(value="noticeID", required=true) String noticeID,
			@ApiParam(value = "조회 글목록의 useYN 여부(useYN='Y' 일 경우 useYN='Y'인 글목록 만, 그 외에는 전체)", required = false, example = "Y") @RequestParam(value="useYN", defaultValue="Y", required=false) String useYN
			) throws Exception {
    	
		return apiResponse(() -> {
	    	Pageable pageable = PageRequest.of(Integer.parseInt(page)-1, Integer.parseInt(limit));
	    	Page<NoticeAttach> pages = noticeAttachService.selectAll(Long.valueOf(noticeID), useYN, pageable);

	    	Grid<NoticeAttach> grid = new Grid<NoticeAttach>();
	    	grid.setRows(pages.getContent());
	    	grid.setPage(page);
	    	grid.setTotal(pages.getTotalPages());
	    	grid.setRecords(pages.getNumberOfElements());
			
log.debug("\n\n\n\n"+grid.toString()+"\n\n\n");
	    	
			return grid;
		});
    }
 
    
	@DeleteMapping(value = "/{id}")
	@ApiOperation(value = "첨부파일 삭제", notes = "첨부파일을 삭제한다.(삭제 시 useYN = 'N')")
	public ResponseEntity<GenericResponse<Object>> delete(
			@ApiParam(value = "삭제할 첨부파일번호", required = true) @PathVariable(value="id", required=true) String id,
			@AuthenticationPrincipal UserInfo userInfo
			) throws Exception {
   		String updUser = "UNAUTHORIZED";
   		if(userInfo != null) {
   			updUser = userInfo.getUsername();
       	}
    		
    	noticeAttachService.disable(Long.valueOf(id), updUser);
    	
    	return apiResponse(() -> GenericResponse.success());
    }
    
	@PostMapping(value = "/upload")
	@ApiOperation(value = "첨부파일 등록", notes = "신규 첨부파일을 등록한다. (MultipartFile 형식 등록)")
    public ResponseEntity<GenericResponse<NoticeAttach>> uploadImage(
    		@ApiParam(value = "등록할 첨부파일 정보", required = true) @ModelAttribute(value="uploadFileForm") ResourceDataForm resourceDataForm, 
    		@AuthenticationPrincipal UserInfo userInfo) throws Exception {
//    		BindingResult result, HttpServletRequest request, HttpServletResponse servletResponse) throws Exception {

		log.info("## resourceDataForm Request :\n"+resourceDataForm);

   		String instUser = "UNAUTHORIZED";
   		if(userInfo != null) {
   			instUser = userInfo.getUsername();
       	}
		NoticeAttach resourceData = noticeAttachService.uploadResource(resourceDataForm, instUser);

		return apiResponse(() -> resourceData);
    }

	
/*	
	@GetMapping("/uploads/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> downloadFile(@PathVariable String filename) throws Exception {

        Resource resource = noticeAttachService.downloadResource(filename);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
*/
	
}