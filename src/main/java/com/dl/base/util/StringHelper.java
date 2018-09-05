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
    
    /**
     * 替换html中的内容为文字
     */
    public static String stripHtml(String content) { 
    	// <p>段落替换为换行 
    	content = content.replaceAll("<p .*?>", "\n"); 
    	// <br><br/>替换为换行 
    	content = content.replaceAll("<br\\s*/?>", "\n"); 
    	// 去掉其它的<>之间的东西 
    	content = content.replaceAll("\\<.*?>", ""); 
    	// 还原HTML 
    	// content = HTMLDecoder.decode(content); 
    	return content; 
   }
    
    
}
