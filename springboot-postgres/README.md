## Springboot and Postgres simple crud example project


### Import GeoGSON data to Postgris
```
ogr2ogr -progress \
-f "PostgreSQL" \
PG:"host=localhost port=5433 dbname=dhruv_testing user=rootdb password=password" \
us_cities.geojson
```

### Important Links
- [Followed this link for serialize and deserialize Geometry type](https://gis.stackexchange.com/questions/32125/hibernate-spatial-mappingexception-for-geometry-column)
