package com.depp3.login.app.role.data.repository;

import com.depp3.login.app.role.data.entity.Role;
import com.depp3.login.app.role.data.enums.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(ERole name);
}
