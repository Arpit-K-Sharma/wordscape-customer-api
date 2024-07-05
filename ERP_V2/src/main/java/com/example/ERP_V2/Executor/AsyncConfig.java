package com.example.ERP_V2.Executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class AsyncConfig {

    @Bean(name = "taskExecutor")
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(1);  // Number of core threads
        executor.setMaxPoolSize(8);  // Maximum number of threads
        executor.setQueueCapacity(10);  // Capacity of the queue
        executor.setThreadNamePrefix("Async-");
        executor.initialize();
        return executor;
    }
}