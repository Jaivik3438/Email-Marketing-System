package com.ems.email_template.model.template_manipulator;

import com.ems.email_template.model.Template;
import com.ems.email_template.model.template_state.TemplateState;

public interface ITemplateManipulator {
    TemplateState updateTemplate(String templateId, Template templateToUpdate);
    TemplateState deleteTemplate(String templateId);
}
