package com.hello.demo.web.controller;

import com.hello.common.core.pojo.LoginBody;
import com.hello.common.core.pojo.R;
import com.hello.common.core.util.JacksonUtils;
import com.hello.demo.web.service.IAuthStrategy;
import com.hello.demo.web.vo.LoginVo;
import com.hello.starter.idempotent.annotation.RepeatSubmit;
import org.springframework.web.bind.annotation.*;

/**
 * 登录接口
 *
 * @author hellor
 */
@RestController
public class LoginController {

    @RepeatSubmit(message = "正在登录...")
    @PostMapping("/login")
    public R<LoginVo> login(@RequestBody String body) {
        LoginBody loginBody = JacksonUtils.parseObject(body, LoginBody.class);
        return R.ok(IAuthStrategy.login(body, loginBody.getGrantType()));
    }

}
