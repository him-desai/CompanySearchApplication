package com.lnrs.filter;

import com.lnrs.constant.AppConstants;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ApiKeyFilterTest {

    private ApiKeyFilter apiKeyFilter;
    private final String validApiKey = "TEMPKEYFORTESTING";

    @BeforeEach
    public void setUp() {
        apiKeyFilter = new ApiKeyFilter(validApiKey);
    }

    @Test
    public void testDoFilter_ValidApiKey() throws IOException, ServletException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader(AppConstants.API_KEY, validApiKey);
        MockHttpServletResponse response = new MockHttpServletResponse();
        FilterChain filterChain = Mockito.mock(FilterChain.class);

        apiKeyFilter.doFilter(request, response, filterChain);

        verify(filterChain).doFilter(request, response);
        assertEquals(200, response.getStatus());
        verifyNoMoreInteractions(filterChain);
    }


}
