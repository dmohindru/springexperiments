package dev.dmohindru.springbootpostgres.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.Point;
import org.n52.jackson.datatype.jts.GeometryDeserializer;
import org.n52.jackson.datatype.jts.GeometrySerializer;
import javax.persistence.*;

@Data
@Entity(name = "us_cities")
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

    @Column(name = "wkb_geometry", columnDefinition = "POINT")
    @JsonSerialize(using = GeometrySerializer.class)
    @JsonDeserialize(contentUsing = GeometryDeserializer.class)
    private Point point;


}
