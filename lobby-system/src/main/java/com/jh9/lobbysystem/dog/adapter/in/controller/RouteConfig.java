package com.jh9.lobbysystem.dog.adapter.in.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class RouteConfig {

    @Bean
    public RouterFunction<ServerResponse> route(DogController controller) {
        return RouterFunctions.route()
            .nest(RequestPredicates.path("/dogs"), builder -> builder
                .GET("", controller::searchDogs)
                .GET("/{id}", controller::searchDog)
                .POST("/{id}/thumbsUp", controller::thumbsUp)
                .POST("/{id}/thumbsDown", controller::thumbsDown)
            )
            .build();
    }

}
