package com.ems.emailAnalytics.buisness;

import com.ems.DbConnection.MySqlPersistenceConnection;
import com.ems.bulkEmail.buisness.EmailDetails;
import com.ems.bulkEmail.buisness.SimpleEmailDetails;
import com.ems.bulkEmail.persistence.EmailDetailsDb;
import com.ems.bulkEmail.persistence.IEmailDetailsPersistence;

import java.util.Date;

public class PixelAnalytics implements Analytics {

    @Override
    public boolean performAnalytics(String pixelId, EmailDetails emailDetails, IEmailDetailsPersistence emailDetailsPersistence) {

        EmailDetails fetchedEmailDetail;
        fetchedEmailDetail=emailDetails.loadEmailDetailsByPixelId(emailDetailsPersistence,pixelId);
        if (fetchedEmailDetail==null){
            return false;
        }
        fetchedEmailDetail.openedTime=new Date();
        fetchedEmailDetail.numberOfTimesOpened=fetchedEmailDetail.numberOfTimesOpened+1;
        boolean result =fetchedEmailDetail.saveEmailDetails(emailDetailsPersistence);
        return true;

    }
}
