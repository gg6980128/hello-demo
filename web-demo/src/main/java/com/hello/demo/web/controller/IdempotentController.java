package com.hello.demo.web.controller;

import com.hello.common.core.pojo.R;
import com.hello.starter.idempotent.annotation.RepeatSubmit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 异常测试控制器
 *
 * @author GS2505
 */
@Slf4j
@RestController
@RequestMapping("/idempotent")
public class IdempotentController {

    @RepeatSubmit(message = "请不要重复提交")
    @GetMapping
    public R<Void> testIdempotent() {
        log.info("测试幂等请求");
        return R.ok();
    }
}
