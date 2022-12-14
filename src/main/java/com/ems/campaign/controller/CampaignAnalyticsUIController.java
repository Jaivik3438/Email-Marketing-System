package com.ems.campaign.controller;

import com.ems.DbConnection.MySqlPersistenceConnection;
import com.ems.bulkEmail.persistence.EmailDetailsDb;
import com.ems.bulkEmail.persistence.IEmailDetailsPersistence;
import com.ems.campaign.model.CampaignAnalytics;
import com.ems.campaign.persistent.CampaignDb;
import com.ems.campaign.persistent.ICampaignPersistent;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;

@Controller()
public class CampaignAnalyticsUIController {
    @GetMapping("/campaigns/{id}")
    public String getCampaignAnalytics(Model model, @PathVariable String id){
        String campaignId = id;
        try{
            IEmailDetailsPersistence emailDetailsPersistence = new EmailDetailsDb(MySqlPersistenceConnection.getInstance().getConnection());
            CampaignAnalytics campaignAnalytics = new CampaignAnalytics().getCampaignAnalytics(emailDetailsPersistence, campaignId);
            model.addAttribute("campaignAnalytics", campaignAnalytics);
            return "campaignAnalytics";
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
