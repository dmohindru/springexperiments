### Import GeoGSON data to Postgres
ogr2ogr -progress \
-f "PostgreSQL" \
PG:"host=localhost port=5433 dbname=dhruv_testing user=rootdb password=password" \
us_cities.geojson