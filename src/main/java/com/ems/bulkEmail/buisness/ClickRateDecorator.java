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
        wrappedMail.generateBody(template);
        String generatedLink=generateLink(template);
        this.body= wrappedMail.body+
                "<p>click on the link below</p><br>"+generatedLink+"<br>";

    }
    private String generateLink(Template template){
        String link=((EmailTemplate)template).getLandingPageLink();
        String encodedLink=new String(Base64.getEncoder().encode(link.getBytes()));
        this.clickId="cl-"+UUID.randomUUID();
        String htmlLink="http://localhost:8080/analytics/click?clickid="+this.clickId+"&link="+encodedLink;
        String generatedLink="<a href='"+htmlLink+"'>"+htmlLink+"</a>";
        return generatedLink;

    }
}
