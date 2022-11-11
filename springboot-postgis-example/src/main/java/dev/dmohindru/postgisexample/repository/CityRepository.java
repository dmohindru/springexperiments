package dev.dmohindru.postgisexample.repository;

import com.vividsolutions.jts.geom.Point;
import dev.dmohindru.postgisexample.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

    @Query(value = "SELECT * FROM us_cities where ST_DistanceSphere(geom, :p) < :distanceM", nativeQuery = true)
    List<City> findNearWithinDistance(Point p, double distanceM);
}
