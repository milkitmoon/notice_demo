package com.milkit.app.common.pattern;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public interface PatternMacherService {
	
	public String getMachingMessage(String orginMessage, Map<String, String> simbol) throws Exception;
	
	public String getMachingMessage(String orginMessage, Map<String, String> simbol, String regexKey) throws Exception;
	
	public boolean hasMachingMessage(String orginMessage) throws Exception;
	
	public boolean hasMachingMessage(String orginMessage, String regexKey) throws Exception;

}
