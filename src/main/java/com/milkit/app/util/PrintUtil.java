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


    public static void print(PrintInterface printer, Object message) throws Exception {
		printer.print(message);
    }
    
    public static void print(PrintInterface printer, Collection message) throws Exception {
		if(printer != null && message != null) {
			printer.print("List size:["+message.size()+"]");
			for (Iterator e = message.iterator(); e.hasNext();) {
				printer.print(e.next());
			}
		}
	}
    
}