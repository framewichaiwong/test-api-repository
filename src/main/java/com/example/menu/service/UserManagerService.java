package com.example.menu.service;

import com.example.menu.entities.UserManager;
import com.example.menu.repository.UserManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class UserManagerService {

    @Autowired
    private UserManagerRepository userManagerRepository;

    public UserManager saveUserManager(UserManager userManager) {
        return userManagerRepository.save(userManager);
    }

    public UserManager loginByUserNameAndPassWord(String userName, String passWord) {
        return userManagerRepository.findByUserNameAndPassWord(userName,passWord);
    }

    public UserManager updateUserManager(UserManager userManager) {
        return userManagerRepository.save(userManager);
    }

    public Optional<UserManager> listUserManagerByManagerId(int managerId) {
        return userManagerRepository.findById(managerId);
    }

    //----------------------------------------------------------------------------------------
    // Set of customer

    public Optional<UserManager> listUserManagerId(int managerId) {
        return userManagerRepository.findById(managerId);
    }
}