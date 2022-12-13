package com.ems.email_template.model.template_fetcher;


import com.ems.authentication.model.User;
import com.ems.email_template.model.Template;
import com.ems.email_template.model.template_state.TemplateState;

import java.util.List;

public interface ITemplateFetcher {
    TemplateState fetchAllTemplates();
    TemplateState fetchTemplate(String templateId);
    TemplateState fetchAllTemplateByUserId(User user);
}
