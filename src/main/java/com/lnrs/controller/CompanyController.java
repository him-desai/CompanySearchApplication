

package com.lnrs.controller;
import com.lnrs.mapper.CompanyList;
import com.lnrs.request.CompanyRequest;
import com.lnrs.service.CompanySearchWithOfficers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;

@RestController
@RequestMapping("/TruProxyAPI/rest/Companies")
public class CompanyController {

    private static final Logger logger = LoggerFactory.getLogger(CompanyController.class);
    CompanySearchWithOfficers companySearchWithOfficers;

    @Autowired
    public CompanyController(CompanySearchWithOfficers companySearchWithOfficers) {
        this.companySearchWithOfficers = companySearchWithOfficers;
    }

    @PostMapping("v1/company")
    public ResponseEntity<CompanyList> createCompany(@Valid @RequestBody CompanyRequest request) {
        String companyName = request.getCompanyName();
        String companyNumber = request.getCompanyNumber();
        String companyStatus = request.getCompanyStatus();
        logger.info("***** Incoming values ***** Name= {}, Number= {}, CompanyStatus= {}", companyName, companyNumber, companyStatus);
        return new ResponseEntity<>(companySearchWithOfficers.searchCompniesAndOfficers(companyName,companyNumber,companyStatus),HttpStatus.OK);


    }
}



