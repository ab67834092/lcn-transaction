package com.cjb.controller;

import com.cjb.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by cjb on 2018/12/25.
 */
@Controller
@RequestMapping("/server2")
public class TestController {

    @Autowired
    TestService testService;

    @RequestMapping(value = "test", method = RequestMethod.GET)
    public void test() {
        testService.test();
    }

}
