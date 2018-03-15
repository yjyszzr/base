package com.dl.base.auth.client.jwt;

import com.dl.base.auth.client.config.UserAuthConfig;
import com.dl.base.enums.RespStatusEnum;
import com.dl.base.exception.ServiceException;
import com.dl.base.util.jwt.IJWTInfo;
import com.dl.base.util.jwt.JWTHelper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * Created by ace on 2017/9/15.
 */
@Configuration
public class UserAuthUtil {
    @Autowired
    private UserAuthConfig userAuthConfig;

    public IJWTInfo getInfoFromToken(String token) throws Exception {
        try {
            return JWTHelper.getInfoFromToken(token, userAuthConfig.getPubKeyPath());
        } catch (ExpiredJwtException ex) {
            throw new ServiceException(RespStatusEnum.TOKEN_EXPIRE.getStatus(), RespStatusEnum.TOKEN_EXPIRE.getMsg());
        } catch (SignatureException ex) {
            throw new ServiceException(RespStatusEnum.BAD_REQUEST.getStatus(), RespStatusEnum.BAD_REQUEST.getMsg());
        } catch (IllegalArgumentException ex) {
            throw new ServiceException(RespStatusEnum.BAD_REQUEST.getStatus(), RespStatusEnum.BAD_REQUEST.getMsg());
        }
    }
}
