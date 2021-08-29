package com.basicsecurity.repository;

import com.basicsecurity.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Integer> {

    public Optional<AppUser> findByUsername(String username);
}
