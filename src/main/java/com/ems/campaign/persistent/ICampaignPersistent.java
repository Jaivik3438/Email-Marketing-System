package com.ems.campaign.persistent;

import com.ems.campaign.model.Campaign;

public interface ICampaignPersistent {
    int save(Campaign campaign, String templateId, String userSegmentId);
}
