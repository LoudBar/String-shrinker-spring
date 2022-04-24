package ru.loudbar.config;

import com.github.fppt.jedismock.RedisServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import redis.clients.jedis.JedisPooled;

import java.io.IOException;

@Configuration
public class Config {

    @Value("${redis.port:6379}")
    private int port;

    @Value("${redis.host:localhost}")
    private String host;

    @Bean("jedisPooled")
    @Primary
    public JedisPooled JedisPooled() {
        return new JedisPooled(host, port);
    }
}
