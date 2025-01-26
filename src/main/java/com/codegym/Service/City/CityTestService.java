package com.codegym.Service.City;

import com.codegym.dao.CityDAO;
import com.codegym.domain.City;

import java.util.List;

public class CityTestService {
    private final CityDAO cityDAO;

    public CityTestService(CityDAO cityDAO) {
        this.cityDAO = cityDAO;
    }

    public void testMysqlData(List<Integer> ids) {
        for (Integer id : ids) {
            City city = cityDAO.getById(id);
            city.getCountry().getLanguages();
        }
    }
}
