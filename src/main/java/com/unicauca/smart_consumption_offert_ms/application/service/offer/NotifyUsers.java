package com.unicauca.smart_consumption_offert_ms.application.service.offer;

import com.unicauca.smart_consumption_offert_ms.domain.notification.Notification;
import com.unicauca.smart_consumption_offert_ms.domain.product.Product;
import com.unicauca.smart_consumption_offert_ms.domain.user.User;
import com.unicauca.smart_consumption_offert_ms.domain.user.ports.out.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class NotifyUsers {

    private final IUserRepository userRepository;
    private final RabbitTemplate rabbitTemplate;

    public void notifyUsers(Product product){
        List<User> interestedUsers = userRepository.findAllUsers();

        List<User> filterUsers =  interestedUsers.stream()
                .filter(user -> user.getWatchList().contains(product)).toList();

        filterUsers.forEach(user -> {
            Notification message = new Notification(user.getId(), product.getId(), product.getName());
            rabbitTemplate.convertAndSend("notifications.queue", message);
            System.out.println("Send notification: " + message);
        });
    }
}
