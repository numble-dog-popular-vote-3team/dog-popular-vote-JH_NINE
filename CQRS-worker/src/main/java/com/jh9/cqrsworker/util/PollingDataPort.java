package com.jh9.cqrsworker.util;

import reactor.core.publisher.Mono;

public interface PollingDataPort<T> {

    Mono<T> pollingDataById(Long id);
}
