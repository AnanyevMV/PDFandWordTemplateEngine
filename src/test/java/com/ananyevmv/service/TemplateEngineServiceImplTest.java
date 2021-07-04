package com.ananyevmv.service;

import com.ananyevmv.dto.TemplateInput;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class TemplateEngineServiceImplTest {

    @Autowired
    private TemplateEngineService templateEngineService;

    @Test()
    void testIfExceptionIsThrownWhenSubstitutionVariableIsMissing() {
        assertThrows(RuntimeException.class, () -> {
            TemplateInput templateInput = new TemplateInput();
            templateInput.setTemplate("${name} ${lastName}");
            templateInput.setSubstitutions(Map.of("name", "John", "LASTNAME", "Doe"));
            String result = templateEngineService.processTemplate(templateInput);;
        });
    }
}