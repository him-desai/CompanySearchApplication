package com.lnrs.util;

import com.lnrs.mapper.Company;
import com.lnrs.mapper.CompanyList;
import com.lnrs.mapper.Officer;
import com.lnrs.mapper.OfficerList;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FilterUtilsTest {

    @Test
    public void testFilterOfficersByResignOnNull() {
        // Prepare test data
        Officer officer1 = new Officer();
        officer1.setAppointed_on("2022-01-01");

        Officer officer2 = new Officer();
        officer2.setAppointed_on(null); // Null appointed_on date

        Officer officer3 = new Officer();
        officer3.setAppointed_on("2022-01-02");

        List<Officer> officers = new ArrayList<>();
        officers.add(officer1);
        officers.add(officer2);
        officers.add(officer3);

        OfficerList officerList = new OfficerList();
        officerList.setItems(officers);

        OfficerList filteredOfficerList = FilterUtils.filterOfficersByResignOnNull(officerList);

        assertEquals(1, filteredOfficerList.getItems().size());
        assertEquals(officer2, filteredOfficerList.getItems().get(0));
    }

    @Test
    public void testFilterActiveCompany() {
        Company company1 = new Company();
        company1.setCompanyStatus("active");

        Company company2 = new Company();
        company2.setCompanyStatus("inactive");

        Company company3 = new Company();
        company3.setCompanyStatus("active");

        List<Company> companies = new ArrayList<>();
        companies.add(company1);
        companies.add(company2);
        companies.add(company3);

        CompanyList companyList = new CompanyList();
        companyList.setItems(companies);

        CompanyList filteredCompanyList = FilterUtils.filterActiveCompany(companyList, "active");

        assertEquals(2, filteredCompanyList.getItems().size());
        assertEquals(company1, filteredCompanyList.getItems().get(0));
        assertEquals(company3, filteredCompanyList.getItems().get(1));
    }
}
