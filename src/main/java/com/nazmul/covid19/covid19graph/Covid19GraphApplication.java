package com.nazmul.covid19.covid19graph;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.nazmul.covid19.covid19graph.coronaservice.CoronaGraphDataService;
import com.vaadin.flow.spring.annotation.EnableVaadin;

@SpringBootApplication
@EnableVaadin
@EnableScheduling
@EnableCaching
@EnableFeignClients
public class Covid19GraphApplication {

	public static void main(String[] args) {
		SpringApplication.run(Covid19GraphApplication.class, args);
	}

    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager(CoronaGraphDataService.COVID_SERVICE_CACHE);
    }

}
