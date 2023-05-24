package com.jh9.cqrsworker.adapter.event;

record DogUpdateMessage(
    String serverName,
    Long id
) {

}
