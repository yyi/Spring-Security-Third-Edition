package com.packtpub.springsecurity.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author Mick Knutson
 * @since chapter 01.00
 */
@Configuration
@Import({SecurityConfig.class})
//@EnableScheduling
public class JavaConfig {

    /*@Autowired
    private RememberMeTokenRepository rememberMeTokenRepository;

    @Scheduled(fixedRate = 600_000)
    public void tokenRepositoryCleaner(){
        Thread trct = new Thread(
                new JpaTokenRepositoryCleaner(
                        rememberMeTokenRepository,
                        100_000L));
        trct.start();
    }*/

} // The End...
