package com.ems.bulkEmail.buisness;

import com.ems.email_template.model.EmailTemplate;
import com.ems.email_template.model.Template;
import java.util.UUID;
import java.util.Base64;

public class ClickRateDecorator extends SimpleEmailDecorator {

    ClickRateDecorator(Mail wrappedMail) {
        super(wrappedMail);
    }
    @Override
    public void generateBody(Template template) {
        super.generateBody(template);
        this.subject= ((EmailTemplate)template).getTemplateSubject();
        String generatedLink=generateLink(template);

        this.body=this.body+
                "<p>"+((EmailTemplate) template).getTemplateDescription()+"</p><br>"+
                "<p>click on the link below</p><br>"+generatedLink;

    }
    private String generateLink(Template template){
        String link=((EmailTemplate)template).getLandingPageLink();
        String encodedLink=String.valueOf(Base64.getUrlEncoder().encode(link.getBytes()));
        this.clickId="cl"+UUID.randomUUID();
        String generatedLink="http://localhost:8080/analytics/click?clickid="+this.clickId+"&link="+encodedLink;
        return generatedLink;

    }
}
