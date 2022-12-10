package com.ems.subscriberList.persistence;

import com.ems.companyDetails.Exception.DatabaseNotFound;
import com.ems.subscriberList.model.Subscriber;
import com.mysql.cj.protocol.Resultset;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class SubscriberDB implements ISubscriberPersistence{
    public Connection connection;
    public SubscriberDB(Connection conn)
    {
        this.connection = conn;
    }

    @Override
    public List<Subscriber> loadSubscriber(Subscriber subscriber) throws Exception {
        if(connection == null)
        {
            throw  new DatabaseNotFound();
        }
        String sql = "select * from subscriber_list";
        List<Subscriber> totalsubscriber = new ArrayList<>();
        try
        {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next())
            {
                Subscriber subscriberList = new Subscriber();
                subscriberList.sub_id = rs.getString("sub_id");
                subscriberList.sub_email = rs.getString("sub_email");
                subscriberList.sub_first_name = rs.getString("sub_first_name");
                subscriberList.sub_last_name = rs.getString("sub_last_name");
                subscriberList.sub_location = rs.getString("sub_location");
                subscriberList.subscription_date = rs.getString("subscription_date");
                subscriberList.sub_status = rs.getString("sub_status");

                totalsubscriber.add(subscriberList);

            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return totalsubscriber;
    }

    @Override
    public int saveSubscriber(Subscriber saveSubscriber) throws Exception {
        try
        {
            Statement statement = connection.createStatement();
            String saveSubscriberQuery = "INSERT INTO subscriber_list VALUES (" +
                    "\"" + saveSubscriber.getSub_id() + "\", " +
                    "\"" + saveSubscriber.getSub_email() + "\", " +
                    "\"" +saveSubscriber.getSub_first_name() + "\", " +
                    "\"" +saveSubscriber.getSub_last_name() + "\", " +
                    "\"" +saveSubscriber.getSub_location() + "\", " +
                    "\"" +saveSubscriber.getSubscription_date() + "\", " +
                    "\"" +saveSubscriber.getSub_status() + "\")";
            int numOfRawsInserted = statement.executeUpdate(saveSubscriberQuery);
            return numOfRawsInserted;

        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return -1;
        }
    }
}
