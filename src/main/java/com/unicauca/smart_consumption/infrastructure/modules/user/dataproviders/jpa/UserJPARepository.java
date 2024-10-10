package com.unicauca.smart_consumption.infrastructure.modules.user.dataproviders.jpa;


import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJPARepository extends JpaRepository<UserJPAEntity, String> {

}
