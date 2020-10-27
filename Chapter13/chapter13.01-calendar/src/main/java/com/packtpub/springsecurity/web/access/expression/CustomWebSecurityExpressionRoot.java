package com.packtpub.springsecurity.web.access.expression;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.expression.WebSecurityExpressionRoot;

/**
 * @creaor:yyi
 * @createDate:2020/10/27
 * @Describle
 */
public class CustomWebSecurityExpressionRoot extends WebSecurityExpressionRoot {
    public CustomWebSecurityExpressionRoot(Authentication authentication, FilterInvocation fi) {
        super(authentication, fi);
    }

    public boolean isLocal() {

        return "localhost".equals(request.getServerName());

    }
}
