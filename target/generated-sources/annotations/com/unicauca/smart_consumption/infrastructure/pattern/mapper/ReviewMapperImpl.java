package com.unicauca.smart_consumption.infrastructure.pattern.mapper;

import com.unicauca.smart_consumption.domain.review.Review;
import com.unicauca.smart_consumption.infrastructure.pattern.dto.ReviewDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-09T19:43:51-0500",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.40.0.v20240919-1711, environment: Java 17.0.12 (Eclipse Adoptium)"
)
@Component
public class ReviewMapperImpl implements ReviewMapper {

    @Override
    public Review toDomain(ReviewDto dto) {
        if ( dto == null ) {
            return null;
        }

        Review review = new Review();

        review.setComment( dto.getComment() );
        review.setDate( dto.getDate() );
        review.setId( dto.getId() );
        review.setRating( dto.getRating() );

        return review;
    }

    @Override
    public ReviewDto toTarget(Review entity) {
        if ( entity == null ) {
            return null;
        }

        ReviewDto.ReviewDtoBuilder reviewDto = ReviewDto.builder();

        reviewDto.comment( entity.getComment() );
        reviewDto.date( entity.getDate() );
        reviewDto.id( entity.getId() );
        reviewDto.rating( entity.getRating() );

        return reviewDto.build();
    }
}
