package com.unicauca.smart_consumption.infrastructure.pattern.mapper;

/**
 * An interface representing a generic mapper between Dto (Data Transfer Object) and domain entity.
 *
 * @param <D> Type parameter representing the Dto (Data Transfer Object).
 * @param <E> Type parameter representing the domain entity.
 */
public interface EntityMapper<D, E> {
  /**
   * Maps a Dto (Data Transfer Object) to a domain entity.
   *
   * @param dto The Dto object to be mapped to the domain entity.
   * @return The domain entity.
   */
  E toDomain(D dto);

  /**
   * Maps a domain entity to a DTO (Data Transfer Object).
   *
   * @param entity The domain entity to be mapped to the DTO.
   * @return The DTO object.
   */
  D toTarget(E entity);

}
