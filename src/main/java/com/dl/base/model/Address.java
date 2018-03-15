package com.dl.base.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @since 2017/12/13 上午11:31
 **/
@Data
public class Address implements Serializable {
    private static final long serialVersionUID = -7706600422738996499L;

    /**
     * 区域编码
     */
    private String regionCode;

    /**
     * 精度
     */
    private String lng;

    /**
     * 维度
     */
    private String lat;
}
