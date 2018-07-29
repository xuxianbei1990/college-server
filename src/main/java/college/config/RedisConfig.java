package college.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import college.utils.RedisUtil;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
@PropertySource("classpath:jdbc.properties")
public class RedisConfig {
	@Autowired
	private Environment env;

	// 配置redis连接池属性
	@Bean
	public JedisPoolConfig poolConfig() {
		JedisPoolConfig result = new JedisPoolConfig();
		result.setMaxIdle(env.getProperty("redis.maxIdle", Integer.class));
		result.setMinIdle(8);
		result.setMaxTotal(env.getProperty("redis.maxActive", Integer.class));
		result.setMaxWaitMillis(env.getProperty("redis.maxWait", Integer.class));
		result.setTestOnBorrow(env.getProperty("redis.testOnBorrow", Boolean.class));
		result.setTestOnReturn(true);
		//Idle时进行连接扫描
		result.setTestWhileIdle(true);
		//表示idle object evitor两次扫描之间要sleep的毫秒数
		result.setTimeBetweenEvictionRunsMillis(30000);
		//表示idle object evitor每次扫描的最多的对象数
		result.setNumTestsPerEvictionRun(10);
		//表示一个对象至少停留在idle状态的最短时间，然后才能被idle object evitor扫描并驱逐；这一项只有在timeBetweenEvictionRunsMillis大于0时才有意义
		result.setMinEvictableIdleTimeMillis(60000);
		return result;

	}
	
	// 配置连接工厂
	@Bean
	public JedisConnectionFactory connectionFactory(JedisPoolConfig poolConfig) {
		JedisConnectionFactory config = new JedisConnectionFactory();
		config.setHostName(env.getProperty("redis.host"));
		config.setPort(env.getProperty("redis.port", Integer.class));
		config.setDatabase(env.getProperty("redis.dbIndex", Integer.class));
		config.setPassword(env.getProperty("redis.password"));
		config.setPoolConfig(poolConfig);
		return config;
	}

	@Bean(name = {"redisTemplate"})
	public RedisTemplate<String, Object> redisTemplate(JedisConnectionFactory connectionFactory) {
		RedisTemplate<String, Object> resut = new RedisTemplate<String, Object>();
		resut.setConnectionFactory(connectionFactory);
		resut.setKeySerializer(new StringRedisSerializer());
		resut.setValueSerializer(new GenericJackson2JsonRedisSerializer());
		return resut;
	}
	
	@Bean
	public RedisUtil redisCacheManager(RedisTemplate<String, Object> redisTemplate) {
		RedisUtil result = new RedisUtil();
		result.setRedisTemplate(redisTemplate);
		return result;
	}
	
	
}
