package com.depp3.login.app.user.data.repository;

import com.depp3.login.app.user.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
    Optional<User> findByUsernameOrEmail(String username, String email);

    /*
        @Query(
                value = "SELECT EXISTS(SELECT * FROM users u WHERE u.username = ?1 OR u.email = ?2) as Result",
                nativeQuery = true
        )
        Integer existeUser(String username, String email);
    */
    Boolean existsByUsernameOrEmail(String username, String email);
}
