package com.lnrs.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter


public class CompanyRequest {
    private String companyName;
    private String companyNumber;
    private String companyStatus;
}
