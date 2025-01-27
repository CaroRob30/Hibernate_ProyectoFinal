package com.codegym.Service.Redis;

import java.util.ArrayList;
import java.util.List;

public class RedisDataFetcher {
    private final RedisService redisService;

    public RedisDataFetcher(RedisService redisService) {
        this.redisService = redisService;
    }

    public List<String> fetchData(List<Integer> ids) {
        List<String> values = new ArrayList<>();
        for (Integer id : ids) {
            values.add(redisService.getValue(String.valueOf(id)));
        }
        return values;
    }
}
