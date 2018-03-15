package com.dl.base.model;

import com.dl.base.enums.PriceTypeEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 价格
 *
 * @since 23/01/2018 11:27 AM
 */
@Data
public class PriceInfoDTO implements Serializable {
    private static final long serialVersionUID = 8998115091161870596L;

    @ApiModelProperty("价格名称")
    private String priceName;

    @ApiModelProperty("价格")
    private BigDecimal priceValue;

    @ApiModelProperty("价格类型 1、活动价   2、 会员价")
    private Integer priceType;

    @ApiModelProperty("原价")
    private BigDecimal originalPrice;

    @ApiModelProperty("原价名称")
    private String originalPriceName = "售价";


    public void initPriceType(PriceTypeEnum priceType) {
        this.priceType = priceType.getType();
        this.priceName = priceType.getName();
        this.originalPriceName = priceType.getOriginalPriceName();
    }


}
