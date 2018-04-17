package wang.willard.boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

public class JedisKeyValueServiceImpl {
    @Autowired
    private RedisTemplate redisTemplate;

    public long setNX(String key,String value, int expire){
        long result = -1;
//        redisTemplate.set
        return 1;
    }
}
