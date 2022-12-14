package com.ems.bulkEmail.buisness;

import com.ems.bulkEmail.persistence.IEmailDetailsPersistence;
import com.ems.campaign.model.Campaign;
import com.ems.subscriberList.model.SubscriberList;
import com.ems.subscriberList.persistence.ISubscriberPersistence;

public interface IBulkEmailFactory {
    public BulkEmail createBulkEmail(Campaign campaign, SubscriberList subscriberList, ISendEmail emailSmtp, IEmailDetailsPersistence emailDetailsPersistence, ISubscriberPersistence subscriberPersistence);
}
