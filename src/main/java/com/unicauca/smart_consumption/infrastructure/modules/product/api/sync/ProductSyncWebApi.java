package com.unicauca.smart_consumption.infrastructure.modules.product.api.sync;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unicauca.smart_consumption.domain.common.ResponseDto;
import com.unicauca.smart_consumption.domain.constant.MessagesConstant;
import com.unicauca.smart_consumption.infrastructure.messages.MessageLoader;

@RestController
@RequestMapping("/sync")
@RequiredArgsConstructor
@Tag(name = "Product Sync APIs", description = "For synchronize products DBs")
public class ProductSyncWebApi {

    private final ProductSyncService productSyncService;

    @PostMapping
    public ResponseDto<String> syncProducts() {
        productSyncService.syncProducts();
        return new ResponseDto<>(HttpStatus.valueOf(200).value(),
            MessageLoader.getInstance().getMessage(MessagesConstant.IM002));
    }
}
