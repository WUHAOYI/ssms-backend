package com.ssms.faraday.core.interceptor;

import org.springframework.http.HttpHeaders;
import com.ssms.faraday.config.MappingProperties;
import com.ssms.faraday.core.http.ResponseData;

import java.util.List;

public class CacheResponseInterceptor implements PostForwardResponseInterceptor {
    @Override
    public void intercept(ResponseData data, MappingProperties mapping) {
        HttpHeaders respHeaders = data.getHeaders();
        if (respHeaders.containsKey(HttpHeaders.CONTENT_TYPE)) {
            List<String> values = respHeaders.get(HttpHeaders.CONTENT_TYPE);
            if (values.contains("text/html")) {
                // insert header to prevent caching
                respHeaders.set(HttpHeaders.CACHE_CONTROL, "no-cache");
            }
        }
    }
}
