package ru.loudbar.dao;


import com.github.fppt.jedismock.RedisServer;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.JedisPooled;

import java.io.IOException;

@RunWith(SpringRunner.class)
public class StringRepoTest {

    RedisServer redisServer;
    StringRepo stringRepo;
    JedisPooled jedisPooled;

    @Before
    public void setUp() throws IOException {
        redisServer = RedisServer.newRedisServer(8080);
        redisServer.start();
        jedisPooled = new JedisPooled(redisServer.getHost(), redisServer.getBindPort());
        stringRepo = new StringRepo(jedisPooled);
    }

    @Test
    public void putStringSuccess() {
        stringRepo.putString("qwerty");
        int id = (int) jedisPooled.dbSize();
        Assert.assertEquals("qwerty", jedisPooled.get(String.valueOf(id)));
    }

    @Test
    public void retrieveStringSuccess() {
        jedisPooled.set(String.valueOf(jedisPooled.dbSize() + 1), "qwerty");
        int id = (int) jedisPooled.dbSize();
        Assert.assertEquals("qwerty", stringRepo.retrieveString(id));
    }

    @Test
    public void retrieveStringFail() {
        Assert.assertNull(jedisPooled.get("any"));
    }

    @After
    public void tearDown() throws IOException {
        redisServer.stop();
    }
}