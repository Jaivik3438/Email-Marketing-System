package com.ems.bulkEmail.persistence;

import com.ems.bulkEmail.buisness.EmailDetails;

import java.util.List;

public interface IEmailDetailsPersistence {

    public boolean saveEmailDetails(EmailDetails emailDetails);

    public boolean createEmailDetails(EmailDetails emailDetails);

    public EmailDetails loadEmailDetailsByPixelId(String pixelId);

    public EmailDetails loadEmailDetailsByClickId(String clickId);

    List<EmailDetails> getAllEmailDetailsOfCampaign(String campaignId);
}
