package com.ems.subscriberList.model;

import java.util.List;

public abstract class SubscriberList {

    public  abstract List<Subscriber> getSubscriberByCampaignID(String campaignId);
    public  abstract List<Subscriber>  getSubscriberByCompanyID(String companyId);
}
