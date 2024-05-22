package com.lnrs.response;

import com.lnrs.mapper.Company;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CompanySearchResponseHandler {
    private List<Company> items;
    private int totalResults;
}