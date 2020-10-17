package com.packtpub.springsecurity.web.authentication.rememberme;

import com.packtpub.springsecurity.repository.RememberMeTokenRepository;

import java.util.Date;

public class JpaTokenRepositoryCleaner implements Runnable {

    private  RememberMeTokenRepository rememberMeTokenRepository;

    private  long tokenValidityInMs;

    public JpaTokenRepositoryCleaner(RememberMeTokenRepository rememberMeTokenRepository, long l) {
        this.rememberMeTokenRepository=rememberMeTokenRepository;
        this.tokenValidityInMs = l;
    }

    @Override
    public void run() {
        long expiredInMs=System.currentTimeMillis()-tokenValidityInMs;
        try{
            rememberMeTokenRepository.delete(rememberMeTokenRepository
                    .findByLastUsedAfter(new Date(expiredInMs)));
        }catch ( Throwable e){}
    }
}
