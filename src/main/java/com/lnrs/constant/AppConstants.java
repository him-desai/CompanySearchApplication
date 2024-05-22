package com.lnrs.constant;

public class AppConstants {
    private AppConstants(){

    }
    public static final String API_KEY = "x-api-key";
    public static final String OFFICER_SEARCH = "${lnrs.officer.search}";
    public static final String COMPANY_SEARCH = "${lnrs.company.search}";


    public static final String OFFICER_EXCEPTION = "Officer exception while processing : ";
    public static final String COMPANY_EXCEPTION = "Company  exception while processing : ";

    public static final String OFFICER_NOT_FOUND = "NO DATA Related to Officer : ";
    public static final String COMPANY_NOT_FOUND = "NO DATA FOUND for COMPANY : ";
    public static final String UNEXPECTED_ERROR = "An unexpected error occurred: ";
    public static final String HTTP_CLIENT_EXCEPTION = "HTTP Client Error occurred during company OR officer search: ";
}
