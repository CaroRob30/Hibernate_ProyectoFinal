package com.codegym.Service.Redis;

import java.util.List;

public class RedisDataTester {
    private final RedisDataFetcher redisDataFetcher;

    public RedisDataTester(RedisDataFetcher redisDataFetcher) {
        this.redisDataFetcher = redisDataFetcher;
    }

    public void fetchRedisData(List<Integer> ids) {
        redisDataFetcher.fetchData(ids);
    }
}
