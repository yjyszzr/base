package com.dl.base.util.jwt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by ace on 2017/9/10.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JWTInfo implements Serializable, IJWTInfo {
    private String unique;
    private String userId;
}
