package com.lnrs.service;

import com.lnrs.mapper.CompanyList;

public interface CompanyService {
    CompanyList companySearchByNumberOrName(String companyName, String companyNumber);
}
