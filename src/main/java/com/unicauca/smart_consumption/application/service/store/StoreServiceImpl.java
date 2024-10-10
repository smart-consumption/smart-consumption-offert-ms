package com.unicauca.smart_consumption.application.service.store;

import com.unicauca.smart_consumption.domain.common.ResponseDto;
import com.unicauca.smart_consumption.domain.constant.MessagesConstant;
import com.unicauca.smart_consumption.domain.product.Product;
import com.unicauca.smart_consumption.domain.product.ports.out.IProductQueryRepository;
import com.unicauca.smart_consumption.domain.store.Store;
import com.unicauca.smart_consumption.domain.store.ports.in.IStoreService;
import com.unicauca.smart_consumption.domain.store.ports.out.IStoreRepository;
import com.unicauca.smart_consumption.infrastructure.exception.BusinessRuleException;
import com.unicauca.smart_consumption.infrastructure.messages.MessageLoader;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class StoreServiceImpl implements IStoreService {

    private final IStoreRepository storeRepository;
    private final IProductQueryRepository productRepository;

    @Override
    public ResponseDto<Store> createStore(Store store) {
        Store createdStore = storeRepository.createStore(store);
        return new ResponseDto<>(HttpStatus.CREATED.value(),
                MessageLoader.getInstance().getMessage(MessagesConstant.IM002), createdStore);
    }

    @Override
    public ResponseDto<Store> updateStore(String id, Store store) {
        Store updatedStore = storeRepository.updateStore(id, store);
        return new ResponseDto<>(HttpStatus.OK.value(),
                MessageLoader.getInstance().getMessage(MessagesConstant.IM003), updatedStore);
    }

    @Override
    public ResponseDto<Void> deleteStore(String id) {
        if (storeRepository.findStoreById(id).isEmpty()) {
            storeRepository.deleteStore(id);
            return new ResponseDto<>(HttpStatus.NO_CONTENT.value(),
                    MessageLoader.getInstance().getMessage(MessagesConstant.IM004));
        } else {
            throw new BusinessRuleException(HttpStatus.BAD_REQUEST.value(), MessagesConstant.EM002,
                    MessageLoader.getInstance().getMessage(MessagesConstant.EM002, id));
        }
    }

    @Override
    public ResponseDto<Store> findStoreById(final String id) {
        Store store = storeRepository.findStoreById(id)
                .orElseThrow(() -> new BusinessRuleException(HttpStatus.BAD_REQUEST.value(), MessagesConstant.EM002,
                        MessageLoader.getInstance().getMessage(MessagesConstant.EM002, id)));
        return new ResponseDto<>(HttpStatus.OK.value(),
                MessageLoader.getInstance().getMessage(MessagesConstant.IM001), store);
    }

    @Override
    public ResponseDto<List<Store>> findAllStores() {
        List<Store> stores = storeRepository.findAllStores();
        return new ResponseDto<>(HttpStatus.OK.value(),
                MessageLoader.getInstance().getMessage(MessagesConstant.IM001), stores);
    }

    @Override
    public ResponseDto<Store> addProductsToStore(String storeId, List<String> productIds) {
        Store store = storeRepository.findStoreById(storeId)
                .orElseThrow(() -> new BusinessRuleException(HttpStatus.BAD_REQUEST.value(), MessagesConstant.EM002,
                        MessageLoader.getInstance().getMessage(MessagesConstant.EM002, storeId)));

        List<Product> products = this.productRepository.findAllByIdIn(productIds);
        store.addProducts(products);
        store = storeRepository.updateStore(storeId, store);
        return new ResponseDto<>(HttpStatus.OK.value(),
                MessageLoader.getInstance().getMessage(MessagesConstant.IM002), store);
    }


}
