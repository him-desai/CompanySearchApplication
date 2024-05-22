
package com.lnrs.service;

import com.lnrs.constant.AppConstants;
import com.lnrs.mapper.OfficerList;
import com.lnrs.mapper.OfficerMapper;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Getter
@Setter
public class OfficerServiceImpl implements OfficerService {

    private final String officerSearchURL;

    @Autowired
    HTTPClient httpClient;
    @Autowired
    OfficerMergerService officerMergerService;

    @Autowired
    public OfficerServiceImpl( @Value(AppConstants.OFFICER_SEARCH) String officerSearchURL,HTTPClient httpClient) {
        this.officerSearchURL = officerSearchURL;
        this.httpClient = httpClient;
    }
    public OfficerList officerSearchByCompanyNumber(String companyId) {
        ResponseEntity<String> officersResponse = null;

        if (StringUtils.isNotBlank(companyId) && StringUtils.isNotBlank(companyId)) {
            officersResponse = httpClient.get(officerSearchURL+companyId);
        }
        return OfficerMapper.mapToOfficer(officersResponse.getBody());

    }

}
