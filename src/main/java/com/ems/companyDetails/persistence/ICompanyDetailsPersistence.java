package com.ems.companyDetails.persistence;

import com.ems.authentication.model.User;
import com.ems.companyDetails.model.companyDetails;

public interface ICompanyDetailsPersistence {
    public companyDetails loadcompanyDetails(companyDetails company) throws Exception;
}
