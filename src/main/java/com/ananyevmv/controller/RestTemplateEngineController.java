package com.ananyevmv.controller;

import com.ananyevmv.dto.TemplateInput;
import com.ananyevmv.service.TemplateEngineService;
import com.ananyevmv.service.TextToPDFService;
import com.ananyevmv.service.TextToWordService;
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
    private final TextToWordService textToWordService;
    private final TextToPDFService textToPDFService;

    @Autowired
    public RestTemplateEngineController(TemplateEngineService templateEngineService,
            TextToWordService textToWordService, TextToPDFService textToPDFService) {
        this.templateEngineService = templateEngineService;
        this.textToWordService = textToWordService;
        this.textToPDFService = textToPDFService;
    }

    @PostMapping("/saveToPDF")
    public ResponseEntity<?> saveToPDF(@RequestBody TemplateInput templateInput) {
        String templateResult = templateEngineService.processTemplate(templateInput);
        
        String fileName = textToPDFService.convertStringToPDF(templateResult);

        return new ResponseEntity<>(templateResult, HttpStatus.OK);
    }

    @PostMapping("/saveToWord")
    public ResponseEntity<?> saveToWord(@RequestBody TemplateInput templateInput) {
        String templateResult = templateEngineService.processTemplate(templateInput);

        String fileName = textToWordService.convertStringToWord(templateResult);

        return new ResponseEntity<>(templateResult, HttpStatus.OK);
    }
}
