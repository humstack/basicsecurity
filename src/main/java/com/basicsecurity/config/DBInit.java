package com.basicsecurity.config;

import com.basicsecurity.model.AppUser;
import com.basicsecurity.model.UserRole;
import com.basicsecurity.service.AppUserService;
import com.basicsecurity.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DBInit implements CommandLineRunner {
    @Autowired
    private UserRoleService roleService;

    @Autowired
    private AppUserService userService;

    @Override
    public void run(String... args) throws Exception {
        UserRole role = new UserRole("ADMIN");

        AppUser user = new AppUser("test@test.com", "Test", "User", "admin",
               "Salty_123$", true, role);

        try{
            userService.addUser(user);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}
