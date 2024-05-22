package com.lnrs.service;


import com.lnrs.constant.AppConstants;
import com.lnrs.exceptions.CompanySearchException;
import com.lnrs.mapper.CompanyList;
import com.lnrs.mapper.CompanyMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class CompanyServiceImpl implements CompanyService {

    private static final Logger logger = LoggerFactory.getLogger(CompanyServiceImpl.class);
    private final String companySearchURL;

    @Autowired
    HTTPClient httpClient;
    @Autowired
    OfficerMergerService officerMergerService;

    @Autowired
    public CompanyServiceImpl(@Value(AppConstants.COMPANY_SEARCH) String companySearchURL, HTTPClient httpClient) {
        this.companySearchURL = companySearchURL;
        this.httpClient = httpClient;
    }

    public CompanyList companySearchByNumberOrName(String companyName, String companyNumber) {
        ResponseEntity<String> companyResponse = null;
        if (StringUtils.isNotBlank(companyNumber)) {
            companyResponse =  this.callWithCompanyParam(companySearchURL,companyNumber);
            logger.debug("URL with Company Number is :: {}{}", companySearchURL, companyNumber);
        } else {
            if (StringUtils.isNotBlank(companyName)) {
                    companyResponse = this.callWithCompanyParam(companySearchURL,companyName);
                    logger.debug("URL with Company Name is :: {}{}", companySearchURL, companyName);
            }
        }
        if (companyResponse == null || companyResponse.getBody() == null) {
            throw new CompanySearchException(AppConstants.COMPANY_NOT_FOUND);
        }
        return CompanyMapper.mapToCompany(companyResponse.getBody());

    }

    private ResponseEntity<String> callWithCompanyParam(String url,String companyNumber){
        ResponseEntity<String> companyResponse = null;
        try {
            companyResponse = httpClient.get(url + companyNumber);
        } catch (Exception e) {
            if (e instanceof HttpClientErrorException) {
                throw new CompanySearchException(AppConstants.HTTP_CLIENT_EXCEPTION);
            }
            throw new CompanySearchException(AppConstants.COMPANY_EXCEPTION);
        }
        return  companyResponse;
    }

}
