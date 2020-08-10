package com.milkit.app.view;

import java.util.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class IndexController {

    @RequestMapping("/") 
    public String index() {
        ModelAndView mav = new ModelAndView("index"); 
        mav.addObject("name", "moon"); 
        
//        return mav;
        return "redirect:main";
    }

    
    @RequestMapping(value = "/test", method = RequestMethod.GET) 
    public String test() {
        ModelAndView mav = new ModelAndView("test"); 
        mav.addObject("name", "test"); 
        
        return "test";
    }
    
}