package com.packtpub.springsecurity.web.access.intercept;

import com.packtpub.springsecurity.repository.SecurityFilterMetadataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @creaor:yyi
 * @createDate:2020/10/27
 * @Describle
 */
@Service
public class JpaRequestConfigMappingService implements RequestConfigMappingService {


    private SecurityFilterMetadataRepository securityFilterMetadataRepository;

    @Autowired
    public JpaRequestConfigMappingService(
            SecurityFilterMetadataRepository sfmr
    ) {
        this.securityFilterMetadataRepository = sfmr;
    }

    @Override
    public List<RequestConfigMapping> getRequestConfigMappings() {
        List<RequestConfigMapping> rcm =
                securityFilterMetadataRepository
                        .findAll()
                        .stream()
                        .sorted((m1, m2) -> m1.getSortOrder() - m2.getSortOrder())
                        .map(md -> new RequestConfigMapping(
                                new AntPathRequestMatcher
                                        (md.getAntPattern()),
                                new SecurityConfig
                                        (md.getExpression()))).collect(toList());
        return rcm;
    }
}
