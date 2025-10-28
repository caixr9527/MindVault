package com.mind.vault.utils;

import com.mind.vault.config.ApiConfig;
import com.mind.vault.model.R;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Component
public class ApiClient {

    private final WebClient webClient;

    @Autowired
    private ApiConfig apiConfig;
    
    public ApiClient() {
        // 创建带有超时配置的HttpClient
        HttpClient httpClient = HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000) // 连接超时5秒
                .responseTimeout(Duration.ofSeconds(10)) // 响应超时10秒
                .doOnConnected(conn ->
                        conn.addHandlerLast(new ReadTimeoutHandler(10, TimeUnit.SECONDS)) // 读取超时10秒
                                .addHandlerLast(new WriteTimeoutHandler(10, TimeUnit.SECONDS)) // 写入超时10秒
                );

        this.webClient = WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .baseUrl(apiConfig.getUrl())
                .build();
    }
    
    public Mono<R> health() {
        return webClient.get()
                .uri("/health")
                .retrieve()
                .bodyToMono(R.class);
    }
    
}
