package com.yevhenii.kpi.readmore.repository;

import com.yevhenii.kpi.readmore.model.User;
import org.springframework.data.repository.Repository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface UserRepository extends Repository<User, Long> {

    Optional<User> findUserByName(String name);

    Optional<User> findUserById(Long id);

    Optional<User> findUserByEmail(String email);
}
