package org.hillel.homework.config;

import org.hillel.homework.persistence.repository.JourneyRepository;
import org.hillel.homework.persistence.repository.JourneyRepositoryImpl;
import org.hillel.homework.service.JourneyService;
import org.hillel.homework.service.TransactionalJourneyService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("org.hillel")
@PropertySource("database.properties")
public class RootConfig {
    @Bean
    public JourneyService transactionalJourneyService() {
        return new TransactionalJourneyService();
    }

    @Bean
    public JourneyRepository journeyRepository() {
        return new JourneyRepositoryImpl();
    }
}
