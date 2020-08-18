package com.milkit.app.common.file;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@ConfigurationProperties(prefix = "file")
public class FileUploadProperties {
	

	private String url;
    private String location;
    private String thumnailPrefix;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getThumnailPrefix() {
		return thumnailPrefix;
	}

	public void setThumnailPrefix(String thumnailPrefix) {
		this.thumnailPrefix = thumnailPrefix;
	}
        
}