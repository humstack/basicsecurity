package com.basicsecurity.service;

import com.basicsecurity.model.AppUser;
import com.basicsecurity.model.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private AppUserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AppUser> appUser = userService.findByUsername(username);

        appUser.ifPresent(user -> System.out.println(user.getUsername()));

        return new CustomUserDetails(appUser.orElseThrow(() -> new UsernameNotFoundException("User not found")));
    }
}
