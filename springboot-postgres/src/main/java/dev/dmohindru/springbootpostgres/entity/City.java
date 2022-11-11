package dev.dmohindru.springbootpostgres.entity;

import com.vividsolutions.jts.geom.Point;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "us_cities")
public class City {

    @Id
    @Column(name = "ogc_fid")
    @SequenceGenerator(name = "city_table_seq_gen", sequenceName = "CITY_TABLE_SEQ_GEN", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="city_table_seq_gen")
    private Long ogcFid;

    @Column(name = "id")
    private String id;

    @Column(name = "pop_2010")
    private Double population2010;

    @Column(name="elev_in_ft")
    private Double altitude;

    @Column(name="state")
    private String state;

//    @Column(columnDefinition = "geography", name = "wkb_geometry")
//    private Point point;


}
