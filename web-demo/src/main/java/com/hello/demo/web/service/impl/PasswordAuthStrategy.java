package com.hello.demo.web.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.stp.parameter.SaLoginParameter;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.validation.ValidationUtil;
import com.hello.common.core.enums.UserType;
import com.hello.common.core.exception.BusinessException;
import com.hello.common.core.pojo.LoginUser;
import com.hello.common.core.util.JacksonUtils;
import com.hello.demo.web.model.PasswordLoginBody;
import com.hello.demo.web.service.IAuthStrategy;
import com.hello.demo.web.vo.LoginVo;
import com.hello.starter.satoken.util.LoginHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 密码认证策略
 *
 * @author hellor
 */
@Slf4j
@Service("password" + IAuthStrategy.BASE_NAME)
@RequiredArgsConstructor
public class PasswordAuthStrategy implements IAuthStrategy {

    @Override
    public LoginVo login(String body) {
        PasswordLoginBody loginBody = JacksonUtils.parseObject(body, PasswordLoginBody.class);
        ValidationUtil.validate(loginBody);
        String username = loginBody.getUsername();

        LoginUser loginUser = loadUserByUsername(username);
        loginUser.setClientKey("ClientKey");
        loginUser.setDeviceType("DeviceType");
        SaLoginParameter model = new SaLoginParameter();
        model.setDeviceType(loginUser.getDeviceType());
        // 自定义分配 不同用户体系 不同 token 授权时间 不设置默认走全局 yml 配置
        // 例如: 后台用户30分钟过期 app用户1天过期
        model.setTimeout(60 * 60);
        model.setActiveTimeout(60 * 10);
        model.setExtra(LoginHelper.CLIENT_KEY, "client_id_1");
        // 生成token
        LoginHelper.login(loginUser, model);

        LoginVo loginVo = new LoginVo();
        loginVo.setAccessToken(StpUtil.getTokenValue());
        loginVo.setExpireIn(StpUtil.getTokenTimeout());
        loginVo.setClientId("client_id_1");
        return loginVo;
    }

    private LoginUser loadUserByUsername(String username) {
        if (!StrUtil.equals("admin", username)) {
            log.info("登录用户：{} 不存在.", username);
            throw new BusinessException(500, "user.not.exists", username);
        }
        LoginUser user = new LoginUser();
        user.setUserId(1L);
        user.setUserType(UserType.SYS_USER);
        user.setUsername(username);
        user.setNickname("管理員");
        return user;
    }

}
