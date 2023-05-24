package com.jh9.lobbysystem.dog.application.port.out.webClient;

import com.jh9.lobbysystem.dog.application.port.in.DogSearchCondition;
import java.util.List;
import reactor.core.publisher.Mono;

public interface DogSearchPort {

    Mono<List<Long>> searchByCondition(DogSearchCondition condition);
}
