package dev.dmohindru.postgisexample.service;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.PrecisionModel;
import dev.dmohindru.postgisexample.entity.City;
import dev.dmohindru.postgisexample.repository.CityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    // WGS-84 SRID
    private GeometryFactory factory = new GeometryFactory(new PrecisionModel(), 4326);

    public Page<City> findAll(Pageable page){
        return cityRepository.findAll(page);
//        return (Page<City>) cityRepository.findAll();
    }

    public List<City> findAround(double lat, double lon, double distanceM){
        log.info("Looking for city around ({},{}) withing {} meters", lat, lon, distanceM);
        Point p = factory.createPoint(new Coordinate(lon, lat));
        return cityRepository.findNearWithinDistance(p, distanceM);
    }
}
