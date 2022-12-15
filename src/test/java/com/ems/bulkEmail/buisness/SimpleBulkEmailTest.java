package com.ems.bulkEmail.buisness;

import com.ems.bulkEmail.persistence.EmailDetailDbMock;
import com.ems.bulkEmail.persistence.IEmailDetailsPersistence;
import com.ems.bulkEmail.smtp.GmailMock;
import com.ems.campaign.model.Campaign;
import com.ems.campaign.model.CampaignEmailScheduler;
import com.ems.campaign.model.SimpleCampaign;
import com.ems.email_template.model.SimpleEmailTemplate;
import com.ems.subscriber.persistence.SubscriberDbMock;
import com.ems.subscriberList.model.SimpleSubscriberList;
import com.ems.subscriberList.model.SubscriberList;
import com.ems.subscriberList.persistence.ISubscriberPersistence;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class SimpleBulkEmailTest {

    private static SimpleBulkEmail simpleBulkEmail;

    private static Campaign campaign;

   @BeforeAll
    public static void init(){
       campaign=new SimpleCampaign();
       campaign.setCampaignId("c-fbfae4ab-0af8-41d5-81bc-cdf916780bc5");
       SimpleEmailTemplate template = new SimpleEmailTemplate();
       template.setTemplateId("123");
       template.setTemplateName("test Template");
       template.setTemplateSubject("test subject");
       template.setLandingPageLink("www.google.com");
       campaign.setEmailTemplate(template);
        SubscriberList subscriberList=new SimpleSubscriberList();
        ISendEmail emailSmtp=new GmailMock();
        IEmailDetailsPersistence emailDetailsPersistence= new EmailDetailDbMock();
        ISubscriberPersistence subscriberPersistence = new SubscriberDbMock();
        simpleBulkEmail=new SimpleBulkEmail(campaign,subscriberList,emailSmtp,emailDetailsPersistence,subscriberPersistence);
    }
    @Test
    public void UpdateTest(){
       simpleBulkEmail.update(new CampaignEmailScheduler(campaign));

    }

}
