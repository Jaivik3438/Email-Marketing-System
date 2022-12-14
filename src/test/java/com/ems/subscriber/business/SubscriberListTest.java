package com.ems.subscriber.business;

import com.ems.subscriber.persistence.SubscriberDbMock;
import com.ems.subscriberList.model.SimpleSubscriberList;
import com.ems.subscriberList.model.Subscriber;
import com.ems.subscriberList.model.SubscriberList;
import com.ems.subscriberList.persistence.ISubscriberPersistence;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

public class SubscriberListTest {
    private static SubscriberList subscriberInformation;
    private static ISubscriberPersistence subscriberPersistence;

    @BeforeAll
    public static void init(){
        subscriberInformation = new SimpleSubscriberList();
        subscriberPersistence = new SubscriberDbMock();
    }

    @Test
    public void getSubscriberByCampaignIdSuccessTest() throws Exception {
        List<Subscriber> subscriberByCampaignId = subscriberInformation.getSubscriberByCampaignID("c-fbfae4ab-0af8-41d5-81bc-cdf916780bc5",subscriberPersistence);


    }
}
