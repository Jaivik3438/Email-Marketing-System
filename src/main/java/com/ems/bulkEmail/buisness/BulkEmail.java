package com.ems.bulkEmail.buisness;

import com.ems.campaign.model.Campaign;
import com.ems.campaign.model.IObserver;
import com.ems.subscriberList.model.Subscriber;
import com.ems.subscriberList.model.SubscriberList;

import java.util.List;

public abstract class  BulkEmail implements IObserver {
    Campaign campaign;
    List<Subscriber> subscribers;

    List<EmailDetails>emailDetailsList;
    SubscriberList subscriberList;

}
