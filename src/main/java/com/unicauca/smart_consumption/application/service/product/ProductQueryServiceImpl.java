package com.unicauca.smart_consumption.application.service.product;

import com.unicauca.smart_consumption.domain.constant.MessagesConstant;
import com.unicauca.smart_consumption.domain.common.ResponseDto;
import com.unicauca.smart_consumption.domain.product.Product;
import com.unicauca.smart_consumption.domain.product.ports.in.IProductQueryService;
import com.unicauca.smart_consumption.domain.product.ports.out.IProductQueryRepository;
import com.unicauca.smart_consumption.infrastructure.exception.BusinessRuleException;
import com.unicauca.smart_consumption.infrastructure.messages.MessageLoader;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ProductQueryServiceImpl implements IProductQueryService {

  private final IProductQueryRepository productQueryRepository;

  @Override
  public ResponseDto<Product> findProductById(final String id) {
    Product product = productQueryRepository.findProductById(id)
        .orElseThrow(() -> new BusinessRuleException(HttpStatus.BAD_REQUEST.value(), MessagesConstant.EM002,
            MessageLoader.getInstance().getMessage(MessagesConstant.EM002, id)));
    return new ResponseDto<>(HttpStatus.OK.value(),
        MessageLoader.getInstance().getMessage(MessagesConstant.IM001), product);
  }

  @Override
  public ResponseDto<List<Product>> findAllProducts() {
    List<Product> products = productQueryRepository.findAllProducts();
    return new ResponseDto<>(HttpStatus.OK.value(),
        MessageLoader.getInstance().getMessage(MessagesConstant.IM001), products);
  }
}
