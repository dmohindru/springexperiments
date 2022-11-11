package dev.dmohindru.springbootpostgres.service;

import dev.dmohindru.springbootpostgres.dao.CityDao;
import dev.dmohindru.springbootpostgres.dto.CityDto;
import dev.dmohindru.springbootpostgres.entity.City;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CityServiceImpl implements CityService {

    private final CityDao cityDao;

    @Override
    public CityDto getCityById(String id) {
        City city = cityDao.findCityById(id);
        CityDto cityDto = CityDto.builder()
                .id(city.getId())
                .elevation(city.getAltitude())
                .population(city.getPopulation2010())
                .state(city.getState())
//                .position(
//                        CityDto.Position.builder()
//                        .lat(city.getPoint().getX())
//                        .lng(city.getPoint().getY())
//                        .build()
//                )
                .build();
        return cityDto;
    }
}
