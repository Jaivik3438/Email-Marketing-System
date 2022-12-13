package com.ems.emailAnalytics.buisness;

import com.ems.DbConnection.MySqlPersistenceConnection;
import com.ems.bulkEmail.buisness.EmailDetails;
import com.ems.bulkEmail.buisness.SimpleEmailDetails;
import com.ems.bulkEmail.persistence.EmailDetailsDb;
import com.ems.bulkEmail.persistence.IEmailDetailsPersistence;

import java.sql.SQLException;

public class ClickAnalytics implements Analytics{
    @Override
    public void performAnalytics(String clickId, EmailDetails emailDetails, IEmailDetailsPersistence emailDetailsPersistence)  {
        try {
            EmailDetails fetchedEmailDetail=emailDetails.loadEmailDetailsByClickId(emailDetailsPersistence,clickId);
            fetchedEmailDetail.numberOfTimesClicked=fetchedEmailDetail.numberOfTimesClicked+1;
            fetchedEmailDetail.saveEmailDetails(new EmailDetailsDb(MySqlPersistenceConnection.getInstance().getConnection()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
