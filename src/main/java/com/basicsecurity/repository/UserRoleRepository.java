package com.basicsecurity.repository;

import com.basicsecurity.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {

    public Optional<UserRole> findByName(String rolename);
}
