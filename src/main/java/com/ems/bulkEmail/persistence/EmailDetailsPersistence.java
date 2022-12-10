package com.ems.bulkEmail.persistence;

import com.ems.bulkEmail.model.EmailDetails;

public class EmailDetailsPersistence implements IEmailDetailsPersistence{
    @Override
    public EmailDetails getEmailDetailsByCampaign(String campaignId) {
        return new EmailDetails();
    }

    @Override
    public boolean setEmailDetails(EmailDetails emailDetails) {
        return false;
    }


}
