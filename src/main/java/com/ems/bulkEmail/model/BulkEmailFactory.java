package com.ems.bulkEmail.model;

import com.ems.campaign.model.Campaign;

public class BulkEmailFactory implements IBulkEmailFactory{
    public BulkEmail CreateBulkEmail(Campaign campaign){
        return new BulkEmail(campaign);
    };
}
