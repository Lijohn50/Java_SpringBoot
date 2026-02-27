package org.example.spring_security.service;

import org.example.spring_security.model.UserPrinciple;
import org.example.spring_security.model.Users;
import org.example.spring_security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetail implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Users user = repository.findByUsername(username);
        if(user == null){

            System.out.println("user not found");
            throw new UsernameNotFoundException("user not found");
        }else{

         return new UserPrinciple(user);
        }
    }
}
