package com.ems.bulkEmail.buisness;

import com.ems.email_template.model.Template;

import java.util.UUID;

public class PixelDecorator extends SimpleEmailDecorator{
    PixelDecorator(Mail wrappedMail) {
        super(wrappedMail);
    }

    @Override
    public void generateBody(Template template) {
        wrappedMail.generateBody(template);
        String generatedPixel=generatePixel();
        this.body=wrappedMail.body+generatedPixel;
        this.clickId=wrappedMail.clickId;
    }
    private String generatePixel(){
        this.pixelId="p-"+ UUID.randomUUID();
        String pixel="<img src='http://localhost:8080/analytics/pixel?pixelid="+this.pixelId+"'  width='1' height='1'>";
        return pixel;
    }
}
