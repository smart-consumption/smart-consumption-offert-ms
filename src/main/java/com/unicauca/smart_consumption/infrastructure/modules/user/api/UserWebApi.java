package com.unicauca.smart_consumption.infrastructure.modules.user.api;

import com.unicauca.smart_consumption.domain.common.ResponseDto;
import com.unicauca.smart_consumption.domain.product.Product;
import com.unicauca.smart_consumption.domain.user.User;
import com.unicauca.smart_consumption.domain.user.ports.in.IUserService;
import com.unicauca.smart_consumption.infrastructure.pattern.dto.ProductDto;
import com.unicauca.smart_consumption.infrastructure.pattern.dto.UserDto;
import com.unicauca.smart_consumption.infrastructure.pattern.mapper.ProductMapper;
import com.unicauca.smart_consumption.infrastructure.pattern.mapper.UserMapper;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@RestController
@RequestMapping(value = "/user")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Tag(name = "User APIs", description = "User web APIs")
public class UserWebApi {
    private final IUserService userService;
    private final UserMapper userMapper;
    private final ProductMapper productMapper;

    @PostMapping
    public ResponseEntity<ResponseDto<UserDto>> createUser(@RequestBody UserDto userDto) {
        User user = userMapper.toDomain(userDto);
        ResponseDto<User> userResponse = userService.createUser(user);
        UserDto createUserDto = userMapper.toTarget(userResponse.getData());
        return new ResponseDto<>(userResponse.getStatus(),
                userResponse.getMessage(),createUserDto).of();
    }

    @PutMapping("/{entityId}")
    public ResponseEntity<ResponseDto<UserDto>> updateUser(@PathVariable String entityId, @RequestBody UserDto userDto) {
        User user = userMapper.toDomain(userDto);
        ResponseDto<User> userResponse = userService.updateUser(entityId, user);
        UserDto updatedUserDto = userMapper.toTarget(userResponse.getData());
        return new ResponseDto<>(userResponse.getStatus(),
                userResponse.getMessage(), updatedUserDto).of();
    }

    @DeleteMapping("/{entityId}")
    public ResponseEntity<ResponseDto<Void>> deleteUser(@PathVariable String entityId) {
        ResponseDto<Void> userResponse = userService.deleteUser(entityId);
        return new ResponseDto<Void>(userResponse.getStatus(), userResponse.getMessage()).of();
    }

    @GetMapping("/{entityId}")
    public ResponseEntity<ResponseDto<UserDto>> getUserById(@PathVariable String entityId) {
        ResponseDto<User> userResponse = userService.findUserById(entityId);
        UserDto userDto = userMapper.toTarget(userResponse.getData());
        ResponseDto<UserDto> userDtoResponse = new ResponseDto<>(userResponse.getStatus(), userResponse.getMessage(), userDto);
        return userDtoResponse.of();
    }

    @GetMapping
    public ResponseEntity<ResponseDto<List<UserDto>>> getAllUsers() {
        ResponseDto<List<User>> userResponse = userService.findAllUsers();
        return new ResponseDto<>(
                userResponse.getStatus(),
                userResponse.getMessage(),
                userResponse.getData().stream().map(userMapper::toTarget).toList()
        ).of();
    }

    @PostMapping("/watchlist/{userId}/{productId}")
    public ResponseEntity<ResponseDto<ProductDto>> addProductToWatchlist(
        @PathVariable String userId,
        @PathVariable String productId) {
        ResponseDto<Product> response=userService.addToWatchList(userId, productId);
        if(response.getData()!=null)
        {
            ProductDto productDto=productMapper.toTarget(response.getData());
            return new ResponseDto<ProductDto>(response.getStatus(),response.getMessage(),productDto).of();
        } 
        return new ResponseDto<ProductDto>(response.getStatus(),response.getMessage(),response.getErrorCode()).of();
    }

    @GetMapping("/watchlist/{userId}")
    public ResponseEntity<ResponseDto<List<ProductDto>>> getWatchlist(
        @PathVariable String userId) {
        ResponseDto<List<Product>> response=userService.getWatchList(userId);
        List<ProductDto> mappedResponse = new ArrayList<>();
        for (Product product : response.getData()) {
            ProductDto productDto = productMapper.toTarget(product);
            mappedResponse.add(productDto);
        }
        return new ResponseDto<List<ProductDto>>(response.getStatus(),response.getMessage(),mappedResponse).of();
    }
}
