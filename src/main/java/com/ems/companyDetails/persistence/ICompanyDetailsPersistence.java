package com.ems.companyDetails.persistence;

import com.ems.companyDetails.model.CompanyDetails;

import java.util.List;

public interface ICompanyDetailsPersistence {
    public List<CompanyDetails> loadcompanyDetails(CompanyDetails company) throws Exception;
    public int saveCompanyDetails(CompanyDetails saveCompany) throws Exception ;
    public CompanyDetails  getCompanyByUserId(String userId) throws Exception;
}
