package com.example.menu.util;

import com.example.menu.entities.UserManager;
import com.example.menu.entities.UserManagerMember;
import com.example.menu.repository.UserManagerMemberRepository;
import com.example.menu.repository.UserManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ContextUtil {

    @Autowired
    private UserManagerRepository userManagerRepository;

    @Autowired
    private UserManagerMemberRepository userManagerMemberRepository;

    public Optional<UserManager> getUserDataFromContext() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserManager optUserData = userManagerRepository.findByUserName(user.getUsername());
        if(optUserData == null){
            return Optional.empty();
        } else {
            return Optional.of(optUserData);
        }
    }

    public Optional<UserManagerMember> getUserDataFromContext2() { //TEST
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); // TEST
        UserManagerMember optUserData = userManagerMemberRepository.findByMemberName(user.getUsername()); // TEST
        if(optUserData == null){ // TEST
            return Optional.empty(); // TEST
        } else { //TEST
            return Optional.of(optUserData); //TEST
        } // TEST
    } // TEST

}
