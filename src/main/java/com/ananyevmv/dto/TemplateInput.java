package com.ananyevmv.dto;

import java.util.Map;

public class TemplateInput {
    private String template;
    private Map<String, String> substitutions;

    public TemplateInput() {

    }

    public TemplateInput(String template, Map<String, String> substitutions) {
        this.template = template;
        this.substitutions = substitutions;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public Map<String, String> getSubstitutions() {
        return substitutions;
    }

    public void setSubstitutions(Map<String, String> substitutions) {
        this.substitutions = substitutions;
    }

    @Override
    public String toString() {
        return "TemplateInput{" +
                "template='" + template + '\'' +
                ", substitutions=" + substitutions +
                '}';
    }
}
