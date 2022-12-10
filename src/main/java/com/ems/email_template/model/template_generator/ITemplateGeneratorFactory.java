package com.ems.email_template.model.template_generator;

import com.ems.email_template.persistent.ITemplatePersistent;

public interface ITemplateGeneratorFactory {
    TemplateGenerator createTemplateGenerator(ITemplatePersistent emailPersistent);
}
