package com.ems.emailAnalytics.buisness;

import com.ems.DbConnection.MySqlPersistenceConnection;
import com.ems.bulkEmail.buisness.EmailDetails;
import com.ems.bulkEmail.buisness.SimpleEmailDetails;
import com.ems.bulkEmail.persistence.EmailDetailsDb;
import com.ems.bulkEmail.persistence.IEmailDetailsPersistence;

import java.util.Date;

public class PixelAnalytics implements Analytics {

    @Override
    public void performAnalytics(String pixelId, EmailDetails emailDetails, IEmailDetailsPersistence emailDetailsPersistence) {

        EmailDetails fetchedEmailDetail;
        try {
            fetchedEmailDetail=emailDetails.loadEmailDetailsByPixelId(emailDetailsPersistence,pixelId);
            fetchedEmailDetail.openedTime=new Date();
            fetchedEmailDetail.numberOfTimesOpened=fetchedEmailDetail.numberOfTimesOpened+1;
            fetchedEmailDetail.saveEmailDetails(emailDetailsPersistence);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
