package com.ems.campaign.controller;

import com.ems.DbConnection.MySqlPersistenceConnection;
import com.ems.bulkEmail.buisness.*;
import com.ems.bulkEmail.persistence.EmailDetailsDb;
import com.ems.bulkEmail.persistence.IEmailDetailsPersistence;
import com.ems.campaign.model.*;
import com.ems.campaign.persistent.CampaignDb;
import com.ems.campaign.persistent.ICampaignPersistent;
import com.ems.configuration.buisness.Configuration;
import com.ems.configuration.buisness.ConfigurationFromJSON;
import com.ems.response_generator.IResponseGeneratorFactory;
import com.ems.response_generator.JsonResponseGeneratorFactory;
import com.ems.response_generator.ResponseGenerator;
import com.ems.subscriberList.model.SimpleSubscriberList;
import com.ems.subscriberList.model.SubscriberList;
import com.ems.subscriberList.persistence.ISubscriberPersistence;
import com.ems.subscriberList.persistence.SubscriberDB;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CampaignController {
    private ICampaignPersistent campaignPersistent;

    public CampaignController() {
        campaignPersistent = new CampaignDb(getConnectionObject());
    }

    private Connection  getConnectionObject() {
        Connection connection = null;
        try {
            connection = MySqlPersistenceConnection.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    @RequestMapping(value = "/campaign", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createNewCampaign(@RequestBody JsonNode body, HttpServletResponse response) {
        ICampaignFactory campaignFactory = new CampaignFactory();
        String campaignName = body.get("name").asText();
        String campaignStartTime = body.get("startTime").asText();
        String templateId = body.get("templateId").asText();
        String userSegmentId = body.get("userSegmentId").asText();

        System.out.println("Campaign Name : " + campaignName);
        System.out.println("Campaign Start Time : " + campaignStartTime);
        System.out.println("Template Id : " + templateId);

        Timestamp timestamp = new Timestamp(Long.parseLong(campaignStartTime));
        System.out.println("Timestamp : " + timestamp);
        Date date = new Date(timestamp.getTime());
        System.out.println(date);

        Campaign campaign = campaignFactory.createCampaign(campaignName, date);

        CampaignEmailScheduler scheduler = new CampaignEmailScheduler(campaign);

        int numOfRowsUpdated = campaign.createNewCampaign(campaignPersistent, templateId, userSegmentId);
        Campaign c = new CampaignFetcher(campaignPersistent).fetchCampaign(campaign.getCampaignId());

        SubscriberList subscriberList = new SimpleSubscriberList();
        IBulkEmailFactory bulkEmailFactory = new BulkEmailFactory();
        Configuration config= ConfigurationFromJSON.getInstance();
        String smtpEmail=config.getConfigurationByKey(config.getEnvironmentFromConfiguration(),"smtpEmail");
        String smtpPassword=config.getConfigurationByKey(config.getEnvironmentFromConfiguration(),"smtpPassword");
        ISendEmail emailSMTP = new Gmail(smtpEmail,smtpPassword);

        IEmailDetailsPersistence emailDetailsPersistence=new EmailDetailsDb(getConnectionObject());
        ISubscriberPersistence subscriberPersistence = new SubscriberDB(getConnectionObject());


        BulkEmail bulkEmail = bulkEmailFactory.createBulkEmail(c, subscriberList, emailSMTP,emailDetailsPersistence,subscriberPersistence);

        scheduler.attach(bulkEmail);
        scheduler.scheduleEmailSender();

        try {
            response.sendRedirect("/campaigns");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//        return numOfRowsUpdated;
    }

    @GetMapping("/campaign")
    public JsonNode getAllCampaigns() {
        CampaignFetcher fetcher = new CampaignFetcher(campaignPersistent);
        List<Campaign> data = fetcher.fetchAllCampaigns();

        IResponseGeneratorFactory generatorFactory = new JsonResponseGeneratorFactory();
        ResponseGenerator<JsonNode> responseGenerator = generatorFactory.createResponseGenerator(HttpStatus.OK, data);
        return responseGenerator.sendResponse();
    }

    @GetMapping("/campaign/{id}")
    public JsonNode getCampaign(@PathVariable String id) {
        CampaignFetcher fetcher = new CampaignFetcher(campaignPersistent);
        Campaign data = fetcher.fetchCampaign(id);

        IResponseGeneratorFactory generatorFactory = new JsonResponseGeneratorFactory();
        ResponseGenerator<JsonNode> responseGenerator = generatorFactory.createResponseGenerator(HttpStatus.OK, data);
        return responseGenerator.sendResponse();
    }

    @PutMapping("/campaign/{id}")
    public JsonNode updateCampaign(@PathVariable String id, @RequestBody JsonNode body) {
        CampaignFactory campaignFactory = new CampaignFactory();
        String campaignName = body.get("name").asText();
        String campaignStartTime = body.get("startTime").asText();

        Campaign campaign = campaignFactory.createCampaign();
        campaign.setCampaignName(campaignName);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = simpleDateFormat.parse(campaignStartTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        campaign.setCampaignStartTime(date);

        CampaignManipulator manipulator = new CampaignManipulator(campaignPersistent);
        int data = manipulator.updateCampaign(id, campaign);

        IResponseGeneratorFactory generatorFactory = new JsonResponseGeneratorFactory();
        ResponseGenerator<JsonNode> responseGenerator = generatorFactory.createResponseGenerator(HttpStatus.OK, data);
        responseGenerator.setDataLabel("rows_updated");
        return responseGenerator.sendResponse();
    }

    @DeleteMapping("/campaign/{id}")
    public JsonNode deleteCampaign(@PathVariable String id) {
        CampaignManipulator manipulator = new CampaignManipulator(campaignPersistent);
        int data = manipulator.deleteCampaign(id);

        IResponseGeneratorFactory generatorFactory = new JsonResponseGeneratorFactory();
        ResponseGenerator<JsonNode> responseGenerator = generatorFactory.createResponseGenerator(HttpStatus.OK, data);
        responseGenerator.setDataLabel("rows_deleted");
        return responseGenerator.sendResponse();
    }
}
