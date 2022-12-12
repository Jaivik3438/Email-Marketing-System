package com.ems.email_template.model.template_generator;


import com.ems.email_template.model.Template;
import com.ems.email_template.model.template_state.TemplateState;

public interface TemplateGenerator {
    TemplateState createNewTemplate(Template template, String userId);
}
