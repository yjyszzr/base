package com.dl.base.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.util.TextUtils;

public class EmojiFilter {
	
	 public static String filter(String str){
	        if(str == null || str.length() == 0){
	            return "";
	        }
	        StringBuffer sb = new StringBuffer();
	        for(int i=0;i<str.length();i++){
	            int ch = str.charAt(i);
	            int min = Integer.parseInt("E001", 16);
	            int max = Integer.parseInt("E537", 16);
	            if(ch >= min && ch <= max){
	                sb.append("");
	            }else{
	                sb.append((char)ch);
	            }
	        }
	        return sb.toString();
	    }

	    /**
	     * 过滤昵称特殊表情
	     */
	    public static String filterName(String name) {
	        if(name==null){
	            return null;

	        }
	        if("".equals(name.trim())){
	            return "";
	        }

	        Pattern patter = Pattern.compile("[a-zA-Z0-9\u4e00-\u9fa5]");
	        Matcher match = patter.matcher(name);

	        StringBuffer buffer = new StringBuffer();
	        boolean flag=false;
	        while (match.find()) {
	            buffer.append(match.group());
	        }
	        if(!name.equals(buffer.toString())){
	            flag=true;
	        }
	        if(flag)
	        return "?"+buffer.toString();   
	        else
	        return buffer.toString();
	    }
	    
	    /**
	     * 将字符串中的Emoji表情转换成Unicode编码
	     * @param src
	     * @return
	     */
	    public static  String emoji2Unicode(String src) {
	        StringBuffer unicode = new StringBuffer();
	        for (int i = 0; i < src.length(); i++) {
	            char c = src.charAt(i);
	            int codepoint = src.codePointAt(i);
	            if(isEmojiCharacter(codepoint)) {
	                unicode.append("\\u").append(Integer.toHexString(c));
	            } else {
	                unicode.append(c);
	            }
	        }
	        return unicode.toString();
	    }
	    /**
	     * 判断是否包含Emoji符号
	     * @param codePoint
	     * @return
	     */
	    public static boolean isEmojiCharacter(int codePoint) {
	        return (codePoint >= 0x2600 && codePoint <= 0x27BF) // 杂项符号与符号字体
	                || codePoint == 0x303D
	                || codePoint == 0x2049
	                || codePoint == 0x203C
	                || (codePoint >= 0x2000 && codePoint <= 0x200F)//
	                || (codePoint >= 0x2028 && codePoint <= 0x202F)//
	                || codePoint == 0x205F //
	                || (codePoint >= 0x2065 && codePoint <= 0x206F)//
	                /* 标点符号占用区域 */
	                || (codePoint >= 0x2100 && codePoint <= 0x214F)// 字母符号
	                || (codePoint >= 0x2300 && codePoint <= 0x23FF)// 各种技术符号
	                || (codePoint >= 0x2B00 && codePoint <= 0x2BFF)// 箭头A
	                || (codePoint >= 0x2900 && codePoint <= 0x297F)// 箭头B
	                || (codePoint >= 0x3200 && codePoint <= 0x32FF)// 中文符号
	                || (codePoint >= 0xD800 && codePoint <= 0xDFFF)// 高低位替代符保留区域
	                || (codePoint >= 0xE000 && codePoint <= 0xF8FF)// 私有保留区域
	                || (codePoint >= 0xFE00 && codePoint <= 0xFE0F)// 变异选择器
	                || codePoint >= 0x10000; // Plane在第二平面以上的，char都不可以存，全部都转
	    }
	    /**
	    * 将Unicode字符转成中文
	    * @param src
	    * @return
	    */
	   public static String unicode2Emoji(String src) {
	       if (TextUtils.isEmpty(src)) {
	           return "";
	       }

	       StringBuffer retBuf = new StringBuffer();
	       int maxLoop = src.length();
	       for (int i = 0; i < maxLoop; i++) {
	           if (src.charAt(i) == '\\') {
	               if ((i < maxLoop - 5) && ((src.charAt(i + 1) == 'u') || (src.charAt(i + 1) == 'U'))) {
	                   try {
	                       retBuf.append((char) Integer.parseInt(src.substring(i + 2, i + 6), 16));
	                       i += 5;
	                   } catch (NumberFormatException localNumberFormatException) {
	                       retBuf.append(src.charAt(i));
	                   }
	               } else {
	                   retBuf.append(src.charAt(i));
	               }
	           } else {
	               retBuf.append(src.charAt(i));
	           }
	       }
	       return retBuf.toString();
	   }
	
	
	public static String filterEmojiV2(String source) {
        if (source == null) {
            return source;
        }
        Pattern emoji = Pattern.compile("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]",
                Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);
        Matcher emojiMatcher = emoji.matcher(source);
        if (emojiMatcher.find()) {
            source = emojiMatcher.replaceAll("*");
            return source;
        }
        return source;
    }
	
	private static boolean isEmojiCharacter(char codePoint) {
        return (codePoint == 0x0) || (codePoint == 0x9) || (codePoint == 0xA)
                || (codePoint == 0xD)
                || ((codePoint >= 0x20) && (codePoint <= 0xD7FF))
                || ((codePoint >= 0xE000) && (codePoint <= 0xFFFD))
                || ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF));
    }
	
	/**
     * 过滤emoji 或者 其他非文字类型的字符
     * @param source
     * @return
     */
    public static String filterEmoji(String source) {
        if (StringUtils.isBlank(source)) {
            return source;
        }
        StringBuilder buf = null;
        int len = source.length();
        for (int i = 0; i < len; i++) {
            char codePoint = source.charAt(i);
            if (isEmojiCharacter(codePoint)) {
                if (buf == null) {
                    buf = new StringBuilder(source.length());
                }
                buf.append(codePoint);
            }
        }
        if (buf == null) {
            return source;
        } else {
            if (buf.length() == len) {
                buf = null;
                return source;
            } else {
                return buf.toString();
            }
        }
    }
}
