package org.practice.security_1.service;

import org.practice.security_1.model.User;
import org.practice.security_1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private JWTService jwt;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository repository;

    public void registerUser(User user) {

        repository.save(user);
    }

    public String verify(User user) {

        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

        if(authentication.isAuthenticated()){

            return jwt.generateToken(user.getUsername());
        }else{

            return "failed";
        }
    }
}
