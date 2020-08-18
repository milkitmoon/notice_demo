package com.milkit.app.domain.notice.service;

import java.util.List;
import java.util.Optional;

import com.milkit.app.common.exception.ServiceException;
import com.milkit.app.common.exception.handler.ApiResponseEntityExceptionHandler;
import com.milkit.app.domain.notice.Notice;
import com.milkit.app.domain.notice.dao.NoticeDao;
import com.milkit.app.domain.notice.dao.NoticeSpec;
import com.milkit.app.domain.userinfo.UserInfo;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class NoticeServiceImpl {

    @Autowired
    private NoticeDao noticeRepository;

    public Page<Notice> selectAll(String useYN, Pageable pageable) throws Exception {
        return noticeRepository.findAll(NoticeSpec.withUseYN(useYN), pageable);
    }

    public Notice select(Long id) throws Exception {
        Notice notice = null;

        Optional<Notice> optionalPost = noticeRepository.findById(id);
        if(optionalPost.isPresent()) {
        	notice = optionalPost.get();
        }
        
		return notice;
    }
    
    public Long selectSeq() throws Exception {
        return noticeRepository.getSeqNextVal();
    }

    public Long insert(Notice notice) throws Exception {
        return noticeRepository.save(notice).getId();
    }
    
    public Integer update(Notice notice) throws Exception {
    	return noticeRepository.update(notice);
    }
    
    public Integer disable(Long id, String updUser) throws Exception {
    	return noticeRepository.disable(id, updUser);
    }

	public void increaseReadNum(Long id) throws Exception {
		noticeRepository.updateReadNum(id);
		
	}
    
}