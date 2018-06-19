package com.dl.base.constant;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class EmptyParam {

	@ApiModelProperty(value="公共的空参数：有一些接口不需要传参数的时候，就传这个类型")
	private String emptyStr;
	
}
