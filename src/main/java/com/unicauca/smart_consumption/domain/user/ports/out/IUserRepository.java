package com.unicauca.smart_consumption.domain.user.ports.out;

import com.unicauca.smart_consumption.domain.user.User;

import java.util.List;
import java.util.Optional;

public interface IUserRepository {
    /**
     * Creates a new user in the system.
     *
     * @param user The {@link User} to be created.
     * @return The created {@link User} object.
     */
    User createUser(User user);

    /**
     * Updates an existing user in the system.
     *
     * @param id The ID of the {@link User} to be updated.
     * @param user The {@link User} with updated information.
     * @return The updated {@link User} object.
     */
    User updateUser(String id, User user);

    /**
     * Deletes an existing user from the system.
     *
     * @param id The ID of the {@link User} to be deleted.
     */
    void deleteUser(String id);

    /**
     * Finds a user in the system by its ID.
     *
     * @param id The ID of the {@link User} to be retrieved.
     * @return An {@link Optional} containing the found {@link User} if it exists, or {@link Optional#empty()} if not found.
     */
    Optional<User> findUserById(String id);


    /**
     * Retrieves a list of all users in the system.
     *
     * @return A list of all {@link User} objects.
     */
    List<User> findAllUsers();

}
