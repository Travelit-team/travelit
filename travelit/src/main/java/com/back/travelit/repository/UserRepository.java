package com.back.travelit.repository;

import com.back.travelit.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByLoginID(String LOGIN_ID);

}
