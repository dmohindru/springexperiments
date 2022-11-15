package dev.dmohindru.springbootpostgres.dao;

import dev.dmohindru.springbootpostgres.entity.City;

public interface CityDao {
    City findCityById(String id);
    City saveCity(City city);
}
