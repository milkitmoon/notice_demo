package com.milkit.app.domain.notice;

public enum ResourceTypeEnum {

	IMAGE("1"), VIDEO("2"), ETC("9");

    private String value;
    

    ResourceTypeEnum(String value) {
		this.value = value;
	}
    
	public String getValue() {		
		return value;
	}
}
