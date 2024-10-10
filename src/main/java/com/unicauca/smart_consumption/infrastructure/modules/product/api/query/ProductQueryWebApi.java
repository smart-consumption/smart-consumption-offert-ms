package com.unicauca.smart_consumption.infrastructure.modules.product.api.query;

import com.unicauca.smart_consumption.domain.common.ResponseDto;
import com.unicauca.smart_consumption.domain.product.Product;
import com.unicauca.smart_consumption.domain.product.ports.in.IProductQueryService;
import com.unicauca.smart_consumption.infrastructure.pattern.dto.ProductDto;
import com.unicauca.smart_consumption.infrastructure.pattern.mapper.ProductMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Exposes remote rest api for feeding file name management config Dashboard.
 *
 * @author carvajal
 * @version 1.0
 * @since 2020-04-13
 */
@Log4j2
@RestController
@RequestMapping(value = "/product-query")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Tag(name = "Product query CQRS APIs", description = "Product web APIs for query services")
public class ProductQueryWebApi {

  private final IProductQueryService productQueryService;
  private final ProductMapper productMapper;

  @GetMapping("/{entityId}")
  public ResponseEntity<ResponseDto<ProductDto>> getProductById(@PathVariable String entityId) {
    ResponseDto<Product> productResponse = productQueryService.findProductById(entityId);
    ProductDto productMongoDto = productMapper.toTarget(productResponse.getData());
    ResponseDto<ProductDto> productDtoResponse = new ResponseDto<>(productResponse.getStatus(), productResponse.getMessage(), productMongoDto);
    return productDtoResponse.of();
  }

  @GetMapping
  public ResponseEntity<ResponseDto<List<ProductDto>>> getAllProducts() {
    ResponseDto<List<Product>> productResponse = productQueryService.findAllProducts();
    return new ResponseDto<>(
        productResponse.getStatus(),
        productResponse.getMessage(),
        productResponse.getData().stream()
            .map(productMapper::toTarget)
            .toList()
    ).of();
  }
}
