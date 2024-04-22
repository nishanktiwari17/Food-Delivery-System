package org.fdschinecenter.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Class to manage beans.
 */
@Configuration
public class MyBeanManager {

    /**
     * Method to create a RestTemplate bean.
     * @return RestTemplate
     */
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    }
