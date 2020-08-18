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
	
	
	@Value("${image.thumbnail.width:240}")
	public int THUMBNALE_WIDTH = 240;
	
	@Value("${image.thumbnail.height:240}")
	public int THUMBNALE_HEIGHT = 240;
	
	@Value("${image.width:368}")
	public int IMAGE_WIDTH = 368;
	
	@Value("${image.height:392}")
	public int IMAGE_HEIGHT = 392;
	
	
	
	@Value("${image.thumbnail.suffix:_tmb}")
	public String THUMBNALE_SUFFIX = "_tmb";
	
	
	public int POINT_LOG_APPR_NO_SIZE = 20;
	
}
