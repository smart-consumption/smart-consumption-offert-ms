package com.unicauca.smart_consumption.application.service.user;

import com.unicauca.smart_consumption.domain.common.ResponseDto;
import com.unicauca.smart_consumption.domain.constant.MessagesConstant;
import com.unicauca.smart_consumption.domain.product.Product;
import com.unicauca.smart_consumption.domain.product.ports.in.IProductQueryService;
import com.unicauca.smart_consumption.domain.user.User;
import com.unicauca.smart_consumption.domain.user.ports.in.IUserService;
import com.unicauca.smart_consumption.domain.user.ports.out.IUserRepository;
import com.unicauca.smart_consumption.infrastructure.exception.BusinessRuleException;
import com.unicauca.smart_consumption.infrastructure.messages.MessageLoader;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UserServiceImpl implements IUserService {
    private final IUserRepository userRepository;
    private final IProductQueryService productQueryService;

    @Override
    public ResponseDto<User> createUser(User user) {
        User createdUser = userRepository.createUser(user);
        return new ResponseDto<>(HttpStatus.CREATED.value(),
                MessageLoader.getInstance().getMessage(MessagesConstant.IM002), createdUser);
    }

    @Override
    public ResponseDto<User> updateUser(String id, User user) {
        User updatedUser = userRepository.updateUser(id, user);
        return new ResponseDto<>(HttpStatus.OK.value(),
                MessageLoader.getInstance().getMessage(MessagesConstant.IM003), updatedUser);
    }

    @Override
    public ResponseDto<Void> deleteUser(String id) {
        if (userRepository.findUserById(id).isEmpty()) {
            userRepository.deleteUser(id);
            return new ResponseDto<>(HttpStatus.NO_CONTENT.value(),
                    MessageLoader.getInstance().getMessage(MessagesConstant.IM004));
        } else {
            throw new BusinessRuleException(HttpStatus.BAD_REQUEST.value(), MessagesConstant.EM002,
                    MessageLoader.getInstance().getMessage(MessagesConstant.EM002, id));
        }
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
        Product product=productQueryService.findProductById(productId).getData();
        if(user.addProductToWatchList(product))
        {
            userRepository.updateUser(user.getId(), user);
            return new ResponseDto<>(HttpStatus.ACCEPTED.value(),
            MessageLoader.getInstance().getMessage(MessagesConstant.IM002), product);
        }
        return new ResponseDto<>(HttpStatus.NOT_FOUND.value(),
                MessageLoader.getInstance().getMessage(MessagesConstant.EM001), "404");
    }

    @Override
    public ResponseDto<Product> removeFromWatchList(String userId, String productId) {
        User user=userRepository.findUserById(userId).get();
        Product product=productQueryService.findProductById(productId).getData();
        if(user.deleteProductFromWatchList(product))
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
