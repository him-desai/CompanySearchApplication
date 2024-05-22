package com.lnrs.mapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Address {
    private String premises;
    @JsonProperty("postal_code")
    private String postal_code;
    private String country;
    private String locality;
    @JsonProperty("address_line_1")
    private String addressLine1;
    @JsonProperty("addressLine2")
    private String address_line_2;

}