package ru.loudbar.dao;


import com.github.fppt.jedismock.RedisServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.JedisPooled;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class StringRepoTest {

    RedisServer redisServer;
    StringRepo stringRepo;
    JedisPooled jedisPooled;

    @BeforeEach
    void setUp() throws IOException {
        redisServer = RedisServer.newRedisServer(8080);
        redisServer.start();
        jedisPooled = new JedisPooled(redisServer.getHost(), redisServer.getBindPort());
        stringRepo = new StringRepo(jedisPooled);
    }

    @Test
    void putStringSuccess() {
        stringRepo.putString("qwerty");
        int id = (int) jedisPooled.dbSize();
        assertEquals("qwerty", jedisPooled.get(String.valueOf(id)));
    }

    @Test
    void retrieveStringSuccess() {
        jedisPooled.set(String.valueOf(jedisPooled.dbSize() + 1), "qwerty");
        int id = (int) jedisPooled.dbSize();
        assertEquals("qwerty", stringRepo.retrieveString(id));
    }

    @Test
    void retrieveStringFail() {
        assertNull(jedisPooled.get("any"));
    }

    @AfterEach
    void tearDown() throws IOException {
        redisServer.stop();
    }
}