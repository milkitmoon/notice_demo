package com.milkit.app.domain.notice;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class ResourceDataForm {
	
	@ApiModelProperty(value="글번호")
	private Long noticeID;
	@ApiModelProperty(value="첨부파일", notes="HTTP Multipart File")
	private MultipartFile uploadfile;

	public ResourceDataForm() {}
	
	public ResourceDataForm(Long noticeID, MultipartFile uploadfile) {
		this.noticeID = noticeID;
		this.uploadfile = uploadfile;
	}
	
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
