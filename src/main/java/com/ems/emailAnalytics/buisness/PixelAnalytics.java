package com.ems.emailAnalytics.buisness;

import com.ems.DbConnection.MySqlPersistenceConnection;
import com.ems.bulkEmail.buisness.EmailDetails;
import com.ems.bulkEmail.buisness.SimpleEmailDetails;
import com.ems.bulkEmail.persistence.EmailDetailsDb;

import java.util.Date;

public class PixelAnalytics implements Analytics {

    @Override
    public void performAnalytics(String pixelId) {
        EmailDetails emailDetails=new SimpleEmailDetails();
        EmailDetails fetchedEmailDetail;
        try {
            fetchedEmailDetail=emailDetails.loadEmailDetailsByPixelId(new EmailDetailsDb(MySqlPersistenceConnection.getInstance().getConnection()),pixelId);
            fetchedEmailDetail.openedTime=new Date();
            fetchedEmailDetail.numberOfTimesOpened=fetchedEmailDetail.numberOfTimesOpened+1;
            fetchedEmailDetail.saveEmailDetails(new EmailDetailsDb(MySqlPersistenceConnection.getInstance().getConnection()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
