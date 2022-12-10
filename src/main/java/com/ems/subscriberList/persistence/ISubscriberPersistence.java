package com.ems.subscriberList.persistence;

import com.ems.subscriberList.model.Subscriber;

import java.util.List;

public interface ISubscriberPersistence {
    public List<Subscriber> loadSubscriber(Subscriber subscriber) throws Exception;
    public int saveSubscriber(Subscriber saveSubscriber) throws Exception;

}
