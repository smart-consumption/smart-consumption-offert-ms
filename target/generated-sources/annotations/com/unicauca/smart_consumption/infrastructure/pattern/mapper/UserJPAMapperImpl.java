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
    date = "2024-10-09T19:43:51-0500",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.40.0.v20240919-1711, environment: Java 17.0.12 (Eclipse Adoptium)"
)
@Component
public class UserJPAMapperImpl implements UserJPAMapper {

    @Override
    public UserJPAEntity toTarget(User entity) {
        if ( entity == null ) {
            return null;
        }

        UserJPAEntity userJPAEntity = new UserJPAEntity();

        userJPAEntity.setCity( cityToCityJPAEntity( entity.getCity() ) );
        userJPAEntity.setId( entity.getId() );
        userJPAEntity.setName( entity.getName() );
        userJPAEntity.setUsername( entity.getUsername() );
        userJPAEntity.setWatchList( productArrayListToProductJpaEntityList( entity.getWatchList() ) );

        return userJPAEntity;
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

    protected ReviewJPAEntity reviewToReviewJPAEntity(Review review) {
        if ( review == null ) {
            return null;
        }

        ReviewJPAEntity reviewJPAEntity = new ReviewJPAEntity();

        reviewJPAEntity.setComment( review.getComment() );
        reviewJPAEntity.setDate( review.getDate() );
        reviewJPAEntity.setId( review.getId() );
        reviewJPAEntity.setProduct( productToProductJpaEntity( review.getProduct() ) );
        reviewJPAEntity.setRating( review.getRating() );
        reviewJPAEntity.setUser( toTarget( review.getUser() ) );

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

    protected ProductJpaEntity productToProductJpaEntity(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductJpaEntity.ProductJpaEntityBuilder productJpaEntity = ProductJpaEntity.builder();

        productJpaEntity.category( categoryToCategoryEmbeddable( product.getCategory() ) );
        productJpaEntity.detail( detailToDetailEmbeddable( product.getDetail() ) );
        productJpaEntity.id( product.getId() );
        productJpaEntity.name( product.getName() );
        productJpaEntity.price( product.getPrice() );
        productJpaEntity.reviews( reviewListToReviewJPAEntityList( product.getReviews() ) );
        productJpaEntity.status( product.getStatus() );
        productJpaEntity.sustainabilityCriteria( sustainabilityCriteriaToSustainabilityCriteriaEmbeddable( product.getSustainabilityCriteria() ) );

        return productJpaEntity.build();
    }

    protected List<ProductJpaEntity> productArrayListToProductJpaEntityList(ArrayList<Product> arrayList) {
        if ( arrayList == null ) {
            return null;
        }

        List<ProductJpaEntity> list = new ArrayList<ProductJpaEntity>( arrayList.size() );
        for ( Product product : arrayList ) {
            list.add( productToProductJpaEntity( product ) );
        }

        return list;
    }
}
