package com.ems.campaign.model;

public class CampaignAnalytics {
    private Double conversionRate;
    private Double unsubscribeRate;
    private Double clickThroughRate;

    public CampaignAnalytics() {
        conversionRate = 0.0;
        unsubscribeRate = 0.0;
        clickThroughRate = 0.0;
    }

    public CampaignAnalytics(Double conversionRate, Double unsubscribeRate, Double clickThroughRate) {
        this.conversionRate = conversionRate;
        this.unsubscribeRate = unsubscribeRate;
        this.clickThroughRate = clickThroughRate;
    }

    public Double getConversionRate() {
        return conversionRate;
    }

    public void setConversionRate(Double conversionRate) {
        this.conversionRate = conversionRate;
    }

    public Double getUnsubscribeRate() {
        return unsubscribeRate;
    }

    public void setUnsubscribeRate(Double unsubscribeRate) {
        this.unsubscribeRate = unsubscribeRate;
    }

    public Double getClickThroughRate() {
        return clickThroughRate;
    }

    public void setClickThroughRate(Double clickThroughRate) {
        this.clickThroughRate = clickThroughRate;
    }
}
