package dev.dmohindru.springbootpostgres.service;

import dev.dmohindru.springbootpostgres.dao.CityDao;
import dev.dmohindru.springbootpostgres.dto.CityDto;
import dev.dmohindru.springbootpostgres.entity.City;
import dev.dmohindru.springbootpostgres.exceptions.DuplicateException;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CityServiceImpl implements CityService {

    private final CityDao cityDao;

    private GeometryFactory geoFactory = new GeometryFactory();

    @Override
    public CityDto getCityById(String id) {
        City city = cityDao.findCityById(id);
        CityDto cityDto = CityDto.builder()
                .id(city.getId())
                .elevation(city.getAltitude())
                .population(city.getPopulation2010())
                .state(city.getState())
                .position(
                        CityDto.Position.builder()
                        .lat(city.getPoint().getX())
                        .lng(city.getPoint().getY())
                        .build()
                )
                .build();
        return cityDto;
    }

    // TODO: Wrtie logic to create City and City.Point user GeometryFactory method
    @Override
    public CityDto saveCity(CityDto cityDto) {

        // Throw exception if city is present with same id
        City foundCity = cityDao.findCityById(cityDto.getId());
        if (foundCity != null) {
            throw new DuplicateException(String.format("City with Id %s already present", cityDto.getId()));
        }

        // Create city object
        City city = City
                .builder()
                .id(cityDto.getId())
                .altitude(cityDto.getElevation())
                .population2010(cityDto.getPopulation())
                .state(cityDto.getState())
                .point(geoFactory.createPoint(
                        new Coordinate(cityDto.getPosition().getLat(),
                                cityDto.getPosition().getLng()))
                )
                .build();

        City savedCity = cityDao.saveCity(city);

        CityDto saveCityDto = CityDto.builder()
                .id(savedCity.getId())
                .elevation(savedCity.getAltitude())
                .population(savedCity.getPopulation2010())
                .state(savedCity.getState())
                .position(CityDto.Position.builder()
                        .lat(savedCity.getPoint().getX())
                        .lng(savedCity.getPoint().getY())
                        .build()

                )
                .build();

        return saveCityDto;
    }
}
