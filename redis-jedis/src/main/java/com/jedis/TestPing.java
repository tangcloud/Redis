package com.jedis;

import redis.clients.jedis.Jedis;

public class TestPing {
    public static void main(String[] args) {
        //创建Jedis对象
        Jedis jedis = new Jedis("8.210.164.203",6379);
        //测试连接
        System.out.println(jedis.ping());

    }
}
