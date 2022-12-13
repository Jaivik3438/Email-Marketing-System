package com.ems.campaign.controller;

import com.ems.DbConnection.MySqlPersistenceConnection;
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
            ICampaignPersistent campaignPersistent = new CampaignDb(MySqlPersistenceConnection.getInstance().getConnection());
            CampaignAnalytics campaignAnalytics = new CampaignAnalytics().getCampaignAnalytics(campaignPersistent, campaignId);
            System.out.println(campaignAnalytics.toString());
            model.addAttribute("campaignAnalytics", campaignAnalytics);
            return "campaignAnalytics";
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
