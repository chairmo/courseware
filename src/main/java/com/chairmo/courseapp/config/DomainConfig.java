package com.chairmo.courseapp.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EntityScan("com.chairmo.courseapp.domain")
@EnableJpaRepositories("com.chairmo.courseapp.repos")
@EnableTransactionManagement
public class DomainConfig {
}
