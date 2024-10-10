package com.unicauca.smart_consumption.application.service.city;

import com.unicauca.smart_consumption.domain.city.City;
import com.unicauca.smart_consumption.domain.city.ports.in.ICityService;
import com.unicauca.smart_consumption.domain.city.ports.out.ICityRepository;
import com.unicauca.smart_consumption.domain.common.ResponseDto;
import com.unicauca.smart_consumption.domain.constant.MessagesConstant;
import com.unicauca.smart_consumption.infrastructure.exception.BusinessRuleException;
import com.unicauca.smart_consumption.infrastructure.messages.MessageLoader;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class CityServiceImpl implements ICityService {
    private final ICityRepository cityRepository;

    @Override
    public ResponseDto<City> createCity(City city) {
        City createdCity = cityRepository.createCity(city);
        return new ResponseDto<>(HttpStatus.CREATED.value(),
                MessageLoader.getInstance().getMessage(MessagesConstant.IM002), createdCity);
    }

    @Override
    public ResponseDto<City> updateCity(String id, City city) {
        City updatedCity = cityRepository.updateCity(id, city);
        return new ResponseDto<>(HttpStatus.OK.value(),
                MessageLoader.getInstance().getMessage(MessagesConstant.IM003), updatedCity);
    }

    @Override
    public ResponseDto<Void> deleteCity(String id) {
        if (cityRepository.findCityById(id).isEmpty()) {
            cityRepository.deleteCity(id);
            return new ResponseDto<>(HttpStatus.NO_CONTENT.value(),
                    MessageLoader.getInstance().getMessage(MessagesConstant.IM004));
        } else {
            throw new BusinessRuleException(HttpStatus.BAD_REQUEST.value(), MessagesConstant.EM002,
                    MessageLoader.getInstance().getMessage(MessagesConstant.EM002, id));
        }
    }

    @Override
    public ResponseDto<City> findCityById(final String id) {
        City city = cityRepository.findCityById(id)
                .orElseThrow(() -> new BusinessRuleException(HttpStatus.BAD_REQUEST.value(), MessagesConstant.EM002,
                        MessageLoader.getInstance().getMessage(MessagesConstant.EM002, id)));
        return new ResponseDto<>(HttpStatus.OK.value(),
                MessageLoader.getInstance().getMessage(MessagesConstant.IM001), city);
    }

    @Override
    public ResponseDto<List<City>> findAllCities() {
        List<City> cities = cityRepository.findAllCities();
        return new ResponseDto<>(HttpStatus.OK.value(),
                MessageLoader.getInstance().getMessage(MessagesConstant.IM001), cities);
    }

}
