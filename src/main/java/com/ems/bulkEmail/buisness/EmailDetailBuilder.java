package com.ems.bulkEmail.buisness;

import com.ems.email_template.model.Template;
import com.ems.subscriberList.model.Subscriber;

public abstract class EmailDetailBuilder {
    public abstract EmailDetails buildEmailDetail(Subscriber subscriber);
    public abstract Mail buildEmail(Template template,IFormatMail emailformatter);

}
