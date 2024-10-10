package com.unicauca.smart_consumption.infrastructure.pattern.mapper;

import com.unicauca.smart_consumption.domain.city.City;
import com.unicauca.smart_consumption.domain.product.Category;
import com.unicauca.smart_consumption.domain.product.Detail;
import com.unicauca.smart_consumption.domain.product.Product;
import com.unicauca.smart_consumption.domain.product.SustainabilityCriteria;
import com.unicauca.smart_consumption.domain.review.Review;
import com.unicauca.smart_consumption.domain.user.User;
import com.unicauca.smart_consumption.infrastructure.modules.city.dataproviders.jpa.CityJPAEntity;
import com.unicauca.smart_consumption.infrastructure.modules.product.dataproviders.command.sql.ProductJpaEntity;
import com.unicauca.smart_consumption.infrastructure.modules.product.dataproviders.common.CategoryEmbeddable;
import com.unicauca.smart_consumption.infrastructure.modules.product.dataproviders.common.DetailEmbeddable;
import com.unicauca.smart_consumption.infrastructure.modules.product.dataproviders.common.SustainabilityCriteriaEmbeddable;
import com.unicauca.smart_consumption.infrastructure.modules.review.dataproviders.jpa.ReviewJPAEntity;
import com.unicauca.smart_consumption.infrastructure.modules.user.dataproviders.jpa.UserJPAEntity;
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
public class ProductJpaEntityMapperImpl implements ProductJpaEntityMapper {

    @Override
    public Product toDomain(ProductJpaEntity dto) {
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
    public ProductJpaEntity toTarget(Product entity) {
        if ( entity == null ) {
            return null;
        }

        ProductJpaEntity.ProductJpaEntityBuilder productJpaEntity = ProductJpaEntity.builder();

        productJpaEntity.category( categoryToCategoryEmbeddable( entity.getCategory() ) );
        productJpaEntity.detail( detailToDetailEmbeddable( entity.getDetail() ) );
        productJpaEntity.id( entity.getId() );
        productJpaEntity.name( entity.getName() );
        productJpaEntity.price( entity.getPrice() );
        productJpaEntity.reviews( reviewListToReviewJPAEntityList( entity.getReviews() ) );
        productJpaEntity.status( entity.getStatus() );
        productJpaEntity.sustainabilityCriteria( sustainabilityCriteriaToSustainabilityCriteriaEmbeddable( entity.getSustainabilityCriteria() ) );

        return productJpaEntity.build();
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

    protected CityJPAEntity cityToCityJPAEntity(City city) {
        if ( city == null ) {
            return null;
        }

        CityJPAEntity cityJPAEntity = new CityJPAEntity();

        cityJPAEntity.setDepartment( city.getDepartment() );
        cityJPAEntity.setId( city.getId() );
        cityJPAEntity.setName( city.getName() );

        return cityJPAEntity;
    }

    protected List<ProductJpaEntity> productArrayListToProductJpaEntityList(ArrayList<Product> arrayList) {
        if ( arrayList == null ) {
            return null;
        }

        List<ProductJpaEntity> list = new ArrayList<ProductJpaEntity>( arrayList.size() );
        for ( Product product : arrayList ) {
            list.add( toTarget( product ) );
        }

        return list;
    }

    protected UserJPAEntity userToUserJPAEntity(User user) {
        if ( user == null ) {
            return null;
        }

        UserJPAEntity userJPAEntity = new UserJPAEntity();

        userJPAEntity.setCity( cityToCityJPAEntity( user.getCity() ) );
        userJPAEntity.setId( user.getId() );
        userJPAEntity.setName( user.getName() );
        userJPAEntity.setUsername( user.getUsername() );
        userJPAEntity.setWatchList( productArrayListToProductJpaEntityList( user.getWatchList() ) );

        return userJPAEntity;
    }

    protected ReviewJPAEntity reviewToReviewJPAEntity(Review review) {
        if ( review == null ) {
            return null;
        }

        ReviewJPAEntity reviewJPAEntity = new ReviewJPAEntity();

        reviewJPAEntity.setComment( review.getComment() );
        reviewJPAEntity.setDate( review.getDate() );
        reviewJPAEntity.setId( review.getId() );
        reviewJPAEntity.setProduct( toTarget( review.getProduct() ) );
        reviewJPAEntity.setRating( review.getRating() );
        reviewJPAEntity.setUser( userToUserJPAEntity( review.getUser() ) );

        return reviewJPAEntity;
    }

    protected List<ReviewJPAEntity> reviewListToReviewJPAEntityList(List<Review> list) {
        if ( list == null ) {
            return null;
        }

        List<ReviewJPAEntity> list1 = new ArrayList<ReviewJPAEntity>( list.size() );
        for ( Review review : list ) {
            list1.add( reviewToReviewJPAEntity( review ) );
        }

        return list1;
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
