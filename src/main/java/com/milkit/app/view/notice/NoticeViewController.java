package com.milkit.app.view.notice;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.milkit.app.domain.notice.service.NoticeServiceImpl;
import com.milkit.app.domain.userinfo.UserInfo;


@Controller
public class NoticeViewController {
	
	private static final Logger logger  = LoggerFactory.getLogger(NoticeViewController.class);
	
	@Autowired
    private NoticeServiceImpl noticeServie;

    @RequestMapping("/main") 
    public ModelAndView main(@AuthenticationPrincipal UserInfo userInfo) {
       
        ModelAndView mav = new ModelAndView("notice/main"); 
        
        return mav;
    }
    
    @RequestMapping("/read")
    public ModelAndView read(@AuthenticationPrincipal UserInfo userInfo,
    		@RequestParam(value="id", defaultValue="-1", required=false) Long id) throws Exception {
       
    	noticeServie.increaseReadNum(id);
    	
        ModelAndView mav = new ModelAndView("notice/read");
        mav.addObject("id", id);
        
//	        mav.addObject(userInfo.getClass().getSimpleName(), userInfo);
        
        return mav;
    }
    
    @RequestMapping("/write")
    public ModelAndView write(@AuthenticationPrincipal UserInfo userInfo) throws Exception {
       
//logger.debug("\n\n\n\n"+userInfo.toString()+"\n\n\n");
    	
    	
        Long id = noticeServie.selectSeq();

        ModelAndView mav = new ModelAndView("notice/write");
        mav.addObject("id", id);
        mav.addObject("wmode", "i");
        
        return mav;
    }
    
    @RequestMapping("/update")
    public ModelAndView update(@AuthenticationPrincipal UserInfo userInfo,
    		@RequestParam(value="id", defaultValue="-1", required=false) Long id) {
       
        ModelAndView mav = new ModelAndView("notice/write"); 
        mav.addObject("id", id);
        mav.addObject("wmode", "u");
        
        return mav;
    }
    
}