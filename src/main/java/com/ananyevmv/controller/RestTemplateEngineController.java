package com.ananyevmv.controller;

import com.ananyevmv.dto.TemplateInput;
import com.ananyevmv.service.TemplateEngineService;
import com.ananyevmv.service.TextToPDFService;
import com.ananyevmv.service.TextToWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Files;
import java.nio.file.Path;

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
    public ResponseEntity<Resource> saveToPDF(@RequestBody TemplateInput templateInput) {
        try {
            String templateResult = templateEngineService.processTemplate(templateInput);

            String fileName = textToPDFService.convertStringToPDF(templateResult);

            ByteArrayResource byteArrayResource = new ByteArrayResource(Files.readAllBytes(Path.of(fileName)));

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=output.pdf");

            return ResponseEntity.ok().headers(headers).contentLength(byteArrayResource.contentLength()).
                    contentType(MediaType.APPLICATION_OCTET_STREAM).body(byteArrayResource);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @PostMapping("/saveToWord")
    public ResponseEntity<?> saveToWord(@RequestBody TemplateInput templateInput) {
        try {
            String templateResult = templateEngineService.processTemplate(templateInput);

            String fileName = textToWordService.convertStringToWord(templateResult);

            ByteArrayResource byteArrayResource = new ByteArrayResource(Files.readAllBytes(Path.of(fileName)));

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=output.docx");

            return ResponseEntity.ok().headers(headers).contentLength(byteArrayResource.contentLength()).
                    contentType(MediaType.APPLICATION_OCTET_STREAM).body(byteArrayResource);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
