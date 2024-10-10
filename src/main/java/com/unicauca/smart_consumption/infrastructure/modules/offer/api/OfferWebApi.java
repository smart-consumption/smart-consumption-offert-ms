package com.unicauca.smart_consumption.infrastructure.modules.offer.api;

import com.unicauca.smart_consumption.domain.common.ResponseDto;
import com.unicauca.smart_consumption.domain.offer.Offer;
import com.unicauca.smart_consumption.domain.offer.ports.in.IOfferService;
import com.unicauca.smart_consumption.infrastructure.pattern.dto.OfferDto;
import com.unicauca.smart_consumption.infrastructure.pattern.mapper.OfferMapper;

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
@RequestMapping(value = "/offer")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Tag(name = "Offer APIs", description = "Ofer web APIs")
public class OfferWebApi {

    private final IOfferService offerService;
    private final OfferMapper offerMapper;

    @PostMapping("/{storeId}/{productId}")
    public ResponseEntity<ResponseDto<OfferDto>> createOffer(
        @RequestBody OfferDto offerDto,
        @PathVariable String storeId,
        @PathVariable String productId) {
        Offer offer = offerMapper.toDomain(offerDto);
        ResponseDto<Offer> offerResponse = offerService.createOffer(offer, storeId, productId);
        OfferDto createOfferDto = offerMapper.toTarget(offerResponse.getData());
        return new ResponseDto<>(offerResponse.getStatus(),
                offerResponse.getMessage(), createOfferDto).of();
    }
    

    @PutMapping("/{entityId}")
    public ResponseEntity<ResponseDto<OfferDto>> updateOffer(@PathVariable String entityId, @RequestBody OfferDto offerDto) {
        Offer offer = offerMapper.toDomain(offerDto);
        ResponseDto<Offer> offerResponse = offerService.updateOffer(entityId, offer);
        OfferDto updatedOfferDto = offerMapper.toTarget(offerResponse.getData());
        return new ResponseDto<>(offerResponse.getStatus(),
                offerResponse.getMessage(), updatedOfferDto).of();
    }

    @DeleteMapping("/{entityId}")
    public ResponseEntity<ResponseDto<Void>> deleteOffer(@PathVariable String entityId) {
        ResponseDto<Void> offerResponse = offerService.deleteOffer(entityId);
        return new ResponseDto<Void>(offerResponse.getStatus(), offerResponse.getMessage()).of();
    }

    @GetMapping("/{entityId}")
    public ResponseEntity<ResponseDto<OfferDto>> getOfferById(@PathVariable String entityId) {
        ResponseDto<Offer> offerResponse = offerService.findOfferById(entityId);
        OfferDto offerDto = offerMapper.toTarget(offerResponse.getData());
        ResponseDto<OfferDto> offerDtoResponse = new ResponseDto<>(offerResponse.getStatus(), offerResponse.getMessage(), offerDto);
        return offerDtoResponse.of();
    }

    @GetMapping
    public ResponseEntity<ResponseDto<List<OfferDto>>> getAllOffers() {
        ResponseDto<List<Offer>> offerResponse = offerService.findAllOffers();
        return new ResponseDto<>(
                offerResponse.getStatus(),
                offerResponse.getMessage(),
                offerResponse.getData().stream().map(offerMapper::toTarget).toList()
        ).of();
    }

}
