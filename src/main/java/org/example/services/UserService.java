package org.example.services;

import jakarta.transaction.Transactional;

import org.example.JPARepositories.IUserRepository;
import org.example.JPARepositories.RoleRepository;
import org.example.model.Cart;
import org.example.model.Role;
import org.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    IUserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Transactional
    public String registerUser(User user) {
        if (userRepository.existsByUsername(user.getUsername()))
            return "Failure";
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCart(new Cart());
        Role userRole = roleRepository.findByName("USER").orElseGet( ()->null);
        if (userRole != null){
            user.getRoles().add(userRole);
        } else{
            Role role = new Role();
            role.setName("USER");
            user.getRoles().add(role);
        }
        userRepository.save(user);
        return "success";
    }

    public User getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails)principal).getUsername();
        return userRepository.findByUsername(username).orElse(null);
    }
}
