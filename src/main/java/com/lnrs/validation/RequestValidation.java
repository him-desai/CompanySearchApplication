package com.lnrs.validation;

import com.lnrs.request.CompanyRequest;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class RequestValidation implements ConstraintValidator<RequestParamValidation, CompanyRequest> {
    @Override
    public void initialize(RequestParamValidation constraintAnnotation) {
    }

    @Override
    public boolean isValid(CompanyRequest request, ConstraintValidatorContext context) {
        return !StringUtils.isBlank(request.getCompanyName()) || !StringUtils.isBlank(request.getCompanyNumber());
    }
}
