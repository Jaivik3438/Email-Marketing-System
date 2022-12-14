package com.ems.campaign.persistent;

import com.ems.bulkEmail.buisness.EmailDetails;
import com.ems.campaign.model.Campaign;
import com.ems.campaign.model.CampaignFactory;
import com.ems.campaign.model.ICampaignFactory;
import com.ems.subscriberList.model.Subscriber;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ListIterator;

public class CampaignDbMock implements ICampaignPersistent {
    List<Campaign> dbMock = new ArrayList<>();
    ICampaignFactory campaignFactory = new CampaignFactory();

    public CampaignDbMock() {
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Campaign c1 = campaignFactory.createCampaign("Black Friday Campaign", dateFormatter.parse("2022-12-01 12:50:00"));
            Campaign c2 = campaignFactory.createCampaign("Summer Sale Campaign", dateFormatter.parse("2022-12-02 11:30:34"));
            Campaign c3 = campaignFactory.createCampaign("Spring Boot Course Campaign", dateFormatter.parse("2022-09-26 06:34:00"));
            dbMock.add(c1);
            dbMock.add(c2);
            dbMock.add(c3);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int save(Campaign campaign, String templateId, String userSegmentId) {
        return 0;
    }

    @Override
    public List<Campaign> loadAllCampaign() {
        return dbMock;
    }

    @Override
    public Campaign loadCampaign(String campaignId) {
        ListIterator<Campaign> iterator = dbMock.listIterator();
        while (iterator.hasNext()) {
            Campaign currentCampaign = iterator.next();
            if (currentCampaign.getCampaignId().equals(campaignId)) {
                return currentCampaign;
            }
        }
        return null;
    }

    @Override
    public List<Campaign> loadCampaignByUserId(String userId) {
        return null;
    }

    @Override
    public int update(String campaignId, Campaign campaignToUpdate) {
        return 0;
    }

    @Override
    public int delete(String campaignId) {
        return 0;
    }

    @Override
    public List<EmailDetails> getAllEmailDetailsOfCampaign(String campaignId) {
        return null;
    }

    @Override
    public Subscriber getSubscriberById(String subscriberId) {
        return null;
    }
}
