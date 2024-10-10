package com.unicauca.smart_consumption.infrastructure.pattern.mapper;

import com.unicauca.smart_consumption.domain.product.Category;
import com.unicauca.smart_consumption.domain.product.Detail;
import com.unicauca.smart_consumption.domain.product.Product;
import com.unicauca.smart_consumption.domain.product.SustainabilityCriteria;
import com.unicauca.smart_consumption.infrastructure.modules.product.dataproviders.common.CategoryEmbeddable;
import com.unicauca.smart_consumption.infrastructure.modules.product.dataproviders.common.DetailEmbeddable;
import com.unicauca.smart_consumption.infrastructure.modules.product.dataproviders.common.SustainabilityCriteriaEmbeddable;
import com.unicauca.smart_consumption.infrastructure.modules.product.dataproviders.query.nosql.ProductMongoEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-09T19:43:51-0500",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.40.0.v20240919-1711, environment: Java 17.0.12 (Eclipse Adoptium)"
)
@Component
public class ProductJsonEntityMapperImpl implements ProductJsonEntityMapper {

    @Override
    public Product toDomain(ProductMongoEntity dto) {
        if ( dto == null ) {
            return null;
        }

        Product product = new Product();

        product.setCategory( categoryEmbeddableToCategory( dto.getCategory() ) );
        product.setDetail( detailEmbeddableToDetail( dto.getDetail() ) );
        product.setId( dto.getId() );
        product.setName( dto.getName() );
        product.setPrice( dto.getPrice() );
        product.setStatus( dto.getStatus() );
        product.setSustainabilityCriteria( sustainabilityCriteriaEmbeddableToSustainabilityCriteria( dto.getSustainabilityCriteria() ) );

        return product;
    }

    @Override
    public ProductMongoEntity toTarget(Product entity) {
        if ( entity == null ) {
            return null;
        }

        ProductMongoEntity productMongoEntity = new ProductMongoEntity();

        productMongoEntity.setCategory( categoryToCategoryEmbeddable( entity.getCategory() ) );
        productMongoEntity.setDetail( detailToDetailEmbeddable( entity.getDetail() ) );
        productMongoEntity.setId( entity.getId() );
        productMongoEntity.setName( entity.getName() );
        productMongoEntity.setPrice( entity.getPrice() );
        productMongoEntity.setStatus( entity.getStatus() );
        productMongoEntity.setSustainabilityCriteria( sustainabilityCriteriaToSustainabilityCriteriaEmbeddable( entity.getSustainabilityCriteria() ) );

        return productMongoEntity;
    }

    protected Category categoryEmbeddableToCategory(CategoryEmbeddable categoryEmbeddable) {
        if ( categoryEmbeddable == null ) {
            return null;
        }

        String categoryName = null;

        categoryName = categoryEmbeddable.getCategoryName();

        Category category = new Category( categoryName );

        return category;
    }

    protected Detail detailEmbeddableToDetail(DetailEmbeddable detailEmbeddable) {
        if ( detailEmbeddable == null ) {
            return null;
        }

        String description = null;
        String specifications = null;

        description = detailEmbeddable.getDescription();
        specifications = detailEmbeddable.getSpecifications();

        Detail detail = new Detail( description, specifications );

        return detail;
    }

    protected SustainabilityCriteria sustainabilityCriteriaEmbeddableToSustainabilityCriteria(SustainabilityCriteriaEmbeddable sustainabilityCriteriaEmbeddable) {
        if ( sustainabilityCriteriaEmbeddable == null ) {
            return null;
        }

        double carbonFootprint = 0.0d;
        double energyEfficiency = 0.0d;
        double resourceUsage = 0.0d;
        double wasteManagement = 0.0d;

        carbonFootprint = sustainabilityCriteriaEmbeddable.getCarbonFootprint();
        energyEfficiency = sustainabilityCriteriaEmbeddable.getEnergyEfficiency();
        resourceUsage = sustainabilityCriteriaEmbeddable.getResourceUsage();
        wasteManagement = sustainabilityCriteriaEmbeddable.getWasteManagement();

        SustainabilityCriteria sustainabilityCriteria = new SustainabilityCriteria( carbonFootprint, energyEfficiency, resourceUsage, wasteManagement );

        return sustainabilityCriteria;
    }

    protected CategoryEmbeddable categoryToCategoryEmbeddable(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryEmbeddable categoryEmbeddable = new CategoryEmbeddable();

        categoryEmbeddable.setCategoryName( category.getCategoryName() );

        return categoryEmbeddable;
    }

    protected DetailEmbeddable detailToDetailEmbeddable(Detail detail) {
        if ( detail == null ) {
            return null;
        }

        DetailEmbeddable detailEmbeddable = new DetailEmbeddable();

        detailEmbeddable.setDescription( detail.getDescription() );
        detailEmbeddable.setSpecifications( detail.getSpecifications() );

        return detailEmbeddable;
    }

    protected SustainabilityCriteriaEmbeddable sustainabilityCriteriaToSustainabilityCriteriaEmbeddable(SustainabilityCriteria sustainabilityCriteria) {
        if ( sustainabilityCriteria == null ) {
            return null;
        }

        SustainabilityCriteriaEmbeddable sustainabilityCriteriaEmbeddable = new SustainabilityCriteriaEmbeddable();

        sustainabilityCriteriaEmbeddable.setCarbonFootprint( sustainabilityCriteria.getCarbonFootprint() );
        sustainabilityCriteriaEmbeddable.setEnergyEfficiency( sustainabilityCriteria.getEnergyEfficiency() );
        sustainabilityCriteriaEmbeddable.setResourceUsage( sustainabilityCriteria.getResourceUsage() );
        sustainabilityCriteriaEmbeddable.setSustainabilityScore( sustainabilityCriteria.getSustainabilityScore() );
        sustainabilityCriteriaEmbeddable.setWasteManagement( sustainabilityCriteria.getWasteManagement() );

        return sustainabilityCriteriaEmbeddable;
    }
}
