package com.milkit.app.domain.notice;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import com.milkit.app.api.notice.NoticeController;
import com.milkit.app.common.response.GridResponse;
import com.milkit.app.config.WebSecurityConfigure;
import com.milkit.app.domain.notice.service.NoticeServiceImpl;
import com.milkit.app.domain.userinfo.service.UserInfoServiceImpl;

import lombok.extern.slf4j.Slf4j;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.annotation.RequestParam;

@WebMvcTest(NoticeController.class) 
@Import(WebSecurityConfigure.class)
@Slf4j
public class NoticeControllerTest {

    @Autowired
    private MockMvc mvc;
    
    @MockBean
    private NoticeServiceImpl noticeService;
    
    @MockBean
    private UserInfoServiceImpl userInfoServie;
    
    
    @Test
    @WithMockUser(username = "test", roles = {"MEMBER"})
    public void noticeAll_테스트() throws Exception {
        //given
    	Pageable pageable = PageRequest.of(0, 10);
        List<Notice> notices = Arrays.asList(new Notice(1L, "제목", "내용", "test"), new Notice(2L, "제목2", "내용2", "test"));
        Page<Notice> pages = new PageImpl<>(notices, pageable, notices.size());
        given(noticeService.selectAll("Y", pageable)).willReturn(pages);

        //when
        final ResultActions actions = mvc.perform(get("/api/notice", "1", "10", "Y")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());

        //then
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("total").value("1"))
                .andExpect(jsonPath("records").value("2"))
                .andExpect(jsonPath("rows[0].id").value(1L))
                .andExpect(jsonPath("rows[0].title").value("제목"))
                .andExpect(jsonPath("rows[0].content").value("내용"))
                .andExpect(jsonPath("rows[0].instUser").value("test"))
                .andExpect(jsonPath("rows[1].id").value(2L))
                .andExpect(jsonPath("rows[1].title").value("제목2"))
                .andExpect(jsonPath("rows[1].content").value("내용2"))
                .andExpect(jsonPath("rows[1].instUser").value("test"))
                ;
    }
    
    
    @Test
    @WithMockUser(username = "test", roles = {"MEMBER"})
    public void notice_테스트() throws Exception {
        //given
        final Notice notice = new Notice(1L, "제목", "내용", "test");
        given(noticeService.select(1L)).willReturn(notice);

        //when
        final ResultActions actions = mvc.perform(get("/api/notice/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());

        //then
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("resultValue.id").value(1L))
                .andExpect(jsonPath("resultValue.title").value("제목"))
                .andExpect(jsonPath("resultValue.content").value("내용"))
                .andExpect(jsonPath("resultValue.instUser").value("test"));
    }
    
    
    @Test
    @WithMockUser(username = "test", roles = {"MEMBER"})
    public void insert_테스트() throws Exception {
        //given
        final Notice notice = new Notice(1L, "제목", "내용", "test");
        given(noticeService.insert(notice)).willReturn(1L);

        //when
        RequestBuilder request = MockMvcRequestBuilders
                .post("/api/notice")
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"id\":1,\"title\":\"제목\",\"content\":\"내용\",\"instUser\":\"test\"}")
                .contentType(MediaType.APPLICATION_JSON);
        
        final ResultActions actions = mvc.perform(request)
                .andDo(print());

        //then
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("resultCode").value("0"))
                .andExpect(jsonPath("resultMessage").value("성공했습니다"));
                
    }
    
    @Test
    @WithMockUser(username = "test", roles = {"MEMBER"})
    public void update_테스트() throws Exception {
        //given
        final Notice notice = new Notice(1L, "제목2", "내용2", "test");
        given(noticeService.update(notice)).willReturn(1);

        //when
        RequestBuilder request = MockMvcRequestBuilders
                .put("/api/notice")
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"id\":1,\"title\":\"제목2\",\"content\":\"내용2\",\"instUser\":\"test\"}")
                .contentType(MediaType.APPLICATION_JSON);
        
        final ResultActions actions = mvc.perform(request)
                .andDo(print());

        //then
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("resultCode").value("0"))
                .andExpect(jsonPath("resultMessage").value("성공했습니다"));
                
    }
    
    @Test
    @WithMockUser(username = "test", roles = {"MEMBER"})
    public void delete_테스트() throws Exception {
        //given
        given(noticeService.disable(1L, "test")).willReturn(1);

        //when
        final ResultActions actions = mvc.perform(delete("/api/notice/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());

        //then
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("resultCode").value("0"))
                .andExpect(jsonPath("resultMessage").value("성공했습니다"));
                
    }
    
    
}
