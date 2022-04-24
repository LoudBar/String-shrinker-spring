package ru.loudbar.services;

import com.github.fppt.jedismock.RedisServer;
import org.junit.jupiter.api.*;
import redis.clients.jedis.JedisPooled;
import ru.loudbar.dao.StringRepo;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StringServiceTest {

    RedisServer redisServer;
    StringRepo stringRepo;
    JedisPooled jedisPooled;
    StringService stringService;

    @BeforeEach
    void setUp() throws IOException {
        redisServer = RedisServer.newRedisServer(8080);
        redisServer.start();
        jedisPooled = new JedisPooled(redisServer.getHost(), redisServer.getBindPort());
        stringRepo = new StringRepo(jedisPooled);
        stringService = new StringService(stringRepo);
    }

    @Test
    @Order(1)
    void encodeSuccess() {
        assertEquals("b", stringService.encode("fawefs"));
    }

    @Test
    @Order(2)
    void decodeSuccess() {
        stringRepo.putString("fawefs");
        assertEquals("fawefs", stringService.decode("b"));
    }

    @Test
    void encodeFailEmptyString() {
        assertNull(stringService.decode(""));
    }

    @Test
    void decodeFailWrongString() {
        assertNull(stringService.decode("ewrgthyj"));
    }

    @AfterEach
    void tearDown() throws IOException {
        redisServer.stop();
    }
}