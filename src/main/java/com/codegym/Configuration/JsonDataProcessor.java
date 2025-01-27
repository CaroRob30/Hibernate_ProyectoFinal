package com.codegym.Configuration;

import com.codegym.Service.Json.JsonService;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.ArrayList;
import java.util.List;

public class JsonDataProcessor {
    private final JsonService jsonService;

    public JsonDataProcessor(JsonService jsonService) {
        this.jsonService = jsonService;
    }

    public <T> List<T> processJsonData(List<String> jsonData, Class<T> clazz) throws JsonProcessingException {
        List<T> result = new ArrayList<>();
        for (String data : jsonData) {
            result.add(jsonService.deserializable(data, clazz));
        }
        return result;
    }
}
