package com.sparta.redistest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    //사실 이거 안만들어줘도 됨(더 잘쓰기 위해 만드는 것 뿐)
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();  //레디스는 일단 json형태임
        template.setConnectionFactory(connectionFactory);

        template.setKeySerializer(new StringRedisSerializer());    //json -> string, string->json
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());  //json -> object, object->json

        return template;
    }
}
