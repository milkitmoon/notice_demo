package com.milkit.app.domain.notice;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@Table(name = "NOTICE_ATTACH")
@Entity
@ApiModel
public class NoticeAttach {
	
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @ApiModelProperty(value="첨부파일번호")
	private Long id;
	
    @Column(name = "NOTICE_ID")
    @ApiModelProperty(value="글번호")
	private Long noticeID;
	
    @Column(name = "FILE_NAME")
    @ApiModelProperty(value="첨부파일명")
	private String filename;
    
    @Column(name = "URL")
    @ApiModelProperty(value="첨부파일 url")
	private String url;
    
    @Column(name = "THUMB_URL")
    @ApiModelProperty(value="첨부파일 Thumbnail url")
	private String thumbUrl;
    
    @Column(name = "USE_YN")
    @ApiModelProperty(value="첨부파일삭제여부")
	private String useYN;
	
    @Column(name = "INST_TIME")
    @ApiModelProperty(value="등록시간")
	private Date instTime;
    
    @Column(name = "UPD_TIME")
    @ApiModelProperty(value="갱신시간")
	private Date updTime;
    
    @Column(name = "INST_USER")
    @ApiModelProperty(value="첨부파일 등록자")
	private String instUser;
    
    @Column(name = "UPD_USER")
    @ApiModelProperty(value="첨부파일 갱신자")
	private String updUser;
    
    @ApiModelProperty(value="첨부파일 타입", notes="형식 (1:image, 9:etc)")
    private String resourceType;

	
	public NoticeAttach() {
		this.id = -1l;
//		this.resourceType = ResourceTypeEnum.IMAGE.getValue();
		this.useYN = "Y";
	}
	
	public NoticeAttach(Long id) {
		this.id = id;
	}
	
	public NoticeAttach(Long noticeID, String filename, String useYN) {
		this();
		
		this.noticeID = noticeID;
		this.filename = filename;
		this.useYN = useYN;
	}

	public NoticeAttach(Long noticeID, String filename, String url, String thumbUrl, String contentType, String instUser) {
		this.noticeID = noticeID;
		this.filename = filename;
		this.url = url;
		this.thumbUrl = thumbUrl;
		
		this.resourceType = convertContentType(contentType);
		this.useYN = "Y";
		
		this.instTime = new Date();
		this.updTime = new Date();
		this.instUser = instUser;
		this.updUser = instUser;
	}

    private String convertContentType(String contentType) {
		return contentType.toLowerCase().matches("image/jpeg|image/png|image/gif") ? ResourceTypeEnum.IMAGE.getValue() : ResourceTypeEnum.ETC.getValue();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getNoticeID() {
		return noticeID;
	}

	public void setNoticeID(Long noticeID) {
		this.noticeID = noticeID;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getThumbUrl() {
		return thumbUrl;
	}

	public void setThumbUrl(String thumbUrl) {
		this.thumbUrl = thumbUrl;
	}

	public String getUseYN() {
		return useYN;
	}

	public void setUseYN(String useYN) {
		this.useYN = useYN;
	}

	public Date getInstTime() {
		return instTime;
	}

	public void setInstTime(Date instTime) {
		this.instTime = instTime;
	}

	public Date getUpdTime() {
		return updTime;
	}

	public void setUpdTime(Date updTime) {
		this.updTime = updTime;
	}

	public String getInstUser() {
		return instUser;
	}

	public void setInstUser(String instUser) {
		this.instUser = instUser;
	}

	public String getUPD_USER() {
		return updUser;
	}

	public void setUpdUser(String updUser) {
		this.updUser = updUser;
	}

	public String getResourceType() {
		return resourceType;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	@Override  
	public String toString() {
		return ToStringBuilder.reflectionToString(
				this, ToStringStyle.SHORT_PREFIX_STYLE
		);
    }

}
