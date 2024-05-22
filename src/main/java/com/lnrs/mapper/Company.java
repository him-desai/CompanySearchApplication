package com.lnrs.mapper;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Company {

    @JsonProperty("company_status")
    private String companyStatus;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonProperty("date_of_creation")
    private Date dateOfCreation;
    @JsonProperty("company_number")
    private String companyNumber;
    private String title;
    @JsonProperty("company_type")
    private String companyType;
    private Address address;
    private List<Officer> officers;

    public Company(String companyStatus,Date date,String companyNumber,String title,String companyType,Address address){
        this.companyStatus = companyStatus;
        this.dateOfCreation = date;
        this.companyNumber = companyNumber;
        this.companyType = companyType;
        this.address = address;
        this.title = title;
    }

}
