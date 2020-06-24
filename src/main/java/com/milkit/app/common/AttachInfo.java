package com.milkit.app.common;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class AttachInfo {
	
	private String resourceURL;
	private String thumbnaleURL;
	private String filename;
	
	private String resourceType;


	public AttachInfo() {}
	
	public AttachInfo(String resourceURL) {
		this();
		this.resourceURL = resourceURL;
	}
		
	public AttachInfo(String resourceURL, String thumbnaleURL) {
		this(resourceURL, thumbnaleURL, null);
	}
	
	public AttachInfo(String resourceURL, String thumbnaleURL, String filename) {
		this();
		this.resourceURL = resourceURL;
		this.thumbnaleURL = thumbnaleURL;
		this.filename = filename;
	}
	

	public String getResourceURL() {
		return resourceURL;
	}

	public void setResourceURL(String resourceURL) {
		this.resourceURL = resourceURL;
	}

	public String getThumbnaleURL() {
		return thumbnaleURL;
	}

	public void setThumbnaleURL(String thumbnaleURL) {
		this.thumbnaleURL = thumbnaleURL;
	}	
	
	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
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
