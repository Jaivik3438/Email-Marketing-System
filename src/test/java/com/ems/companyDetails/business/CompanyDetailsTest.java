package com.ems.companyDetails.business;

import com.ems.companyDetails.model.CompanyDetails;
import com.ems.companyDetails.persistence.CompanyDetailsDB;
import com.ems.companyDetails.persistence.CompanyDetailsDbMock;
import com.ems.companyDetails.persistence.ICompanyDetailsPersistence;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CompanyDetailsTest {
    private static CompanyDetails companyDetails;
    private static ICompanyDetailsPersistence companyDetailsPersistence;

    @BeforeAll
    public static void init()
    {
        companyDetails = new CompanyDetails();
        companyDetailsPersistence = new CompanyDetailsDbMock();
    }

    @Test
    public void saveCompanyDetailsSuccessTest() throws Exception {
        CompanyDetails inputCompanyDetails = new CompanyDetails();
        inputCompanyDetails.company_id = "C-deb64786-c58e-4d70-a5ae-46e4cfcf14ec";
        int response = inputCompanyDetails.saveCompanyDetails(companyDetailsPersistence);
        assertEquals(1,response);

    }
    @Test
    public void saveCompanyDetailsFailTest() throws Exception {
        CompanyDetails inputCompanyDetails = new CompanyDetails();
        inputCompanyDetails.company_id = "xyz";
        int response = inputCompanyDetails.saveCompanyDetails(companyDetailsPersistence);
        assertEquals(-1,response);

    }
    @Test
    public void getCompanyDetailsByUserIdSuccessTest() throws Exception {
        CompanyDetails getCompanyByUserId = companyDetails.getCompanyDetailsByUserId("3bef6c13-68ec-4366-8531-3e22af4d3314",companyDetailsPersistence);
        CompanyDetails expectedCompanyDetails = new CompanyDetails();
        expectedCompanyDetails.company_id = "C-deb64786-c58e-4d70-a5ae-46e4cfcf14ec";
        assertEquals(getCompanyByUserId.company_id,expectedCompanyDetails.company_id);
    }
}
