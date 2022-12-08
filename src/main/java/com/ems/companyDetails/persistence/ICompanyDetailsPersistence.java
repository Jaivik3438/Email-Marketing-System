package com.ems.companyDetails.persistence;

import com.ems.companyDetails.model.CompanyDetails;

public interface ICompanyDetailsPersistence {
    public CompanyDetails loadcompanyDetails(CompanyDetails company) throws Exception;
    public int saveCompanyDetails(CompanyDetails saveCompany) throws Exception ;
}
