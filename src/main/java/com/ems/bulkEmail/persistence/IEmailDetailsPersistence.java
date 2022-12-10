package com.ems.bulkEmail.persistence;

import com.ems.bulkEmail.model.EmailDetails;

public interface IEmailDetailsPersistence {
    public EmailDetails getEmailDetailsByCampaign(String campaignId);
    public boolean setEmailDetails(EmailDetails emailDetails);
}
