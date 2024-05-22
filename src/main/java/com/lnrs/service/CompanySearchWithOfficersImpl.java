package com.lnrs.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lnrs.constant.AppConstants;
import com.lnrs.dto.CompanyDTO;
import com.lnrs.entity.CompanyEntity;
import com.lnrs.exceptions.CompanySearchException;
import com.lnrs.mapper.CompanyList;
import com.lnrs.mapper.DBMapper;
import com.lnrs.repository.CompanyRepository;
import com.lnrs.util.FilterUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;




@Service
public class CompanySearchWithOfficersImpl implements CompanySearchWithOfficers{
    private static final Logger logger = LoggerFactory.getLogger(CompanySearchWithOfficersImpl.class);
    CompanyService companyService;
    OfficerMergerService officerMergerService;
    CompanyRepository companyRepository;

    @Autowired
    public CompanySearchWithOfficersImpl(CompanyService companyService,OfficerMergerService officerMergerService,CompanyRepository companyRepository ){
        this.companyRepository = companyRepository;
        this.companyService = companyService;
        this.officerMergerService = officerMergerService;
    }

    public CompanyList searchCompniesAndOfficers(String companyName, String companyNumber,String companyStatus){
        CompanyList companyList = null;
                // check database for existing company number and response:
        if(StringUtils.isNotBlank(companyNumber) && StringUtils.isBlank(companyStatus)) {
            logger.info("Company Number is Not blank and Company Status is Blank, There will be DB Code");
            companyList = this.getCompanyListFromDB(companyNumber);
        }

        if(companyList == null ){
            companyList =   companyService.companySearchByNumberOrName(companyName,companyNumber);
            if(companyList==null ||  companyList.getItems() == null || companyList.getItems().isEmpty()){
                throw new CompanySearchException(AppConstants.COMPANY_NOT_FOUND);
            }
            if(StringUtils.isNotBlank(companyStatus) ){
                logger.info("Filtering the Company by Status");
                companyList = FilterUtils.filterActiveCompany(companyList,companyStatus);
            }
            companyList =  officerMergerService.officerSearchByCompanyNumber(companyList);
            if(StringUtils.isBlank(companyStatus)){
                logger.info("Saving company API Response with Officer in Database");
                this.saveCompanyListToDB(companyNumber,companyList);
            }

        }
         // code to store compalyList into DB if not already in DB.

        companyList.setTotalResults(companyList.getItems().size());
         return companyList;
    }

    private CompanyList getCompanyListFromDB(String companyNumber){
        CompanyList companyList = null;
        CompanyEntity companyEntity = companyRepository.findAllByCompanyNumber(companyNumber);
        if(companyEntity != null){
            CompanyDTO companyDTO = DBMapper.mapToDTO(companyEntity);
            String allCompanyAndOfficerData = companyDTO.getCompanyListWithOfficers();
            if (StringUtils.isNotBlank(allCompanyAndOfficerData)) {
                try {
                    companyList = DBMapper.deserializeList((allCompanyAndOfficerData));
                    logger.info("Getting data from Database.");
                } catch (JsonProcessingException e) {
                    //We have to skip this exception as anyway we will call APIs.
                }
            }
        }
        return companyList;
    }

    private void saveCompanyListToDB(String companyNumber,CompanyList companyList){
        CompanyEntity companyEntity = new CompanyEntity();
        try {
            companyEntity.setCompanyData(DBMapper.serializeList(companyList));
            companyEntity.setCompanyNumber(companyNumber);
            companyRepository.save(companyEntity);
            logger.info("Saving data to Database");
        } catch (JsonProcessingException e) {
          // Skipping it as this should not stop flow.
        }
    }
}
