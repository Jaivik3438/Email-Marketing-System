package com.ems.bulkEmail.buisness;

import com.ems.campaign.model.Campaign;
import com.ems.subscriberList.model.SubscriberList;

public interface IBulkEmailFactory {
    public BulkEmail CreateBulkEmail(Campaign campaign, SubscriberList subscriberList,ISendEmail emailSmtp);
}
