package com.lnrs.service;

import com.lnrs.constant.AppConstants;
import com.lnrs.mapper.Company;
import com.lnrs.mapper.CompanyList;
import com.lnrs.mapper.OfficerList;
import com.lnrs.mapper.OfficerMapper;
import com.lnrs.util.FilterUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfficerMergerServiceImpl implements  OfficerMergerService{

    @Autowired
    HTTPClient httpClient;
    private final String officerSearchURL;

    @Autowired
    public OfficerMergerServiceImpl(@Value(AppConstants.OFFICER_SEARCH) String officerSearchURL) {
        this.officerSearchURL = officerSearchURL;
    }

    public CompanyList officerSearchByCompanyNumber(CompanyList companyList) {
        List<Company> companyWithOfficerList = companyList.getItems().stream().map(company -> {
            System.out.println(officerSearchURL+company.getCompanyNumber());
            //httpClient.get(officerSearchURL+company.getCompanyNumber())
            ResponseEntity<String>  officersResponse = null;
            boolean skip = false;
            try {
                officersResponse = httpClient.get(officerSearchURL + company.getCompanyNumber());
            }catch (Exception e){
                skip = true;
                officersResponse = new ResponseEntity<>("", HttpStatus.OK);
            }
            OfficerList officerList = new OfficerList();
            if(skip){
                skip = false;
            }else{
                officerList = OfficerMapper.mapToOfficer(officersResponse.getBody());
            }
            //Filtering officers based on Resign not null.
            if(officerList.getItems() != null){
                officerList = FilterUtils.filterOfficersByResignOnNull(officerList);
            }
            company.setOfficers(officerList.getItems());
            return company;
        }).collect(Collectors.toList());

        return new CompanyList(companyWithOfficerList);

    }
}
