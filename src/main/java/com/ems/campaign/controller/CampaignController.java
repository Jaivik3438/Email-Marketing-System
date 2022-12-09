package com.ems.campaign.controller;

import com.ems.DbConnection.MySqlPersistenceConnection;
import com.ems.campaign.model.Campaign;
import com.ems.campaign.model.CampaignEmailScheduler;
import com.ems.campaign.persistent.CampaignDb;
import com.ems.campaign.persistent.ICampaignPersistent;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

@RestController
@RequestMapping("/api")
public class CampaignController {

    @PostMapping("/campaign")
    public int createNewCampaign(HttpServletRequest request, @RequestBody JsonNode body) throws ParseException, SQLException {
        ICampaignPersistent campaignPersistent = new CampaignDb(MySqlPersistenceConnection.getInstance().getConnection());
        String campaignName = body.get("name").asText();
        String campaignStartTime = body.get("startTime").asText();
        String templateId = body.get("templateId").asText();
        String userSegmentId = body.get("userSegmentId").asText();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(campaignStartTime);
        Campaign campaign = new Campaign(campaignName, date);

        int numOfRowsUpdated = campaign.createCampaign(campaignPersistent, templateId, userSegmentId);
        CampaignEmailScheduler scheduler = new CampaignEmailScheduler(campaign);
        scheduler.scheduleEmailSender();

        return numOfRowsUpdated;
    }
}
