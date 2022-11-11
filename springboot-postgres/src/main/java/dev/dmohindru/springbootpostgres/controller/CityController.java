package dev.dmohindru.springbootpostgres.controller;

import dev.dmohindru.springbootpostgres.dto.CityDto;
import dev.dmohindru.springbootpostgres.entity.City;
import dev.dmohindru.springbootpostgres.exceptions.InvalidRequestParameters;
import dev.dmohindru.springbootpostgres.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/city")
@RequiredArgsConstructor
public class CityController {

    private final CityService cityService;

    @RequestMapping(method = RequestMethod.GET)
    CityDto getCityById(@RequestParam(name = "id", required = false) String id) {
        if (id != null)
            return cityService.getCityById(id);
        throw new InvalidRequestParameters("Invalid request parameter for city controller");
    }

    @RequestMapping(method = RequestMethod.POST)
    CityDto saveCity(@RequestBody CityDto cityDto) {
        return cityService.saveCity(cityDto);
    }
}
