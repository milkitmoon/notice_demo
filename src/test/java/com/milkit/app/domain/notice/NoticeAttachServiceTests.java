package com.milkit.app.domain.notice;


import static org.junit.jupiter.api.Assertions.*;

import java.util.List;


import com.milkit.app.domain.notice.service.NoticeAttachServiceImpl;

import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.beans.factory.annotation.Autowired;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootTest
@Slf4j
class NoticeAttachServiceTests {

	@Autowired
    private NoticeAttachServiceImpl noticeAttachServie;

	@BeforeEach
	public void init() throws Exception {
		noticeAttachServie.insert(new NoticeAttach(1l, "test1.jpg", "Y"));
		noticeAttachServie.insert(new NoticeAttach(1l, "test2.jpg", "Y"));
		noticeAttachServie.insert(new NoticeAttach(1l, "test3.jpg", "Y"));
	}


/**/
	@Test
	public void 전체조회_테스트() throws Exception {
		long noticeID = 1L;
		
		List<NoticeAttach> list = selectAll(noticeID, 0, 2);

		assertTrue(list.size() == 2);
	}

	@Test
	public void 등록_테스트() throws Exception {
		long id = noticeAttachServie.insert(new NoticeAttach(2l, "test2.jpg", "Y"));

		assertTrue(id > 0);
	}

	
	@Test
	public void 삭제_테스트() throws Exception {
		
		long id = noticeAttachServie.insert(new NoticeAttach(1L, "test9.jpg", "Y"));
		
		noticeAttachServie.disable(id, "test");
		
		List<NoticeAttach> list = selectAll(1L, 0, 10);
		
		log.debug("## size:"+list.size());
		
		assertTrue(list.size() == 3);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void 업로드_테스트() throws Exception {
		
        MockMultipartFile uploadfile = new MockMultipartFile("uploadfile", "foo.txt", MediaType.TEXT_PLAIN_VALUE, "Hello World".getBytes());
        ResourceDataForm resourceDataForm = new ResourceDataForm(1L, uploadfile);
        
		NoticeAttach noticeAttach = noticeAttachServie.uploadResource(resourceDataForm, "test");
		
		assertTrue(noticeAttach != null);
		assertEquals(noticeAttach.getNoticeID(), 1L);
		assertEquals(noticeAttach.getFilename(), "foo.txt");
	}
	
	
	
	private List<NoticeAttach> selectAll(Long noticeID, int page, int size) throws Exception {
		Pageable firstPageWithTwoElements = PageRequest.of(page, size);
		Page<NoticeAttach> pages = noticeAttachServie.selectAll(noticeID, "Y", firstPageWithTwoElements);

		return pages.getContent();
	}

}
