package com.ems.bulkEmail.persistence;

import com.ems.bulkEmail.buisness.EmailDetails;

public interface IEmailDetailsPersistence {
    public EmailDetails loadEmailDetailsByCampaign(String campaignId);
    public boolean saveEmailDetails(EmailDetails emailDetails);

    public boolean createEmailDetails(EmailDetails emailDetails);

    public EmailDetails loadEmailDetailsByPixelId(String pixelId);

    public EmailDetails loadEmailDetailsByClickId(String clickId);
}
