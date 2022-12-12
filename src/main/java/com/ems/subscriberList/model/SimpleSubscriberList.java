package com.ems.subscriberList.model;

import com.ems.subscriberList.persistence.ISubscriberPersistence;

import java.util.List;

public class SimpleSubscriberList extends SubscriberList{
    @Override
    public List<Subscriber> getSubscriberByCampaignID(String campaignId, ISubscriberPersistence persistence) throws Exception {
        return persistence.getSubscriberByCampaignId(campaignId);
    }

    @Override
    public List<Subscriber> getSubscriberByUserID(String userId, ISubscriberPersistence persistence) throws Exception {
        return persistence.getSubscriberByUserId(userId);
    }
}
