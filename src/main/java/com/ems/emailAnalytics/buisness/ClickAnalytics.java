package com.ems.emailAnalytics.buisness;

import com.ems.DbConnection.MySqlPersistenceConnection;
import com.ems.bulkEmail.model.EmailDetails;
import com.ems.bulkEmail.persistence.EmailDetailsDb;

import java.sql.SQLException;

public class ClickAnalytics implements Analytics{
    @Override
    public void performAnalytics(String clickId)  {
        EmailDetails emailDetails=new EmailDetails();
        try {
            EmailDetails fetchedEmailDetail=emailDetails.loadEmailDetailsByClickId(new EmailDetailsDb(MySqlPersistenceConnection.getInstance().getConnection()),clickId);
            fetchedEmailDetail.numberOfTimesClicked=fetchedEmailDetail.numberOfTimesClicked+1;
            fetchedEmailDetail.saveEmailDetails(new EmailDetailsDb(MySqlPersistenceConnection.getInstance().getConnection()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
