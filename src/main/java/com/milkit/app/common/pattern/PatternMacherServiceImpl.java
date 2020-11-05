package com.milkit.app.common.pattern;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;



@Service
public class PatternMacherServiceImpl implements PatternMacherService {
	
	
//	private String regexKey = "\\$\\{([\\w|\\uac00-\\ud7a3]*)\\}"; // ${key}
	private String regexKey = "\\#\\{([\\w|\\uac00-\\ud7a3]*)\\}"; // #{key}
	
	
	public String getMachingMessage(String orginMessage, Map<String, String> simbol, String regexKey) throws Exception {
		String machingMessage = null;
		
		if(orginMessage != null && simbol != null) {
			Pattern pattern = Pattern.compile(regexKey);
	
			Matcher matcher = pattern.matcher(orginMessage);
			StringBuffer sb = new StringBuffer();
			while (matcher.find()) {
				String key = matcher.group(1);
				String replacementValue = simbol.get(key);
				if(replacementValue != null) {
					matcher.appendReplacement(sb, replacementValue);
				} else {
					matcher.appendReplacement(sb, "");
				}
			}
			matcher.appendTail(sb);
			machingMessage = sb.toString();
		}
		
		return machingMessage;
	}
	

	public String getMachingMessage(String orginMessage, Map<String, String> simbol) throws Exception {
		return getMachingMessage(orginMessage, simbol, this.regexKey);
	}

	public boolean hasMachingMessage(String orginMessage) throws Exception {
		return hasMachingMessage(orginMessage, this.regexKey);
	}
	
	public boolean hasMachingMessage(String orginMessage, String regexKey) throws Exception {
		boolean hasMaching = false;
		
		Pattern pattern = Pattern.compile(regexKey);
		
		Matcher matcher = pattern.matcher(orginMessage);
		while (matcher.find()) {
			hasMaching = true;
			break;
		}
		
		return hasMaching;
	}
	
	public String getRegexKey() {
		return regexKey;
	}
	
	public void setRegexKey(String regexKey) {
		this.regexKey = regexKey;
	}



}
