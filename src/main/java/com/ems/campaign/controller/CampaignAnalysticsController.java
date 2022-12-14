package com.ems.campaign.controller;

import com.ems.DbConnection.MySqlPersistenceConnection;
import com.ems.bulkEmail.buisness.EmailDetails;
import com.ems.bulkEmail.persistence.EmailDetailsDb;
import com.ems.bulkEmail.persistence.IEmailDetailsPersistence;
import com.ems.campaign.model.CampaignAnalytics;
import com.ems.campaign.model.CampaignManipulator;
import com.ems.campaign.persistent.CampaignDb;
import com.ems.campaign.persistent.ICampaignPersistent;
import com.ems.subscriberList.model.Subscriber;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController()
@RequestMapping("/api/analytics/campaign")
public class CampaignAnalysticsController {

    @GetMapping("/")
    public CampaignAnalytics getCampaignSubscribers(HttpServletRequest request){
            String campaignId = request.getParameter("campaign_id");

            try{
                IEmailDetailsPersistence emailDetailsPersistence = new EmailDetailsDb(MySqlPersistenceConnection.getInstance().getConnection());
                CampaignAnalytics campaignAnalytics = new CampaignAnalytics().getCampaignAnalytics(emailDetailsPersistence, campaignId);
                return campaignAnalytics;
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
    }
}
