package com.love.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author my
 * @description
 * @date 2020/5/18
 */
@Controller
public class FilePageController {

    @GetMapping("/")
    public String uploadFilePage() {
        return "upload";
    }
}
