package xu.all.apps.redis;

import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.util.Properties;

public class JedisFactory {

    private static String REDIS_DATABASE = "redis.database";
    private static String REDIS_HOST = "redis.host";
    private static String REDIS_PORT = "redis.port";
    private static String REDIS_PASSWORD = "redis.password";
    private static String REDIS_MAX_TOTAL = "redis.pool.max-total";
    private static String REDIS_MAX_WAIT_MILLIS = "redis.pool.max-wait-millis";
    private static String REDIS_MAX_IDLE = "redis.pool.max-idle";
    private static String REDIS_MIN_IDLE = "redis.pool.min-idle";

    public static JedisConnectionFactory buildJedisConnection() {
        Properties properties = new Properties();
        try {
            //读取类路径下的配置文件
            properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("application.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String redisHost = properties.getProperty(REDIS_HOST);
        String redisPort = properties.getProperty(REDIS_PORT);
        String redisPassword = properties.getProperty(REDIS_PASSWORD);
        String redisDataBase = properties.getProperty(REDIS_DATABASE);
        String redisMaxTotal = properties.getProperty(REDIS_MAX_TOTAL);
        String redisMaxWaitMillis = properties.getProperty(REDIS_MAX_WAIT_MILLIS);
        String redisMaxIdle = properties.getProperty(REDIS_MAX_IDLE);
        String redisMinIdle = properties.getProperty(REDIS_MIN_IDLE);

        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(redisHost, Integer.valueOf(redisPort));
        redisStandaloneConfiguration.setPassword(RedisPassword.of(redisPassword));
        redisStandaloneConfiguration.setDatabase(Integer.valueOf(redisDataBase));

        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(Integer.valueOf(redisMaxTotal));
        poolConfig.setMaxWaitMillis(Integer.valueOf(redisMaxWaitMillis));
        poolConfig.setMaxIdle(Integer.valueOf(redisMaxIdle));
        poolConfig.setMinIdle(Integer.valueOf(redisMinIdle));
        JedisClientConfiguration.JedisPoolingClientConfigurationBuilder jpcb = (JedisClientConfiguration.JedisPoolingClientConfigurationBuilder) JedisClientConfiguration.builder();
        jpcb.poolConfig(poolConfig);
        JedisClientConfiguration jedisClientConfiguration = jpcb.build();
        return new JedisConnectionFactory(redisStandaloneConfiguration, jedisClientConfiguration);
    }

    public static RedisTemplate<String, String> buildRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        //设置序列化，防止乱码
        RedisSerializer stringSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setHashValueSerializer(stringSerializer);
        //刷新配置
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

}
