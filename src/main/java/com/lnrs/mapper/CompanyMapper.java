package com.lnrs.mapper;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lnrs.constant.AppConstants;
import com.lnrs.exceptions.CompanySearchException;
import com.lnrs.service.CompanySearchWithOfficersImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class CompanyMapper {

    private static final Logger logger = LoggerFactory.getLogger(CompanyMapper.class);
    private CompanyMapper(){

    }

    public static CompanyList mapToCompany(String companyResponse) {
        logger.info("Mapping data to Company Object");
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        CompanyList companyList;
        try {
            companyList = mapper.readValue(companyResponse, CompanyList.class);
        } catch (Exception e) {
            throw new CompanySearchException(AppConstants.COMPANY_EXCEPTION);
        }
        if(companyList ==null || companyList.getItems() == null){
            throw new CompanySearchException(AppConstants.COMPANY_NOT_FOUND);
        }
        return companyList;
    }
}
