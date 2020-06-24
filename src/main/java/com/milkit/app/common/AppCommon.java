package com.milkit.app.common;

import org.springframework.beans.factory.annotation.Value;



public class AppCommon {
	
	
	private static AppCommon singleInstance;
	
	private AppCommon() {}
	
    private static class SingleTonHolder {
        private static final AppCommon instance = new AppCommon();
    }
     
    public static AppCommon getInstance() {
        return SingleTonHolder.instance;
    }
	
	
	@Value("${image.thumbnail.width:200}")
	public int THUMBNALE_WIDTH = 200;
	
	@Value("${image.thumbnail.height:200}")
	public int THUMBNALE_HEIGHT = 200;
	
	@Value("${image.width:200}")
	public int IMAGE_WIDTH = 200;
	
	@Value("${image.height:200}")
	public int IMAGE_HEIGHT = 200;
	
	
	
	@Value("${image.thumbnail.suffix:_tmb}")
	public String THUMBNALE_SUFFIX = "_tmb";
	
	
	public int POINT_LOG_APPR_NO_SIZE = 20;
	
}
