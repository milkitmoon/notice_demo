package com.milkit.app.common;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonPrinter implements PrintInterface {

    @Override
    public void print(Class<?> clazz, Object printObj) throws Exception {
        LoggerFactory.getLogger(clazz);

		ObjectMapper mapper = new ObjectMapper();
        String jsonStr = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(printObj);
        
        LoggerFactory.getLogger(clazz).debug(jsonStr);
    }
    
}