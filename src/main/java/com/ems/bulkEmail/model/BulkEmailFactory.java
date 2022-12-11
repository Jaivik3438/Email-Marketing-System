package com.ems.bulkEmail.model;

import com.ems.campaign.model.Campaign;
import com.ems.subscriberList.model.SubscriberList;

public class BulkEmailFactory implements IBulkEmailFactory{
    public BulkEmail CreateBulkEmail(Campaign campaign, SubscriberList subscriberList){
        return new BulkEmail(campaign,subscriberList);
    };
}
