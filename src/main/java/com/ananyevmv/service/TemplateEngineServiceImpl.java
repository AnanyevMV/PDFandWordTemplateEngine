package com.ananyevmv.service;

import com.ananyevmv.dto.TemplateInput;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class TemplateEngineServiceImpl implements TemplateEngineService {
    /**
     * Метод обработки шаблона.
     * @param templateInput входные данные
     * @return строка, в которой каждое шаблонное выражение вида ${variable} заменено на значение из входных данных
     *
     * Метод может выбрасывать unchecked-исключения при неверных входных данных
     */
    @Override
    public String processTemplate(TemplateInput templateInput) {
        StringBuilder templateStringBuilder = new StringBuilder(templateInput.getTemplate());
        Map<String, String> substitutions = templateInput.getSubstitutions();

        int fromIndex = 0;
        int startOfExpressionIndex;
        int endOfExpressionIndex;

        while ((startOfExpressionIndex = templateStringBuilder.indexOf("${", fromIndex)) != -1) {
            endOfExpressionIndex = templateStringBuilder.indexOf("}", startOfExpressionIndex);
            // к переменной startOffExpressionIndex прибавляется 2, т.к. символы ${ нас не интересуют
            String substitutionVariable = templateStringBuilder.substring(startOfExpressionIndex + 2, endOfExpressionIndex);
            String substitutionValue = substitutions.get(substitutionVariable);
            checkSubstitutionValue(substitutionValue, substitutionVariable);
            templateStringBuilder.insert(startOfExpressionIndex, substitutionValue);
            // ко второму параметру метода delete мы прибавляем значение 1, т.к. delete работает от start до end - 1
            // при stringBuilder.delete(start, end)
            templateStringBuilder.delete(startOfExpressionIndex + substitutionValue.length(),
            endOfExpressionIndex + substitutionValue.length() + 1);
            fromIndex = startOfExpressionIndex;
        }
        return templateStringBuilder.toString();
    }

    private void checkSubstitutionValue(String substitutionValue, String substitutionVariable) {
        if (substitutionValue == null) {
            throw new RuntimeException("No substitution value for variable " + substitutionVariable);
        }
        // Предположим, что текст для замены будет '               ${city}'
        // Тогда, программа будет вечно заменять выражение '${city}' на '               ${city}'
        // Поэтому запретим пользователю указывать такие символы в тексте для замены
        if (substitutionValue.contains("${")) {
            throw new RuntimeException("Substitution value cannot contain characters '${'");
        }
    }
}
