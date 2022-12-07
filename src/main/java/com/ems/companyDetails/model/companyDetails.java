package com.ems.companyDetails.model;

import com.ems.companyDetails.persistence.ICompanyDetailsPersistence;

public class companyDetails {
    public String company_id;
    public String company_name;
    public String website_link;
    public String company_email;
    public String  owner_name;
    public String facebook_link;
    public String instagram_link;
    public String twitter_url;

    public companyDetails loadCompanyDetails (ICompanyDetailsPersistence persistence) throws java.lang.Exception {
        return persistence.loadcompanyDetails(this);
    }
}
