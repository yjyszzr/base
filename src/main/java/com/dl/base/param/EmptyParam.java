package com.dl.base.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 定义接口的时候不用传参数的时候,就传这个就好了
 * @author zzr
 *
 */
@ApiModel("空参数信息")
@Data
public class EmptyParam {

	@ApiModelProperty("空参数信息")
    private String emptyStr;
}
