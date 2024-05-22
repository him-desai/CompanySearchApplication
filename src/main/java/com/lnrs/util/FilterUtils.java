package com.lnrs.util;

import com.lnrs.mapper.Company;
import com.lnrs.mapper.CompanyList;
import com.lnrs.mapper.Officer;
import com.lnrs.mapper.OfficerList;

import java.util.List;
import java.util.stream.Collectors;

public class FilterUtils {

    // Should be replaced with Unmodifiable list. But not able to do it due to time issue, will do it in future in my git - Himanshu
    public static OfficerList filterOfficersByResignOnNull(OfficerList list) {
        List<Officer> filteredOfficers =  list.getItems().stream().filter(officer -> officer.getAppointed_on() == null).collect(Collectors.toList());
        OfficerList filteredOfficerList =  new OfficerList();
        filteredOfficerList.setItems(filteredOfficers);
        return filteredOfficerList;
    }

    public static CompanyList filterActiveCompany(CompanyList companies,String companyStatus){
        List<Company> companyList =  companies.getItems().stream().filter(company -> company.getCompanyStatus().equalsIgnoreCase(companyStatus)).collect(Collectors.toList());
        CompanyList filteredCompanyList = new CompanyList();
        filteredCompanyList.setItems(companyList);
        return filteredCompanyList;
    }


}

