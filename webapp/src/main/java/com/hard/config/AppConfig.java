package com.hard.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({
        "com.hard.repositories.impl",
        "com.hard.services.impl",
})
public class AppConfig {
}
