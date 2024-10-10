package com.unicauca.smart_consumption.infrastructure.pattern.mapper;

import com.unicauca.smart_consumption.domain.city.City;
import com.unicauca.smart_consumption.domain.product.Category;
import com.unicauca.smart_consumption.domain.product.Product;
import com.unicauca.smart_consumption.domain.user.User;
import com.unicauca.smart_consumption.infrastructure.pattern.dto.CategoryDto;
import com.unicauca.smart_consumption.infrastructure.pattern.dto.CityDto;
import com.unicauca.smart_consumption.infrastructure.pattern.dto.ProductDto;
import com.unicauca.smart_consumption.infrastructure.pattern.dto.UserDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-09T19:43:52-0500",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.40.0.v20240919-1711, environment: Java 17.0.12 (Eclipse Adoptium)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User toDomain(UserDto dto) {
        if ( dto == null ) {
            return null;
        }

        User user = new User();

        user.setCity( cityDtoToCity( dto.getCity() ) );
        user.setId( dto.getId() );
        user.setName( dto.getName() );
        user.setUsername( dto.getUsername() );
        user.setWatchList( productDtoListToProductArrayList( dto.getWatchList() ) );

        return user;
    }

    @Override
    public UserDto toTarget(User entity) {
        if ( entity == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setCity( cityToCityDto( entity.getCity() ) );
        userDto.setId( entity.getId() );
        userDto.setName( entity.getName() );
        userDto.setUsername( entity.getUsername() );
        userDto.setWatchList( productArrayListToProductDtoList( entity.getWatchList() ) );

        return userDto;
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

    protected ArrayList<Product> productDtoListToProductArrayList(List<ProductDto> list) {
        if ( list == null ) {
            return null;
        }

        ArrayList<Product> arrayList = new ArrayList<Product>();
        for ( ProductDto productDto : list ) {
            arrayList.add( productDtoToProduct( productDto ) );
        }

        return arrayList;
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

    protected List<ProductDto> productArrayListToProductDtoList(ArrayList<Product> arrayList) {
        if ( arrayList == null ) {
            return null;
        }

        List<ProductDto> list = new ArrayList<ProductDto>( arrayList.size() );
        for ( Product product : arrayList ) {
            list.add( productToProductDto( product ) );
        }

        return list;
    }
}
