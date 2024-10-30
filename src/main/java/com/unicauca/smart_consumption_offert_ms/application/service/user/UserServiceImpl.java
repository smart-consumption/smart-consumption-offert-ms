package com.unicauca.smart_consumption_offert_ms.application.service.user;

import com.unicauca.smart_consumption_offert_ms.domain.common.ResponseDto;
import com.unicauca.smart_consumption_offert_ms.domain.constant.MessagesConstant;
import com.unicauca.smart_consumption_offert_ms.domain.product.Product;
import com.unicauca.smart_consumption_offert_ms.domain.product.ports.in.IProductCommandService;
import com.unicauca.smart_consumption_offert_ms.domain.user.User;
import com.unicauca.smart_consumption_offert_ms.domain.user.ports.in.IUserService;
import com.unicauca.smart_consumption_offert_ms.domain.user.ports.out.IUserRepository;
import com.unicauca.smart_consumption_offert_ms.infrastructure.config.RabbitMQConfig;
import com.unicauca.smart_consumption_offert_ms.infrastructure.exception.BusinessRuleException;
import com.unicauca.smart_consumption_offert_ms.infrastructure.messages.MessageLoader;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;


@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UserServiceImpl implements IUserService {
    private final IUserRepository userRepository;
    private final IProductCommandService productCommandService;


    @Override
    @RabbitListener(queues = RabbitMQConfig.USER_CREATED_QUEUE)
    public void createUser(User user) {
        User createdUser = userRepository.createUser(user);
        System.out.println("user from ms_user" + createdUser.toString());
    }

    @Override
    @RabbitListener(queues = RabbitMQConfig.USER_UPDATED_QUEUE)
    public void updateUser(String id, User user) {
        User updatedUser = userRepository.updateUser(id, user);
        System.out.println("user from ms_user" + updatedUser.toString());
    }

    @Override
    public ResponseDto<User> findUserById(final String id) {
        User user = userRepository.findUserById(id)
                .orElseThrow(() -> new BusinessRuleException(HttpStatus.BAD_REQUEST.value(), MessagesConstant.EM002,
                        MessageLoader.getInstance().getMessage(MessagesConstant.EM002, id)));
        return new ResponseDto<>(HttpStatus.OK.value(),
                MessageLoader.getInstance().getMessage(MessagesConstant.IM001), user);
    }

    @Override
    public ResponseDto<List<User>> findAllUsers() {
        List<User> users = userRepository.findAllUsers();
        return new ResponseDto<>(HttpStatus.OK.value(),
                MessageLoader.getInstance().getMessage(MessagesConstant.IM001), users);
    }

    @Override
    public ResponseDto<Product> addToWatchList(String userId, String productId) {
        User user=userRepository.findUserById(userId).get();
        Product product=productCommandService.getProduct(productId).getData();
        if(user.addProductToWatchList(product))
        {
            return new ResponseDto<>(HttpStatus.ACCEPTED.value(),
            MessageLoader.getInstance().getMessage(MessagesConstant.IM002), product);
        }
        return new ResponseDto<>(HttpStatus.NOT_FOUND.value(),
                MessageLoader.getInstance().getMessage(MessagesConstant.EM001), "404");
    }

    @Override
    public ResponseDto<List<Product>> getWatchList(String userId) {
        User user=userRepository.findUserById(userId).orElseThrow(() -> new BusinessRuleException(HttpStatus.BAD_REQUEST.value(), MessagesConstant.EM002,
            MessageLoader.getInstance().getMessage(MessagesConstant.EM002, userId)));;
        return new ResponseDto<>(HttpStatus.ACCEPTED.value(),
        MessageLoader.getInstance().getMessage(MessagesConstant.IM001), user.getWatchList());
    }
}
