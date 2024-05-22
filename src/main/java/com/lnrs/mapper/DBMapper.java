package com.lnrs.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lnrs.dto.CompanyDTO;
import com.lnrs.entity.CompanyEntity;

public class DBMapper {

    private DBMapper(){

    }

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static  String serializeList(CompanyList list) throws JsonProcessingException {
        return objectMapper.writeValueAsString(list);
    }

    public static CompanyList deserializeList(String json) throws JsonProcessingException {
        return objectMapper.readValue(json, CompanyList.class);
    }

    public static CompanyDTO mapToDTO(CompanyEntity companyEntity){
        CompanyDTO companyDTO = new CompanyDTO();
        companyDTO.setCompanyNumber(companyEntity.getCompanyNumber());
        companyDTO.setCompanyListWithOfficers(companyEntity.getCompanyData());
        return companyDTO;
    }

    public static CompanyEntity mapToDTO(CompanyDTO companyDTO){
        CompanyEntity companyEntity = new CompanyEntity();
        companyEntity.setCompanyNumber(companyDTO.getCompanyNumber());
        companyEntity.setCompanyData(companyDTO.getCompanyListWithOfficers());
        return companyEntity;
    }
}
