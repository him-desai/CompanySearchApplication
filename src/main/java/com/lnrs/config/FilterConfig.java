package com.lnrs.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.lnrs.filter.ApiKeyFilter;

@Configuration
public class FilterConfig {

    @Value("${api.value}")
    private String apiKey;

    @Bean
    public ApiKeyFilter apiKeyFilter() {
        return new ApiKeyFilter(apiKey);
    }

    @Bean
    public FilterRegistrationBean<ApiKeyFilter> filterRegistrationBean() {
        FilterRegistrationBean<ApiKeyFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(apiKeyFilter());
        registrationBean.addUrlPatterns("/TruProxyAPI/rest/Companies/*");
        return registrationBean;
    }

}
