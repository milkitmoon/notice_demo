package com.milkit.app.common;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
//import com.spcnetworks.core.marshaller.MarshallingTest.InfoHashMap;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import io.swagger.annotations.ApiModelProperty;


public class Grid {

	@ApiModelProperty(value="현재Page 번호", notes="기본값 (1)")
	private String page = "1";
	@ApiModelProperty(value="전체Page 갯수")
	private String total = "0";
	@ApiModelProperty(value="전체 record 갯수")
	private String records = "0";
	@ApiModelProperty(value="record의 결과값", notes="형식 (List)")
	protected List rows;
	
	public Grid() {
		this.page = "1";
		this.total = "0";
		this.records = "0";
		this.rows = new ArrayList();
	}
	
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String getRecords() {
		return records;
	}
	public void setRecords(String records) {
		this.records = records;
	}
	
	public List getRows() {
		return rows;
	}
	public void setRows(List rows) {
		this.rows = rows;
	}
	
    @Override  
	public String toString() {
		return ToStringBuilder.reflectionToString(
				this, ToStringStyle.SHORT_PREFIX_STYLE
		);
    }
}
