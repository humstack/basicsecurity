package com.basicsecurity.controller;

import com.basicsecurity.model.AppUser;
import com.basicsecurity.service.AppUserService;
import com.basicsecurity.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class RegistrationController {

    @Autowired
    private AppUserService userService;
    
    @Autowired
    private UserRoleService roleService;
    
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("users", userService.findAll()); //Get all users and display list

        return "index";
    }

    @GetMapping("/user/{id}")
    public String getUser(@PathVariable("id") int id, Model model, RedirectAttributes attributes){
        Optional<AppUser> user = userService.findById(id);

        if (user.isEmpty()){
            attributes.addFlashAttribute("User not found");
            return "redirect:/";
        }

        model.addAttribute("user", user.get());

        return "view-user";
    }

    @GetMapping("/user/add")
    public String addUser(Model model) {
        AppUser appUser = new AppUser();

        //appUser.setRole(new UserRole());

        model.addAttribute("user", appUser);

        model.addAttribute("roles", roleService.findAll());
    
        return "add-user";
    }

    @PostMapping("/user/add")
    public String addUser(@Valid @ModelAttribute("user") AppUser appUser,
                          BindingResult result, Model model, RedirectAttributes attributes) {

        if (result.hasErrors()) {

            model.addAttribute("roles", roleService.findAll());
            return "add-user";
        }

        try {
            userService.addUser(appUser);
        } catch (IllegalArgumentException ex) {
            result.addError(new FieldError("appUser", "username", ex.getMessage()));
            model.addAttribute("roles", roleService.findAll());

            return "add-user";
        }

        return "redirect:/";
    }

    @GetMapping("/user/edit/{id}")
    public String editUser(@PathVariable("id") int id, Model model, RedirectAttributes attributes) {

        Optional<AppUser> user = userService.findById(id);

        if (user.isEmpty()) {
            attributes.addFlashAttribute("errors", "User does not exist");
            return "redirect:/";
        }

        model.addAttribute("roles", roleService.findAll());

        model.addAttribute("usr", user.get());

        return "edit-user";
    }

    @PostMapping("/user/edit/{id}")
    public String editUser(@PathVariable("id") int id, @Valid @ModelAttribute("usr") AppUser appUser,
                           BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("roles", roleService.findAll());
            return "edit-user";
        }

        try {
            userService.editUser(id, appUser);
        } catch (IllegalArgumentException ex) {
            model.addAttribute("roles", roleService.findAll());
            result.addError(new FieldError("appUser", "username", ex.getMessage()));
            return "edit-user";
        }

        return "redirect:/";
    }

    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") int id, Model model, RedirectAttributes attributes){
        Optional<AppUser> user = userService.findById(id);
    
        if (user.isEmpty()) {
            attributes.addFlashAttribute("errors", "User does not exist");
            return "redirect:/";
        }
        
        model.addAttribute("usr", user.get());
        
        return "delete-user";
    }
    
    @PostMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id")int id, AppUser appUser, Model model){
        
        try {
            userService.deleteUser(id, appUser);
        }catch (IllegalArgumentException ex) {
            model.addAttribute("errors", ex.getMessage());
            return "redirect:/";
        }

        return "redirect:/";
    }
}
