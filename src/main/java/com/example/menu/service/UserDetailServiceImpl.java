package com.example.menu.service;

import com.example.menu.entities.UserManager;
import com.example.menu.repository.UserManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserManagerRepository userManagerRepository;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserManager userManager = userManagerRepository.findByUserName(s);
        if (userManager == null) {
            throw new UsernameNotFoundException(s);
        }
        return new User(userManager.getUserName(), "", getAuthority());
    }

    private List<SimpleGrantedAuthority> getAuthority() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }
}
