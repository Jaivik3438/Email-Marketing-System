package com.ems.email_template.model.template_manipulator;


import com.ems.email_template.persistent.ITemplatePersistent;

public class EmailTemplateManipulatorFactory implements ITemplateManipulatorFactory {

    @Override
    public ITemplateManipulator createTemplateManipulator(ITemplatePersistent templatePersistent) {
        return new EmailTemplateManipulator(templatePersistent);
    }
}
