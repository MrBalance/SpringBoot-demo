package com.mrbalance.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: yunzhang.du
 * @description:
 * @Date: 2019-06-19 10:09
 */
@Controller
public class HelloWorldController {
    @RequestMapping("index")
    public String hello(){
        return "hello world!";
    }
}
