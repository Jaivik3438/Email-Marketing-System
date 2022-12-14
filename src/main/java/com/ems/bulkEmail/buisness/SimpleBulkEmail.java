package com.ems.bulkEmail.buisness;

import com.ems.DbConnection.MySqlPersistenceConnection;
import com.ems.bulkEmail.persistence.EmailDetailsDb;
import com.ems.bulkEmail.persistence.IEmailDetailsPersistence;
import com.ems.campaign.model.Campaign;
import com.ems.campaign.model.IObserver;
import com.ems.campaign.model.Subject;
import com.ems.subscriberList.model.Subscriber;
import com.ems.subscriberList.model.SubscriberList;
import com.ems.subscriberList.persistence.ISubscriberPersistence;
import com.ems.subscriberList.persistence.SubscriberDB;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Date;

import java.util.List;

public class SimpleBulkEmail extends BulkEmail{

    private IEmailDetailsPersistence emailDetailsPersistence;
    private ISubscriberPersistence subscriberPersistence;
    public SimpleBulkEmail(Campaign campaign, SubscriberList subscriberList,ISendEmail emailSmtp,IEmailDetailsPersistence emailDetailsPersistence,ISubscriberPersistence subscriberPersistence ){
        this.campaign=campaign;
        this.subscriberList=subscriberList;
        this.emailSmtp=emailSmtp;
        this.emailDetailsList=new ArrayList<>();
        this.emailDetailsPersistence=emailDetailsPersistence;
        this.subscriberPersistence=subscriberPersistence;

    }

    @Override
    public void update(Subject s) {
        emailDetailsList=generateEmailDetailList();
        for(int i=0;i<emailDetailsList.size();i++){
           EmailDetails emailDetail=emailDetailsList.get(i);
           sendEmail(emailDetail,emailSmtp);
           emailDetail.sentTime= new Date();
           emailDetail.createEmailDetails(emailDetailsPersistence);
        }
    }
    private List<EmailDetails> generateEmailDetailList(){
        try {
            subscribers=subscriberList.getSubscriberByCampaignID(campaign.getCampaignId(),subscriberPersistence);
        } catch (Exception e) {
             e.printStackTrace();
        }
        List<EmailDetails> emailList=new ArrayList<>();
        EmailDetailBuilder emailDetailBuilder= new SimpleEmailDetailBuilder();
        for (Subscriber subscriber:subscribers){
            EmailDetails emailDetails=emailDetailBuilder.buildEmailDetail(subscriber);
            emailDetails.campaignId=campaign.getCampaignId();
            Mail mail=emailDetailBuilder.buildEmail(campaign.getEmailTemplate(),new HtmlFormatter());
            emailDetails.mail=mail;
            emailList.add(emailDetails);
        }
        return emailList;
    }
    private void sendEmail(EmailDetails emailDetail,ISendEmail emailSmtp){
        emailSmtp.sendEmail(emailDetail);
    }
}
