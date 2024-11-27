package com.unicauca.smart_consumption_offert_ms;

import com.unicauca.smart_consumption_offert_ms.domain.offer.Offer;
import com.unicauca.smart_consumption_offert_ms.domain.offer.Period;
import com.unicauca.smart_consumption_offert_ms.domain.product.Product;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OfferValueObjectTest {

    
    @Test
    public void testCreateOffer() {
        Offer offer = new Offer();
        offer.setDescription("New Year Sale");
        offer.setDiscountPercentage(50);
        LocalDateTime startDate = LocalDateTime.of(2023, 9, 20, 14, 30);
        LocalDateTime endDate = LocalDateTime.of(2023, 9, 30, 14, 30);
        Period period = new Period(startDate, endDate);
        offer.setPeriod(period);
        Product product = new Product();
        product.setId("35057b47-aff7-423b-9e84-53022e2bc1b6");
        product.setPrice(10);
        offer.setProduct(product);
        double priceOffer = offer.calculateDiscountedPrice();
        assertEquals("New Year Sale", offer.getDescription());
        assertEquals(50, offer.getDiscountPercentage());
        assertEquals(startDate, offer.getPeriod().getStartDate());
        assertEquals(endDate, offer.getPeriod().getEndDate());
        assertEquals("35057b47-aff7-423b-9e84-53022e2bc1b6", offer.getProduct().getId());
        assertEquals(5, priceOffer);
    }
}
