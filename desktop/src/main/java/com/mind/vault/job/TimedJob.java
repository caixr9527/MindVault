package com.mind.vault.job;

import com.mind.vault.utils.ApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @author caixr
 * @date 2025/10/21 15:46
 */
@EnableScheduling
@Configuration
public class TimedJob {

    @Autowired
    private ApiClient apiClient;

//    @Scheduled(fixedRate = 1000, initialDelay = 3000)
    public void checkHealth() {
        apiClient.health().subscribe(r -> {
            System.out.println(r.getData());
        }, e-> {
            System.out.println(e.getMessage());
        }, () ->{
            System.out.println("complete");
        });
    }

}
