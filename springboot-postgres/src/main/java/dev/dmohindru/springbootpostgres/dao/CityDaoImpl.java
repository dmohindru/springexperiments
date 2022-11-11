package dev.dmohindru.springbootpostgres.dao;

import dev.dmohindru.springbootpostgres.entity.City;
import dev.dmohindru.springbootpostgres.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Slf4j
@Component
public class CityDaoImpl implements CityDao{

    private final CityRepository cityRepository;

    @Override
    public City findCityById(String id) {
        return cityRepository.findFirstById(id);
    }
}
