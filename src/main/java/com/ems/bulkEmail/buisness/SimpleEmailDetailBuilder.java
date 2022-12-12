package com.ems.bulkEmail.buisness;

import com.ems.email_template.model.Template;
import com.ems.subscriberList.model.Subscriber;



public class SimpleEmailDetailBuilder extends EmailDetailBuilder{
    @Override
    public EmailDetails buildEmailDetail(Subscriber subscriber) {
        EmailDetails emailDetails=new EmailDetails();
        emailDetails.generateId();
        emailDetails.subscriber=subscriber;
        return emailDetails;
    }

    @Override
    public Mail buildEmail(Template template, IFormatMail emailformatter) {
        Mail mail = new SimpleEmail();
        Mail emailWithPixelAnalytics=new PixelDecorator(mail);
        Mail emailWithClickRateAnalytics=new ClickRateDecorator(emailWithPixelAnalytics);
        emailWithClickRateAnalytics.generateSubject(template);
        emailWithClickRateAnalytics.generateBody(template);
        emailWithClickRateAnalytics.body=new HtmlFormatter().formatMail(emailWithClickRateAnalytics.body);
        return emailWithClickRateAnalytics;
    }
}
