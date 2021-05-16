package org.hillel.homework.config;

import org.hillel.homework.persistence.repository.CommonRepository;
import org.hillel.homework.persistence.repository.JourneyRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("org.hillel")
@PropertySource("database.properties")
public class RootConfig {

}
