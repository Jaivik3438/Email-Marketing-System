package com.ems.bulkEmail.model;

import com.ems.email_template.model.Template;

import java.util.UUID;

public class PixelDecorator extends SimpleEmailDecorator{
    PixelDecorator(Mail wrappedMail) {
        super(wrappedMail);
    }

    @Override
    public void generateBody(Template template) {
        super.generateBody(template);
        String generatedPixel=generatePixel();
        this.body=this.body+generatedPixel;

    }
    private String generatePixel(){
        this.pixelId="p"+ UUID.randomUUID();
        String pixel="<img src='http://localhost:8080/analytics/'"+this.pixelId+".png  width='1' height='1'>";
        return pixel;
    }
}
