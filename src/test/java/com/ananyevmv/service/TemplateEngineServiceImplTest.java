package com.ananyevmv.service;

import com.ananyevmv.dto.TemplateInput;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class TemplateEngineServiceImplTest {

    @Autowired
    private TemplateEngineService templateEngineService;

    @Test
    public void testIfExceptionIsThrownWhenSubstitutionVariableIsMissing() {
        assertThrows(RuntimeException.class, () -> {
            TemplateInput templateInput = new TemplateInput();
            templateInput.setTemplate("${name} ${lastName}");
            templateInput.setSubstitutions(Map.of("name", "John", "LASTNAME", "Doe"));
            String result = templateEngineService.processTemplate(templateInput);
        });
    }

    @Test
    public void testIfExceptionIsThrownWhenSubstitutionValueContainsIllegalSymbols() {
        assertThrows(RuntimeException.class, () -> {
            TemplateInput templateInput = new TemplateInput();
            templateInput.setTemplate("${name} ${lastName}");
            templateInput.setSubstitutions(Map.of("name", "John", "lastName", "abcdefgh${lastName}"));
            String result = templateEngineService.processTemplate(templateInput);
        });
    }

    @Test
    public void testIfTemplateResultEqualToCorrectValue() {
        TemplateInput templateInput = new TemplateInput();
        templateInput.setTemplate("${name} ${lastName}");
        templateInput.setSubstitutions(Map.of("name", "John", "lastName", "Doe"));
        String result = templateEngineService.processTemplate(templateInput);
        assertEquals(result, "John Doe");
    }
}