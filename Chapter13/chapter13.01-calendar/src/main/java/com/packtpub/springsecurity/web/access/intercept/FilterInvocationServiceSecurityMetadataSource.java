package com.packtpub.springsecurity.web.access.intercept;

import com.packtpub.springsecurity.web.access.expression.CustomWebSecurityExpressionHandler;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.expression.SecurityExpressionHandler;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.expression.ExpressionBasedFilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @creaor:yyi
 * @createDate:2020/10/27
 * @Describle
 */
@Component
public class FilterInvocationServiceSecurityMetadataSource implements
        FilterInvocationSecurityMetadataSource, InitializingBean {

    @Autowired
    private RequestConfigMappingService requestConfigMappingService;

    private ExpressionBasedFilterInvocationSecurityMetadataSource delegate;

    @Autowired
    private CustomWebSecurityExpressionHandler  expressionHandler;

    @Override
    public void afterPropertiesSet() throws Exception {
        List<RequestConfigMapping> requestConfigMappings =
                requestConfigMappingService.getRequestConfigMappings();
        LinkedHashMap requestMap = new
                LinkedHashMap(requestConfigMappings.size());
        for (RequestConfigMapping requestConfigMapping :
                requestConfigMappings) {
            RequestMatcher matcher =
                    requestConfigMapping.getMatcher();
            Collection<ConfigAttribute> attributes =
                    requestConfigMapping.getAttributes();
            requestMap.put(matcher, attributes);
        }
        this.delegate = new ExpressionBasedFilterInvocationSecurityMetadataSource(requestMap, expressionHandler);
    }


    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        return this.delegate.getAttributes(object);
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return this.delegate.getAllConfigAttributes();
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return this.delegate.supports(clazz);
    }
}
