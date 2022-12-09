package com.ems.campaign.persistent;

import com.ems.campaign.model.Campaign;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

public class CampaignDb implements ICampaignPersistent {
    private Connection connection;

    public CampaignDb(Connection connection) {
        this.connection = connection;
    }

    @Override
    public int save(Campaign campaign, String templateId, String userSegmentId) {
        try {
            Statement statement = connection.createStatement();
            String insertCampaignQuery = "INSERT INTO campaign VALUES (" +
                    "\"" + campaign.getCampaignId() + "\", " +
                    "\"" + campaign.getCampaignName() + "\", " +
                    "\"" + campaign.getCampaignStatus() + "\", " +
                    "\"" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(campaign.getCampaignStartTime()) + "\", " +
                    "\"" + campaign.getAnalytics().getConversionRate() + "\", " +
                    "\"" + campaign.getAnalytics().getUnsubscribeRate() + "\", " +
                    "\"" + campaign.getAnalytics().getClickThroughRate() + "\", " +
                    "\"" + userSegmentId + "\", " +
                    "\"" + templateId + "\")";
            return statement.executeUpdate(insertCampaignQuery);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
