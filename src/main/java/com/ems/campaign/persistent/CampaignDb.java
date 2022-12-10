package com.ems.campaign.persistent;

import com.ems.campaign.model.Campaign;
import com.ems.campaign.model.CampaignFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class CampaignDb implements ICampaignPersistent {
    private Connection connection;

    private final String CAMPAIGN_ID = "campaign_id";
    private final String CAMPAIGN_NAME = "campaign_name";
    private final String CAMPAIGN_STATUS = "campaign_status";
    private final String CAMPAIGN_START_TIME = "campaign_starttime";
    private final String CONVERSION_RATE = "conversion_rate";
    private final String UNSUBSCRIBE_RATE = "unsubscribe_rate";
    private final String CTR_RATE = "ctr_rate";

    public CampaignDb(Connection connection) {
        this.connection = connection;
    }

    private ResultSet load(String query) throws SQLException {
        Statement statement = connection.createStatement();
        return statement.executeQuery(query);
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
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public List<Campaign> loadAllCampaign() {
        List<Campaign> campaigns = new ArrayList<>();
        CampaignFactory campaignFactory = new CampaignFactory();
        String selectAllCampaignQuery = "SELECT * FROM campaign";
        Campaign campaign;
        try {
            ResultSet result = load(selectAllCampaignQuery);
            while (result.next()) {
                String campaignId = result.getString(CAMPAIGN_ID);
                String campaignName = result.getString(CAMPAIGN_NAME);
                String campaignStatus = result.getString(CAMPAIGN_STATUS);
                String campaignStartTime = result.getString(CAMPAIGN_START_TIME);
                String conversionRate = result.getString(CONVERSION_RATE);
                String unsubscribeRate = result.getString(UNSUBSCRIBE_RATE);
                String ctrRate = result.getString(CTR_RATE);

                campaign = campaignFactory.createCampaign();
                campaign.setCampaignId(campaignId);
                campaign.setCampaignName(campaignName);
                campaign.setCampaignStatus(campaignStatus);
                campaign.setCampaignStartTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(campaignStartTime));
                campaign.getAnalytics().setConversionRate(Double.parseDouble(conversionRate));
                campaign.getAnalytics().setUnsubscribeRate(Double.parseDouble(unsubscribeRate));
                campaign.getAnalytics().setClickThroughRate(Double.parseDouble(ctrRate));

                campaigns.add(campaign);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return campaigns;
    }

    @Override
    public Campaign loadCampaign(String campaignId) {
        CampaignFactory campaignFactory = new CampaignFactory();
        Campaign campaign = null;
        try {
            String selectCampaignQuery = "SELECT * FROM campaign WHERE campaign_id = \"" + campaignId + "\"";
            ResultSet result = load(selectCampaignQuery);
            while (result.next()) {
                campaign = campaignFactory.createCampaign();
                campaign.setCampaignId(result.getString(CAMPAIGN_ID));
                campaign.setCampaignName(result.getString(CAMPAIGN_NAME));
                campaign.setCampaignStatus(result.getString(CAMPAIGN_STATUS));
                campaign.setCampaignStartTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(result.getString(CAMPAIGN_START_TIME)));
                campaign.getAnalytics().setConversionRate(Double.parseDouble(result.getString(CONVERSION_RATE)));
                campaign.getAnalytics().setUnsubscribeRate(Double.parseDouble(result.getString(UNSUBSCRIBE_RATE)));
                campaign.getAnalytics().setClickThroughRate(Double.parseDouble(result.getString(CTR_RATE)));
            }
            return campaign;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int update(String campaignId, Campaign updatedCampaign) {
        try {
            Statement statement = connection.createStatement();
            String updateCampaignQuery = "UPDATE campaign SET " +
                    CAMPAIGN_NAME + " = \"" + updatedCampaign.getCampaignName() + "\", " +
                    CAMPAIGN_START_TIME + " = \"" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(updatedCampaign.getCampaignStartTime()) + "\" " +
                    "WHERE " + CAMPAIGN_ID + " = \"" + campaignId + "\"";
            System.out.println(updateCampaignQuery);
            return statement.executeUpdate(updateCampaignQuery);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public int delete(String campaignId) {
        try {
            Statement statement = connection.createStatement();
            String deleteCampaignQuery = "DELETE FROM campaign WHERE " + CAMPAIGN_ID + " = \"" + campaignId + "\"";
            return statement.executeUpdate(deleteCampaignQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
