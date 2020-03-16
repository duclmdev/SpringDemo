package com.viettel.ems.fm;

import com.viettel.ems.fm.config.YamlConfig;
import com.viettel.ems.fm.storage.StorageProperties;
import com.viettel.ems.fm.storage.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import static org.springframework.boot.SpringApplication.run;

@Slf4j
@EnableScheduling
@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class HaruApplication {

    private final YamlConfig config;

    public HaruApplication(YamlConfig config) {
        this.config = config;
    }

    public static void main(String[] args) {
        run(HaruApplication.class, args);
    }

    @Bean
    public CommandLineRunner init(StorageService storageService) {
        log.info("Environment: {}", config.getEnvironment());
        log.info("Name:        {}", config.getName());
        log.info("Servers:     {}", config.getServers());

        return args -> {
            storageService.deleteAll();
            storageService.init();
        };
    }
}
