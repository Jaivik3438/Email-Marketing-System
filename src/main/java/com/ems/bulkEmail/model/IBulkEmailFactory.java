package com.ems.bulkEmail.model;

import com.ems.campaign.model.Campaign;

public interface IBulkEmailFactory {
    public BulkEmail CreateBulkEmail(Campaign campaign);
}
