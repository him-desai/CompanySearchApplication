package com.lnrs.service;

import com.lnrs.mapper.CompanyList;

public interface CompanySearchWithOfficers {

    CompanyList searchCompniesAndOfficers(String companyName, String companyNumber, String companyStatus);
}
