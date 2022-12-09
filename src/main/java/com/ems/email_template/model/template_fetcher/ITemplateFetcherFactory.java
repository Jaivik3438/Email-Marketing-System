package com.ems.email_template.model.template_fetcher;


import com.ems.email_template.persistent.ITemplatePersistent;

public interface ITemplateFetcherFactory {
    ITemplateFetcher createTemplateFetcher(ITemplatePersistent templatePersistent);
}
