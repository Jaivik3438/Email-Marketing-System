package com.ems.email_template.model.exception;

public class TemplateNotFoundException extends Exception {
    public TemplateNotFoundException(String message) {
        super(message);
    }
}