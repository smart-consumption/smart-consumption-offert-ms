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
    date = "2024-10-09T19:43:50-0500",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.40.0.v20240919-1711, environment: Java 17.0.12 (Eclipse Adoptium)"
)
@Component
public class OfferMapperImpl implements OfferMapper {

    @Override
    public Offer toDomain(OfferDto dto) {
        if ( dto == null ) {
            return null;
        }

        Offer offer = new Offer();

        offer.setDescription( dto.getDescription() );
        offer.setDiscountPercentage( dto.getDiscountPercentage() );
        offer.setDiscountedPrice( dto.getDiscountedPrice() );
        offer.setId( dto.getId() );
        offer.setPeriod( dto.getPeriod() );
        offer.setProduct( productDtoToProduct( dto.getProduct() ) );
        offer.setStore( storeDtoToStore( dto.getStore() ) );

        return offer;
    }

    @Override
    public OfferDto toTarget(Offer entity) {
        if ( entity == null ) {
            return null;
        }

        OfferDto offerDto = new OfferDto();

        offerDto.setDescription( entity.getDescription() );
        offerDto.setDiscountPercentage( entity.getDiscountPercentage() );
        offerDto.setDiscountedPrice( entity.getDiscountedPrice() );
        offerDto.setId( entity.getId() );
        offerDto.setPeriod( entity.getPeriod() );
        offerDto.setProduct( productToProductDto( entity.getProduct() ) );
        offerDto.setStore( storeToStoreDto( entity.getStore() ) );

        return offerDto;
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

    protected List<Offer> offerDtoListToOfferList(List<OfferDto> list) {
        if ( list == null ) {
            return null;
        }

        List<Offer> list1 = new ArrayList<Offer>( list.size() );
        for ( OfferDto offerDto : list ) {
            list1.add( toDomain( offerDto ) );
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

    protected Store storeDtoToStore(StoreDto storeDto) {
        if ( storeDto == null ) {
            return null;
        }

        Store store = new Store();

        store.setCity( cityDtoToCity( storeDto.getCity() ) );
        store.setId( storeDto.getId() );
        store.setName( storeDto.getName() );
        store.setOffers( offerDtoListToOfferList( storeDto.getOffers() ) );
        store.setProducts( productDtoListToProductList( storeDto.getProducts() ) );

        return store;
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

    protected List<OfferDto> offerListToOfferDtoList(List<Offer> list) {
        if ( list == null ) {
            return null;
        }

        List<OfferDto> list1 = new ArrayList<OfferDto>( list.size() );
        for ( Offer offer : list ) {
            list1.add( toTarget( offer ) );
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

    protected StoreDto storeToStoreDto(Store store) {
        if ( store == null ) {
            return null;
        }

        StoreDto storeDto = new StoreDto();

        storeDto.setCity( cityToCityDto( store.getCity() ) );
        storeDto.setId( store.getId() );
        storeDto.setName( store.getName() );
        storeDto.setOffers( offerListToOfferDtoList( store.getOffers() ) );
        storeDto.setProducts( productListToProductDtoList( store.getProducts() ) );

        return storeDto;
    }
}
