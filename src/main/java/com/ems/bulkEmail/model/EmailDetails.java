package com.ems.bulkEmail.model;

import com.ems.bulkEmail.persistence.EmailDetailsDb;
import com.ems.bulkEmail.persistence.IEmailDetailsPersistence;
import com.ems.subscriberList.model.Subscriber;

import java.util.Date;
import java.util.UUID;

public class EmailDetails {



    public Mail mail;
    public Subscriber subscriber;
    public Date sentTime;
    public Date openedTime;
    public String campaignId;
    public String id;
    public int numberOfTimesOpened;
    public int numberOfTimesClicked;

    public void generateId(){
        this.id= "m-"+UUID.randomUUID().toString();
    }
    public EmailDetails loadEmailDetailsByPixelId(IEmailDetailsPersistence emailDetailsPersistence,String pixelId){
        return emailDetailsPersistence.loadEmailDetailsByPixelId(pixelId);
    }

    public EmailDetails loadEmailDetailsByClickId(IEmailDetailsPersistence emailDetailsPersistence, String clickId){
        return emailDetailsPersistence.loadEmailDetailsByClickId(clickId);
    }
    public boolean saveEmailDetails(IEmailDetailsPersistence emailDetailsPersistence){
        return emailDetailsPersistence.saveEmailDetails(this);
    }



}
