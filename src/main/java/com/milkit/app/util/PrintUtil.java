package com.milkit.app.util;

import java.util.*;

import com.milkit.app.common.PrintInterface;

public class PrintUtil {

    private PrintUtil() {}
     
    private static class SingleTonHolder {
        private static final PrintUtil instance = new PrintUtil();
    }
     
    public static PrintUtil getInstance() {
        return SingleTonHolder.instance;
    }


    public static void print(PrintInterface printer, Class<?> clazz, Object message) throws Exception {
		printer.print(clazz, message);
    }
    
    public static void print(PrintInterface printer, Class<?> clazz, Collection message) throws Exception {
		if(printer != null && message != null) {
			printer.print(clazz, "List size:["+message.size()+"]");
			for (Iterator e = message.iterator(); e.hasNext();) {
//				Object obj = e.next();
//				printer.print(obj.getClass().getInterfaces()[0]);
				printer.print(clazz, e.next());
			}
		}
	}
    
}