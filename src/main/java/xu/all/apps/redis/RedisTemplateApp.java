package xu.all.apps.redis;

import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @Description: 配置连接redis
 * @Author: xuyujun
 * @Date: 2021/8/23
 */
public class RedisTemplateApp {

    public static void main(String[] args) {
        JedisConnectionFactory jedisConnection = JedisFactory.buildJedisConnection();
        jedisConnection.afterPropertiesSet();
        RedisTemplate<String, String> redisTemplate = JedisFactory.buildRedisTemplate(jedisConnection);

        String key = "k1";
        String value = "v1";
        redisTemplate.opsForValue().set(key, value);
        String result = redisTemplate.opsForValue().get(key);
        System.out.println("The value of key:" + key + " is [" + result + "]");
    }

}
