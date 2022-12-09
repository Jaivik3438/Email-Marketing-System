package com.ems.campaign.model;

import com.ems.campaign.persistent.ICampaignPersistent;

import java.util.Date;
import java.util.UUID;

public class Campaign extends Subject {
    private String campaignId;
    private String campaignName;
    private String campaignStatus;
    private Date campaignStartTime;
    private CampaignAnalytics analytics;

    public Campaign() {
        this.campaignId = generateId();
    }

    public Campaign(String campaignName, Date campaignStartTime) {
        this.campaignId = generateId();
        this.campaignName = campaignName;
        this.campaignStatus = "upcoming";
        this.campaignStartTime = campaignStartTime;
        this.analytics = new CampaignAnalytics();
    }

    public Campaign(String campaignId, String campaignName, Date campaignStartTime) {
        this.campaignId = campaignId;
        this.campaignName = campaignName;
        this.campaignStatus = campaignStatus;
        this.campaignStartTime = campaignStartTime;
    }

    public int createCampaign(ICampaignPersistent campaignPersistent, String templateId, String userSegmentId) {
        return campaignPersistent.save(this, templateId, userSegmentId);
    }

    private String generateId() {
        return "c-" + UUID.randomUUID();
    }

    public String getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(String campaignId) {
        this.campaignId = campaignId;
    }

    public String getCampaignName() {
        return campaignName;
    }

    public void setCampaignName(String campaignName) {
        this.campaignName = campaignName;
    }

    public String getCampaignStatus() {
        return campaignStatus;
    }

    public void setCampaignStatus(String campaignStatus) {
        this.campaignStatus = campaignStatus;
    }

    public Date getCampaignStartTime() {
        return campaignStartTime;
    }

    public void setCampaignStartTime(Date campaignStartTime) {
        this.campaignStartTime = campaignStartTime;
    }

    public CampaignAnalytics getAnalytics() {
        return analytics;
    }

    public void setAnalytics(CampaignAnalytics analytics) {
        this.analytics = analytics;
    }
}
