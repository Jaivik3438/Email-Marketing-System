package com.ems.emailAnalytics.buisness;

import com.ems.bulkEmail.persistence.EmailDetailDbMock;
import com.ems.bulkEmail.persistence.IEmailDetailsPersistence;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ClickAnalyticsTest {

    private static ClickAnalytics clickAnalytics;
    private static IEmailDetailsPersistence emailDetailsPersistence;

    @BeforeAll
    public static void init(){
        clickAnalytics= new ClickAnalytics();
        emailDetailsPersistence= new EmailDetailDbMock();


    }
    @Test
    public void performAnalyticsTest(){

    }
}
