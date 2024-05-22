package com.lnrs.mapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Officer {
    private Address address;
    private String name;
    @JsonProperty("appointedOn")
    private String appointed_on;
    @JsonProperty("officerRole")
    private String officer_role;
}
