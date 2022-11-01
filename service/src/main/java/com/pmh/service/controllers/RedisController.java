package com.pmh.service.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.data.redis.connection.RedisZSetCommands;
import org.springframework.data.redis.core.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.function.Consumer;

@RestController
public class RedisController {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    public static final String CACHE_PREFIX = "FP:";

    @RequestMapping("/redis")
    public String testRedis(){

        System.out.println("字符串和散列Value和Hash-------------------------------------------------------");
        redisTemplate.opsForValue().set("string_key", "string value");
        System.out.println("-------------set字符串-------------------: " + redisTemplate.opsForValue().get("string_key"));

        redisTemplate.opsForValue().set("int_key", "1");
        System.out.println("-------------set int_key-------------------: " + redisTemplate.opsForValue().get("int_key"));

        stringRedisTemplate.opsForValue().set("int", "1");
        System.out.println("-------------stringRedisTemplate set  int-------------------: " + redisTemplate.opsForValue().get("int"));

        stringRedisTemplate.opsForValue().increment("int", 1);
        System.out.println("-------------使用运算+1-------------------: " + redisTemplate.opsForValue().get("int"));

        Map<String, String> hash = new HashMap<String, String>();
        hash.put("field1", "value1");
        hash.put("field2", "value2");
        stringRedisTemplate.opsForHash().putAll("hash", hash);
        System.out.println("-------------存入一个散列数据类型-------------------: ");
        System.out.println("-------------map 遍历-------------------: ");
        redisTemplate.opsForHash().entries("hash").forEach((k, v) -> {
            System.out.println(k + ": " + v);
        });
        System.out.println("-------------map->set 遍历-------------------: ");
        redisTemplate.opsForHash().keys("hash").forEach(key -> {
            System.out.println(key + ": " + redisTemplate.opsForHash().get("hash", key));
        });
        stringRedisTemplate.opsForHash().put("hash", "field3", "value3");
        System.out.println("-------------新增一个字段 field3-------------------: ");
        redisTemplate.opsForHash().entries("hash").forEach((k, v) -> {
            System.out.println(k + ": " + v);
        });
        BoundHashOperations hashOps = stringRedisTemplate.boundHashOps("hash");
        hashOps.delete("field1", "field2");
        System.out.println("-------------删除两个字段 field1 field2-------------------: ");
        hashOps.entries().forEach((k, v) -> {
            System.out.println(k + ": " + v);
        });
        hashOps.put("field5", "value5");
        System.out.println("-------------新增一个字段 field5-------------------: ");
        hashOps.entries().forEach((k, v) -> {
            System.out.println(k + ": " + v);
        });


        System.out.println("列表（链表）List-------------------------------------------------------");
        stringRedisTemplate.opsForList().leftPushAll("list1", "v2", "v4", "v6", "v8", "v10");
        System.out.println("----------链表从左到右的顺序为v10,v8,v6,v4,v2----------------:");
        stringRedisTemplate.opsForList().range("list1", 0, stringRedisTemplate.opsForList().size("list1") - 1).forEach(s -> System.out.println(s));

        stringRedisTemplate.opsForList().rightPushAll("list2", "v1", "v2", "v3", "v4", "v5", "v6");
        System.out.println("----------从左到右顺序为v1,v2,v3,v4,v5,v6----------------:");
        stringRedisTemplate.opsForList().range("list2", 0, stringRedisTemplate.opsForList().size("list2") - 1).forEach(s -> System.out.println(s));
        BoundListOperations listOps = stringRedisTemplate.boundListOps("list2");
        String result1 = (String)listOps.rightPop();
        System.out.println("----------从右边弹出一个成员----------------: " + result1);
        listOps.range(0, listOps.size() - 1).forEach(s -> System.out.println(s));
        String result2 = (String)listOps.index(1);
        System.out.println("----------获取定位元素,redis从0开始运算----------------: " + result2);
        listOps.range(0, listOps.size() - 1).forEach(s -> System.out.println(s));
        listOps.leftPush("v0");
        System.out.println("----------从左边插入链表----------------: " + "v0");
        listOps.range(0, listOps.size() - 1).forEach(s -> System.out.println(s));
        Long size = listOps.size();
        List elements = listOps.range(0, size - 2);
        System.out.println("----------求链表下标区间成员0->size-2 ----------------: " + "v0");
        elements.forEach(s -> System.out.println(s));





        System.out.println("集合Set示例-------------------------------------------------------");
        stringRedisTemplate.opsForSet().add("set1", "v1", "v1", "v2", "v3", "v4", "v5");
        stringRedisTemplate.opsForSet().add("set2", "v2", "v4", "v6", "v8");
        BoundSetOperations setOps = stringRedisTemplate.boundSetOps("set1");
        setOps.add("v6", "v7");
        setOps.remove("v1", "v7");
        Set set1 = setOps.members();
        size = setOps.size();
        Set inner = setOps.intersect("set2");
        setOps.intersectAndStore("set2", "inner");
        Set diff = setOps.diff("set2");
        setOps.diffAndStore("set2", "diff");
        Set union = setOps.union("set2");
        setOps.unionAndStore("set2", "union");


        Set<ZSetOperations.TypedTuple<String>> typedTupleSet = new HashSet<>();
        for (int i = 1; i <= 9; i++) {
            double score = i * 0.1;
            ZSetOperations.TypedTuple<String> typedTuple = new DefaultTypedTuple<String>("value" + i, score);
            typedTupleSet.add(typedTuple);
        }
        stringRedisTemplate.opsForZSet().add("zset1", typedTupleSet);
        BoundZSetOperations zsetOps = stringRedisTemplate.boundZSetOps("zset1");
        System.out.println("------------init----------------");
        zsetOps.rangeWithScores(0, zsetOps.size() - 1).forEach(new Consumer() {

            @Override
            public void accept(Object t) {
                ZSetOperations.TypedTuple<String> s = (ZSetOperations.TypedTuple)t;
                System.out.println(s.getValue() + " : " + s.getScore());
            }
        });
        zsetOps.add("value10", 0.26);
        System.out.println("------------增加一个元素 value10----------------");
        zsetOps.rangeWithScores(0, zsetOps.size() - 1).forEach(new Consumer() {

            @Override
            public void accept(Object t) {
                ZSetOperations.TypedTuple<String> s = (ZSetOperations.TypedTuple)t;
                System.out.println(s.getValue() + " : " + s.getScore());
            }

        });
        Set<String> setRange = zsetOps.range(1, 6);
        System.out.println("------------获得range 1---6---------------");
        Iterator itor = setRange.iterator();
        if (itor.hasNext()) {
            String s = (String)itor.next();
            System.out.println(s);
        }

        Set<String> setScore = zsetOps.rangeByScore(0.2, 0.6);
        System.out.println("------------按分数排序获得有序集合 (0.2, 0.6)---------------");
        Iterator itor2 = setScore.iterator();
        if (itor.hasNext()) {
            String s = (String)itor.next();
            System.out.println(s);
        }

        RedisZSetCommands.Range range = new RedisZSetCommands.Range();
        range.gt("value3");
        range.lte("value8");
        Set<String> setLex = zsetOps.rangeByLex(range);
        System.out.println("------------自定义范围 (value3, value8)---------------");
        Iterator itor3 = setLex.iterator();
        if (itor.hasNext()) {
            String s = (String)itor.next();
            System.out.println(s);
        }

        zsetOps.remove("value9", "value2");
        System.out.println("------------删除元素 value9, value2---------------");
        zsetOps.rangeWithScores(0, zsetOps.size() - 1).forEach(new Consumer() {

            @Override
            public void accept(Object t) {
                ZSetOperations.TypedTuple<String> s = (ZSetOperations.TypedTuple)t;
                System.out.println(s.getValue() + " : " + s.getScore());
            }

        });

        Double score = zsetOps.score("value8");
        System.out.println("-----------求分数 value8---------------：" + score);
        Set<ZSetOperations.TypedTuple<String>> rangeSet = zsetOps.rangeWithScores(1, 6);
        System.out.println("-----------在下标区间下，按分数排序 (1, 6)---------------");
        rangeSet.forEach(new Consumer() {

            @Override
            public void accept(Object t) {
                ZSetOperations.TypedTuple<String> s = (ZSetOperations.TypedTuple)t;
                System.out.println(s.getValue() + " : " + s.getScore());
            }

        });
        Set<ZSetOperations.TypedTuple<String>> scoreSet = zsetOps.rangeByScoreWithScores(0.1, 0.6);
        System.out.println("-----------在分数区间下，按分数排序 (0.1, 0.6)---------------");
        scoreSet.forEach(new Consumer() {

            @Override
            public void accept(Object t) {
                ZSetOperations.TypedTuple<String> s = (ZSetOperations.TypedTuple)t;
                System.out.println(s.getValue() + " : " + s.getScore());
            }

        });
        Set<String> reverseSet = zsetOps.reverseRange(0, zsetOps.size() - 1);
        System.out.println("-----------按从大到小排序---------------");
        reverseSet.forEach(s -> System.out.println(s));

        System.out.println("redis开启事务--------------------------");
        redisTemplate.opsForValue().set("key1", "value1");
        List list = (List)redisTemplate.execute((RedisOperations operations) -> {
            operations.watch("key1");
            operations.multi();
            operations.opsForValue().set("key2", "value2");
            Object value2 = operations.opsForValue().get("key2");
            System.out.println("命令在队列中，所以key2为null【" + value2 + "】");
            operations.opsForValue().set("key3", "value3");
            System.out.println("命令在队列中，所以key3为null【" + value2 + "】");
            return operations.exec();
        });


        System.out.println("redis流水线--------------------------");
        Long start = System.currentTimeMillis();
        List lineList = redisTemplate.executePipelined((RedisOperations operations) -> {
            for (int i = 1; i <= 100000; i++) {
                operations.opsForValue().set("pipeline_" + i, "value" + i);
                String value = (String)operations.opsForValue().get("pipeline_" + i);
                if (i == 100000) {
                    System.out.println("命令在队列中，所以值为null【" + value + "】");
                }
            }
            return null;
        });
        Long end = System.currentTimeMillis();
        System.out.println("耗时： " + (end - start) + "毫秒");

        return "";
    }
}
