package com.ems.email_template.model.template_manipulator;

import com.ems.email_template.model.Template;
import com.ems.email_template.model.template_state.TemplateFailureState;
import com.ems.email_template.model.template_state.TemplateNotFoundState;
import com.ems.email_template.model.template_state.TemplateState;
import com.ems.email_template.model.template_state.TemplateSuccessState;
import com.ems.email_template.persistent.ITemplatePersistent;

public class EmailTemplateManipulator implements ITemplateManipulator {
    ITemplatePersistent templatePersistent;

    public EmailTemplateManipulator(ITemplatePersistent templatePersistent) {
        this.templatePersistent = templatePersistent;
    }

    @Override
    public TemplateState updateTemplate(String templateId, Template updatedTemplate) {
        int numOfRowsUpdated = templatePersistent.update(templateId, updatedTemplate);
        if (numOfRowsUpdated > 0) {
            return new TemplateSuccessState(numOfRowsUpdated);
        } else {
            return new TemplateNotFoundState("Template not found with the id " + templateId);
        }
    }

    @Override
    public TemplateState deleteTemplate(String templateId) {
        int numOfRowsDeleted = templatePersistent.delete(templateId);
        if (numOfRowsDeleted > 0) {
            return new TemplateSuccessState(numOfRowsDeleted);
        } else {
            return new TemplateFailureState("Fail to delete template");
        }
    }
}
