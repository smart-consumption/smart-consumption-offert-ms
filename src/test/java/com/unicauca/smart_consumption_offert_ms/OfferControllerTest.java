package com.unicauca.smart_consumption_offert_ms;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.unicauca.smart_consumption_offert_ms.application.service.offer.OfferServiceImpl;
import com.unicauca.smart_consumption_offert_ms.domain.common.ResponseDto;
import com.unicauca.smart_consumption_offert_ms.domain.offer.Offer;
import com.unicauca.smart_consumption_offert_ms.domain.offer.Period;
import com.unicauca.smart_consumption_offert_ms.infrastructure.modules.offer.api.OfferWebApi;
import com.unicauca.smart_consumption_offert_ms.infrastructure.pattern.dto.OfferDto;
import com.unicauca.smart_consumption_offert_ms.infrastructure.pattern.dto.ProductDto;
import com.unicauca.smart_consumption_offert_ms.infrastructure.pattern.mapper.OfferMapper;
import jakarta.ws.rs.core.MediaType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(OfferWebApi.class)
public class OfferControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private OfferMapper offerMapper;
    @MockBean
    private OfferServiceImpl offerService;

    @Test
    public void testControllerCreateOffer() throws Exception {
        OfferDto offerDto = new OfferDto();
        Offer offer = new Offer();
        offerDto.setDescription("New Year Sale");
        offerDto.setDiscountPercentage(50);
        LocalDateTime startDate = LocalDateTime.of(2023, 9, 20, 14, 30);
        LocalDateTime endDate = LocalDateTime.of(2023, 9, 30, 14, 30);
        Period period = new Period(startDate, endDate);
        offerDto.setPeriod(period);
        ProductDto product = new ProductDto();
        product.setId("35057b47-aff7-423b-9e84-53022e2bc1b6");
        offerDto.setProduct(product);
        ResponseDto<Offer> offerResponse = new ResponseDto<>(201, "Success", offer);
        OfferDto createOfferDto = new OfferDto();
        when(offerMapper.toDomain(any(OfferDto.class))).thenReturn(offer);
        when(offerService.createOffer(any(Offer.class),
                eq("35057b47-aff7-423b-9e84-53022e2bc1b6"))).thenReturn(offerResponse);
        when(offerMapper.toTarget(any(Offer.class))).thenReturn(createOfferDto);
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/offer/{productId}", "35057b47-aff7-423b-9e84-53022e2bc1b6")
                        .content(asJsonString(offerDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    public static String asJsonString(final Object obj) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testMinimalControllerCreateOffer() throws Exception {
        when(offerMapper.toDomain(any(OfferDto.class))).thenReturn(new Offer());
        when(offerService.createOffer(any(Offer.class), anyString())).thenReturn(new ResponseDto<>(201, "Success", new Offer()));
        when(offerMapper.toTarget(any(Offer.class))).thenReturn(new OfferDto());

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/offer/{productId}", "123")
                        .content("{}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }


}
