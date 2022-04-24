package ru.loudbar.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPooled;

@Repository
@RequiredArgsConstructor
public class StringRepo {

    private final JedisPooled jedis;

    public int putString(String string) {
        int id = (int) jedis.dbSize() + 1;
        jedis.set(String.valueOf(id), string);
        return id;
    }

    public String retrieveString(int id) {
        return jedis.get(String.valueOf(id));
    }

    public int getDbSize() {
        return (int) jedis.dbSize() + 1; }
}
