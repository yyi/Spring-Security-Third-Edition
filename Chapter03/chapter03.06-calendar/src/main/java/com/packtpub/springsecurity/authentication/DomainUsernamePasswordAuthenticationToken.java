package com.packtpub.springsecurity.authentication;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class DomainUsernamePasswordAuthenticationToken extends UsernamePasswordAuthenticationToken {

    private String domain;

    public DomainUsernamePasswordAuthenticationToken(Object principal, Object credentials,String domain) {
        super(principal, credentials);
        this.domain = domain;
    }

    public DomainUsernamePasswordAuthenticationToken(Object principal, Object credentials, String domain,Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
        this.domain = domain;
    }

    public String getDomain() {
        return domain;
    }
}
