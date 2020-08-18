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

import com.milkit.app.util.DateUtil;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@Table(name = "NOTICE_BULLETIN")
@Entity
@ApiModel
public class Notice {
 

    @Id
    @ApiModelProperty(value="글번호")
    private Long id;

    @Column(name = "TITLE")
    @ApiModelProperty(value="글제목")
    private String title;

    @Column(name = "CONTENT")
    @ApiModelProperty(value="글내용")
    private String content;

    @Column(name = "READ_NUM")
    @ApiModelProperty(value="글조회수")
    private int readNum;

    @Column(name = "USER_IP")
    @ApiModelProperty(value="작성자IP")
    private String userIP;

    @Column(name = "USE_YN")
    @ApiModelProperty(value="글삭제여부")
    private String useYN;

    @Column(name = "INST_TIME")
    @ApiModelProperty(value="등록시간")
    private Date instTime;

    @Column(name = "UPD_TIME")
    @ApiModelProperty(value="갱신시간")
    private Date updTime;

    @Column(name = "INST_USER")
    @ApiModelProperty(value="글작성자")
    private String instUser;

    @Column(name = "UPD_USER")
    @ApiModelProperty(value="글수정자")
    private String updUser;


    public Notice() {
        this.instTime = new Date();
        this.updTime = new Date();
        this.useYN = "Y";
    }
    public Notice(Long id, String title, String content, String instUser) {
    	this();
    	this.id = id;
        this.title = title;
        this.content = content;
        this.instUser = instUser;
        this.updUser = instUser;
    }

    public void initNotice(String instUser) {
        this.instUser = instUser;
        this.updUser = instUser;
//        this.instTime = new Date();
//        this.updTime = new Date();
//        this.useYN = "Y";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getReadNum() {
        return readNum;
    }

    public void setReadNum(int readNum) {
        this.readNum = readNum;
    }

    public String getUserIP() {
        return userIP;
    }

    public void setUserIP(String userIP) {
        this.userIP = userIP;
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

    public String getUpdUser() {
        return updUser;
    }

    public void setUpdUser(String updUser) {
        this.updUser = updUser;
    }
    
    @ApiModelProperty(value="글작성시간", notes="형식 (yyyy-MM-dd HH:mm:ss)")
    public String getFormInstTime() {
    	String formInstDay = "";
    	try {
    		formInstDay = DateUtil.parseDateString(instTime, "yyyy-MM-dd HH:mm:ss");
		} catch (Exception e) {}
    	
    	return formInstDay;
    }
    @ApiModelProperty(value="글수정시간", notes="형식 (yyyy-MM-dd HH:mm:ss)")
    public String getFormUpdTime() {
    	String formInstDay = "";
    	try {
    		formInstDay = DateUtil.parseDateString(updTime, "yyyy-MM-dd HH:mm:ss");
		} catch (Exception e) {}
    	
    	return formInstDay;
    }
    

    
    @Override  
	public String toString() {
		return ToStringBuilder.reflectionToString(
				this, ToStringStyle.SHORT_PREFIX_STYLE
		);
    }
    
}