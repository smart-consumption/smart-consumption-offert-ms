package com.unicauca.smart_consumption.infrastructure.modules.city.api;

import com.unicauca.smart_consumption.domain.city.City;
import com.unicauca.smart_consumption.domain.city.ports.in.ICityService;
import com.unicauca.smart_consumption.domain.common.ResponseDto;
import com.unicauca.smart_consumption.infrastructure.pattern.dto.CityDto;
import com.unicauca.smart_consumption.infrastructure.pattern.mapper.CityMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RestController
@RequestMapping(value = "/city")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Tag(name = "City APIs", description = "City web APIs")
public class CityWebApi {

    private final ICityService cityService;
    private final CityMapper cityMapper;

    @PostMapping
    public ResponseEntity<ResponseDto<CityDto>> createCity(@RequestBody CityDto cityDto) {
        City city = cityMapper.toDomain(cityDto);
        ResponseDto<City> cityResponse = cityService.createCity(city);
        CityDto createCityDto = cityMapper.toTarget(cityResponse.getData());
        return new ResponseDto<>(cityResponse.getStatus(),
                cityResponse.getMessage(), createCityDto).of();
    }

    @PutMapping("/{entityId}")
    public ResponseEntity<ResponseDto<CityDto>> updateCity(@PathVariable String entityId, @RequestBody CityDto cityDto) {
        City city = cityMapper.toDomain(cityDto);
        ResponseDto<City> cityResponse = cityService.updateCity(entityId, city);
        CityDto updatedCityDto = cityMapper.toTarget(cityResponse.getData());
        return new ResponseDto<>(cityResponse.getStatus(),
                cityResponse.getMessage(), updatedCityDto).of();
    }

    @DeleteMapping("/{entityId}")
    public ResponseEntity<ResponseDto<Void>> deleteCity(@PathVariable String entityId) {
        ResponseDto<Void> cityResponse = cityService.deleteCity(entityId);
        return new ResponseDto<Void>(cityResponse.getStatus(), cityResponse.getMessage()).of();
    }

    @GetMapping("/{entityId}")
    public ResponseEntity<ResponseDto<CityDto>> getCityById(@PathVariable String entityId) {
        ResponseDto<City> cityResponse = cityService.findCityById(entityId);
        CityDto cityDto = cityMapper.toTarget(cityResponse.getData());
        ResponseDto<CityDto> cityDtoResponse = new ResponseDto<>(cityResponse.getStatus(), cityResponse.getMessage(), cityDto);
        return cityDtoResponse.of();
    }

    @GetMapping
    public ResponseEntity<ResponseDto<List<CityDto>>> getAllCities() {
        ResponseDto<List<City>> cityResponse = cityService.findAllCities();
        return new ResponseDto<>(
                cityResponse.getStatus(),
                cityResponse.getMessage(),
                cityResponse.getData().stream().map(cityMapper::toTarget).toList()
        ).of();
    }

}
