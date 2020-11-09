package com.conference.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Configuration class
 * @author rshah
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.conference")
public class ConferenceConfiguration {


}