package com.jedis;

import com.alibaba.fastjson.JSONObject;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

/**
 * 测试事务
 */
public class TestTX {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("8.210.164.203", 6379);
        //创建JSON数据
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("hello","world");
        jsonObject.put("name","tangxianchen");
        //开启事务
        Transaction multi = jedis.multi();
        String jsonStr = jsonObject.toJSONString();
        try {
            multi.set("user",jsonStr);
            multi.set("user2",jsonStr);
            //执行事务
            multi.exec();
        } catch (Exception e) {
            //失败 放弃事物
            multi.discard();
            e.printStackTrace();
        } finally {
            System.out.println(jedis.get("user"));
            System.out.println(jedis.get("user2"));
            //清空数据
            jedis.flushDB();
            //关闭链接
            jedis.close();
        }


    }
}
