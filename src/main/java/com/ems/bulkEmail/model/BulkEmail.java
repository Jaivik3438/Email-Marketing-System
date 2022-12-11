package com.ems.bulkEmail.model;

import com.ems.campaign.model.Campaign;
import com.ems.campaign.model.IObserver;
import com.ems.campaign.model.Subject;
import com.ems.subscriberList.model.Subscriber;
import com.ems.subscriberList.model.SubscriberList;

import java.util.List;

public class BulkEmail implements IObserver {
    Campaign campaign;
    List<Subscriber> subscribers;
    SubscriberList subscriberList;
    public BulkEmail(Campaign campaign,SubscriberList subscriberList){
        this.campaign=campaign;
        this.subscriberList=subscriberList;

    }

    @Override
    public void update(Subject s) {
        subscribers=subscriberList.getSubscriberByCampaignID(campaign.getCampaignId());
    }
}
