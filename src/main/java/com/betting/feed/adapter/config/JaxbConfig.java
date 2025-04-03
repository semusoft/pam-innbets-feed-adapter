package com.betting.feed.adapter.config;

import com.betting.feed.adapter.feed.v1.model.Sports;
import com.betting.feed.adapter.feed.v1.model.Sport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class JaxbConfig {

    @Bean
    public Jaxb2Marshaller jaxb2Marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setClassesToBeBound(Sport.class, Sports.class);
        return marshaller;
    }
}
