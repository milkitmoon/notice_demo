package com.milkit.app.domain.notice;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import com.milkit.app.api.notice.NoticeAttachController;
import com.milkit.app.api.notice.NoticeController;
import com.milkit.app.common.response.GenericResponse;
import com.milkit.app.config.WebSecurityConfigure;
import com.milkit.app.domain.notice.service.NoticeAttachServiceImpl;
import com.milkit.app.domain.notice.service.NoticeServiceImpl;
import com.milkit.app.domain.userinfo.UserInfo;
import com.milkit.app.domain.userinfo.service.UserInfoServiceImpl;

import lombok.extern.slf4j.Slf4j;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.multipart.MultipartFile;

@WebMvcTest(NoticeAttachController.class) 
@Import(WebSecurityConfigure.class)
@Slf4j
public class NoticeAttachControllerTest {

    @Autowired
    private MockMvc mvc;
    
    @MockBean
    private NoticeAttachServiceImpl noticeAttachService;
    
    @MockBean
    private UserInfoServiceImpl userInfoServie;
    
    
    @Test
    @WithMockUser(username = "test", roles = {"MEMBER"})
    public void noticeattach_테스트() throws Exception {
        //given
    	Pageable pageable = PageRequest.of(0, 10);
        List<NoticeAttach> noticeAttachs = Arrays.asList(
        		new NoticeAttach(1L, "test.jpg", "/upload/test.jpg", "/upload/tmb_test.jpg", "image/jpeg", "test"), 
        		new NoticeAttach(1L, "test2.jpg", "/upload/test2.jpg", "/upload/tmb_test2.jpg", "image/jpeg", "test"));
        Page<NoticeAttach> pages = new PageImpl<>(noticeAttachs, pageable, noticeAttachs.size());
        given(noticeAttachService.selectAll(1L, "Y", pageable)).willReturn(pages);

        //when
        final ResultActions actions = mvc.perform(get("/api/noticeattach/{noticeID}", "1", "10", "1", "Y")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());

        //then
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("value.total").value("1"))
                .andExpect(jsonPath("value.records").value("2"))
                .andExpect(jsonPath("value.rows[0].noticeID").value(1L))
                .andExpect(jsonPath("value.rows[0].filename").value("test.jpg"))
                .andExpect(jsonPath("value.rows[0].url").value("/upload/test.jpg"))
                .andExpect(jsonPath("value.rows[0].thumbUrl").value("/upload/tmb_test.jpg"))
                .andExpect(jsonPath("value.rows[0].resourceType").value("1"))
                .andExpect(jsonPath("value.rows[0].instUser").value("test"))
                .andExpect(jsonPath("value.rows[1].noticeID").value(1L))
                .andExpect(jsonPath("value.rows[1].filename").value("test2.jpg"))
                .andExpect(jsonPath("value.rows[1].url").value("/upload/test2.jpg"))
                .andExpect(jsonPath("value.rows[1].thumbUrl").value("/upload/tmb_test2.jpg"))
                .andExpect(jsonPath("value.rows[1].resourceType").value("1"))
                .andExpect(jsonPath("value.rows[1].instUser").value("test"))
                ;
    }
    
    @Test
    @WithMockUser(username = "test", roles = {"MEMBER"})
    public void delete_테스트() throws Exception {
        //given
        given(noticeAttachService.disable(1L, "test")).willReturn(1);

        //when
        final ResultActions actions = mvc.perform(delete("/api/noticeattach/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());

        //then
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("code").value("0"))
                .andExpect(jsonPath("message").value("성공했습니다"));
                
    }
    
/**/
    @Test
    @WithMockUser(username = "test", roles = {"MEMBER"})
    public void uploadImage_테스트() throws Exception {
        //given
        MockMultipartFile uploadfile = new MockMultipartFile("uploadfile", "foo.txt", MediaType.TEXT_PLAIN_VALUE, "Hello World".getBytes());
        
        ResourceDataForm resourceDataForm = new ResourceDataForm(1L, uploadfile);
        final NoticeAttach noticeAttach = new NoticeAttach(1L, "foo.txt", "http://localhost/uploads/foo.txt", "http://localhost/uploads/tmb_foo.txt", "text/txt", "test");
        given(noticeAttachService.uploadResource(resourceDataForm, "test")).willReturn(noticeAttach);
        
    	//when
        final ResultActions actions = mvc.perform(MockMvcRequestBuilders
              .multipart("/api/noticeattach/upload")
              .file(uploadfile)
              .accept(MediaType.APPLICATION_JSON)
              .contentType(MediaType.MULTIPART_FORM_DATA)
              .param("noticeID", "1"))
    		  .andDo(print());
        
        //then
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("code").value("0"))
                .andExpect(jsonPath("message").value("성공했습니다"))
                ;
                
    }
    
}
