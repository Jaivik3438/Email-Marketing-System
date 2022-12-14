package com.ems.bulkEmail.buisness;

import com.ems.email_template.model.Template;
import com.ems.subscriberList.model.Subscriber;



public class SimpleEmailDetailBuilder extends EmailDetailBuilder{
    @Override
    public EmailDetails buildEmailDetail(Subscriber subscriber) {
        EmailDetails emailDetails=new SimpleEmailDetails();
        emailDetails.generateId();
        emailDetails.subscriber=subscriber;
        return emailDetails;
    }
    @Override
    public Mail buildEmail(Template template, IFormatMail emailFormatter) {
        Mail mail = new SimpleEmail();
        Mail emailWithClickRateAnalytics=new ClickRateDecorator(mail);
        Mail emailWithPixelAnalytics=new PixelDecorator(emailWithClickRateAnalytics);
        emailWithPixelAnalytics.generateSubject(template);
        emailWithPixelAnalytics.generateBody(template);
        emailWithPixelAnalytics.clickId=emailWithClickRateAnalytics.clickId;
        emailWithPixelAnalytics.body=emailFormatter.formatMail(emailWithPixelAnalytics.body);
        return emailWithPixelAnalytics;
    }
}
