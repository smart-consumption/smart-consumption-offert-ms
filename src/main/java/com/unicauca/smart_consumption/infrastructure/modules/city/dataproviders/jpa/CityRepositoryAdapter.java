package com.unicauca.smart_consumption.infrastructure.modules.city.dataproviders.jpa;

import com.unicauca.smart_consumption.domain.city.City;
import com.unicauca.smart_consumption.domain.city.ports.out.ICityRepository;
import com.unicauca.smart_consumption.infrastructure.pattern.mapper.CityJPAMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class CityRepositoryAdapter implements ICityRepository {

    private final CityJPARepository cityJPARepository;
    private final CityJPAMapper cityJPAMapper;


    @Override
    public City createCity(City city) {
        CityJPAEntity entity = cityJPAMapper.toTarget(city);
        return cityJPAMapper.toDomain(cityJPARepository.save(entity));
    }

    @Override
    public City updateCity(String id, City city) {
        return cityJPARepository.findById(id)
                .map(cityEntity -> {
                    City domainCity = cityJPAMapper.toDomain(cityEntity);
                    domainCity.update(city.getName(), city.getDepartment());
                    CityJPAEntity updatedEntity = cityJPAMapper.toTarget(domainCity);
                    cityJPARepository.save(updatedEntity);
                    return domainCity;
                })
                .orElseThrow(() -> new EntityNotFoundException("City not found with id " + id));
    }


    @Override
    public void deleteCity(String id) {
        if (cityJPARepository.findById(id).isEmpty()) {
            cityJPARepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("City not found with id " + id);
        }
    }

    @Override
    public Optional<City> findCityById(String id) {
        return cityJPARepository.findById(id).map(cityJPAMapper::toDomain);
    }

    @Override
    public List<City> findAllCities() {
        return cityJPARepository.findAll().stream()
                .map(cityJPAMapper::toDomain).toList();
    }

}
