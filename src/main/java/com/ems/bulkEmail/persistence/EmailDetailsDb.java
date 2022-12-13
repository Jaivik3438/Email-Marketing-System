package com.ems.bulkEmail.persistence;

import com.ems.bulkEmail.buisness.EmailDetails;
import com.ems.bulkEmail.buisness.Mail;
import com.ems.bulkEmail.buisness.SimpleEmail;
import com.ems.bulkEmail.buisness.SimpleEmailDetails;
import com.ems.subscriberList.model.Subscriber;

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
        return new SimpleEmailDetails();
    }

    @Override
    public boolean saveEmailDetails(EmailDetails emailDetails) {
        try {
            Statement statement = connection.createStatement();
            String formatSentTime=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(emailDetails.sentTime);
            String formatOpenTime;
            String updateEmailQuery="";
            if (null==emailDetails.openedTime){
                updateEmailQuery = "UPDATE  mail SET" +
                        "`pixel_id` = '"+ emailDetails.mail.pixelId+ "'," +
                        "`ctr_id` = '"+ emailDetails.mail.clickId+ "'," +
                        "`sent_time`= '"+ formatSentTime + "'," +
                        "`number_of_times_clicked`= '"+ emailDetails.numberOfTimesClicked + "'," +
                        "`number_of_times_opened`= '"+ emailDetails.numberOfTimesOpened + "'," +
                        "`sub_id` = '"+ emailDetails.subscriber.sub_id+ "'," +
                        "`campaign_id`= '"+ emailDetails.campaignId+ "'" +
                        "WHERE `mail_id`= '"+ emailDetails.id+ "';";

            }else{
                formatOpenTime=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(emailDetails.openedTime);
                updateEmailQuery = "UPDATE  mail SET" +
                        "`pixel_id` = '"+ emailDetails.mail.pixelId+ "'," +
                        "`ctr_id` = '"+ emailDetails.mail.clickId+ "'," +
                        "`sent_time`= '"+ formatSentTime + "'," +
                        "`open_time`= '"+ formatOpenTime + "'," +
                        "`number_of_times_clicked`= '"+ emailDetails.numberOfTimesClicked + "'," +
                        "`number_of_times_opened`= '"+ emailDetails.numberOfTimesOpened + "'," +
                        "`sub_id` = '"+ emailDetails.subscriber.sub_id+ "'," +
                        "`campaign_id`= '"+ emailDetails.campaignId+ "'" +
                        "WHERE `mail_id`= '"+ emailDetails.id+ "';";
            }
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
            String createEmailQuery = "INSERT INTO `mail`(`mail_id`,`pixel_id`,`ctr_id`,`sent_time`,`sub_id`,`campaign_id`) VALUES (" +
            "\"" + emailDetails.id + "\", " +
                    "\"" + emailDetails.mail.pixelId+ "\", " +
                    "\"" + emailDetails.mail.clickId + "\", " +
                    "\"" + formatSentTime + "\", " +
                    "\"" + emailDetails.subscriber.getSub_id()+ "\", " +
                    "\"" + emailDetails.campaignId + "\")";
            Statement stmt =connection.createStatement();

            return stmt.executeUpdate(createEmailQuery)>0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public EmailDetails loadEmailDetailsByPixelId(String pixelId) {

        String sql="select * from mail,subscriber_list where mail.sub_id=subscriber_list.sub_id and mail.pixel_id="+"\"" + pixelId+ "\"";
        try {
            Statement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery(sql);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            while (rs.next()){
                EmailDetails  emailDetails=setEmilDetailsFromResultSet(rs);

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
        String sql="select * from mail,subscriber_list where mail.sub_id=subscriber_list.sub_id and mail.ctr_id="+"\"" + clickId+ "\"";
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                EmailDetails  emailDetails=setEmilDetailsFromResultSet(rs);

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
    private EmailDetails setEmilDetailsFromResultSet(ResultSet rs) throws SQLException, ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        EmailDetails  emailDetails=new SimpleEmailDetails();
        emailDetails.id=rs.getString("mail_id");
        emailDetails.campaignId=rs.getString("campaign_id");
        String sentTime=rs.getString("sent_time");
        emailDetails.numberOfTimesOpened=rs.getInt("number_of_times_opened");
        emailDetails.numberOfTimesClicked=rs.getInt("number_of_times_clicked");
        if (sentTime==""){
            emailDetails.sentTime=null;
        }
        else{
            emailDetails.sentTime=simpleDateFormat.parse(sentTime);
        }
        String openedTime=rs.getString("open_time");

        Subscriber  subscriber= new Subscriber();
        subscriber.sub_id=rs.getString("sub_id");
        emailDetails.subscriber=subscriber;
        Mail mail= new SimpleEmail();
        mail.pixelId=rs.getString("pixel_id");
        mail.clickId=rs.getString("ctr_id");
        emailDetails.mail=mail;

        if (openedTime==null){
            emailDetails.openedTime=null;
        }else{
            emailDetails.openedTime=simpleDateFormat.parse(openedTime);
        }

        return emailDetails;

    }


}
