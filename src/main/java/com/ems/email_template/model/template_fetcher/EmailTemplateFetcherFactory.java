package com.ems.email_template.model.template_fetcher;

import com.ems.email_template.persistent.ITemplatePersistent;

public class EmailTemplateFetcherFactory implements ITemplateFetcherFactory {
    @Override
    public ITemplateFetcher createTemplateFetcher(ITemplatePersistent templatePersistent) {
        return new EmailTemplateFetcher(templatePersistent);
    }
}
