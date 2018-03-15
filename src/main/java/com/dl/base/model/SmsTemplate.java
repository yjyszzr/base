package com.dl.base.model;

import org.hibernate.validator.constraints.NotBlank;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;

/**
 * 短信信息
 *
 */

@Data
public class SmsTemplate {

  @NotBlank(message="手机号能为空")	
  private String mobile;
  @NotBlank(message="模板code不能为空") //szy_message_template中的code
  private String templateCode;
  
  private JSONObject contentjson;// szy_message_template模板内容中的key和value,key注意和数据库保持一致

}