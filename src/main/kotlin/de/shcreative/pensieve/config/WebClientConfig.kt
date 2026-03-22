package de.shcreative.pensieve.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class WebClientConfig {

    @Bean
    fun mindWebClient(
        @Value("\${pensieve.mind.url}") mindUrl: String
    ): WebClient =
        WebClient.builder()
            .baseUrl(mindUrl)
            .defaultHeader("Content-Type", "application/json")
            .defaultHeader("Accept", "application/json")
            .build()
}
