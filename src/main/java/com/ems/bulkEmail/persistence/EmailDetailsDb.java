package com.ems.bulkEmail.persistence;

import com.ems.authentication.exception.DatabaseNotFound;
import com.ems.authentication.model.Company;
import com.ems.authentication.model.Role;
import com.ems.authentication.model.User;
import com.ems.bulkEmail.model.EmailDetails;
import com.ems.bulkEmail.model.Mail;
import com.ems.bulkEmail.model.SimpleEmail;
import com.ems.subscriberList.model.Subscriber;
import org.springframework.format.datetime.DateFormatter;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class EmailDetailsDb implements IEmailDetailsPersistence{
    public Connection connection;
    public EmailDetailsDb(Connection conn){
        this.connection=conn;
    }
    @Override
    public EmailDetails loadEmailDetailsByCampaign(String campaignId) {
        return new EmailDetails();
    }

    @Override
    public boolean saveEmailDetails(EmailDetails emailDetails) {
        try {
            Statement statement = connection.createStatement();
            String formatSentTime=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(emailDetails.sentTime);
            String formatOpenTime;
            if (null==emailDetails.openedTime){
                formatOpenTime="";
            }else{
                formatOpenTime=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(emailDetails.openedTime);
            }
            String updateEmailQuery = "UPDATE  mail SET" +
                    "`pixel_id` = '"+ emailDetails.mail.pixelId+ "'," +
                    "`ctr_id` = '"+ emailDetails.mail.clickId+ "'," +
                    "`sent_time` '"+ formatSentTime + "'," +
                    "`open_time` '"+ formatOpenTime + "'," +
                    "`sub_id` = '"+ emailDetails.subscriber.sub_id+ "'," +
                    "`campaign_id` '"+ emailDetails.campaignId+ "'," +
                    "WHERE `mail_id` '"+ emailDetails.id+ "';";
            return statement.executeUpdate(updateEmailQuery)>0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean createEmailDetails(EmailDetails emailDetails) {
        try {
            String formatSentTime=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(emailDetails.sentTime);
            String createEmailQuery = "INSERT INTO `mail`(`mail_id`,`pixel_id`,`ctr_id`,`open_time`,`sub_id`,`campaign_id`)VALUES(?,?,?,?,?,?);";
            PreparedStatement ps =connection.prepareStatement(createEmailQuery);
            ps.setString(1,emailDetails.id);
            ps.setString(2,emailDetails.mail.pixelId);
            ps.setString(3,emailDetails.mail.clickId);
            ps.setString(4,formatSentTime);
            ps.setString(5,emailDetails.subscriber.getSub_id());
            ps.setString(6,emailDetails.campaignId);
            return ps.executeUpdate(createEmailQuery)>0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public EmailDetails loadEmailDetailsByPixelId(String pixelId) {

        String sql="select * from mail,subscriber_list,where mail.sub_id=subscriber_list.sub_id user.pixelId=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, pixelId);

            ResultSet rs = stmt.executeQuery();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            while (rs.next()){
                EmailDetails  emailDetails=new EmailDetails();
                emailDetails.id=rs.getString("mail_id");
                emailDetails.campaignId=rs.getString("campaign_id");
                String sentTime=rs.getString("sent_time");
                if (sentTime==""){
                    emailDetails.sentTime=null;
                }
                else{
                    emailDetails.sentTime=simpleDateFormat.parse(sentTime);
                }
                String openedTime=rs.getString("open_time");
                if (openedTime==""){
                    emailDetails.openedTime=null;
                }else{
                    emailDetails.openedTime=simpleDateFormat.parse(openedTime);
                }

                Subscriber  subscriber= new Subscriber();
                subscriber.sub_id=rs.getString("sub_id");
                emailDetails.subscriber=subscriber;
                Mail mail= new SimpleEmail();
                mail.pixelId=rs.getString("pixel_id");
                mail.clickId=rs.getString("ctr_id");
                emailDetails.mail=mail;

                return emailDetails;
            }
            return  null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (ParseException e) {
             e.printStackTrace();
             return null;
        }
    }

    @Override
    public EmailDetails loadEmailDetailsByClickId(String clickId) {
        String sql="select * from mail,subscriber_list,where mail.sub_id=subscriber_list.sub_id user.ctr-id=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, clickId);

            ResultSet rs = stmt.executeQuery();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            while (rs.next()){
                EmailDetails  emailDetails=new EmailDetails();
                emailDetails.id=rs.getString("mail_id");
                emailDetails.campaignId=rs.getString("campaign_id");
                String sentTime=rs.getString("sent_time");
                if (sentTime==""){
                    emailDetails.sentTime=null;
                }
                else{
                    emailDetails.sentTime=simpleDateFormat.parse(sentTime);
                }
                String openedTime=rs.getString("open_time");
                if (openedTime==""){
                    emailDetails.openedTime=null;
                }else{
                    emailDetails.openedTime=simpleDateFormat.parse(openedTime);
                }

                Subscriber  subscriber= new Subscriber();
                subscriber.sub_id=rs.getString("sub_id");
                emailDetails.subscriber=subscriber;
                Mail mail= new SimpleEmail();
                mail.pixelId=rs.getString("pixel_id");
                mail.clickId=rs.getString("ctr_id");
                emailDetails.mail=mail;

                return emailDetails;
            }
            return  null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }


}
