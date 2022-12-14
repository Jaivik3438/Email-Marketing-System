package com.ems.emailAnalytics.buisness;

import com.ems.DbConnection.MySqlPersistenceConnection;
import com.ems.bulkEmail.buisness.EmailDetails;
import com.ems.bulkEmail.buisness.SimpleEmailDetails;
import com.ems.bulkEmail.persistence.EmailDetailsDb;
import com.ems.bulkEmail.persistence.IEmailDetailsPersistence;

import java.sql.SQLException;

public class ClickAnalytics implements Analytics{
    @Override
    public boolean performAnalytics(String clickId, EmailDetails emailDetails, IEmailDetailsPersistence emailDetailsPersistence)  {

            EmailDetails fetchedEmailDetail=emailDetails.loadEmailDetailsByClickId(emailDetailsPersistence,clickId);
            if(fetchedEmailDetail==null){
                return false;
            }
            fetchedEmailDetail.numberOfTimesClicked=fetchedEmailDetail.numberOfTimesClicked+1;
            boolean  result=fetchedEmailDetail.saveEmailDetails(emailDetailsPersistence);
            return result;
    }
}
