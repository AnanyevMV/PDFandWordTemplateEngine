package com.ananyevmv.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/error")
public class RestErrorController implements ErrorController {
    @RequestMapping
    public void handleError() {
        throw new RuntimeException("BAD REQUEST");
    }
}
