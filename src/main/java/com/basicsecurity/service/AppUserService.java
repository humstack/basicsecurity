package com.basicsecurity.service;

import com.basicsecurity.model.AppUser;
import com.basicsecurity.repository.AppUserRepository;
import com.basicsecurity.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppUserService {

    @Autowired
    private AppUserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    @Autowired
    private UserRoleRepository roleRepository;

    private static final int SYSTEM_ADMIN = 1;
    
    public Optional<AppUser> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public List<AppUser> findAll() {
        return userRepository.findAll();
    }

    public void addUser(AppUser appUser) {
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword())); //Encode user's password

        if (userRepository.findByUsername(appUser.getUsername()).isPresent()) {
            throw new IllegalArgumentException("The user already exists");
        } else {
            userRepository.save(appUser);
        }
    }

    public void editUser(int id, AppUser appUser) {

        AppUser user = findUser(id);

        user.setFirstName(appUser.getFirstName());
        user.setEmail(appUser.getEmail());
        if (user.getId() != SYSTEM_ADMIN){
            user.setEnabled(appUser.isEnabled());
            user.setRole(appUser.getRole());
            user.setPassword(passwordEncoder.encode(appUser.getPassword())); //Encode user's password
        }

        userRepository.save(user);
    }

    public Optional<AppUser> findById(int id) {
        return userRepository.findById(id);
    }
    
    public void deleteUser(int id, AppUser appUser) {
    
        if (userRepository.findById(appUser.getId()).isEmpty()) {
            throw new IllegalArgumentException("The user is invalid");
        }else if(appUser.getId() == SYSTEM_ADMIN){
            throw new IllegalArgumentException("ADMIN user cannot be deleted");
        }
        
        userRepository.delete(appUser);
    }
    
    public void assignRole(int id, AppUser user) {

        userRepository.save(user); //Update the user

    }
    
    private AppUser findUser(int id) throws IllegalArgumentException{
        Optional<AppUser> user = userRepository.findById(id);
    
        if (user.isEmpty()) {
            throw new IllegalArgumentException("The user is invalid");
        }
        
        return user.get();
    }
}
