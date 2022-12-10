package com.ems.bulkEmail.model;

import com.ems.campaign.model.Campaign;
import com.ems.campaign.model.IObserver;
import com.ems.campaign.model.Subject;

public class BulkEmail implements IObserver {
    Campaign campaign;
    public BulkEmail(Campaign campaign){
        this.campaign=campaign;
    }

    @Override
    public void update(Subject s) {

    }
}
