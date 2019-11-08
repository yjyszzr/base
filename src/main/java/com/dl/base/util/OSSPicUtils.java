package com.dl.base.util;

import org.apache.commons.lang3.StringUtils;

/***
 * OSS图片链接处理
 * @author Administrator
 */
public class OSSPicUtils {
	
	/**
	 * 获取图片链接
	 * @param width
	 * @param height
	 * @param picUrl
	 * @return
	 */
	public static final String getPicFormat(int width,int height,String picUrl) {
		StringBuilder builder = new StringBuilder();
		if(!StringUtils.isEmpty(picUrl)) {
			builder.append(picUrl);
			//将图按比例缩略到原来的 1/3
			builder.append("?x-oss-process=image/resize,p_30");
			if(width > 0 && height > 0) {
				builder.append(",w_" + width);
				builder.append(",h_" + height);
			}
		}
		return builder.toString();
	}
}
