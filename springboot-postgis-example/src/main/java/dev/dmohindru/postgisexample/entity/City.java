package dev.dmohindru.postgisexample.entity;

import com.vividsolutions.jts.geom.Point;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity(name = "us_cities")
public class City {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "pop_2010")
    private Long population2010;

    @Column(name="elev_in_ft")
    private Long altitude;

    @Column(columnDefinition = "geography", name = "wkb_geometry")
    private Point point;


}
