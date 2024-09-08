package ru.alexbat.manager.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import ru.alexbat.manager.client.impl.ProductsRestClientImpl;

@Configuration
public class ClientBeans {

    @Bean
    public ProductsRestClientImpl productsRestClient(@Value("${services.catalogue.uri:http://localhost:8081}") String catalogueBaseUri) {
        return new ProductsRestClientImpl(RestClient.builder()
                .baseUrl(catalogueBaseUri)
                .build());
    }
}
