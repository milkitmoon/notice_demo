package com.milkit.app.domain.notice;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.web.multipart.MultipartFile;

public class ResourceDataForm {
	
	private Long noticeID;
	
	private MultipartFile uploadfile;

	
	public Long getNoticeID() {
		return noticeID;
	}

	public void setNoticeID(Long downloadCD) {
		this.noticeID = downloadCD;
	}

	public MultipartFile getUploadfile() {
		return uploadfile;
	}

	public void setUploadfile(MultipartFile uploadfile) {
		this.uploadfile = uploadfile;
	}

    @Override  
	public String toString() {
		return ToStringBuilder.reflectionToString(
				this, ToStringStyle.SHORT_PREFIX_STYLE
		);
    }
}
