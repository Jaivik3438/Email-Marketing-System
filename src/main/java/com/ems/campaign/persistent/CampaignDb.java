package com.ems.campaign.persistent;

import com.ems.bulkEmail.buisness.EmailDetails;
import com.ems.bulkEmail.buisness.SimpleEmailDetails;
import com.ems.campaign.model.Campaign;
import com.ems.campaign.model.CampaignFactory;
import com.ems.campaign.model.ICampaignFactory;
import com.ems.email_template.model.EmailTemplate;
import com.ems.email_template.model.EmailTemplateFactory;
import com.ems.email_template.model.ITemplateFactory;
import com.ems.email_template.model.Template;
import com.ems.email_template.persistent.EmailTemplateDb;
import com.ems.email_template.persistent.ITemplatePersistent;
import com.ems.subscriberList.model.Subscriber;
import com.ems.userSegment.model.UserSegment;

import java.sql.*;
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
    private final String USER_SEGMENT_ID = "user_segment_id";
    private final String TEMPLATE_ID = "template_id";

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
            // TODO: Check if template exists with the given id
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
                String userSegmentId = result.getString(USER_SEGMENT_ID);
                String templateId = result.getString(TEMPLATE_ID);

                ITemplatePersistent templatePersistent = new EmailTemplateDb(connection);
                EmailTemplate template = (EmailTemplate) templatePersistent.loadTemplateById(templateId);

                campaign = campaignFactory.createCampaign();
                campaign.setCampaignId(campaignId);
                campaign.setCampaignName(campaignName);
                campaign.setCampaignStatus(campaignStatus);
                campaign.setCampaignStartTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(campaignStartTime));
                campaign.getAnalytics().setConversionRate(Double.parseDouble(conversionRate));
                campaign.getAnalytics().setUnsubscribeRate(Double.parseDouble(unsubscribeRate));
                campaign.getAnalytics().setClickThroughRate(Double.parseDouble(ctrRate));
                campaign.setUserSegmentId(userSegmentId);
                campaign.setEmailTemplate(template);

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
                campaign.setUserSegmentId(result.getString(USER_SEGMENT_ID));

                ITemplatePersistent templatePersistent = new EmailTemplateDb(connection);
                EmailTemplate template = (EmailTemplate) templatePersistent.loadTemplateById(result.getString(TEMPLATE_ID));

                campaign.setEmailTemplate(template);
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

    @Override
    public List<EmailDetails> getAllEmailDetailsOfCampaign(String campaignId) {
        if (connection != null) {
            String sql = "select * from mail where campaign_id= \"" + campaignId + "\"";
            try {
                List<EmailDetails> emailDetailsList = new ArrayList<>();
                PreparedStatement stmt = connection.prepareStatement(sql);
//                stmt.setString(1, campaignId);

                ResultSet rs = stmt.executeQuery(sql);

                while (rs.next()) {
                    EmailDetails emailDetails = new SimpleEmailDetails();

                    String subscriberId = rs.getString("sub_id");
                    if(subscriberId != null){
                        Subscriber subscriber = getSubscriberById(subscriberId);
                        if(subscriber != null){
                            emailDetails.subscriber = subscriber;
                        }
                    }
                    emailDetails.openedTime = rs.getTimestamp("open_time");
                    emailDetails.sentTime = rs.getTimestamp("sent_time");
                    emailDetails.numberOfTimesClicked = rs.getInt("number_of_times_clicked");
                    emailDetails.numberOfTimesOpened = rs.getInt("number_of_times_opened");

                    emailDetailsList.add(emailDetails);
                }

                return emailDetailsList;

            } catch (Exception exception) {
                exception.printStackTrace();
                return null;
            }
        }
        return null;
    }

    @Override
    public Subscriber getSubscriberById(String subscriberId) {
        if (connection != null) {
            String sql = "select * from subscriber_list where sub_id= \"" + subscriberId + "\"";
            try {
                Subscriber subscriber = new Subscriber();
                PreparedStatement stmt = connection.prepareStatement(sql);
//                stmt.setString(1, subscriberId);

                ResultSet rs = stmt.executeQuery(sql);

                while (rs.next()) {
                    subscriber.sub_id = rs.getString("sub_id");
                    subscriber.sub_first_name = rs.getString("sub_first_name");
                    subscriber.sub_last_name = rs.getString("sub_last_name");
                    subscriber.sub_location = rs.getString("sub_location");
                    subscriber.sub_email = rs.getString("sub_email");
                    subscriber.subscription_date = rs.getString("subscription_date");
                    subscriber.sub_status = rs.getString("sub_status");
                }

                return subscriber;

            } catch (Exception exception) {
                exception.printStackTrace();
                return null;
            }
        }
        return null;
    }

    @Override
    public List<Campaign> loadCampaignByUserId(String userId) {
        List<Campaign> campaigns = new ArrayList<>();
        ICampaignFactory campaignFactory = new CampaignFactory();
        Campaign campaign = null;
        try {
            Statement statement = connection.createStatement();
            String selectCampaignByUserIdQuery = "SELECT c.* FROM campaign AS c " +
                    "INNER JOIN user_segment us ON c.user_segment_id = us.user_segment_id " +
                    "WHERE us.user_id = \"" + userId + "\"";
            System.out.println(selectCampaignByUserIdQuery);
            ResultSet result = statement.executeQuery(selectCampaignByUserIdQuery);
            while (result.next()) {
                ITemplatePersistent templatePersistent = new EmailTemplateDb(connection);
                EmailTemplate template = (EmailTemplate) templatePersistent.loadTemplateById(result.getString(TEMPLATE_ID));

                campaign = campaignFactory.createCampaign();
                campaign.setCampaignId(result.getString(CAMPAIGN_ID));
                campaign.setCampaignName(result.getString(CAMPAIGN_NAME));
                campaign.setCampaignStatus(result.getString(CAMPAIGN_STATUS));
                campaign.setCampaignStartTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(result.getString(CAMPAIGN_START_TIME)));
                campaign.getAnalytics().setConversionRate(result.getDouble(CONVERSION_RATE));
                campaign.getAnalytics().setUnsubscribeRate(result.getDouble(UNSUBSCRIBE_RATE));
                campaign.getAnalytics().setClickThroughRate(result.getDouble(CTR_RATE));

                campaign.setEmailTemplate(template);
                campaigns.add(campaign);
            }
            return campaigns;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
