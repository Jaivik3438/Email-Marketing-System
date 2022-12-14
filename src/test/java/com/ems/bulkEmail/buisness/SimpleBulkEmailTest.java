package com.ems.bulkEmail.buisness;

import com.ems.bulkEmail.persistence.EmailDetailDbMock;
import com.ems.bulkEmail.persistence.IEmailDetailsPersistence;
import com.ems.campaign.model.Campaign;
import com.ems.campaign.model.SimpleCampaign;
import com.ems.subscriberList.model.SimpleSubscriberList;
import com.ems.subscriberList.model.SubscriberList;
import com.ems.subscriberList.persistence.ISubscriberPersistence;
import com.ems.subscriberList.persistence.SubscriberDB;
import org.junit.jupiter.api.BeforeAll;

public class SimpleBulkEmailTest {

    private static SimpleBulkEmail simpleBulkEmail;

/*    @BeforeAll
    public static void init(){
        Campaign campaign=new SimpleCampaign();
        SubscriberList subscriberList=new SimpleSubscriberList();
        ISendEmail emailSmtp=new GmailMock();
        IEmailDetailsPersistence emailDetailsPersistence= new EmailDetailDbMock();
        ISubscriberPersistence subscriberPersistence = new SubscriberDB();
        simpleBulkEmail=new SimpleBulkEmail(campaign,subscriberList,emailSmtp,emailDetailsPersistence,subscriberPersistence);
    }*/
}
