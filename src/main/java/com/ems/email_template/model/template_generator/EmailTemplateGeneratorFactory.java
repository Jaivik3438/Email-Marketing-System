package com.ems.email_template.model.template_generator;

import com.ems.email_template.persistent.ITemplatePersistent;

public class EmailTemplateGeneratorFactory implements ITemplateGeneratorFactory {
    @Override
    public TemplateGenerator createTemplateGenerator(ITemplatePersistent emailPersistent) {
        return new EmailTemplateGenerator(emailPersistent);
    }
}
