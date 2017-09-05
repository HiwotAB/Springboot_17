package com.hiwotab.springboot17.Controller;

import com.hiwotab.springboot17.Model.Role;
import com.hiwotab.springboot17.Model.User;
import com.hiwotab.springboot17.Repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

import java.util.HashSet;
import java.util.Set;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class HomeController {

    @Autowired
    UserRepo userRepo;
    @RequestMapping("/")
    public String showHomePage() {
        return "index";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/admin")
    public String admin() {
        return "admin";
    }
    @RequestMapping("/secure")
    public String secure() {
        return "secure";
    }
    @GetMapping("/signUpForm")
    public String addUser(Model model) {
        model.addAttribute("newUser", new User());
        return "signUpForm";
    }
    @PostMapping("/signUpForm")
    public String addUserConfirm(@Valid @ModelAttribute("newUser") User user,  BindingResult bindingResult ,Model model) {
        if (bindingResult.hasErrors()) {
            return "signUpForm";
        }
        user.setEnabled(true);
        Role role=new Role();
        role.setRole("USER");
        Set<Role> roleSet = new HashSet<Role>();
        roleSet.add(role);
        user.setRoles(roleSet);
        userRepo.save(user);
        return "signUpConfirm";
    }

}