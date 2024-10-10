package com.unicauca.smart_consumption.application.service.offer;

import com.unicauca.smart_consumption.domain.product.Product;
import com.unicauca.smart_consumption.domain.user.User;
import com.unicauca.smart_consumption.domain.user.ports.out.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class NotifyUsers {

    private final IUserRepository userRepository;

    public void notifyUsers(Product product){
        List<User> interestedUsers = userRepository.findAllUsers();

        List<User> filterUsers =  interestedUsers.stream()
                .filter(user -> user.getWatchList().contains(product)).toList();

        filterUsers.forEach(user -> {
            System.out.println("Notificación: El usuario " + user.getUsername()
                    + " tiene un nuevo descuento en su producto " + product.getName());
        });
    }
}
