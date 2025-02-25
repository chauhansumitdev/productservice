package com.example.productservice.retrofit;


import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import org.springframework.cloud.client.discovery.DiscoveryClient;


import java.util.List;

@Slf4j
@Configuration
public class RetrofitConfig {

    private final DiscoveryClient discoveryClient;

    public RetrofitConfig(DiscoveryClient discoveryClient){
        this.discoveryClient = discoveryClient;
    }

    @Bean
    public Retrofit retrofit(){
        return new Retrofit.Builder()
                .baseUrl(getURI("AUTH-SERVICE"))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private String getURI(String serviceName) {
        log.warn("Available services: " + discoveryClient.getServices());

        List<ServiceInstance> services = discoveryClient.getInstances(serviceName);

        if (services.isEmpty()) {
            throw new IllegalStateException("NO INSTANCE AVAILABLE " + serviceName);
        }

        log.warn("URI NAME : " + services.get(0).getUri());
        return services.get(0).getUri().toString();
    }
}