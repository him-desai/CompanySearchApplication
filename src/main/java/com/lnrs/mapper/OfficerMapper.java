package com.lnrs.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class OfficerMapper {
    private static final Logger logger = LoggerFactory.getLogger(OfficerMapper.class);
    public static OfficerList mapToOfficer(String officerResponse) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        OfficerList officerList = null;
        try {
            officerList = mapper.readValue(officerResponse, OfficerList.class);
        } catch (JsonProcessingException e) {
            logger.info("Exception for mapping officers, We are skipping this exception");
            //throw new RuntimeException(e); Skipping this one as we need Officers for each company and for some company we are getting 404 error : eg. no: OE030985
        }
        return officerList;
    }
}
