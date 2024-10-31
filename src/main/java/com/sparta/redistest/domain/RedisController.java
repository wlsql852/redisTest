package com.sparta.redistest.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RedisController {
    private final RedisTemplate<String, Object> redisTemplate;

    @GetMapping
    public String temp() {
        return "이게 되네";
    }

    @GetMapping("/test")
    public String temp1() {
        String title="이론수업 개꿀";
        redisTemplate.opsForValue().set("test", title);
        return title;
    }

    @GetMapping("/test/get")
    public String temp2() {
        String result = (String) redisTemplate.opsForValue().get("test");  //값을 object로 받아오기 때문에 String으로 강제 형변환
        return result;
    }

    //실무 연습
    @GetMapping("/redis")
    public String getRedisData(@RequestParam(name = "keyword") String keyword) {
        var key = "keyword:%s".formatted(keyword);
        Object temp = redisTemplate.opsForValue().get(key);  //레디스 한번 검사
        if(temp == null) {   //없으면 redis에 새로 저장, 있으면 바로 가져옴
            temp = keyword;
            redisTemplate.opsForValue().set(key, keyword);
            return "redis에서 값을 찾을 수 없어서 값을 저장하였습니다. 저장한 값은 %s%n".formatted(temp.toString());
        }
        return "redis에서 값을 가져왔습니다. 가져온 값은 %s%n".formatted(temp.toString());

    }
}
