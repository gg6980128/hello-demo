package com.hello.demo.web.service;


import cn.hutool.extra.spring.SpringUtil;
import com.hello.common.core.exception.BusinessException;
import com.hello.demo.web.vo.LoginVo;

import java.util.Objects;

/**
 * 授权策略
 *
 * @author hellor
 */
public interface IAuthStrategy {

    String BASE_NAME = "AuthStrategy";

    /**
     * 登录
     *
     * @param body      登录对象
     * @param grantType 授权类型
     * @return 登录验证信息
     */
    static LoginVo login(String body, String grantType) {
        // 授权类型和客户端id
        String beanName = grantType + BASE_NAME;
        IAuthStrategy instance = SpringUtil.getBean(beanName);
        if (Objects.isNull(instance)) {
            throw new BusinessException("授权类型不正确!");
        }
        return instance.login(body);
    }

    /**
     * 登录
     *
     * @param body   登录对象
     * @return 登录验证信息
     */
    LoginVo login(String body);

}
