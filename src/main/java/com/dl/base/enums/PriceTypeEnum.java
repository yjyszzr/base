package com.dl.base.enums;

/**
 * 价格类型
 *
 * @since 23/01/2018 2:26 PM
 */
public enum PriceTypeEnum {
    /**
     * 活动价
     */
    ActivityPrice(1, "活动价", "原价"),

    /**
     * 会员价
     */
    MembershipPrice(2, "会员价", "原价");


    int type;
    String name;
    String originalPriceName;

    public int getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getOriginalPriceName() {
        return originalPriceName;
    }

    PriceTypeEnum(int type, String name, String originalPriceName) {
        this.type = type;
        this.name = name;
        this.originalPriceName = originalPriceName;
    }
}
