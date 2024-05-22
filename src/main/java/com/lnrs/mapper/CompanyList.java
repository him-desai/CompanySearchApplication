package com.lnrs.mapper;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CompanyList {
    private List<Company> items;
    @JsonProperty("total_results")
    private int totalResults;

    public CompanyList(List<Company> items){
        this.items = items;
    }
}