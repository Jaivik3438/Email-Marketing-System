package com.ems.email_template.model.template_manipulator;

import com.ems.email_template.persistent.ITemplatePersistent;

public interface ITemplateManipulatorFactory {
    ITemplateManipulator createTemplateManipulator(ITemplatePersistent templatePersistent);
}
