package com.unicauca.smart_consumption.infrastructure.pattern.mapper;

import com.unicauca.smart_consumption.domain.city.City;
import com.unicauca.smart_consumption.domain.offer.Offer;
import com.unicauca.smart_consumption.domain.product.Category;
import com.unicauca.smart_consumption.domain.product.Product;
import com.unicauca.smart_consumption.domain.store.Store;
import com.unicauca.smart_consumption.infrastructure.pattern.dto.CategoryDto;
import com.unicauca.smart_consumption.infrastructure.pattern.dto.CityDto;
import com.unicauca.smart_consumption.infrastructure.pattern.dto.OfferDto;
import com.unicauca.smart_consumption.infrastructure.pattern.dto.ProductDto;
import com.unicauca.smart_consumption.infrastructure.pattern.dto.StoreDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-09T19:43:51-0500",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.40.0.v20240919-1711, environment: Java 17.0.12 (Eclipse Adoptium)"
)
@Component
public class StoreMapperImpl implements StoreMapper {

    @Override
    public Store toDomain(StoreDto dto) {
        if ( dto == null ) {
            return null;
        }

        Store store = new Store();

        store.setCity( cityDtoToCity( dto.getCity() ) );
        store.setId( dto.getId() );
        store.setName( dto.getName() );
        store.setOffers( offerDtoListToOfferList( dto.getOffers() ) );
        store.setProducts( productDtoListToProductList( dto.getProducts() ) );

        return store;
    }

    @Override
    public StoreDto toTarget(Store entity) {
        if ( entity == null ) {
            return null;
        }

        StoreDto storeDto = new StoreDto();

        storeDto.setCity( cityToCityDto( entity.getCity() ) );
        storeDto.setId( entity.getId() );
        storeDto.setName( entity.getName() );
        storeDto.setOffers( offerListToOfferDtoList( entity.getOffers() ) );
        storeDto.setProducts( productListToProductDtoList( entity.getProducts() ) );

        return storeDto;
    }

    protected City cityDtoToCity(CityDto cityDto) {
        if ( cityDto == null ) {
            return null;
        }

        City city = new City();

        city.setDepartment( cityDto.getDepartment() );
        city.setId( cityDto.getId() );
        city.setName( cityDto.getName() );

        return city;
    }

    protected Category categoryDtoToCategory(CategoryDto categoryDto) {
        if ( categoryDto == null ) {
            return null;
        }

        String categoryName = null;

        categoryName = categoryDto.getCategoryName();

        Category category = new Category( categoryName );

        return category;
    }

    protected Product productDtoToProduct(ProductDto productDto) {
        if ( productDto == null ) {
            return null;
        }

        Product product = new Product();

        product.setCategory( categoryDtoToCategory( productDto.getCategory() ) );
        product.setDetail( productDto.getDetail() );
        product.setId( productDto.getId() );
        product.setName( productDto.getName() );
        product.setPrice( productDto.getPrice() );
        product.setStatus( productDto.getStatus() );
        product.setSustainabilityCriteria( productDto.getSustainabilityCriteria() );

        return product;
    }

    protected Offer offerDtoToOffer(OfferDto offerDto) {
        if ( offerDto == null ) {
            return null;
        }

        Offer offer = new Offer();

        offer.setDescription( offerDto.getDescription() );
        offer.setDiscountPercentage( offerDto.getDiscountPercentage() );
        offer.setDiscountedPrice( offerDto.getDiscountedPrice() );
        offer.setId( offerDto.getId() );
        offer.setPeriod( offerDto.getPeriod() );
        offer.setProduct( productDtoToProduct( offerDto.getProduct() ) );
        offer.setStore( toDomain( offerDto.getStore() ) );

        return offer;
    }

    protected List<Offer> offerDtoListToOfferList(List<OfferDto> list) {
        if ( list == null ) {
            return null;
        }

        List<Offer> list1 = new ArrayList<Offer>( list.size() );
        for ( OfferDto offerDto : list ) {
            list1.add( offerDtoToOffer( offerDto ) );
        }

        return list1;
    }

    protected List<Product> productDtoListToProductList(List<ProductDto> list) {
        if ( list == null ) {
            return null;
        }

        List<Product> list1 = new ArrayList<Product>( list.size() );
        for ( ProductDto productDto : list ) {
            list1.add( productDtoToProduct( productDto ) );
        }

        return list1;
    }

    protected CityDto cityToCityDto(City city) {
        if ( city == null ) {
            return null;
        }

        CityDto cityDto = new CityDto();

        cityDto.setDepartment( city.getDepartment() );
        cityDto.setId( city.getId() );
        cityDto.setName( city.getName() );

        return cityDto;
    }

    protected CategoryDto categoryToCategoryDto(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryDto categoryDto = new CategoryDto();

        categoryDto.setCategoryName( category.getCategoryName() );

        return categoryDto;
    }

    protected ProductDto productToProductDto(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductDto.ProductDtoBuilder productDto = ProductDto.builder();

        productDto.category( categoryToCategoryDto( product.getCategory() ) );
        productDto.detail( product.getDetail() );
        productDto.id( product.getId() );
        productDto.name( product.getName() );
        productDto.price( product.getPrice() );
        productDto.status( product.getStatus() );
        productDto.sustainabilityCriteria( product.getSustainabilityCriteria() );

        return productDto.build();
    }

    protected OfferDto offerToOfferDto(Offer offer) {
        if ( offer == null ) {
            return null;
        }

        OfferDto offerDto = new OfferDto();

        offerDto.setDescription( offer.getDescription() );
        offerDto.setDiscountPercentage( offer.getDiscountPercentage() );
        offerDto.setDiscountedPrice( offer.getDiscountedPrice() );
        offerDto.setId( offer.getId() );
        offerDto.setPeriod( offer.getPeriod() );
        offerDto.setProduct( productToProductDto( offer.getProduct() ) );
        offerDto.setStore( toTarget( offer.getStore() ) );

        return offerDto;
    }

    protected List<OfferDto> offerListToOfferDtoList(List<Offer> list) {
        if ( list == null ) {
            return null;
        }

        List<OfferDto> list1 = new ArrayList<OfferDto>( list.size() );
        for ( Offer offer : list ) {
            list1.add( offerToOfferDto( offer ) );
        }

        return list1;
    }

    protected List<ProductDto> productListToProductDtoList(List<Product> list) {
        if ( list == null ) {
            return null;
        }

        List<ProductDto> list1 = new ArrayList<ProductDto>( list.size() );
        for ( Product product : list ) {
            list1.add( productToProductDto( product ) );
        }

        return list1;
    }
}
