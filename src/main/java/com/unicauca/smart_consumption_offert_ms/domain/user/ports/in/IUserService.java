package com.unicauca.smart_consumption_offert_ms.domain.user.ports.in;

import com.unicauca.smart_consumption_offert_ms.domain.common.ResponseDto;
import com.unicauca.smart_consumption_offert_ms.domain.product.Product;
import com.unicauca.smart_consumption_offert_ms.domain.user.User;

import java.util.List;

public interface IUserService {

    /**
     * Creates a     new user in the system.
     *
     * @param user The {@link User} to be created.
     * @return A {@link ResponseDto} containing the created {@link User} object and an HTTP status code.
     */
    ResponseDto<User> createUser(User user);


    /**
     * Finds a user in the system by its ID.
     *
     * @param id The ID of the {@link User} to be retrieved.
     * @return A {@link ResponseDto} containing the found {@link User} if it exists, and an HTTP status code.
     */
    ResponseDto<User> findUserById(String id);

    /**
     * Retrieves a list of all users in the system.
     *
     * @return A {@link ResponseDto} containing the list of all {@link User} objects and an HTTP status code.
     */
    ResponseDto<List<User>> findAllUsers();
    ResponseDto<Product> addToWatchList(String userId, String productId);
    ResponseDto<List<Product>> getWatchList(String userId);

    ResponseDto<User> updateUser(String id, User user);
}
