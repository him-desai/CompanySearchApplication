package com.lnrs.service;

import com.lnrs.mapper.OfficerList;

public interface OfficerService {
    public OfficerList officerSearchByCompanyNumber(String companyId);
}
