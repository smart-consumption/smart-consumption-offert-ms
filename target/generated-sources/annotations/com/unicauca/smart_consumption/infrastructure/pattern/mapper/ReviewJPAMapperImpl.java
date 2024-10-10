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
public class ReviewJPAMapperImpl implements ReviewJPAMapper {

    @Override
    public Review toDomain(ReviewJPAEntity dto) {
        if ( dto == null ) {
            return null;
        }

        Review review = new Review();

        review.setComment( dto.getComment() );
        review.setDate( dto.getDate() );
        review.setId( dto.getId() );
        review.setProduct( productJpaEntityToProduct( dto.getProduct() ) );
        review.setRating( dto.getRating() );
        review.setUser( userJPAEntityToUser( dto.getUser() ) );

        return review;
    }

    @Override
    public ReviewJPAEntity toTarget(Review entity) {
        if ( entity == null ) {
            return null;
        }

        ReviewJPAEntity reviewJPAEntity = new ReviewJPAEntity();

        reviewJPAEntity.setComment( entity.getComment() );
        reviewJPAEntity.setDate( entity.getDate() );
        reviewJPAEntity.setId( entity.getId() );
        reviewJPAEntity.setProduct( productToProductJpaEntity( entity.getProduct() ) );
        reviewJPAEntity.setRating( entity.getRating() );
        reviewJPAEntity.setUser( userToUserJPAEntity( entity.getUser() ) );

        return reviewJPAEntity;
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

    protected List<Review> reviewJPAEntityListToReviewList(List<ReviewJPAEntity> list) {
        if ( list == null ) {
            return null;
        }

        List<Review> list1 = new ArrayList<Review>( list.size() );
        for ( ReviewJPAEntity reviewJPAEntity : list ) {
            list1.add( toDomain( reviewJPAEntity ) );
        }

        return list1;
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

    protected Product productJpaEntityToProduct(ProductJpaEntity productJpaEntity) {
        if ( productJpaEntity == null ) {
            return null;
        }

        Product product = new Product();

        product.setCategory( categoryEmbeddableToCategory( productJpaEntity.getCategory() ) );
        product.setDetail( detailEmbeddableToDetail( productJpaEntity.getDetail() ) );
        product.setId( productJpaEntity.getId() );
        product.setName( productJpaEntity.getName() );
        product.setPrice( productJpaEntity.getPrice() );
        product.setReviews( reviewJPAEntityListToReviewList( productJpaEntity.getReviews() ) );
        product.setStatus( productJpaEntity.getStatus() );
        product.setSustainabilityCriteria( sustainabilityCriteriaEmbeddableToSustainabilityCriteria( productJpaEntity.getSustainabilityCriteria() ) );

        return product;
    }

    protected City cityJPAEntityToCity(CityJPAEntity cityJPAEntity) {
        if ( cityJPAEntity == null ) {
            return null;
        }

        City city = new City();

        city.setDepartment( cityJPAEntity.getDepartment() );
        city.setId( cityJPAEntity.getId() );
        city.setName( cityJPAEntity.getName() );

        return city;
    }

    protected ArrayList<Product> productJpaEntityListToProductArrayList(List<ProductJpaEntity> list) {
        if ( list == null ) {
            return null;
        }

        ArrayList<Product> arrayList = new ArrayList<Product>();
        for ( ProductJpaEntity productJpaEntity : list ) {
            arrayList.add( productJpaEntityToProduct( productJpaEntity ) );
        }

        return arrayList;
    }

    protected User userJPAEntityToUser(UserJPAEntity userJPAEntity) {
        if ( userJPAEntity == null ) {
            return null;
        }

        User user = new User();

        user.setCity( cityJPAEntityToCity( userJPAEntity.getCity() ) );
        user.setId( userJPAEntity.getId() );
        user.setName( userJPAEntity.getName() );
        user.setUsername( userJPAEntity.getUsername() );
        user.setWatchList( productJpaEntityListToProductArrayList( userJPAEntity.getWatchList() ) );

        return user;
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

    protected List<ReviewJPAEntity> reviewListToReviewJPAEntityList(List<Review> list) {
        if ( list == null ) {
            return null;
        }

        List<ReviewJPAEntity> list1 = new ArrayList<ReviewJPAEntity>( list.size() );
        for ( Review review : list ) {
            list1.add( toTarget( review ) );
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
            list.add( productToProductJpaEntity( product ) );
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
}
