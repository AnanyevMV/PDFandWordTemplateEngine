package com.ananyevmv.controller;

import com.ananyevmv.dto.TemplateInput;
import com.ananyevmv.service.TemplateEngineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RestTemplateEngineController {

    private final TemplateEngineService templateEngineService;

    @Autowired
    public RestTemplateEngineController(TemplateEngineService templateEngineService) {
        this.templateEngineService = templateEngineService;
    }

    @PostMapping("/saveToPDF")
    public ResponseEntity<?> saveToPDF(@RequestBody TemplateInput templateInput) {
        System.out.println(templateInput);
        String templateResult = templateEngineService.processTemplate(templateInput);
        System.out.println(templateResult);
        return new ResponseEntity<>(templateResult, HttpStatus.OK);
    }

    @PostMapping("/saveToWord")
    public ResponseEntity<?> saveToWord(@RequestBody TemplateInput templateInput) {
        System.out.println(templateInput);
        String templateResult = templateEngineService.processTemplate(templateInput);
        System.out.println(templateResult);
        return new ResponseEntity<>(templateResult, HttpStatus.OK);
    }
}
