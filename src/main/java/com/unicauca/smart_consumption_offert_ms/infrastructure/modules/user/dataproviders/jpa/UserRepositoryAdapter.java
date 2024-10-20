package com.unicauca.smart_consumption_offert_ms.infrastructure.modules.user.dataproviders.jpa;

import com.unicauca.smart_consumption_offert_ms.domain.constant.MessagesConstant;
import com.unicauca.smart_consumption_offert_ms.domain.user.User;
import com.unicauca.smart_consumption_offert_ms.domain.user.ports.out.IUserRepository;
import com.unicauca.smart_consumption_offert_ms.infrastructure.exception.BusinessRuleException;
import com.unicauca.smart_consumption_offert_ms.infrastructure.messages.MessageLoader;
import com.unicauca.smart_consumption_offert_ms.infrastructure.pattern.mapper.UserJPAMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UserRepositoryAdapter implements IUserRepository {

    private final UserJPARepository userJPARepository;
    private final UserJPAMapper userJPAMapper;

    @Override
    public User createUser(User user) {
        UserJPAEntity entity = userJPAMapper.toTarget(user);
        return userJPAMapper.toDomain(userJPARepository.save(entity));
    }



    @Override
    public Optional<User> findUserById(String id) {
        Optional<UserJPAEntity> userJPA=userJPARepository.findById(id);
        User mapped=userJPAMapper.toDomain(userJPA.orElseThrow(() -> new BusinessRuleException(HttpStatus.BAD_REQUEST.value(), MessagesConstant.EM002,
                MessageLoader.getInstance().getMessage(MessagesConstant.EM002, id))));
        return Optional.of(mapped);
    }

    @Override
    public List<User> findAllUsers() {
        return userJPARepository.findAll().stream()
                .map(userJPAMapper::toDomain).toList();
    }

    @Override
    public User updateUser(String id, User user) {
        return userJPARepository.findById(id)
                .map(userEntity -> {
                    User domainUser = userJPAMapper.toDomain(userEntity);
                    domainUser.setWatchList(user.getWatchList());
                    UserJPAEntity updatedEntity = userJPAMapper.toTarget(domainUser);
                    userJPARepository.save(updatedEntity);
                    return userJPAMapper.toDomain(updatedEntity);
                })
                .orElseThrow(() -> new EntityNotFoundException("User not found with id " + id));
    }


}
