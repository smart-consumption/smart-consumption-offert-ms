package com.unicauca.smart_consumption_offert_ms.application.service.product;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.unicauca.smart_consumption_offert_ms.domain.common.ResponseDto;
import com.unicauca.smart_consumption_offert_ms.domain.constant.MessagesConstant;
import com.unicauca.smart_consumption_offert_ms.domain.product.Product;
import com.unicauca.smart_consumption_offert_ms.domain.product.ports.in.IProductCommandService;
import com.unicauca.smart_consumption_offert_ms.domain.product.ports.out.IProductCommandRepository;
import com.unicauca.smart_consumption_offert_ms.infrastructure.messages.MessageLoader;
import lombok.RequiredArgsConstructor;
import com.unicauca.smart_consumption_offert_ms.infrastructure.config.RabbitMQConfig;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Service
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:4200","http://localhost:4200/new"})
public class ProductServiceImpl implements IProductCommandService{

    private final IProductCommandRepository productCommandRepository;

    @Override
    @RabbitListener(queues = {RabbitMQConfig.PRODUCT_CREATED_QUEUE,
            RabbitMQConfig.PRODUCT_UPDATED_QUEUE})

    public void createProduct(Product product) {
        Product productNew = productCommandRepository.createProduct(product);
        System.out.println("product from ms_product" + productNew.toString());
    }

    @Override
    public ResponseDto<Product> getProduct(String productId) {
        Product product = productCommandRepository.getProduct(productId);
        return new ResponseDto<>(HttpStatus.OK.value(),
                MessageLoader.getInstance().getMessage(MessagesConstant.IM002), product);
    }

    @Override
    public ResponseDto<List<Product>>getAllProducts() {
        List<Product> products = productCommandRepository.getAllProducts();
        return new ResponseDto<>(HttpStatus.OK.value(),
                MessageLoader.getInstance().getMessage(MessagesConstant.IM002),products);
    }


}
