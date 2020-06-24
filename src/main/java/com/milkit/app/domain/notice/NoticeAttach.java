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

import com.milkit.app.common.AttachInfo;


@Table(name = "NOTICE_ATTACH")
@Entity
public class NoticeAttach {
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
    @Column(name = "NOTICE_ID")
	private Long noticeID;
	
    @Column(name = "FILE_NAME")
	private String filename;
    @Column(name = "URL")
	private String url;
    @Column(name = "THUMB_URL")
	private String thumbUrl;
    @Column(name = "USE_YN")
	private String useYN;
	
    @Column(name = "INST_TIME")
	private Date instTime;
    @Column(name = "UPD_TIME")
	private Date updTime;
    @Column(name = "INST_USER")
	private String instUser;
    @Column(name = "UPD_USER")
	private String updUser;
    
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


	public NoticeAttach(String httpUrl, Long noticeID, AttachInfo attachInfo) {
		this();

		this.noticeID = noticeID;
		this.filename = attachInfo.getFilename();
		this.url = httpUrl+attachInfo.getResourceURL();
		this.thumbUrl = httpUrl+attachInfo.getThumbnaleURL();
		this.resourceType = attachInfo.getResourceType();
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
		return contentType.toLowerCase().matches("image/jpeg|image/png|image/gif") ? "1" : "9";
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
