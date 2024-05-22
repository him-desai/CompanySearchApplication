package com.lnrs.service;

import com.lnrs.mapper.Address;
import com.lnrs.mapper.Officer;
import com.lnrs.mapper.OfficerList;
import com.lnrs.mapper.OfficerMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
//@RunWith(JUnitPlatform.class)
public class OfficerServiceImplTest {

    @InjectMocks
    private OfficerServiceImpl officerService;

    @Mock
    private HTTPClient hTTPClient;

    @Mock
    private ResponseEntity<String> mockResponseEntity;


    private final String officerSearchURL = "http://example.com/officer/";

    @BeforeEach
    public void setup() {
        //MockitoAnnotations.initMocks(this);
        officerService = new OfficerServiceImpl(officerSearchURL,hTTPClient);
        mockStatic(OfficerMapper.class);
    }

    @Test
    public void testOfficerSearchByCompanyNumber_Success() {
        OfficerList officerList = new OfficerList();
        List<Officer> lisbObjOfficer = new ArrayList<>();
        lisbObjOfficer.add(new Officer(new Address("SomeAdd","WD10398","UK","AMJ","addline1","addline3"),"Albert","3-2-90","ceo"));
        lisbObjOfficer.add(new Officer(new Address("SomeAdd2","WD10322","UK","A2","addline11","addlin33"),"Robert","3-2-94","none"));
        String companyId = "123456";
        officerList.setItems(lisbObjOfficer);

        String responseBody = "{\"name\": \"John Doe\", \"position\": \"Manager\"}";

        when(mockResponseEntity.getBody()).thenReturn(responseBody);
        when(hTTPClient.get(officerSearchURL + companyId)).thenReturn(mockResponseEntity);
        Mockito.when(OfficerMapper.mapToOfficer(any())).thenReturn(officerList);

        OfficerList officerListReturned = officerService.officerSearchByCompanyNumber(companyId);

        assertEquals(2, officerListReturned.getItems().size());
        assertEquals("Albert", officerListReturned.getItems().get(0).getName());
        assertEquals("Robert", officerListReturned.getItems().get(1).getName());
    }
}

