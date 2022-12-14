package com.ems.subscriber.business;

import com.ems.subscriber.persistence.SubscriberDbMock;
import com.ems.subscriberList.model.Subscriber;
import com.ems.subscriberList.persistence.ISubscriberPersistence;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SubscriberTest {
    private static Subscriber subscriberInformation;
    private static ISubscriberPersistence subscriberPersistence;

    @BeforeAll
    public static void init(){
        subscriberInformation = new Subscriber();
        subscriberPersistence = new SubscriberDbMock();
    }
    @Test
    public void saveSubsciberSuccessTest() throws Exception {
        Subscriber subscriberdetails = new Subscriber();
        subscriberdetails.sub_id ="S-0442977d-e64d-448a-8005-eac45d134496";
        int response = subscriberdetails.saveSubscriber(subscriberPersistence);
        assertEquals(1,response);
    }
    @Test
    public void saveSubscriberFailTest() throws Exception {
        Subscriber subscriberdetails = new Subscriber();
        subscriberdetails.sub_id ="xyz";
        int response = subscriberdetails.saveSubscriber(subscriberPersistence);
        assertEquals(-1,response);
    }
    @Test
    public void saveSubcriberAndUserSegmentIDSuccessTest() throws Exception {
        Subscriber subscriber = new Subscriber();
        subscriber.sub_id = "S-0442977d-e64d-448a-8005-eac45d134496";
        String UserId = "us-2ab0469f-4e1c-45ed-bad5-419e90371a09";
        int response = subscriber.saveSubcriberAndUserSegmentID(subscriberPersistence,"us-2ab0469f-4e1c-45ed-bad5-419e90371a09");
        assertEquals(1,response);
    }
    @Test
    public void saveSubcriberAndUserSegmentIDFailTest() throws Exception {
        Subscriber subscriber = new Subscriber();
        subscriber.sub_id = "xyz";
        int response = subscriber.saveSubcriberAndUserSegmentID(subscriberPersistence,"");
        assertEquals(-1,response);
    }
    @Test
    public void saveSubcriberAndUserSegmentIDfailTest() throws Exception {
        Subscriber subscriber = new Subscriber();
        subscriber.sub_id = "S-0442977d-e64d-448a-8005-eac45d134496";
        int response = subscriber.saveSubcriberAndUserSegmentID(subscriberPersistence,"");
        assertEquals(-1,response);
    }

}
