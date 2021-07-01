package com.ananyevmv.controller;

import com.ananyevmv.dto.TemplateInput;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RestTemplateEngineController {


    @PostMapping("/saveToPDF")
    public ResponseEntity<?> saveToPDF(@RequestBody TemplateInput templateInput) {
        System.out.println(templateInput);
        return new ResponseEntity<>(templateInput, HttpStatus.OK);
    }

    @PostMapping("/saveToWord")
    public ResponseEntity<?> saveToWord(@RequestBody TemplateInput templateInput) {
        System.out.println(templateInput);
        return new ResponseEntity<>(templateInput, HttpStatus.OK);
    }
}
