package com.ems.campaign.model;

import java.util.Date;

public class SimpleCampaign extends Campaign {
    public SimpleCampaign() {
        super();
    }

    public SimpleCampaign(String campaignName, Date campaignStartTime) {
        super(campaignName, campaignStartTime);
    }

    public SimpleCampaign(String campaignId, String campaignName, Date campaignStartTime) {
        super(campaignId, campaignName, campaignStartTime);
    }
}
