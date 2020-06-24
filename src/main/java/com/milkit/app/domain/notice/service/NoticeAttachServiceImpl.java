package com.milkit.app.domain.notice.service;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import com.milkit.app.common.AppCommon;
import com.milkit.app.common.AttachInfo;
import com.milkit.app.common.file.FileSystemStorageServiceImpl;
import com.milkit.app.common.file.FileUploadProperties;
import com.milkit.app.domain.notice.Notice;
import com.milkit.app.domain.notice.NoticeAttach;
import com.milkit.app.domain.notice.ResourceDataForm;
import com.milkit.app.domain.notice.dao.NoticeAttachDao;
import com.milkit.app.domain.notice.dao.NoticeAttachSpec;
import com.milkit.app.domain.notice.dao.NoticeDao;
import com.milkit.app.domain.notice.dao.NoticeSpec;
import com.milkit.app.domain.userinfo.UserInfo;
import com.milkit.app.view.notice.NoticeViewController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Component
public class NoticeAttachServiceImpl {
	
	private static final Logger logger  = LoggerFactory.getLogger(NoticeAttachServiceImpl.class);

    @Autowired
    private NoticeAttachDao noticeRepository;
	
	@Autowired
	private FileSystemStorageServiceImpl fileSystemStorageService;
	
	private FileUploadProperties properties;
	
	@Autowired
    public NoticeAttachServiceImpl(FileUploadProperties properties) throws Exception {
		this.properties = properties;
	}

    public Page<NoticeAttach> selectAll(Long noticeID, String useYN, Pageable pageable) throws Exception {
        return noticeRepository.findAll(NoticeAttachSpec.withNoticeID(noticeID).and(NoticeAttachSpec.withUseYN(useYN)), pageable);
    }

    
    public Integer disable(Long id, String updUser) throws Exception {
    	return noticeRepository.disable(id, updUser);
    }
    
	public Long insert(NoticeAttach noticeAttach) throws Exception {
		
		return noticeRepository.save(noticeAttach).getId();
	}
 

	public NoticeAttach uploadResource(ResourceDataForm resourceDataForm, String instUser) throws Exception {
		MultipartFile file = resourceDataForm.getUploadfile();
		
        String fileName = fileSystemStorageService.store(file);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                                .path(properties.getUrl())
                                .path(fileName)
                                .toUriString();
        String thumbFileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(properties.getUrl())
                .path(properties.getThumnailPrefix()+fileName)
                .toUriString();

        NoticeAttach noticeAttach = new NoticeAttach(resourceDataForm.getNoticeID(), fileName, fileDownloadUri, thumbFileDownloadUri, file.getContentType(), instUser);
        insert(noticeAttach);
        
        return noticeAttach;        

	}
	
	public Resource downloadResource(String filename) throws Exception {
		return fileSystemStorageService.loadAsResource(filename);
	}
    
}