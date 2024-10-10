package com.unicauca.smart_consumption.infrastructure.modules.store.api;

import com.unicauca.smart_consumption.domain.common.ResponseDto;
import com.unicauca.smart_consumption.domain.store.Store;
import com.unicauca.smart_consumption.domain.store.ports.in.IStoreService;
import com.unicauca.smart_consumption.infrastructure.pattern.dto.StoreDto;
import com.unicauca.smart_consumption.infrastructure.pattern.mapper.StoreMapper;

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
@RequestMapping(value = "/store")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Tag(name = "Store APIs", description = "Store web APIs")
public class StoreWebApi {

    private final IStoreService storeService;
    private final StoreMapper storeMapper;

    @PostMapping
    public ResponseEntity<ResponseDto<StoreDto>> createStore(@RequestBody StoreDto storeDto) {
        Store store = storeMapper.toDomain(storeDto);
        ResponseDto<Store> storeResponse = storeService.createStore(store);
        StoreDto createStoreDto = storeMapper.toTarget(storeResponse.getData());
        return new ResponseDto<>(storeResponse.getStatus(),
                storeResponse.getMessage(), createStoreDto).of();
    }

    @PutMapping("/{entityId}")
    public ResponseEntity<ResponseDto<StoreDto>> updateStore(@PathVariable String entityId, @RequestBody StoreDto storeDto) {
        Store store = storeMapper.toDomain(storeDto);
        ResponseDto<Store> storeResponse = storeService.updateStore(entityId, store);
        StoreDto updatedStoreDto = storeMapper.toTarget(storeResponse.getData());
        return new ResponseDto<>(storeResponse.getStatus(),
                storeResponse.getMessage(), updatedStoreDto).of();
    }

    @DeleteMapping("/{entityId}")
    public ResponseEntity<ResponseDto<Void>> deleteStore(@PathVariable String entityId) {
        ResponseDto<Void> storeResponse = storeService.deleteStore(entityId);
        return new ResponseDto<Void>(storeResponse.getStatus(), storeResponse.getMessage()).of();
    }

    @GetMapping("/{entityId}")
    public ResponseEntity<ResponseDto<StoreDto>> getStoreById(@PathVariable String entityId) {
        ResponseDto<Store> storeResponse = storeService.findStoreById(entityId);
        StoreDto storeDto = storeMapper.toTarget(storeResponse.getData());
        ResponseDto<StoreDto> storeDtoResponse = new ResponseDto<>(storeResponse.getStatus(), storeResponse.getMessage(), storeDto);
        return storeDtoResponse.of();
    }

    @GetMapping
    public ResponseEntity<ResponseDto<List<StoreDto>>> getAllStores() {
        ResponseDto<List<Store>> storeResponse = storeService.findAllStores();
        return new ResponseDto<>(
                storeResponse.getStatus(),
                storeResponse.getMessage(),
                storeResponse.getData().stream().map(storeMapper::toTarget).toList()
        ).of();
    }

    @PostMapping("/{storeId}/products")
    public ResponseEntity<ResponseDto<StoreDto>>  addProductsToStore(@PathVariable String storeId, @RequestBody List<String> productIds) {
        ResponseDto<Store> storeResponse = storeService.addProductsToStore(storeId, productIds);
        StoreDto storeDto = storeMapper.toTarget(storeResponse.getData());
        return new ResponseDto<>(storeResponse.getStatus(), storeResponse.getMessage(), storeDto).of();
    }


}
