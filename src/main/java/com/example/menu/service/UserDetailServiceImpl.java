package com.example.menu.service;

import com.example.menu.entities.UserManager;
import com.example.menu.entities.UserManagerMember;
import com.example.menu.repository.UserManagerMemberRepository;
import com.example.menu.repository.UserManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.ParameterResolutionDelegate;
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

    @Autowired // TEST
    private UserManagerMemberRepository userManagerMemberRepository; // TEST


    /*@Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserManager userManager = userManagerRepository.findByUserName(s);
        if (userManager == null) {
            throw new UsernameNotFoundException(s);
        }
        return new User(userManager.getUserName(), "", getAuthority());
    }*/

    @Override // TEST
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException { // TEST
        UserManager userManager = userManagerRepository.findByUserName(s); // TEST
        UserManagerMember userManagerMember = userManagerMemberRepository.findByMemberName(s); // TEST
        if (userManager != null) { // TEST
            return new User(userManager.getUserName(), "", getAuthority()); // TEST
        }else if(userManagerMember != null) { // TEST
            return new User(userManagerMember.getMemberName(), "", getAuthority()); // TEST
        } // TEST
        throw new UsernameNotFoundException(s); // TEST
    }

    private List<SimpleGrantedAuthority> getAuthority() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }
}
