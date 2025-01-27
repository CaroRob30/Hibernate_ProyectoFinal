package com.codegym.Service.City;

import com.codegym.dao.CityDAO;
import com.codegym.domain.City;

import java.util.ArrayList;
import java.util.List;

public class CityService {
    private final CityDAO cityDAO;

    public CityService(CityDAO cityDAO) {
        this.cityDAO = cityDAO;
    }

    public List<City> fetchData() {
        List<City> allCities = new ArrayList<>();
        int totalCount = cityDAO.getTotalCount();
        int step = 500;
        for (int i = 0; i < totalCount; i += step) {
            allCities.addAll(cityDAO.getItems(i, step));
        }
        return allCities;
    }
}
