package com.hello.demo.web.controller;

import com.hello.common.core.exception.BusinessException;
import com.hello.common.core.pojo.R;
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
@RequestMapping("/exception")
public class ExceptionController {

    @GetMapping
    public R<Void> testException() {
        log.info("测试异常请求");
        throw new BusinessException(500, "测试异常");
    }
}
