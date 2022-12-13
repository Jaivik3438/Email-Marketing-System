package com.ems.campaign.persistent;

import com.ems.bulkEmail.buisness.EmailDetails;
import com.ems.campaign.model.Campaign;
import com.ems.subscriberList.model.Subscriber;

import java.util.List;

public interface ICampaignPersistent {
    int save(Campaign campaign, String templateId, String userSegmentId);
    List<Campaign> loadAllCampaign();
    Campaign loadCampaign(String campaignId);
    List<Campaign> loadCampaignByUserId(String userId);
    int update(String campaignId, Campaign campaignToUpdate);
    int delete(String campaignId);

    List<EmailDetails> getAllEmailDetailsOfCampaign(String campaignId);

    Subscriber getSubscriberById(String subscriberId);
}
