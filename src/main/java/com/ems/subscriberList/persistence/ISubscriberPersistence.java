package com.ems.subscriberList.persistence;

import com.ems.subscriberList.model.Subscriber;

import java.util.List;

public interface ISubscriberPersistence {
    public List<Subscriber> loadSubscriber(Subscriber subscriber) throws Exception;
    public int saveSubscriber(Subscriber saveSubscriber) throws Exception;
    public int saveSubcriberAndUserSegmentID(Subscriber saveSubscriberwithUserSegmentID, String UserSegmentId) throws Exception;
    public List<Subscriber> getSubscriberByCampaignId(String campaignId) throws Exception;
    public List<Subscriber> getSubscriberByUserId(String userId) throws Exception;
    Subscriber getSubscriberBySubscriberId(String subscriberId);
}
