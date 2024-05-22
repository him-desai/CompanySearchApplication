package com.lnrs;

import com.lnrs.controller.CompanyController;
import com.lnrs.mapper.Company;
import com.lnrs.mapper.CompanyList;
import com.lnrs.mapper.Officer;
import com.lnrs.mapper.OfficerList;
import com.lnrs.request.CompanyRequest;
import com.lnrs.service.CompanySearchWithOfficers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class CompanyControllerTest {

    @Mock
    private CompanySearchWithOfficers companySearchWithOfficers;

    @InjectMocks
    private CompanyController companyController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateCompany_Success() {

        CompanyRequest request = new CompanyRequest();
        request.setCompanyName("Test Company");
        request.setCompanyNumber("123456");


        Company company = new Company();
        OfficerList officerList = new OfficerList();
        Officer officer = new Officer();

        List<Officer> officerObjList = new ArrayList<>();
        officerObjList.add(officer);
        company.setOfficers(officerObjList);
        List<Company> cList = new ArrayList<>();
        cList.add(company);
        CompanyList expectedCompanyList = new CompanyList();
        expectedCompanyList.setItems(cList);

        //searchResponse.setOfficerList(officerList);

        //companyList expectedResponse = Collections.singletonList(companyList);

        // Mock service method
        when(companySearchWithOfficers.searchCompniesAndOfficers(any(), any(), any())).thenReturn(expectedCompanyList);

        // Call controller method
        ResponseEntity<CompanyList> responseEntity = companyController.createCompany(request);

        // Verify response
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedCompanyList.getItems().get(0).getTitle(),responseEntity.getBody().getItems().get(0).getTitle());
    }

    // Add more test cases for different scenarios (e.g., invalid input, error cases, etc.)
}
