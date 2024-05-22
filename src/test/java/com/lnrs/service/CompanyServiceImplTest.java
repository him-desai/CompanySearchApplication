package com.lnrs.service;

import com.lnrs.mapper.Address;
import com.lnrs.mapper.Company;
import com.lnrs.mapper.CompanyList;
import com.lnrs.mapper.CompanyMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CompanyServiceImplTest {


    @InjectMocks
    private CompanyServiceImpl companyService;

    @Mock
    private HTTPClient hTTPClient;

    @Mock
    private ResponseEntity<String> mockResponseEntity;


    private final String companySearchURL = "http://example.com/officer/";

    @BeforeEach
    public void setup() {
        //MockitoAnnotations.initMocks(this);
        companyService = new CompanyServiceImpl(companySearchURL, hTTPClient);
        mockStatic(CompanyMapper.class);
    }

    @Test
    public void testCompanySearchByNumberOrName_Success() {
        CompanyList companyList = new CompanyList();
        List<Company> lisbObjCompany = new ArrayList<>();

        lisbObjCompany.add(new Company("active", null, "10101010", "", "", new Address("SomeAdd", "WD10398", "UK", "AMJ", "addline1", "addline3")));
        lisbObjCompany.add(new Company("dissolved", null, "11111111", "", "", new Address("SomeAdd2", "WD103982", "UK2", "AMJ", "addline1", "addline3")));
        String companyId = "123456";
        companyList.setItems(lisbObjCompany);

        String responseBody = "{\"name\": \"John Doe\", \"position\": \"Manager\"}";

        when(mockResponseEntity.getBody()).thenReturn(responseBody);
        when(hTTPClient.get(companySearchURL + companyId)).thenReturn(mockResponseEntity);
        Mockito.when(CompanyMapper.mapToCompany(any())).thenReturn(companyList);

        CompanyList returnedCompanyList = companyService.companySearchByNumberOrName(companyId, "");

        assertEquals(2, returnedCompanyList.getItems().size());
        assertEquals("active", returnedCompanyList.getItems().get(0).getCompanyStatus());
        assertEquals("dissolved", returnedCompanyList.getItems().get(1).getCompanyStatus());

    }
}

