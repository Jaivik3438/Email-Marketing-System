package com.ems.bulkEmail.model;

import com.ems.campaign.model.Campaign;
import com.ems.subscriberList.model.SubscriberList;

public interface IBulkEmailFactory {
    public BulkEmail CreateBulkEmail(Campaign campaign, SubscriberList subscriberList);
}
