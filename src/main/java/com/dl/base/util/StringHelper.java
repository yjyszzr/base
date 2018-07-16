package com.dl.base.util;

/**
 * Created by ace on 2017/9/10.
 */
public class StringHelper {
    public static String getObjectValue(Object obj){
        return obj==null?"":obj.toString();
    }
    
    public static String replaceChangeStr(String oldStr) {
    	if(oldStr.contains("↓") || oldStr.contains("↑")) {
    		return oldStr.substring(0, oldStr.length() - 1);
    	}
    	return oldStr;
    }
    
}
