package com.milkit.app.domain.notice;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.milkit.app.common.JsonPrinter;
import com.milkit.app.common.exception.handler.ApiResponseEntityExceptionHandler;
import com.milkit.app.domain.notice.service.NoticeServiceImpl;
import com.milkit.app.util.PrintUtil;

import lombok.extern.slf4j.Slf4j;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootTest
@Slf4j
class NoticeServiceTests {

	@Autowired
    private NoticeServiceImpl noticeServie;


	@BeforeEach
	public void init() throws Exception {
		noticeServie.insert(new Notice(noticeServie.selectSeq(), "title1", "contents1", "kim"));
		noticeServie.insert(new Notice(noticeServie.selectSeq(), "title2", "contents2", "kim"));
		noticeServie.insert(new Notice(noticeServie.selectSeq(), "title3", "contents3", "kim"));
	}



	
	@Test
	public void 전체조회_테스트() throws Exception {
		List<Notice> list = selectAll(0, 2);
		
		log.debug("list3:"+list);

		assertTrue(list.size() == 2);

	}

	@Test
	public void 단일조회_테스트() throws Exception {
		Notice notice = noticeServie.select(1L);

		assertTrue(notice != null);
	}

	@Test
	public void 등록_테스트() throws Exception {
		Long id = noticeServie.insert(new Notice(noticeServie.selectSeq(), "title1", "contents1", "kim"));

		assertTrue(id > 0);
	}
	
	@Test
	public void 갱신_테스트() throws Exception {
		long id = 1L;
		
		noticeServie.update(new Notice(id, "title2", "contents2", "kim"));
		
		Notice notice = noticeServie.select(id);

		assertTrue(notice.getTitle().equals("title2"));
		assertTrue(notice.getContent().equals("contents2"));
	}
	
	@Test
	public void 삭제_테스트() throws Exception {
		noticeServie.disable(1L, "test");
		
		Notice notice = noticeServie.select(1L);

		assertTrue(notice.getUseYN().equals("N"));
	}

	
	@Test
	public void sequence조회_테스트() throws Exception {
		Long seq = noticeServie.selectSeq();

		assertTrue(seq > 0);

		log.debug("seq:"+seq);
	}

	

	
	private List<Notice> selectAll(int page, int size) throws Exception {
		Pageable firstPageWithTwoElements = PageRequest.of(page, size);
		Page<Notice> pages = noticeServie.selectAll("Y", firstPageWithTwoElements);

		return pages.getContent();
	}

}
