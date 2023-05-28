package com.jh9.lobbysystem.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import reactor.core.publisher.Mono;

public abstract class MultiValueMapConverter {

    private static final Logger log = LoggerFactory.getLogger(MultiValueMapConverter.class);

    private MultiValueMapConverter() { }

    public static MultiValueMap<String, String> convert(ObjectMapper objectMapper, Object dto) {
        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            Map<String, String> map = objectMapper.convertValue(dto, new TypeReference<>() {});
            map.entrySet()
                .removeIf(entry -> entry.getValue() == null || entry.getValue().isEmpty());

            params.setAll(map);

            return params;
        } catch (Exception e) {
            log.error("Url Parameter 변환중 오류가 발생했습니다. requestDto={}", dto, e);
            throw new IllegalStateException("Url Parameter 변환중 오류가 발생했습니다.");
        }
    }

    public static  <T> Mono<T> mapToObject(Class<T> target, MultiValueMap<String, String> multiValueMap) {
        ObjectMapper objectMapper = new ObjectMapper();
        Mono<T> searchCondition;
        try {
            String json = objectMapper.writeValueAsString(multiValueMap);
            searchCondition = Mono.just(objectMapper.readValue(json, target));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return searchCondition;
    }
}
