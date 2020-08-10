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

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.milkit.app.common.ErrorCode;
import com.milkit.app.common.exception.ServiceException;
import com.milkit.app.common.exception.handler.RestResponseEntityExceptionHandler;
import com.milkit.app.common.response.GenericResponse;
import com.milkit.app.common.response.GridResponse;
import com.milkit.app.domain.notice.Notice;
import com.milkit.app.domain.notice.NoticeAttach;
import com.milkit.app.domain.notice.ResourceDataForm;
import com.milkit.app.domain.notice.service.NoticeAttachServiceImpl;
import com.milkit.app.domain.notice.service.NoticeServiceImpl;
import com.milkit.app.domain.userinfo.UserInfo;

import com.milkit.app.domain.userinfo.service.UserInfoServiceImpl;

import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/api/noticeattach")
@Slf4j
public class NoticeAttachController {

    @Autowired
    private NoticeAttachServiceImpl noticeAttachService;

    @GetMapping(value = "/{noticeID}")
    public @ResponseBody GridResponse<NoticeAttach> noticeattach(
			@RequestParam(value="page", defaultValue="1", required=false) String page,
			@RequestParam(value="rows", defaultValue="10", required=false) String limit,
			@PathVariable(value="noticeID") String noticeID,
			@RequestParam(value="useYN", defaultValue="Y", required=false) String useYN
			) throws Exception {

    	Pageable pageable = PageRequest.of(Integer.parseInt(page)-1, Integer.parseInt(limit));
    	Page<NoticeAttach> pages = noticeAttachService.selectAll(Long.valueOf(noticeID), useYN, pageable);

    	GridResponse<NoticeAttach> response = new GridResponse<NoticeAttach>();
    	response.setRows(pages.getContent());
		response.setPage(page);
		response.setTotal(pages.getTotalPages());
		response.setRecords(pages.getNumberOfElements());
    	
		return response;
    }
 
    
	@DeleteMapping(value = "/{id}")
    @SuppressWarnings("rawtypes")
	public @ResponseBody GenericResponse<?> delete(
    		@PathVariable(value="id") String id,
			@AuthenticationPrincipal UserInfo userInfo
			) throws Exception {
   		String updUser = userInfo.getUsername();
    		
    	noticeAttachService.disable(Long.valueOf(id), updUser);
    	
		return new GenericResponse();
    }
    
    
	@PostMapping(value = "/upload")
    public GenericResponse<NoticeAttach> uploadImage(
    		@ModelAttribute(value="uploadFileForm") ResourceDataForm resourceDataForm, 
    		@AuthenticationPrincipal UserInfo userInfo) throws Exception {
//    		BindingResult result, HttpServletRequest request, HttpServletResponse servletResponse) throws Exception {

		log.info("## resourceDataForm Request :\n"+resourceDataForm);

		String instUser = userInfo.getUsername();
		NoticeAttach resourceData = noticeAttachService.uploadResource(resourceDataForm, instUser);

		return new GenericResponse<NoticeAttach>(resourceData);
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