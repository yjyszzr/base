package com.dl.base.util;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.dl.base.constant.CommonConstants;
import com.dl.base.model.SmsTemplate;
import com.dl.base.result.BaseResult;
import com.dl.base.result.ResultGenerator;


public class SMSUtil {
    private static RestTemplate restTemplate = new RestTemplate();	
    /**
     * @see 发送短信
     * @param
     * @return
     */
    public static BaseResult<String> sendMessage(SmsTemplate sms) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("mobile", sms.getMobile());
        map.add("templateCode", sms.getTemplateCode());
        map.add("templateParams", sms.getContentjson().toJSONString());   
		BaseResult<String> rst = restTemplate.postForObject(CommonConstants.SMS_URL, map, BaseResult.class);
        if(rst.getCode() != 0) {
        	return ResultGenerator.genFailResult(rst.getMsg());
        }
        return ResultGenerator.genSuccessResult("发送成功");
    }
}
