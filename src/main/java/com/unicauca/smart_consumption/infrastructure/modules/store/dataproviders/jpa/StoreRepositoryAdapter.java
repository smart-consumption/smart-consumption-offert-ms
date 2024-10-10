package com.unicauca.smart_consumption.infrastructure.modules.store.dataproviders.jpa;

import com.unicauca.smart_consumption.domain.store.Store;
import com.unicauca.smart_consumption.domain.store.ports.out.IStoreRepository;
import com.unicauca.smart_consumption.infrastructure.pattern.mapper.StoreJPAMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class StoreRepositoryAdapter implements IStoreRepository {

    private final StoreJPARepository storeRepository;
    private final StoreJPAMapper storeJPAMapper;


    @Override
    public Store createStore(Store store) {
        StoreJPAEntity entity = storeJPAMapper.toTarget(store);
        return storeJPAMapper.toDomain(storeRepository.save(entity));
    }

    @Override
    public Store updateStore(String id, Store store) {
        return storeRepository.findById(id)
                .map(storeEntity -> {
                    Store domainStore = storeJPAMapper.toDomain(storeEntity);
                    domainStore.updateStore(store.getName(), store.getCity());
                    domainStore.setProducts(store.getProducts());
                    domainStore.setOffers(store.getOffers());
                    StoreJPAEntity updatedEntity = storeJPAMapper.toTarget(domainStore);
                    storeRepository.save(updatedEntity);
                    return domainStore;
                })
                .orElseThrow(() -> new EntityNotFoundException("Store not found with id " + id));
    }


    @Override
    public void deleteStore(String id) {
        if (storeRepository.findById(id).isEmpty()) {
            storeRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Store not found with id " + id);
        }
    }

    @Override
    public Optional<Store> findStoreById(String id) {
        return storeRepository.findById(id).map(storeJPAMapper::toDomain);
    }

    @Override
    public List<Store> findAllStores() {
        return storeRepository.findAll().stream()
                .map(storeJPAMapper::toDomain).toList();
    }

}
