package com.yevhenii.kpi.readmore.repository;

import com.yevhenii.kpi.readmore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByName(String name);

    Optional<User> findUserById(Long id);

    Optional<User> findUserByEmail(String email);
}
