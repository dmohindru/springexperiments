package dev.dmohindru.springbootpostgres.service;

import dev.dmohindru.springbootpostgres.dto.CityDto;

public interface CityService {
    CityDto getCityById(String id);
}
