package com.lnrs.exceptions;

import com.lnrs.constant.AppConstants;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CompanySearchException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public CustomErrorResponse handleCompanyAPIException(CompanySearchException ex) {
        return new CustomErrorResponse(ex.getMessage());
    }

    @ExceptionHandler(OfficerSearchException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public CustomErrorResponse handleOfficersAPIException(OfficerSearchException ex) {
        return new CustomErrorResponse(AppConstants.OFFICER_EXCEPTION + ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public CustomErrorResponse handleGeneralException(Exception ex) {
        return new CustomErrorResponse(AppConstants.UNEXPECTED_ERROR + ex.getMessage());
    }
}

