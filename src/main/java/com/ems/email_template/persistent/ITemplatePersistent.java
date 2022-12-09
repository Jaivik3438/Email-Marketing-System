package com.ems.email_template.persistent;

import com.ems.email_template.model.Template;

import java.util.List;

public interface ITemplatePersistent {
    int save(Template template);
    List<Template> loadAllTemplates();
    Template loadTemplateById(String templateId);
    int update(String templateId, Template updatedTemplate);
    int delete(String templateId);
}
