package com.ems.bulkEmail.buisness;

import com.ems.subscriberList.model.Subscriber;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SimpleEmailDetailBuilderTest {
    private static EmailDetailBuilder emailDetailBuilder;

    @BeforeAll
    public static void init(){
        emailDetailBuilder= new SimpleEmailDetailBuilder();
    }
    @Test
    public void BuildEmailDetailNotNullTest(){
        Subscriber subscriber = new Subscriber();
        subscriber.sub_id="S-0442977d-e64d-448a-8005-eac45d134496";
        EmailDetails emailDetails= emailDetailBuilder.buildEmailDetail(subscriber);
        assertNotNull(emailDetails);

    }
    @Test
    public void BuildEmailDetailObjectTest(){
        Subscriber subscriber = new Subscriber();
        subscriber.sub_id="S-0442977d-e64d-448a-8005-eac45d134496";
        EmailDetails emailDetails= emailDetailBuilder.buildEmailDetail(subscriber);
        assertEquals(emailDetails.subscriber.sub_id,subscriber.sub_id);

    }

    @Test
    public void BuildMailTest(){
        Subscriber subscriber = new Subscriber();
        subscriber.sub_id="S-0442977d-e64d-448a-8005-eac45d134496";
        EmailDetails emailDetails= emailDetailBuilder.buildEmailDetail(subscriber);
        assertEquals(emailDetails.subscriber.sub_id,subscriber.sub_id);

    }
}
