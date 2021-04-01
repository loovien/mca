package redis;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

@Component
public class RedisOperator {

    final RedisTemplate<String, Object> redisTemplate;

    public RedisOperator(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }


    public void redisTest() {
        ValueOperations<String, Object> stringObjectValueOperations = redisTemplate.opsForValue();
        stringObjectValueOperations.set("luowen", new Person("luwoen", 1243));

        System.out.println("stringObjectValueOperations.get(\"luowen\") = " + stringObjectValueOperations.get("luowen"));
    }
}
