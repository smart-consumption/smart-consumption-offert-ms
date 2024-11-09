package com.unicauca.smart_consumption_offert_ms.infrastructure.modules.offer.dataproviders.jpa;

import com.unicauca.smart_consumption_offert_ms.domain.offer.Offer;
import com.unicauca.smart_consumption_offert_ms.domain.offer.ports.IOfferEventPublisher;
import com.unicauca.smart_consumption_offert_ms.domain.offer.ports.out.IOfferRepository;
import com.unicauca.smart_consumption_offert_ms.domain.offer.Period;
import com.unicauca.smart_consumption_offert_ms.infrastructure.pattern.mapper.OfferJPAMapper;
import com.unicauca.smart_consumption_offert_ms.infrastructure.pattern.mapper.ProductJpaEntityMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class OfferRepositoryAdapter implements IOfferRepository {

    private final OfferJPARepository offerJPARepository;
    private final OfferJPAMapper offerJPAMapper;
    private final ProductJpaEntityMapper productJpaEntityMapper;
    private final IOfferEventPublisher offerEventPublisher;
    private final ProductJpaEntityMapper productMapper;

    @Override
    public Offer createOffer(Offer offer) {
        OfferJPAEntity entity = offerJPAMapper.toTarget(offer);
        final var offerCreated = offerJPAMapper.toDomain(offerJPARepository.save(entity));
        offerEventPublisher.publishOffertCreated(offerCreated);
        return offerCreated;
    }

    
    @Override
    public Offer updateOffer(String id, Offer offer) {
        OfferJPAEntity offerJPAEntity = offerJPARepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Offer not found with id " + id));
        Offer domainOffer = offerJPAMapper.toDomain(offerJPAEntity);
        domainOffer.setDescription(offer.getDescription());
        domainOffer.setDiscountPercentage(offer.getDiscountPercentage());
        domainOffer.setPeriod(offer.getPeriod());
        domainOffer.setProduct(productMapper.toDomain(offerJPAEntity.getProduct()));
        OfferJPAEntity updatedEntity = offerJPAMapper.toTarget(domainOffer);
        offerJPARepository.save(updatedEntity);
        final var offerUpdate = offerJPAMapper.toDomain(updatedEntity);
        offerEventPublisher.publishOffertUpdated(offerUpdate);
        return offerUpdate;
    }


    @Override
    public void deleteOffer(String id) {
        if (offerJPARepository.findById(id).isPresent()) {
            offerJPARepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Offer not found with id " + id);
        }
    }

    @Override
    public Optional<Offer> findOfferById(String id) {
        return offerJPARepository.findById(id).map(entity -> {
            Offer offer = new Offer();
            offer.setId(entity.getId());
            offer.setDescription(entity.getDescription());
            offer.setDiscountPercentage(entity.getDiscountPercentage());
            offer.setDiscountedPrice(entity.getDiscountedPrice());
            offer.setPeriod(new Period(entity.getPeriod().getStartDate()
                    ,entity.getPeriod().getEndDate()));
            offer.setProduct(productJpaEntityMapper.toDomain(entity.getProduct()));
            return offer;}
        );
    }

    @Override
    public List<Offer> findAllOffers() {
        return offerJPARepository.findAll().stream()
                .map(entity -> {
                    Offer offer = new Offer();
                    offer.setId(entity.getId());
                    offer.setDescription(entity.getDescription());
                    offer.setDiscountPercentage(entity.getDiscountPercentage());
                    offer.setDiscountedPrice(entity.getDiscountedPrice());
                    offer.setPeriod(new Period(entity.getPeriod().getStartDate()
                            ,entity.getPeriod().getEndDate()));
                    offer.setProduct(productJpaEntityMapper.toDomain(entity.getProduct()));
                    return offer;
                })
                .collect(Collectors.toList());
    }

}
