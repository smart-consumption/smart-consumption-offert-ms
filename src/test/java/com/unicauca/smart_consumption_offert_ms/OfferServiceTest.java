package com.unicauca.smart_consumption_offert_ms;

import com.unicauca.smart_consumption_offert_ms.application.service.offer.NotifyUsers;
import com.unicauca.smart_consumption_offert_ms.application.service.offer.OfferServiceImpl;
import com.unicauca.smart_consumption_offert_ms.application.service.product.ProductServiceImpl;
import com.unicauca.smart_consumption_offert_ms.domain.common.ResponseDto;
import com.unicauca.smart_consumption_offert_ms.domain.offer.Offer;
import com.unicauca.smart_consumption_offert_ms.domain.offer.Period;
import com.unicauca.smart_consumption_offert_ms.domain.offer.ports.out.IOfferRepository;
import com.unicauca.smart_consumption_offert_ms.domain.product.Product;
import com.unicauca.smart_consumption_offert_ms.domain.product.ports.in.IProductCommandService;
import com.unicauca.smart_consumption_offert_ms.domain.product.ports.out.IProductCommandRepository;
import com.unicauca.smart_consumption_offert_ms.infrastructure.modules.offer.dataproviders.jpa.OfferRepositoryAdapter;
import com.unicauca.smart_consumption_offert_ms.infrastructure.modules.product.dataproviders.command.sql.ProductJpaCommandRepositoryAdapter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OfferServiceTest {

    @Mock
    private IOfferRepository offerRepository;
    @Mock
    private IProductCommandService productCommandService;
    @Mock
    private NotifyUsers notify;
    @InjectMocks
    private OfferServiceImpl offerService;

    @Test public void testCreateOffer() {
         Offer offer = new Offer();
        offer.setDescription("New Year Sale");
        offer.setDiscountPercentage(50);
        LocalDateTime startDate = LocalDateTime.of(2023, 9, 20, 14, 30);
        LocalDateTime endDate = LocalDateTime.of(2023, 9, 30, 14, 30);
        Period period = new Period(startDate, endDate); offer.setPeriod(period);
        Product product = new Product();
        product.setId("35057b47-aff7-423b-9e84-53022e2bc1b6");
        when(productCommandService.getProduct(anyString()))
                .thenReturn(new ResponseDto<>(HttpStatus.OK.value(), "Success", product));
        when(offerRepository.createOffer(any(Offer.class))).thenReturn(offer);
        Offer result = offerService.createOffer(offer, "35057b47-aff7-423b-9e84-53022e2bc1b6").getData();
        assertNotNull(result, "Expected created offer to be not null");
        assertEquals(offer, result); verify(productCommandService, times(1))
                .getProduct(anyString()); verify(offerRepository, times(1))
                .createOffer(any(Offer.class));
        verify(notify, times(1)).notifyUsers(any(Product.class));
    }
}
