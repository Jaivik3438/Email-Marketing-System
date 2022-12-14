package com.ems.subscriberList.controller;

import com.ems.DbConnection.MySqlPersistenceConnection;
import com.ems.authentication.model.User;
import com.ems.subscriberList.model.SimpleSubscriberList;
import com.ems.subscriberList.model.Subscriber;
import com.ems.subscriberList.model.SubscriberList;
import com.ems.subscriberList.persistence.ISubscriberPersistence;
import com.ems.subscriberList.persistence.SubscriberDB;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/subscriber")
public class SubscriberListController {
    private Subscriber subscriberDetails = new Subscriber();
    private SimpleSubscriberList subscriberList = new SimpleSubscriberList();
    public Connection getConnectionObject() {
        Connection connection = null;
        try {
            connection = MySqlPersistenceConnection.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
    @GetMapping("/getsubscribers")
    public List<Subscriber> getSubscribers()
    {
        try
        {
            return subscriberDetails.loadSubscriber(new SubscriberDB(getConnectionObject()));

        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    @PostMapping("/savesubscriber")
    public String createSubscriberList( HttpServletRequest request,  HttpServletResponse response)
    {
         try
         {
             String Subscriber_email = request.getParameter("sub_email");
             String Subscriber_firstname = request.getParameter("sub_first_name");
             String Subscriber_lastname = request.getParameter("sub_last_name");
             String Subscriber_location =request.getParameter("sub_location");
             String Subscriber_date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
             String Subscriber_userSegmentId =request.getParameter("user_segment_id");

             SubscriberDB DatabaseObj = new SubscriberDB(getConnectionObject());
             Subscriber addSubscriber = new Subscriber(Subscriber_email,Subscriber_firstname,Subscriber_lastname,Subscriber_location,Subscriber_date);
             int responseCode = addSubscriber.saveSubscriber(DatabaseObj);
             int responseCodeforSubscriberidwithUserSegment =addSubscriber.saveSubcriberAndUserSegmentID(DatabaseObj,Subscriber_userSegmentId);
             if(responseCode != -1)
             {
                 response.sendRedirect("/subscriber-list");
             }
             else
             {
                 response.sendRedirect("/add-subscriber");
             }
             return "Success: " + addSubscriber.toString();

         }
         catch(Exception exception)
         {
          return "Exception" + exception.getMessage();
         }
    }
    @GetMapping("/getsubscribersbycampignid/{id}")
    public List<Subscriber> getSubscriberByCampaignId(@PathVariable String campaignId)
    {
        try
        {
            SubscriberDB DatabaseObj = new SubscriberDB(getConnectionObject());
            return subscriberList.getSubscriberByCampaignID(campaignId,DatabaseObj);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }

    }
    @GetMapping("/getsubscriberbyuserid")
    public List<Subscriber> getSubscriberByUserId(HttpSession session) throws Exception{
        User user = (User) session.getAttribute("user");
        SubscriberDB DatabaseObj = new SubscriberDB(getConnectionObject());
        return subscriberList.getSubscriberByUserID(user.userId, DatabaseObj);

    }

}
