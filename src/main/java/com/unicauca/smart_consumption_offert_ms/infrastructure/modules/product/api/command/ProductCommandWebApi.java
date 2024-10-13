package com.unicauca.smart_consumption_offert_ms.infrastructure.modules.product.api.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unicauca.smart_consumption_offert_ms.domain.common.ResponseDto;
import com.unicauca.smart_consumption_offert_ms.domain.product.Product;
import com.unicauca.smart_consumption_offert_ms.domain.product.ports.in.IProductCommandService;
import com.unicauca.smart_consumption_offert_ms.infrastructure.pattern.dto.ProductDto;
import com.unicauca.smart_consumption_offert_ms.infrastructure.pattern.mapper.ProductMapper;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping(value = "/product-command")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Tag(name = "Product command CQRS APIs", description = "Product web APIs for command services")
public class ProductCommandWebApi {

    private final IProductCommandService productCommandService;
    private final ProductMapper productMapper;

    @PostMapping
    public ResponseEntity<ResponseDto<ProductDto>> createProduct(@RequestBody ProductDto productDto) {
        Product product =  productMapper.toDomain(productDto);
        ResponseDto<Product> productResponse = productCommandService.createProduct(product);
        ProductDto createdProductDto = productMapper.toTarget(productResponse.getData());
        return new ResponseDto<>(productResponse.getStatus(),
                productResponse.getMessage(), createdProductDto).of();
    }
    
}
