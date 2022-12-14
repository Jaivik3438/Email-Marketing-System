package com.ems.bulkEmail.buisness;

import org.junit.jupiter.api.BeforeAll;

public class BulkEmailFactoryTest {
    private static IBulkEmailFactory bulkEmailFactory;

    @BeforeAll
    public static void init(){
        bulkEmailFactory= new BulkEmailFactory();
    }


}
