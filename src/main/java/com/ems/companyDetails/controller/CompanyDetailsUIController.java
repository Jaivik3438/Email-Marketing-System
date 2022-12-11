package com.ems.companyDetails.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CompanyDetailsUIController {
    @GetMapping("/add-company-details")
    public String getCompanyaDetails(){
         return "companyDetailsForm";
    }
    @GetMapping("/company-details")
    public String companyDetails(Model model){
        return "aaa";
    }
}
