package com.unicauca.smart_consumption.infrastructure.pattern.mapper;

import com.unicauca.smart_consumption.domain.product.Category;
import com.unicauca.smart_consumption.domain.product.Product;
import com.unicauca.smart_consumption.infrastructure.pattern.dto.CategoryDto;
import com.unicauca.smart_consumption.infrastructure.pattern.dto.ProductDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-09T19:43:51-0500",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.40.0.v20240919-1711, environment: Java 17.0.12 (Eclipse Adoptium)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public Product toDomain(ProductDto dto) {
        if ( dto == null ) {
            return null;
        }

        Product product = new Product();

        product.setCategory( categoryDtoToCategory( dto.getCategory() ) );
        product.setDetail( dto.getDetail() );
        product.setId( dto.getId() );
        product.setName( dto.getName() );
        product.setPrice( dto.getPrice() );
        product.setStatus( dto.getStatus() );
        product.setSustainabilityCriteria( dto.getSustainabilityCriteria() );

        return product;
    }

    @Override
    public ProductDto toTarget(Product entity) {
        if ( entity == null ) {
            return null;
        }

        ProductDto.ProductDtoBuilder productDto = ProductDto.builder();

        productDto.category( categoryToCategoryDto( entity.getCategory() ) );
        productDto.detail( entity.getDetail() );
        productDto.id( entity.getId() );
        productDto.name( entity.getName() );
        productDto.price( entity.getPrice() );
        productDto.status( entity.getStatus() );
        productDto.sustainabilityCriteria( entity.getSustainabilityCriteria() );

        return productDto.build();
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

    protected CategoryDto categoryToCategoryDto(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryDto categoryDto = new CategoryDto();

        categoryDto.setCategoryName( category.getCategoryName() );

        return categoryDto;
    }
}
